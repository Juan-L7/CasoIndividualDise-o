/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;
import java.util.List;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author juanl
 */
public class Paquete {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idPaquete;
    private ImagenSerializada imagen;
    private String nombre;
    private Double precio;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;
    private List<DetallePaquete> listaProductos;

    public Paquete() {
    }
    
    public Paquete(String idPaquete, ImagenSerializada imagen, String nombre, Double precio, LocalDate fechaInicioVigencia, LocalDate fechaFinVigencia, List<DetallePaquete> listaProductos) {
        this.idPaquete = idPaquete;
        this.imagen = imagen;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.listaProductos = listaProductos;
    }

    public String getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(String idPaquete) {
        this.idPaquete = idPaquete;
    }

    public ImagenSerializada getImagen() {
        return imagen;
    }

    public void setImagen(ImagenSerializada imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(LocalDate fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public LocalDate getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(LocalDate fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public List<DetallePaquete> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<DetallePaquete> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    
}
