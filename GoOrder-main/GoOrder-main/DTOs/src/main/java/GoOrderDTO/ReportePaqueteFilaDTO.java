/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GoOrderDTO;

/**
 *
 * @author juanl
 */
public class ReportePaqueteFilaDTO {
    private String nombre;
    private String productos; 
    private String vigencia;  
    private String precio;    

    public ReportePaqueteFilaDTO() {
    }

    public ReportePaqueteFilaDTO(String nombre, String productos, String vigencia, String precio) {
        this.nombre = nombre;
        this.productos = productos;
        this.vigencia = vigencia;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
    
}
