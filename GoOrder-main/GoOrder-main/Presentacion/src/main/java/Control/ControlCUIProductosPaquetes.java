/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import static Enums.Disponibilidad.DISPONIBLE;
import GoOrderDTO.CategoriaDTO;
import GoOrderDTO.ImagenDTO;
import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoDTOCom;
import PantallasCUIProductosPaq.RegistroProducto;
import PantallasCUIProductosPaq.inicioAdmin;
import java.awt.Color;
import java.util.List;
import javax.swing.JFrame;
import org.example.NegocioException;
import org.itson.gestionproductospaquetescui.GestionProductosPaquetesCUI;
import org.itson.gestionproductospaquetescui.IGestionProductosPaquetesCUI;

/**
 *
 * @author juanl
 */
public class ControlCUIProductosPaquetes {
    public final Color COLOR_FONDO = new Color(18, 18, 18);
    public final Color COLOR_NEON = new Color(0, 255, 150);
    public final Color COLOR_TARJETA = new Color(35, 35, 35);
    public final Color COLOR_BOTON = new Color(35, 35, 35);
    public final Color COLOR_ERROR = new Color(255, 80, 80);
    public final Color COLOR_INPUT = new Color(25, 25, 25);
    public final Color COLOR_BORDE = new Color(60, 60, 60);
    private IGestionProductosPaquetesCUI cui;
    
    public ControlCUIProductosPaquetes() {
        this.cui =  new GestionProductosPaquetesCUI();
        
    }
    public ProductoDTO registrarProducto(NuevoProductoDTO nuevoProducto) throws NegocioException{
        return cui.RegistrarProducto(nuevoProducto);
    }
    
    public List<CategoriaDTO> listarCategoria()throws NegocioException{
        return cui.listarCategoria();
    }
      
    //Navegacion
    private JFrame ventanaActual = null;
    
    private void mostrarPantallas(JFrame nuevaVentana){
        if(ventanaActual != null){
            ventanaActual.dispose();
        }
        ventanaActual = nuevaVentana;
        ventanaActual.setVisible(true);
    }
    public void mostrarInicio(){
        mostrarPantallas(new inicioAdmin(this));
    }
    
    public void mostrarRegistroProducto(){
        mostrarPantallas(new RegistroProducto(this));
    }
    
    
    
    
}
