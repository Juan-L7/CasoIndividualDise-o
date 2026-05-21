/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Fachada;

import Entidades.Categoria;
import Entidades.Paquete;
import Entidades.Producto;
import goorderpersistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author juanl
 */
public interface IFachadaPersistencia {
    
    public abstract Producto registrarProducto(Producto producto) throws PersistenciaException;
    
    public abstract Producto actualizarProducto(Producto productoActualizado) throws PersistenciaException;
    
    public List<Producto> buscarProductosDinamico(String nombre, String idCategoria, Double precioMin, Double precioMax) throws PersistenciaException;
    
    public abstract List<Producto> buscarProducto(String nombre) throws PersistenciaException;
    
    public abstract Producto eliminarProducto(String id) throws PersistenciaException;
    
    public abstract List<Producto> listarProductos() throws PersistenciaException;
    
    public abstract List<Categoria> listarCategorias() throws PersistenciaException;
    
    public abstract Categoria buscarCategoriaPorId(String idCategoria) throws PersistenciaException;
    
    public abstract Paquete registrarPaquete(Paquete paquete) throws PersistenciaException;
    
    public abstract List<Paquete> listarPaquetes() throws PersistenciaException;
    
    public Producto buscarProductoPorId(String idProducto) throws PersistenciaException;
    
    public List<Paquete> buscarPaquetesDinamico(String nombre, Double precioMax) throws PersistenciaException;
    
    public Paquete actualizarPaquete(Paquete paqueteActualizado) throws PersistenciaException;
    
    public Paquete eliminarPaquete(String id) throws PersistenciaException;
}
