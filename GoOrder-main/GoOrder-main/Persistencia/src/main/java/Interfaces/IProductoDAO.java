
package Interfaces;

import Entitys.Producto;
import goorderpersistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author
 */
public interface IProductoDAO {
    
    public abstract List<Producto> buscarProducto(String nombreProducto) throws PersistenciaException;
    
    public abstract List<Producto> listarProductos() throws PersistenciaException;
    
}
