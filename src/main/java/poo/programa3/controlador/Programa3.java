/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package poo.programa3.controlador;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import poo.programa3.modelo.*;
import poo.programa3.vista.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

   
/**
 * Programa 2 de POO. Futushiki
 * @author Daniel Granados Retana, carné 2022104692, y Diego Granados Retana, carné 2022158363
 */
public class Programa3 {
      
      private String nivelConfig = "Fácil";
    private String reloj = "Sí";
    private boolean lado = true;
    private HashMap<String, javax.swing.JRadioButton> niveles = new HashMap<>();
    private HashMap<String, javax.swing.JRadioButton> relojes = new HashMap<>();
    private HashMap<Boolean, javax.swing.JRadioButton> lados = new HashMap<>();
    private HashMap<Integer, javax.swing.JRadioButton> tamaños = new HashMap<>();
    
      private JugarFrame jugarFrame = new JugarFrame();
      private ConfiguracionGUI configGUI = new ConfiguracionGUI();
      private Posibles posiblesFrame = new Posibles();
      private AcercaDe acercaFrame = new AcercaDe();
      private Top10Frame top10 = new Top10Frame();
      
      private boolean started = false; // indica si ya empezó el juego.
    boolean win = false; // indica si ya se ganó el juego
    private static Juego juego; // se usa para guardar el juego
    private Partida partida; // se usa para establecer la partida.
    
    private ArrayList<javax.swing.JLabel> labelsHorizontales5 = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> labelsVerticales5 = new ArrayList<>(); 
    private javax.swing.JButton [][] casillas5; // contienen todos los botones del tablero.
    private ArrayList<javax.swing.JLabel> nonLabelsHorizontales5 = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> nonLabelsVerticales5 = new ArrayList<>(); 
    private ArrayList<javax.swing.JButton> nonCasillas5 = new ArrayList<>(); // contienen todos los botones que no son del tablero.
    
    private ArrayList<javax.swing.JLabel> labelsHorizontales6 = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> labelsVerticales6 = new ArrayList<>(); 
    private javax.swing.JButton [][] casillas6; // contienen todos los botones del tablero.
    private ArrayList<javax.swing.JLabel> nonLabelsHorizontales6 = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> nonLabelsVerticales6 = new ArrayList<>(); 
    private ArrayList<javax.swing.JButton> nonCasillas6 = new ArrayList<>(); // contienen todos los botones que no son del tablero.
    
    private ArrayList<javax.swing.JLabel> labelsHorizontales7 = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> labelsVerticales7 = new ArrayList<>(); 
    private javax.swing.JButton [][] casillas7; // contienen todos los botones del tablero.
    private ArrayList<javax.swing.JLabel> nonLabelsHorizontales7 = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> nonLabelsVerticales7 = new ArrayList<>(); 
    private ArrayList<javax.swing.JButton> nonCasillas7 = new ArrayList<>(); // contienen todos los botones que no son del tablero.
    
    private ArrayList<javax.swing.JLabel> labelsHorizontales8 = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> labelsVerticales8 = new ArrayList<>(); 
    private javax.swing.JButton [][] casillas8; // contienen todos los botones del tablero.
    private ArrayList<javax.swing.JLabel> nonLabelsHorizontales8 = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> nonLabelsVerticales8 = new ArrayList<>(); 
    private ArrayList<javax.swing.JButton> nonCasillas8 = new ArrayList<>(); // contienen todos los botones que no son del tablero.
    
    private ArrayList<javax.swing.JLabel> labelsHorizontales9 = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> labelsVerticales9 = new ArrayList<>(); 
    private javax.swing.JButton [][] casillas9; // contienen todos los botones del tablero.
    private ArrayList<javax.swing.JLabel> nonLabelsHorizontales9 = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> nonLabelsVerticales9 = new ArrayList<>(); 
    private ArrayList<javax.swing.JButton> nonCasillas9 = new ArrayList<>(); // contienen todos los botones que no son del tablero.
    
    private HashMap<Integer, javax.swing.JButton [][]> casillasPorTablero = new HashMap<>();
    private HashMap<Integer, ArrayList<javax.swing.JButton>> nonCasillasPorTablero = new HashMap<>();
    private HashMap<Integer, ArrayList<javax.swing.JLabel>> labelsHorizontalesPorTablero = new HashMap<>();
    private HashMap<Integer, ArrayList<javax.swing.JLabel>> nonLabelsHorizontalesPorTablero = new HashMap<>();
    private HashMap<Integer, ArrayList<javax.swing.JLabel>> labelsVerticalesPorTablero = new HashMap<>();
    private HashMap<Integer, ArrayList<javax.swing.JLabel>> nonLabelsVerticalesPorTablero = new HashMap<>();
    
    private int index; // indice de la partida.
    private String horassave; // se usa para salvar el tiempo del timer antes de empezar el juego.
    private String minutossave;
    private String segundossave;
    private String nivel;
    private int nivelCont;
    private int horasMulti;
    private int minutosMulti;
    private int  segundosMulti;
    /**
     * Objeto para controlar el tiempo
     */
    public static Timer stopwatch;

    int count = 0; // se usa para el contador del timer
    int delay = 1000; // este delay se usa para que el timer cambie cada segundo.
      
   private int calcularFila(int  pFila){
         if (Configuracion.getTamaño() == 5){
               pFila -= 2;
         } else if (Configuracion.getTamaño() <= 8){
               pFila -= 1;
         }
         return pFila;
   }
   
   private int calcularColumna(int  pColumna){
         if (Configuracion.getTamaño() <= 6){
               pColumna -= 2;
         } else if (Configuracion.getTamaño() <= 8){
               pColumna -= 1;
         }
         return pColumna;
   }
   
