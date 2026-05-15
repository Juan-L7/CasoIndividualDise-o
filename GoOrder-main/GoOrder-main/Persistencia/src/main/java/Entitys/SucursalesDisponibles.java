package Entitys;

import GoOrderDTO.SucursalDTO;

import java.util.ArrayList;
import java.util.List;

public class SucursalesDisponibles {
    private List<SucursalDTO> sucursalesDisponibles;

    public SucursalesDisponibles() {
        this.sucursalesDisponibles = new ArrayList<>();
    }

    public void agregarSucursal(SucursalDTO sucursal){
        this.sucursalesDisponibles.add(sucursal);
    }

    public List<SucursalDTO> getSucursalesDisponibles() {
        return sucursalesDisponibles;
    }
}
