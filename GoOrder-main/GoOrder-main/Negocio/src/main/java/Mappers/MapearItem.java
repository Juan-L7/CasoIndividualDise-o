/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import Entidades.DetallePaquete;
import GoOrderDTO.ItemPaqueteDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanl
 */
public class MapearItem {
    
    public static List<ItemPaqueteDTO> mapearItems(List<DetallePaquete> detallesEntidad) {
        List<ItemPaqueteDTO> itemsDTO = new ArrayList<>();
        
        if (detallesEntidad != null) {
            for (DetallePaquete detalle : detallesEntidad) {
                ItemPaqueteDTO itemDTO = new ItemPaqueteDTO();
                
                itemDTO.setIdProducto(detalle.getId()); 
                itemDTO.setCantidad(detalle.getCantidad());
                
                itemsDTO.add(itemDTO);
            }
        }
        return itemsDTO;
    }
}
