/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.gestionproductospaquetescui;

import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.ProductoDTO;
import org.example.NegocioException;

/**
 *
 * @author juanl
 */
public interface IGestionProductosPaquetesCUI {
    
    public abstract ProductoDTO RegistrarProducto(NuevoProductoDTO nuevoProducto) throws NegocioException;
}
