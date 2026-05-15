package Entitys;

public class Producto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;


    public Producto() {
    }

    public Producto(Long id, String nombre, String descripcion, Double precio, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Producto(String nombre, String descripcion, Double precio, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }


    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Double getPrecio() { return precio; }
    public String getImagen() { return imagen; }
    public String getDescripcion() {return descripcion;}

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}