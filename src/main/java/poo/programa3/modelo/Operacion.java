/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa3.modelo;

/**
 * Esta clase tiene como objetivo representar las operaciones de una partida, al contener el caracter de la operación y 
 * la fila y la columna.
 * @author Daniel Granados Retana, carné 2022104692 <dagranados@astudiantec.cr>
 */
public class Operacion {
    private int indiceFila;
    private int indiceColumna;
    private char tipo; // no es necesariamente el tipo de desigualdad, puede ser la constante

    /**
     * Constructor
     * @param indiceFila
     * @param indiceColumna
     * @param tipo 
     */
    public Operacion(char tipo, int indiceFila, int indiceColumna) {
        this.indiceFila = indiceFila;
        this.indiceColumna = indiceColumna;
        this.tipo = tipo;
    }

    /**
     * Obtiene el índice de la fila
     * @return indiceFila
     */
    public int getIndiceFila() {
        return indiceFila;
    }

    /**
     * Establece el índice de fila
     * @param indiceFila
     */
    public void setIndiceFila(int indiceFila) {
        this.indiceFila = indiceFila;
    }

    /**
     * Obtiene el índice de la columna
     * @return indiceFila
     */
    public int getIndiceColumna() {
        return indiceColumna;
    }

    /**
     * Establece el índice de columna
     * @param indiceColumna
     */
    public void setIndiceColumna(int indiceColumna) {
        this.indiceColumna = indiceColumna;
    }

    /**
     * Obtiene el tipo de la operación.
     * @return tipo
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de la operación.
     * @param tipo
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
