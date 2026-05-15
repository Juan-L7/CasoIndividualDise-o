package GUI;

import Control.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeleccionFormaPago extends JFrame {

    private final Color COLOR_FONDO = new Color(18, 18, 18);
    private final Color COLOR_NEON = new Color(0, 255, 150);
    private final Color COLOR_BOTON = new Color(35, 35, 35);

    private Control control;

    public SeleccionFormaPago(Control control) {
        this.control = control;

        setTitle("GoOrder - Forma de Pago");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_FONDO);


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(COLOR_FONDO);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JButton btnRegresar = new JButton("←");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 24));
        btnRegresar.setForeground(Color.LIGHT_GRAY);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRegresar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnRegresar.setForeground(COLOR_NEON); }
            public void mouseExited(MouseEvent e) { btnRegresar.setForeground(Color.LIGHT_GRAY); }
        });

        btnRegresar.addActionListener(e -> {
           control.mostrarTotalPrecioProductos();
        });

        JLabel lblTitulo = new JLabel("FORMA DE PAGO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(COLOR_NEON);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblEspacio = new JLabel("    ");
        lblEspacio.setPreferredSize(btnRegresar.getPreferredSize());

        headerPanel.add(btnRegresar, BorderLayout.WEST);
        headerPanel.add(lblTitulo, BorderLayout.CENTER);
        headerPanel.add(lblEspacio, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(COLOR_FONDO);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

        JLabel lblInstruccion = new JLabel("SELECCIONA UN MÉTODO:");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 14));
        lblInstruccion.setForeground(Color.WHITE);
        lblInstruccion.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(lblInstruccion);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        BotonNeon btnTarjeta = new BotonNeon("PAGAR CON TARJETA");
        BotonNeon btnEfectivo = new BotonNeon("PAGAR CON EFECTIVO");
        BotonNeon btnReferencia = new BotonNeon("PAGAR CON REFERENCIA");

        btnTarjeta.addActionListener(e -> {
            control.mostrarPagoTarjeta();
        });

        btnEfectivo.addActionListener(e -> {
            control.mostrarPagoEfectivo();
        });

        btnReferencia.addActionListener(e -> {
            control.mostrarPagoReferencia();
        });

        contentPanel.add(btnTarjeta);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        contentPanel.add(btnEfectivo);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        contentPanel.add(btnReferencia);

        contentPanel.add(Box.createVerticalGlue());

        add(contentPanel, BorderLayout.CENTER);
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
            setPreferredSize(new Dimension(340, 55));
            setMaximumSize(new Dimension(340, 55));

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