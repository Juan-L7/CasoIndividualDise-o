package PantallasCUIProductosPaq;

import Control.ControlCUIProductosPaquetes;
import GoOrderDTO.ImagenDTO;
import GoOrderDTO.ItemPaqueteDTO;
import GoOrderDTO.NuevoPaqueteDTO;
import GoOrderDTO.PaqueteDTO; 
import GoOrderDTO.ProductoDTOCom;
import Observer.IProductoSeleccionadoObserver;
import PantallasCUIProductosPaq.BuscarProductoDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import utilerias.MenuLateral;

/**
 * Pantalla para el registro y actualización de paquetes.
 * Implementa IProductoObserver para escuchar al buscador de productos.
 */
public class RegistroPaquete extends JFrame implements IProductoSeleccionadoObserver {

    private ControlCUIProductosPaquetes control;
    private JFrame ventanaPadre;
    
    private final Color COLOR_FONDO_VERDE = new Color(85, 239, 153); 
    private final Color COLOR_BOTON_NEGRO = Color.BLACK;
    private final Color COLOR_TEXTO_BLANCO = Color.WHITE;

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JLabel lblImagenPreview;
    private JPanel panelProductosContenedor;
    private JButton btnRegistrar;

    private final List<ItemPaqueteDTO> listaProductosSeleccionados;
    private String base64ImagenActual = null;
    private String formatoImagenActual = null;
    
    private PaqueteDTO paqueteEdicion = null;

    public RegistroPaquete(ControlCUIProductosPaquetes control) {
        this.control = control;
        this.listaProductosSeleccionados = new ArrayList<>();
        this.paqueteEdicion = null; 
        inicializarComponentes();
    }

    public RegistroPaquete(ControlCUIProductosPaquetes control, PaqueteDTO paqueteAEditar) {
        this.control = control;
        this.listaProductosSeleccionados = new ArrayList<>();
        this.paqueteEdicion = paqueteAEditar; 
        inicializarComponentes();
        cargarDatosEdicion(); 
    }

    private void inicializarComponentes() {
        String textoTitulo = (paqueteEdicion == null) ? "Nuevo Paquete" : "Actualizar Paquete";
        String textoBoton = (paqueteEdicion == null) ? "Registrar" : "Actualizar";

        setTitle("GoOrder - " + textoTitulo);
        setSize(400, 650); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_FONDO_VERDE);
        setLayout(null); 
        
        MenuLateral panelMenu = new MenuLateral(this, control);
        panelMenu.setBounds(0, 0, 110, 650); 
        add(panelMenu); 

        Font fuenteTitulos = new Font("Arial", Font.BOLD, 24);
        Font fuenteEtiquetas = new Font("Arial", Font.BOLD, 14);
        Font fuenteCampos = new Font("Arial", Font.PLAIN, 12);

