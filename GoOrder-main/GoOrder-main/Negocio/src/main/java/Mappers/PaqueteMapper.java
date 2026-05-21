/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import Entidades.DetallePaquete;
import GoOrderDTO.ItemPaqueteDTO;
import GoOrderDTO.PaqueteDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanl
 */
public class PaqueteMapper {
    
    public static GoOrderDTO.PaqueteDTO toNegocio(Entidades.Paquete p) {
    if (p == null) return null;

    // 1. Convertimos la lista interna de subdocumentos a DTOs
    List<ItemPaqueteDTO> productosDTO = new ArrayList<>();
    if (p.getListaProductos() != null) {
        for (DetallePaquete detalle : p.getListaProductos()) {
            ItemPaqueteDTO item = new ItemPaqueteDTO();
            item.setIdProducto(detalle.getId());
            item.setCantidad(detalle.getCantidad());
            productosDTO.add(item);
        }
    }

    GoOrderDTO.ImagenDTO imagenDTO = null;
    if (p.getImagen() != null) {
        imagenDTO = new GoOrderDTO.ImagenDTO(
                p.getImagen().getImagen(),
                p.getImagen().getFormato()
        );
    }

    return new GoOrderDTO.PaqueteDTO(
            p.getIdPaquete(),
            imagenDTO, 
            p.getNombre(),
            p.getPrecio(),
            p.getFechaInicioVigencia(),
            p.getFechaFinVigencia(),
            productosDTO
    );
}
}