package GUI;

import Control.Control;
import GoOrderDTO.CarritoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Agradecimiento extends JFrame {


    private Control control;

    public Agradecimiento(Control control) {
        this.control = control;

        setTitle("GoOrder - Confirmación de Pedido");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(control.COLOR_FONDO);


        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(control.COLOR_FONDO);
        headerPanel.setBorder(new EmptyBorder(30, 20, 10, 20));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(control.COLOR_FONDO);
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        contentPanel.add(Box.createVerticalGlue());

        JLabel lblGracias = new JLabel("GRACIAS POR");
        lblGracias.setFont(new Font("Arial", Font.BOLD, 32));
        lblGracias.setForeground(control.COLOR_NEON);
        lblGracias.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblPedido = new JLabel("TU PEDIDO!");
        lblPedido.setFont(new Font("Arial", Font.BOLD, 32));
        lblPedido.setForeground(control.COLOR_NEON);
        lblPedido.setAlignmentX(Component.CENTER_ALIGNMENT);
        String textoMensaje = "TE ESPERAMOS";
        try {
            CarritoDTO carrito = control.getCarrito();
            if (carrito != null && carrito.getMetodoEntrega() != null) {
                if (carrito.getMetodoEntrega().equals("DOMICILIO")) {
                    textoMensaje = "TU PEDIDO ESTÁ EN CAMINO";
                }
            }
        } catch (Exception ex) {
            System.out.println("No se pudo cargar el método de entrega: " + ex.getMessage());
        }
        JLabel lblEsperamos = new JLabel(textoMensaje);
        lblEsperamos.setFont(new Font("Arial", Font.PLAIN, 18));
        lblEsperamos.setForeground(Color.LIGHT_GRAY);
        lblEsperamos.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(lblGracias);
        contentPanel.add(lblPedido);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(lblEsperamos);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(Box.createVerticalGlue());
        add(contentPanel, BorderLayout.CENTER);


        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(control.COLOR_FONDO);
        footerPanel.setBorder(new EmptyBorder(10, 30, 30, 30));
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        BotonNeon btnInicio = new BotonNeon("VOLVER AL INICIO");

        btnInicio.addActionListener(e -> {
            if (control != null) {

                control.mostrarInicio();
                
                
            }
        });

        footerPanel.add(btnInicio);
        add(footerPanel, BorderLayout.SOUTH);
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
            setPreferredSize(new Dimension(340, 50));
            setMaximumSize(new Dimension(340, 50));

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { over = true; repaint(); }
                public void mouseExited(MouseEvent e) { over = false; repaint(); }
            });
        }

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