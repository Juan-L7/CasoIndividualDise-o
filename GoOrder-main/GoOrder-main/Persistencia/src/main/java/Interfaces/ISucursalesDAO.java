package Interfaces;

import DTOs.SucursalDTO;
import goorderpersistencia.PersistenciaException;
import java.util.List;

public interface ISucursalesDAO {
    public List<SucursalDTO> consultarSucursales() throws PersistenciaException;
}