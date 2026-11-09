package vista;

import controlador.ControladorReportes;
import controlador.ControladorTaquilla;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modelo.Boleta;
import modelo.Evento;
import util.CodigoBarras39;

/**
 * Ventana de la Taquilla: el cajero elige un evento activo y vende un boleto.
 */
public class PanelTaquilla extends javax.swing.JPanel {

    // El controlador conecta esta pantalla con la base de datos
    private ControladorTaquilla controlador = new ControladorTaquilla();
    private ControladorReportes reportes = new ControladorReportes();

    // Datos del último boleto vendido (para imprimirlo o guardarlo)
    private Boleta ultimaBoleta = null;
    private Evento ultimoEvento = null;
    private BufferedImage ultimaImagen = null;

    public PanelTaquilla() {
        initComponents();
        lblBarras.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btnImprimir.setEnabled(false);
        btnGuardar.setEnabled(false);
        cargarEventos();
    }

    /**
     * Este método lo genera el diseñador de NetBeans (pestaña Design).
     * NO lo edite a mano: mueva los componentes con el mouse.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbEventos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblDisponibles = new javax.swing.JLabel();
        btnVender = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblBarras = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        lblTitulo.setText("TAQUILLA - Venta de boletos");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));

        jLabel1.setText("Evento:");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14));

        cmbEventos.addActionListener(this::cmbEventosActionPerformed);

        jLabel2.setText("Fecha:");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14));

        lblFecha.setText("-");
        lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 14));

        jLabel3.setText("Precio:");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14));

        lblPrecio.setText("-");
        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 14));

        jLabel4.setText("Disponibles:");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14));

        lblDisponibles.setText("-");
        lblDisponibles.setFont(new java.awt.Font("Segoe UI", 1, 14));

        btnVender.setText("VENDER BOLETO");
        btnVender.setFont(new java.awt.Font("Segoe UI", 1, 16));
        btnVender.addActionListener(this::btnVenderActionPerformed);

        jLabel5.setText("Código de barras generado:");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14));

        lblBarras.setText("");
        lblBarras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBarras.setBackground(new java.awt.Color(255, 255, 255));
        lblBarras.setOpaque(true);

        lblCodigo.setText("-");
        lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnImprimir.setText("Imprimir boleto (Jasper)");
        btnImprimir.addActionListener(this::btnImprimirActionPerformed);

        btnGuardar.setText("Guardar imagen PNG");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(700, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(cmbEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(710, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(505, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(420, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(385, 385, 385)
                .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(305, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(530, 530, 530)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(635, 635, 635)
                .addComponent(lblDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnVender, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(530, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(470, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(390, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(390, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(440, 440, 440)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(440, 440, 440)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(433, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(383, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(cmbEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(382, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(337, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(337, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(337, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(337, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(337, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(lblDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(337, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(btnVender, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(262, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(lblBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEventosActionPerformed
        mostrarDatosEvento();
    }//GEN-LAST:event_cmbEventosActionPerformed

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        Evento ev = (Evento) cmbEventos.getSelectedItem();
        if (ev == null) {
            JOptionPane.showMessageDialog(this, "Primero cree o seleccione un evento.");
            return;
        }
        try {
            Boleta b = controlador.venderBoleto(ev);
            if (b == null) {
                JOptionPane.showMessageDialog(this, "Evento agotado: ya no quedan entradas.");
                return;
            }
            // Guardamos el boleto vendido y dibujamos su código de barras
            ultimaBoleta = b;
            ultimoEvento = ev;
            ultimaImagen = CodigoBarras39.crear(b.getCodigoUnico());
            lblBarras.setIcon(new ImageIcon(ultimaImagen));
            lblCodigo.setText(b.getCodigoUnico());
            btnImprimir.setEnabled(true);
            btnGuardar.setEnabled(true);
            mostrarDatosEvento();   // actualiza las entradas disponibles
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo vender el boleto:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnVenderActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            reportes.verBoleto(ultimoEvento, ultimaBoleta, ultimaImagen);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el boleto:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Exporta el código de barras como imagen PNG
        JFileChooser selector = new JFileChooser();
        selector.setSelectedFile(new File(ultimaBoleta.getCodigoUnico() + ".png"));
        if (selector.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(ultimaImagen, "png", selector.getSelectedFile());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "No se pudo guardar la imagen:\n" + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    // Llena el combo con los eventos de la base de datos
    public void cargarEventos() {
        try {
            cmbEventos.removeAllItems();
            for (Evento ev : controlador.listarEventos()) {
                cmbEventos.addItem(ev);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los eventos:\n" + ex.getMessage());
        }
        mostrarDatosEvento();
    }

    // Muestra fecha, precio y entradas disponibles del evento elegido
    public void mostrarDatosEvento() {
        Evento ev = (Evento) cmbEventos.getSelectedItem();
        if (ev == null) {
            lblFecha.setText("-");
            lblPrecio.setText("-");
            lblDisponibles.setText("-");
            return;
        }
        lblFecha.setText(ev.getFecha().toString());
        lblPrecio.setText(String.format("$ %.2f", ev.getPrecio()));
        try {
            lblDisponibles.setText(String.valueOf(controlador.entradasDisponibles(ev)));
        } catch (SQLException ex) {
            lblDisponibles.setText("?");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnVender;
    private javax.swing.JComboBox<modelo.Evento> cmbEventos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblBarras;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDisponibles;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
