/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa3;

/**
 * Esta clase representa una jugada hecha por el jugador, con el número puesto en una fila y columna.
 * @author Daniel Granados Retana y Diego Granados Retana
 */
public class Jugada {
      private int fila;
      private int columna;
      private String numero;

    /**
     * Constructor
     * @param pFila
     * @param pColumna
     * @param pNumero
     */
    public Jugada(int pFila, int pColumna, String pNumero){
            fila = pFila;
            columna = pColumna;
            numero = pNumero;
      }

    /**
     * Obtiene la fila de la jugada realizada
     * @return fila
     */
    public int getFila() {
            return fila;
      }

    /**
     * Obtiene la columna de la jugada realizada
     * @return columna
     */
    public int getColumna() {
            return columna;
      }

    /**
     * Obtiene el numero de la jugada realizada
     * @return numero
     */
    public String getNumero() {
            return numero;
      }
      
      
}
