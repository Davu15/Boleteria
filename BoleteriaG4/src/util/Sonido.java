package util;

import java.awt.Toolkit;

/** Alertas sonoras de la puerta. */
public class Sonido {

    /** Hace sonar el "beep" del sistema la cantidad de veces indicada. */
    public static void pitar(int veces) {
        // Se hace en otro hilo para no congelar la pantalla mientras suena
        new Thread(() -> {
            for (int i = 0; i < veces; i++) {
                Toolkit.getDefaultToolkit().beep();
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();
    }
}
