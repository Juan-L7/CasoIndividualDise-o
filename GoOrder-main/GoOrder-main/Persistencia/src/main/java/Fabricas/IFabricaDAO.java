/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Fabricas;

import Interfaces.ICatalogoCategoriasDAO;
import Interfaces.ICatalogoProductosDAO;
import Interfaces.IProductosDAO;
import goorderpersistencia.PersistenciaException;

/**
 *
 * @author juanl
 */
public interface IFabricaDAO {
    
    public abstract IProductosDAO crearProductosDAO() throws PersistenciaException;
    
    public abstract ICatalogoProductosDAO crearCatalogoProductosDAO() throws PersistenciaException;
    
    public abstract ICatalogoCategoriasDAO crearCatalogoCategoriasDAO() throws PersistenciaException;
}
