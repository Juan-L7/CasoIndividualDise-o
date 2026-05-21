/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import Entidades.DetallePaquete;
import Entidades.ImagenSerializada;
import Entidades.Paquete;
import GoOrderDTO.ItemPaqueteDTO;
import GoOrderDTO.NuevoPaqueteDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanl
 */
public class PaqueteAdapter {
    public static Paquete convertirAEntidad(NuevoPaqueteDTO dto) {
        if (dto == null) {
            return null;
        }

        ImagenSerializada imagenEntidad = null;
        if (dto.getImagen() != null) {
            imagenEntidad = new ImagenSerializada(
                    dto.getImagen().getImagen(),
                    dto.getImagen().getFormato()
            );
        }

        List<DetallePaquete> listaDetalles = new ArrayList<>();
        if (dto.getListaProductos() != null) {
            for (ItemPaqueteDTO itemDTO : dto.getListaProductos()) {
                DetallePaquete detalle = new DetallePaquete(
                        itemDTO.getIdProducto(), 
                        itemDTO.getCantidad()
                );
                listaDetalles.add(detalle);
            }
        }

        return new Paquete(
                null, 
                imagenEntidad,
                dto.getNombre(),
                dto.getPrecio(),
                dto.getFechaInicioVigencia(),
                dto.getFechaFinVigencia(),
                listaDetalles
        );
    }
    
    
}

