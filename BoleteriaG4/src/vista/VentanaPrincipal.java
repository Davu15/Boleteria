package vista;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import controlador.ControladorLogin;
import controlador.ControladorReportes;
import modelo.Sesion;

/**
 * Ventana principal (contenedora): tiene un menú y un escritorio (JDesktopPane)
 * donde se abren las ventanas internas (JInternalFrame).
 * El menú muestra solo lo que el perfil del usuario puede usar:
 *   ADMIN  -> todo
 *   CAJERO -> taquilla y sus ventas
 *   PUERTA -> control de acceso
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    // Ventanas internas abiertas (para no abrir la misma dos veces)
    private HashMap<String, JInternalFrame> abiertas = new HashMap<>();

    public VentanaPrincipal() {
        initComponents();
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        aplicarPerfil();
    }

    // Muestra el usuario activo y oculta los menús que no correspondan al perfil
    private void aplicarPerfil() {
        if (Sesion.actual != null) {
            lblEstado.setText("Usuario: " + Sesion.actual.getNombre() + "   |   Perfil: " + Sesion.actual.getPerfil());
        }
        menuEventos.setVisible(Sesion.esAdmin());
        menuVentas.setVisible(Sesion.esAdmin() || Sesion.esCajero());
        menuAcceso.setVisible(Sesion.esAdmin() || Sesion.esPuerta());
        menuAdmin.setVisible(Sesion.esAdmin());
    }

    /**
     * Este método lo genera el diseñador de NetBeans (pestaña Design).
     * NO lo edite a mano: mueva los componentes con el mouse.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        barra = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        menuSistema = new javax.swing.JMenu();
        itemCerrar = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();
        menuEventos = new javax.swing.JMenu();
        itemEventos = new javax.swing.JMenuItem();
        menuVentas = new javax.swing.JMenu();
        itemTaquilla = new javax.swing.JMenuItem();
        itemConsulta = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemReporte = new javax.swing.JMenuItem();
        menuAcceso = new javax.swing.JMenu();
        itemPuerta = new javax.swing.JMenuItem();
        menuAdmin = new javax.swing.JMenu();
        itemUsuarios = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Venta de Boletos y Control de Acceso");

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        barra.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));

        lblEstado.setText("Usuario:   |   Perfil:");

        javax.swing.GroupLayout barraLayout = new javax.swing.GroupLayout(barra);
        barra.setLayout(barraLayout);
        barraLayout.setHorizontalGroup(
            barraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraLayout.createSequentialGroup()
                .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE))
        );
        barraLayout.setVerticalGroup(
            barraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        menuSistema.setText("Sistema");

        itemCerrar.setText("Cerrar sesión");
        itemCerrar.addActionListener(this::itemCerrarActionPerformed);
        menuSistema.add(itemCerrar);

        itemSalir.setText("Salir");
        itemSalir.addActionListener(this::itemSalirActionPerformed);
        menuSistema.add(itemSalir);

        barraMenu.add(menuSistema);

        menuEventos.setText("Eventos");

        itemEventos.setText("Gestionar eventos");
        itemEventos.addActionListener(this::itemEventosActionPerformed);
        menuEventos.add(itemEventos);

        barraMenu.add(menuEventos);

        menuVentas.setText("Ventas");

        itemTaquilla.setText("Taquilla - Vender boletos");
        itemTaquilla.addActionListener(this::itemTaquillaActionPerformed);
        menuVentas.add(itemTaquilla);

        itemConsulta.setText("Consultar ventas");
        itemConsulta.addActionListener(this::itemConsultaActionPerformed);
        menuVentas.add(itemConsulta);
        menuVentas.add(jSeparator1);

        itemReporte.setText("Reporte de ventas (Jasper)");
        itemReporte.addActionListener(this::itemReporteActionPerformed);
        menuVentas.add(itemReporte);

        barraMenu.add(menuVentas);

        menuAcceso.setText("Acceso");

        itemPuerta.setText("Puerta - Control de acceso");
        itemPuerta.addActionListener(this::itemPuertaActionPerformed);
        menuAcceso.add(itemPuerta);

        barraMenu.add(menuAcceso);

        menuAdmin.setText("Administración");

        itemUsuarios.setText("Usuarios y perfiles");
        itemUsuarios.addActionListener(this::itemUsuariosActionPerformed);
        menuAdmin.add(itemUsuarios);

        barraMenu.add(menuAdmin);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
            .addComponent(barra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escritorio)
                .addGap(0, 0, 0)
                .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCerrarActionPerformed
        cerrarSesion();
    }//GEN-LAST:event_itemCerrarActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEventosActionPerformed
        abrir("Eventos", new PanelEventos());
    }//GEN-LAST:event_itemEventosActionPerformed

    private void itemTaquillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTaquillaActionPerformed
        abrir("Taquilla", new PanelTaquilla());
    }//GEN-LAST:event_itemTaquillaActionPerformed

    private void itemConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConsultaActionPerformed
        abrir("Ventas", new PanelVentas());
    }//GEN-LAST:event_itemConsultaActionPerformed

    private void itemReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReporteActionPerformed
        verReporte();
    }//GEN-LAST:event_itemReporteActionPerformed

    private void itemPuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPuertaActionPerformed
        abrirPuerta();
    }//GEN-LAST:event_itemPuertaActionPerformed

    private void itemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUsuariosActionPerformed
        abrir("Usuarios", new PanelUsuarios());
    }//GEN-LAST:event_itemUsuariosActionPerformed

    // ------------------------------------------------- ventanas internas
    /** Abre un panel dentro de una ventana interna. Si ya estaba abierta, solo la trae al frente. */
    private JInternalFrame abrir(String titulo, JPanel panel) {
        JInternalFrame existente = abiertas.get(titulo);
        if (existente != null) {
            traerAlFrente(existente);
            return existente;
        }

        // Parámetros: título, redimensionable, cerrable, maximizable, minimizable
        JInternalFrame marco = new JInternalFrame(titulo, true, true, true, true);
        marco.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        marco.setContentPane(panel);
        marco.pack();

        // Cada ventana nueva aparece un poco corrida (en cascada)
        int desplazamiento = 30 * abiertas.size();
        marco.setLocation(desplazamiento, desplazamiento);

        // Al cerrarla se quita de la lista de abiertas
        marco.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                abiertas.remove(titulo);
            }
        });

        escritorio.add(marco);
        marco.setVisible(true);
        abiertas.put(titulo, marco);
        traerAlFrente(marco);
        return marco;
    }

    // La puerta necesita que el cursor quede en la caja del lector cada vez que se activa
    private void abrirPuerta() {
        boolean yaAbierta = abiertas.containsKey("Puerta");
        PanelPuerta panel = new PanelPuerta();
        JInternalFrame marco = yaAbierta ? abiertas.get("Puerta") : abrir("Puerta", panel);
        if (!yaAbierta) {
            marco.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameActivated(InternalFrameEvent e) {
                    panel.enfocar();
                }
            });
            panel.enfocar();
        }
        traerAlFrente(marco);
    }

    private void traerAlFrente(JInternalFrame marco) {
        marco.toFront();
        try {
            marco.setSelected(true);
            if (marco.isIcon()) {
                marco.setIcon(false);
            }
        } catch (PropertyVetoException ex) {
            // no pasa nada si no se puede seleccionar
        }
    }

    // ------------------------------------------------------- otras acciones
    private void verReporte() {
        try {
            new ControladorReportes().verReporteVentas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el reporte:\n" + ex.getMessage());
        }
    }

    private void cerrarSesion() {
        new ControladorLogin().cerrarSesion();
        dispose();
        new VentanaLogin().setVisible(true);
    }

    /**
     * Permite ejecutar este archivo directamente (Run File).
     * La ventana principal necesita un usuario con sesión iniciada,
     * por eso el programa siempre empieza en el login.
     */
    public static void main(String[] args) {
        VentanaLogin.main(args);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barra;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuItem itemCerrar;
    private javax.swing.JMenuItem itemConsulta;
    private javax.swing.JMenuItem itemEventos;
    private javax.swing.JMenuItem itemPuerta;
    private javax.swing.JMenuItem itemReporte;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem itemTaquilla;
    private javax.swing.JMenuItem itemUsuarios;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JMenu menuAcceso;
    private javax.swing.JMenu menuAdmin;
    private javax.swing.JMenu menuEventos;
    private javax.swing.JMenu menuSistema;
    private javax.swing.JMenu menuVentas;
    // End of variables declaration//GEN-END:variables
}
