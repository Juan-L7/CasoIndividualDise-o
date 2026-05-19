/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

/**
 *
 * @author juanl
 */
public class CategoriasMapper {
    public static GoOrderDTO.CategoriaDTO toNegocio(Entidades.Categoria c) {
        if (c == null) return null;
        return new GoOrderDTO.CategoriaDTO(
                c.getId(), 
                c.getNombre(), 
                c.getDescripcion()
              
        );
    }
}
