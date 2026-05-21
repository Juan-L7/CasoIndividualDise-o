/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PantallasCUIProductosPaq;

import javax.swing.JFrame;
import Control.ControlCUIProductosPaquetes;
import Enums.Disponibilidad;
import GoOrderDTO.CategoriaDTO;
import GoOrderDTO.ImagenDTO;
import GoOrderDTO.ItemCategoriaDTO;
import GoOrderDTO.NuevoProductoDTO;
import GoOrderDTO.ProductoActualizadoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoDTOCom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import utilerias.MenuLateral;
/**
 *
 * @author juanl
 */
public class RegistroProducto extends JFrame {
    private ControlCUIProductosPaquetes control;
    private ProductoDTOCom productoExistente = null;
    
    private final Color COLOR_FONDO_VERDE = new Color(85, 239, 153); 
    private final Color COLOR_BOTON_NEGRO = Color.BLACK;
    private final Color COLOR_TEXTO_BLANCO = Color.WHITE;

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextArea txtDescripcion;
    private JLabel lblImagenPreview;
    private JButton btnBuscarImagen;
    private JComboBox<ItemCategoriaDTO> cmbCategoria;
    private JComboBox<String> cmbEstado;
    private JTextField txtStock;
    private JButton btnRegistrar;

    private String base64ImagenActual = null;
    private String formatoImagenActual = null;

    public RegistroProducto(ControlCUIProductosPaquetes control) {
        this.control = control;
        inicializarComponentes();
    }
    public RegistroProducto(ControlCUIProductosPaquetes control, ProductoDTOCom productoAEditar) {
        this.control = control;
        this.productoExistente = productoAEditar; 
        inicializarComponentes();
        cargarDatosProducto(); 
    }
    

