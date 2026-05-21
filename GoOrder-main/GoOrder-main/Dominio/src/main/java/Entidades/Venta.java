/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author juanl
 */
public class Venta {
    
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private LocalDate fechaVenta;
    private String idPaquete; 
    private String nombrePaquete; 
    private int cantidadVendida;
    private double totalVenta;

    public Venta() {}

    public Venta(LocalDate fechaVenta, String idPaquete, String nombrePaquete, int cantidadVendida, double totalVenta) {
        this.fechaVenta = fechaVenta;
        this.idPaquete = idPaquete;
        this.nombrePaquete = nombrePaquete;
        this.cantidadVendida = cantidadVendida;
        this.totalVenta = totalVenta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(String idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }
    
    
}