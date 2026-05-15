package GUI;

import Control.Control;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * Esta clase representa la ventana donde el usuario ve un mapa interactivo.
 * Su propósito es permitir al usuario buscar y seleccionar el punto exacto
 * donde quiere que le entreguen su pedido.
 */
public class AjustarDireccionMapa extends JFrame {

    private Control control;

    private JXMapViewer mapViewer;
    private WaypointPainter<Waypoint> waypointPainter;
    private GeoPosition ubicacionSeleccionada = null;

    /**
     * Constructor de la ventana. Aquí se arma toda la pantalla, incluyendo
     * el botón de regresar, el mapa cargado desde internet y el botón para confirmar.
     *
     * @param control El objeto principal que maneja los datos del programa y los cambios de pantalla.
     */
    public AjustarDireccionMapa(Control control) {
        setTitle("GoOrder - Seleccionar Ubicación");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        this.control = control;
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(control.COLOR_FONDO);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
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
            try {
                control.mostrarDomicilioFORM();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al regresar: " + ex.getMessage());
            }
        });

        JLabel lblTitulo = new JLabel("UBICA TU ENTREGA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(control.COLOR_NEON);
        headerPanel.add(btnRegresar, BorderLayout.WEST);
        headerPanel.add(lblTitulo);
        add(headerPanel, BorderLayout.NORTH);

        mapViewer = new JXMapViewer();

        TileFactoryInfo info = new TileFactoryInfo(1, 19, 19,
                256, true, true,
                "https://a.basemaps.cartocdn.com/light_all",
                "x", "y", "z") {
            public String getTileUrl(int x, int y, int zoom) {
                int z = 19 - zoom;
                return this.baseURL + "/" + z + "/" + x + "/" + y + ".png";
            }
        };
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        GeoPosition cdObregon = new GeoPosition(27.4961, -109.9328);
        mapViewer.setZoom(5);
        mapViewer.setAddressLocation(cdObregon);

        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

        waypointPainter = new WaypointPainter<>();
        mapViewer.setOverlayPainter(waypointPainter);

        mapViewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    ubicacionSeleccionada = mapViewer.convertPointToGeoPosition(e.getPoint());
                    actualizarPinEnMapa(ubicacionSeleccionada);
                }
            }
        });

        JPanel mapContainer = new JPanel(new BorderLayout());
        mapContainer.setBorder(BorderFactory.createLineBorder(control.COLOR_NEON, 2));
        mapContainer.add(mapViewer, BorderLayout.CENTER);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(control.COLOR_FONDO);
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        wrapperPanel.add(mapContainer, BorderLayout.CENTER);

        JLabel lblInstrucciones = new JLabel("Haz doble clic en el mapa para marcar tu ubicación", SwingConstants.CENTER);
        lblInstrucciones.setForeground(Color.LIGHT_GRAY);
        lblInstrucciones.setFont(new Font("Arial", Font.ITALIC, 12));
        lblInstrucciones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        wrapperPanel.add(lblInstrucciones, BorderLayout.SOUTH);

        add(wrapperPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(control.COLOR_FONDO);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        BotonNeon btnConfirmar = new BotonNeon("CONFIRMAR UBICACIÓN");
        btnConfirmar.setPreferredSize(new Dimension(340, 50));
        btnConfirmar.setMaximumSize(new Dimension(340, 50));

        btnConfirmar.addActionListener(e -> {
            if (ubicacionSeleccionada == null) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, selecciona una ubicación en el mapa haciendo doble clic.",
                        "Ubicación requerida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            btnConfirmar.setText("BUSCANDO DIRECCIÓN...");
            btnConfirmar.setEnabled(false);

            SwingWorker<String, Void> worker = new SwingWorker<>() {
                @Override
                protected String doInBackground() {
                    return obtenerDireccionDesdeCoordenadas(ubicacionSeleccionada);
                }

                @Override
                protected void done() {
                    try {
                        String direccionExacta = get();
                        int opcion = JOptionPane.showConfirmDialog(
                                AjustarDireccionMapa.this,
                                "¿Es esta tu dirección correcta?\n" + direccionExacta,
                                "Confirmar Dirección",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );
                        if (opcion == JOptionPane.YES_OPTION) {
                            control.getCarrito().setDireccionEntrega(direccionExacta);
                            control.mostrarTotalPrecioProductos();
                        } else {
                            btnConfirmar.setText("CONFIRMAR UBICACIÓN");
                            btnConfirmar.setEnabled(true);
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(AjustarDireccionMapa.this, "Error: " + ex.getMessage());
                        btnConfirmar.setText("CONFIRMAR UBICACIÓN");
                        btnConfirmar.setEnabled(true);
                    }
                }
            };
            worker.execute();
        });

        footerPanel.add(btnConfirmar);
        add(footerPanel, BorderLayout.SOUTH);
    }

    /**
     * Pone una marca o "pin" en el mapa para que el usuario vea dónde hizo clic.
     *
     * @param posicion Las coordenadas de latitud y longitud donde se pondrá la marca.
     */
    private void actualizarPinEnMapa(GeoPosition posicion) {
        Set<Waypoint> waypoints = new HashSet<>();
        waypoints.add(new DefaultWaypoint(posicion));
        waypointPainter.setWaypoints(waypoints);
        mapViewer.repaint();
    }

    /**
     * Toma unas coordenadas del mapa y se conecta a internet para convertirlas
     * en el nombre de la calle y ciudad.
     *
     * @param posicion Las coordenadas que seleccionó el usuario en el mapa.
     * @return Una cadena de texto con la dirección real encontrada.
     */
    private String obtenerDireccionDesdeCoordenadas(GeoPosition posicion) {
        String direccion = "Dirección no encontrada";
        try {
            String urlString = "https://nominatim.openstreetmap.org/reverse?format=json&lat="
                    + posicion.getLatitude() + "&lon=" + posicion.getLongitude();

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "GoOrderApp/1.0");

            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonResponse = response.toString();
                String key = "\"display_name\":\"";
                int startIndex = jsonResponse.indexOf(key);
                if (startIndex != -1) {
                    startIndex += key.length();
                    int endIndex = jsonResponse.indexOf("\"", startIndex);
                    direccion = jsonResponse.substring(startIndex, endIndex);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al obtener la dirección: " + ex.getMessage());
        }
        return direccion;
    }

    /**
     * Clase interna que sirve para crear los botones personalizados de la aplicación.
     * Estos botones tienen bordes redondeados y cambian de color cuando el ratón pasa por encima.
     */
    class BotonNeon extends JButton {
        private boolean over = false;

        /**
         * Crea un botón visualmente personalizado.
         *
         * @param texto La palabra o mensaje que va a mostrar el botón adentro.
         */
        public BotonNeon(String texto) {
            super(texto);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setAlignmentX(Component.CENTER_ALIGNMENT);

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { over = true; repaint(); }
                public void mouseExited(MouseEvent e) { over = false; repaint(); }
            });
        }

        /**
         * Dibuja los colores y la forma del botón en la pantalla.
         *
         * @param g La herramienta de gráficos que Java usa para dibujar cosas en la ventana.
         */
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (!isEnabled()) {
                g2.setColor(Color.DARK_GRAY);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(Color.GRAY);
                g2.setFont(new Font("Arial", Font.BOLD, 14));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
                return;
            }
            if (over) {
                g2.setColor(control.COLOR_NEON);
            } else {
                g2.setColor(control.COLOR_BOTON);
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