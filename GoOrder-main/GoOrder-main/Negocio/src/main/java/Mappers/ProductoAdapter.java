/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import Entidades.ImagenSerializada;
import Entidades.Producto;
import GoOrderDTO.ImagenDTO;
import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoDTOCom;

/**
 *
 * @author juanl
 */
public class ProductoAdapter {
    public static Producto convertirAEntidad(NuevoProductoDTO dto) {
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

        return new Producto(
                null, 
                imagenEntidad,
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecio(),
                dto.getDisponibilidad() != null ? Enumeradores.Disponibilidad.valueOf(dto.getDisponibilidad().name()) : null,
                dto.getIdcategoria(),
                dto.getStock()
        );
    }

    public static ProductoDTOCom convertirADTO(Producto entidad) {
        if (entidad == null) {
            return null;
        }

        ImagenDTO imagenDTO = null;
        if (entidad.getImagen() != null) {
            imagenDTO = new ImagenDTO(
                    entidad.getImagen().getImagen(),
                    entidad.getImagen().getFormato()
            );
        }

        return new ProductoDTOCom(
                entidad.getId(),
                imagenDTO,
                entidad.getNombre(),
                entidad.getDescripcion(),
                entidad.getPrecio(),
                entidad.getDisponibilidad() != null ? Enums.Disponibilidad.valueOf(entidad.getDisponibilidad().name()) : null,
                entidad.getIdcategoria(),
                entidad.getStock()
        );
    }
}
