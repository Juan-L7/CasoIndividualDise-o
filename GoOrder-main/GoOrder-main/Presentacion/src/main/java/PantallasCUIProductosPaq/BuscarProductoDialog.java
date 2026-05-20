package PantallasCUIProductosPaq;

import Control.ControlCUIProductosPaquetes;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoDTOCom;
import Observer.IProductoSeleccionadoObserver;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.example.NegocioException;

public class BuscarProductoDialog extends JDialog {

    private ControlCUIProductosPaquetes control;
    private IProductoSeleccionadoObserver observer; 

    private final Color COLOR_FONDO_VERDE = new Color(85, 239, 153);
    private final Color COLOR_BOTON_NEGRO = Color.BLACK;
    private final Color COLOR_TEXTO_BLANCO = Color.WHITE;

    private JTextField txtNombre;
    private JComboBox<String> cmbCategoria;
    private JTextField txtPrecioMin;
    private JTextField txtPrecioMax;
    private JPanel panelResultados;

    public BuscarProductoDialog(JFrame parent, ControlCUIProductosPaquetes control, IProductoSeleccionadoObserver observer) {
        super(parent, true); 
        this.control = control;
        this.observer = observer; 
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("GoOrder - Buscar Producto");
        setSize(450, 750);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelIzquierdo = new JPanel(null);
        panelIzquierdo.setBackground(Color.BLACK);
        panelIzquierdo.setPreferredSize(new Dimension(80, getHeight()));
        
        JLabel lblAtras = new JLabel("←");
        lblAtras.setForeground(Color.WHITE);
        lblAtras.setFont(new Font("Arial", Font.PLAIN, 30));
        lblAtras.setBounds(20, 20, 50, 30);
        lblAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose(); 
            }
        });
        panelIzquierdo.add(lblAtras);
        add(panelIzquierdo, BorderLayout.WEST);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(COLOR_FONDO_VERDE);

        // -- Sub-Panel Superior (Filtros) --
        JPanel panelFiltros = new JPanel(null);
        panelFiltros.setBackground(COLOR_FONDO_VERDE);
        panelFiltros.setPreferredSize(new Dimension(370, 300));

        Font fuenteTitulos = new Font("Arial", Font.BOLD, 45);
        Font fuenteEtiquetas = new Font("Arial", Font.BOLD, 22);

        JLabel lblTitulo1 = new JLabel("Buscar");
        lblTitulo1.setFont(fuenteTitulos);
        lblTitulo1.setBounds(90, 20, 200, 50);
        panelFiltros.add(lblTitulo1);

        JLabel lblTitulo2 = new JLabel("Producto");
        lblTitulo2.setFont(fuenteTitulos);
        lblTitulo2.setBounds(60, 70, 250, 50);
        panelFiltros.add(lblTitulo2);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(fuenteEtiquetas);
        lblNombre.setBounds(20, 140, 100, 30);
        panelFiltros.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 140, 180, 30);
        txtNombre.setBorder(BorderFactory.createEmptyBorder());
        panelFiltros.add(txtNombre);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setFont(fuenteEtiquetas);
        lblCategoria.setBounds(20, 185, 120, 30);
        panelFiltros.add(lblCategoria);

        cmbCategoria = new JComboBox<>(new String[]{"Todas", "Bebidas", "Postres"});
        cmbCategoria.setBounds(140, 185, 170, 30);
        cmbCategoria.setBorder(BorderFactory.createEmptyBorder());
        panelFiltros.add(cmbCategoria);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(fuenteEtiquetas);
        lblPrecio.setBounds(20, 230, 90, 30);
        panelFiltros.add(lblPrecio);

        txtPrecioMin = new JTextField();
        txtPrecioMin.setBounds(110, 230, 60, 30);
        txtPrecioMin.setBorder(BorderFactory.createEmptyBorder());
        txtPrecioMin.setHorizontalAlignment(JTextField.CENTER);
        panelFiltros.add(txtPrecioMin);

        JLabel lblA = new JLabel("A");
        lblA.setFont(fuenteEtiquetas);
        lblA.setBounds(185, 230, 30, 30);
        panelFiltros.add(lblA);

        txtPrecioMax = new JTextField();
        txtPrecioMax.setBounds(215, 230, 60, 30);
        txtPrecioMax.setBorder(BorderFactory.createEmptyBorder());
        txtPrecioMax.setHorizontalAlignment(JTextField.CENTER);
        panelFiltros.add(txtPrecioMax);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(COLOR_BOTON_NEGRO);
        btnBuscar.setForeground(COLOR_TEXTO_BLANCO);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 14));
        btnBuscar.setBounds(240, 270, 90, 30);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorder(BorderFactory.createEmptyBorder());
        btnBuscar.addActionListener(e -> {
            try {
                ejecutarBusqueda();
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelFiltros.add(btnBuscar);

        panelPrincipal.add(panelFiltros, BorderLayout.NORTH);

        panelResultados = new JPanel();
        panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS));
        panelResultados.setBackground(COLOR_FONDO_VERDE);

        JScrollPane scrollPane = new JScrollPane(panelResultados);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(COLOR_FONDO_VERDE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void ejecutarBusqueda() throws NegocioException {
        panelResultados.removeAll();

        String nombre = txtNombre.getText();
        String cat = cmbCategoria.getSelectedItem().toString();
        Double pMin = txtPrecioMin.getText().isEmpty() ? null : Double.parseDouble(txtPrecioMin.getText());
        Double pMax = txtPrecioMax.getText().isEmpty() ? null : Double.parseDouble(txtPrecioMax.getText());
        
        List<ProductoDTOCom> lista = control.buscarProductosDinamico(nombre, cat, pMin, pMax);
        
        for (ProductoDTOCom productoDTO : lista) {
            panelResultados.add(Box.createRigidArea(new Dimension(0, 15))); 
            panelResultados.add(crearPanelProducto(productoDTO));
        }

        panelResultados.revalidate();
        panelResultados.repaint();
    }

    private JPanel crearPanelProducto(ProductoDTOCom producto) {
        JPanel tarjeta = new JPanel(null); 
        tarjeta.setBackground(Color.WHITE);
        
        Dimension dimensionTarjeta = new Dimension(330, 80);
        tarjeta.setPreferredSize(dimensionTarjeta);
        tarjeta.setMinimumSize(dimensionTarjeta);
        tarjeta.setMaximumSize(dimensionTarjeta);
        tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT); 

        JLabel lblProdNombre = new JLabel(producto.getNombre());
        lblProdNombre.setFont(new Font("Arial", Font.PLAIN, 15));
        lblProdNombre.setBounds(15, 15, 120, 20); 
        tarjeta.add(lblProdNombre);

        JLabel lblCosto = new JLabel("Costo: $" + String.format("%.2f", producto.getPrecio()));
        lblCosto.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCosto.setBounds(15, 45, 120, 20);
        tarjeta.add(lblCosto);

        JLabel lblImagen = new JLabel();
        lblImagen.setOpaque(true);
        lblImagen.setBackground(Color.LIGHT_GRAY); 
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setBounds(140, 10, 60, 60); 

        if (producto.getImagen() != null && producto.getImagen().getImagen() != null) {
            try {
                String base64Imagen = producto.getImagen().getImagen(); 
                if (base64Imagen.contains(",")) {
                    base64Imagen = base64Imagen.substring(base64Imagen.indexOf(",") + 1);
                }
                base64Imagen = base64Imagen.replace('-', '+').replace('_', '/');
                base64Imagen = base64Imagen.replaceAll("\\s", "");
                
                byte[] bytesImagen = java.util.Base64.getDecoder().decode(base64Imagen);
                ImageIcon iconoOriginal = new ImageIcon(bytesImagen);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(imagenEscalada));
                lblImagen.setBackground(Color.WHITE); 
            } catch (Exception ex) {
                System.out.println("Error al decodificar: " + ex.getMessage());
            }
        }
        tarjeta.add(lblImagen);

        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBackground(COLOR_BOTON_NEGRO);
        btnSeleccionar.setForeground(COLOR_TEXTO_BLANCO);
        btnSeleccionar.setFont(new Font("Arial", Font.BOLD, 11)); 
        btnSeleccionar.setBounds(215, 25, 100, 30); 
        btnSeleccionar.setFocusPainted(false);
        btnSeleccionar.setBorder(BorderFactory.createEmptyBorder());
        btnSeleccionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnSeleccionar.setMargin(new Insets(0, 0, 0, 0)); 
        
        btnSeleccionar.addActionListener(e -> {
            if(observer != null) {
                observer.onProductoSeleccionado(producto); 
            }
            dispose(); 
        });
        
        tarjeta.add(btnSeleccionar);

        return tarjeta;
    }
}