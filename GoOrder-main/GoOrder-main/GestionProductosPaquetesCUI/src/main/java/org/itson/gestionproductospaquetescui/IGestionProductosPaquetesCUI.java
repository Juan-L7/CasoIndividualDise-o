/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.gestionproductospaquetescui;

import GoOrderDTO.CategoriaDTO;
import GoOrderDTO.NuevoPaqueteDTO;
import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.PaqueteDTO;
import GoOrderDTO.ProductoActualizadoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoDTOCom;
import GoOrderDTO.VentaDTO;
import java.time.LocalDate;
import java.util.List;
import org.example.NegocioException;

/**
 *
 * @author juanl
 */
public interface IGestionProductosPaquetesCUI  {
    
    public abstract ProductoDTO RegistrarProducto(NuevoProductoDTO nuevoProducto) throws NegocioException;
    
    public abstract List<CategoriaDTO> listarCategorias()throws NegocioException;
    
    public abstract ProductoDTO actualizarProducto(ProductoActualizadoDTO productoActualizado) throws NegocioException;
    
    public List<ProductoDTOCom> buscarProductosDinamico(String nombre, String idCategoria, Double precioMin, Double precioMax) throws NegocioException;

    public abstract ProductoDTO eliminarProducto(String id) throws NegocioException;
    
    public PaqueteDTO registrarPaquete(NuevoPaqueteDTO nuevopaquete) throws NegocioException;
    
    public List<PaqueteDTO> listarPaquetes() throws NegocioException;
    
    public List<PaqueteDTO> buscarPaquetesDinamico(String nombre, Double precioMax) throws NegocioException;
    
    public PaqueteDTO actualizarPaquete(PaqueteDTO paqueteActualizado) throws NegocioException;
    
    public PaqueteDTO eliminarPaquete(String id) throws NegocioException;
    
    public List<VentaDTO> obtenerVentasPorRango(LocalDate inicio,LocalDate fin) throws NegocioException;
    
    public void generarReportePDF() throws NegocioException ;
    
}
 