package GUI;

import Control.Control;
import GoOrderDTO.SucursalDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelSucursal extends JPanel {
    private SucursalDTO sucursal;
    private Control control;
    private SeleccionSucursalesDisponibles SucursalSeleccionada;

    public PanelSucursal(SucursalDTO s, Control control, SeleccionSucursalesDisponibles SucursalSeleccionada) {
        this.sucursal = s;
        this.control = control;
        this.SucursalSeleccionada = SucursalSeleccionada;

        setLayout(new BorderLayout(15, 0));
        setBackground(control.COLOR_TARJETA);
        setBorder(BorderFactory.createLineBorder(control.COLOR_TARJETA, 2, true));
        setMaximumSize(new Dimension(350, 120));
        setPreferredSize(new Dimension(350, 120));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon iconoOriginal = control.obtenerImagen(s.getNombreImagen());
        Image imgEscalada = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel lblIcono = new JLabel(new ImageIcon(imgEscalada));
        lblIcono.setPreferredSize(new Dimension(100, 100));
        add(lblIcono, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(control.COLOR_TARJETA);
        infoPanel.setBorder(new EmptyBorder(10, 0, 10, 10));

        JLabel lblNombre = new JLabel(s.getNombre());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setForeground(Color.WHITE);

        JLabel lblDireccion = new JLabel("Dirección: " + s.getCalle());
        lblDireccion.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDireccion.setForeground(Color.LIGHT_GRAY);

        JLabel lblColonia = new JLabel("Colonia: " + s.getColonia());
        lblColonia.setFont(new Font("Arial", Font.PLAIN, 12));
        lblColonia.setForeground(Color.LIGHT_GRAY);

        infoPanel.add(lblNombre);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(lblDireccion);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        infoPanel.add(lblColonia);

        if (s.getDescripcion() != null && !s.getDescripcion().isEmpty()) {
            JLabel lblDesc = new JLabel(s.getDescripcion());
            lblDesc.setFont(new Font("Arial", Font.ITALIC, 11));
            lblDesc.setForeground(control.COLOR_NEON);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            infoPanel.add(lblDesc);
        }

        add(infoPanel, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SucursalSeleccionada.gestionarSeleccion(PanelSucursal.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!setBorderColorIsNeon()) {
                    setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2, true));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!setBorderColorIsNeon()) {
                    desmarcar();
                }
            }
        });
    }

    private boolean setBorderColorIsNeon() {
        return getBorder() != null && sucursal.equals(SucursalSeleccionada.getSucursalSeleccionada());
    }

    public void marcarComoSeleccionada() {
        setBorder(BorderFactory.createLineBorder(control.COLOR_NEON, 2, true));
        repaint();
    }

    public void desmarcar() {
        setBorder(BorderFactory.createLineBorder(control.COLOR_TARJETA, 2, true));
        repaint();
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }
}