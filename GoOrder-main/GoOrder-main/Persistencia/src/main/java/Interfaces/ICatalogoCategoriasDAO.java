/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Categoria;
import goorderpersistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author juanl
 */
public interface ICatalogoCategoriasDAO {
    
    public abstract List<Categoria> listaCategorias()throws PersistenciaException;
    
    public abstract Categoria buscarCategoriaPorId(String idCategoria) throws PersistenciaException;
    
}
