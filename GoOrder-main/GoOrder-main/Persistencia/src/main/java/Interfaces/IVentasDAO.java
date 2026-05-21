/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import Entidades.Venta;
import goorderpersistencia.PersistenciaException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author juanl
 */
public interface IVentasDAO {
    
    public void registrarVenta(Venta venta) throws PersistenciaException;
    
    public List<Venta> obtenerVentasPorRango(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    
    public List<Venta> obtenerVentas() throws PersistenciaException ;
}
