/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GoOrderDTO;

/**
 *
 * @author juanl
 */
public class ImagenDTO {
    private String imagen;
    private String formato;

    public ImagenDTO() {
    }
    
    public ImagenDTO(String imagen, String formato) {
        this.imagen = imagen;
        this.formato = formato;
    }

    public String getImagen() {
        return imagen;
    }

    public String getFormato() {
        return formato;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    
    
}
