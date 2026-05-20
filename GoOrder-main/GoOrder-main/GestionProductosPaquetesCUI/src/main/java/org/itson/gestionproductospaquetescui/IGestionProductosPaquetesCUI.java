/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.gestionproductospaquetescui;

import GoOrderDTO.CategoriaDTO;
import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.ProductoActualizadoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoDTOCom;
import java.util.List;
import org.example.NegocioException;

/**
 *
 * @author juanl
 */
public interface IGestionProductosPaquetesCUI  {
    
    public abstract ProductoDTO RegistrarProducto(NuevoProductoDTO nuevoProducto) throws NegocioException;
    
    public abstract List<CategoriaDTO> listarCategorias()throws NegocioException;
    
    public abstract ProductoDTO actualizarProducto(ProductoActualizadoDTO productoActualizado) throws NegocioException;
    
    public List<ProductoDTOCom> buscarProductosDinamico(String nombre, String idCategoria, Double precioMin, Double precioMax) throws NegocioException;

}
 