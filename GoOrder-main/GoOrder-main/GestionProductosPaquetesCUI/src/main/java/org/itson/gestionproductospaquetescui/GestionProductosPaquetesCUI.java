/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.gestionproductospaquetescui;

import GoOrderDTO.CategoriaDTO;
import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.ProductoActualizadoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoDTOCom;
import Interfaces.ICatalogoCategoriasBO;
import Interfaces.IProductoBO;
import java.util.List;
import org.example.CatalogoCategoriasBO;
import org.example.NegocioException;
import org.example.ProductoBO;

/**
 *
 * @author juanl
 */
public class GestionProductosPaquetesCUI implements IGestionProductosPaquetesCUI {

    private IProductoBO productoBO;
    private ICatalogoCategoriasBO categoriasBO;

    public GestionProductosPaquetesCUI() {
        this.productoBO = new ProductoBO();
        this.categoriasBO = new CatalogoCategoriasBO();
    }
            
    
    
    @Override
    public ProductoDTO RegistrarProducto(NuevoProductoDTO nuevoProducto) throws NegocioException {
       
        try {
            return productoBO.registrarProducto(nuevoProducto);
        } catch (NegocioException e) {
            e.printStackTrace(); 
            throw new NegocioException("Falló el BO: " + e.getMessage()); 
        }
    } 

    @Override
    public List<CategoriaDTO> listarCategorias() throws NegocioException {
           return categoriasBO.listaCategorias();
    }

    @Override
    public List<ProductoDTOCom> buscarProductosDinamico(String nombre, String idCategoria, Double precioMin, Double precioMax) throws NegocioException {
           return productoBO.buscarProductosDinamico(nombre, idCategoria, precioMin, precioMax);
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoActualizadoDTO productoActualizado) throws NegocioException {
            return productoBO.actualizarProducto(productoActualizado);
    }
}
 