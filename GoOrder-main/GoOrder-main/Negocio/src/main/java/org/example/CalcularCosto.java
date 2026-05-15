package org.example;

import GoOrderDTO.ProductoDTO;
import GoOrderDTO.CostoDTO;
import java.util.List;

public class CalcularCosto {

    private ICostoServicio servicio;

    public CalcularCosto(ICostoServicio servicio) {
        this.servicio = servicio;
    }

    public CostoDTO ejecutar(List<ProductoDTO> productos, double descuento) {
        return servicio.calcularCosto(productos, descuento);
    }
}