/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import Entidades.Paquete;
import Fachada.FachadaPersistencia;
import Fachada.IFachadaPersistencia;
import GoOrderDTO.NuevoPaqueteDTO;
import GoOrderDTO.PaqueteDTO;
import Interfaces.IPaquetesBO;
import Mappers.PaqueteAdapter;
import Mappers.PaqueteMapper;
import goorderpersistencia.PersistenciaException;

/**
 *
 * @author juanl
 */
public class PaquetesBO implements IPaquetesBO{

    private IFachadaPersistencia fachada;

    public PaquetesBO() {
        this.fachada = new FachadaPersistencia();
    }
    
    
    @Override
    public PaqueteDTO registrarPaquete(NuevoPaqueteDTO nuevopaquete) throws NegocioException {
        
        try {
            Paquete paqueteEntidad = PaqueteAdapter.convertirAEntidad(nuevopaquete);
            Entidades.Paquete entidadRegistrada;
            entidadRegistrada = fachada.registrarPaquete(paqueteEntidad);
            
            GoOrderDTO.PaqueteDTO productoRegistradoDTO = PaqueteMapper.toNegocio(entidadRegistrada);
            return productoRegistradoDTO;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el paquete en el sistema: " + ex.getMessage());
        }
        
    }
    
}
