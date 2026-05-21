
package Interfaces;

import Entidades.Producto;
import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.ProductoActualizadoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoDTOCom;
import goorderpersistencia.PersistenciaException;
import java.util.List;
import org.example.NegocioException;

/**
 *
 * @author 
 */
public interface IProductoBO {
    
    public abstract List<ProductoDTO> buscarProducto(String nombreProducto) throws NegocioException;
    
    public abstract List<ProductoDTO> listarProductos() throws NegocioException;

    public abstract ProductoDTO registrarProducto(NuevoProductoDTO nuevoProducto) throws NegocioException;
    
    public abstract ProductoDTO actualizarProducto(ProductoActualizadoDTO productoActualizado) throws NegocioException;
    
    public abstract ProductoDTO eliminarProducto(String id) throws NegocioException;
    
    public List<ProductoDTOCom> buscarProductosDinamico(String nombre, String idCategoria, Double precioMin, Double precioMax) throws NegocioException;
    
}
