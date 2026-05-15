/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import GoOrderDTO.SucursalDTO;
import java.util.List;
import org.example.NegocioException;

/**
 *
 * @author juanl
 */
public interface ISucursalesBO {
    
    public List<SucursalDTO> consultarSucursales() throws NegocioException;
}
