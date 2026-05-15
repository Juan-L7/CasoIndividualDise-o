
package GoOrderDTO;

/**
 *
 * @author juanl
 */
public class ProductoSeleccionadoDTO {
    
    private String nombre;
    private Integer cantidad;
    private Double importe;
    private Double precioActual;
    private ProductoDTO producto;

    public ProductoSeleccionadoDTO(){        
    }
    
    public ProductoSeleccionadoDTO(String nombre, Integer cantidad, Double importe, Double precioActual, ProductoDTO producto) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.importe = importe;
        this.precioActual = precioActual;
        this.producto = producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(Double precioActual) {
        this.precioActual = precioActual;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }   
}