    private void inicializarComponentes() {
        setTitle("GoOrder - Registrar Producto");
        setSize(400, 650); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        
        getContentPane().setBackground(COLOR_FONDO_VERDE);
        setLayout(null); 
        
        MenuLateral panelMenu = new MenuLateral(this, control);
        panelMenu.setBounds(0, 0, 110, 650); 
        add(panelMenu); 

        Font fuenteTitulos = new Font("Arial", Font.BOLD, 28);
        Font fuenteEtiquetas = new Font("Arial", Font.BOLD, 14);
        Font fuenteCampos = new Font("Arial", Font.PLAIN, 12);

        JLabel lblTitulo = new JLabel(productoExistente == null ? "Registro" : "Actualizar");
        lblTitulo.setFont(fuenteTitulos);
        lblTitulo.setBounds(130, 20, 200, 40);
        add(lblTitulo);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(fuenteEtiquetas);
        lblNombre.setBounds(120, 90, 70, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(fuenteCampos);
        txtNombre.setBounds(190, 90, 180, 25);
        txtNombre.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(fuenteEtiquetas);
        lblPrecio.setBounds(120, 130, 70, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setFont(fuenteCampos);
        txtPrecio.setBounds(190, 130, 100, 25);
        txtPrecio.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add(txtPrecio);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setFont(fuenteEtiquetas);
        lblDescripcion.setBounds(120, 170, 100, 25);
        add(lblDescripcion);

        txtDescripcion = new JTextArea();
        txtDescripcion.setFont(fuenteCampos);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setBounds(120, 200, 250, 70); 
        scrollDescripcion.setBorder(BorderFactory.createEmptyBorder());
        add(scrollDescripcion);

        JLabel lblImagen = new JLabel("Imagen:");
        lblImagen.setFont(fuenteEtiquetas);
        lblImagen.setBounds(120, 290, 70, 25);
        add(lblImagen);

        lblImagenPreview = new JLabel();
        lblImagenPreview.setOpaque(true);
        lblImagenPreview.setBackground(Color.WHITE);
        lblImagenPreview.setBounds(190, 290, 90, 90); // Preview un poco más chica
        lblImagenPreview.setHorizontalAlignment(JLabel.CENTER);
        add(lblImagenPreview);

        btnBuscarImagen = new JButton("Buscar");
        btnBuscarImagen.setBackground(COLOR_BOTON_NEGRO);
        btnBuscarImagen.setForeground(COLOR_TEXTO_BLANCO);
        btnBuscarImagen.setFont(new Font("Arial", Font.BOLD, 11));
        btnBuscarImagen.setBounds(290, 290, 80, 30);
        btnBuscarImagen.setFocusPainted(false);
        btnBuscarImagen.setBorder(BorderFactory.createEmptyBorder());
        btnBuscarImagen.addActionListener(e -> seleccionarImagen());
        add(btnBuscarImagen);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setFont(fuenteEtiquetas);
        lblCategoria.setBounds(120, 400, 80, 25);
        add(lblCategoria);

        cmbCategoria = new JComboBox<>();
        cmbCategoria.setBounds(200, 400, 170, 25);
        cmbCategoria.setBorder(BorderFactory.createEmptyBorder());
        add(cmbCategoria);

        llenarComboCategorias();

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setFont(fuenteEtiquetas);
        lblEstado.setBounds(120, 440, 70, 25);
        add(lblEstado);

        cmbEstado = new JComboBox<>(new String[]{"Disponible", "No Disponible"});
        cmbEstado.setBounds(200, 440, 170, 25);
        cmbEstado.setBorder(BorderFactory.createEmptyBorder());
        add(cmbEstado);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setFont(fuenteEtiquetas);
        lblStock.setBounds(120, 480, 70, 25);
        add(lblStock);

        txtStock = new JTextField();
        txtStock.setFont(fuenteCampos);
        txtStock.setBounds(200, 480, 100, 25);
        txtStock.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        add(txtStock);

        btnRegistrar = new JButton(productoExistente == null ? "Registrar" : "Actualizar");
        btnRegistrar.setBackground(COLOR_BOTON_NEGRO);
        btnRegistrar.setForeground(COLOR_TEXTO_BLANCO);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBounds(190, 530, 100, 35);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder());
        btnRegistrar.addActionListener(e -> ejecutarRegistro());
        add(btnRegistrar);
    }

    private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (jpg, png, jpeg)", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                byte[] bytesImagen = Files.readAllBytes(archivo.toPath());
                base64ImagenActual = Base64.getEncoder().encodeToString(bytesImagen);
                
                String nombreArchivo = archivo.getName();
                formatoImagenActual = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();

                ImageIcon iconoOriginal = new ImageIcon(bytesImagen);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(lblImagenPreview.getWidth(), lblImagenPreview.getHeight(), Image.SCALE_SMOOTH);
                lblImagenPreview.setIcon(new ImageIcon(imagenEscalada));
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void ejecutarRegistro() {
        try {
            if (txtNombre.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, llena los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ImagenDTO imagenDTO = null;
            if (base64ImagenActual != null && !base64ImagenActual.trim().isEmpty()) {
                imagenDTO = new ImagenDTO(base64ImagenActual, formatoImagenActual);
            }

            Disponibilidad disp = cmbEstado.getSelectedIndex() == 0 ? Disponibilidad.DISPONIBLE : Disponibilidad.NO_DISPONIBLE;
            ItemCategoriaDTO categoriaSeleccionada = (ItemCategoriaDTO) cmbCategoria.getSelectedItem();

            if (categoriaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar una categoría.");
                return;
            }
            String idCategoriaReal = categoriaSeleccionada.getId(); 
            
            if (productoExistente == null) {
                NuevoProductoDTO nuevoProd = new NuevoProductoDTO(
                        imagenDTO,
                        txtNombre.getText().trim(),
                        txtDescripcion.getText().trim(),
                        Double.parseDouble(txtPrecio.getText().trim()),
                        disp,
                        idCategoriaReal, 
                        Integer.parseInt(txtStock.getText().trim())
                );

                ProductoDTO resultado = control.registrarProducto(nuevoProd); 
                if (resultado != null) {
                    JOptionPane.showMessageDialog(this, "¡Producto registrado con éxito!\nNombre: " + resultado.getNombre(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                ProductoActualizadoDTO productoActualizado = new ProductoActualizadoDTO(
                        productoExistente.getId(), 
                        imagenDTO,                 
                        txtNombre.getText().trim(),
                        txtDescripcion.getText().trim(),
                        Double.parseDouble(txtPrecio.getText().trim()),
                        disp,                      
                        idCategoriaReal,           
                        Integer.parseInt(txtStock.getText().trim())
                );
                
                ProductoDTO resultado = control.actualizarProducto(productoActualizado); 
                
                if (resultado != null) {
                    JOptionPane.showMessageDialog(this, "¡Producto actualizado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    control.mostrarPantallaMenu();
                } else {
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "El precio y el stock deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error del Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarDatosProducto() {
        if (productoExistente == null) return;

        txtNombre.setText(productoExistente.getNombre());
        txtPrecio.setText(String.valueOf(productoExistente.getPrecio()));
        txtDescripcion.setText(productoExistente.getDescripcion());
        txtStock.setText(String.valueOf(productoExistente.getStock()));
        
        if (productoExistente.getDisponibilidad() == Enums.Disponibilidad.DISPONIBLE) {
            cmbEstado.setSelectedIndex(0);
        } else {
            cmbEstado.setSelectedIndex(1);
        }
        
        if (productoExistente.getImagen() != null && productoExistente.getImagen().getImagen() != null) {
            this.base64ImagenActual = productoExistente.getImagen().getImagen();
            this.formatoImagenActual = productoExistente.getImagen().getFormato();
            
            try {
                String base64Limpio = base64ImagenActual.replaceAll("\\s+", "");
                if (base64Limpio.contains(",")) {
                    base64Limpio = base64Limpio.substring(base64Limpio.indexOf(",") + 1);
                }

                byte[] bytesImagen = Base64.getDecoder().decode(base64Limpio);
                ImageIcon iconoOriginal = new ImageIcon(bytesImagen);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                lblImagenPreview.setIcon(new ImageIcon(imagenEscalada));
                
            } catch (Exception e) {
                System.err.println("Error al cargar la preview de la imagen: " + e.getMessage());
                e.printStackTrace(); 
            }
        }
    }
    private void llenarComboCategorias() {
        try {
            List<CategoriaDTO> listaCategorias = control.listarCategorias(); 

            for (CategoriaDTO cat : listaCategorias) {
                cmbCategoria.addItem(new ItemCategoriaDTO(cat.getId(), cat.getNombre()));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las categorías: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtDescripcion.setText("");
        txtStock.setText("");
        lblImagenPreview.setIcon(null);
        base64ImagenActual = null;
        formatoImagenActual = null;
        cmbCategoria.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
    }
    
    
}
