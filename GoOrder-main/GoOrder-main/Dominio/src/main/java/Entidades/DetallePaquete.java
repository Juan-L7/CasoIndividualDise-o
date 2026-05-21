/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author juanl
 */
public class DetallePaquete {
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idProducto;
    private Integer cantidad;

    public DetallePaquete() {
    }
    
    public DetallePaquete(String idProducto, Integer cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public String getId() {
        return idProducto;
    }

    public void setId(String idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