   public void putNumber(int fila, int columna){
            if (!started) {
                  return;             
            }
            fila = calcularFila(fila);
            columna = calcularColumna(columna);
            String numero = getSelectedButtonText();
            javax.swing.JButton [][] casillas = juego.getCasillas();
            for (javax.swing.JButton buttonIter : casillas[fila]){
                  if (buttonIter.getText().equals(numero)){
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              stopwatch.stop();
                        }
                        
                        buttonIter.setBackground(Color.red);
                        JOptionPane.showMessageDialog(jugarFrame, "JUGADA NO ES VÁLIDA PORQUE EL ELEMENTO YA ESTÁ EN LA FILA.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        buttonIter.setBackground(null);
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              stopwatch.start();
                        }
                        return;
                 }
            }
            for (int i = 0; i < Configuracion.getTamaño(); i++){
                  if (casillas[i][columna].getText().equals(numero)){
                        casillas[i][columna].setBackground(Color.red);
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              stopwatch.stop();
                        }
                        JOptionPane.showMessageDialog(jugarFrame, "JUGADA NO ES VÁLIDA PORQUE EL ELEMENTO YA ESTÁ EN LA COLUMNA.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        casillas[i][columna].setBackground(null);
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              stopwatch.start();
                        }
                        return;
                  }
            }
            for (Operacion constante : partida.getConstantes()){
                  if (constante.getIndiceFila() == fila && constante.getIndiceColumna() == columna){
                        casillas[fila][columna].setBackground(Color.red);
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              stopwatch.stop();
                        }
                        JOptionPane.showMessageDialog(jugarFrame, "JUGADA NO ES VÁLIDA PORQUE ESTE ES UN DÍGITO FIJO.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                        if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                              stopwatch.start();
                        }
                        casillas[fila][columna].setBackground(null);
                        return;
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
                                          stopwatch.stop();
                                    }
                                    JOptionPane.showMessageDialog(jugarFrame, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MAYOR.", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          stopwatch.start();
                                    }
                                    casillas[fila][columna].setBackground(null);
                                    return;
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
                                          stopwatch.stop();
                                    }
                                    JOptionPane.showMessageDialog(jugarFrame, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MENOR.", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          stopwatch.start();
                                    }
                                    casillas[fila][columna].setBackground(null);
                                    return;
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
                                          stopwatch.stop();
                                    }
                                    JOptionPane.showMessageDialog(jugarFrame, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MAYOR.", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          stopwatch.start();
                                    }
                                    casillas[fila][columna].setBackground(null);
                                    return;
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
                                          stopwatch.stop();
                                    }
                                     JOptionPane.showMessageDialog(jugarFrame, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MENOR.", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                                    if ("Sí".equals(Configuracion.getReloj()) || "Timer".equals(Configuracion.getReloj())){
                                          stopwatch.start();
                                    }
                                    casillas[fila][columna].setBackground(null);
                                    return;
                              } else {
                                    continue;
                              }                       
                  }
            }
            win = juego.añadirNumero(numero, fila, columna);
            if (win){
                  JOptionPane.showMessageDialog(jugarFrame, "JUEGO TERMINADO CON ÉXITO");
                  RegisterWin();
            }
   }
   
   // Esta función restablece el tablero a vacío
    private void resetTablero(int size){
        ArrayList<javax.swing.JLabel> labelsHorizontales = labelsHorizontalesPorTablero.get(size);
        ArrayList<javax.swing.JLabel> nonLabelsHorizontales = nonLabelsHorizontalesPorTablero.get(size);
        ArrayList<javax.swing.JLabel> labelsVerticales = labelsVerticalesPorTablero.get(size);
        ArrayList<javax.swing.JLabel> nonLabelsVerticales = nonLabelsVerticalesPorTablero.get(size);
        javax.swing.JButton [][] casillas = casillasPorTablero.get(size);
        ArrayList<javax.swing.JButton> nonCasillas = nonCasillasPorTablero.get(size);
        
        for (javax.swing.JLabel nonLabel : nonLabelsHorizontales){
            nonLabel.setVisible(false);
        }
        
        for (javax.swing.JLabel nonLabel : nonLabelsVerticales){
            nonLabel.setVisible(false);
        }
        
        for (javax.swing.JButton nonButton : nonCasillas){
            nonButton.setVisible(false);
        }
        
        for (javax.swing.JLabel label : labelsHorizontales){ // itera por todos los labels y botones de casillas
            label.setVisible(true);
            label.setText("");
        }
        
        for (javax.swing.JLabel label : labelsVerticales){
            label.setVisible(true);
            label.setText("");
        }
        
        for (javax.swing.JButton[] fila : casillas){
            for (javax.swing.JButton button : fila){
                button.setVisible(true);
                button.setText("");
            }
        }
    }
    
   
    // esta función establece el tablero con respecto a la partida escogida.
    private void establecerTablero(int size){
        ArrayList<javax.swing.JLabel> labelsHorizontales = labelsHorizontalesPorTablero.get(size);
        ArrayList<javax.swing.JLabel> labelsVerticales = labelsVerticalesPorTablero.get(size);
        
        for(Operacion desigualdad : partida.getOperaciones()){
            switch(desigualdad.getTipo()){
                case 'a': // pone los labels con el símbolo correcto
                    labelsHorizontales.get((size - 1) * desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText(">");
                    break;
                case 'b':
                    labelsHorizontales.get((size - 1) * desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText("<");
                    break;
                case 'y':
                    labelsVerticales.get(size*desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText("∨");
                    break;
                case 'z':
                    labelsVerticales.get(size*desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText("∧");  
            }
        }

        // llena las casillas de constantes
        javax.swing.JButton [][]  casillas = casillasPorTablero.get(size);
        for(Operacion constante : partida.getConstantes()){
            casillas[constante.getIndiceFila()][constante.getIndiceColumna()].setText(Character.toString(constante.getTipo()));
        }
    }
    
    // Esta función establece la partida de acuerdo con el nivel. 
    private void establecerPartida(){
          if (Partida.getPartidasPorNivel(Configuracion.getTamaño()).get(nivel).isEmpty()){ // si no hay partidas disponibles, se da un mensaje de error.
                  JOptionPane.showMessageDialog(jugarFrame, "NO HAY PARTIDAS DE ESE NIVEL.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                  jugarFrame.dispose();
                  Menu window = new Menu();
                  window.setVisible(true);
          }
          // se establece la partida de acuerdo con el nivel.
        index = Partida.getIndex(Configuracion.getTamaño(), nivel);
        partida = Partida.getPartidasPorNivel(Configuracion.getTamaño()).get(nivel).get(index);
        resetTablero(Configuracion.getTamaño());
        establecerTablero(Configuracion.getTamaño());
    }
      
    private void fill5(){
        casillas5 = new javax.swing.JButton[5][5];
        nonCasillas5.add(jugarFrame.getBtn00());
        nonCasillas5.add(jugarFrame.getBtn01());
        nonCasillas5.add(jugarFrame.getBtn02());
        nonCasillas5.add(jugarFrame.getBtn03());
        nonCasillas5.add(jugarFrame.getBtn04());
        nonCasillas5.add(jugarFrame.getBtn05());
        nonCasillas5.add(jugarFrame.getBtn06());
        nonCasillas5.add(jugarFrame.getBtn07());
        nonCasillas5.add(jugarFrame.getBtn08());
        nonCasillas5.add(jugarFrame.getBtn10());
        nonCasillas5.add(jugarFrame.getBtn11());
        nonCasillas5.add(jugarFrame.getBtn12());
        nonCasillas5.add(jugarFrame.getBtn13());
        nonCasillas5.add(jugarFrame.getBtn14());
        nonCasillas5.add(jugarFrame.getBtn15());
        nonCasillas5.add(jugarFrame.getBtn16());
        nonCasillas5.add(jugarFrame.getBtn17());
        nonCasillas5.add(jugarFrame.getBtn18());
        nonCasillas5.add(jugarFrame.getBtn20());
        nonCasillas5.add(jugarFrame.getBtn21());
        nonCasillas5.add(jugarFrame.getBtn27());
        nonCasillas5.add(jugarFrame.getBtn28());
        nonCasillas5.add(jugarFrame.getBtn30());
        nonCasillas5.add(jugarFrame.getBtn31());
        nonCasillas5.add(jugarFrame.getBtn37());
        nonCasillas5.add(jugarFrame.getBtn38());
        nonCasillas5.add(jugarFrame.getBtn40());
        nonCasillas5.add(jugarFrame.getBtn41());
        nonCasillas5.add(jugarFrame.getBtn47());
        nonCasillas5.add(jugarFrame.getBtn48());
        nonCasillas5.add(jugarFrame.getBtn50());
        nonCasillas5.add(jugarFrame.getBtn51());
        nonCasillas5.add(jugarFrame.getBtn57());
        nonCasillas5.add(jugarFrame.getBtn58());
        nonCasillas5.add(jugarFrame.getBtn60());
        nonCasillas5.add(jugarFrame.getBtn61());
        nonCasillas5.add(jugarFrame.getBtn67());
        nonCasillas5.add(jugarFrame.getBtn68());
        nonCasillas5.add(jugarFrame.getBtn70());
        nonCasillas5.add(jugarFrame.getBtn71());
        nonCasillas5.add(jugarFrame.getBtn72());
        nonCasillas5.add(jugarFrame.getBtn73());
        nonCasillas5.add(jugarFrame.getBtn74());
        nonCasillas5.add(jugarFrame.getBtn75());
        nonCasillas5.add(jugarFrame.getBtn76());
        nonCasillas5.add(jugarFrame.getBtn77());
        nonCasillas5.add(jugarFrame.getBtn78());
        nonCasillas5.add(jugarFrame.getBtn80());
        nonCasillas5.add(jugarFrame.getBtn81());
        nonCasillas5.add(jugarFrame.getBtn82());
        nonCasillas5.add(jugarFrame.getBtn83());
        nonCasillas5.add(jugarFrame.getBtn84());
        nonCasillas5.add(jugarFrame.getBtn85());
        nonCasillas5.add(jugarFrame.getBtn86());
        nonCasillas5.add(jugarFrame.getBtn87());
        nonCasillas5.add(jugarFrame.getBtn88());
        
        casillas5[0][0] = jugarFrame.getBtn22();
        casillas5[0][1] = jugarFrame.getBtn23();
        casillas5[0][2] = jugarFrame.getBtn24();
        casillas5[0][3] = jugarFrame.getBtn25();
        casillas5[0][4] = jugarFrame.getBtn26();
        casillas5[1][0] = jugarFrame.getBtn32();
        casillas5[1][1] = jugarFrame.getBtn33();
        casillas5[1][2] = jugarFrame.getBtn34();
        casillas5[1][3] = jugarFrame.getBtn35();
        casillas5[1][4] = jugarFrame.getBtn36();
        casillas5[2][0] = jugarFrame.getBtn42();
        casillas5[2][1] = jugarFrame.getBtn43();
        casillas5[2][2] = jugarFrame.getBtn44();
        casillas5[2][3] = jugarFrame.getBtn45();
        casillas5[2][4] = jugarFrame.getBtn46();
        casillas5[3][0] = jugarFrame.getBtn52();
        casillas5[3][1] = jugarFrame.getBtn53();
        casillas5[3][2] = jugarFrame.getBtn54();
        casillas5[3][3] = jugarFrame.getBtn55();
        casillas5[3][4] = jugarFrame.getBtn56();
        casillas5[4][0] = jugarFrame.getBtn62();
        casillas5[4][1] = jugarFrame.getBtn63();
        casillas5[4][2] = jugarFrame.getBtn64();
        casillas5[4][3] = jugarFrame.getBtn65();
        casillas5[4][4] = jugarFrame.getBtn66();
        
        nonLabelsHorizontales5.add(jugarFrame.getOp001());
        nonLabelsHorizontales5.add(jugarFrame.getOp012());
        nonLabelsHorizontales5.add(jugarFrame.getOp023());
        nonLabelsHorizontales5.add(jugarFrame.getOp034());
        nonLabelsHorizontales5.add(jugarFrame.getOp045());
        nonLabelsHorizontales5.add(jugarFrame.getOp056());
        nonLabelsHorizontales5.add(jugarFrame.getOp067());
        nonLabelsHorizontales5.add(jugarFrame.getOp078());
        nonLabelsHorizontales5.add(jugarFrame.getOp101());
        nonLabelsHorizontales5.add(jugarFrame.getOp112());
        nonLabelsHorizontales5.add(jugarFrame.getOp123());
        nonLabelsHorizontales5.add(jugarFrame.getOp134());
        nonLabelsHorizontales5.add(jugarFrame.getOp145());
        nonLabelsHorizontales5.add(jugarFrame.getOp156());
        nonLabelsHorizontales5.add(jugarFrame.getOp167());
        nonLabelsHorizontales5.add(jugarFrame.getOp178());
        nonLabelsHorizontales5.add(jugarFrame.getOp201());
        nonLabelsHorizontales5.add(jugarFrame.getOp212());
        labelsHorizontales5.add(jugarFrame.getOp223());
        labelsHorizontales5.add(jugarFrame.getOp234());
        labelsHorizontales5.add(jugarFrame.getOp245());
        labelsHorizontales5.add(jugarFrame.getOp256());
        nonLabelsHorizontales5.add(jugarFrame.getOp267());
        nonLabelsHorizontales5.add(jugarFrame.getOp278());
        nonLabelsHorizontales5.add(jugarFrame.getOp301());
        nonLabelsHorizontales5.add(jugarFrame.getOp312());
        labelsHorizontales5.add(jugarFrame.getOp323());
        labelsHorizontales5.add(jugarFrame.getOp334());
        labelsHorizontales5.add(jugarFrame.getOp345());
        labelsHorizontales5.add(jugarFrame.getOp356());
        nonLabelsHorizontales5.add(jugarFrame.getOp367());
        nonLabelsHorizontales5.add(jugarFrame.getOp378());
        nonLabelsHorizontales5.add(jugarFrame.getOp401());
        nonLabelsHorizontales5.add(jugarFrame.getOp412());
        labelsHorizontales5.add(jugarFrame.getOp423());
        labelsHorizontales5.add(jugarFrame.getOp434());
        labelsHorizontales5.add(jugarFrame.getOp445());
        labelsHorizontales5.add(jugarFrame.getOp456());
        nonLabelsHorizontales5.add(jugarFrame.getOp467());
        nonLabelsHorizontales5.add(jugarFrame.getOp478());
        nonLabelsHorizontales5.add(jugarFrame.getOp501());
        nonLabelsHorizontales5.add(jugarFrame.getOp512());        
        labelsHorizontales5.add(jugarFrame.getOp523());
        labelsHorizontales5.add(jugarFrame.getOp534());
        labelsHorizontales5.add(jugarFrame.getOp545());
        labelsHorizontales5.add(jugarFrame.getOp556());
        nonLabelsHorizontales5.add(jugarFrame.getOp567());
        nonLabelsHorizontales5.add(jugarFrame.getOp578());
        nonLabelsHorizontales5.add(jugarFrame.getOp601());
        nonLabelsHorizontales5.add(jugarFrame.getOp612());
        labelsHorizontales5.add(jugarFrame.getOp623());
        labelsHorizontales5.add(jugarFrame.getOp634());
        labelsHorizontales5.add(jugarFrame.getOp645());
        labelsHorizontales5.add(jugarFrame.getOp656());
        nonLabelsHorizontales5.add(jugarFrame.getOp667());
        nonLabelsHorizontales5.add(jugarFrame.getOp678());
        nonLabelsHorizontales5.add(jugarFrame.getOp701());
        nonLabelsHorizontales5.add(jugarFrame.getOp712());
        nonLabelsHorizontales5.add(jugarFrame.getOp723());
        nonLabelsHorizontales5.add(jugarFrame.getOp734());
        nonLabelsHorizontales5.add(jugarFrame.getOp745());
        nonLabelsHorizontales5.add(jugarFrame.getOp756());
        nonLabelsHorizontales5.add(jugarFrame.getOp767());
        nonLabelsHorizontales5.add(jugarFrame.getOp778());
        nonLabelsHorizontales5.add(jugarFrame.getOp801());
        nonLabelsHorizontales5.add(jugarFrame.getOp812());
        nonLabelsHorizontales5.add(jugarFrame.getOp823());
        nonLabelsHorizontales5.add(jugarFrame.getOp834());
        nonLabelsHorizontales5.add(jugarFrame.getOp845());
        nonLabelsHorizontales5.add(jugarFrame.getOp856());
        nonLabelsHorizontales5.add(jugarFrame.getOp867());
        nonLabelsHorizontales5.add(jugarFrame.getOp878());

        nonLabelsVerticales5.add(jugarFrame.getOp00V());        
        nonLabelsVerticales5.add(jugarFrame.getOp01V());
        nonLabelsVerticales5.add(jugarFrame.getOp02V());
        nonLabelsVerticales5.add(jugarFrame.getOp03V());
        nonLabelsVerticales5.add(jugarFrame.getOp04V());
        nonLabelsVerticales5.add(jugarFrame.getOp05V());
        nonLabelsVerticales5.add(jugarFrame.getOp06V());
        nonLabelsVerticales5.add(jugarFrame.getOp07V());
        nonLabelsVerticales5.add(jugarFrame.getOp08V());
        nonLabelsVerticales5.add(jugarFrame.getOp10V( ));  
        nonLabelsVerticales5.add(jugarFrame.getOp11V());
        nonLabelsVerticales5.add(jugarFrame.getOp12V());
        nonLabelsVerticales5.add(jugarFrame.getOp13V());
        nonLabelsVerticales5.add(jugarFrame.getOp14V());
        nonLabelsVerticales5.add(jugarFrame.getOp15V());
        nonLabelsVerticales5.add(jugarFrame.getOp16V());
        nonLabelsVerticales5.add(jugarFrame.getOp17V());
        nonLabelsVerticales5.add(jugarFrame.getOp18V());
        nonLabelsVerticales5.add(jugarFrame.getOp20V());        
        nonLabelsVerticales5.add(jugarFrame.getOp21V());
        labelsVerticales5.add(jugarFrame.getOp22V());
        labelsVerticales5.add(jugarFrame.getOp23V());
        labelsVerticales5.add(jugarFrame.getOp24V());
        labelsVerticales5.add(jugarFrame.getOp25V());
        labelsVerticales5.add(jugarFrame.getOp26V());
        nonLabelsVerticales5.add(jugarFrame.getOp27V());
        nonLabelsVerticales5.add(jugarFrame.getOp28V());
        nonLabelsVerticales5.add(jugarFrame.getOp30V());        
        nonLabelsVerticales5.add(jugarFrame.getOp31V());
        labelsVerticales5.add(jugarFrame.getOp32V());
        labelsVerticales5.add(jugarFrame.getOp33V());
        labelsVerticales5.add(jugarFrame.getOp34V());
        labelsVerticales5.add(jugarFrame.getOp35V());
        labelsVerticales5.add(jugarFrame.getOp36V());
        nonLabelsVerticales5.add(jugarFrame.getOp37V());
        nonLabelsVerticales5.add(jugarFrame.getOp38V());
        nonLabelsVerticales5.add(jugarFrame.getOp40V());        
        nonLabelsVerticales5.add(jugarFrame.getOp41V());
        labelsVerticales5.add(jugarFrame.getOp42V());
        labelsVerticales5.add(jugarFrame.getOp43V());
        labelsVerticales5.add(jugarFrame.getOp44V());
        labelsVerticales5.add(jugarFrame.getOp45V());
        labelsVerticales5.add(jugarFrame.getOp46V());
        nonLabelsVerticales5.add(jugarFrame.getOp47V());
        nonLabelsVerticales5.add(jugarFrame.getOp48V());
        nonLabelsVerticales5.add(jugarFrame.getOp50V());        
        nonLabelsVerticales5.add(jugarFrame.getOp51V());
        labelsVerticales5.add(jugarFrame.getOp52V());
        labelsVerticales5.add(jugarFrame.getOp53V());
        labelsVerticales5.add(jugarFrame.getOp54V());
        labelsVerticales5.add(jugarFrame.getOp55V());
        labelsVerticales5.add(jugarFrame.getOp56V());
        nonLabelsVerticales5.add(jugarFrame.getOp57V());
        nonLabelsVerticales5.add(jugarFrame.getOp58V());
        nonLabelsVerticales5.add(jugarFrame.getOp60V());        
        nonLabelsVerticales5.add(jugarFrame.getOp61V());
        nonLabelsVerticales5.add(jugarFrame.getOp62V());
        nonLabelsVerticales5.add(jugarFrame.getOp63V());
        nonLabelsVerticales5.add(jugarFrame.getOp64V());
        nonLabelsVerticales5.add(jugarFrame.getOp65V());
        nonLabelsVerticales5.add(jugarFrame.getOp66V());
        nonLabelsVerticales5.add(jugarFrame.getOp67V());
        nonLabelsVerticales5.add(jugarFrame.getOp68V());
        nonLabelsVerticales5.add(jugarFrame.getOp70V());        
        nonLabelsVerticales5.add(jugarFrame.getOp71V());
        nonLabelsVerticales5.add(jugarFrame.getOp72V());
        nonLabelsVerticales5.add(jugarFrame.getOp73V());
        nonLabelsVerticales5.add(jugarFrame.getOp74V());
        nonLabelsVerticales5.add(jugarFrame.getOp75V());
        nonLabelsVerticales5.add(jugarFrame.getOp76V());
        nonLabelsVerticales5.add(jugarFrame.getOp77V());
        nonLabelsVerticales5.add(jugarFrame.getOp78V());

    }
    
    private void fill6(){
        casillas6 = new javax.swing.JButton[6][6];
        nonCasillas6.add(jugarFrame.getBtn00());
        nonCasillas6.add(jugarFrame.getBtn01());
        nonCasillas6.add(jugarFrame.getBtn02());
        nonCasillas6.add(jugarFrame.getBtn03());
        nonCasillas6.add(jugarFrame.getBtn04());
        nonCasillas6.add(jugarFrame.getBtn05());
        nonCasillas6.add(jugarFrame.getBtn06());
        nonCasillas6.add(jugarFrame.getBtn07());
        nonCasillas6.add(jugarFrame.getBtn08());
        nonCasillas6.add(jugarFrame.getBtn10());
        nonCasillas6.add(jugarFrame.getBtn11());
        nonCasillas6.add(jugarFrame.getBtn18());
        nonCasillas6.add(jugarFrame.getBtn20());
        nonCasillas6.add(jugarFrame.getBtn21());
        nonCasillas6.add(jugarFrame.getBtn28());
        nonCasillas6.add(jugarFrame.getBtn30());
        nonCasillas6.add(jugarFrame.getBtn31());
        nonCasillas6.add(jugarFrame.getBtn38());
        nonCasillas6.add(jugarFrame.getBtn40());
        nonCasillas6.add(jugarFrame.getBtn41());
        nonCasillas6.add(jugarFrame.getBtn48());
        nonCasillas6.add(jugarFrame.getBtn50());
        nonCasillas6.add(jugarFrame.getBtn51());
        nonCasillas6.add(jugarFrame.getBtn58());
        nonCasillas6.add(jugarFrame.getBtn60());
        nonCasillas6.add(jugarFrame.getBtn61());
        nonCasillas6.add(jugarFrame.getBtn68());
        nonCasillas6.add(jugarFrame.getBtn70());
        nonCasillas6.add(jugarFrame.getBtn71());
        nonCasillas6.add(jugarFrame.getBtn72());
        nonCasillas6.add(jugarFrame.getBtn73());
        nonCasillas6.add(jugarFrame.getBtn74());
        nonCasillas6.add(jugarFrame.getBtn75());
        nonCasillas6.add(jugarFrame.getBtn76());
        nonCasillas6.add(jugarFrame.getBtn77());
        nonCasillas6.add(jugarFrame.getBtn78());
        nonCasillas6.add(jugarFrame.getBtn80());
        nonCasillas6.add(jugarFrame.getBtn81());
        nonCasillas6.add(jugarFrame.getBtn82());
        nonCasillas6.add(jugarFrame.getBtn83());
        nonCasillas6.add(jugarFrame.getBtn84());
        nonCasillas6.add(jugarFrame.getBtn85());
        nonCasillas6.add(jugarFrame.getBtn86());
        nonCasillas6.add(jugarFrame.getBtn87());
        nonCasillas6.add(jugarFrame.getBtn88());
        
        casillas6[0][0] = jugarFrame.getBtn12();
        casillas6[0][1] = jugarFrame.getBtn13();
        casillas6[0][2] = jugarFrame.getBtn14();
        casillas6[0][3] = jugarFrame.getBtn15();
        casillas6[0][4] = jugarFrame.getBtn16();
        casillas6[0][5] = jugarFrame.getBtn17();
        casillas6[1][0] = jugarFrame.getBtn22();
        casillas6[1][1] = jugarFrame.getBtn23();
        casillas6[1][2] = jugarFrame.getBtn24();
        casillas6[1][3] = jugarFrame.getBtn25();
        casillas6[1][4] = jugarFrame.getBtn26();
        casillas6[1][5] = jugarFrame.getBtn27();
        casillas6[2][0] = jugarFrame.getBtn32();
        casillas6[2][1] = jugarFrame.getBtn33();
        casillas6[2][2] = jugarFrame.getBtn34();
        casillas6[2][3] = jugarFrame.getBtn35();
        casillas6[2][4] = jugarFrame.getBtn36();
        casillas6[2][5] = jugarFrame.getBtn37();
        casillas6[3][0] = jugarFrame.getBtn42();
        casillas6[3][1] = jugarFrame.getBtn43();
        casillas6[3][2] = jugarFrame.getBtn44();
        casillas6[3][3] = jugarFrame.getBtn45();
        casillas6[3][4] = jugarFrame.getBtn46();
        casillas6[3][5] = jugarFrame.getBtn47();
        casillas6[4][0] = jugarFrame.getBtn52();
        casillas6[4][1] = jugarFrame.getBtn53();
        casillas6[4][2] = jugarFrame.getBtn54();
        casillas6[4][3] = jugarFrame.getBtn55();
        casillas6[4][4] = jugarFrame.getBtn56();
        casillas6[4][5] = jugarFrame.getBtn57();
        casillas6[5][0] = jugarFrame.getBtn62();
        casillas6[5][1] = jugarFrame.getBtn63();
        casillas6[5][2] = jugarFrame.getBtn64();
        casillas6[5][3] = jugarFrame.getBtn65();
        casillas6[5][4] = jugarFrame.getBtn66();
        casillas6[5][5] = jugarFrame.getBtn67();
        
        nonLabelsHorizontales6.add(jugarFrame.getOp001());
        nonLabelsHorizontales6.add(jugarFrame.getOp012());
        nonLabelsHorizontales6.add(jugarFrame.getOp023());
        nonLabelsHorizontales6.add(jugarFrame.getOp034());
        nonLabelsHorizontales6.add(jugarFrame.getOp045());
        nonLabelsHorizontales6.add(jugarFrame.getOp056());
        nonLabelsHorizontales6.add(jugarFrame.getOp067());
        nonLabelsHorizontales6.add(jugarFrame.getOp078());
        nonLabelsHorizontales6.add(jugarFrame.getOp101());
        nonLabelsHorizontales6.add(jugarFrame.getOp112());
        labelsHorizontales6.add(jugarFrame.getOp123());
        labelsHorizontales6.add(jugarFrame.getOp134());
        labelsHorizontales6.add(jugarFrame.getOp145());
        labelsHorizontales6.add(jugarFrame.getOp156());
        labelsHorizontales6.add(jugarFrame.getOp167());
        nonLabelsHorizontales6.add(jugarFrame.getOp178());
        nonLabelsHorizontales6.add(jugarFrame.getOp201());
        nonLabelsHorizontales6.add(jugarFrame.getOp212());
        labelsHorizontales6.add(jugarFrame.getOp223());
        labelsHorizontales6.add(jugarFrame.getOp234());
        labelsHorizontales6.add(jugarFrame.getOp245());
        labelsHorizontales6.add(jugarFrame.getOp256());
        labelsHorizontales6.add(jugarFrame.getOp267());
        nonLabelsHorizontales6.add(jugarFrame.getOp278());
        nonLabelsHorizontales6.add(jugarFrame.getOp301());
        nonLabelsHorizontales6.add(jugarFrame.getOp312());
        labelsHorizontales6.add(jugarFrame.getOp323());
        labelsHorizontales6.add(jugarFrame.getOp334());
        labelsHorizontales6.add(jugarFrame.getOp345());
        labelsHorizontales6.add(jugarFrame.getOp356());
        labelsHorizontales6.add(jugarFrame.getOp367());
        nonLabelsHorizontales6.add(jugarFrame.getOp378());
        nonLabelsHorizontales6.add(jugarFrame.getOp401());
        nonLabelsHorizontales6.add(jugarFrame.getOp412());
        labelsHorizontales6.add(jugarFrame.getOp423());
        labelsHorizontales6.add(jugarFrame.getOp434());
        labelsHorizontales6.add(jugarFrame.getOp445());
        labelsHorizontales6.add(jugarFrame.getOp456());
        labelsHorizontales6.add(jugarFrame.getOp467());
        nonLabelsHorizontales6.add(jugarFrame.getOp478());
        nonLabelsHorizontales6.add(jugarFrame.getOp501());
        nonLabelsHorizontales6.add(jugarFrame.getOp512());        
        labelsHorizontales6.add(jugarFrame.getOp523());
        labelsHorizontales6.add(jugarFrame.getOp534());
        labelsHorizontales6.add(jugarFrame.getOp545());
        labelsHorizontales6.add(jugarFrame.getOp556());
        labelsHorizontales6.add(jugarFrame.getOp567());
        nonLabelsHorizontales6.add(jugarFrame.getOp578());
        nonLabelsHorizontales6.add(jugarFrame.getOp601());
        nonLabelsHorizontales6.add(jugarFrame.getOp612());
        labelsHorizontales6.add(jugarFrame.getOp623());
        labelsHorizontales6.add(jugarFrame.getOp634());
        labelsHorizontales6.add(jugarFrame.getOp645());
        labelsHorizontales6.add(jugarFrame.getOp656());
        labelsHorizontales6.add(jugarFrame.getOp667());
        nonLabelsHorizontales6.add(jugarFrame.getOp678());
        nonLabelsHorizontales6.add(jugarFrame.getOp701());
        nonLabelsHorizontales6.add(jugarFrame.getOp712());
        nonLabelsHorizontales6.add(jugarFrame.getOp723());
        nonLabelsHorizontales6.add(jugarFrame.getOp734());
        nonLabelsHorizontales6.add(jugarFrame.getOp745());
        nonLabelsHorizontales6.add(jugarFrame.getOp756());
        nonLabelsHorizontales6.add(jugarFrame.getOp767());
        nonLabelsHorizontales6.add(jugarFrame.getOp778());
        nonLabelsHorizontales6.add(jugarFrame.getOp801());
        nonLabelsHorizontales6.add(jugarFrame.getOp812());
        nonLabelsHorizontales6.add(jugarFrame.getOp823());
        nonLabelsHorizontales6.add(jugarFrame.getOp834());
        nonLabelsHorizontales6.add(jugarFrame.getOp845());
        nonLabelsHorizontales6.add(jugarFrame.getOp856());
        nonLabelsHorizontales6.add(jugarFrame.getOp867());
        nonLabelsHorizontales6.add(jugarFrame.getOp878());
        
        nonLabelsVerticales6.add(jugarFrame.getOp00V());        
        nonLabelsVerticales6.add(jugarFrame.getOp01V());
        nonLabelsVerticales6.add(jugarFrame.getOp02V());
        nonLabelsVerticales6.add(jugarFrame.getOp03V());
        nonLabelsVerticales6.add(jugarFrame.getOp04V());
        nonLabelsVerticales6.add(jugarFrame.getOp05V());
        nonLabelsVerticales6.add(jugarFrame.getOp06V());
        nonLabelsVerticales6.add(jugarFrame.getOp07V());
        nonLabelsVerticales6.add(jugarFrame.getOp08V());
        nonLabelsVerticales6.add(jugarFrame.getOp10V());        
        nonLabelsVerticales6.add(jugarFrame.getOp11V());
        labelsVerticales6.add(jugarFrame.getOp12V());
        labelsVerticales6.add(jugarFrame.getOp13V());
        labelsVerticales6.add(jugarFrame.getOp14V());
        labelsVerticales6.add(jugarFrame.getOp15V());
        labelsVerticales6.add(jugarFrame.getOp16V());
        labelsVerticales6.add(jugarFrame.getOp17V());
        nonLabelsVerticales6.add(jugarFrame.getOp18V());
        nonLabelsVerticales6.add(jugarFrame.getOp20V());        
        nonLabelsVerticales6.add(jugarFrame.getOp21V());
        labelsVerticales6.add(jugarFrame.getOp22V());
        labelsVerticales6.add(jugarFrame.getOp23V());
        labelsVerticales6.add(jugarFrame.getOp24V());
        labelsVerticales6.add(jugarFrame.getOp25V());
        labelsVerticales6.add(jugarFrame.getOp26V());
        labelsVerticales6.add(jugarFrame.getOp27V());
        nonLabelsVerticales6.add(jugarFrame.getOp28V());
        nonLabelsVerticales6.add(jugarFrame.getOp30V());        
        nonLabelsVerticales6.add(jugarFrame.getOp31V());
        labelsVerticales6.add(jugarFrame.getOp32V());
        labelsVerticales6.add(jugarFrame.getOp33V());
        labelsVerticales6.add(jugarFrame.getOp34V());
        labelsVerticales6.add(jugarFrame.getOp35V());
        labelsVerticales6.add(jugarFrame.getOp36V());
        labelsVerticales6.add(jugarFrame.getOp37V());
        nonLabelsVerticales6.add(jugarFrame.getOp38V());
        nonLabelsVerticales6.add(jugarFrame.getOp40V());        
        nonLabelsVerticales6.add(jugarFrame.getOp41V());
        labelsVerticales6.add(jugarFrame.getOp42V());
        labelsVerticales6.add(jugarFrame.getOp43V());
        labelsVerticales6.add(jugarFrame.getOp44V());
        labelsVerticales6.add(jugarFrame.getOp45V());
        labelsVerticales6.add(jugarFrame.getOp46V());
        labelsVerticales6.add(jugarFrame.getOp47V());
        nonLabelsVerticales6.add(jugarFrame.getOp48V());
        nonLabelsVerticales6.add(jugarFrame.getOp50V());        
        nonLabelsVerticales6.add(jugarFrame.getOp51V());
        labelsVerticales6.add(jugarFrame.getOp52V());
        labelsVerticales6.add(jugarFrame.getOp53V());
        labelsVerticales6.add(jugarFrame.getOp54V());
        labelsVerticales6.add(jugarFrame.getOp55V());
        labelsVerticales6.add(jugarFrame.getOp56V());
        labelsVerticales6.add(jugarFrame.getOp57V());
        nonLabelsVerticales6.add(jugarFrame.getOp58V());
        nonLabelsVerticales6.add(jugarFrame.getOp60V());        
        nonLabelsVerticales6.add(jugarFrame.getOp61V());
        nonLabelsVerticales6.add(jugarFrame.getOp62V());
        nonLabelsVerticales6.add(jugarFrame.getOp63V());
        nonLabelsVerticales6.add(jugarFrame.getOp64V());
        nonLabelsVerticales6.add(jugarFrame.getOp65V());
        nonLabelsVerticales6.add(jugarFrame.getOp66V());
        nonLabelsVerticales6.add(jugarFrame.getOp67V());
        nonLabelsVerticales6.add(jugarFrame.getOp68V());
        nonLabelsVerticales6.add(jugarFrame.getOp70V());        
        nonLabelsVerticales6.add(jugarFrame.getOp71V());
        nonLabelsVerticales6.add(jugarFrame.getOp72V());
        nonLabelsVerticales6.add(jugarFrame.getOp73V());
        nonLabelsVerticales6.add(jugarFrame.getOp74V());
        nonLabelsVerticales6.add(jugarFrame.getOp75V());
        nonLabelsVerticales6.add(jugarFrame.getOp76V());
        nonLabelsVerticales6.add(jugarFrame.getOp77V());
        nonLabelsVerticales6.add(jugarFrame.getOp78V());
    }

    private void fill7(){
        casillas7 = new javax.swing.JButton[7][7];
        nonCasillas7.add(jugarFrame.getBtn00());
        nonCasillas7.add(jugarFrame.getBtn01());
        nonCasillas7.add(jugarFrame.getBtn02());
        nonCasillas7.add(jugarFrame.getBtn03());
        nonCasillas7.add(jugarFrame.getBtn04());
        nonCasillas7.add(jugarFrame.getBtn05());
        nonCasillas7.add(jugarFrame.getBtn06());
        nonCasillas7.add(jugarFrame.getBtn07());
        nonCasillas7.add(jugarFrame.getBtn08());
        nonCasillas7.add(jugarFrame.getBtn10());
        nonCasillas7.add(jugarFrame.getBtn18());
        nonCasillas7.add(jugarFrame.getBtn20());
        nonCasillas7.add(jugarFrame.getBtn28());
        nonCasillas7.add(jugarFrame.getBtn30());
        nonCasillas7.add(jugarFrame.getBtn38());
        nonCasillas7.add(jugarFrame.getBtn40());
        nonCasillas7.add(jugarFrame.getBtn48());
        nonCasillas7.add(jugarFrame.getBtn50());
        nonCasillas7.add(jugarFrame.getBtn58());
        nonCasillas7.add(jugarFrame.getBtn60());
        nonCasillas7.add(jugarFrame.getBtn68());
        nonCasillas7.add(jugarFrame.getBtn70());
        nonCasillas7.add(jugarFrame.getBtn71());
        nonCasillas7.add(jugarFrame.getBtn78());
        nonCasillas7.add(jugarFrame.getBtn80());
        nonCasillas7.add(jugarFrame.getBtn81());
        nonCasillas7.add(jugarFrame.getBtn82());
        nonCasillas7.add(jugarFrame.getBtn83());
        nonCasillas7.add(jugarFrame.getBtn84());
        nonCasillas7.add(jugarFrame.getBtn85());
        nonCasillas7.add(jugarFrame.getBtn86());
        nonCasillas7.add(jugarFrame.getBtn87());
        nonCasillas7.add(jugarFrame.getBtn88());
        
        casillas7[0][0] = jugarFrame.getBtn11();
        casillas7[0][1] = jugarFrame.getBtn12();
        casillas7[0][2] = jugarFrame.getBtn13();
        casillas7[0][3] = jugarFrame.getBtn14();
        casillas7[0][4] = jugarFrame.getBtn15();
        casillas7[0][5] = jugarFrame.getBtn16();
        casillas7[0][6] = jugarFrame.getBtn17();
        casillas7[1][0] = jugarFrame.getBtn21();
        casillas7[1][1] = jugarFrame.getBtn22();
        casillas7[1][2] = jugarFrame.getBtn23();
        casillas7[1][3] = jugarFrame.getBtn24();
        casillas7[1][4] = jugarFrame.getBtn25();
        casillas7[1][5] = jugarFrame.getBtn26();
        casillas7[1][6] = jugarFrame.getBtn27();
        casillas7[2][0] = jugarFrame.getBtn31();
        casillas7[2][1] = jugarFrame.getBtn32();
        casillas7[2][2] = jugarFrame.getBtn33();
        casillas7[2][3] = jugarFrame.getBtn34();
        casillas7[2][4] = jugarFrame.getBtn35();
        casillas7[2][5] = jugarFrame.getBtn36();
        casillas7[2][6] = jugarFrame.getBtn37();
        casillas7[3][0] = jugarFrame.getBtn41();
        casillas7[3][1] = jugarFrame.getBtn42();
        casillas7[3][2] = jugarFrame.getBtn43();
        casillas7[3][3] = jugarFrame.getBtn44();
        casillas7[3][4] = jugarFrame.getBtn45();
        casillas7[3][5] = jugarFrame.getBtn46();
        casillas7[3][6] = jugarFrame.getBtn47();
        casillas7[4][0] = jugarFrame.getBtn51();
        casillas7[4][1] = jugarFrame.getBtn52();
        casillas7[4][2] = jugarFrame.getBtn53();
        casillas7[4][3] = jugarFrame.getBtn54();
        casillas7[4][4] = jugarFrame.getBtn55();
        casillas7[4][5] = jugarFrame.getBtn56();
        casillas7[4][6] = jugarFrame.getBtn57();
        casillas7[5][0] = jugarFrame.getBtn61();
        casillas7[5][1] = jugarFrame.getBtn62();
        casillas7[5][2] = jugarFrame.getBtn63();
        casillas7[5][3] = jugarFrame.getBtn64();
        casillas7[5][4] = jugarFrame.getBtn65();
        casillas7[5][5] = jugarFrame.getBtn66();
        casillas7[5][6] = jugarFrame.getBtn67();
        casillas7[6][0] = jugarFrame.getBtn71();
        casillas7[6][1] = jugarFrame.getBtn72();
        casillas7[6][2] = jugarFrame.getBtn73();
        casillas7[6][3] = jugarFrame.getBtn74();
        casillas7[6][4] = jugarFrame.getBtn75();
        casillas7[6][5] = jugarFrame.getBtn76();
        casillas7[6][6] = jugarFrame.getBtn77();
       
        nonLabelsHorizontales7.add(jugarFrame.getOp001());
        nonLabelsHorizontales7.add(jugarFrame.getOp012());
        nonLabelsHorizontales7.add(jugarFrame.getOp023());
        nonLabelsHorizontales7.add(jugarFrame.getOp034());
        nonLabelsHorizontales7.add(jugarFrame.getOp045());
        nonLabelsHorizontales7.add(jugarFrame.getOp056());
        nonLabelsHorizontales7.add(jugarFrame.getOp067());
        nonLabelsHorizontales7.add(jugarFrame.getOp078());
        nonLabelsHorizontales7.add(jugarFrame.getOp101());
        labelsHorizontales7.add(jugarFrame.getOp112());
        labelsHorizontales7.add(jugarFrame.getOp123());
        labelsHorizontales7.add(jugarFrame.getOp134());
        labelsHorizontales7.add(jugarFrame.getOp145());
        labelsHorizontales7.add(jugarFrame.getOp156());
        labelsHorizontales7.add(jugarFrame.getOp167());
        nonLabelsHorizontales7.add(jugarFrame.getOp178());
        nonLabelsHorizontales7.add(jugarFrame.getOp201());
        labelsHorizontales7.add(jugarFrame.getOp212());
        labelsHorizontales7.add(jugarFrame.getOp223());
        labelsHorizontales7.add(jugarFrame.getOp234());
        labelsHorizontales7.add(jugarFrame.getOp245());
        labelsHorizontales7.add(jugarFrame.getOp256());
        labelsHorizontales7.add(jugarFrame.getOp267());
        nonLabelsHorizontales7.add(jugarFrame.getOp278());
        nonLabelsHorizontales7.add(jugarFrame.getOp301());
        labelsHorizontales7.add(jugarFrame.getOp312());
        labelsHorizontales7.add(jugarFrame.getOp323());
        labelsHorizontales7.add(jugarFrame.getOp334());
        labelsHorizontales7.add(jugarFrame.getOp345());
        labelsHorizontales7.add(jugarFrame.getOp356());
        labelsHorizontales7.add(jugarFrame.getOp367());
        nonLabelsHorizontales7.add(jugarFrame.getOp378());
        nonLabelsHorizontales7.add(jugarFrame.getOp401());
        labelsHorizontales7.add(jugarFrame.getOp412());
        labelsHorizontales7.add(jugarFrame.getOp423());
        labelsHorizontales7.add(jugarFrame.getOp434());
        labelsHorizontales7.add(jugarFrame.getOp445());
        labelsHorizontales7.add(jugarFrame.getOp456());
        labelsHorizontales7.add(jugarFrame.getOp467());
        nonLabelsHorizontales7.add(jugarFrame.getOp478());
        nonLabelsHorizontales7.add(jugarFrame.getOp501());
        labelsHorizontales7.add(jugarFrame.getOp512());        
        labelsHorizontales7.add(jugarFrame.getOp523());
        labelsHorizontales7.add(jugarFrame.getOp534());
        labelsHorizontales7.add(jugarFrame.getOp545());
        labelsHorizontales7.add(jugarFrame.getOp556());
        labelsHorizontales7.add(jugarFrame.getOp567());
        nonLabelsHorizontales7.add(jugarFrame.getOp578());
        nonLabelsHorizontales7.add(jugarFrame.getOp601());
        labelsHorizontales7.add(jugarFrame.getOp612());
        labelsHorizontales7.add(jugarFrame.getOp623());
        labelsHorizontales7.add(jugarFrame.getOp634());
        labelsHorizontales7.add(jugarFrame.getOp645());
        labelsHorizontales7.add(jugarFrame.getOp656());
        labelsHorizontales7.add(jugarFrame.getOp667());
        nonLabelsHorizontales7.add(jugarFrame.getOp678());
        nonLabelsHorizontales7.add(jugarFrame.getOp701());
        labelsHorizontales7.add(jugarFrame.getOp712());
        labelsHorizontales7.add(jugarFrame.getOp723());
        labelsHorizontales7.add(jugarFrame.getOp734());
        labelsHorizontales7.add(jugarFrame.getOp745());
        labelsHorizontales7.add(jugarFrame.getOp756());
        labelsHorizontales7.add(jugarFrame.getOp767());
        nonLabelsHorizontales7.add(jugarFrame.getOp778());
        nonLabelsHorizontales7.add(jugarFrame.getOp801());
        nonLabelsHorizontales7.add(jugarFrame.getOp812());
        nonLabelsHorizontales7.add(jugarFrame.getOp823());
        nonLabelsHorizontales7.add(jugarFrame.getOp834());
        nonLabelsHorizontales7.add(jugarFrame.getOp845());
        nonLabelsHorizontales7.add(jugarFrame.getOp856());
        nonLabelsHorizontales7.add(jugarFrame.getOp867());
        nonLabelsHorizontales7.add(jugarFrame.getOp878());
        
        nonLabelsVerticales7.add(jugarFrame.getOp00V());        
        nonLabelsVerticales7.add(jugarFrame.getOp01V());
        nonLabelsVerticales7.add(jugarFrame.getOp02V());
        nonLabelsVerticales7.add(jugarFrame.getOp03V());
        nonLabelsVerticales7.add(jugarFrame.getOp04V());
        nonLabelsVerticales7.add(jugarFrame.getOp05V());
        nonLabelsVerticales7.add(jugarFrame.getOp06V());
        nonLabelsVerticales7.add(jugarFrame.getOp07V());
        nonLabelsVerticales7.add(jugarFrame.getOp08V());
        nonLabelsVerticales7.add(jugarFrame.getOp10V());        
        labelsVerticales7.add(jugarFrame.getOp11V());
        labelsVerticales7.add(jugarFrame.getOp12V());
        labelsVerticales7.add(jugarFrame.getOp13V());
        labelsVerticales7.add(jugarFrame.getOp14V());
        labelsVerticales7.add(jugarFrame.getOp15V());
        labelsVerticales7.add(jugarFrame.getOp16V());
        labelsVerticales7.add(jugarFrame.getOp17V());
        nonLabelsVerticales7.add(jugarFrame.getOp18V());
        nonLabelsVerticales7.add(jugarFrame.getOp20V());        
        labelsVerticales7.add(jugarFrame.getOp21V());
        labelsVerticales7.add(jugarFrame.getOp22V());
        labelsVerticales7.add(jugarFrame.getOp23V());
        labelsVerticales7.add(jugarFrame.getOp24V());
        labelsVerticales7.add(jugarFrame.getOp25V());
        labelsVerticales7.add(jugarFrame.getOp26V());
        labelsVerticales7.add(jugarFrame.getOp27V());
        nonLabelsVerticales7.add(jugarFrame.getOp28V());
        nonLabelsVerticales7.add(jugarFrame.getOp30V());        
        labelsVerticales7.add(jugarFrame.getOp31V());
        labelsVerticales7.add(jugarFrame.getOp32V());
        labelsVerticales7.add(jugarFrame.getOp33V());
        labelsVerticales7.add(jugarFrame.getOp34V());
        labelsVerticales7.add(jugarFrame.getOp35V());
        labelsVerticales7.add(jugarFrame.getOp36V());
        labelsVerticales7.add(jugarFrame.getOp37V());
        nonLabelsVerticales7.add(jugarFrame.getOp38V());
        nonLabelsVerticales7.add(jugarFrame.getOp40V());        
        labelsVerticales7.add(jugarFrame.getOp41V());
        labelsVerticales7.add(jugarFrame.getOp42V());
        labelsVerticales7.add(jugarFrame.getOp43V());
        labelsVerticales7.add(jugarFrame.getOp44V());
        labelsVerticales7.add(jugarFrame.getOp45V());
        labelsVerticales7.add(jugarFrame.getOp46V());
        labelsVerticales7.add(jugarFrame.getOp47V());
        nonLabelsVerticales7.add(jugarFrame.getOp48V());
        nonLabelsVerticales7.add(jugarFrame.getOp50V());        
        labelsVerticales7.add(jugarFrame.getOp51V());
        labelsVerticales7.add(jugarFrame.getOp52V());
        labelsVerticales7.add(jugarFrame.getOp53V());
        labelsVerticales7.add(jugarFrame.getOp54V());
        labelsVerticales7.add(jugarFrame.getOp55V());
        labelsVerticales7.add(jugarFrame.getOp56V());
        labelsVerticales7.add(jugarFrame.getOp57V());
        nonLabelsVerticales7.add(jugarFrame.getOp58V());
        nonLabelsVerticales7.add(jugarFrame.getOp60V());        
        labelsVerticales7.add(jugarFrame.getOp61V());
        labelsVerticales7.add(jugarFrame.getOp62V());
        labelsVerticales7.add(jugarFrame.getOp63V());
        labelsVerticales7.add(jugarFrame.getOp64V());
        labelsVerticales7.add(jugarFrame.getOp65V());
        labelsVerticales7.add(jugarFrame.getOp66V());
        labelsVerticales7.add(jugarFrame.getOp67V());
        nonLabelsVerticales7.add(jugarFrame.getOp68V());
        nonLabelsVerticales7.add(jugarFrame.getOp70V());        
        nonLabelsVerticales7.add(jugarFrame.getOp71V());
        nonLabelsVerticales7.add(jugarFrame.getOp72V());
        nonLabelsVerticales7.add(jugarFrame.getOp73V());
        nonLabelsVerticales7.add(jugarFrame.getOp74V());
        nonLabelsVerticales7.add(jugarFrame.getOp75V());
        nonLabelsVerticales7.add(jugarFrame.getOp76V());
        nonLabelsVerticales7.add(jugarFrame.getOp77V());
        nonLabelsVerticales7.add(jugarFrame.getOp78V());
    }
        
    
    private void fill8(){
        casillas8 = new javax.swing.JButton[8][8];
        nonCasillas8.add(jugarFrame.getBtn00());
        nonCasillas8.add(jugarFrame.getBtn01());
        nonCasillas8.add(jugarFrame.getBtn02());
        nonCasillas8.add(jugarFrame.getBtn03());
        nonCasillas8.add(jugarFrame.getBtn04());
        nonCasillas8.add(jugarFrame.getBtn05());
        nonCasillas8.add(jugarFrame.getBtn06());
        nonCasillas8.add(jugarFrame.getBtn07());
        nonCasillas8.add(jugarFrame.getBtn08());
        nonCasillas8.add(jugarFrame.getBtn10());
        nonCasillas8.add(jugarFrame.getBtn20());
        nonCasillas8.add(jugarFrame.getBtn30());
        nonCasillas8.add(jugarFrame.getBtn40());
        nonCasillas8.add(jugarFrame.getBtn50());
        nonCasillas8.add(jugarFrame.getBtn60());
        nonCasillas8.add(jugarFrame.getBtn70());
        nonCasillas8.add(jugarFrame.getBtn80());
        
        casillas8[0][0] = jugarFrame.getBtn11();
        casillas8[0][1] = jugarFrame.getBtn12();
        casillas8[0][2] = jugarFrame.getBtn13();
        casillas8[0][3] = jugarFrame.getBtn14();
        casillas8[0][4] = jugarFrame.getBtn15();
        casillas8[0][5] = jugarFrame.getBtn16();
        casillas8[0][6] = jugarFrame.getBtn17();
        casillas8[0][7] = jugarFrame.getBtn18();
        casillas8[1][0] = jugarFrame.getBtn21();
        casillas8[1][1] = jugarFrame.getBtn22();
        casillas8[1][2] = jugarFrame.getBtn23();
        casillas8[1][3] = jugarFrame.getBtn24();
        casillas8[1][4] = jugarFrame.getBtn25();
        casillas8[1][5] = jugarFrame.getBtn26();
        casillas8[1][6] = jugarFrame.getBtn27();
        casillas8[1][7] = jugarFrame.getBtn28();
        casillas8[2][0] = jugarFrame.getBtn31();
        casillas8[2][1] = jugarFrame.getBtn32();
        casillas8[2][2] = jugarFrame.getBtn33();
        casillas8[2][3] = jugarFrame.getBtn34();
        casillas8[2][4] = jugarFrame.getBtn35();
        casillas8[2][5] = jugarFrame.getBtn36();
        casillas8[2][6] = jugarFrame.getBtn37();
        casillas8[2][7] = jugarFrame.getBtn38();
        casillas8[3][0] = jugarFrame.getBtn41();
        casillas8[3][1] = jugarFrame.getBtn42();
        casillas8[3][2] = jugarFrame.getBtn43();
        casillas8[3][3] = jugarFrame.getBtn44();
        casillas8[3][4] = jugarFrame.getBtn45();
        casillas8[3][5] = jugarFrame.getBtn46();
        casillas8[3][6] = jugarFrame.getBtn47();
        casillas8[3][7] = jugarFrame.getBtn48();
        casillas8[4][0] = jugarFrame.getBtn51();
        casillas8[4][1] = jugarFrame.getBtn52();
        casillas8[4][2] = jugarFrame.getBtn53();
        casillas8[4][3] = jugarFrame.getBtn54();
        casillas8[4][4] = jugarFrame.getBtn55();
        casillas8[4][5] = jugarFrame.getBtn56();
        casillas8[4][6] = jugarFrame.getBtn57();
        casillas8[4][7] = jugarFrame.getBtn58();
        casillas8[5][0] = jugarFrame.getBtn61();
        casillas8[5][1] = jugarFrame.getBtn62();
        casillas8[5][2] = jugarFrame.getBtn63();
        casillas8[5][3] = jugarFrame.getBtn64();
        casillas8[5][4] = jugarFrame.getBtn65();
        casillas8[5][5] = jugarFrame.getBtn66();
        casillas8[5][6] = jugarFrame.getBtn67();
        casillas8[5][7] = jugarFrame.getBtn68();
        casillas8[6][0] = jugarFrame.getBtn71();
        casillas8[6][1] = jugarFrame.getBtn72();
        casillas8[6][2] = jugarFrame.getBtn73();
        casillas8[6][3] = jugarFrame.getBtn74();
        casillas8[6][4] = jugarFrame.getBtn75();
        casillas8[6][5] = jugarFrame.getBtn76();
        casillas8[6][6] = jugarFrame.getBtn77();
        casillas8[6][7] = jugarFrame.getBtn78();
        casillas8[7][0] = jugarFrame.getBtn81();
        casillas8[7][1] = jugarFrame.getBtn82();
        casillas8[7][2] = jugarFrame.getBtn83();
        casillas8[7][3] = jugarFrame.getBtn84();
        casillas8[7][4] = jugarFrame.getBtn85();
        casillas8[7][5] = jugarFrame.getBtn86();
        casillas8[7][6] = jugarFrame.getBtn87();
        casillas8[7][7] = jugarFrame.getBtn88();
       
        nonLabelsHorizontales8.add(jugarFrame.getOp001());
        nonLabelsHorizontales8.add(jugarFrame.getOp012());
        nonLabelsHorizontales8.add(jugarFrame.getOp023());
        nonLabelsHorizontales8.add(jugarFrame.getOp034());
        nonLabelsHorizontales8.add(jugarFrame.getOp045());
        nonLabelsHorizontales8.add(jugarFrame.getOp056());
        nonLabelsHorizontales8.add(jugarFrame.getOp067());
        nonLabelsHorizontales8.add(jugarFrame.getOp078());
        nonLabelsHorizontales8.add(jugarFrame.getOp101());
        labelsHorizontales8.add(jugarFrame.getOp112());
        labelsHorizontales8.add(jugarFrame.getOp123());
        labelsHorizontales8.add(jugarFrame.getOp134());
        labelsHorizontales8.add(jugarFrame.getOp145());
        labelsHorizontales8.add(jugarFrame.getOp156());
        labelsHorizontales8.add(jugarFrame.getOp167());
        labelsHorizontales8.add(jugarFrame.getOp178());
        nonLabelsHorizontales8.add(jugarFrame.getOp201());
        labelsHorizontales8.add(jugarFrame.getOp212());
        labelsHorizontales8.add(jugarFrame.getOp223());
        labelsHorizontales8.add(jugarFrame.getOp234());
        labelsHorizontales8.add(jugarFrame.getOp245());
        labelsHorizontales8.add(jugarFrame.getOp256());
        labelsHorizontales8.add(jugarFrame.getOp267());
        labelsHorizontales8.add(jugarFrame.getOp278());
        nonLabelsHorizontales8.add(jugarFrame.getOp301());
        labelsHorizontales8.add(jugarFrame.getOp312());
        labelsHorizontales8.add(jugarFrame.getOp323());
        labelsHorizontales8.add(jugarFrame.getOp334());
        labelsHorizontales8.add(jugarFrame.getOp345());
        labelsHorizontales8.add(jugarFrame.getOp356());
        labelsHorizontales8.add(jugarFrame.getOp367());
        labelsHorizontales8.add(jugarFrame.getOp378());
        nonLabelsHorizontales8.add(jugarFrame.getOp401());
        labelsHorizontales8.add(jugarFrame.getOp412());
        labelsHorizontales8.add(jugarFrame.getOp423());
        labelsHorizontales8.add(jugarFrame.getOp434());
        labelsHorizontales8.add(jugarFrame.getOp445());
        labelsHorizontales8.add(jugarFrame.getOp456());
        labelsHorizontales8.add(jugarFrame.getOp467());
        labelsHorizontales8.add(jugarFrame.getOp478());
        nonLabelsHorizontales8.add(jugarFrame.getOp501());
        labelsHorizontales8.add(jugarFrame.getOp512());        
        labelsHorizontales8.add(jugarFrame.getOp523());
        labelsHorizontales8.add(jugarFrame.getOp534());
        labelsHorizontales8.add(jugarFrame.getOp545());
        labelsHorizontales8.add(jugarFrame.getOp556());
        labelsHorizontales8.add(jugarFrame.getOp567());
        labelsHorizontales8.add(jugarFrame.getOp578());
        nonLabelsHorizontales8.add(jugarFrame.getOp601());
        labelsHorizontales8.add(jugarFrame.getOp612());
        labelsHorizontales8.add(jugarFrame.getOp623());
        labelsHorizontales8.add(jugarFrame.getOp634());
        labelsHorizontales8.add(jugarFrame.getOp645());
        labelsHorizontales8.add(jugarFrame.getOp656());
        labelsHorizontales8.add(jugarFrame.getOp667());
        labelsHorizontales8.add(jugarFrame.getOp678());
        nonLabelsHorizontales8.add(jugarFrame.getOp701());
        labelsHorizontales8.add(jugarFrame.getOp712());
        labelsHorizontales8.add(jugarFrame.getOp723());
        labelsHorizontales8.add(jugarFrame.getOp734());
        labelsHorizontales8.add(jugarFrame.getOp745());
        labelsHorizontales8.add(jugarFrame.getOp756());
        labelsHorizontales8.add(jugarFrame.getOp767());
        labelsHorizontales8.add(jugarFrame.getOp778());
        nonLabelsHorizontales8.add(jugarFrame.getOp801());
        labelsHorizontales8.add(jugarFrame.getOp812());
        labelsHorizontales8.add(jugarFrame.getOp823());
        labelsHorizontales8.add(jugarFrame.getOp834());
        labelsHorizontales8.add(jugarFrame.getOp845());
        labelsHorizontales8.add(jugarFrame.getOp856());
        labelsHorizontales8.add(jugarFrame.getOp867());
        labelsHorizontales8.add(jugarFrame.getOp878());
        
        nonLabelsVerticales8.add(jugarFrame.getOp00V());        
        nonLabelsVerticales8.add(jugarFrame.getOp01V());
        nonLabelsVerticales8.add(jugarFrame.getOp02V());
        nonLabelsVerticales8.add(jugarFrame.getOp03V());
        nonLabelsVerticales8.add(jugarFrame.getOp04V());
        nonLabelsVerticales8.add(jugarFrame.getOp05V());
        nonLabelsVerticales8.add(jugarFrame.getOp06V());
        nonLabelsVerticales8.add(jugarFrame.getOp07V());
        nonLabelsVerticales8.add(jugarFrame.getOp08V());
        nonLabelsVerticales8.add(jugarFrame.getOp10V());        
        labelsVerticales8.add(jugarFrame.getOp11V());
        labelsVerticales8.add(jugarFrame.getOp12V());
        labelsVerticales8.add(jugarFrame.getOp13V());
        labelsVerticales8.add(jugarFrame.getOp14V());
        labelsVerticales8.add(jugarFrame.getOp15V());
        labelsVerticales8.add(jugarFrame.getOp16V());
        labelsVerticales8.add(jugarFrame.getOp17V());
        labelsVerticales8.add(jugarFrame.getOp18V());
        nonLabelsVerticales8.add(jugarFrame.getOp20V());        
        labelsVerticales8.add(jugarFrame.getOp21V());
        labelsVerticales8.add(jugarFrame.getOp22V());
        labelsVerticales8.add(jugarFrame.getOp23V());
        labelsVerticales8.add(jugarFrame.getOp24V());
        labelsVerticales8.add(jugarFrame.getOp25V());
        labelsVerticales8.add(jugarFrame.getOp26V());
        labelsVerticales8.add(jugarFrame.getOp27V());
        labelsVerticales8.add(jugarFrame.getOp28V());
        nonLabelsVerticales8.add(jugarFrame.getOp30V());        
        labelsVerticales8.add(jugarFrame.getOp31V());
        labelsVerticales8.add(jugarFrame.getOp32V());
        labelsVerticales8.add(jugarFrame.getOp33V());
        labelsVerticales8.add(jugarFrame.getOp34V());
        labelsVerticales8.add(jugarFrame.getOp35V());
        labelsVerticales8.add(jugarFrame.getOp36V());
        labelsVerticales8.add(jugarFrame.getOp37V());
        labelsVerticales8.add(jugarFrame.getOp38V());
        nonLabelsVerticales8.add(jugarFrame.getOp40V());        
        labelsVerticales8.add(jugarFrame.getOp41V());
        labelsVerticales8.add(jugarFrame.getOp42V());
        labelsVerticales8.add(jugarFrame.getOp43V());
        labelsVerticales8.add(jugarFrame.getOp44V());
        labelsVerticales8.add(jugarFrame.getOp45V());
        labelsVerticales8.add(jugarFrame.getOp46V());
        labelsVerticales8.add(jugarFrame.getOp47V());
        labelsVerticales8.add(jugarFrame.getOp48V());
        nonLabelsVerticales8.add(jugarFrame.getOp50V());        
        labelsVerticales8.add(jugarFrame.getOp51V());
        labelsVerticales8.add(jugarFrame.getOp52V());
        labelsVerticales8.add(jugarFrame.getOp53V());
        labelsVerticales8.add(jugarFrame.getOp54V());
        labelsVerticales8.add(jugarFrame.getOp55V());
        labelsVerticales8.add(jugarFrame.getOp56V());
        labelsVerticales8.add(jugarFrame.getOp57V());
        labelsVerticales8.add(jugarFrame.getOp58V());
        nonLabelsVerticales8.add(jugarFrame.getOp60V());        
        labelsVerticales8.add(jugarFrame.getOp61V());
        labelsVerticales8.add(jugarFrame.getOp62V());
        labelsVerticales8.add(jugarFrame.getOp63V());
        labelsVerticales8.add(jugarFrame.getOp64V());
        labelsVerticales8.add(jugarFrame.getOp65V());
        labelsVerticales8.add(jugarFrame.getOp66V());
        labelsVerticales8.add(jugarFrame.getOp67V());
        labelsVerticales8.add(jugarFrame.getOp68V());
        nonLabelsVerticales8.add(jugarFrame.getOp70V());        
        labelsVerticales8.add(jugarFrame.getOp71V());
        labelsVerticales8.add(jugarFrame.getOp72V());
        labelsVerticales8.add(jugarFrame.getOp73V());
        labelsVerticales8.add(jugarFrame.getOp74V());
        labelsVerticales8.add(jugarFrame.getOp75V());
        labelsVerticales8.add(jugarFrame.getOp76V());
        labelsVerticales8.add(jugarFrame.getOp77V());
        labelsVerticales8.add(jugarFrame.getOp78V());
    }
    
    private void fill9(){
        casillas9 = new javax.swing.JButton[9][9];
        
        casillas9[0][0] = jugarFrame.getBtn00();
        casillas9[0][1] = jugarFrame.getBtn01();
        casillas9[0][2] = jugarFrame.getBtn02();
        casillas9[0][3] = jugarFrame.getBtn03();
        casillas9[0][4] = jugarFrame.getBtn04();
        casillas9[0][5] = jugarFrame.getBtn05();
        casillas9[0][6] = jugarFrame.getBtn06();
        casillas9[0][7] = jugarFrame.getBtn07();
        casillas9[0][8] = jugarFrame.getBtn08();
        casillas9[1][0] = jugarFrame.getBtn10();
        casillas9[1][1] = jugarFrame.getBtn11();
        casillas9[1][2] = jugarFrame.getBtn12();
        casillas9[1][3] = jugarFrame.getBtn13();
        casillas9[1][4] = jugarFrame.getBtn14();
        casillas9[1][5] = jugarFrame.getBtn15();
        casillas9[1][6] = jugarFrame.getBtn16();
        casillas9[1][7] = jugarFrame.getBtn17();
        casillas9[1][8] = jugarFrame.getBtn18();
        casillas9[2][0] = jugarFrame.getBtn20();
        casillas9[2][1] = jugarFrame.getBtn21();
        casillas9[2][2] = jugarFrame.getBtn22();
        casillas9[2][3] = jugarFrame.getBtn23();
        casillas9[2][4] = jugarFrame.getBtn24();
        casillas9[2][5] = jugarFrame.getBtn25();
        casillas9[2][6] = jugarFrame.getBtn26();
        casillas9[2][7] = jugarFrame.getBtn27();
        casillas9[2][8] = jugarFrame.getBtn28();
        casillas9[3][0] = jugarFrame.getBtn30();
        casillas9[3][1] = jugarFrame.getBtn31();
        casillas9[3][2] = jugarFrame.getBtn32();
        casillas9[3][3] = jugarFrame.getBtn33();
        casillas9[3][4] = jugarFrame.getBtn34();
        casillas9[3][5] = jugarFrame.getBtn35();
        casillas9[3][6] = jugarFrame.getBtn36();
        casillas9[3][7] = jugarFrame.getBtn37();
        casillas9[3][8] = jugarFrame.getBtn38();
        casillas9[4][0] = jugarFrame.getBtn40();
        casillas9[4][1] = jugarFrame.getBtn41();
        casillas9[4][2] = jugarFrame.getBtn42();
        casillas9[4][3] = jugarFrame.getBtn43();
        casillas9[4][4] = jugarFrame.getBtn44();
        casillas9[4][5] = jugarFrame.getBtn45();
        casillas9[4][6] = jugarFrame.getBtn46();
        casillas9[4][7] = jugarFrame.getBtn47();
        casillas9[4][8] = jugarFrame.getBtn48();
        casillas9[5][0] = jugarFrame.getBtn50();
        casillas9[5][1] = jugarFrame.getBtn51();
        casillas9[5][2] = jugarFrame.getBtn52();
        casillas9[5][3] = jugarFrame.getBtn53();
        casillas9[5][4] = jugarFrame.getBtn54();
        casillas9[5][5] = jugarFrame.getBtn55();
        casillas9[5][6] = jugarFrame.getBtn56();
        casillas9[5][7] = jugarFrame.getBtn57();
        casillas9[5][8] = jugarFrame.getBtn58();
        casillas9[6][0] = jugarFrame.getBtn60();
        casillas9[6][1] = jugarFrame.getBtn61();
        casillas9[6][2] = jugarFrame.getBtn62();
        casillas9[6][3] = jugarFrame.getBtn63();
        casillas9[6][4] = jugarFrame.getBtn64();
        casillas9[6][5] = jugarFrame.getBtn65();
        casillas9[6][6] = jugarFrame.getBtn66();
        casillas9[6][7] = jugarFrame.getBtn67();
        casillas9[6][8] = jugarFrame.getBtn68();
        casillas9[7][0] = jugarFrame.getBtn70();
        casillas9[7][1] = jugarFrame.getBtn71();
        casillas9[7][2] = jugarFrame.getBtn72();
        casillas9[7][3] = jugarFrame.getBtn73();
        casillas9[7][4] = jugarFrame.getBtn74();
        casillas9[7][5] = jugarFrame.getBtn75();
        casillas9[7][6] = jugarFrame.getBtn76();
        casillas9[7][7] = jugarFrame.getBtn77();
        casillas9[7][8] = jugarFrame.getBtn78();
        casillas9[8][0] = jugarFrame.getBtn80();
        casillas9[8][1] = jugarFrame.getBtn81();
        casillas9[8][2] = jugarFrame.getBtn82();
        casillas9[8][3] = jugarFrame.getBtn83();
        casillas9[8][4] = jugarFrame.getBtn84();
        casillas9[8][5] = jugarFrame.getBtn85();
        casillas9[8][6] = jugarFrame.getBtn86();
        casillas9[8][7] = jugarFrame.getBtn87();
        casillas9[8][8] = jugarFrame.getBtn88();
       
        labelsHorizontales9.add(jugarFrame.getOp001());
        labelsHorizontales9.add(jugarFrame.getOp012());
        labelsHorizontales9.add(jugarFrame.getOp023());
        labelsHorizontales9.add(jugarFrame.getOp034());
        labelsHorizontales9.add(jugarFrame.getOp045());
        labelsHorizontales9.add(jugarFrame.getOp056());
        labelsHorizontales9.add(jugarFrame.getOp067());
        labelsHorizontales9.add(jugarFrame.getOp078());
        labelsHorizontales9.add(jugarFrame.getOp101());
        labelsHorizontales9.add(jugarFrame.getOp112());
        labelsHorizontales9.add(jugarFrame.getOp123());
        labelsHorizontales9.add(jugarFrame.getOp134());
        labelsHorizontales9.add(jugarFrame.getOp145());
        labelsHorizontales9.add(jugarFrame.getOp156());
        labelsHorizontales9.add(jugarFrame.getOp167());
        labelsHorizontales9.add(jugarFrame.getOp178());
        labelsHorizontales9.add(jugarFrame.getOp201());
        labelsHorizontales9.add(jugarFrame.getOp212());
        labelsHorizontales9.add(jugarFrame.getOp223());
        labelsHorizontales9.add(jugarFrame.getOp234());
        labelsHorizontales9.add(jugarFrame.getOp245());
        labelsHorizontales9.add(jugarFrame.getOp256());
        labelsHorizontales9.add(jugarFrame.getOp267());
        labelsHorizontales9.add(jugarFrame.getOp278());
        labelsHorizontales9.add(jugarFrame.getOp301());
        labelsHorizontales9.add(jugarFrame.getOp312());
        labelsHorizontales9.add(jugarFrame.getOp323());
        labelsHorizontales9.add(jugarFrame.getOp334());
        labelsHorizontales9.add(jugarFrame.getOp345());
        labelsHorizontales9.add(jugarFrame.getOp356());
        labelsHorizontales9.add(jugarFrame.getOp367());
        labelsHorizontales9.add(jugarFrame.getOp378());
        labelsHorizontales9.add(jugarFrame.getOp401());
        labelsHorizontales9.add(jugarFrame.getOp412());
        labelsHorizontales9.add(jugarFrame.getOp423());
        labelsHorizontales9.add(jugarFrame.getOp434());
        labelsHorizontales9.add(jugarFrame.getOp445());
        labelsHorizontales9.add(jugarFrame.getOp456());
        labelsHorizontales9.add(jugarFrame.getOp467());
        labelsHorizontales9.add(jugarFrame.getOp478());
        labelsHorizontales9.add(jugarFrame.getOp501());
        labelsHorizontales9.add(jugarFrame.getOp512());        
        labelsHorizontales9.add(jugarFrame.getOp523());
        labelsHorizontales9.add(jugarFrame.getOp534());
        labelsHorizontales9.add(jugarFrame.getOp545());
        labelsHorizontales9.add(jugarFrame.getOp556());
        labelsHorizontales9.add(jugarFrame.getOp567());
        labelsHorizontales9.add(jugarFrame.getOp578());
        labelsHorizontales9.add(jugarFrame.getOp601());
        labelsHorizontales9.add(jugarFrame.getOp612());
        labelsHorizontales9.add(jugarFrame.getOp623());
        labelsHorizontales9.add(jugarFrame.getOp634());
        labelsHorizontales9.add(jugarFrame.getOp645());
        labelsHorizontales9.add(jugarFrame.getOp656());
        labelsHorizontales9.add(jugarFrame.getOp667());
        labelsHorizontales9.add(jugarFrame.getOp678());
        labelsHorizontales9.add(jugarFrame.getOp701());
        labelsHorizontales9.add(jugarFrame.getOp712());
        labelsHorizontales9.add(jugarFrame.getOp723());
        labelsHorizontales9.add(jugarFrame.getOp734());
        labelsHorizontales9.add(jugarFrame.getOp745());
        labelsHorizontales9.add(jugarFrame.getOp756());
        labelsHorizontales9.add(jugarFrame.getOp767());
        labelsHorizontales9.add(jugarFrame.getOp778());
        labelsHorizontales9.add(jugarFrame.getOp801());
        labelsHorizontales9.add(jugarFrame.getOp812());
        labelsHorizontales9.add(jugarFrame.getOp823());
        labelsHorizontales9.add(jugarFrame.getOp834());
        labelsHorizontales9.add(jugarFrame.getOp845());
        labelsHorizontales9.add(jugarFrame.getOp856());
        labelsHorizontales9.add(jugarFrame.getOp867());
        labelsHorizontales9.add(jugarFrame.getOp878());
        
        labelsVerticales9.add(jugarFrame.getOp00V());        
        labelsVerticales9.add(jugarFrame.getOp01V());
        labelsVerticales9.add(jugarFrame.getOp02V());
        labelsVerticales9.add(jugarFrame.getOp03V());
        labelsVerticales9.add(jugarFrame.getOp04V());
        labelsVerticales9.add(jugarFrame.getOp05V());
        labelsVerticales9.add(jugarFrame.getOp06V());
        labelsVerticales9.add(jugarFrame.getOp07V());
        labelsVerticales9.add(jugarFrame.getOp08V());
        labelsVerticales9.add(jugarFrame.getOp10V());        
        labelsVerticales9.add(jugarFrame.getOp11V());
        labelsVerticales9.add(jugarFrame.getOp12V());
        labelsVerticales9.add(jugarFrame.getOp13V());
        labelsVerticales9.add(jugarFrame.getOp14V());
        labelsVerticales9.add(jugarFrame.getOp15V());
        labelsVerticales9.add(jugarFrame.getOp16V());
        labelsVerticales9.add(jugarFrame.getOp17V());
        labelsVerticales9.add(jugarFrame.getOp18V());
        labelsVerticales9.add(jugarFrame.getOp20V());        
        labelsVerticales9.add(jugarFrame.getOp21V());
        labelsVerticales9.add(jugarFrame.getOp22V());
        labelsVerticales9.add(jugarFrame.getOp23V());
        labelsVerticales9.add(jugarFrame.getOp24V());
        labelsVerticales9.add(jugarFrame.getOp25V());
        labelsVerticales9.add(jugarFrame.getOp26V());
        labelsVerticales9.add(jugarFrame.getOp27V());
        labelsVerticales9.add(jugarFrame.getOp28V());
        labelsVerticales9.add(jugarFrame.getOp30V());        
        labelsVerticales9.add(jugarFrame.getOp31V());
        labelsVerticales9.add(jugarFrame.getOp32V());
        labelsVerticales9.add(jugarFrame.getOp33V());
        labelsVerticales9.add(jugarFrame.getOp34V());
        labelsVerticales9.add(jugarFrame.getOp35V());
        labelsVerticales9.add(jugarFrame.getOp36V());
        labelsVerticales9.add(jugarFrame.getOp37V());
        labelsVerticales9.add(jugarFrame.getOp38V());
        labelsVerticales9.add(jugarFrame.getOp40V());        
        labelsVerticales9.add(jugarFrame.getOp41V());
        labelsVerticales9.add(jugarFrame.getOp42V());
        labelsVerticales9.add(jugarFrame.getOp43V());
        labelsVerticales9.add(jugarFrame.getOp44V());
        labelsVerticales9.add(jugarFrame.getOp45V());
        labelsVerticales9.add(jugarFrame.getOp46V());
        labelsVerticales9.add(jugarFrame.getOp47V());
        labelsVerticales9.add(jugarFrame.getOp48V());
        labelsVerticales9.add(jugarFrame.getOp50V());        
        labelsVerticales9.add(jugarFrame.getOp51V());
        labelsVerticales9.add(jugarFrame.getOp52V());
        labelsVerticales9.add(jugarFrame.getOp53V());
        labelsVerticales9.add(jugarFrame.getOp54V());
        labelsVerticales9.add(jugarFrame.getOp55V());
        labelsVerticales9.add(jugarFrame.getOp56V());
        labelsVerticales9.add(jugarFrame.getOp57V());
        labelsVerticales9.add(jugarFrame.getOp58V());
        labelsVerticales9.add(jugarFrame.getOp60V());        
        labelsVerticales9.add(jugarFrame.getOp61V());
        labelsVerticales9.add(jugarFrame.getOp62V());
        labelsVerticales9.add(jugarFrame.getOp63V());
        labelsVerticales9.add(jugarFrame.getOp64V());
        labelsVerticales9.add(jugarFrame.getOp65V());
        labelsVerticales9.add(jugarFrame.getOp66V());
        labelsVerticales9.add(jugarFrame.getOp67V());
        labelsVerticales9.add(jugarFrame.getOp68V());
        labelsVerticales9.add(jugarFrame.getOp70V());        
        labelsVerticales9.add(jugarFrame.getOp71V());
        labelsVerticales9.add(jugarFrame.getOp72V());
        labelsVerticales9.add(jugarFrame.getOp73V());
        labelsVerticales9.add(jugarFrame.getOp74V());
        labelsVerticales9.add(jugarFrame.getOp75V());
        labelsVerticales9.add(jugarFrame.getOp76V());
        labelsVerticales9.add(jugarFrame.getOp77V());
        labelsVerticales9.add(jugarFrame.getOp78V());
    }
    /**
     * Esta función mueve el panel de números de lado dependiendo de la configuración.
     */
     public void moveButtonPanel(){
           if (!Configuracion.getLado()){
                 jugarFrame.getNumbersPanel().setLocation(5, 100);
            } else {
                  jugarFrame.getNumbersPanel().setLocation(710, 60);
            }
     }
     
     public void setButtonPanel(){
           if (Configuracion.getTamaño() != 9) {
                  jugarFrame.getBtn9().setVisible(false);
            }
            if (Configuracion.getTamaño() <8){
                  jugarFrame.getBtn8().setVisible(false);
            }
            if (Configuracion.getTamaño() <= 6){
                  jugarFrame.getBtn7().setVisible(false);
            }
            if (Configuracion.getTamaño() == 5){
                 jugarFrame.getBtn6().setVisible(false);
            }
     }
     
      private Menu menu = new Menu();
      /**
     * Método que inicia el reloj
     * @param window
     */
      public void startClock(Component window){
            ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                  int tiempo = count;
                  int hours = tiempo / 3600;
                  tiempo %= 3600;
                  int minutes = tiempo / 60;
                  int seconds = tiempo % 60;
                  jugarFrame.getHorasTXT().setText(Integer.toString(hours)); // calcula el número de horas, minutos y segundos que deben ser desplegados
                  jugarFrame.getMinutosTXT().setText(Integer.toString(minutes));
                  jugarFrame.getSegundosTXT().setText(Integer.toString(seconds));
                  count ++; // incrementa el valor del contador cada segundo
            }
        };
        stopwatch = new Timer(delay, action);
        stopwatch.setInitialDelay(0);
        stopwatch.start(); // empieza el reloj
      }
      // esta función registra la victoria del juego y la escribe en la lista de top 10
      private void RegisterWin(){
            if (Configuracion.getReloj().equals("Sí") || Configuracion.getReloj().equals("Timer")){
                  stopwatch.stop(); // detiene el contador
                  int horas; // obtiene el tiempo actual
                  int minutos;
                  int segundos;
                  int tiemponow;
                  horas = Integer.parseInt(jugarFrame.getHorasTXT().getText());
                  minutos = Integer.parseInt(jugarFrame.getMinutosTXT().getText());
                  segundos = Integer.parseInt(jugarFrame.getSegundosTXT().getText());
                  tiemponow = horas *3600 + minutos * 60 + segundos;
                  
                  if (Configuracion.getReloj().equals("Timer")){ // si el reloj era un timer, el reloj tiempo actual es lo que falta, no lo que se duró
                        if (Configuracion.getNivel().equals("Multinivel") && !nivel.equals("Fácil")) {
                              int tiempoantes = horasMulti * 3600 + minutosMulti * 60 + segundosMulti;
                              tiemponow = tiempoantes - tiemponow;
                        } else {
                              int horasog = Integer.parseInt(horassave);
                              int minutosog = Integer.parseInt(minutossave);
                              int segundosog = Integer.parseInt(segundossave);
                              int tiempoog = horasog * 3600 + minutosog *60+ segundosog;
                              tiemponow  = tiempoog - tiemponow; // se obtiene el tiempo que se duró al restar el original con lo que queda.
                        }
                       
                  } else if (Configuracion.getNivel().equals("Multinivel") && !nivel.equals("Fácil")) {
                        int tiempoantes = horasMulti * 3600 + minutosMulti * 60+ segundosMulti;
                        tiemponow = tiemponow - tiempoantes;
                  }
                  // se crea una marca nueva
                  juego.saveMarcas(jugarFrame.getNombreTXT().getText(), tiemponow, jugarFrame.getNivelTXT().getText());
            }
            if (Configuracion.getNivel().equals("Multinivel")){
                  if (nivelCont != 3){
                        if (nivelCont == 1){
                              nivel = "Intermedio";
                        } else if (nivelCont == 2){
                              nivel = "Difícil";
                        }
                        jugarFrame.getNivelTXT().setText(nivel);
                        nivelCont++;
                        horasMulti = Integer.valueOf(jugarFrame.getHorasTXT().getText());
                        minutosMulti = Integer.valueOf(jugarFrame.getMinutosTXT().getText());
                        segundosMulti = Integer.valueOf(jugarFrame.getSegundosTXT().getText());
                        resetTablero(Configuracion.getTamaño());
                        establecerPartida();
                        establecerTablero(Configuracion.getTamaño());
                        juego = new Juego(jugarFrame.getNombreTXT().getText(), casillasPorTablero.get(Configuracion.getTamaño())); // se crea el nuevo juego
                        if (Configuracion.getReloj().equals("Sí") || Configuracion.getReloj().equals("Timer")){
                              stopwatch.start();
                        }
                        return;
                  }
            }
            started = false; // detiene el juego
            jugarFrame.getGuardarJuego().setEnabled(false); // desactiva todos los botones
            jugarFrame.getRehacerJugada().setEnabled(false);
            jugarFrame.getBorrarJugada().setEnabled(false);
            jugarFrame.getTerminarJuego().setEnabled(false);
            jugarFrame.getBorrarJuego().setEnabled(false);
      } 
    /**
     * Esta función inicia el timer
     * @param window
     */
      public void startTimer(Component window){
        ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(count == 0){ // si el timer llega a 0, debe pedir si se quiere continuar con el juego
                        stopwatch.stop(); // se detiene el stopwatch
                        
                        // se pide la confirmación
                        int input = JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "EL TIEMPO HA EXPIRADO.",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                  // 0=sí, 1=no, 
                        if (input == 0){  // si es sí, se continúa con el timer.
                              jugarFrame.getHorasTXT().setText(horassave);
                              jugarFrame.getMinutosTXT().setText(minutossave);
                              jugarFrame.getSegundosTXT().setText(segundossave);
                              count = Integer.valueOf(jugarFrame.getHorasTXT().getText()) * 3600 + Integer.valueOf(jugarFrame.getMinutosTXT().getText()) * 60 + Integer.valueOf(jugarFrame.getSegundosTXT().getText());
                              startClock(window);
                        } else{
                              started = false;
                        }
                } else { // si no ha terminado el timer, se despliegan los valores correctos y se decrementa el contador
                        int tiempo = count;
                        int hours = tiempo / 3600;
                        tiempo %= 3600;
                        int minutes = tiempo / 60;
                        int seconds = tiempo % 60;
                        jugarFrame.getHorasTXT().setText(Integer.toString(hours));
                        jugarFrame.getMinutosTXT().setText(Integer.toString(minutes));
                       jugarFrame.getSegundosTXT().setText(Integer.toString(seconds));
                        count --;
                }
            }
        };
        stopwatch = new Timer(delay, action);
        stopwatch.setInitialDelay(0);
        stopwatch.start();
    }
      public String getSelectedButtonText() {
        for (Enumeration<AbstractButton> buttons = jugarFrame.getOpciones().getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
      
      public void jugar(){
            fill5();
            fill6();
            fill7();
            fill8();
            fill9();
            
            casillasPorTablero.put(5, casillas5);
            casillasPorTablero.put(6, casillas6);
            casillasPorTablero.put(7, casillas7);
            casillasPorTablero.put(8, casillas8);
            casillasPorTablero.put(9, casillas9);
            
            nonCasillasPorTablero.put(5, nonCasillas5);
            nonCasillasPorTablero.put(6, nonCasillas6);
            nonCasillasPorTablero.put(7, nonCasillas7);
            nonCasillasPorTablero.put(8, nonCasillas8);
            nonCasillasPorTablero.put(9, nonCasillas9);
            
            labelsHorizontalesPorTablero.put(5, labelsHorizontales5);
            labelsHorizontalesPorTablero.put(6, labelsHorizontales6);
            labelsHorizontalesPorTablero.put(7, labelsHorizontales7);
            labelsHorizontalesPorTablero.put(8, labelsHorizontales8);
            labelsHorizontalesPorTablero.put(9, labelsHorizontales9);
            
            nonLabelsHorizontalesPorTablero.put(5, nonLabelsHorizontales5);
            nonLabelsHorizontalesPorTablero.put(6, nonLabelsHorizontales6);
            nonLabelsHorizontalesPorTablero.put(7, nonLabelsHorizontales7);
            nonLabelsHorizontalesPorTablero.put(8, nonLabelsHorizontales8);
            nonLabelsHorizontalesPorTablero.put(9, nonLabelsHorizontales9);
            
            labelsVerticalesPorTablero.put(5, labelsVerticales5);
            labelsVerticalesPorTablero.put(6, labelsVerticales6);
            labelsVerticalesPorTablero.put(7, labelsVerticales7);
            labelsVerticalesPorTablero.put(8, labelsVerticales8);
            labelsVerticalesPorTablero.put(9, labelsVerticales9);
            
            nonLabelsVerticalesPorTablero.put(5, nonLabelsVerticales5);
            nonLabelsVerticalesPorTablero.put(6, nonLabelsVerticales6);
            nonLabelsVerticalesPorTablero.put(7, nonLabelsVerticales7);
            nonLabelsVerticalesPorTablero.put(8, nonLabelsVerticales8);
            nonLabelsVerticalesPorTablero.put(9, nonLabelsVerticales9);
            
            if (Configuracion.getNivel().equals("Multinivel")){
                  nivel = "Fácil";
            } else {
                  nivel = Configuracion.getNivel();
            }
            nivelCont = 1;
            jugarFrame.getNivelTXT().setText(nivel); // se establece el nivel
            jugarFrame.setLayout(null); // esto se usa para poder mover el panel de botones.
            moveButtonPanel();
            setButtonPanel();
            
            
            count = 0;
            jugarFrame.getHorasTXT().setText("0");
            jugarFrame.getMinutosTXT().setText("0");
            jugarFrame.getSegundosTXT().setText("0");
            if (Configuracion.getReloj().equals("No")){ //  si no hay un reloj, se quita de la pantalla
                  jugarFrame.getRelojFrame().setVisible(false);
             } else if (Configuracion.getReloj().equals("Timer")) { // si hay un timer, se deja editar el tiempo
                  count = count = Reloj.getHorasInt() * 3600 + Reloj.getMinutosInt() * 60 + Reloj.getSegundosInt();
                  jugarFrame.getHorasTXT().setEditable(true);
                  jugarFrame.getMinutosTXT().setEditable(true);
                  jugarFrame.getSegundosTXT().setEditable(true);
                  jugarFrame.getHorasTXT().setText(Reloj.getHoras()); // se establecen los tiempos de la configuración
                  jugarFrame.getMinutosTXT().setText(Reloj.getMinutos());
                  jugarFrame.getSegundosTXT().setText(Reloj.getSegundos());
            }
            
            establecerPartida();
            jugarFrame.getIniciarJuego().setEnabled(true);
            jugarFrame.getNombreTXT().setEditable(true);
            jugarFrame.getGuardarJuego().setEnabled(false);
            jugarFrame.getRehacerJugada().setEnabled(false);
            jugarFrame.getBorrarJugada().setEnabled(false);
            jugarFrame.getTerminarJuego().setEnabled(false);
            jugarFrame.getBorrarJuego().setEnabled(false);
            jugarFrame.getPistaButton().setEnabled(false);
            
      }
      
      
      private String getSizeButton() {
        for (Enumeration<AbstractButton> buttons = configGUI.obtainSizeButton().getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
      
      public void configurar(){
            niveles.put("Fácil", configGUI.getFacilButton());
        niveles.put("Intermedio", configGUI.getIntermedioButton());
        niveles.put("Difícil", configGUI.getDificilButton());
        relojes.put("Sí", configGUI.getSiButton());
        relojes.put("Timer", configGUI.getTimerButton());
        relojes.put("No", configGUI.getNoButton());
        lados.put(false, configGUI.getIzquierdaButton());
        lados.put(true, configGUI.getDerechaButton());
        tamaños.put(5, configGUI.getCincoButton());
        tamaños.put(6,configGUI.getSeisButton());
        tamaños.put(7, configGUI.getSieteButton());
        tamaños.put(8, configGUI.getOchoButton());
        tamaños.put(9, configGUI.getNueveButton());
        if (!Configuracion.getNivel().equals("Multinivel")){
              niveles.get(Configuracion.getNivel()).setSelected(true); // obtenemos el objeto del boton respectivo al nivel y lo activamos.
        } else {
              configGUI.getMultinivelButton().setSelected(true);
        }
        
        relojes.get(Configuracion.getReloj()).setSelected(true); // obtenemos el objeto del boton respectivo al reloj y lo activamos.
        lados.get(Configuracion.getLado()).setSelected(true); // obtenemos el objeto del boton respectivo al lado y lo activamos.
        tamaños.get(Configuracion.getTamaño()).setSelected(true);
        if (Configuracion.getReloj().equals("No")){
            configGUI.getRelojFrame().setVisible(false);
        } else if (Configuracion.getReloj().equals("Sí")){
            configGUI.getRelojFrame().setVisible(true);
            configGUI.getHorasField().setEditable(false);
            configGUI.getMinutosField().setEditable(false);
            configGUI.getSegundosField().setEditable(false);
            configGUI.getHorasField().setText("0");
            configGUI.getMinutosField().setText("0");
            configGUI.getSegundosField().setText("0");
        } else {
            configGUI.getHorasField().setEditable(true);
            configGUI.getMinutosField().setEditable(true);
            configGUI.getSegundosField().setEditable(true);
            configGUI.getHorasField().setText(Reloj.getHoras());
            configGUI.getMinutosField().setText(Reloj.getMinutos());
            configGUI.getSegundosField().setText(Reloj.getSegundos());
        }
        nivel = Configuracion.getNivel();
        reloj = Configuracion.getReloj();
        lado = Configuracion.getLado();
      }
      /**
     * Función que llena la lista del Top 10 del nivel fácil
     */   
     
      public void IniciarJuego(){
            if (!started) { // si no ha empezado el juego
                  if (jugarFrame.getNombreTXT().getText().length() < 1 || jugarFrame.getNombreTXT().getText().length() > 20){ // se valida el nombre del jugador
                      JOptionPane.showMessageDialog(jugarFrame, "El nombre debe tener una longitud entre 1 y 20 caracteres.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  if (Configuracion.getReloj().equals("Timer")){ // si hay un timer, se empieza a correr
                        
                        try {
                        count = Integer.valueOf(jugarFrame.getHorasTXT().getText()) * 3600 + Integer.valueOf(jugarFrame.getMinutosTXT().getText()) * 60 + Integer.valueOf(jugarFrame.getSegundosTXT().getText()); // se calcula la cantidad de segundos totales con los que se pueden jugar.
                        } 
                        
                        catch (Exception e){
                              JOptionPane.showMessageDialog(jugarFrame, "Ese tiempo no es válido.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                      return;
                        }
                        jugarFrame.getHorasTXT().setEditable(false);
                        jugarFrame.getMinutosTXT().setEditable(false);
                        jugarFrame.getSegundosTXT().setEditable(false);
                        horassave = jugarFrame.getHorasTXT().getText();
                        minutossave = jugarFrame.getMinutosTXT().getText();
                        segundossave = jugarFrame.getSegundosTXT().getText();
                        startTimer(jugarFrame);
                  } else if (Configuracion.getReloj().equals("Sí")){ // si hay un reloj, se empieza a correr
                        jugarFrame.getHorasTXT().setEditable(false);
                        jugarFrame.getMinutosTXT().setEditable(false);
                        jugarFrame.getSegundosTXT().setEditable(false);
                        startClock(jugarFrame);
                  }
                  started = true; // se empieza 
                  juego = new Juego(jugarFrame.getNombreTXT().getText(), 
                          casillasPorTablero.get(Configuracion.getTamaño())); // se crea el nuevo juego

                  jugarFrame.getNombreTXT().setEditable(false);
                  // si hay timer

                  jugarFrame.getBtn1().setSelected(true); // se selecciona el 1 como número para poner predeterminado 
                  jugarFrame.getIniciarJuego().setEnabled(false); // se desactiva este botón y se activan los demás
                  jugarFrame.getTerminarJuego().setEnabled(true);
                  jugarFrame.getGuardarJuego().setEnabled(true);
                  jugarFrame.getRehacerJugada().setEnabled(true);
                  jugarFrame.getBorrarJugada().setEnabled(true);
                  jugarFrame.getTerminarJuego().setEnabled(true);
                  jugarFrame.getBorrarJuego().setEnabled(true);
                  jugarFrame.getPistaButton().setEnabled(true);
                  
            }
      }
              
      public void init(){
            
            menu.setVisible(true);
            menu.addJugarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  menu.dispose();
                // jugarFrame = new JugarFrame();
                jugarFrame.setVisible(true);
                jugar();
            }
            });
            
            menu.addConfigurarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  menu.dispose();
               // configGUI = new ConfiguracionGUI();
            configGUI.setVisible(true);
            configurar();
            }
            });
            
            menu.addAyudaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  if (Desktop.isDesktopSupported()) { // se abre el archivo del manual de usuario
            try {
                File myFile = new File("src\\main\\java\\poo\\programa3\\modelo\\programa3_manual_de_usuario.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                  JOptionPane.showMessageDialog(menu, "NO SE PUDO ABRIR LA AYUDA.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
            }
            });
            
            menu.addAcercaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  menu.dispose();
                  acercaFrame.setVisible(true);
            }
            });
            
            menu.addSalirActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  menu.dispose();
                  System.exit(0);
            }
            
            });
            
            jugarFrame.addIniciarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  IniciarJuego();
            }
            });
            
            jugarFrame.addBorrarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                        int input = JOptionPane.showConfirmDialog(null, "¿Desea borrar el juego?", "Selecione una opción...", 
                      JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE); // pide confirmación de borrar el juego
                  // 0=sí, 1=no, 
                  if (input == 0){ // si sí,                  
                        resetTablero(Configuracion.getTamaño());
                        establecerTablero(Configuracion.getTamaño());
                        if (Configuracion.getReloj().equals("Sí")){
                              stopwatch.stop(); // se detiene el reloj
                              jugarFrame.getHorasTXT().setText("0"); // se restablecen todos los valores
                              jugarFrame.getMinutosTXT().setText("0");
                              jugarFrame.getSegundosTXT().setText("0");
                              count = 0;
                              stopwatch.start(); // se reinicia el reloj
                        } else if (Configuracion.getReloj().equals("Timer")){
                              stopwatch.stop(); // 
                              jugarFrame.getHorasTXT().setText(horassave);
                              jugarFrame.getMinutosTXT().setText(minutossave);
                              jugarFrame.getSegundosTXT().setText(segundossave);
                              count = Integer.parseInt(jugarFrame.getHorasTXT().getText()) * 3600 + Integer.parseInt(jugarFrame.getMinutosTXT().getText()) * 60 + Integer.parseInt(jugarFrame.getSegundosTXT().getText());
                              stopwatch.start();
                        }

                        JOptionPane.showMessageDialog(jugarFrame, "Juego eliminado existosamente."); // se da un mensaje de éxito

                  }
            }
            });
            
            jugarFrame.addTerminarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  Object[] options = {"Sí",
                            "No"};
        int respuesta = JOptionPane.showOptionDialog(jugarFrame,
            "¿Está seguro de terminar el juego (Sí o No)?",
            "Terminar juego",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[0]); //default button title
        if (respuesta == JOptionPane.YES_OPTION){
             jugarFrame.getIniciarJuego().setEnabled(true); // se reactiva el botón de iniciar juego
             jugarFrame.getTerminarJuego().setEnabled(false); // se desactiva este botón
            establecerPartida(); // se establece una nueva partida
             jugarFrame.getNombreTXT().setEditable(true);
            started = false;
            if (Configuracion.getReloj().equals("Timer")) { // si había un timer o reloj, se detiene y reinicia el contador
                  stopwatch.stop();
                   jugarFrame.getHorasTXT().setEditable(true);
                   jugarFrame.getMinutosTXT().setEditable(true);
                   jugarFrame.getSegundosTXT().setEditable(true);
                   jugarFrame.getHorasTXT().setText(horassave);
                   jugarFrame.getMinutosTXT().setText(minutossave);
                   jugarFrame.getSegundosTXT().setText(segundossave);
                  count = Reloj.getHorasInt() * 3600 + Reloj.getMinutosInt() * 60 + Reloj.getSegundosInt();
            } else if(Configuracion.getReloj().equals("Sí")){
                  stopwatch.stop();
                  count = 0;
            }
            // se desactivan todos los otros botones
             jugarFrame.getGuardarJuego().setEnabled(false);
             jugarFrame.getRehacerJugada().setEnabled(false);
             jugarFrame.getBorrarJugada().setEnabled(false);
             jugarFrame.getTerminarJuego().setEnabled(false);
             jugarFrame.getBorrarJuego().setEnabled(false);
        }
            }
            });
            
            jugarFrame.addTop10ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  top10.setVisible(true);
            }
            });
            
            jugarFrame.addGuardarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  try{
            FileWriter save = new FileWriter("futoshiki2022juegoactual.dat"); // se escribe en el archivo
            save.write(Configuracion.getNivel() + "\n"); // se salva el nivel
            save.write(index + "\n"); // se salva el índice de la partida para volverla a desplegar luego
            save.write(Configuracion.getReloj() + "\n"); // se salva si hay reloj o no
            save.write(jugarFrame.getHorasTXT().getText() + "\n"); // se salvan las horas, minutos y segundos
            save.write(jugarFrame.getMinutosTXT().getText() + "\n");
            save.write(jugarFrame.getSegundosTXT().getText() + "\n");
            save.write(Boolean.toString(Configuracion.getLado()) + "\n"); // se salva el lado del panel
            save.write(jugarFrame.getNombreTXT().getText() + "\n"); // se salva el nombre del jugador
            save.write(horassave + "\n"); // se salva el nombre del jugador
            save.write(minutossave + "\n"); // se salva el nombre del jugador
            save.write(segundossave + "\n"); // se salva el nombre del jugador
            save.write(Configuracion.getTamaño() + "\n");
            for (javax.swing.JButton[] fila : casillasPorTablero.get(Configuracion.getTamaño())){ // se salvan todas las casillas
                for (javax.swing.JButton casilla : fila){
                    save.write(casilla.getText() + "\n");
                }
            }
            Stack<Jugada> copia = (Stack) juego.getJugadas().clone(); // guardamos la pila de jugadas realizadas
            while(!copia.isEmpty()){
                Jugada jugada = copia.pop();
                String jugStr = jugada.getNumero() + "," + jugada.getFila() + "," + jugada.getColumna() + "\n";
                save.write(jugStr);
            }
            save.write("J\n");
            
            if (juego.getRedoJugadas() != null) {
                Stack<Jugada> copiaRedo = (Stack) juego.getRedoJugadas().clone(); // guardamos la pila de jugadas realizadas
                while(!copiaRedo.isEmpty()){
                    Jugada jugada = copiaRedo.pop();
                    String jugStr = jugada.getNumero() + "," + jugada.getFila() + "," + jugada.getColumna() + "\n";
                    save.write(jugStr);
                }
            }
            save.write("R\n");
            
            save.close(); // se cierra el archivo
            JOptionPane.showMessageDialog(jugarFrame, "Partida guardada con éxito"); // se da un mensaje de éxito
        } catch (IOException e){
            JOptionPane.showMessageDialog(jugarFrame, "No se pudo grabar la partida.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
            }
            });
            
            jugarFrame.addCargarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  // TODO add your handling code here:
        if (started){ // si ya hay se está jugando, no se deja cargar un juevo
              JOptionPane.showMessageDialog(jugarFrame, "Ya hay una partida en juego. Termine su partida y vuelva a intentar.", 
                "Error", JOptionPane.ERROR_MESSAGE);
              return;
        }
        try{ // se abre el archivo
            List<String> lineas = Files.readAllLines(Paths.get("futoshiki2022juegoactual.dat"));
            Configuracion.setNivel(lineas.get(0)); // se establece el nivel
            jugarFrame.getNivelTXT().setText(lineas.get(0)); 
            partida = Partida.getPartidasPorNivel(Configuracion.getTamaño()).get(lineas.get(0)).get(Integer.parseInt(lineas.get(1))); // se obtiene la partida
            // si se había guardado una partida con reloj o timer, se pone el frame
            if (Configuracion.getReloj().equals("Timer") || Configuracion.getReloj().equals("Sí")){
                jugarFrame.getRelojFrame().setVisible(true);
            } else { // si no tiene reloj, se elimina.
                jugarFrame.getRelojFrame().setVisible(true);
            }
            jugarFrame.getHorasTXT().setText(lineas.get(3));
            jugarFrame.getMinutosTXT().setText(lineas.get(4));
            jugarFrame.getSegundosTXT().setText(lineas.get(5));
            count = Integer.parseInt(jugarFrame.getHorasTXT().getText()) * 3600 + Integer.parseInt(jugarFrame.getMinutosTXT().getText()) * 60 + Integer.parseInt(jugarFrame.getSegundosTXT().getText()); // se calcula la cantidad de segundos totales con los que se pueden jugar.
            Configuracion.setLado(Boolean.parseBoolean(lineas.get(6))); // se obtiene el lado del 
            moveButtonPanel();
            jugarFrame.getNombreTXT().setText(lineas.get(7)); // se vuelve a poner el nombre
            horassave = lineas.get(8);
            minutossave = lineas.get(9);
            segundossave = lineas.get(10);
            int size = Integer.parseInt(lineas.get(11));
            Configuracion.setTamaño(size);
            resetTablero(Configuracion.getTamaño()); // se borra el tablero
            establecerTablero(Configuracion.getTamaño()); // se vuelve a establecer el tablero con la partida recuperada
            setButtonPanel();
            for (int casilla = 0; casilla < size * size; casilla++){ // se vuelven a poner los valores de las casillas
                casillasPorTablero.get(Configuracion.getTamaño())[casilla / size][casilla % size].setText(lineas.get(12 + casilla));
            }
            
            IniciarJuego(); // se inicia el juego otra vez
            int indexList = 12 + size * size;
            // establecemos las pilas
            Stack<String> temp = new Stack<>(); // pila temporal para luego conseguir el orden verdadero de la pila
            Stack<Jugada> jugadasStack = new Stack<>();
            while(!lineas.get(indexList).equals("J")){ // mientras no haya llegado al indicador de dónde termina la pila del final de la pila
                temp.push(lineas.get(indexList++)); // va metiendo a la pila temporal
            }
            while(!temp.isEmpty()){
                // como la pila se fue leyendo de atras para adelante, debemos popear los elementos para agregarlos en orden
                String[] entry = temp.pop().split(",");
                int fila = Integer.parseInt(entry[1]);
                int columna = Integer.parseInt(entry[2]);
                jugadasStack.push(new Jugada(fila, columna, entry[0]));
            }
            juego.setJugadas(jugadasStack);

            indexList++;
            temp = new Stack<>();
            Stack<Jugada> rehacerStack = new Stack<>();
            while(!lineas.get(indexList).equals("R")){ // mientras no haya llegado al indicador de dónde termina la pila del final de la pila
                temp.push(lineas.get(indexList++)); // va metiendo a la pila temporal
            }
            while(!temp.isEmpty()){
                // como la pila se fue leyendo de atras para adelante, debemos popear los elementos para agregarlos en orden
                String[] entry = temp.pop().split(",");
                int fila = Integer.parseInt(entry[1]);
                int columna = Integer.parseInt(entry[2]);
                rehacerStack.push(new Jugada(fila, columna, entry[0]));
            }
            juego.setRedoJugadas(rehacerStack);
        } catch (IOException e){
            JOptionPane.showMessageDialog(jugarFrame, "La partida no se pudo cargar porque el archivo no existe.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
            }
            });
            
            jugarFrame.addPistaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  posiblesFrame.setVisible(true);
            }
            });
            
            jugarFrame.addUndoActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  boolean done = juego.borrarJugada();
                  if (!done) {
                        JOptionPane.showMessageDialog(jugarFrame, "ERROR: NO HAY MÁS JUGADAS POR BORRAR.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                  }
            }
            });
            
            jugarFrame.addRedoActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  boolean done = juego.rehacerJugada();
                  if (!done) {
                        JOptionPane.showMessageDialog(jugarFrame, "ERROR: NO HAY MÁS JUGADAS POR REHACER.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                  }
            }
            });      
            jugarFrame.addCerrarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  if (started){
                  int input = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el juego y perder el progreso?", "Selecione una opción...",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            // 0=sí, 1=no, 
                  if (input == 1){ 
                        return;
                  }
            }
            // cierra la pestaña
            started = false;
            if (!("No".equals(Configuracion.getReloj())) && stopwatch != null){
                  stopwatch.stop();
            }
            jugarFrame.dispose();
            menu.setVisible(true);
            }
            });     
            
            jugarFrame.addBtn00ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  putNumber(0,0);
            }
            });
            
            jugarFrame.addBtn01ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  putNumber(0,1);
            }
            });
            
            jugarFrame.addBtn02ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  putNumber(0,2);
            }
            });
            
            jugarFrame.addBtn03ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  putNumber(0,3);
            }
            });
            
            jugarFrame.addBtn04ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  putNumber(0,4);
            }
            });
            
            jugarFrame.addBtn05ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  putNumber(0,5);
            }
            });
            
            jugarFrame.addBtn06ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  putNumber(0,6);
            }
            });
            
            jugarFrame.addBtn07ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  putNumber(0,7);
            }
            });
            
            jugarFrame.addBtn08ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  putNumber(0,8);
            }
            });
            
            jugarFrame.addBtn10ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  putNumber(1,0);
            }
            });
            
            jugarFrame.addBtn11ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(1,1);
            }
            });
            
            jugarFrame.addBtn12ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(1,2);
            }
            });
            
            jugarFrame.addBtn13ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(1,3);
            }
            });
            
            jugarFrame.addBtn14ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(1,4);
            }
            });
            
            jugarFrame.addBtn15ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(1,5);
            }
            });
            
            jugarFrame.addBtn16ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(1,6);
            }
            });
            
            jugarFrame.addBtn17ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(1,7);
            }
            });
            
            jugarFrame.addBtn18ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(1,8);
            }
            });
            
            jugarFrame.addBtn20ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(2,0);
            }
            });
            
            jugarFrame.addBtn21ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(2,1);
            }
            });
            
            jugarFrame.addBtn22ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(2,2);
            }
            });
            
            jugarFrame.addBtn23ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(2,3);
            }
            });
            
            jugarFrame.addBtn24ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(2,4);
            }
            });
            
            jugarFrame.addBtn25ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(2,5);
            }
            });
            
            jugarFrame.addBtn26ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(2,6);
            }
            });
            
            jugarFrame.addBtn27ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(2,7);
            }
            });
            
            jugarFrame.addBtn28ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(2,8);
            }
            });
            
            jugarFrame.addBtn30ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(3,0);
            }
            });
            
            jugarFrame.addBtn31ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(3,1);
            }
            });
            
            jugarFrame.addBtn32ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(3,2);
            }
            });
            
            jugarFrame.addBtn33ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(3,3);
            }
            });
            
            jugarFrame.addBtn34ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(3,4);
            }
            });
            
            jugarFrame.addBtn35ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(3,5);
            }
            });
            
            jugarFrame.addBtn36ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(3,6);
            }
            });
            
            jugarFrame.addBtn37ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(3,7);
            }
            });
            
            jugarFrame.addBtn38ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(3,8);
            }
            });
            
            jugarFrame.addBtn40ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(4,0);
            }
            });
            
            jugarFrame.addBtn41ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(4,1);
            }
            });
            
            jugarFrame.addBtn42ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(4,2);
            }
            });
            
            jugarFrame.addBtn43ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(4,3);
            }
            });
            
            jugarFrame.addBtn44ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(4,4);
            }
            });
            
            jugarFrame.addBtn45ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(4,5);
            }
            });
            
            jugarFrame.addBtn46ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(4,6);
            }
            });
            
            jugarFrame.addBtn47ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(4,7);
            }
            });
            
            jugarFrame.addBtn48ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(4,8);
            }
            });
            
            jugarFrame.addBtn50ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(5,0);
            }
            });
            
            jugarFrame.addBtn51ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(5,1);
            }
            });
            
            jugarFrame.addBtn52ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(5,2);
            }
            });
            
            jugarFrame.addBtn53ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(5,3);
            }
            });
            
            jugarFrame.addBtn54ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(5,4);
            }
            });
            
            jugarFrame.addBtn55ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(5,5);
            }
            });
            
            jugarFrame.addBtn56ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(5,6);
            }
            });
            
            jugarFrame.addBtn57ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(5,7);
            }
            });
            
            jugarFrame.addBtn58ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(5,8);
            }
            });
            
            jugarFrame.addBtn60ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(6,0);
            }
            });
            
            jugarFrame.addBtn61ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(6,1);
            }
            });
            
            jugarFrame.addBtn62ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(6,2);
            }
            });
            
            jugarFrame.addBtn63ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(6,3);
            }
            });
            
            jugarFrame.addBtn64ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(6,4);
            }
            });
            
            jugarFrame.addBtn65ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(6,5);
            }
            });
            
            jugarFrame.addBtn66ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(6,6);
            }
            });
            
            jugarFrame.addBtn67ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(6,7);
            }
            });
            
            jugarFrame.addBtn68ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(6,8);
            }
            });
            
            jugarFrame.addBtn70ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(7,0);
            }
            });
            
            jugarFrame.addBtn71ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(7,1);
            }
            });
            
            jugarFrame.addBtn72ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(7,2);
            }
            });
            
            jugarFrame.addBtn73ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(7,3);
            }
            });
            
            jugarFrame.addBtn74ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(7,4);
            }
            });
            
            jugarFrame.addBtn75ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(7,5);
            }
            });
            
            jugarFrame.addBtn76ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(7,6);
            }
            });
            
            jugarFrame.addBtn77ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(7,7);
            }
            });
            
            jugarFrame.addBtn78ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(7,8);
            }
            });
            
            jugarFrame.addBtn80ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(8,0);
            }
            });
            
            jugarFrame.addBtn81ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(8,1);
            }
            });
            
            jugarFrame.addBtn82ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(8,2);
            }
            });
            
            jugarFrame.addBtn83ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(8,3);
            }
            });
            
            jugarFrame.addBtn84ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(8,4);
            }
            });
            
            jugarFrame.addBtn85ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(8,5);
            }
            });
            
            jugarFrame.addBtn86ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(8,6);
            }
            });
            
            jugarFrame.addBtn87ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(8,7);
            }
            });
            
            jugarFrame.addBtn88ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      putNumber(8,8);
            }
            });
            
            acercaFrame.addCerrarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      acercaFrame.dispose();
                      menu.setVisible(true);
            }
            });
            
            top10.addCerrarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      top10.dispose();
            }
            });
            
            top10.addClickActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      top10.FillFacilListModel(Integer.parseInt((String)top10.getSizeBox().getSelectedItem()));
            top10.FillIntermedioListModel(Integer.parseInt((String)top10.getSizeBox().getSelectedItem()));
            top10.FillDificilListModel(Integer.parseInt((String)top10.getSizeBox().getSelectedItem()));
            }
            });
            
            configGUI.addFacilActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      nivelConfig = "Fácil";
            }
            });
            
            configGUI.addIntermedioActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      nivelConfig = "Intermedio";
            }
            });
            
            configGUI.addDificilActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      nivelConfig = "Difícil";
            }
            });
            
            configGUI.addSiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      reloj = "Sí";
                  configGUI.getRelojFrame().setVisible(true);
                  configGUI.getHorasField().setEditable(false);
                  configGUI.getMinutosField().setEditable(false);
                  configGUI.getSegundosField().setEditable(false);
                  configGUI.getHorasField().setText("0");
                  configGUI.getMinutosField().setText("0");
                  configGUI.getSegundosField().setText("0");
            }
            });
            
            configGUI.addTimerActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      reloj = "Timer";
                        configGUI.getRelojFrame().setVisible(true);
                        configGUI.getHorasField().setEditable(true);
                        configGUI.getMinutosField().setEditable(true);
                        configGUI.getSegundosField().setEditable(true);
            }
            });
            
            configGUI.addNoActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      reloj = "No";
                  configGUI.getRelojFrame().setVisible(false);
            }
            });
            
            configGUI.addIzquierdaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      lado = false;
            }
            });
            
            configGUI.addDerechaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      lado = true;
            }
            });
            
            configGUI.addSaveActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      if (!configGUI.getNoButton().isSelected()){
            String horas = configGUI.getHorasField().getText();
            String minutos = configGUI.getMinutosField().getText();
            String segundos = configGUI.getSegundosField().getText();
                if (configGUI.getTimerButton().isSelected()){
                // validación de las horas, minutos y segundos
                try{
                    Reloj.validarEntrada(horas, 0, 2);
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(configGUI, "Las horas deben ser un número.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (RangeException e){
                    JOptionPane.showMessageDialog(configGUI, "Las horas deben ser del 0 al 2.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    Reloj.validarEntrada(minutos, 0, 59);
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(configGUI, "Los minutos deben ser un número.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (RangeException e){
                    JOptionPane.showMessageDialog(configGUI, "Los minutos deben ser del 0 al 59.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    Reloj.validarEntrada(segundos, 0, 59);
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(configGUI, "Los segundos deben ser un número.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (RangeException e){
                    JOptionPane.showMessageDialog(configGUI, "Los segundos deben ser del 0 al 59.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // si los tres son 0, enviamos un error
                if (horas.equals("0") && minutos.equals("0") && segundos.equals("0")){
                    JOptionPane.showMessageDialog(configGUI, "Al menos un valor no debe ser 0.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            Reloj.setHoras(horas);
            Reloj.setMinutos(minutos);
            Reloj.setSegundos(segundos);   
        }
        
        Configuracion.setNivel(nivelConfig);
        Configuracion.setReloj(reloj);
        Configuracion.setLado(lado);
        Configuracion.setTamaño(Integer.parseInt(getSizeButton()));
        JOptionPane.showMessageDialog(configGUI, "¡Configuración guardada con éxito!");
        configGUI.dispose();
        menu.setVisible(true);
            }
            });
            
            configGUI.addCancelActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  configGUI.dispose();
                        menu.setVisible(true);
            }
            });
            
            configGUI.addMultinivelActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  nivelConfig = "Multinivel";
            }
            });
            
            posiblesFrame.addFindActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  int fila = 0;
            int columna = 0;
            try {
                  fila = Integer.valueOf(posiblesFrame.getFilaTXT().getText());
                  columna = Integer.valueOf(posiblesFrame.getColumnaTXT().getText());
                  if (fila > Configuracion.getTamaño() || fila < 1 || columna > Configuracion.getTamaño() || columna < 1) {
                        throw new ArithmeticException("message");
                  }
            }
            catch (Exception e){
                  JOptionPane.showMessageDialog(posiblesFrame, "ESOS NÚMEROS NO SON VÁLIDOS.", 
                "Error", JOptionPane.ERROR_MESSAGE);
                  return;
            }
            fila -= 1;
            columna -= 1;
            
            // Check columna

            javax.swing.JButton [][] casillas = juego.getCasillas();
            ArrayList<Integer> posibles = new ArrayList<>();
            for (int i = 0; i < Configuracion.getTamaño(); i++){
                  if (!casillas[fila][i].getText().equals("")){
                        posibles.add(Integer.valueOf(casillas[fila][i].getText()));
                  }
                  if (!casillas[i][columna].getText().equals("")){
                        posibles.add(Integer.valueOf(casillas[i][columna].getText()));
                  }
            }
            String texto = "Las jugadas posibles son: ";
            OUTER:
            for (int j = 1; j <= Configuracion.getTamaño(); j++){
                  for (Integer num : posibles){
                        if (j == num){
                              continue OUTER; 
                        }
                  }
                  int i = 0;
                  for (; i < partida.getOperaciones().size(); i ++) {
                  int colCompare;
                  int colCompare2;
                  switch(partida.getOperaciones().get(i).getTipo()){
                        case 'a':
                              if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila){
                                    try {
                                          colCompare = j;
                                          colCompare2 = Integer.valueOf(casillas[fila][columna+1].getText());
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else if (partida.getOperaciones().get(i).getIndiceColumna() == columna - 1 && partida.getOperaciones().get(i).getIndiceFila() == fila) {
                                    try {
                                          colCompare = Integer.valueOf(casillas[fila][columna-1].getText());
                                          colCompare2 = j;
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else {
                                    continue;
                              }
                              if (!(colCompare > colCompare2)) {
                                    continue OUTER;
                              } else {
                                    continue;
                              }
                     case 'b':
                              if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila){
                                    try {
                                          colCompare = j;
                                          colCompare2 = Integer.valueOf(casillas[fila][columna+1].getText());
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else if (partida.getOperaciones().get(i).getIndiceColumna() == columna - 1 && partida.getOperaciones().get(i).getIndiceFila() == fila) {
                                    try {
                                          colCompare = Integer.valueOf(casillas[fila][columna-1].getText());
                                          colCompare2 = j;
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else {
                                    continue;
                              }
                              if (!(colCompare < colCompare2)) {
                                    continue OUTER;
                              } else {
                                    continue;
                              }
                    case 'y':
                              if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila){
                                    try {
                                          colCompare = j;
                                          colCompare2 = Integer.valueOf(casillas[fila+1][columna].getText());
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila -1) {
                                    try {
                                          colCompare = Integer.valueOf(casillas[fila-1][columna].getText());
                                          colCompare2 = j;
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else {
                                    continue;
                              }
                              if (!(colCompare > colCompare2)) {
                                    continue OUTER;
                              } else {
                                    continue;
                              }
                      case 'z':
                              if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila){
                                    try {
                                          colCompare = j;
                                          colCompare2 = Integer.valueOf(casillas[fila+1][columna].getText());
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila -1) {
                                    try {
                                          colCompare = Integer.valueOf(casillas[fila-1][columna].getText());
                                          colCompare2 = j;
                                    }
                                    catch (Exception e){
                                          continue;
                                    }
                              } else {
                                    continue;
                              }
                              if (!(colCompare < colCompare2)) {
                                    continue OUTER;
                              } else {
                                    continue;
                              }                       
                  }
            }
                  texto += " " + j;
            }
            
            JOptionPane.showMessageDialog(posiblesFrame, texto); 
            posiblesFrame.dispose();
            }
            });
            
            posiblesFrame.addCerrarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  posiblesFrame.dispose();
            }
            });
            
      }
      
      @SuppressWarnings("CallToThreadDumpStack")
    public static void main(String[] args) {
          try {
                Partida.leerPartidas();
            Marca.createHashMap();
            new Programa3().init();
          }
          catch (Exception e) {
            e.printStackTrace();
          }       
    }
    
    
}
