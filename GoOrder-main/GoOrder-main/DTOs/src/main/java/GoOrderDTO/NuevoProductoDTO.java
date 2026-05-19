/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GoOrderDTO;

import Enums.Disponibilidad;

/**
 *
 * @author juanl
 */
public class NuevoProductoDTO {
    private ImagenDTO imagen;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Disponibilidad disponibilidad;
    private String idcategoria;
    private int stock;

    public NuevoProductoDTO() {
    }

    public NuevoProductoDTO(ImagenDTO imagen, String nombre, String descripcion, Double precio, Disponibilidad disponibilidad, String idcategoria, int stock) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.idcategoria = idcategoria;
        this.stock = stock;
    }

    public ImagenDTO getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public String getIdcategoria() {
        return idcategoria;
    }

    public int getStock() {
        return stock;
    }
    
    
}
