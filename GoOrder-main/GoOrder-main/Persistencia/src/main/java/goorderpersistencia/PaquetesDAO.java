/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package goorderpersistencia;

import ConexionBD.ManejadorConexiones;
import static ConexionBD.ManejadorConexiones.obtenerCodecs;
import Entidades.Paquete;
import Interfaces.IPaquetesDAO;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.LinkedList;
import java.util.List;

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
    
}
