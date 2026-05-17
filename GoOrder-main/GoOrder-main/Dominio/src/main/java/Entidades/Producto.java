
package Entidades;

import Enumeradores.Disponibilidad;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author 
 */
public class Producto {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private ImagenSerializada imagen;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Disponibilidad disponibilidad;
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idcategoria;
    private int stock;
    

    public Producto() {
    }

    public Producto(String id, ImagenSerializada imagen, String nombre, String descripcion, Double precio, Disponibilidad disponibilidad, String idcategoria, int stock) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.idcategoria = idcategoria;
        this.stock = stock;
    }

    public Producto(String nombre, String descripcion, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }      

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }        

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(String idcategoria) {
        this.idcategoria = idcategoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", disponibilidad=" + disponibilidad + ", imagen=" + imagen + '}';
    }
}