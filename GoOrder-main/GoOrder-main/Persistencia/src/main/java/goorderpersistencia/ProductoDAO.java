
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
import com.mongodb.client.model.Filters;
import java.util.LinkedList;
import java.util.regex.Pattern;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author
 */
public class ProductoDAO implements ICatalogoProductosDAO,IProductosDAO {
    
    private static final String NOMBRE_COLECCION = "Productos";

    public ProductoDAO() {
    
    }


    @Override
    public List<Producto> buscarProducto(String nombreProducto) throws PersistenciaException {
        List<Producto> resultado = new ArrayList<>();
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Producto> coleccion = this.obtenerColecciones(baseDatos);    
        
            Bson filtro = Filters.eq("nombre", nombreProducto);
            coleccion.find(filtro).into(resultado);
        
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

            Document setUpdates = new Document()
                .append("nombre", productoActualizado.getNombre())
                .append("descripcion", productoActualizado.getDescripcion())
                .append("precio", productoActualizado.getPrecio())
                .append("idCategoria", productoActualizado.getIdcategoria()) 
                .append("stock", productoActualizado.getStock());

            if (productoActualizado.getDisponibilidad() != null) {
                setUpdates.append("disponibilidad", productoActualizado.getDisponibilidad().name());
            }

            if (productoActualizado.getImagen() != null && productoActualizado.getImagen().getImagen() != null) {
                Document docImagen = new Document()
                    .append("imagen", productoActualizado.getImagen().getImagen()) 
                    .append("formato", productoActualizado.getImagen().getFormato());
                
                setUpdates.append("imagen", docImagen);
            }

            Document datosActualizados = new Document("$set", setUpdates);
            com.mongodb.client.result.UpdateResult resultado = coleccion.updateOne(filtro, datosActualizados);

            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaException("No se encontró el producto con ID: " + productoActualizado.getId() + " en la BD.");
            }
            return productoActualizado; 
            
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el producto en la base de datos: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Producto> buscarProductosDinamico(String nombre, String idCategoria, Double precioMin, Double precioMax) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Producto> coleccion = this.obtenerColecciones(baseDatos);

            List<Bson> listaFiltros = new ArrayList<>();

            if (nombre != null && !nombre.trim().isEmpty()) {
                listaFiltros.add(Filters.regex("nombre", Pattern.quote(nombre.trim()), "i")); 
            }

            if (idCategoria != null && !idCategoria.trim().isEmpty() && !idCategoria.equalsIgnoreCase("TODAS")) {
                listaFiltros.add(Filters.eq("idcategoria", new ObjectId(idCategoria)));
            }

            if (precioMin != null) {
                listaFiltros.add(Filters.gte("precio", precioMin));
            }
            if (precioMax != null) {
                listaFiltros.add(Filters.lte("precio", precioMax));
            }

            Bson filtroFinal;
            if (listaFiltros.isEmpty()) {
                filtroFinal = new org.bson.Document(); 
            } else {
                filtroFinal = Filters.and(listaFiltros);
            }

            List<Producto> productosEncontrados = new ArrayList<>();
            coleccion.find(filtroFinal).into(productosEncontrados);

            return productosEncontrados;

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos con filtros combinados", e);
        }
    }

    @Override
    public Producto eliminarProducto(String id) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Producto> coleccion = this.obtenerColecciones(baseDatos);

            ObjectId idProducto = new ObjectId(id);
            Bson filtro = Filters.eq("_id", idProducto);

            Producto productoEliminado = coleccion.findOneAndDelete(filtro);

            if (productoEliminado == null) {
                throw new PersistenciaException("No se encontró el producto con ID: " + id + " para eliminar.");
            }

            return productoEliminado; 
        } catch (Exception e) {
            throw new PersistenciaException("Error al intentar eliminar el producto en la base de datos", e);
        }
    }
    
    @Override
    public Producto buscarProductoPorId(String idProducto) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Producto> coleccion = this.obtenerColecciones(baseDatos);    

            ObjectId idPro = new ObjectId(idProducto);
            Bson filtro = Filters.eq("_id", idPro);

            return coleccion.find(filtro).first();

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar el producto por ID", e);
        }
    }
}