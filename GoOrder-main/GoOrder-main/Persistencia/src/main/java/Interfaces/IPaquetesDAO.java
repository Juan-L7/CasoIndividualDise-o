/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Paquete;
import goorderpersistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author juanl
 */
public interface IPaquetesDAO {
    
    public abstract Paquete registrarPaquete(Paquete paquete) throws PersistenciaException;
    
    public abstract List<Paquete> listarPaquetes() throws PersistenciaException;
    
    public List<Paquete> buscarPaquetesDinamico(String nombre, Double precioMax) throws PersistenciaException ;
    
    public Paquete actualizarPaquete(Paquete paqueteActualizado) throws PersistenciaException;
    
    public Paquete eliminarPaquete(String id) throws PersistenciaException;
}
