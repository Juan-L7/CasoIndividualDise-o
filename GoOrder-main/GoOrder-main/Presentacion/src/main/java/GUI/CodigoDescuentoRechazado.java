package GUI;

import Control.Control;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Esta clase representa una pantalla de aviso o alerta en la aplicación GoOrder.
 * Se muestra específicamente cuando el usuario intenta usar un código de descuento
 * que está mal escrito, no existe o simplemente no es válido.
 */
public class CodigoDescuentoRechazado extends JFrame {



    private Control control;

    /**
     * Constructor de la ventana. Se encarga de armar la interfaz visual,
     * mostrando un mensaje grande y claro de que el código falló, y acomodando
     * un botón en la parte de abajo para que el usuario pueda regresar a intentarlo de nuevo.
     *
     * @param control El objeto principal que maneja los datos y nos permite regresar a la pantalla anterior.
     */
    public CodigoDescuentoRechazado(Control control) {
        this.control = control;

        setTitle("GoOrder - Código No Válido");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(control.COLOR_FONDO);


        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(control.COLOR_FONDO);
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        contentPanel.add(Box.createVerticalGlue());



        JLabel lblTitulo = new JLabel("CÓDIGO NO VÁLIDO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(control.COLOR_ERROR);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Intenta volver a ingresar el código.");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubtitulo.setForeground(Color.LIGHT_GRAY);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(lblTitulo);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(lblSubtitulo);

        contentPanel.add(Box.createVerticalGlue());

        add(contentPanel, BorderLayout.CENTER);


        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(control.COLOR_FONDO);
        footerPanel.setBorder(new EmptyBorder(10, 30, 30, 30));
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        BotonNeon btnRegresar = new BotonNeon("REGRESAR");

        btnRegresar.addActionListener(e -> {
            control.mostrarCodigoDescuento();
        });

        footerPanel.add(btnRegresar);
        add(footerPanel, BorderLayout.SOUTH);
    }

    /**
     * Clase interna para crear un botón con estilo personalizado.
     * En esta pantalla en particular, el botón tiene un diseño un poco más discreto
     * (sin los colores neón brillantes) para indicar que es una acción secundaria
     * o de "regresar".
     */
    class BotonNeon extends JButton {
        private boolean over = false;

        /**
         * Construye el botón personalizado.
         *
         * @param texto El texto que aparecerá adentro del botón (ej. "REGRESAR").
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
         * Dibuja los colores y la forma del botón. Cuando el ratón pasa por encima,
         * hace que el color oscuro se vuelva un poco más claro para darle entender
         * al usuario que se le puede hacer clic.
         *
         * @param g La herramienta gráfica que usa Java para dibujar en la pantalla.
         */
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (over) {
                g2.setColor(control.COLOR_TARJETA.brighter());
            } else {
                g2.setColor(control.COLOR_TARJETA);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            g2.setFont(new Font("Arial", Font.BOLD, 14));
            g2.setColor(Color.WHITE);

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            g2.drawString(getText(), x, y);
            g2.dispose();
        }
    }


}