        JLabel lblTitulo = new JLabel(textoTitulo);
        lblTitulo.setFont(fuenteTitulos);
        lblTitulo.setBounds(130, 20, 250, 40);
        add(lblTitulo);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(fuenteEtiquetas);
        lblNombre.setBounds(120, 70, 70, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(fuenteCampos);
        txtNombre.setBounds(190, 70, 180, 25);
        txtNombre.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(fuenteEtiquetas);
        lblPrecio.setBounds(120, 110, 70, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setFont(fuenteCampos);
        txtPrecio.setBounds(190, 110, 100, 25);
        txtPrecio.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add(txtPrecio);

        JLabel lblProductos = new JLabel("Productos:");
        lblProductos.setFont(fuenteEtiquetas);
        lblProductos.setBounds(120, 150, 90, 25);
        add(lblProductos);

        JButton btnBuscarProducto = new JButton("Buscar");
        btnBuscarProducto.setBackground(COLOR_BOTON_NEGRO);
        btnBuscarProducto.setForeground(COLOR_TEXTO_BLANCO);
        btnBuscarProducto.setFont(new Font("Arial", Font.BOLD, 11));
        btnBuscarProducto.setBounds(290, 150, 80, 25);
        btnBuscarProducto.setFocusPainted(false);
        btnBuscarProducto.setBorder(BorderFactory.createEmptyBorder());
        btnBuscarProducto.addActionListener(e -> abrirBuscadorProductos());
        add(btnBuscarProducto);

        panelProductosContenedor = new JPanel();
        panelProductosContenedor.setLayout(new BoxLayout(panelProductosContenedor, BoxLayout.Y_AXIS));
        panelProductosContenedor.setBackground(COLOR_FONDO_VERDE);
        
        JScrollPane scrollProductos = new JScrollPane(panelProductosContenedor);
        scrollProductos.setBounds(120, 180, 250, 110);
        scrollProductos.setBorder(BorderFactory.createEmptyBorder());
        add(scrollProductos);

        JLabel lblVigencia = new JLabel("Vigencia:");
        lblVigencia.setFont(fuenteEtiquetas);
        lblVigencia.setBounds(120, 300, 100, 25);
        add(lblVigencia);

        txtFechaInicio = new JTextField("30/04/2026");
        txtFechaInicio.setFont(fuenteCampos);
        txtFechaInicio.setBounds(120, 330, 90, 25);
        txtFechaInicio.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add(txtFechaInicio);

        JLabel lblA = new JLabel("A", SwingConstants.CENTER);
        lblA.setFont(fuenteEtiquetas);
        lblA.setBounds(215, 330, 20, 25);
        add(lblA);

        txtFechaFin = new JTextField("30/05/2026");
        txtFechaFin.setFont(fuenteCampos);
        txtFechaFin.setBounds(240, 330, 90, 25);
        txtFechaFin.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add(txtFechaFin);

        JLabel lblImagen = new JLabel("Imagen:");
        lblImagen.setFont(fuenteEtiquetas);
        lblImagen.setBounds(120, 370, 70, 25);
        add(lblImagen);

        JButton btnBuscarImagen = new JButton("Buscar");
        btnBuscarImagen.setBackground(COLOR_BOTON_NEGRO);
        btnBuscarImagen.setForeground(COLOR_TEXTO_BLANCO);
        btnBuscarImagen.setFont(new Font("Arial", Font.BOLD, 11));
        btnBuscarImagen.setBounds(290, 370, 80, 25);
        btnBuscarImagen.setFocusPainted(false);
        btnBuscarImagen.setBorder(BorderFactory.createEmptyBorder());
        btnBuscarImagen.addActionListener(e -> seleccionarImagen());
        add(btnBuscarImagen);

        lblImagenPreview = new JLabel();
        lblImagenPreview.setOpaque(true);
        lblImagenPreview.setBackground(Color.WHITE);
        lblImagenPreview.setBounds(190, 405, 90, 90);
        lblImagenPreview.setHorizontalAlignment(JLabel.CENTER);
        add(lblImagenPreview);

        btnRegistrar = new JButton(textoBoton);
        btnRegistrar.setBackground(COLOR_BOTON_NEGRO);
        btnRegistrar.setForeground(COLOR_TEXTO_BLANCO);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBounds(185, 520, 100, 35);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder());
        btnRegistrar.addActionListener(e -> ejecutarGuardado()); 
        add(btnRegistrar);
    }

    private void cargarDatosEdicion() {
        if (paqueteEdicion == null) return;

        txtNombre.setText(paqueteEdicion.getNombre());
        txtPrecio.setText(String.valueOf(paqueteEdicion.getPrecio()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (paqueteEdicion.getFechaInicioVigencia() != null) {
            txtFechaInicio.setText(paqueteEdicion.getFechaInicioVigencia().format(formatter));
        }
        if (paqueteEdicion.getFechaFinVigencia() != null) {
            txtFechaFin.setText(paqueteEdicion.getFechaFinVigencia().format(formatter));
        }

        if (paqueteEdicion.getImagen() != null && paqueteEdicion.getImagen().getImagen() != null) {
            try {
                base64ImagenActual = paqueteEdicion.getImagen().getImagen();
                formatoImagenActual = paqueteEdicion.getImagen().getFormato();
                
                byte[] bytesImagen = Base64.getDecoder().decode(base64ImagenActual);
                ImageIcon iconoOriginal = new ImageIcon(bytesImagen);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                lblImagenPreview.setIcon(new ImageIcon(imagenEscalada));
            } catch (Exception e) {
                System.out.println("Error decodificando imagen existente: " + e.getMessage());
            }
        }

        if (paqueteEdicion.getListaProductos() != null) {
            this.listaProductosSeleccionados.addAll(paqueteEdicion.getListaProductos());
            actualizarListaVisualProductos();
        }
    }

    private void abrirBuscadorProductos() {
        BuscarProductoDialog dialog = new BuscarProductoDialog(this, control, this);
        dialog.setVisible(true);
    }

    @Override
    public void onProductoSeleccionado(ProductoDTOCom producto) {
        if (producto == null) return;
        boolean existe = false;
        for (ItemPaqueteDTO item : listaProductosSeleccionados) {
            if (item.getIdProducto().equals(producto.getId())) {
                item.setCantidad(item.getCantidad() + 1);
                existe = true;
                break;
            }
        }
        if (!existe) {
            ItemPaqueteDTO nuevoItem = new ItemPaqueteDTO();
            nuevoItem.setIdProducto(producto.getId());
            nuevoItem.setNombre(producto.getNombre());
            nuevoItem.setPrecio(producto.getPrecio());
            nuevoItem.setCantidad(1);
            listaProductosSeleccionados.add(nuevoItem);
        }
        actualizarListaVisualProductos();
    }

    private void actualizarListaVisualProductos() {
        panelProductosContenedor.removeAll();
        
        for (ItemPaqueteDTO item : listaProductosSeleccionados) {
            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            fila.setBackground(Color.WHITE);
            fila.setPreferredSize(new Dimension(230, 35)); 
            fila.setMaximumSize(new Dimension(230, 35));
            fila.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel lblNombreProd = new JLabel(item.getNombre());
            lblNombreProd.setPreferredSize(new Dimension(100, 20)); 
            lblNombreProd.setFont(new Font("Arial", Font.PLAIN, 12));

            JButton btnEliminar = new JButton("🗑");
            btnEliminar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14)); 
            btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnEliminar.setMargin(new Insets(0, 0, 0, 0));
            btnEliminar.setContentAreaFilled(false); 
            btnEliminar.setBorderPainted(false); 
            btnEliminar.addActionListener(e -> {
                listaProductosSeleccionados.remove(item);
                actualizarListaVisualProductos();
            });

            JButton btnMenos = new JButton("━");
            btnMenos.setForeground(Color.RED);
            btnMenos.setFont(new Font("Arial", Font.BOLD, 14));
            btnMenos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnMenos.setMargin(new Insets(0, 0, 0, 0));
            btnMenos.setContentAreaFilled(false);
            btnMenos.setBorderPainted(false);
            btnMenos.addActionListener(e -> {
                if (item.getCantidad() > 1) {
                    item.setCantidad(item.getCantidad() - 1);
                } else {
                    listaProductosSeleccionados.remove(item);
                }
                actualizarListaVisualProductos();
            });

            JLabel lblCantidad = new JLabel(String.valueOf(item.getCantidad()), SwingConstants.CENTER);
            lblCantidad.setPreferredSize(new Dimension(20, 20));
            lblCantidad.setFont(new Font("Arial", Font.BOLD, 12));

            JButton btnMas = new JButton("✚");
            btnMas.setForeground(new Color(0, 180, 0)); 
            btnMas.setFont(new Font("Arial", Font.BOLD, 12));
            btnMas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnMas.setMargin(new Insets(0, 0, 0, 0));
            btnMas.setContentAreaFilled(false);
            btnMas.setBorderPainted(false);
            btnMas.addActionListener(e -> {
                item.setCantidad(item.getCantidad() + 1);
                actualizarListaVisualProductos();
            });

            fila.add(lblNombreProd);
            fila.add(btnEliminar);
            fila.add(btnMenos);
            fila.add(lblCantidad);
            fila.add(btnMas);

            panelProductosContenedor.add(fila);
            panelProductosContenedor.add(Box.createRigidArea(new Dimension(0, 5))); 
        }
        
        panelProductosContenedor.revalidate();
        panelProductosContenedor.repaint();
    }

