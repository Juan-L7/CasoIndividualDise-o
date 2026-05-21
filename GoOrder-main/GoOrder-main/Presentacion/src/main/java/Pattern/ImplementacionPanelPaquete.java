package Pattern;

import Control.Control;
import GoOrderDTO.PaqueteDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoSeleccionadoDTO;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImplementacionPanelPaquete extends javax.swing.JPanel implements IPaneles {

    private Control control;
    private PaqueteDTO paquete;

    private Color colorOriginal;
    private Color colorIluminado;

    public ImplementacionPanelPaquete(Control control, PaqueteDTO paquete) {

        this.control = control;
        this.paquete = paquete;

        initComponents();

        lbNombre.setText(paquete.getNombre());
        lbPrecio.setText("$" + paquete.getPrecio());

        String textoBase64 = paquete.getImagen().getImagen();

        if (textoBase64 != null && !textoBase64.trim().isEmpty()) {

            IngresarImagen.ingresarImagenBase64(
                    lbImagen,
                    textoBase64,
                    170,
                    150
            );

        } else {

            lbImagen.setText("SIN IMAGEN");
        }
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbNombre = new javax.swing.JLabel();
        lbPrecio = new javax.swing.JLabel();
        btnAgregar = new BotonRedondeado();
        btnDescripcion = new BotonRedondeado();
        lbImagen = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        lbNombre.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lbNombre.setForeground(new java.awt.Color(255, 255, 255));

        lbPrecio.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lbPrecio.setForeground(new java.awt.Color(0, 255, 150));

        // BOTÓN +
        btnAgregar.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 24));
        btnAgregar.setForeground(new java.awt.Color(0, 255, 150));
        btnAgregar.setText("+");
        btnAgregar.setBorderPainted(false);
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setFocusPainted(false);

        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorIluminado = new Color(51, 136, 20);
                btnAgregar.setBackground(colorIluminado);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorOriginal = new Color(35, 35, 35);
                btnAgregar.setBackground(colorOriginal);
            }
        });

        btnAgregar.addActionListener(evt -> {

            try {

                ProductoDTO productoPaquete = new ProductoDTO();

                productoPaquete.setNombre(paquete.getNombre());
                productoPaquete.setPrecio(paquete.getPrecio());
                productoPaquete.setImagen(paquete.getImagen().getImagen());

                ProductoSeleccionadoDTO paqueteSeleccionado
                        = new ProductoSeleccionadoDTO(
                                paquete.getNombre(),
                                1,
                                paquete.getPrecio(),
                                paquete.getPrecio(),
                                productoPaquete
                        );

                control.agregarProducto(paqueteSeleccionado);

                JOptionPane.showMessageDialog(
                        this,
                        "Paquete agregado al carrito"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error al agregar paquete: " + ex.getMessage()
                );
            }
        });

        // BOTÓN ...
        btnDescripcion.setBackground(new java.awt.Color(0, 0, 0));
        btnDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 24));
        btnDescripcion.setForeground(new java.awt.Color(0, 255, 150));
        btnDescripcion.setText("...");
        btnDescripcion.setBorderPainted(false);
        btnDescripcion.setContentAreaFilled(false);
        btnDescripcion.setFocusPainted(false);

        btnDescripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorIluminado = new Color(51, 136, 20);
                btnDescripcion.setBackground(colorIluminado);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorOriginal = new Color(35, 35, 35);
                btnDescripcion.setBackground(colorOriginal);
            }
        });

        btnDescripcion.addActionListener(evt -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Descripción del paquete:\n" + paquete.getNombre()
            );
        });

        lbImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout
                = new javax.swing.GroupLayout(jPanel1);

        jPanel1.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lbPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                        .addComponent(btnDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout
                = new javax.swing.GroupLayout(this);

        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lbImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }

    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnDescripcion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbImagen;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbPrecio;
}