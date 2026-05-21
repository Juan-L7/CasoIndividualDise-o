/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import static Enums.Disponibilidad.DISPONIBLE;
import GoOrderDTO.CategoriaDTO;
import GoOrderDTO.ImagenDTO;
import GoOrderDTO.NuevoPaqueteDTO;
import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.PaqueteDTO;
import GoOrderDTO.ProductoActualizadoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoDTOCom;
import GoOrderDTO.VentaDTO;
import PantallasCUIProductosPaq.BuscarProductoDialog;
import PantallasCUIProductosPaq.EliminarProducto;
import PantallasCUIProductosPaq.PantallaMenu;
import PantallasCUIProductosPaq.PantallaPaquetes;
import PantallasCUIProductosPaq.RegistroPaquete;
import PantallasCUIProductosPaq.RegistroProducto;
import PantallasCUIProductosPaq.inicioAdmin;
import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JFrame;
import org.example.NegocioException;
import org.itson.gestionproductospaquetescui.GestionProductosPaquetesCUI;
import org.itson.gestionproductospaquetescui.IGestionProductosPaquetesCUI;
import org.itson.infraestructura.GeneradorReportesPDF;

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
    
    public List<CategoriaDTO> listarCategorias()throws NegocioException{
        return cui.listarCategorias();
    }
    
    public List<ProductoDTOCom> buscarProductosDinamico(String nombre, String idCategoria, Double precioMin, Double precioMax) throws NegocioException{
        return cui.buscarProductosDinamico(nombre, idCategoria, precioMin, precioMax);
    }
    
    public ProductoDTO actualizarProducto(ProductoActualizadoDTO productoActualizado) throws NegocioException{
        return cui.actualizarProducto(productoActualizado);
    }
    
    public ProductoDTO eliminarProducto(String id)throws NegocioException{
        return cui.eliminarProducto(id);
    }
    
    public PaqueteDTO registrarPaquete(NuevoPaqueteDTO paquete) throws NegocioException{
        return cui.registrarPaquete(paquete);
    }
    
    public List<PaqueteDTO> listarPaquetes() throws NegocioException{
        return cui.listarPaquetes();
    }
    
    public List<PaqueteDTO> buscarPaquetesDinamico(String nombre, Double precioMax) throws NegocioException{
        return cui.buscarPaquetesDinamico(nombre, precioMax);
    }
    
    public PaqueteDTO actualizarPaquete(PaqueteDTO paqueteActualizado) throws NegocioException {
        return cui.actualizarPaquete(paqueteActualizado);
    }
    
    public PaqueteDTO eliminarPaquete(String id) throws NegocioException{
        return cui.eliminarPaquete(id);
    }
    
    public List<VentaDTO> obtenerVentasPorRango(LocalDate inicio,LocalDate fin) throws NegocioException{
        return cui.obtenerVentasPorRango(inicio, fin);
    } 
    
    public void generarReportePDF() throws NegocioException{
            cui.generarReportePDF();
    }

    
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
    
    public void mostrarPantallaMenu(){
        mostrarPantallas(new PantallaMenu(this));
    }
    
    public void mostrarPantallaEliminarProducto(){
        mostrarPantallas(new EliminarProducto(this,null));
    }
    
    public void mostrarPantallaRegistrarPaquete(){
        mostrarPantallas(new RegistroPaquete(this));
    }
    
    public void mostrarPantallaPaquetes(){
        mostrarPantallas(new PantallaPaquetes(this));
    }
    
    
    
    
    
    
    
}
