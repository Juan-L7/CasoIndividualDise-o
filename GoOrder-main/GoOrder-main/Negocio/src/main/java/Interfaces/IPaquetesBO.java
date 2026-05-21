/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import GoOrderDTO.NuevoPaqueteDTO;
import GoOrderDTO.PaqueteDTO;
import java.util.List;
import org.example.NegocioException;

/**
 *
 * @author juanl
 */
public interface IPaquetesBO {
    
    public abstract PaqueteDTO registrarPaquete(NuevoPaqueteDTO paquete) throws NegocioException;
    
    public abstract List<PaqueteDTO> listarPaquetes() throws NegocioException;
    
    public List<PaqueteDTO> buscarPaquetesDinamico(String nombre, Double precioMax) throws NegocioException;
    
    public PaqueteDTO actualizarPaquete(PaqueteDTO paqueteActualizado) throws NegocioException;
    
    public PaqueteDTO eliminarPaquete(String id) throws NegocioException;
}
