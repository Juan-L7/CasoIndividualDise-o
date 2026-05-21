/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package goorderpersistencia;

import ConexionBD.ManejadorConexiones;
import static ConexionBD.ManejadorConexiones.obtenerCodecs;
import Entidades.Producto;
import Entidades.Venta;
import Interfaces.IVentasDAO;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;

/**
 *
 * @author juanl
 */
public class VentasDAO implements IVentasDAO{

    private static final String NOMBRE_COLECCION = "Ventas";
    
    public void registrarVenta(Venta venta) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Venta> coleccion = baseDatos.getCollection("ventas", Venta.class);
            
            coleccion.insertOne(venta);
            
        } catch (Exception e) {
            throw new PersistenciaException("Error al registrar la venta en la base de datos.", e);
        }
    }

    public List<Venta> obtenerVentasPorRango(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase baseDatos = this.obtenerBaseDatos(cliente);
            MongoCollection<Venta> coleccion = baseDatos.getCollection("ventas", Venta.class);

            Bson filtro = Filters.and(
                    Filters.gte("fechaVenta", fechaInicio),
                    Filters.lte("fechaVenta", fechaFin)
            );

            List<Venta> ventas = new ArrayList<>();
            coleccion.find(filtro).into(ventas);
            
            return ventas;
            
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar las ventas para el reporte.", e);
        }
    }
    
    @Override
    public List<Venta> obtenerVentas() throws PersistenciaException {

    try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

        MongoDatabase baseDatos = obtenerBaseDatos(cliente);

        MongoCollection<Venta> coleccion =
                baseDatos.getCollection("ventas", Venta.class);

        return coleccion.find().into(new ArrayList<>());

    } catch (Exception e) {

        throw new PersistenciaException(
                "Error al obtener ventas", e
        );
    }
}
    
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
          
          MongoDatabase GoOrderBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
          
          return GoOrderBD;
    }

    public MongoCollection obtenerColecciones(MongoDatabase baseDatos) {
           MongoCollection<Venta> coleccionVentas = baseDatos.getCollection(NOMBRE_COLECCION,Venta.class);
           return coleccionVentas;

    }
}

