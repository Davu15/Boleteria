/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jean
 */

/**
 * Dibuja un código de barras Code 39 usando solo Graphics2D (sin librerías externas).
 *
 * Cada letra se dibuja con 9 elementos: barra, espacio, barra, espacio...
 * En el patrón, 'n' = elemento angosto y 'w' = elemento ancho (3 de los 9 son anchos).
 */
public class CodigoBarras39 {
    
    // Caracteres permitidos en Code 39 (el * es el inicio y el fin)
    private static final String CARACTERES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%*";

    // Patrón de cada carácter, en el MISMO orden que CARACTERES
    private static final String[] PATRONES = {
        "nnnwwnwnn", "wnnwnnnnw", "nnwwnnnnw", "wnwwnnnnn", "nnnwwnnnw",   // 0 1 2 3 4
        "wnnwwnnnn", "nnwwwnnnn", "nnnwnnwnw", "wnnwnnwnn", "nnwwnnwnn",   // 5 6 7 8 9
        "wnnnnwnnw", "nnwnnwnnw", "wnwnnwnnn", "nnnnwwnnw", "wnnnwwnnn",   // A B C D E
        "nnwnwwnnn", "nnnnnwwnw", "wnnnnwwnn", "nnwnnwwnn", "nnnnwwwnn",   // F G H I J
        "wnnnnnnww", "nnwnnnnww", "wnwnnnnwn", "nnnnwnnww", "wnnnwnnwn",   // K L M N O
        "nnwnwnnwn", "nnnnnnwww", "wnnnnnwwn", "nnwnnnwwn", "nnnnwnwwn",   // P Q R S T
        "wwnnnnnnw", "nwwnnnnnw", "wwwnnnnnn", "nwnnwnnnw", "wwnnwnnnn",   // U V W X Y
        "nwwnwnnnn",                                                       // Z
        "nwnnnnwnw", "wwnnnnwnn", "nwwnnnwnn", "nwnwnwnnn",                // - . espacio $
        "nwnwnnnwn", "nwnnnwnwn", "nnnwnwnwn", "nwnnwnwnn"                 // / + % *
    };

    private static final int FINO = 2;          // ancho de una barra angosta (pixeles)
    private static final int ANCHO = 5;         // ancho de una barra ancha (pixeles)
    private static final int MARGEN = 20;       // espacio en blanco a los lados
    private static final int ALTO_BARRAS = 70;
    private static final int ALTO_TEXTO = 25;

    /** Crea la imagen del código de barras para el texto indicado. */
    public static BufferedImage crear(String texto) {
        String completo = "*" + texto.toUpperCase() + "*";   // se agregan inicio y fin

        // 1) Calcular el ancho total de la imagen
        int anchoTotal = MARGEN * 2;
        for (int i = 0; i < completo.length(); i++) {
            String patron = patronDe(completo.charAt(i));
            for (int j = 0; j < patron.length(); j++) {
                anchoTotal += (patron.charAt(j) == 'w') ? ANCHO : FINO;
            }
            anchoTotal += FINO;   // espacio entre letras
        }
        int alto = ALTO_BARRAS + ALTO_TEXTO + 20;

        // 2) Crear la imagen en memoria y pintar el fondo blanco
        BufferedImage imagen = new BufferedImage(anchoTotal, alto, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = imagen.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, anchoTotal, alto);

        // 3) Dibujar las barras
        g.setColor(Color.BLACK);
        int x = MARGEN;
        for (int i = 0; i < completo.length(); i++) {
            String patron = patronDe(completo.charAt(i));
            for (int j = 0; j < patron.length(); j++) {
                int grosor = (patron.charAt(j) == 'w') ? ANCHO : FINO;
                if (j % 2 == 0) {                       // posiciones pares = barras negras
                    g.fillRect(x, 10, grosor, ALTO_BARRAS);
                }
                x += grosor;                            // posiciones impares = espacios
            }
            x += FINO;
        }

        // 4) Escribir el texto debajo, centrado
        g.setFont(new Font("Monospaced", Font.BOLD, 14));
        FontMetrics medidas = g.getFontMetrics();
        int xTexto = (anchoTotal - medidas.stringWidth(texto)) / 2;
        g.drawString(texto, xTexto, 10 + ALTO_BARRAS + 18);

        g.dispose();
        return imagen;
    }

    // Busca el patrón de un carácter; si no existe en Code 39 lanza error
    private static String patronDe(char c) {
        int posicion = CARACTERES.indexOf(c);
        if (posicion < 0) {
            throw new IllegalArgumentException("Carácter no válido para Code 39: " + c);
        }
        return PATRONES[posicion];
    }
}
