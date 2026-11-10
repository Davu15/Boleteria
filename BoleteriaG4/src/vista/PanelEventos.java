package vista;

import controlador.ControladorEventos;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import modelo.Evento;

/**
 * Ventana de gestión de eventos: agregar, modificar, eliminar y actualizar.
 */
public class PanelEventos extends javax.swing.JPanel {

    private ControladorEventos controlador = new ControladorEventos();

    // Eventos mostrados en la tabla (la fila 0 de la tabla es el evento 0 de esta lista)
    private ArrayList<Evento> eventos = new ArrayList<>();

    // Evento elegido en la tabla (null = no hay ninguno, se está creando uno nuevo)
    private Evento seleccionado = null;

    public PanelEventos() {
        initComponents();

        // Cada campo usa el componente que corresponde a su tipo de dato:
        spnFecha.setModel(new SpinnerDateModel());                          // fecha
        spnFecha.setEditor(new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy"));
        spnPrecio.setModel(new SpinnerNumberModel(10.0, 0.0, 10000.0, 0.5)); // decimal
        spnAforo.setModel(new SpinnerNumberModel(100, 1, 100000, 10));       // entero
        txaDescripcion.setLineWrap(true);                                    // texto largo
        txaDescripcion.setWrapStyleWord(true);

        tblEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEventos.getColumnModel().getColumn(0).setPreferredWidth(40);
        // Al hacer clic en una fila, sus datos pasan al formulario
        tblEventos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarFilaEnFormulario();
            }
        });

        cargarTabla();
        limpiar();
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
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        spnFecha = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        spnPrecio = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        spnAforo = new javax.swing.JSpinner();
        chkActivo = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaDescripcion = new javax.swing.JTextArea();
        btnLimpiar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEventos = new javax.swing.JTable();

        lblTitulo.setText("EVENTOS - Gestión de eventos");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));

        jLabel1.setText("Nombre:");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13));

        txtNombre.setText("");

        jLabel2.setText("Categoría:");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13));

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Concierto", "Teatro", "Deportivo", "Conferencia", "Otro" }));

        jLabel3.setText("Fecha:");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13));


        jLabel4.setText("Precio ($):");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13));


        jLabel5.setText("Aforo:");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13));


        chkActivo.setText("Evento activo (se puede vender)");
        chkActivo.setSelected(true);

        jLabel6.setText("Descripción:");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13));

        txaDescripcion.setColumns(20);
        txaDescripcion.setRows(5);
        jScrollPane2.setViewportView(txaDescripcion);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(this::btnModificarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnActualizar.setText("Actualizar lista");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        tblEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Categoría", "Fecha", "Precio", "Aforo", "Activo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblEventos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(330, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(730, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(470, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(730, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(470, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(730, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(spnFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(570, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(730, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(spnPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(570, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(730, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(spnAforo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(570, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(460, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(430, 430, 430)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(330, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(430, 430, 430)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(730, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(610, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(490, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(370, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(500, 500, 500)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(523, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(475, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(474, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(435, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(434, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(395, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(spnFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(394, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(355, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(spnPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(354, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(315, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(spnAforo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(277, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(475, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(312, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(360, 360, 360)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Evento ev = leerFormulario();
        if (ev == null) {
            return;
        }
        try {
            controlador.agregar(ev);
            JOptionPane.showMessageDialog(this, "Evento agregado.");
            cargarTabla();
            limpiar();
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Ya existe un evento con ese nombre en esa fecha.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo agregar:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Primero seleccione un evento de la tabla.");
            return;
        }
        Evento ev = leerFormulario();
        if (ev == null) {
            return;
        }
        ev.setIdEvento(seleccionado.getIdEvento());
        try {
            controlador.modificar(ev);
            JOptionPane.showMessageDialog(this, "Evento modificado.");
            cargarTabla();
            limpiar();
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Ya existe un evento con ese nombre en esa fecha.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo modificar:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Primero seleccione un evento de la tabla.");
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Eliminar el evento \"" + seleccionado.getNombre() + "\"?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            controlador.eliminar(seleccionado.getIdEvento());
            cargarTabla();
            limpiar();
        } catch (SQLIntegrityConstraintViolationException ex) {
            // La base no deja borrar un evento que ya tiene boletas vendidas
            JOptionPane.showMessageDialog(this, "No se puede eliminar: el evento ya tiene boletas vendidas.\n"
                    + "Desmarque \"Evento activo\" y use Modificar para ocultarlo de la taquilla.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        cargarTabla();
    }//GEN-LAST:event_btnActualizarActionPerformed

    // Vuelve a leer los eventos de la base de datos y llena la tabla
    public void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tblEventos.getModel();
        modelo.setRowCount(0);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            eventos = controlador.listar();
            for (Evento ev : eventos) {
                modelo.addRow(new Object[]{
                    ev.getIdEvento(), ev.getNombre(), ev.getCategoria(), formato.format(ev.getFecha()),
                    String.format("$ %.2f", ev.getPrecio()), ev.getAforo(), ev.isActivo() ? "Sí" : "No"
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudieron leer los eventos:\n" + ex.getMessage());
        }
    }

    // Deja el formulario vacío para crear un evento nuevo
    private void limpiar() {
        seleccionado = null;
        tblEventos.clearSelection();
        txtNombre.setText("");
        cmbCategoria.setSelectedIndex(0);
        spnFecha.setValue(new Date());
        spnPrecio.setValue(10.0);
        spnAforo.setValue(100);
        chkActivo.setSelected(true);
        txaDescripcion.setText("");
        txtNombre.requestFocusInWindow();
    }

    // Pasa al formulario los datos de la fila elegida
    private void cargarFilaEnFormulario() {
        int fila = tblEventos.getSelectedRow();
        if (fila == -1) {
            return;
        }
        seleccionado = eventos.get(fila);
        txtNombre.setText(seleccionado.getNombre());
        cmbCategoria.setSelectedItem(seleccionado.getCategoria());
        spnFecha.setValue(seleccionado.getFecha());
        spnPrecio.setValue(seleccionado.getPrecio());
        spnAforo.setValue(seleccionado.getAforo());
        chkActivo.setSelected(seleccionado.isActivo());
        txaDescripcion.setText(seleccionado.getDescripcion());
    }

    // Convierte lo escrito en el formulario en un objeto Evento (null si hay un error)
    private Evento leerFormulario() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba el nombre del evento.");
            txtNombre.requestFocusInWindow();
            return null;
        }
        String descripcion = txaDescripcion.getText().trim();
        if (descripcion.length() > 255) {
            JOptionPane.showMessageDialog(this, "La descripción no puede pasar de 255 letras.");
            return null;
        }
        Evento ev = new Evento();
        ev.setNombre(nombre);
        ev.setCategoria(cmbCategoria.getSelectedItem().toString());
        ev.setFecha(new java.sql.Date(((Date) spnFecha.getValue()).getTime()));
        ev.setPrecio(((Number) spnPrecio.getValue()).doubleValue());
        ev.setAforo(((Number) spnAforo.getValue()).intValue());
        ev.setActivo(chkActivo.isSelected());
        ev.setDescripcion(descripcion);
        return ev;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JSpinner spnAforo;
    private javax.swing.JSpinner spnFecha;
    private javax.swing.JSpinner spnPrecio;
    private javax.swing.JTable tblEventos;
    private javax.swing.JTextArea txaDescripcion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
