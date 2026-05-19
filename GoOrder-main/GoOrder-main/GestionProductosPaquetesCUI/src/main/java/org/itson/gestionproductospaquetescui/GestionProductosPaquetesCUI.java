/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.gestionproductospaquetescui;

import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.ProductoDTO;
import Interfaces.IProductoBO;
import org.example.NegocioException;
import org.example.ProductoBO;

/**
 *
 * @author juanl
 */
public class GestionProductosPaquetesCUI implements IGestionProductosPaquetesCUI {

    private IProductoBO productoBO;

    public GestionProductosPaquetesCUI() {
        this.productoBO = new ProductoBO();
    }
            
    
    
    @Override
    public ProductoDTO RegistrarProducto(NuevoProductoDTO nuevoProducto) throws NegocioException {
       
        try {
            return productoBO.registrarProducto(nuevoProducto);
        } catch (NegocioException e) {
            throw new NegocioException("No fue posible realizar listado de productos.");
        }
    } 
}
