package GUI;

import Control.Control;
import GoOrderDTO.CarritoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoSeleccionadoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.NegocioException;

/**
 * Esta clase representa la pantalla donde el usuario puede ingresar un código
 * de promoción o descuento antes de pagar.
 * También muestra un pequeño resumen tipo "ticket" con los productos
 * del carrito, el subtotal y el total final a pagar.
 */
public class CodigoDescuentoFORM extends JFrame {

    private Control control;
    private String descuentoActual = "";

    private JTextField txtCodigo;
    private JLabel lblCantidadDescuento;

    /**
     * Constructor de la ventana de descuentos.
     * Se encarga de armar la interfaz gráfica, crear la caja de texto para escribir
     * el código, y generar la lista del resumen de compra leyendo los datos del carrito.
     *
     * @param control El objeto principal que maneja los datos y los cambios de pantalla en GoOrder.
     */
    public CodigoDescuentoFORM(Control control) {
        this.control = control;

        setTitle("GoOrder - Código de Descuento");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(control.COLOR_FONDO);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(control.COLOR_FONDO);
        headerPanel.setBorder(new EmptyBorder(20, 20, 10, 20));

        JButton btnRegresar = new JButton("←");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 24));
        btnRegresar.setForeground(Color.LIGHT_GRAY);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRegresar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnRegresar.setForeground(control.COLOR_NEON); }
            public void mouseExited(MouseEvent e) { btnRegresar.setForeground(Color.LIGHT_GRAY); }
        });

        btnRegresar.addActionListener(e -> {
            control.mostrarTotalPrecioProductos();
        });

        JLabel lblTitulo = new JLabel("CÓDIGO DESCUENTO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(control.COLOR_NEON);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblEspacio = new JLabel("    ");
        lblEspacio.setPreferredSize(btnRegresar.getPreferredSize());

        headerPanel.add(btnRegresar, BorderLayout.WEST);
        headerPanel.add(lblTitulo, BorderLayout.CENTER);
        headerPanel.add(lblEspacio, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(control.COLOR_FONDO);
        contentPanel.setBorder(new EmptyBorder(10, 30, 10, 30));

        JPanel panelInput = new JPanel(new BorderLayout(10, 0));
        panelInput.setBackground(control.COLOR_FONDO);
        panelInput.setMaximumSize(new Dimension(340, 45));

        txtCodigo = new JTextField();
        txtCodigo.setBackground(control.COLOR_TARJETA);
        txtCodigo.setForeground(Color.WHITE);
        txtCodigo.setFont(new Font("Arial", Font.BOLD, 16));
        txtCodigo.setCaretColor(control.COLOR_NEON);
        txtCodigo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true),
                new EmptyBorder(5, 10, 5, 10)
        ));



        panelInput.add(txtCodigo, BorderLayout.CENTER);

        contentPanel.add(panelInput);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel panelResumen = new JPanel();
        panelResumen.setLayout(new BoxLayout(panelResumen, BoxLayout.Y_AXIS));
        panelResumen.setBackground(control.COLOR_TARJETA);
        panelResumen.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(control.COLOR_BOTON, 2, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        Font fontNormal = new Font("Arial", Font.PLAIN, 15);
        Font fontBold = new Font("Arial", Font.BOLD, 16);

        try {
            CarritoDTO miCarrito = control.getCarrito();

            if (miCarrito != null && miCarrito.getProductos() != null) {

                for (ProductoSeleccionadoDTO p : miCarrito.getProductos()) {
                    panelResumen.add(crearFilaTexto(
                            p.getNombre() + " x" + p.getCantidad(),
                            String.format("$%.2f", p.getImporte()),
                            fontNormal,
                            Color.WHITE
                    ));
                }

                panelResumen.add(Box.createRigidArea(new Dimension(0, 10)));

                panelResumen.add(crearFilaTexto("Subtotal", String.format("$%.2f", miCarrito.getSubTotal()), fontNormal, Color.WHITE));

                if (miCarrito.getDescuento() > 0) {
                }

                panelResumen.add(Box.createRigidArea(new Dimension(0, 10)));
                JSeparator sep = new JSeparator();
                sep.setForeground(Color.DARK_GRAY);
                panelResumen.add(sep);
                panelResumen.add(Box.createRigidArea(new Dimension(0, 10)));

                panelResumen.add(crearFilaTexto("TOTAL", String.format("$%.2f", miCarrito.getTotal()), fontBold, Color.WHITE));
            }
        } catch (Exception ex) {
            Logger.getLogger(CodigoDescuentoFORM.class.getName()).log(Level.SEVERE, "Error al cargar resumen", ex);
        }

        contentPanel.add(panelResumen);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));


        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        contentPanel.add(Box.createVerticalGlue());
        add(contentPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(control.COLOR_FONDO);
        footerPanel.setBorder(new EmptyBorder(10, 30, 30, 30));
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        BotonNeon btnAplicar = new BotonNeon("CONFIRMAR");

        btnAplicar.addActionListener(e -> accionAplicarDescuento());

        footerPanel.add(btnAplicar);
        add(footerPanel, BorderLayout.SOUTH);
    }

    /**
     * Este método se activa cuando el usuario hace clic en el botón de confirmar.
     * Lee el código que el usuario escribió en la caja de texto y le pide a la capa de negocio
     * que valide si existe o no. Si funciona, muestra un mensaje de éxito, de lo contrario
     * avisa que el código no es válido.
     */
    private void accionAplicarDescuento() {
        String textoIngresado = txtCodigo.getText().trim();
        if (textoIngresado.isEmpty()) {
            control.mostrarCodigoDescuentoRechazado();
            return;
        }

        try {
            control.AplicarDescuento(txtCodigo.getText());
            JOptionPane.showMessageDialog(this, "Descuento aplicado al ticket.");
            control.mostrarTotalPrecioProductos();

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this,
                    "El código ingresado no es válido o no existe.",
                    "Código no válido",
                    JOptionPane.WARNING_MESSAGE);
            System.out.println("Aviso: Intento de descuento fallido. Motivo: " + ex.getMessage());
        }
    }

    /**
     * Es un método de ayuda para crear los renglones del resumen de compra.
     * Acomoda un texto del lado izquierdo (como el nombre del producto) y
     * otro del lado derecho (como su precio).
     *
     * @param textoIzq El texto que aparecerá a la izquierda.
     * @param textoDer El texto que aparecerá a la derecha.
     * @param fuente El tipo de letra y tamaño que se usará.
     * @param colorTexto El color de las letras.
     * @return Un pequeño panel ya organizado con los dos textos.
     */
    private JPanel crearFilaTexto(String textoIzq, String textoDer, Font fuente, Color colorTexto) {
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
        return panelFila;
    }

    /**
     * Clase interna para generar el botón llamativo de la pantalla.
     * Tiene un estilo oscuro por defecto, pero sus bordes y texto cambian de color
     * al interactuar con el ratón.
     */
    class BotonNeon extends JButton {
        private boolean over = false;

        /**
         * Construye el botón personalizado.
         *
         * @param texto El mensaje que el botón mostrará en su interior.
         */
        public BotonNeon(String texto) {
            super(texto);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(340, 50));
            setMaximumSize(new Dimension(340, 50));

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { over = true; repaint(); }
                public void mouseExited(MouseEvent e) { over = false; repaint(); }
            });
        }

        /**
         * Define visualmente cómo se dibuja el botón. Evalúa si el ratón está
         * pasando por encima para pintar su color de iluminación o mantener el color original.
         *
         * @param g La herramienta de Java para pintar gráficos en la pantalla.
         */
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (over) g2.setColor(control.COLOR_NEON);
            else g2.setColor(control.COLOR_TARJETA);

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