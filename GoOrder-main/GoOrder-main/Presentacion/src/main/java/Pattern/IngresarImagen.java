
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
}