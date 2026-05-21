package PantallasCUIProductosPaq;

import Control.ControlCUIProductosPaquetes;
import GoOrderDTO.PaqueteDTO; 
import GoOrderDTO.ItemPaqueteDTO;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.example.NegocioException;
import utilerias.MenuLateral;

/**
 * Pantalla para consultar, filtrar y administrar la lista de paquetes.
 */
public class PantallaPaquetes extends JFrame {

    private ControlCUIProductosPaquetes control;

    private final Color COLOR_FONDO_VERDE = new Color(85, 239, 153); 
    private final Color COLOR_BOTON_NEGRO = Color.BLACK;
    private final Color COLOR_TEXTO_BLANCO = Color.WHITE;

    private JTextField txtFiltroNombre;
    private JTextField txtFiltroPrecio;
    private JPanel panelPaquetesContenedor;
    private JButton btnBuscar;
    private JButton btnReporte;
    private JButton btnNuevoPaquete;

    public PantallaPaquetes(ControlCUIProductosPaquetes control) {
        this.control = control;
        inicializarComponentes();
        cargarPaquetesIniciales();
    }

    private void inicializarComponentes() {
        setTitle("GoOrder - Paquetes");
        setSize(400, 650); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_FONDO_VERDE);
        setLayout(null); 
        
