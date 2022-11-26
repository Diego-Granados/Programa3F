/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa3;

/**
 * Clase para guardar los parámetros de la configuración.
 * No se deberían hacer instancias de esta clase.
 * @author Daniel Granados Retana y Diego Granados Retana
 */
public class Configuracion {
    private static String nivel = "Fácil";
    private static String reloj = "Sí";
    private static boolean lado = true; // true es derecha e izquierda es false
    private static int tamaño = 5;

    /**
     * Retorna el nivel
     * @return nivel
     */
    public static String getNivel() {
        return nivel;
    }

    /**
     * Establece el nivel
     * @param nivel
     */
    public static void setNivel(String nivel) {
        Configuracion.nivel = nivel;
    }

    /**
     * Obtiene el reloj
     * @return reloj
     */
    public static String getReloj() {
        return reloj;
    }
    
    public static int getTamaño(){
          return tamaño;
    }
    
    public static void setTamaño(int pTamaño){
          tamaño = pTamaño;
    }

    /**
     * Establece el reloj
     * @param reloj
     */
    public static void setReloj(String reloj) {
        Configuracion.reloj = reloj;
    }

    /**
     * Obtiene el lado
     * @return lado
     */
    public static boolean getLado() {
        return lado;
    }

    /**
     * Establece el lado
     * @param lado
     */
    public static void setLado(boolean lado) {
        Configuracion.lado = lado;
    }
}
