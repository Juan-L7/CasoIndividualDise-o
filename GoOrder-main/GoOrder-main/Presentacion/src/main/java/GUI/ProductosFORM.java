
package GUI;

import Control.Control;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoSeleccionadoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductosFORM extends JFrame {

    private final Control control;
    private JPanel pnlGrid;
    
    public ProductosFORM(Control control) throws Exception {
        this.control = control;

        setTitle("GoOrder - Menú");
        setSize(400, 700); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(control.COLOR_FONDO);

        JPanel pnlNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        pnlNorte.setBackground(control.COLOR_FONDO);
        
        JTextField txtBuscar = new JTextField(20);
        txtBuscar.setPreferredSize(new Dimension(300, 35));
        txtBuscar.setBackground(control.COLOR_TARJETA);
        txtBuscar.setForeground(Color.WHITE);
        txtBuscar.setCaretColor(control.COLOR_NEON);
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(control.COLOR_NEON, 1, true),
                new EmptyBorder(5, 10, 5, 10)
        ));
        
        BotonNeon btnBuscar = new BotonNeon("Buscar");
        btnBuscar.setPreferredSize(new Dimension(100, 35));
        
        pnlNorte.add(txtBuscar);
        pnlNorte.add(btnBuscar);
                        
        pnlGrid = new JPanel();
        pnlGrid.setLayout(new BoxLayout(pnlGrid, BoxLayout.Y_AXIS));
        pnlGrid.setBackground(control.COLOR_FONDO);
        pnlGrid.setBorder(new EmptyBorder(10, 15, 10, 15));

        for (ProductoDTO producto : control.listarProductos()){
            pnlGrid.add(crearTarjetaProducto(producto));
            pnlGrid.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JScrollPane scrollPane = new JScrollPane(pnlGrid);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(control.COLOR_FONDO);

        JPanel pnlSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        pnlSur.setBackground(control.COLOR_FONDO);
        
        BotonNeon btnCarrito = new BotonNeon("VER CARRITO");
        btnCarrito.setPreferredSize(new Dimension(350, 50));
        btnCarrito.addActionListener(e -> control.mostrarCarrito());
        pnlSur.add(btnCarrito);

        btnBuscar.addActionListener(e -> {
            String nombre = txtBuscar.getText().trim();
            
            pnlGrid.removeAll();
            if (nombre.isEmpty()) {
                try {
                    for (ProductoDTO pDto: control.listarProductos()) {
                        pnlGrid.add(crearTarjetaProducto(pDto));
                        pnlGrid.add(Box.createRigidArea(new Dimension(0, 10)));
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "No se pudo cargar el catálogo: " + ex.getMessage());
                }
            } else {
                try {
                    ProductoDTO producto = (ProductoDTO) control.buscarProducto(nombre);
                    if(producto != null) {
                        pnlGrid.add(crearTarjetaProducto(producto));
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró ningún producto con ese nombre.");
                    }
                } catch (Exception exm) {
                    JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + exm.getMessage());
                }
            }
            pnlGrid.revalidate();
            pnlGrid.repaint();
        });
        
        add(pnlNorte, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);
    }

    private JPanel crearTarjetaProducto(ProductoDTO producto) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setBackground(control.COLOR_TARJETA);
        tarjeta.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1, true));

        JLabel lblImagen = new JLabel("", SwingConstants.CENTER);
        lblImagen.setOpaque(true);
        lblImagen.setBackground(new Color(45, 45, 45));
        lblImagen.setForeground(Color.GRAY);
        lblImagen.setPreferredSize(new Dimension(150, 100));
        
        ImageIcon imagen = control.obtenerImagen(producto.getImagen());
        Image imgEscalada = imagen.getImage().getScaledInstance(170, 100, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(imgEscalada));
        tarjeta.add(lblImagen, BorderLayout.NORTH);

        JPanel pnlInfo = new JPanel(new GridBagLayout());
        pnlInfo.setBackground(control.COLOR_TARJETA);
        pnlInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel lblNombre = new JLabel(producto.getNombre());
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNombre.setForeground(Color.WHITE);
        
        JLabel lblPrecio = new JLabel(String.format("$%.2f", producto.getPrecio()));
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 14));
        lblPrecio.setForeground(control.COLOR_NEON);

        BotonAgregar btnAdd = new BotonAgregar(producto);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST;
        pnlInfo.add(lblNombre, gbc);
        
        gbc.gridy = 1; 
        pnlInfo.add(lblPrecio, gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 2; gbc.weightx = 0; gbc.anchor = GridBagConstraints.CENTER;
        pnlInfo.add(btnAdd, gbc);

        tarjeta.add(pnlInfo, BorderLayout.CENTER);

        tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        return tarjeta;
    }

    class BotonAgregar extends JButton {
        private boolean over = false;

        public BotonAgregar(ProductoDTO producto) {
            super("+");
            setFont(new Font("Arial", Font.BOLD, 22));
            setMargin(new Insets(0, 0, 0, 0));
            setForeground(control.COLOR_NEON);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(35, 35));

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { over = true; repaint(); }
                public void mouseExited(MouseEvent e) { over = false; repaint(); }
            });
            
            addActionListener(e -> {
                try {
                    ProductoSeleccionadoDTO itemCarrito = new ProductoSeleccionadoDTO(producto.getNombre(),1,producto.getPrecio(),producto.getPrecio(),producto);
                    itemCarrito.setProducto(producto); 
                    itemCarrito.setNombre(producto.getNombre());
                    itemCarrito.setPrecioActual(producto.getPrecio());
                    itemCarrito.setCantidad(1); 
                    control.agregarProducto(itemCarrito);
                    
                    JOptionPane.showMessageDialog(ProductosFORM.this, 
                            "Se agregó " + producto.getNombre() + " al carrito.", 
                            "¡Agregado!", 
                            JOptionPane.INFORMATION_MESSAGE);
                            
                } catch (Exception ex) {
                    Logger.getLogger(ProductosFORM.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(ProductosFORM.this, 
                            "Error al agregar al carrito: " + ex.getMessage(), 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (over) {
                g2.setColor(control.COLOR_NEON);
                setForeground(Color.BLACK);
            } else {
                g2.setColor(control.COLOR_BOTON);
                setForeground(control.COLOR_NEON);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            
            super.paintComponent(g);
            g2.dispose();
        }
    }

    class BotonNeon extends JButton {
        private boolean over = false;

        public BotonNeon(String texto) {
            super(texto);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { over = true; repaint(); }
                public void mouseExited(MouseEvent e) { over = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (over) {
                g2.setColor(control.COLOR_NEON);
            } else {
                g2.setColor(control.COLOR_BOTON);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

            g2.setFont(getFont());

            if (over) {
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(control.COLOR_NEON);
            }

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 3;

            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }
}
