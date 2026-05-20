/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import Enums.Disponibilidad;

/**
 *
 * @author juanl
 */
public class ProductoAdatadorAProductoDTOCom {
    public static GoOrderDTO.ProductoDTOCom toNegocio(Entidades.Producto p) {
        if (p == null) {
            return null;
        }

        GoOrderDTO.ImagenDTO imagenAdaptada = null;
        
        if (p.getImagen() != null) {
            imagenAdaptada = new GoOrderDTO.ImagenDTO();
            
            imagenAdaptada.setImagen(p.getImagen().getImagen()); 
            imagenAdaptada.setFormato(p.getImagen().getFormato());
        }

        return new GoOrderDTO.ProductoDTOCom(
            p.getId(),
            imagenAdaptada, 
            p.getNombre(),
            p.getDescripcion(),
            p.getPrecio(),
            p.getDisponibilidad() != null ? Enums.Disponibilidad.valueOf(p.getDisponibilidad().name()) : null,
            p.getIdcategoria(),
            p.getStock()
        );
    }
}
