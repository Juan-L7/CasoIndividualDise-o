package org.example;

import GoOrderDTO.ProductoDTO;
import GoOrderDTO.CostoDTO;
import java.util.List;

public interface ICostoServicio {
    CostoDTO calcularCosto(List<ProductoDTO> productos, double descuento);
}