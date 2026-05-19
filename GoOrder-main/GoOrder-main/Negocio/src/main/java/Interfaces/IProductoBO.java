
package Interfaces;

import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.ProductoActualizadoDTO;
import GoOrderDTO.ProductoDTO;
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
}
