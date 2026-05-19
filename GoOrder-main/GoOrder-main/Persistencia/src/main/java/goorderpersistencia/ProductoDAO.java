
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
import java.util.LinkedList;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author
 */
public class ProductoDAO implements ICatalogoProductosDAO,IProductosDAO {
    
    private static final String NOMBRE_COLECCION = "Productos";
//    private List<Producto> productos;

    public ProductoDAO() {
//        productos = new ArrayList<>();
//        productos.add(new Producto("1",(new ImagenSerializada("latte_vainilla.png","png")),"Latte", "Un tipo de cafe", 50.00,DISPONIBLE,"1",100 ));
//        productos.add(new Producto("2",(new ImagenSerializada("panini_clasico.png","png")),"Paninni", "Queso y Jamon", 50.00,DISPONIBLE,"2",100 ));
//        productos.add(new Producto("3",(new ImagenSerializada("galleta_chispas.png","png")),"Galleta de chispas", "Galleta con chispas de chocolate", 15.00,DISPONIBLE,"2",100 ));
//
//        productos.add(new Producto("4",(new ImagenSerializada("espresso.png","png")),"Espresso", "Cafe fuerte", 30.00,DISPONIBLE,"1",100 ));
//        productos.add(new Producto("5",(new ImagenSerializada("capuccino.png","png")),"Capuccino", "Cafe espumado", 50.00,DISPONIBLE,"1",100 ));
//        productos.add(new Producto("6",(new ImagenSerializada("mocha.png","png")),"Mocha", "Cafe chocolate", 55.00,DISPONIBLE,"1",100));
//        productos.add(new Producto("7",(new ImagenSerializada("croissant.png","png")),"Croissant", "Pan mantequilla", 35.00,DISPONIBLE,"2",100));
//
//        productos.add(new Producto("8",(new ImagenSerializada("muffin.png","png")),"Muffin", "Pan dulce", 28.00,DISPONIBLE,"2",100 ));
//        productos.add(new Producto("9",(new ImagenSerializada("brownie.png","png")),"Brownie","Pastel chocolate", 32.00,DISPONIBLE,"2",100 ));
//        productos.add(new Producto("10",(new ImagenSerializada("sandwich.png","png")),"Sandwich", "Pan con jamon", 48.00,DISPONIBLE,"2",100 ));
//        
//        productos.add(new Producto("11",(new ImagenSerializada("bagel.png","png")),"Bagel", "Pan con queso", 40.00,DISPONIBLE,"2",100 ));      
    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto) throws PersistenciaException {
        List<Producto> resultado = new ArrayList<>();
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Producto> coleccion = this.obtenerColecciones(baseDatos);    
        
            List<Producto> productos = new LinkedList<>();
            coleccion.find().into(productos);
            for (Producto producto : productos) {
                if(producto.getNombre().equals(nombreProducto)){
                    resultado.add(producto);
                }
            }
        
        }
        return resultado;
    }

    @Override
    public List<Producto> listarProductos() throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Producto> coleccion = this.obtenerColecciones(baseDatos);    
        
            List<Producto> productos = new LinkedList<>();
            coleccion.find().into(productos);
            return productos;
        
        }
    }

    @Override
    public Producto registrarProducto(Producto nuevoProducto) throws PersistenciaException {
          try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Producto> coleccion = this.obtenerColecciones(baseDatos);

            coleccion.insertOne(nuevoProducto);
            
            return nuevoProducto; 
          }catch(Exception e){
              throw new PersistenciaException("Error al registrar el producto en la base de datos", e);
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

    @Override
    public Producto actualizarProducto(Producto productoActualizado) throws PersistenciaException {
            try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Producto> coleccion = this.obtenerColecciones(baseDatos);

            ObjectId idProducto = new ObjectId(productoActualizado.getId());
            Document filtro = new Document("_id", idProducto);

            Document docImagen = null;
            if (productoActualizado.getImagen() != null) {
                docImagen = new Document()
                    .append("datosImagen", productoActualizado.getImagen().getImagen())
                    .append("formato", productoActualizado.getImagen().getFormato());
            }

            String disponibilidadTexto = null;
            if (productoActualizado.getDisponibilidad() != null) {
                disponibilidadTexto = productoActualizado.getDisponibilidad().name();
            }

            // 3. Armamos el update completo
            Document datosActualizados = new Document("$set", new Document()
                .append("imagen", docImagen)
                .append("nombre", productoActualizado.getNombre())
                .append("descripcion", productoActualizado.getDescripcion())
                .append("precio", productoActualizado.getPrecio())
                .append("disponibilidad", disponibilidadTexto) 
                .append("idCategoria", productoActualizado.getIdcategoria()) 
                .append("stock", productoActualizado.getStock())
            );

            coleccion.updateOne(filtro, datosActualizados);

            return productoActualizado; 

        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el producto en la base de datos", e);
        }
    }

}