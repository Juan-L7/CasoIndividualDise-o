
package Pattern;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Alex García Trejo
 */
public class IngresarImagen {
    
    public static void ingresarImagen(JLabel label, String nombreImagen, int ancho, int alto) {
        String rutaImagen = "/Resources/" + nombreImagen;
        URL imagen = IngresarImagen.class.getResource(rutaImagen);
        
        System.out.println("Intentando cargar la ruta: " + rutaImagen + " | Resultado URL: " + imagen);
        
        if (imagen != null) {
            label.setText("");
            ImageIcon icon = new ImageIcon(imagen);
            Image imagenEscalada = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(imagenEscalada));
        } else {
            label.setIcon(null);
            label.setText("SIN IMAGEN DISPONIBLE");
        }       
    }   
    
    public static void ingresarImagenBase64(JLabel label, String base64Imagen, int ancho, int alto) {
        if (base64Imagen != null && !base64Imagen.trim().isEmpty()) {
            try {
                byte[] bytesImagen = java.util.Base64.getDecoder().decode(base64Imagen);
                
                javax.swing.ImageIcon icon = new javax.swing.ImageIcon(bytesImagen);
                java.awt.Image imagenEscalada = icon.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);
                
                label.setText("");
                label.setIcon(new javax.swing.ImageIcon(imagenEscalada));
                
            } catch (IllegalArgumentException e) {
                label.setIcon(null);
                label.setText("IMAGEN CORRUPTA");
                System.err.println("Error decodificando Base64: " + e.getMessage());
            } catch (Exception e) {
                label.setIcon(null);
                label.setText("ERROR AL CARGAR");
            }
        } else {
            label.setIcon(null);
            label.setText("SIN IMAGEN DISPONIBLE");
        }
    }
}