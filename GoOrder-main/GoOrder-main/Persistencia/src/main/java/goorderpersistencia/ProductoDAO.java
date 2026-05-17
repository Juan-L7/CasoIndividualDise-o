
package goorderpersistencia;

import ConexionBD.ManejadorConexiones;
import static ConexionBD.ManejadorConexiones.obtenerCodecs;
import Entidades.ImagenSerializada;
import Entidades.Producto;
import Enumeradores.Disponibilidad;
import static Enumeradores.Disponibilidad.DISPONIBLE;
import java.util.ArrayList;
import java.util.List;
import Interfaces.ICatalogoProductosDAO;
import Interfaces.IProductosDAO;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author
 */
public class ProductoDAO implements ICatalogoProductosDAO,IProductosDAO {
    
    private static final String NOMBRE_COLECCION = "Productos";
    private List<Producto> productos;

    public ProductoDAO() {
        productos = new ArrayList<>();
        productos.add(new Producto("1",(new ImagenSerializada("1","latte_vainilla.png","png")),"Latte", "Un tipo de cafe", 50.00,DISPONIBLE,"1",100 ));
        productos.add(new Producto("2",(new ImagenSerializada("2","panini_clasico.png","png")),"Paninni", "Queso y Jamon", 50.00,DISPONIBLE,"2",100 ));
        productos.add(new Producto("3",(new ImagenSerializada("3","galleta_chispas.png","png")),"Galleta de chispas", "Galleta con chispas de chocolate", 15.00,DISPONIBLE,"2",100 ));

        productos.add(new Producto("4",(new ImagenSerializada("4","espresso.png","png")),"Espresso", "Cafe fuerte", 30.00,DISPONIBLE,"1",100 ));
        productos.add(new Producto("5",(new ImagenSerializada("5","capuccino.png","png")),"Capuccino", "Cafe espumado", 50.00,DISPONIBLE,"1",100 ));
        productos.add(new Producto("6",(new ImagenSerializada("6","mocha.png","png")),"Mocha", "Cafe chocolate", 55.00,DISPONIBLE,"1",100));
        productos.add(new Producto("7",(new ImagenSerializada("7","croissant.png","png")),"Croissant", "Pan mantequilla", 35.00,DISPONIBLE,"2",100));

        productos.add(new Producto("8",(new ImagenSerializada("8","muffin.png","png")),"Muffin", "Pan dulce", 28.00,DISPONIBLE,"2",100 ));
        productos.add(new Producto("9",(new ImagenSerializada("9","brownie.png","png")),"Brownie","Pastel chocolate", 32.00,DISPONIBLE,"2",100 ));
        productos.add(new Producto("10",(new ImagenSerializada("10","sandwich.png","png")),"Sandwich", "Pan con jamon", 48.00,DISPONIBLE,"2",100 ));
        
        productos.add(new Producto("11",(new ImagenSerializada("11","bagel.png","png")),"Bagel", "Pan con queso", 40.00,DISPONIBLE,"2",100 ));      
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

    @Override
    public Producto registrarProducto(Producto nuevoProducto) throws PersistenciaException {
          try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Producto> coleccion = this.obtenerColecciones(baseDatos);

            coleccion.insertOne(nuevoProducto);
            
            return nuevoProducto; 
          }catch(Exception e){
              throw new PersistenciaException("Error al registrar el producto en MongoDB", e);
          }
    }
    
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
          
          MongoDatabase GoOrderBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
          
          return GoOrderBD;
    }

    public MongoCollection obtenerColecciones(MongoDatabase baseDatos) {
           MongoCollection<Producto> coleccionProductos = baseDatos.getCollection(NOMBRE_COLECCION,Producto.class);
           return coleccionProductos;

    }

}