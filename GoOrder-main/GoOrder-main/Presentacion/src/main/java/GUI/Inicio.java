package GUI;

import Control.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.NegocioException;

public class Inicio extends JFrame {
    private final Control control;

    public Inicio(Control control) {
        this.control = control;

        setTitle("GoOrder - Inicio");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(control.COLOR_FONDO);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(control.COLOR_FONDO);

        contentPanel.add(Box.createVerticalGlue());

        JLabel lblTitulo = new JLabel("GoOrder");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 56));
        lblTitulo.setForeground(control.COLOR_NEON);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Tu comida, al instante.");
        lblSubtitulo.setFont(new Font("Arial", Font.ITALIC, 16));
        lblSubtitulo.setForeground(Color.LIGHT_GRAY);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(lblTitulo);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(lblSubtitulo);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 80)));

        BotonNeon btnOrdenar = new BotonNeon("ORDENAR AHORA");

        btnOrdenar.addActionListener(e -> {
            if (this.control != null) {

                try {
                    this.control.mostrarProductosFORM();
                } catch (NegocioException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    System.getLogger(Inicio.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        });

        contentPanel.add(btnOrdenar);

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
            setPreferredSize(new Dimension(280, 60));
            setMaximumSize(new Dimension(280, 60));

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
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            g2.setFont(new Font("Arial", Font.BOLD, 18));
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