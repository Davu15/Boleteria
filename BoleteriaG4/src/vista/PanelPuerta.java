package vista;

import controlador.ControladorPuerta;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.Timer;
import modelo.ResultadoAcceso;
import util.Sonido;

/**
 * Ventana de la Puerta: recibe el código del lector USB (que escribe como un teclado) y valida el boleto.
 */
public class PanelPuerta extends javax.swing.JPanel {

    private ControladorPuerta controlador = new ControladorPuerta();

    // Después de unos segundos la pantalla vuelve a "ESPERANDO LECTURA"
    private Timer temporizador;

    public PanelPuerta() {
        initComponents();
        temporizador = new Timer(4000, e -> ponerEnEspera());
        temporizador.setRepeats(false);
        ponerEnEspera();
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
        txtCodigo = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        lblDetalle = new javax.swing.JLabel();

        lblTitulo.setText("PUERTA - Control de acceso");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));

        jLabel1.setText("Código del boleto (lo escribe el lector USB):");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14));

        txtCodigo.setText("");
        txtCodigo.setFont(new java.awt.Font("Monospaced", 1, 22));
        txtCodigo.addActionListener(this::txtCodigoActionPerformed);

        lblEstado.setText("ESPERANDO LECTURA");
        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 44));
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado.setForeground(new java.awt.Color(60, 60, 60));
        lblEstado.setBackground(new java.awt.Color(200, 200, 200));
        lblEstado.setOpaque(true);

        lblDetalle.setText("Acerque el boleto al lector");
        lblDetalle.setFont(new java.awt.Font("Segoe UI", 0, 18));
        lblDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(350, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(433, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(390, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(343, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(345, 345, 345)
                .addComponent(lblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // El lector envía el código y luego "Enter": este método se ejecuta solo
        String codigo = txtCodigo.getText().trim();
        txtCodigo.setText("");          // se limpia para la siguiente lectura
        if (codigo.isEmpty()) {
            return;
        }
        try {
            ResultadoAcceso r = controlador.validar(codigo);
            if (r.getTipo() == ResultadoAcceso.APROBADO) {
                mostrar(new Color(0, 160, 70), "APROBADO", r.getMensaje());
                Sonido.pitar(1);
            } else if (r.getTipo() == ResultadoAcceso.DUPLICADO) {
                mostrar(new Color(235, 140, 0), "BOLETO DUPLICADO", r.getMensaje());
                Sonido.pitar(3);
            } else {
                mostrar(new Color(200, 30, 30), "DENEGADO", r.getMensaje());
                Sonido.pitar(2);
            }
        } catch (SQLException ex) {
            mostrar(new Color(200, 30, 30), "ERROR", "Falla de base de datos: " + ex.getMessage());
        }
        enfocar();
    }//GEN-LAST:event_txtCodigoActionPerformed

    // Deja el cursor listo en la caja de texto para la siguiente lectura
    public void enfocar() {
        txtCodigo.requestFocusInWindow();
    }

    // Cambia el color y los textos de la pantalla grande
    private void mostrar(Color fondo, String estado, String detalle) {
        lblEstado.setBackground(fondo);
        lblEstado.setForeground(Color.WHITE);
        lblEstado.setText(estado);
        lblDetalle.setText(detalle);
        temporizador.restart();
    }

    private void ponerEnEspera() {
        lblEstado.setBackground(new Color(200, 200, 200));
        lblEstado.setForeground(new Color(60, 60, 60));
        lblEstado.setText("ESPERANDO LECTURA");
        lblDetalle.setText("Acerque el boleto al lector");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
