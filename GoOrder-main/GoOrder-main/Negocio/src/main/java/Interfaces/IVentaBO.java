/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import GoOrderDTO.CarritoDTO;
import GoOrderDTO.ReportePaquetesResumenDTO;
import GoOrderDTO.VentaDTO;
import java.time.LocalDate;
import java.util.List;
import org.example.NegocioException;

/**
 *
 * @author juanl
 */
public interface IVentaBO {
    
    public void registrarVenta(CarritoDTO carrito) throws NegocioException;
    
    public List<VentaDTO> obtenerVentasPorRango(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException;
    
    public ReportePaquetesResumenDTO generarDatosReporte() throws NegocioException;
}
