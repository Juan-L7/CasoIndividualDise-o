/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PantallasCUIProductosPaq;
import Control.ControlCUIProductosPaquetes;
import GoOrderDTO.ProductoDTOCom;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.util.Base64;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import utilerias.MenuLateral;
/**
 *
 * @author juanl
 */
public class EliminarProducto extends JFrame {
    
    private ControlCUIProductosPaquetes control;
    private ProductoDTOCom productoAEliminar;
    
    private final Color COLOR_FONDO_VERDE = new Color(85, 239, 153); 
    private final Color COLOR_BOTON_NEGRO = Color.BLACK;
    private final Color COLOR_TEXTO_BLANCO = Color.WHITE;
    private final Color COLOR_SOLO_LECTURA = new Color(220, 220, 220); 

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextArea txtDescripcion;
    private JLabel lblImagenPreview;
    private JTextField txtCategoria;
    private JTextField txtEstado;
    private JButton btnEliminar;

    public EliminarProducto(ControlCUIProductosPaquetes control, ProductoDTOCom productoAEliminar) {
        this.control = control;
        this.productoAEliminar = productoAEliminar; 
        inicializarComponentes();
        cargarDatosProducto(); 
    }

    private void inicializarComponentes() {
        setTitle("GoOrder - Eliminar Producto");
        setSize(400, 650); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        
        getContentPane().setBackground(COLOR_FONDO_VERDE);
        setLayout(null); 
        
        MenuLateral panelMenu = new MenuLateral(this, control);
        panelMenu.setBounds(0, 0, 110, 650); 
        add(panelMenu); 

        Font fuenteTitulos = new Font("Arial", Font.BOLD, 32);
        Font fuenteEtiquetas = new Font("Arial", Font.BOLD, 16);
        Font fuenteCampos = new Font("Arial", Font.PLAIN, 12);

        JLabel lblTitulo = new JLabel("Eliminar");
        lblTitulo.setFont(fuenteTitulos);
        lblTitulo.setBounds(160, 20, 200, 40);
        add(lblTitulo);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(fuenteEtiquetas);
        lblNombre.setBounds(120, 90, 80, 25);
        add(lblNombre);
        

        txtNombre = new JTextField();
        txtNombre.setFont(fuenteCampos);
        txtNombre.setBounds(200, 90, 160, 25);
        txtNombre.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        txtNombre.setEditable(false);
        txtNombre.setBackground(COLOR_SOLO_LECTURA);
        add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(fuenteEtiquetas);
        lblPrecio.setBounds(120, 140, 80, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setFont(fuenteCampos);
        txtPrecio.setBounds(200, 140, 160, 25);
        txtPrecio.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        txtPrecio.setEditable(false);
        txtPrecio.setBackground(COLOR_SOLO_LECTURA);
        add(txtPrecio);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setFont(fuenteEtiquetas);
        lblDescripcion.setBounds(120, 190, 110, 25);
        add(lblDescripcion);

        txtDescripcion = new JTextArea();
        txtDescripcion.setFont(fuenteCampos);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(COLOR_SOLO_LECTURA);
        
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setBounds(120, 220, 240, 70); 
        scrollDescripcion.setBorder(BorderFactory.createEmptyBorder());
        add(scrollDescripcion);

        JLabel lblImagen = new JLabel("Imagen:");
        lblImagen.setFont(fuenteEtiquetas);
        lblImagen.setBounds(120, 310, 80, 25);
        add(lblImagen);

        lblImagenPreview = new JLabel();
        lblImagenPreview.setOpaque(true);
        lblImagenPreview.setBackground(Color.WHITE);
        lblImagenPreview.setBounds(200, 310, 90, 90);
        lblImagenPreview.setHorizontalAlignment(JLabel.CENTER);
        add(lblImagenPreview);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setFont(fuenteEtiquetas);
        lblCategoria.setBounds(120, 420, 90, 25);
        add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setFont(fuenteCampos);
        txtCategoria.setBounds(210, 420, 150, 25);
        txtCategoria.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        txtCategoria.setEditable(false);
        txtCategoria.setBackground(COLOR_SOLO_LECTURA);
        add(txtCategoria);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setFont(fuenteEtiquetas);
        lblEstado.setBounds(120, 470, 80, 25);
        add(lblEstado);

        txtEstado = new JTextField();
        txtEstado.setFont(fuenteCampos);
        txtEstado.setBounds(210, 470, 150, 25);
        txtEstado.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        txtEstado.setEditable(false);
        txtEstado.setBackground(COLOR_SOLO_LECTURA);
        add(txtEstado);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(COLOR_BOTON_NEGRO);
        btnEliminar.setForeground(COLOR_TEXTO_BLANCO);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEliminar.setBounds(260, 540, 100, 35); // Alineado a la derecha como en tu boceto
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorder(BorderFactory.createEmptyBorder());
        btnEliminar.addActionListener(e -> ejecutarEliminacion());
        add(btnEliminar);
    }

    private void cargarDatosProducto() {
        if (productoAEliminar == null) return;

        txtNombre.setText(productoAEliminar.getNombre());
        txtPrecio.setText(String.valueOf(productoAEliminar.getPrecio()));
        txtDescripcion.setText(productoAEliminar.getDescripcion());
        
        txtCategoria.setText(productoAEliminar.getIdcategoria() != null ? productoAEliminar.getIdcategoria() : "Sin Categoría");
        
        if (productoAEliminar.getDisponibilidad() == Enums.Disponibilidad.DISPONIBLE) {
            txtEstado.setText("Disponible");
        } else {
            txtEstado.setText("No Disponible");
        }
        
        if (productoAEliminar.getImagen() != null && productoAEliminar.getImagen().getImagen() != null) {
            try {
                String base64Limpio = productoAEliminar.getImagen().getImagen().replaceAll("\\s+", "");
                if (base64Limpio.contains(",")) {
                    base64Limpio = base64Limpio.substring(base64Limpio.indexOf(",") + 1);
                }

                byte[] bytesImagen = Base64.getDecoder().decode(base64Limpio);
                ImageIcon iconoOriginal = new ImageIcon(bytesImagen);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                lblImagenPreview.setIcon(new ImageIcon(imagenEscalada));
                
            } catch (Exception e) {
                System.err.println("Error al cargar la preview de la imagen: " + e.getMessage());
            }
        }
    }

    private void ejecutarEliminacion() {
        int confirmacion = JOptionPane.showConfirmDialog(
                this, 
                "¿Estás completamente seguro de que deseas eliminar '" + productoAEliminar.getNombre() + "'?\nEsta acción no se puede deshacer.", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                control.eliminarProducto(productoAEliminar.getId()); 
                
                JOptionPane.showMessageDialog(this, "Producto eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); 
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
