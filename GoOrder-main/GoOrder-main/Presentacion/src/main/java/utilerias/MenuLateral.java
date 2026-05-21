/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import Control.ControlCUIProductosPaquetes;
import PantallasCUIProductosPaq.BuscarProductoDialog;
import PantallasCUIProductosPaq.EliminarProducto;
import PantallasCUIProductosPaq.RegistroProducto;

import javax.swing.*;
import java.awt.*;

public class MenuLateral extends JPanel {

    private JFrame ventanaPadre;
    private ControlCUIProductosPaquetes control;

    public MenuLateral(JFrame ventanaPadre, ControlCUIProductosPaquetes control) {
        this.ventanaPadre = ventanaPadre;
        this.control = control;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(120, getHeight())); 
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createRigidArea(new Dimension(0, 80)));

        JButton btnRegistrar = crearBotonMenu("Registrar producto");
        btnRegistrar.addActionListener(e -> {
             ventanaPadre.dispose(); 
             new RegistroProducto(control).setVisible(true); 
        });
        add(btnRegistrar);
        add(Box.createRigidArea(new Dimension(0, 20))); 

        JButton btnEliminar = crearBotonMenu("Eliminar producto");
        btnEliminar.addActionListener(e -> {
            BuscarProductoDialog dialog = new BuscarProductoDialog(ventanaPadre, control, productoSeleccionado -> {
                
                 ventanaPadre.dispose();
                 new EliminarProducto(control, productoSeleccionado).setVisible(true);
                
            });
            dialog.setVisible(true);
        });
        add(btnEliminar);
        add(Box.createRigidArea(new Dimension(0, 20)));

        JButton btnActualizar = crearBotonMenu("Actualizar producto");
        btnActualizar.addActionListener(e -> {
            
            BuscarProductoDialog dialog = new BuscarProductoDialog(ventanaPadre, control, productoSeleccionado -> {
                
                 ventanaPadre.dispose();
                 new RegistroProducto(control, productoSeleccionado).setVisible(true);
                JOptionPane.showMessageDialog(ventanaPadre, "Vas a actualizar: " + productoSeleccionado.getNombre());
                
            });
            dialog.setVisible(true);
            
        });
        add(btnActualizar);
        add(Box.createRigidArea(new Dimension(0, 20)));

        JButton btnPaquetes = crearBotonMenu("Paquetes");
        btnPaquetes.addActionListener(e -> {
            control.mostrarPantallaRegistrarPaquete();
        });
        add(btnPaquetes);
    }

    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton("<html><div style='text-align: center;'>" + texto.replace(" ", "<br>") + "</div></html>");
        boton.setMaximumSize(new Dimension(100, 50)); 
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.BLACK);
        boton.setFont(new Font("Arial", Font.PLAIN, 12));
        boton.setFocusPainted(false);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }
}