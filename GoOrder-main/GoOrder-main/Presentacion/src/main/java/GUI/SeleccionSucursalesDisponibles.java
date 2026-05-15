package GUI;

import Control.Control;
import GoOrderDTO.SucursalDTO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa la pantalla donde el usuario puede ver y elegir
 * a qué sucursal quiere ir a recoger su pedido (cuando selecciona la opción "Pick Up").
 */
public class SeleccionSucursalesDisponibles extends JFrame {

    private Control control;
    private SucursalDTO sucursalSeleccionada = null;
    private List<PanelSucursal> listaTarjetas = new ArrayList<>();
    private BotonConfirmar btnConfirmar;

    /**
     * Constructor de la ventana. Se encarga de armar la interfaz gráfica,
     * crear la lista de sucursales en la pantalla y configurar el botón de confirmar.
     *
     * @param control El objeto principal que nos permite cambiar de pantallas y manejar datos.
     * @param sucursalesDisponibles Una lista con todas las sucursales que se van a mostrar en la pantalla.
     */
    public SeleccionSucursalesDisponibles(Control control, List<SucursalDTO> sucursalesDisponibles) {
        this.control = control;

        setTitle("GoOrder - Sucursales");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(control.COLOR_FONDO);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JButton btnRegresar = new JButton("←");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 24));
        btnRegresar.setForeground(Color.LIGHT_GRAY);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRegresar.addActionListener(e -> control.mostrarSeleccionMetodoEntrega());

        JLabel lblTitulo = new JLabel("SUCURSALES DISPONIBLES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(control.COLOR_NEON);

        headerPanel.add(btnRegresar);
        headerPanel.add(lblTitulo);
        add(headerPanel, BorderLayout.NORTH);

        JPanel panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBackground(control.COLOR_FONDO);
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (SucursalDTO s : sucursalesDisponibles) {
            PanelSucursal tarjeta = new PanelSucursal(s, control, this);
            listaTarjetas.add(tarjeta);
            panelLista.add(tarjeta);
            panelLista.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        JScrollPane scrollPane = new JScrollPane(panelLista);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(control.COLOR_FONDO);
        add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(control.COLOR_FONDO);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnConfirmar = new BotonConfirmar("CONFIRMAR");
        btnConfirmar.setEnabled(false);
        btnConfirmar.addActionListener(e -> control.mostrarTotalPrecioProductos());

        footerPanel.add(btnConfirmar);
        add(footerPanel, BorderLayout.SOUTH);
    }

    /**
     * Este método se encarga de que solo una sucursal pueda estar seleccionada a la vez.
     * Si el usuario hace clic en una sucursal, desmarca todas las demás, marca la elegida
     * y enciende el botón de "Confirmar".
     *
     * @param tarjetaSeleccionada El panel visual de la sucursal a la que el usuario le hizo clic.
     */
    public void gestionarSeleccion(PanelSucursal tarjetaSeleccionada) {
        for (PanelSucursal t : listaTarjetas) {
            t.desmarcar();
        }

        tarjetaSeleccionada.marcarComoSeleccionada();

        this.sucursalSeleccionada = tarjetaSeleccionada.getSucursal();
        btnConfirmar.setEnabled(true);
        btnConfirmar.repaint();
    }

    /**
     * Permite obtener la sucursal que el usuario eligió al final.
     *
     * @return El objeto SucursalDTO con los datos de la sucursal seleccionada.
     */
    public SucursalDTO getSucursalSeleccionada() {
        return sucursalSeleccionada;
    }

    /**
     * Clase interna para crear un botón de confirmación personalizado.
     * Su aspecto cambia (se ilumina) cuando el usuario ya seleccionó una sucursal,
     * indicando que ya puede avanzar.
     */
    class BotonConfirmar extends JButton {

        /**
         * Crea el botón de confirmación.
         *
         * @param texto El texto que dirá el botón (ej. "CONFIRMAR").
         */
        public BotonConfirmar(String texto) {
            super(texto);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(300, 50));
        }

        /**
         * Dibuja los colores del botón dependiendo de si está activado (verde neón)
         * o desactivado (gris oscuro).
         *
         * @param g La herramienta gráfica de Java.
         */
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (isEnabled()) {
                g2.setColor(control.COLOR_NEON);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(control.COLOR_TARJETA);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(Color.GRAY);
            }

            g2.setFont(new Font("Arial", Font.BOLD, 14));
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            g2.drawString(getText(), x, y);
            g2.dispose();
        }
    }
}