        JLabel lblAtras = new JLabel("←");
        lblAtras.setForeground(Color.WHITE);
        lblAtras.setFont(new Font("Arial", Font.PLAIN, 30));
        lblAtras.setBounds(20, 20, 50, 30);
        lblAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose(); 
                control.mostrarPantallaMenu();
            }
        });
        MenuLateral panelMenu = new MenuLateral(this, control);
        panelMenu.setBounds(0, 0, 110, 650); 
        panelMenu.add(lblAtras);
        add(panelMenu); 

        Font fuenteTitulos = new Font("Arial", Font.BOLD, 36); 
        Font fuenteEtiquetas = new Font("Arial", Font.BOLD, 14);
        Font fuenteCampos = new Font("Arial", Font.PLAIN, 12);

        // Alineado a la izquierda, junto al menú
        JLabel lblTitulo = new JLabel("Paquetes");
        lblTitulo.setFont(fuenteTitulos);
        lblTitulo.setBounds(120, 15, 200, 40);
        add(lblTitulo);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(fuenteEtiquetas);
        lblNombre.setBounds(120, 70, 70, 25);
        add(lblNombre);

        txtFiltroNombre = new JTextField();
        txtFiltroNombre.setFont(fuenteCampos);
        txtFiltroNombre.setBounds(190, 70, 90, 25);
        txtFiltroNombre.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add(txtFiltroNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(fuenteEtiquetas);
        lblPrecio.setBounds(120, 100, 70, 25);
        add(lblPrecio);

        txtFiltroPrecio = new JTextField();
        txtFiltroPrecio.setFont(fuenteCampos);
        txtFiltroPrecio.setBounds(190, 100, 90, 25);
        txtFiltroPrecio.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add(txtFiltroPrecio);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(COLOR_BOTON_NEGRO);
        btnBuscar.setForeground(COLOR_TEXTO_BLANCO);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        btnBuscar.setBounds(290, 70, 80, 25);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorder(BorderFactory.createEmptyBorder());
        btnBuscar.addActionListener(e -> filtrarPaquetes());
        add(btnBuscar);

        panelPaquetesContenedor = new JPanel();
        panelPaquetesContenedor.setLayout(new BoxLayout(panelPaquetesContenedor, BoxLayout.Y_AXIS));
        panelPaquetesContenedor.setBackground(COLOR_FONDO_VERDE);
        
        JScrollPane scrollPaquetes = new JScrollPane(panelPaquetesContenedor);
        scrollPaquetes.setBounds(120, 140, 255, 410); 
        scrollPaquetes.setBorder(BorderFactory.createEmptyBorder());
        scrollPaquetes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaquetes.getVerticalScrollBar().setUnitIncrement(16); 
        add(scrollPaquetes);

        btnReporte = new JButton("Reporte de paquetes");
        btnReporte.setBackground(COLOR_BOTON_NEGRO);
        btnReporte.setForeground(COLOR_TEXTO_BLANCO);
        btnReporte.setFont(new Font("Arial", Font.BOLD, 11));
        btnReporte.setBounds(120, 565, 125, 30);
        btnReporte.setFocusPainted(false);
        btnReporte.setBorder(BorderFactory.createEmptyBorder());
        btnReporte.addActionListener(e -> {
            try {
                generarReporte();
            } catch (NegocioException ex) {
                System.getLogger(PantallaPaquetes.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
        add(btnReporte);

        btnNuevoPaquete = new JButton("Nuevo paquete");
        btnNuevoPaquete.setBackground(COLOR_BOTON_NEGRO);
        btnNuevoPaquete.setForeground(COLOR_TEXTO_BLANCO);
        btnNuevoPaquete.setFont(new Font("Arial", Font.BOLD, 11));
        btnNuevoPaquete.setBounds(250, 565, 125, 30);
        btnNuevoPaquete.setFocusPainted(false);
        btnNuevoPaquete.setBorder(BorderFactory.createEmptyBorder());
        btnNuevoPaquete.addActionListener(e -> abrirRegistroPaquete());
        add(btnNuevoPaquete);
    }

    private void cargarPaquetesIniciales() {
        try {
            List<PaqueteDTO> lista = control.listarPaquetes();
            actualizarListaVisual(lista);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los paquetes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filtrarPaquetes() {
        try {
            String nombre = txtFiltroNombre.getText().trim();
            String precioStr = txtFiltroPrecio.getText().trim();
            
            Double precioMax = precioStr.isEmpty() ? null : Double.parseDouble(precioStr);

            List<PaqueteDTO> listaFiltrada = control.buscarPaquetesDinamico(nombre, precioMax);
            
            actualizarListaVisual(listaFiltrada);
            
            if (listaFiltrada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron paquetes con esos filtros.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo de precio debe ser un número válido (ej. 150.50).", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al filtrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarListaVisual(List<PaqueteDTO> paquetes) {
        panelPaquetesContenedor.removeAll();
        
        for (PaqueteDTO paquete : paquetes) {
            JPanel tarjeta = crearTarjetaPaquete(paquete);
            panelPaquetesContenedor.add(tarjeta);
            panelPaquetesContenedor.add(Box.createRigidArea(new Dimension(0, 10))); 
        }
        
        panelPaquetesContenedor.revalidate();
        panelPaquetesContenedor.repaint();
    }

    /**
     * Crea un JPanel con diseño absoluto para simular la tarjeta blanca de la imagen.
     */
    private JPanel crearTarjetaPaquete(PaqueteDTO paquete) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(null);
        tarjeta.setBackground(Color.WHITE);
        
        Dimension dim = new Dimension(240, 125);
        tarjeta.setPreferredSize(dim);
        tarjeta.setMinimumSize(dim);
        tarjeta.setMaximumSize(dim);
        tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT); 
        tarjeta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel lblNombre = new JLabel(paquete.getNombre());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre.setBounds(10, 5, 120, 20);
        tarjeta.add(lblNombre);

        JLabel lblPrecio = new JLabel("$" + paquete.getPrecio());
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 14));
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrecio.setBounds(130, 5, 100, 20);
        tarjeta.add(lblPrecio);

        JTextArea txtProductos = new JTextArea();
        txtProductos.setFont(new Font("Arial", Font.PLAIN, 12));
        txtProductos.setEditable(false);
        txtProductos.setFocusable(false);
        txtProductos.setBorder(null);
        
        StringBuilder sbProductos = new StringBuilder();
        if(paquete.getListaProductos() != null) {
            for (ItemPaqueteDTO item : paquete.getListaProductos()) {
                sbProductos.append(item.getCantidad()).append(" ").append(item.getNombre()).append("\n");
            }
        }
        txtProductos.setText(sbProductos.toString());
        txtProductos.setBounds(10, 30, 120, 55); 
        tarjeta.add(txtProductos);

        JLabel lblImagen = new JLabel();
        lblImagen.setBackground(Color.WHITE);
        lblImagen.setOpaque(true);
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setBounds(140, 30, 90, 55);
        
        if (paquete.getImagen() != null && paquete.getImagen().getImagen() != null) {
            try {
                String base64Imagen = paquete.getImagen().getImagen(); 
                if (base64Imagen.contains(",")) {
                    base64Imagen = base64Imagen.substring(base64Imagen.indexOf(",") + 1);
                }
                base64Imagen = base64Imagen.replace('-', '+').replace('_', '/');
                base64Imagen = base64Imagen.replaceAll("\\s", "");
                
                byte[] bytesImagen = java.util.Base64.getDecoder().decode(base64Imagen);
                ImageIcon iconoOriginal = new ImageIcon(bytesImagen);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(90, 55, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(imagenEscalada));
            } catch (Exception ex) {
                System.out.println("Error al decodificar: " + ex.getMessage());
            }
        } else {
            lblImagen.setText("Sin foto");
        }
        tarjeta.add(lblImagen);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEditar.setForeground(new Color(0, 102, 204)); 
        btnEditar.setBounds(10, 90, 65, 25); 
        btnEditar.setMargin(new Insets(0, 0, 0, 0));
        btnEditar.setContentAreaFilled(false);
        btnEditar.setBorderPainted(false);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        btnEditar.addActionListener(e -> {
            RegistroPaquete pantallaEditar = new RegistroPaquete(control, paquete);
            pantallaEditar.setVisible(true);
            this.dispose(); 
        });
        tarjeta.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Color.RED);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEliminar.setBounds(80, 90, 75, 25); 
        btnEliminar.setMargin(new Insets(0, 0, 0, 0));
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar paquete?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                try {
                    control.eliminarPaquete(paquete.getIdPaquete());

                    JOptionPane.showMessageDialog(this, "Paquete eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    cargarPaquetesIniciales();

                } catch (NegocioException ex) {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el paquete: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.getLogger(PantallaPaquetes.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        });
        tarjeta.add(btnEliminar);

        return tarjeta;
    }

    private void generarReporte() throws NegocioException {
        control.generarReportePDF();
    }

    private void abrirRegistroPaquete() {
        control.mostrarPantallaRegistrarPaquete();
        this.dispose(); 
    }
}