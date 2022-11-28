/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa3;

import java.awt.Color;
import java.awt.Component;
import java.util.*;
import javax.swing.JOptionPane;
import poo.programa3.GUI.JugarFrame;
/**
 * Esta clase se usa para representar un juego de futoshiki.
 * @author Daniel Granados Retana y Diego Granados Retana
 */
public class Juego {
      private javax.swing.JButton [][] casillas; // almacena los botones de las casillas del tablero 
      private Partida partida; // almacena la partida.
      private Stack<Jugada> jugadas; // En esta pila se almacenan las jugadas realizadas
      private Stack<Jugada> redoJugadas; // en esta pila se almacenan las jugadas borradas
      
    /**
     * Constructor de la clase
     * @param pPartida
     * @param nombre
     * @param pCasillas
     */
      public Juego(Partida pPartida, String nombre, javax.swing.JButton [][] pCasillas){
            casillas = pCasillas;         
            jugadas = new Stack<>();
            partida = pPartida;
      }

    /**
     * Esta función añade un número al tablero. Recibe el número, la fila y columna del botón y la ventana del juego.
     * @param numero
     * @param fila
     * @param columna
     * @param window
     * @return verdadero si se añadió y falso si no
     */
      public boolean añadirNumero(String numero, int fila, int columna,Component window){ // recibe las coordenadas de la casilla a donde se va a poner el número
            // Se validan las restricciones
            for (javax.swing.JButton buttonIter : casillas[fila]){
                  if (buttonIter.getText().equals(numero)){
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              JugarFrame.stopwatch.stop();
                        }
                        
                        buttonIter.setBackground(Color.red);
                        JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE EL ELEMENTO YA ESTÁ EN LA FILA.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        buttonIter.setBackground(null);
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              JugarFrame.stopwatch.start();
                        }
                        return false;
                 }
            }
            for (int i = 0; i < Configuracion.getTamaño(); i++){
                  if (casillas[i][columna].getText().equals(numero)){
                        casillas[i][columna].setBackground(Color.red);
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              JugarFrame.stopwatch.stop();
                        }
                        JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE EL ELEMENTO YA ESTÁ EN LA COLUMNA.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        casillas[i][columna].setBackground(null);
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              JugarFrame.stopwatch.start();
                        }
                        return false;
                  }
            }
            for (Operacion constante : partida.getConstantes()){
                  if (constante.getIndiceFila() == fila && constante.getIndiceColumna() == columna){
                        casillas[fila][columna].setBackground(Color.red);
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              JugarFrame.stopwatch.stop();
                        }
                        JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE ESTE ES UN DÍGITO FIJO.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              JugarFrame.stopwatch.start();
                        }
                        casillas[fila][columna].setBackground(null);
                        return false;
                  }
            }
            int i = 0;
            OUTER:
            for (; i < partida.getOperaciones().size(); i ++) {
                  int colCompare;
                  int colCompare2;
                  switch(partida.getOperaciones().get(i).getTipo()){
                        case 'a':
                              if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila){
                                    try {
                                          colCompare = Integer.valueOf(numero);
                                          colCompare2 = Integer.valueOf(casillas[fila][columna+1].getText());
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else if (partida.getOperaciones().get(i).getIndiceColumna() == columna - 1 && partida.getOperaciones().get(i).getIndiceFila() == fila) {
                                    try {
                                          colCompare = Integer.valueOf(casillas[fila][columna-1].getText());
                                          colCompare2 = Integer.valueOf(numero);
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else {
                                    continue;
                              }
                              if (!(colCompare > colCompare2)) {
                                    casillas[fila][columna].setBackground(Color.red);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          JugarFrame.stopwatch.stop();
                                    }
                                    JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MAYOR.", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          JugarFrame.stopwatch.start();
                                    }
                                    casillas[fila][columna].setBackground(null);
                                    return false;
                              } else {
                                    continue;
                              }
                     case 'b':
                              if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila){
                                    try {
                                          colCompare = Integer.valueOf(numero);
                                          colCompare2 = Integer.valueOf(casillas[fila][columna+1].getText());
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else if (partida.getOperaciones().get(i).getIndiceColumna() == columna - 1 && partida.getOperaciones().get(i).getIndiceFila() == fila) {
                                    try {
                                          colCompare = Integer.valueOf(casillas[fila][columna-1].getText());
                                          colCompare2 = Integer.valueOf(numero);
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else {
                                    continue;
                              }
                              if (!(colCompare < colCompare2)) {
                                    casillas[fila][columna].setBackground(Color.red);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          JugarFrame.stopwatch.stop();
                                    }
                                    JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MENOR.", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          JugarFrame.stopwatch.start();
                                    }
                                    casillas[fila][columna].setBackground(null);
                                    return false;
                              } else {
                                    continue;
                              }
                    case 'y':
                              if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila){
                                    try {
                                          colCompare = Integer.valueOf(numero);
                                          colCompare2 = Integer.valueOf(casillas[fila+1][columna].getText());
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila -1) {
                                    try {
                                          colCompare = Integer.valueOf(casillas[fila-1][columna].getText());
                                          colCompare2 = Integer.valueOf(numero);
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else {
                                    continue;
                              }
                              if (!(colCompare > colCompare2)) {
                                    casillas[fila][columna].setBackground(Color.red);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          JugarFrame.stopwatch.stop();
                                    }
                                    JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MAYOR.", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          JugarFrame.stopwatch.start();
                                    }
                                    casillas[fila][columna].setBackground(null);
                                    return false;
                              } else {
                                    continue;
                              }
                      case 'z':
                              if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila){
                                    try {
                                          colCompare = Integer.valueOf(numero);
                                          colCompare2 = Integer.valueOf(casillas[fila+1][columna].getText());
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila -1) {
                                    try {
                                          colCompare = Integer.valueOf(casillas[fila-1][columna].getText());
                                          colCompare2 = Integer.valueOf(numero);
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else {
                                    continue;
                              }
                              if (!(colCompare < colCompare2)) {
                                    casillas[fila][columna].setBackground(Color.red);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          JugarFrame.stopwatch.stop();
                                    }
                                     JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MENOR.", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          JugarFrame.stopwatch.start();
                                    }
                                    casillas[fila][columna].setBackground(null);
                                    return false;
                              } else {
                                    continue;
                              }                       
                  }
            }
            
            // si hoy errores, se ingresa la jugada en la pila
            jugadas.push(new Jugada(fila,columna,numero)); // mete la jugada a la pila de jugadas para luego hacer el undo si se requiere
            if (!numero.equals("Borrar")){  // se altera el texto de la casilla
                  casillas[fila][columna].setText(numero);  // se cambie el texto al número
            } else {
                  casillas[fila][columna].setText("");      // se borra 
            }
            casillas[fila][columna].setBackground(null);
            redoJugadas = new Stack<>();            // Se crea la pila para poner las jugadas por rehacer.
            return checkWin(window); // se revisa si ya se terminó el juego
      }
      
    /**
     * Saca la última jugada
     * @param window
     */
      public void borrarJugada(Component window){
            if (!jugadas.empty()){ // si la pila no está vacía
                  Jugada borrada = jugadas.pop(); // saca el último elemento
                  redoJugadas.push(borrada); // lo mete a la pila de rehacer jugada
                  casillas[borrada.getFila()][borrada.getColumna()].setText(""); // restaura la casilla
            } else {
                  JOptionPane.showMessageDialog(window, "YA NO HAY MÁS JUGADAS POR BORRAR.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
            }
      }
      
      public javax.swing.JButton [][] getCasillas(){
            return casillas;
      }
      
    /**
     * Rehace una jugada
     * @param window
     */
    public void rehacerJugada(Component window){
            if (!redoJugadas.empty()){ // si la pila no está vacía
                  Jugada restored = redoJugadas.pop(); // saca el último elemento
                  jugadas.push(restored); // se mete a la pila de jugadas
                  if (!restored.getNumero().equals("Borrar")){
                        casillas[restored.getFila()][restored.getColumna()].setText(restored.getNumero()); // restaura la casilla al valor que tenía antes
                  } else {
                         casillas[restored.getFila()][restored.getColumna()].setText(""); // restaura la casilla al valor que tenía antes
                  }
            } else {
                  JOptionPane.showMessageDialog(window, "YA NO HAY MÁS JUGADAS POR REHACER.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
            }
      }
      
    /**
     * Revisa si se ganó la partida
     * @param window
     * @return verdadero si se ganó, falso si no
     */
    public boolean checkWin(Component window){
           int limit = Configuracion.getTamaño();
            for (int i = 0; i < limit; i++){
                  for (int j = 0; j < limit; j++){
                        if (casillas[i][j].getText().equals("")){
                              return false;
                        }
                  }
            }
            JOptionPane.showMessageDialog(window, "¡EXCELENTE! JUEGO TERMINADO CON ÉXITO");  
            return true;
      }
      
    /**
     * Obtiene la pila de jugadas
     * @return pila de jugadas
     */
    public Stack<Jugada> getJugadas() {
        return jugadas;
    }

    /**
     * Estable la pila de jugadas
     * @param jugadas
     */
    public void setJugadas(Stack<Jugada> jugadas) {
        this.jugadas = jugadas;
    }

    /**
     * Obtiene la pila de jugadas por rehacer
     * @return pila de redoJugadas
     */
    public Stack<Jugada> getRedoJugadas() {
        return redoJugadas;
    }

    /** 
     * Establece la pila de jugadas a rehacer
     * @param redoJugadas
     */
    public void setRedoJugadas(Stack<Jugada> redoJugadas) {
        this.redoJugadas = redoJugadas;
    }
      
}
