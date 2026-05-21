/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GoOrderDTO;

import java.time.LocalDate;

/**
 *
 * @author juanl
 */
public class VentaDTO {
    
    private String id;
    private LocalDate fechaVenta;
    private String idPaquete;
    private String nombrePaquete;
    private Integer cantidadVendida;
    private Double totalVenta;

    public VentaDTO() {
    }

    public VentaDTO(
            String id,
            LocalDate fechaVenta,
            String idPaquete,
            String nombrePaquete,
            Integer cantidadVendida,
            Double totalVenta
    ) {
        this.id = id;
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

    public Integer getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Integer cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }
}
