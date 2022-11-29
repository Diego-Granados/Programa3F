/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa3.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
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
     * @return verdadero si se añadió y falso si no
     */
      public boolean añadirNumero(String numero, int fila, int columna){ // recibe las coordenadas de la casilla a donde se va a poner el número
            
            // si hoy errores, se ingresa la jugada en la pila
            jugadas.push(new Jugada(fila,columna,numero)); // mete la jugada a la pila de jugadas para luego hacer el undo si se requiere
            if (!numero.equals("Borrar")){  // se altera el texto de la casilla
                  casillas[fila][columna].setText(numero);  // se cambie el texto al número
            } else {
                  casillas[fila][columna].setText("");      // se borra 
            }
            casillas[fila][columna].setBackground(null);
            redoJugadas = new Stack<>();            // Se crea la pila para poner las jugadas por rehacer.
            return checkWin(); // se revisa si ya se terminó el juego
      }
      
    /**
     * Saca la última jugada
     */
      public boolean borrarJugada(){
            if (!jugadas.empty()){ // si la pila no está vacía
                  Jugada borrada = jugadas.pop(); // saca el último elemento
                  redoJugadas.push(borrada); // lo mete a la pila de rehacer jugada
                  casillas[borrada.getFila()][borrada.getColumna()].setText(""); // restaura la casilla
            } else {
                        return false ;
            }
            return true;
      }
      
      public javax.swing.JButton [][] getCasillas(){
            return casillas;
      }
      
    /**
     * Rehace una jugada
     */
    public boolean rehacerJugada(){
            if (!redoJugadas.empty()){ // si la pila no está vacía
                  Jugada restored = redoJugadas.pop(); // saca el último elemento
                  jugadas.push(restored); // se mete a la pila de jugadas
                  if (!restored.getNumero().equals("Borrar")){
                        casillas[restored.getFila()][restored.getColumna()].setText(restored.getNumero()); // restaura la casilla al valor que tenía antes
                  } else {
                         casillas[restored.getFila()][restored.getColumna()].setText(""); // restaura la casilla al valor que tenía antes
                  }
            } else {
                  return false;
            }
            return true;
      }
      
    /**
     * Revisa si se ganó la partida
     * @param window
     * @return verdadero si se ganó, falso si no
     */
    public boolean checkWin(){
           int limit = Configuracion.getTamaño();
            for (int i = 0; i < limit; i++){
                  for (int j = 0; j < limit; j++){
                        if (casillas[i][j].getText().equals("")){
                              return false;
                        }
                  }
            }
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
     
    public void saveMarcas(String nombre, int tiempo, String nivel){
          Marca marca = new Marca(nombre, tiempo, nivel);
                  int i = 0; // contador para iterar por la lista del nivel
                  for (; i < Marca.marcas.get(marca.getNivel()).size() ; i++){
                        if (Marca.marcas.get(marca.getNivel()).get(i).getTiempo() > marca.getTiempo()  && !(Marca.marcas.get(marca.getNivel()).contains(marca))){ // si la marca que se está revisando es mayor a la que se obtuvo en este juego
                              int index = Marca.marcas.get(marca.getNivel()).indexOf(Marca.marcas.get(marca.getNivel()).get(i)); // se añade el elemento nuevo en esa posición
                              Marca.marcas.get(marca.getNivel()).add(index, marca);
                        }
                  }
                  // si la marca no era menor que alguna en la lista, pero está todavía no está completa o está vacía
                  if (Marca.marcas.get(marca.getNivel()).isEmpty() ||  (i < 10 && !(Marca.marcas.get(marca.getNivel()).contains(marca)))){
                        Marca.marcas.get(marca.getNivel()).add(marca); // la añade al final
                  }
                  if (Marca.marcas.get(marca.getNivel()).size() > 10) { // si el tamaño de la lista 
                        Marca.marcas.get(marca.getNivel()).remove(10);
                  }
                  
                 FileWriter fw; // se crea un file writer para escribir en el archivo
                 try { // se usa un try porque puede darse un error
                       fw = new FileWriter("src\\main\\java\\poo\\programa3\\modelo\\futoshiki2022top10.dat");
                       String nivelM = "Fácil";
                       String str ;
                       for (int y = 0; y < 3; y++){ // se itera por cada nivel
                             for (i = 0; i < 10; i++){ // se itera por cada marca del nivel
                                   for (int x = 0; x < 3; x++){ // se escribe cada parte de la marca: el nombre, el tiempo y el nivel
                                         if (Marca.marcas.get(nivelM).size() <= i) { // si la lista de marcas es menor a 10, se llena los espacios con falta con N de nulo
                                               str = "N";
                                         } else{
                                               str = switch (x) { // se usa un switch para ver qué se tiene escribir.
                                                     case 0 -> Marca.marcas.get(nivelM).get(i).getNombre();
                                                     case 1 -> Integer.toString(Marca.marcas.get(nivelM).get(i).getTiempo());
                                                     default -> nivelM;
                                               };
                                         }
                                           for (int j = 0; j < str.length(); j++){ // se escriben todos los caracteres presentes en el string que se está escribiendo
                                                 fw.write(str.charAt(j));
                                           }
                                           if (x < 2) { // si el elemento a escribir no es el nivel, se pone un espacio después para separarlos
                                                 fw.write(" ");
                                           }
                                   }
                                   if (i != 9 || y != 2){ // si el elemento a escribir no es el último del nivel difícil, se pone un cambio de línea
                                          fw.write("\n");
                                   }
                              }
                             if (y == 0){ // se establece el nivel al siguiente
                                   nivelM = "Intermedio";
                             } else {
                                   nivelM = "Difícil";
                             }
                       }
                       fw.close(); // se cierra el archivo
                       
                 }
                 catch (IOException except){
                       
                 }
    }
}
