
package Interfaces;

import Entidades.ProductoSeleccionado;
import GoOrderDTO.CarritoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoSeleccionadoDTO;
import org.example.NegocioException;

/**
 *
 * @author juanl
 */
public interface ICarritoBO {
    
    public abstract CarritoDTO AgregarProductoCarrito(ProductoSeleccionadoDTO producto) throws NegocioException;
    
    public abstract CarritoDTO LimpiarCarrito() throws NegocioException;
    
    public abstract CarritoDTO ObtenerCarrito() throws NegocioException;
    
    public abstract CarritoDTO IncrementarCantidad(ProductoSeleccionadoDTO producto) throws NegocioException;
    
    public abstract CarritoDTO DescrementarCantidad(ProductoSeleccionadoDTO producto) throws NegocioException;
    
    public abstract CarritoDTO AplicarDescuento(String codigo)throws NegocioException;
    
    public abstract CarritoDTO EliminarProductoCarrito(ProductoSeleccionadoDTO producto)throws NegocioException;
}
