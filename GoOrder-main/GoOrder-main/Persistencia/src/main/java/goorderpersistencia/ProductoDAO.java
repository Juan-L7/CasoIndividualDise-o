
package goorderpersistencia;

import Entitys.Producto;
import Interfaces.IProductoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class ProductoDAO implements IProductoDAO {

    private List<Producto> productos;

    public ProductoDAO() {
        productos = new ArrayList<>();
        productos.add(new Producto("Latte", "Un tipo de cafe", 50.00, "latte_vainilla.png"));
        productos.add(new Producto("Paninni", "Queso y Jamon", 50.00, "panini_clasico.png"));
        productos.add(new Producto("Galleta de chispas", "Galleta con chispas de chocolate", 15.00, "galleta_chispas.png"));

        productos.add(new Producto("Espresso", "Cafe fuerte", 30.00, "espresso.png"));
        productos.add(new Producto("Capuccino", "Cafe espumado", 50.00, "capuccino.png"));
        productos.add(new Producto("Mocha", "Cafe chocolate", 55.00, "mocha.png"));
        productos.add(new Producto("Croissant", "Pan mantequilla", 35.00, "croissant.png"));

        productos.add(new Producto("Muffin", "Pan dulce", 28.00, "muffin.png"));
        productos.add(new Producto("Brownie", "Pastel chocolate", 32.00, "brownie.png"));
        productos.add(new Producto("Sandwich", "Pan con jamon", 48.00, "sandwich.png"));
        
        productos.add(new Producto("Bagel", "Pan con queso", 40.00, "bagel.png"));      
    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto) throws PersistenciaException {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p: productos) {
            if (p.getNombre().toLowerCase().contains(nombreProducto.toLowerCase())) {
                resultado.add(p);
            }
        }
        if (resultado.isEmpty()) {
            throw new PersistenciaException("Producto(s) no encontrado(s)");
        }
        return resultado;        
    }

    @Override
    public List<Producto> listarProductos() throws PersistenciaException {
        return productos;
    }

}