/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTOs.ProductoDTO;
import Entidades.Producto;
import goorderpersistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author juanl
 */
public interface IProductosDAO {
    
    public abstract Producto registrarProducto(Producto nuevoProducto)throws PersistenciaException;
    
    public abstract Producto actualizarProducto(Producto productoActualizado) throws PersistenciaException;
    
    public abstract Producto eliminarProducto(String id) throws PersistenciaException;
    
    public List<Producto> buscarProductosDinamico(String nombre, String idCategoria, Double precioMin, Double precioMax) throws PersistenciaException;
}
