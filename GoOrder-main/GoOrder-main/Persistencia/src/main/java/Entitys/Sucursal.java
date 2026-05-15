 package Entitys;

public class Sucursal {
    private int sucursal_id;
    private String nombre;
    private String colonia;
    private String calle;
    private String descripcion;
    private String nombreImagen;

    public Sucursal(int sucursal_id, String nombre, String colonia, String calle, String descripcion, String nombreImagen) {
        this.sucursal_id = sucursal_id;
        this.nombre = nombre;
        this.colonia = colonia;
        this.calle = calle;
        this.descripcion = descripcion;
        this.nombreImagen = nombreImagen;
    }

    public int getSucursal_id() {
        return sucursal_id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCalle() {
        return calle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }
}
