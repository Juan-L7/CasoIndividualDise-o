
package goorderpersistencia;

import DTOs.SucursalDTO;
import Interfaces.ISucursalesDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanl
 */
public class SucursalesDAO implements ISucursalesDAO{

    @Override
    public List<SucursalDTO> consultarSucursales() throws PersistenciaException {
        List<SucursalDTO> sucursalesDisponibles = new ArrayList<>();
        sucursalesDisponibles.add(new SucursalDTO(1, "Cafe del compa miguel", "Colonia Cedros", "Casa blanca #456", "Sucursal mas cercana", "sucursal_casa.png"));
        sucursalesDisponibles.add(new SucursalDTO(2, "Cafe del compa miguel", "Colonia Esperanza", "Paris #765", " ", "sucursal_miguel.png"));
        sucursalesDisponibles.add(new SucursalDTO(3, "Cafe del compa miguel", "Colonia Libertad", "Boulevard Lincon", " ", "sucursal_cafesito.png"));
        return sucursalesDisponibles;
    }
    
}
