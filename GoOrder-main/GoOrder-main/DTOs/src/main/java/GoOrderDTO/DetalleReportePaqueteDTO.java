/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GoOrderDTO;

/**
 *
 * @author juanl
 */
public class DetalleReportePaqueteDTO {

    private String nombrePaquete;
    private Integer cantidadVendida;
    private Double totalVenta;

    public DetalleReportePaqueteDTO() {
    }

    public DetalleReportePaqueteDTO(String nombrePaquete, Integer cantidadVendida, Double totalVenta) {
        this.nombrePaquete = nombrePaquete;
        this.cantidadVendida = cantidadVendida;
        this.totalVenta = totalVenta;
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
