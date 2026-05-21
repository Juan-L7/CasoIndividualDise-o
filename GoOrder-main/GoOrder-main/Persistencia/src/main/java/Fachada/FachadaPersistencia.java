/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fachada;

import Entidades.Categoria;
import Entidades.Paquete;
import Entidades.Producto;
import Fabricas.FabricaDAOMongo;
import Fabricas.IFabricaDAO;
import Interfaces.ICatalogoCategoriasDAO;
import Interfaces.ICatalogoProductosDAO;
import Interfaces.IPaquetesDAO;
import Interfaces.IProductosDAO;
import goorderpersistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author juanl
 */
public class FachadaPersistencia implements IFachadaPersistencia{

    private final IFabricaDAO fabrica;

    public FachadaPersistencia() {
        this.fabrica = new FabricaDAOMongo();
    }
    
    
    @Override
    public Producto registrarProducto(Producto producto) throws PersistenciaException {
        IProductosDAO productoDAO = fabrica.crearProductosDAO();
        return productoDAO.registrarProducto(producto);
    }

    @Override
    public List<Producto> buscarProducto(String nombre) throws PersistenciaException {
        ICatalogoProductosDAO catalogoProductoDAO = fabrica.crearCatalogoProductosDAO();
        return catalogoProductoDAO.buscarProducto(nombre);
    }

    @Override
    public List<Producto> listarProductos() throws PersistenciaException {
        ICatalogoProductosDAO catalogoProductoDAO = fabrica.crearCatalogoProductosDAO();
        return catalogoProductoDAO.listarProductos();
    }

    @Override
    public List<Categoria> listarCategorias() throws PersistenciaException {
        ICatalogoCategoriasDAO catalogoCategoriasDAO = fabrica.crearCatalogoCategoriasDAO();
        return catalogoCategoriasDAO.listaCategorias();
    }

    @Override
    public Categoria buscarCategoriaPorId(String idCategoria) throws PersistenciaException {
        ICatalogoCategoriasDAO catalogoCategoriasDAO = fabrica.crearCatalogoCategoriasDAO();
        return catalogoCategoriasDAO.buscarCategoriaPorId(idCategoria);
    }

    @Override
    public Producto actualizarProducto(Producto productoActualizado) throws PersistenciaException {
        IProductosDAO productoDAO = fabrica.crearProductosDAO();
        return productoDAO.actualizarProducto(productoActualizado);    
    }

    @Override
    public List<Producto> buscarProductosDinamico(String nombre, String idCategoria, Double precioMin, Double precioMax) throws PersistenciaException {
        IProductosDAO productoDAO = fabrica.crearProductosDAO();
        return productoDAO.buscarProductosDinamico(nombre, idCategoria, precioMin, precioMax);
    }

    @Override
    public Producto eliminarProducto(String id) throws PersistenciaException {
        IProductosDAO productoDAO = fabrica.crearProductosDAO();
        return productoDAO.eliminarProducto(id);
    }

    @Override
    public Paquete registrarPaquete(Paquete paquete) throws PersistenciaException {
        IPaquetesDAO paquetesDAO = fabrica.crearPaquetesDAO();
        return paquetesDAO.registrarPaquete(paquete);
    }
    
}
