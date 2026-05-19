/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Fachada;

import Entidades.Categoria;
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
    
    public abstract List<Producto> buscarProducto(String nombre) throws PersistenciaException;
    
    public abstract List<Producto> listarProductos() throws PersistenciaException;
    
    public abstract List<Categoria> listarCategorias() throws PersistenciaException;
    
    public abstract Categoria buscarCategoriaPorId(String idCategoria) throws PersistenciaException;
}
