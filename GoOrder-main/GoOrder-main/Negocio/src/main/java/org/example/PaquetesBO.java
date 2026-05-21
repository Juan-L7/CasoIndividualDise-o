/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import Entidades.Paquete;
import Entidades.Producto;
import Fachada.FachadaPersistencia;
import Fachada.IFachadaPersistencia;
import GoOrderDTO.ItemPaqueteDTO;
import GoOrderDTO.NuevoPaqueteDTO;
import GoOrderDTO.PaqueteDTO;
import Interfaces.IPaquetesBO;
import Mappers.MapearItem;
import Mappers.PaqueteAdapter;
import Mappers.PaqueteMapper;
import goorderpersistencia.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanl
 */
public class PaquetesBO implements IPaquetesBO {

    private IFachadaPersistencia fachada;

    public PaquetesBO() {
        this.fachada = new FachadaPersistencia();
    }
    
    @Override
    public PaqueteDTO registrarPaquete(NuevoPaqueteDTO nuevopaquete) throws NegocioException {
        
        if (nuevopaquete == null) {
            throw new NegocioException("Error: Los datos del paquete están vacíos.");
        }
        if (nuevopaquete.getNombre() == null || nuevopaquete.getNombre().trim().isEmpty()) {
            throw new NegocioException("Error: El nombre del paquete es obligatorio.");
        }
        if (nuevopaquete.getPrecio() == null || nuevopaquete.getPrecio() <= 0) {
            throw new NegocioException("Error: El precio del paquete debe ser mayor a cero.");
        }
        if (nuevopaquete.getListaProductos() == null || nuevopaquete.getListaProductos().isEmpty()) {
            throw new NegocioException("Error: El paquete debe contener al menos un producto.");
        }
        
        try {
            Paquete paqueteEntidad = PaqueteAdapter.convertirAEntidad(nuevopaquete);
            Entidades.Paquete entidadRegistrada;
            entidadRegistrada = fachada.registrarPaquete(paqueteEntidad);
            
            GoOrderDTO.PaqueteDTO productoRegistradoDTO = PaqueteMapper.toNegocio(entidadRegistrada);
            return productoRegistradoDTO;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el paquete en el sistema: " + ex.getMessage());
        }
    }

    @Override
    public List<PaqueteDTO> listarPaquetes() throws NegocioException {
        try {
            List<Paquete> listaEntidades = fachada.listarPaquetes();
            List<GoOrderDTO.PaqueteDTO> listaNegocio = new ArrayList<>();

            for (Paquete p : listaEntidades) {
                GoOrderDTO.PaqueteDTO paqueteDTO = PaqueteMapper.toNegocio(p);
                
                if (paqueteDTO.getListaProductos() != null) {
                    for (GoOrderDTO.ItemPaqueteDTO item : paqueteDTO.getListaProductos()) {
                        
                        Producto productoBD = fachada.buscarProductoPorId(item.getIdProducto());
                        
                        if (productoBD != null) {
                            item.setNombre(productoBD.getNombre()); 
                        } else {
                            item.setNombre("Producto no encontrado");
                        }
                    }
                }
                listaNegocio.add(paqueteDTO);
            }
            return listaNegocio;
            
        } catch (PersistenciaException e) {
            e.printStackTrace();
            throw new NegocioException("No fue posible consultar los paquetes.");
        }
    }

    @Override
    public List<PaqueteDTO> buscarPaquetesDinamico(String nombre, Double precioMax) throws NegocioException {
        if (precioMax != null && precioMax < 0) {
            throw new NegocioException("Error: El precio máximo de búsqueda no puede ser negativo.");
        }

        try {
            List<Paquete> paquetesEntidad = fachada.buscarPaquetesDinamico(nombre, precioMax);
            List<PaqueteDTO> paquetesDTO = new ArrayList<>();
            
            for (Paquete p : paquetesEntidad) {
                PaqueteDTO dto = new PaqueteDTO();
                dto.setIdPaquete(p.getIdPaquete()); 
                dto.setNombre(p.getNombre());
                dto.setPrecio(p.getPrecio());
                
                if (p.getImagen() != null) {
                    GoOrderDTO.ImagenDTO imgDTO = new GoOrderDTO.ImagenDTO();
                    imgDTO.setImagen(p.getImagen().getImagen());
                    imgDTO.setFormato(p.getImagen().getFormato()); 
                    dto.setImagen(imgDTO);
                }
                
                dto.setListaProductos(MapearItem.mapearItems(p.getListaProductos())); 
                
                if (dto.getListaProductos() != null) {
                    for (ItemPaqueteDTO item : dto.getListaProductos()) {
                        Producto productoBD = fachada.buscarProductoPorId(item.getIdProducto());
                        if (productoBD != null) {
                            item.setNombre(productoBD.getNombre());
                        } else {
                            item.setNombre("Producto no encontrado");
                        }
                    }
                }
                
                paquetesDTO.add(dto);
            }
            return paquetesDTO;
        } catch (Exception ex) {
            throw new NegocioException("Error al filtrar paquetes.", ex);
        }
    }

    @Override
    public PaqueteDTO actualizarPaquete(PaqueteDTO paqueteActualizado) throws NegocioException {
        if (paqueteActualizado == null) {
            throw new NegocioException("Error: No se enviaron datos para actualizar el paquete.");
        }
        if (paqueteActualizado.getIdPaquete() == null || paqueteActualizado.getIdPaquete().trim().isEmpty()) {
            throw new NegocioException("Error: El ID del paquete a actualizar es obligatorio.");
        }
        if (paqueteActualizado.getPrecio() != null && paqueteActualizado.getPrecio() < 0) {
            throw new NegocioException("Error: El precio del paquete no puede ser negativo.");
        }

        Entidades.Paquete entidadAActualizar = PaqueteAdapter.convertirDTOAEntidad(paqueteActualizado);
        
        try {
            Paquete paquete = fachada.actualizarPaquete(entidadAActualizar);
            GoOrderDTO.PaqueteDTO paqueteRegistradoDTO = PaqueteMapper.toNegocio(paquete);
            return paqueteRegistradoDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar el paquete en el sistema: " + ex.getMessage());
        }
    }

    @Override
    public PaqueteDTO eliminarPaquete(String id) throws NegocioException {
        if (id == null || id.trim().isEmpty()) {
            throw new NegocioException("Error: Debes proporcionar un ID válido para eliminar el paquete.");
        }

        Paquete paquete;
        try {
            paquete = fachada.eliminarPaquete(id);
            GoOrderDTO.PaqueteDTO paqueteEliminadoDTO = PaqueteMapper.toNegocio(paquete);
            return paqueteEliminadoDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar el paquete en el sistema: " + ex.getMessage());
        }
    }
    
}
