/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import Entidades.Venta;
import GoOrderDTO.CarritoDTO;
import GoOrderDTO.DetalleReportePaqueteDTO;
import GoOrderDTO.ProductoSeleccionadoDTO;
import GoOrderDTO.ReportePaqueteFilaDTO;
import GoOrderDTO.ReportePaquetesResumenDTO;
import GoOrderDTO.VentaDTO;
import Interfaces.IVentaBO;
import Interfaces.IVentasDAO;
import goorderpersistencia.PersistenciaException;
import goorderpersistencia.VentasDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanl
 */
public class VentaBO implements IVentaBO{

    private IVentasDAO ventasDAO;

    public VentaBO() {
        this.ventasDAO = new VentasDAO();
    }
    
    @Override
    public void registrarVenta(CarritoDTO carrito) throws NegocioException {
         try {

            for (ProductoSeleccionadoDTO producto : carrito.getProductos()) {

                Venta venta = new Venta();

                venta.setFechaVenta(LocalDate.now());

                
                venta.setNombrePaquete(
                        producto.getNombre()
                );

                venta.setCantidadVendida(
                        producto.getCantidad()
                );

                venta.setTotalVenta(
                        producto.getImporte()
                );

                ventasDAO.registrarVenta(venta);
            }

        } catch (Exception e) {

            throw new NegocioException(
                    "Error al registrar venta: " + e.getMessage()
            );
        }
    }

    @Override
    public List<VentaDTO> obtenerVentasPorRango(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
       try {

        List<Venta> ventas =
                ventasDAO.obtenerVentasPorRango(fechaInicio, fechaFin);

        List<VentaDTO> ventasDTO = new ArrayList<>();

        for (Venta venta : ventas) {

            VentaDTO dto = new VentaDTO();

            dto.setFechaVenta(venta.getFechaVenta());
            dto.setNombrePaquete(venta.getNombrePaquete());
            dto.setCantidadVendida(venta.getCantidadVendida());
            dto.setTotalVenta(venta.getTotalVenta());

            ventasDTO.add(dto);
        }

        return ventasDTO;
        
    }   catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener las venta: " + ex.getMessage()
            );
        }
    }

    @Override
public ReportePaquetesResumenDTO generarDatosReporte() throws NegocioException {

    try {

        List<Venta> ventas =
                ventasDAO.obtenerVentasPorRango(
                        LocalDate.of(2000, 1, 1),
                        LocalDate.now()
                );

        ReportePaquetesResumenDTO reporte =
                new ReportePaquetesResumenDTO();

        reporte.setFechaGeneracion(
                LocalDate.now().toString()
        );

        reporte.setRangoFechas(
                "Histórico"
        );

        reporte.setTotalPaquetesRegistrados(
                ventas.size()
        );

        int totalVendidos = 0;

        String masVendido = "";

        int mayorCantidad = 0;

        for (Venta venta : ventas) {

            totalVendidos += venta.getCantidadVendida();

            if (venta.getCantidadVendida() > mayorCantidad) {

                mayorCantidad = venta.getCantidadVendida();

                masVendido = venta.getNombrePaquete();
            }
        }

        reporte.setTotalPaquetesVendidos(
                totalVendidos
        );

        reporte.setPaqueteMasVendido(
                masVendido
        );

        List<ReportePaqueteFilaDTO> filas =
                new ArrayList<>();

        for (Venta venta : ventas) {

            ReportePaqueteFilaDTO fila =
                    new ReportePaqueteFilaDTO();

            fila.setNombre(
                    venta.getNombrePaquete()
            );

            fila.setProductos(
                    "Cantidad vendida: "
                    + venta.getCantidadVendida()
            );

            fila.setVigencia(
                    venta.getFechaVenta().toString()
            );

            fila.setPrecio(
                    "$" + venta.getTotalVenta()
            );

            filas.add(fila);
        }

        reporte.setDetallesTabla(filas);

        return reporte;

    } catch (Exception e) {

        throw new NegocioException(
                "Error al generar reporte: "
                + e.getMessage()
        );
    }
}
    
    
    
    

    
}
