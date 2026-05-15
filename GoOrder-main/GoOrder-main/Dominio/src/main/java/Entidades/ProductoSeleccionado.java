/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import GoOrderDTO.ProductoDTO;

/**
 *
 * @author juanl
 */
public class ProductoSeleccionado {
    
    private Integer id;
    private Integer cantidad;
    private Double importe;
    private Double precioActual;
    private Producto producto;

    public ProductoSeleccionado() {
    }

    
    public ProductoSeleccionado(Integer id, Integer cantidad, Double importe, Double precioActual, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.importe = importe;
        this.precioActual = precioActual;
        this.producto = producto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
