/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import GoOrderDTO.ProductoDTO;
import java.util.List;

/**
 *
 * @author juanl
 */
public class Carrito {
    
    private Long idCarrito;
    private List<ProductoSeleccionado> listaP;

    public Carrito(Long idCarrito, List<ProductoSeleccionado> listaP) {
        this.idCarrito = idCarrito;
        this.listaP = listaP;
    }

    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public List<ProductoSeleccionado> getListaP() {
        return listaP;
    }

    public void setListaP(List<ProductoSeleccionado> listaP) {
        this.listaP = listaP;
    }

   
    
    
            
}
