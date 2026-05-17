/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTOs.ProductoDTO;
import Entidades.Producto;
import goorderpersistencia.PersistenciaException;

/**
 *
 * @author juanl
 */
public interface IProductosDAO {
    
    public abstract Producto registrarProducto(Producto nuevoProducto)throws PersistenciaException;
    
}
