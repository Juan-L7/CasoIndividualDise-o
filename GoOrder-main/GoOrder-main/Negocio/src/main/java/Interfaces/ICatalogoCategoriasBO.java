/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import GoOrderDTO.CategoriaDTO;
import java.util.List;
import org.example.NegocioException;

/**
 *
 * @author juanl
 */
public interface ICatalogoCategoriasBO {
    
    public abstract List<CategoriaDTO> listaCategorias() throws NegocioException;
    
    public abstract CategoriaDTO buscarCategoriaPorId(String idCategoria) throws NegocioException;
    
}
