
package org.example;

import Entidades.Producto;
import Fachada.FachadaPersistencia;
import Fachada.IFachadaPersistencia;
import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.ProductoActualizadoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoDTOCom;
import Interfaces.IProductoBO;
import Mappers.ProductoMapper;
import goorderpersistencia.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import Interfaces.ICatalogoProductosDAO;
import Mappers.ProductoAdapter;
import Mappers.ProductoAdatadorAProductoDTOCom;

/**
 *
 * @author maild
 */
public class ProductoBO implements IProductoBO {

    private IFachadaPersistencia fachada;

    public ProductoBO() {
        this.fachada = new FachadaPersistencia();
    }

    @Override
    public List<GoOrderDTO.ProductoDTO> buscarProducto(String nombreProducto) throws NegocioException {
        
        if (nombreProducto == null) {
            throw new NegocioException("El texto de búsqueda no puede ser nulo.");
        }
        
        try {
            List<Producto> listaEntity = fachada.buscarProducto(nombreProducto);
            List<GoOrderDTO.ProductoDTO> listaDTo = new ArrayList<>();

            for (Producto p : listaEntity) {
                listaDTo.add(ProductoMapper.toNegocio(p));
            }

            return listaDTo;
        } catch (PersistenciaException e) {
            throw new NegocioException("No fue posible realizar la búsqueda.");
        }
    }

    @Override
    public List<GoOrderDTO.ProductoDTO> listarProductos() throws NegocioException {
        try {
            List<Producto> listaEntidades = fachada.listarProductos();
            List<GoOrderDTO.ProductoDTO> listaNegocio = new ArrayList<>();
            
            for (Producto p : listaEntidades) {
                listaNegocio.add(ProductoMapper.toNegocio(p));
            }
            return listaNegocio;
        } catch (PersistenciaException e) {
            throw new NegocioException("No fue posible consultar productos.");
        }
    }

    @Override
    public ProductoDTO registrarProducto(NuevoProductoDTO nuevoProducto) throws NegocioException {
    
        if (nuevoProducto == null) {
            throw new NegocioException("Error: Los datos del producto a registrar están vacíos.");
        }
        
        if (nuevoProducto.getNombre() == null || nuevoProducto.getNombre().trim().isEmpty()) {
            throw new NegocioException("Error: El nombre del producto es obligatorio.");
        }
        if (nuevoProducto.getPrecio() == null || nuevoProducto.getPrecio() <= 0) {
            throw new NegocioException("Error: El precio del producto debe ser mayor a cero.");
        }
        
        try {
            String idCategoria = nuevoProducto.getIdcategoria();
        
            if (idCategoria == null || idCategoria.trim().isEmpty()) {
                throw new NegocioException("Error: Debes seleccionar una categoría para registrar el producto.");
            }

            Entidades.Categoria categoriaExiste = fachada.buscarCategoriaPorId(idCategoria);

            if (categoriaExiste == null) {
                throw new NegocioException("Error: La categoría seleccionada no existe en el sistema.");
            }
            
            Entidades.Producto entidadAGuardar = ProductoAdapter.convertirAEntidad(nuevoProducto);
            Entidades.Producto entidadRegistrada;
            entidadRegistrada = fachada.registrarProducto(entidadAGuardar);

            GoOrderDTO.ProductoDTO productoRegistradoDTO = ProductoMapper.toNegocio(entidadRegistrada);
            return productoRegistradoDTO;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar el producto en el sistema: " + e.getMessage());
        }
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoActualizadoDTO productoActualizado) throws NegocioException {
        
        if (productoActualizado == null) {
            throw new NegocioException("Error: No se enviaron datos para actualizar.");
        }
        if (productoActualizado.getId() == null || productoActualizado.getId().trim().isEmpty()) {
            throw new NegocioException("Error: El ID del producto a actualizar es obligatorio.");
        }
        if (productoActualizado.getPrecio() != null && productoActualizado.getPrecio() < 0) {
            throw new NegocioException("Error: El precio no puede ser un valor negativo.");
        }
        
        Entidades.Producto entidadAActualizar = ProductoAdapter.convertirActualizadoAEntidad(productoActualizado);
        
        try {
            Producto producto = fachada.actualizarProducto(entidadAActualizar);
            GoOrderDTO.ProductoDTO productoRegistradoDTO = ProductoMapper.toNegocio(producto);
            return productoRegistradoDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar el producto en el sistema: " + ex.getMessage());
        }
    }

    @Override
    public List<ProductoDTOCom> buscarProductosDinamico(String nombre, String idCategoria, Double precioMin, Double precioMax) throws NegocioException {
 
        if (precioMin != null && precioMin < 0) {
            throw new NegocioException("Error: El precio mínimo no puede ser negativo.");
        }
        if (precioMax != null && precioMax < 0) {
            throw new NegocioException("Error: El precio máximo no puede ser negativo.");
        }
        if (precioMin != null && precioMax != null && precioMin > precioMax) {
            throw new NegocioException("Error: El precio mínimo no puede ser mayor al precio máximo.");
        }
        
        try {
            List<Producto> listaEntity = fachada.buscarProductosDinamico(nombre, idCategoria, precioMin, precioMax);
            List<GoOrderDTO.ProductoDTOCom> listaDTo = new ArrayList<>();

            for (Producto p : listaEntity) {
                listaDTo.add(ProductoAdatadorAProductoDTOCom.toNegocio(p));
            }

            return listaDTo;
        } catch (PersistenciaException e) {
            throw new NegocioException("No fue posible realizar la búsqueda dinámica.");
        }  
    }

    @Override
    public ProductoDTO eliminarProducto(String id) throws NegocioException {
        if (id == null || id.trim().isEmpty()) {
            throw new NegocioException("Error: Debes proporcionar un ID válido para eliminar el producto.");
        }
        
        Producto producto;
        try {
            producto = fachada.eliminarProducto(id);
            GoOrderDTO.ProductoDTO productoEliminadoDTO = ProductoMapper.toNegocio(producto);
            return productoEliminadoDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible eliminar el producto.");
        }
            
    }

}