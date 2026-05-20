/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PantallasCUIProductosPaq;
import Control.ControlCUIProductosPaquetes;
import utilerias.MenuLateral; 

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author juanl
 */

public class PantallaMenu extends JFrame {

    private ControlCUIProductosPaquetes control;
    private final Color COLOR_FONDO_VERDE = new Color(85, 239, 153);

    public PantallaMenu(ControlCUIProductosPaquetes control) {
        this.control = control;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("GoOrder - Inicio");
        setSize(450, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        MenuLateral panelMenu = new MenuLateral(this, control);
        add(panelMenu, BorderLayout.WEST); 

        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(COLOR_FONDO_VERDE);

        JLabel lblTitulo = new JLabel("Inicio");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 55));
        lblTitulo.setBounds(0, 70, 310, 70); 
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lblTitulo);

        // Agregamos el panel verde al centro
        add(panelPrincipal, BorderLayout.CENTER);
    }
}
