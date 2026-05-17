/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import GoOrderDTO.ProductoDTO;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author juanl
 */
public class ProductoSeleccionado {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private Integer cantidad;
    private Double importe;
    private Double precioActual;
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idProducto;

    public ProductoSeleccionado() {
    }

    
    public ProductoSeleccionado(String id, Integer cantidad, Double importe, Double precioActual, String idProducto) {
        this.id = id;
        this.cantidad = cantidad;
        this.importe = importe;
        this.precioActual = precioActual;
        this.idProducto = idProducto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(Double precioActual) {
        this.precioActual = precioActual;
    }

    public String getidProducto() {
        return idProducto;
    }

    public void setidProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    
    
}
