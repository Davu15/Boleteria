package controlador;

import conexion.Conexion;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import modelo.Boleta;
import modelo.Evento;
import modelo.Sesion;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/** Abre los reportes de JasperReports (archivos .jrxml de la carpeta reportes). */
public class ControladorReportes {

    /** Reporte de ventas agrupadas por evento (usa la base de datos). */
    public void verReporteVentas() throws Exception {
        JasperReport reporte = cargar("ReporteVentas.jrxml");

        try (Connection cn = Conexion.conectar()) {
            // El administrador ve todo; los demás solo sus propias ventas
            Map<String, Object> parametros = new HashMap<>();
            if (Sesion.esAdmin()) {
                parametros.put("ID_USUARIO", 0);
                parametros.put("FILTRO", "Todos los usuarios");
            } else {
                parametros.put("ID_USUARIO", Sesion.idActual());
                parametros.put("FILTRO", Sesion.actual.getNombre());
            }
            JasperPrint impresion = JasperFillManager.fillReport(reporte, parametros, cn);
            JasperViewer.viewReport(impresion, false);
        }
    }

    /** Boleto listo para imprimir, con su código de barras. */
    public void verBoleto(Evento ev, Boleta b, BufferedImage barras) throws Exception {
        JasperReport reporte = cargar("Boleto.jrxml");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("EVENTO", ev.getNombre());
        parametros.put("FECHA", new SimpleDateFormat("dd/MM/yyyy").format(ev.getFecha()));
        parametros.put("PRECIO", String.format("$ %.2f", ev.getPrecio()));
        parametros.put("CODIGO", b.getCodigoUnico());
        parametros.put("BARRAS", barras);

        // Este reporte no usa base de datos: se llena con un origen de datos vacío
        JasperPrint impresion = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource(1));
        JasperViewer.viewReport(impresion, false);
    }

    // Busca el .jrxml y lo convierte en un reporte que Jasper puede ejecutar
    private JasperReport cargar(String nombre) throws Exception {
        // Plan A: dentro del proyecto compilado (carpeta reportes del classpath)
        InputStream archivo = getClass().getResourceAsStream("/reportes/" + nombre);

        // Plan B: directo desde la carpeta del proyecto (por si NetBeans no copió el archivo)
        if (archivo == null) {
            File[] posibles = {new File("src/reportes/" + nombre), new File("reportes/" + nombre)};
            for (File f : posibles) {
                if (f.exists()) {
                    archivo = new FileInputStream(f);
                    break;
                }
            }
        }

        if (archivo == null) {
            throw new FileNotFoundException("No se encontró el reporte " + nombre
                    + ".\nHaga Clean and Build (Shift+F11) y vuelva a ejecutar.");
        }
        try (InputStream entrada = archivo) {
            return JasperCompileManager.compileReport(entrada);
        }
    }
}
