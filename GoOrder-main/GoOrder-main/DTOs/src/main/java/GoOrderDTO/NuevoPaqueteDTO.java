/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GoOrderDTO;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author juanl
 */
public class NuevoPaqueteDTO {
    
    private ImagenDTO imagen;
    private String nombre;
    private Double precio;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;
    private List<ItemPaqueteDTO> listaProductos;

    public NuevoPaqueteDTO() {
    }

    public NuevoPaqueteDTO(ImagenDTO imagen, String nombre, Double precio, LocalDate fechaInicioVigencia, LocalDate fechaFinVigencia, List<ItemPaqueteDTO> listaProductos) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.listaProductos = listaProductos;
    }

    public ImagenDTO getImagen() {
        return imagen;
    }

    public void setImagen(ImagenDTO imagen) {
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

    public List<ItemPaqueteDTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ItemPaqueteDTO> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    
    
}
