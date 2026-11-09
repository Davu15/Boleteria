package vista;

import controlador.ControladorReportes;
import controlador.ControladorVentas;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.Boleta;
import modelo.Sesion;

/**
 * Ventana de Ventas: lista las boletas (el administrador ve todas, los demás solo las suyas), permite anular y abrir el reporte de Jasper.
 */
public class PanelVentas extends javax.swing.JPanel {

    private ControladorVentas controlador = new ControladorVentas();
    private ControladorReportes reportes = new ControladorReportes();

    public PanelVentas() {
        initComponents();
        tblVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblVentas.getColumnModel().getColumn(0).setPreferredWidth(40);   // columna ID angosta
        btnAnular.setEnabled(Sesion.esAdmin());   // solo el administrador anula boletas
        cargarTabla();
    }

    /**
     * Este método lo genera el diseñador de NetBeans (pestaña Design).
     * NO lo edite a mano: mueva los componentes con el mouse.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();

        lblTitulo.setText("VENTAS - Boletas registradas");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Evento", "Código", "Estado", "Fecha de venta", "Vendedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblVentas);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnAnular.setText("Anular boleta");
        btnAnular.addActionListener(this::btnAnularActionPerformed);

        btnReporte.setText("Reporte de ventas (Jasper)");
        btnReporte.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnReporte.addActionListener(this::btnReporteActionPerformed);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(640, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(475, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(433, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        cargarTabla();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        int fila = tblVentas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una boleta de la tabla.");
            return;
        }
        int id = Integer.parseInt(tblVentas.getValueAt(fila, 0).toString());
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Anular la boleta seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            if (controlador.anular(id) == 0) {
                JOptionPane.showMessageDialog(this, "Solo se pueden anular boletas en estado DISPONIBLE.");
            }
            cargarTabla();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo anular:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        try {
            reportes.verReporteVentas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el reporte:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    // Vuelve a leer las boletas de la base de datos y llena la tabla
    public void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tblVentas.getModel();
        modelo.setRowCount(0);   // borra las filas anteriores
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            for (Boleta b : controlador.listar()) {
                modelo.addRow(new Object[]{
                    b.getIdBoleta(), b.getNombreEvento(), b.getCodigoUnico(), b.getEstado(),
                    formato.format(b.getFechaVenta()), b.getNombreVendedor()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo leer las ventas:\n" + ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnReporte;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblVentas;
    // End of variables declaration//GEN-END:variables
}
