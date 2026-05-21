/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GoOrderDTO;

import java.util.List;

/**
 *
 * @author juanl
 */
public class ReportePaquetesResumenDTO {
    private String fechaGeneracion;
    private String rangoFechas;
    private int totalPaquetesRegistrados;
    private int totalPaquetesVendidos;
    private String paqueteMasVendido;
    private List<ReportePaqueteFilaDTO> detallesTabla;

    public ReportePaquetesResumenDTO() {
    }

    public ReportePaquetesResumenDTO(String fechaGeneracion, String rangoFechas, int totalPaquetesRegistrados, int totalPaquetesVendidos, String paqueteMasVendido, List<ReportePaqueteFilaDTO> detallesTabla) {
        this.fechaGeneracion = fechaGeneracion;
        this.rangoFechas = rangoFechas;
        this.totalPaquetesRegistrados = totalPaquetesRegistrados;
        this.totalPaquetesVendidos = totalPaquetesVendidos;
        this.paqueteMasVendido = paqueteMasVendido;
        this.detallesTabla = detallesTabla;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getRangoFechas() {
        return rangoFechas;
    }

    public void setRangoFechas(String rangoFechas) {
        this.rangoFechas = rangoFechas;
    }

    public int getTotalPaquetesRegistrados() {
        return totalPaquetesRegistrados;
    }

    public void setTotalPaquetesRegistrados(int totalPaquetesRegistrados) {
        this.totalPaquetesRegistrados = totalPaquetesRegistrados;
    }

    public int getTotalPaquetesVendidos() {
        return totalPaquetesVendidos;
    }

    public void setTotalPaquetesVendidos(int totalPaquetesVendidos) {
        this.totalPaquetesVendidos = totalPaquetesVendidos;
    }

    public String getPaqueteMasVendido() {
        return paqueteMasVendido;
    }

    public void setPaqueteMasVendido(String paqueteMasVendido) {
        this.paqueteMasVendido = paqueteMasVendido;
    }

    public List<ReportePaqueteFilaDTO> getDetallesTabla() {
        return detallesTabla;
    }

    public void setDetallesTabla(List<ReportePaqueteFilaDTO> detallesTabla) {
        this.detallesTabla = detallesTabla;
    }
    
    
}
