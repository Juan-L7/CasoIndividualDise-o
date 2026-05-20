/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Observer;

import GoOrderDTO.ProductoDTOCom;

/**
 *
 * @author juanl
 */
public interface IProductoSeleccionadoObserver {
    public void onProductoSeleccionado(ProductoDTOCom producto);
}
