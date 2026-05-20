/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package goorderpersistencia;

import ConexionBD.ManejadorConexiones;
import static ConexionBD.ManejadorConexiones.obtenerCodecs;
import Entidades.Categoria;
import Entidades.Producto;
import Interfaces.ICatalogoCategoriasDAO;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author juanl
 */
public class CatalogoCategoriasDAO implements ICatalogoCategoriasDAO{

    private static final String NOMBRE_COLECCION = "Categorias";
    
    @Override
    public List<Categoria> listaCategorias() throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
                MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
                MongoCollection<Categoria> coleccion = this.obtenerColecciones(baseDatos);    

                List<Categoria> categorias = new LinkedList<>();
                coleccion.find().into(categorias);
                return categorias;

            }    
    }
    
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
          
          MongoDatabase GoOrderBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
          
          return GoOrderBD;
    }

    public MongoCollection obtenerColecciones(MongoDatabase baseDatos) {
           MongoCollection<Categoria> coleccionCategorias = baseDatos.getCollection(NOMBRE_COLECCION,Categoria.class);
           return coleccionCategorias;

    }

    @Override
    public Categoria buscarCategoriaPorId(String idCategoria) throws PersistenciaException {
        if (idCategoria == null || idCategoria.trim().isEmpty()) {
            return null;
        }

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Entidades.Categoria> coleccion = baseDatos.getCollection(NOMBRE_COLECCION, Categoria.class);

            ObjectId idMongo = new ObjectId(idCategoria);

            Categoria categoriaEncontrada = coleccion.find(Filters.eq("_id", idMongo)).first();

            return categoriaEncontrada; 

        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("El formato del ID de la categoría no es válido: " + idCategoria, e);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar la categoría en la base de datos", e);
        }
    }
    
}
