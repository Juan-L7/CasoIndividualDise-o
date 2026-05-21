/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package goorderpersistencia;

import ConexionBD.ManejadorConexiones;
import static ConexionBD.ManejadorConexiones.obtenerCodecs;
import Entidades.DetallePaquete;
import Entidades.Paquete;
import Interfaces.IPaquetesDAO;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author juanl
 */
public class PaquetesDAO implements IPaquetesDAO{

    private static final String NOMBRE_COLECCION = "Paquetes";
    
    @Override
    public Paquete registrarPaquete(Paquete paquete) throws PersistenciaException {
         try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Paquete> coleccion = this.obtenerColecciones(baseDatos);
            
            coleccion.insertOne(paquete);
            
            return paquete; 
          }catch(Exception e){
              throw new PersistenciaException("Error al registrar el paquete en la base de datos", e);
          }
    }
    
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
          
          MongoDatabase GoOrderBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
          
          return GoOrderBD;
    }

    public MongoCollection obtenerColecciones(MongoDatabase baseDatos) {
           MongoCollection<Paquete> coleccionPaquetes = baseDatos.getCollection(NOMBRE_COLECCION,Paquete.class);
           return coleccionPaquetes;

    }

    @Override
    public List<Paquete> listarPaquetes() throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Paquete> coleccion = this.obtenerColecciones(baseDatos);    
            
            List<Paquete> listaPaquetes = new LinkedList<>();
            coleccion.find().into(listaPaquetes);
            return listaPaquetes;
            
        }catch(Exception e){
              throw new PersistenciaException("Error al consultar los paquetes en la base de datos", e);
          }
    }
    
    @Override
    public List<Paquete> buscarPaquetesDinamico(String nombre, Double precioMax) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Paquete> coleccion = this.obtenerColecciones(baseDatos); 

            List<Bson> listaFiltros = new ArrayList<>();

            if (nombre != null && !nombre.trim().isEmpty()) {
                listaFiltros.add(Filters.regex("nombre", Pattern.quote(nombre.trim()), "i")); 
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

            List<Paquete> paquetesEncontrados = new ArrayList<>();
            coleccion.find(filtroFinal).into(paquetesEncontrados);

            return paquetesEncontrados;

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar paquetes por nombre y precio", e);
        }
    }
    
    @Override
    public Paquete actualizarPaquete(Paquete paqueteActualizado) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Paquete> coleccion = this.obtenerColecciones(baseDatos);

            ObjectId idPaquete = new ObjectId(paqueteActualizado.getIdPaquete());
            Document filtro = new Document("_id", idPaquete);

            Document setUpdates = new Document()
                .append("nombre", paqueteActualizado.getNombre())
                .append("precio", paqueteActualizado.getPrecio());

            if (paqueteActualizado.getFechaInicioVigencia() != null) {
                setUpdates.append("fechaInicioVigencia", paqueteActualizado.getFechaInicioVigencia());
            }
            if (paqueteActualizado.getFechaFinVigencia() != null) {
                setUpdates.append("fechaFinVigencia", paqueteActualizado.getFechaFinVigencia());
            }

            if (paqueteActualizado.getListaProductos() != null) {
                List<Document> docListaProductos = new ArrayList<>();
                for (DetallePaquete detalle : paqueteActualizado.getListaProductos()) {
                    Document docDetalle = new Document()
                        .append("_id", detalle.getId()) 
                        .append("cantidad", detalle.getCantidad());
                    docListaProductos.add(docDetalle);
                }
                setUpdates.append("listaProductos", docListaProductos);
            }

            if (paqueteActualizado.getImagen() != null && paqueteActualizado.getImagen().getImagen() != null) {
                Document docImagen = new Document()
                    .append("imagen", paqueteActualizado.getImagen().getImagen()) 
                    .append("formato", paqueteActualizado.getImagen().getFormato());
                
                setUpdates.append("imagen", docImagen);
            }

            Document datosActualizados = new Document("$set", setUpdates);
            com.mongodb.client.result.UpdateResult resultado = coleccion.updateOne(filtro, datosActualizados);

            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaException("No se encontró el paquete con ID: " + paqueteActualizado.getIdPaquete() + " en la BD.");
            }
            
            return paqueteActualizado; 
            
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el paquete en la base de datos: " + e.getMessage(), e);
        }
    }

    @Override
    public Paquete eliminarPaquete(String id) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Paquete> coleccion = this.obtenerColecciones(baseDatos);

            ObjectId idPaquete = new ObjectId(id);
            Bson filtro = Filters.eq("_id", idPaquete);

            Paquete paqueteEliminado = coleccion.findOneAndDelete(filtro);

            if (paqueteEliminado == null) {
                throw new PersistenciaException("No se encontró el paquete con ID: " + id + " para eliminar.");
            }

            return paqueteEliminado; 

        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("El ID del paquete proporcionado no tiene un formato válido.", e);
        } catch (Exception e) {
            throw new PersistenciaException("Error al intentar eliminar el paquete en la base de datos.", e);
        }
    }
    
}