    private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (jpg, png, jpeg)", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                byte[] bytesImagen = Files.readAllBytes(archivo.toPath());
                base64ImagenActual = Base64.getEncoder().encodeToString(bytesImagen);
                String nombreArchivo = archivo.getName();
                formatoImagenActual = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();

                ImageIcon iconoOriginal = new ImageIcon(bytesImagen);
                Image imagenEscalade = iconoOriginal.getImage().getScaledInstance(lblImagenPreview.getWidth(), lblImagenPreview.getHeight(), Image.SCALE_SMOOTH);
                lblImagenPreview.setIcon(new ImageIcon(imagenEscalade));
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void ejecutarGuardado() {
        try {
            if (txtNombre.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Llena los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (listaProductosSeleccionados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El paquete debe contener productos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ImagenDTO imagenDTO = null;
            if (base64ImagenActual != null && !base64ImagenActual.trim().isEmpty()) {
                imagenDTO = new ImagenDTO(base64ImagenActual, formatoImagenActual);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaInicio = LocalDate.parse(txtFechaInicio.getText().trim(), formatter);
            LocalDate fechaFin = LocalDate.parse(txtFechaFin.getText().trim(), formatter);

            if (paqueteEdicion == null) {
                NuevoPaqueteDTO nuevoPaquete = new NuevoPaqueteDTO();
                nuevoPaquete.setNombre(txtNombre.getText().trim());
                nuevoPaquete.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
                nuevoPaquete.setImagen(imagenDTO);
                nuevoPaquete.setFechaInicioVigencia(fechaInicio);
                nuevoPaquete.setFechaFinVigencia(fechaFin);
                nuevoPaquete.setListaProductos(listaProductosSeleccionados);

                control.registrarPaquete(nuevoPaquete);
                JOptionPane.showMessageDialog(this, "Paquete registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                PaqueteDTO paqueteActualizar = new PaqueteDTO();
                paqueteActualizar.setIdPaquete(paqueteEdicion.getIdPaquete()); 
                paqueteActualizar.setNombre(txtNombre.getText().trim());
                paqueteActualizar.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
                paqueteActualizar.setImagen(imagenDTO);
                paqueteActualizar.setFechaInicioVigencia(fechaInicio);
                paqueteActualizar.setFechaFinVigencia(fechaFin);
                paqueteActualizar.setListaProductos(listaProductosSeleccionados);

                control.actualizarPaquete(paqueteActualizar); 
                JOptionPane.showMessageDialog(this, "Paquete actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            
            control.mostrarPantallaPaquetes();
            
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos o en el guardado: " + ex.getMessage(), "Error del Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}