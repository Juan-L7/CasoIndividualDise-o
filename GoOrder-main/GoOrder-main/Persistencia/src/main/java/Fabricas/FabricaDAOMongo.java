/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas;

import Interfaces.ICatalogoCategoriasDAO;
import Interfaces.ICatalogoProductosDAO;
import Interfaces.IPaquetesDAO;
import Interfaces.IProductosDAO;
import goorderpersistencia.CatalogoCategoriasDAO;
import goorderpersistencia.PaquetesDAO;
import goorderpersistencia.PersistenciaException;
import goorderpersistencia.ProductoDAO;

/**
 *
 * @author juanl
 */
public class FabricaDAOMongo implements IFabricaDAO{

    @Override
    public IProductosDAO crearProductosDAO() throws PersistenciaException {
        return new ProductoDAO();
    }

    @Override
    public ICatalogoProductosDAO crearCatalogoProductosDAO() throws PersistenciaException {
        return new ProductoDAO();
    }

    @Override
    public ICatalogoCategoriasDAO crearCatalogoCategoriasDAO() throws PersistenciaException {
        return new CatalogoCategoriasDAO();
    }

    @Override
    public IPaquetesDAO crearPaquetesDAO() throws PersistenciaException {
        return new PaquetesDAO();
    }
    
}
