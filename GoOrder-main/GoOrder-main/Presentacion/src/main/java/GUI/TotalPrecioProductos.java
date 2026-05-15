package GUI;

import Control.Control;
import GoOrderDTO.CarritoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoSeleccionadoDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import org.example.NegocioException;

public class TotalPrecioProductos extends JFrame {

    private final Color COLOR_FONDO = new Color(18, 18, 18);
    private final Color COLOR_NEON = new Color(0, 255, 150);
    private final Color COLOR_BOTON = new Color(35, 35, 35);
    private final Color COLOR_TICKET = new Color(25, 25, 25);

    private Control control;

    public TotalPrecioProductos(Control control) {
        this.control = control;

        setTitle("GoOrder - Resumen de Compra");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_FONDO);

        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.setBackground(COLOR_FONDO);
        panelImagen.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        JButton btnRegresar = new JButton("←");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 24));
        btnRegresar.setForeground(Color.LIGHT_GRAY);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnRegresar.setForeground(new Color(0, 255, 150));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnRegresar.setForeground(Color.LIGHT_GRAY);
            }
        });

        btnRegresar.addActionListener(e -> {
            control.mostrarInicio();
        });
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        try {
            File archivoImagen = new File(".." + File.separator + "Resources" + File.separator + "cafe_interior.png");
            if (!archivoImagen.exists()) {
                archivoImagen = new File("Resources" + File.separator + "cafe_interior.png");
            }
            if (archivoImagen.exists()) {
                ImageIcon originalIcon = new ImageIcon(archivoImagen.getAbsolutePath());
                Image imgEscalada = originalIcon.getImage().getScaledInstance(340, 180, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(imgEscalada));
            } else {
                lblImagen.setText("[ IMAGEN DE CAFETERÍA ]");
                lblImagen.setForeground(Color.DARK_GRAY);
                lblImagen.setPreferredSize(new Dimension(340, 180));
                lblImagen.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        panelImagen.add(lblImagen, BorderLayout.CENTER);
        add(panelImagen, BorderLayout.NORTH);


        JPanel panelTicket = new JPanel();
        panelTicket.setLayout(new BoxLayout(panelTicket, BoxLayout.Y_AXIS));
        panelTicket.setBackground(COLOR_TICKET);
        panelTicket.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20),
                BorderFactory.createLineBorder(COLOR_BOTON, 2, true)
        ));

        JPanel interiorTicket = new JPanel();
        interiorTicket.setLayout(new BoxLayout(interiorTicket, BoxLayout.Y_AXIS));
        interiorTicket.setOpaque(false);
        interiorTicket.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        Font fontNormal = new Font("Arial", Font.PLAIN, 16);
        Font fontBold = new Font("Arial", Font.BOLD, 16);
        Font fontTotal = new Font("Arial", Font.BOLD, 20);

        try {
            CarritoDTO miCarrito = control.getCarrito();

            if (miCarrito != null && miCarrito.getProductos() != null) {
                
                for (ProductoSeleccionadoDTO producto : miCarrito.getProductos()) {
                    String nombreProducto = producto.getNombre() + " (x" + producto.getCantidad() + ")"; 
                    double importeProducto = producto.getImporte(); 

                    interiorTicket.add(crearFila(nombreProducto, String.format("$%.2f", importeProducto), fontNormal, Color.WHITE));
                }

                interiorTicket.add(Box.createRigidArea(new Dimension(0, 10)));
                JSeparator sep1 = new JSeparator();
                sep1.setForeground(Color.DARK_GRAY);
                interiorTicket.add(sep1);
                interiorTicket.add(Box.createRigidArea(new Dimension(0, 10)));

                double subtotal = miCarrito.getSubTotal();
                double descuento = miCarrito.getDescuento();

                interiorTicket.add(crearFila("Subtotal", String.format("$%.2f", subtotal), fontBold, Color.WHITE));

                if (descuento > 0) {
                    interiorTicket.add(crearFila("Descuento", String.format("-$%.2f", descuento), fontNormal, COLOR_NEON));
                }

                double ivaVisual = (subtotal - descuento) * 0.16; 
                interiorTicket.add(crearFila("I.V.A (Incluido)", String.format("$%.2f", ivaVisual), fontNormal, Color.LIGHT_GRAY));

                interiorTicket.add(Box.createRigidArea(new Dimension(0, 10)));
                JSeparator sep2 = new JSeparator();
                sep2.setForeground(Color.DARK_GRAY);
                interiorTicket.add(sep2);
                interiorTicket.add(Box.createRigidArea(new Dimension(0, 10)));

                double totalFinal = miCarrito.getTotal()+ ivaVisual;
                miCarrito.setTotal(totalFinal);
                interiorTicket.add(crearFila("TOTAL", String.format("$%.2f", totalFinal), fontTotal, COLOR_NEON));
            }
            
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el carrito: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        panelTicket.add(interiorTicket);

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        centerWrapper.add(panelTicket, BorderLayout.CENTER);
        add(centerWrapper, BorderLayout.CENTER);



        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(COLOR_FONDO);
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));

        BotonNeon btnDescuento = new BotonNeon("APLICAR DESCUENTO");
        BotonNeon btnPago = new BotonNeon("FORMA DE PAGO");

        btnDescuento.addActionListener(e -> {
            try {
                if(control.getCarrito().getDescuento()>0){
                    JOptionPane.showMessageDialog(this, "Ya aplicaste un descuento " , "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                control.mostrarCodigoDescuento();
                }
            } catch (NegocioException ex) {
                System.getLogger(TotalPrecioProductos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
           
        });

        btnPago.addActionListener(e -> {
        control.mostrarSeleccionFormaPago();
        
        });

        footerPanel.add(btnDescuento);
        footerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        footerPanel.add(btnPago);

        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel crearFila(String textoIzq, String textoDer, Font fuente, Color colorTexto) {
        JPanel panelFila = new JPanel(new BorderLayout());
        panelFila.setOpaque(false);

        JLabel lblIzq = new JLabel(textoIzq);
        lblIzq.setFont(fuente);
        lblIzq.setForeground(colorTexto);

        JLabel lblDer = new JLabel(textoDer);
        lblDer.setFont(fuente);
        lblDer.setForeground(colorTexto);

        panelFila.add(lblIzq, BorderLayout.WEST);
        panelFila.add(lblDer, BorderLayout.EAST);
        panelFila.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panelFila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        return panelFila;
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
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(340, 45));
            setMaximumSize(new Dimension(340, 45));

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
                g2.setColor(COLOR_NEON);
            } else {
                g2.setColor(COLOR_BOTON);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            g2.setFont(new Font("Arial", Font.BOLD, 14));
            if (over) {
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.setColor(Color.WHITE);
                g2.drawRoundRect(2, 2, getWidth()-5, getHeight()-5, 15, 15);
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(Color.WHITE);
            }

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }


}