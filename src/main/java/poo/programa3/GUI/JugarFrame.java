/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poo.programa3.GUI;

import poo.programa3.Marca;
import poo.programa3.Jugada;
import poo.programa3.Configuracion;
import poo.programa3.Operacion;
import poo.programa3.Partida;
import poo.programa3.Reloj;
import poo.programa3.Juego;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

/**
 * Ventana para el juego
 * @author Daniel Granados Retana, carné 2022104692, y Diego Granados Retana, carné 2022158363
 */
public class JugarFrame extends javax.swing.JFrame {
    private boolean started = false; // indica si ya empezó el juego.
    boolean win = false; // indica si ya se ganó el juego
    private static Juego juego; // se usa para guardar el juego
    private Partida partida; // se usa para establecer la partida.
    private ArrayList<javax.swing.JLabel> labelsHorizontales = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> labelsVerticales = new ArrayList<>(); 
    private javax.swing.JButton [][] casillas; // contienen todos los botones del tablero.
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
      
   // Esta función restablece el tablero a vacío
    private void resetTablero(){
        for (javax.swing.JLabel label : labelsHorizontales){ // itera por todos los labels y botones de casillas
          label.setText("");
        }
        for (javax.swing.JLabel label : labelsVerticales){
          label.setText("");
        }
        for (javax.swing.JButton[] fila : casillas){
            for (javax.swing.JButton button : fila){
                button.setText("");
            }
        }
    }
    
   
    // esta función establece el tablero con respecto a la partida escogida.
    private void establecerTablero(){
        for(Operacion desigualdad : partida.getOperaciones()){
            switch(desigualdad.getTipo()){
                case 'a': // pone los labels con el símbolo correcto
                    labelsHorizontales.get(8 * desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText(">");
                    break;
                case 'b':
                    labelsHorizontales.get(8 * desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText("<");
                    break;
                case 'y':
                    labelsVerticales.get(9*desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText("∨");
                    break;
                case 'z':
                    labelsVerticales.get(9*desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText("∧");  
            }
        }

        // llena las casillas de constantes
        for(Operacion constante : partida.getConstantes()){
            casillas[constante.getIndiceFila()][constante.getIndiceColumna()].setText(Character.toString(constante.getTipo()));
        }
    }
    
    // Esta función establece la partida de acuerdo con el nivel. 
    private void establecerPartida(){
          
          if (Partida.getPartidasPorNivel().get(nivel).isEmpty()){ // si no hay partidas disponibles, se da un mensaje de error.
                  JOptionPane.showMessageDialog(this, "NO HAY PARTIDAS DE ESE NIVEL.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                  dispose();
                  Menu window = new Menu();
                  window.setVisible(true);
          }
          // se establece la partida de acuerdo con el nivel.
        index = Partida.getIndex(nivel);
        partida = Partida.getPartidasPorNivel().get(nivel).get(index);
        resetTablero();
        establecerTablero();
    }
      
    // esta función mueve el panel de botones de acuerdo con el lado establecido en la configuración

    /**
     * Esta función mueve el panel de números de lado dependiendo de la configuración.
     */
     public void moveButtonPanel(){
           if (!Configuracion.getLado()){
                 NumbersPanel.setLocation(5, 100);
            } else {
                  NumbersPanel.setLocation(710, 60);
            }
     }
      /**
       * Creates new form JugarFrame
       */
     // Esta función crea el frame de esta pestaña
      public JugarFrame() {
            initComponents();
            this.setLocationRelativeTo(null); // se centra la ventana
            
            if (Configuracion.getNivel().equals("Multinivel")){
                  nivel = "Fácil";
            } else {
                  nivel = Configuracion.getNivel();
            }
            nivelCont = 1;
            NivelTXT.setText(nivel); // se establece el ninvel
            setLayout(null); // esto se usa para poder mover el panel de botones.
            moveButtonPanel();
            if (Configuracion.getTamaño() != 9) {
                  Btn08.setVisible(false);
                  Btn18.setVisible(false);
                  Btn28.setVisible(false);
                  Btn38.setVisible(false);
                  Btn48.setVisible(false);
                  Btn58.setVisible(false);
                  Btn68.setVisible(false);
                  Btn78.setVisible(false);
                  Btn88.setVisible(false);
                  Btn80.setVisible(false);
                  Btn81.setVisible(false);
                  Btn82.setVisible(false);
                  Btn82.setVisible(false);
                  Btn83.setVisible(false);
                  Btn84.setVisible(false);
                  Btn85.setVisible(false);
                  Btn86.setVisible(false);
                  Btn87.setVisible(false);
                  Op70V.setVisible(false);
                  Op71V.setVisible(false);
                  Op72V.setVisible(false);
                  Op73V.setVisible(false);
                  Op74V.setVisible(false);
                  Op75V.setVisible(false);
                  Op76V.setVisible(false);
                  Op77V.setVisible(false);
                  Op78V.setVisible(false);
                  Op08V.setVisible(false);
                  Op18V.setVisible(false);
                  Op28V.setVisible(false);
                  Op38V.setVisible(false);
                  Op48V.setVisible(false);
                  Op58V.setVisible(false);
                  Op68V.setVisible(false);
                  Op801.setVisible(false);
                  Op812.setVisible(false);
                  Op823.setVisible(false);
                  Op834.setVisible(false);
                  Op845.setVisible(false);
                  Op856.setVisible(false);
                  Op867.setVisible(false);
                  Op878.setVisible(false);
                  Op778.setVisible(false);
                  Op678.setVisible(false);
                  Op578.setVisible(false);
                  Op478.setVisible(false);
                  Op378.setVisible(false);
                  Op278.setVisible(false);
                  Op178.setVisible(false);
                  Op078.setVisible(false);
            }
            if (Configuracion.getTamaño() != 8  && Configuracion.getTamaño() != 9){
                  Btn07.setVisible(false);
                  Btn17.setVisible(false);
                  Btn27.setVisible(false);
                  Btn37.setVisible(false);
                  Btn47.setVisible(false);
                  Btn57.setVisible(false);
                  Btn67.setVisible(false);
                  Btn77.setVisible(false);
                  Btn87.setVisible(false);
                  Btn70.setVisible(false);
                  Btn71.setVisible(false);
                  Btn72.setVisible(false);
                  Btn72.setVisible(false);
                  Btn73.setVisible(false);
                  Btn74.setVisible(false);
                  Btn75.setVisible(false);
                  Btn76.setVisible(false);
                  Btn77.setVisible(false);
                  Op60V.setVisible(false);
                  Op61V.setVisible(false);
                  Op62V.setVisible(false);
                  Op63V.setVisible(false);
                  Op64V.setVisible(false);
                  Op65V.setVisible(false);
                  Op66V.setVisible(false);
                  Op67V.setVisible(false);
                  Op07V.setVisible(false);
                  Op17V.setVisible(false);
                  Op27V.setVisible(false);
                  Op37V.setVisible(false);
                  Op47V.setVisible(false);
                  Op57V.setVisible(false);
                  Op67V.setVisible(false);
                  Op701.setVisible(false);
                  Op712.setVisible(false);
                  Op723.setVisible(false);
                  Op734.setVisible(false);
                  Op745.setVisible(false);
                  Op756.setVisible(false);
                  Op767.setVisible(false);
                  Op778.setVisible(false);
                  Op767.setVisible(false);
                  Op667.setVisible(false);
                  Op567.setVisible(false);
                  Op467.setVisible(false);
                  Op367.setVisible(false);
                  Op267.setVisible(false);
                  Op167.setVisible(false);
                  Op067.setVisible(false);
            }
            if (Configuracion.getTamaño() == 5  || Configuracion.getTamaño() == 6){
                  Btn06.setVisible(false);
                  Btn16.setVisible(false);
                  Btn26.setVisible(false);
                  Btn36.setVisible(false);
                  Btn46.setVisible(false);
                  Btn56.setVisible(false);
                  Btn66.setVisible(false);
                  Btn76.setVisible(false);
                  Btn86.setVisible(false);
                  Btn60.setVisible(false);
                  Btn61.setVisible(false);
                  Btn62.setVisible(false);
                  Btn62.setVisible(false);
                  Btn63.setVisible(false);
                  Btn64.setVisible(false);
                  Btn65.setVisible(false);
                  Btn66.setVisible(false);
                  Btn67.setVisible(false);
                  Op50V.setVisible(false);
                  Op51V.setVisible(false);
                  Op52V.setVisible(false);
                  Op53V.setVisible(false);
                  Op54V.setVisible(false);
                  Op55V.setVisible(false);
                  Op56V.setVisible(false);
                  Op57V.setVisible(false);
                  Op06V.setVisible(false);
                  Op16V.setVisible(false);
                  Op26V.setVisible(false);
                  Op36V.setVisible(false);
                  Op46V.setVisible(false);
                  Op56V.setVisible(false);
                  Op66V.setVisible(false);
                  Op601.setVisible(false);
                  Op612.setVisible(false);
                  Op623.setVisible(false);
                  Op634.setVisible(false);
                  Op645.setVisible(false);
                  Op656.setVisible(false);
                  Op667.setVisible(false);
                  Op678.setVisible(false);
                  Op756.setVisible(false);
                  Op656.setVisible(false);
                  Op556.setVisible(false);
                  Op456.setVisible(false);
                  Op356.setVisible(false);
                  Op256.setVisible(false);
                  Op156.setVisible(false);
                  Op056.setVisible(false);
            }
            if (Configuracion.getTamaño() == 5){
                  Btn05.setVisible(false);
                  Btn15.setVisible(false);
                  Btn25.setVisible(false);
                  Btn35.setVisible(false);
                  Btn45.setVisible(false);
                  Btn55.setVisible(false);
                  Btn65.setVisible(false);
                  Btn75.setVisible(false);
                  Btn85.setVisible(false);
                  Btn50.setVisible(false);
                  Btn51.setVisible(false);
                  Btn52.setVisible(false);
                  Btn53.setVisible(false);
                  Btn54.setVisible(false);
                  Btn55.setVisible(false);
                  Btn56.setVisible(false);
                  Btn57.setVisible(false);
                  Op40V.setVisible(false);
                  Op41V.setVisible(false);
                  Op42V.setVisible(false);
                  Op43V.setVisible(false);
                  Op44V.setVisible(false);
                  Op45V.setVisible(false);
                  Op46V.setVisible(false);
                  Op47V.setVisible(false);
                  Op05V.setVisible(false);
                  Op15V.setVisible(false);
                  Op25V.setVisible(false);
                  Op35V.setVisible(false);
                  Op45V.setVisible(false);
                  Op55V.setVisible(false);
                  Op65V.setVisible(false);
                  Op501.setVisible(false);
                  Op512.setVisible(false);
                  Op523.setVisible(false);
                  Op534.setVisible(false);
                  Op545.setVisible(false);
                  Op556.setVisible(false);
                  Op567.setVisible(false);
                  Op578.setVisible(false);
                  Op745.setVisible(false);
                  Op645.setVisible(false);
                  Op545.setVisible(false);
                  Op445.setVisible(false);
                  Op345.setVisible(false);
                  Op245.setVisible(false);
                  Op145.setVisible(false);
                  Op045.setVisible(false);
            }
            if (Configuracion.getReloj().equals("No")){ //  si no hay un reloj, se quita de la pantalla
                  relojFrame.setVisible(false);
             } else if (Configuracion.getReloj().equals("Timer")) { // si hay un timer, se deja editar el tiempo
                  
                  HorasTXT.setEditable(true);
                  MinutosTXT.setEditable(true);
                  SegundosTXT.setEditable(true);
                  HorasTXT.setText(Reloj.getHoras()); // se establecen los tiempos de la configuración
                  MinutosTXT.setText(Reloj.getMinutos());
                  SegundosTXT.setText(Reloj.getSegundos());
            }
            // se añaden los labels a las listas
            labelsHorizontales.add(Op001);
            labelsHorizontales.add(Op012);
            labelsHorizontales.add(Op023);
            labelsHorizontales.add(Op034);
            labelsHorizontales.add(Op045);
            labelsHorizontales.add(Op056);
            labelsHorizontales.add(Op067);
            labelsHorizontales.add(Op078);
            labelsHorizontales.add(Op101);
            labelsHorizontales.add(Op112);
            labelsHorizontales.add(Op123);
            labelsHorizontales.add(Op134);
            labelsHorizontales.add(Op145);
            labelsHorizontales.add(Op156);
            labelsHorizontales.add(Op167);
            labelsHorizontales.add(Op178);
            labelsHorizontales.add(Op201);
            labelsHorizontales.add(Op212);
            labelsHorizontales.add(Op223);
            labelsHorizontales.add(Op234);
            labelsHorizontales.add(Op245);
            labelsHorizontales.add(Op256);
            labelsHorizontales.add(Op267);
            labelsHorizontales.add(Op278);
            labelsHorizontales.add(Op301);
            labelsHorizontales.add(Op312);
            labelsHorizontales.add(Op323);
            labelsHorizontales.add(Op334);
            labelsHorizontales.add(Op345);
            labelsHorizontales.add(Op356);
            labelsHorizontales.add(Op367);
            labelsHorizontales.add(Op378);
            labelsHorizontales.add(Op401);
            labelsHorizontales.add(Op412);
            labelsHorizontales.add(Op423);
            labelsHorizontales.add(Op434);
            labelsHorizontales.add(Op445);
            labelsHorizontales.add(Op456);
            labelsHorizontales.add(Op467);
            labelsHorizontales.add(Op478);
            labelsHorizontales.add(Op501);
            labelsHorizontales.add(Op512);
            labelsHorizontales.add(Op523);
            labelsHorizontales.add(Op534);
            labelsHorizontales.add(Op545);
            labelsHorizontales.add(Op556);
            labelsHorizontales.add(Op567);
            labelsHorizontales.add(Op578);
            labelsHorizontales.add(Op601);
            labelsHorizontales.add(Op612);
            labelsHorizontales.add(Op623);
            labelsHorizontales.add(Op634);
            labelsHorizontales.add(Op645);
            labelsHorizontales.add(Op656);
            labelsHorizontales.add(Op667);
            labelsHorizontales.add(Op678);
            labelsHorizontales.add(Op701);
            labelsHorizontales.add(Op712);
            labelsHorizontales.add(Op723);
            labelsHorizontales.add(Op734);
            labelsHorizontales.add(Op745);
            labelsHorizontales.add(Op756);
            labelsHorizontales.add(Op767);
            labelsHorizontales.add(Op778);
            labelsHorizontales.add(Op801);
            labelsHorizontales.add(Op812);
            labelsHorizontales.add(Op823);
            labelsHorizontales.add(Op834);
            labelsHorizontales.add(Op845);
            labelsHorizontales.add(Op856);
            labelsHorizontales.add(Op867);
            labelsHorizontales.add(Op878);
            labelsVerticales.add(Op00V);
            labelsVerticales.add(Op01V);
            labelsVerticales.add(Op02V);
            labelsVerticales.add(Op03V);
            labelsVerticales.add(Op04V);
            labelsVerticales.add(Op05V);
            labelsVerticales.add(Op06V);
            labelsVerticales.add(Op07V);
            labelsVerticales.add(Op08V);
            labelsVerticales.add(Op10V);
            labelsVerticales.add(Op11V);
            labelsVerticales.add(Op12V);
            labelsVerticales.add(Op13V);
            labelsVerticales.add(Op14V);
            labelsVerticales.add(Op15V);
            labelsVerticales.add(Op16V);
            labelsVerticales.add(Op17V);
            labelsVerticales.add(Op18V);
            labelsVerticales.add(Op20V);
            labelsVerticales.add(Op21V);
            labelsVerticales.add(Op22V);
            labelsVerticales.add(Op23V);
            labelsVerticales.add(Op24V);
            labelsVerticales.add(Op25V);
            labelsVerticales.add(Op26V);
            labelsVerticales.add(Op27V);
            labelsVerticales.add(Op28V);
            labelsVerticales.add(Op30V);
            labelsVerticales.add(Op31V);
            labelsVerticales.add(Op32V);
            labelsVerticales.add(Op33V);
            labelsVerticales.add(Op34V);
            labelsVerticales.add(Op35V);
            labelsVerticales.add(Op36V);
            labelsVerticales.add(Op37V);
            labelsVerticales.add(Op38V);
            labelsVerticales.add(Op40V);
            labelsVerticales.add(Op41V);
            labelsVerticales.add(Op42V);
            labelsVerticales.add(Op43V);
            labelsVerticales.add(Op44V);
            labelsVerticales.add(Op45V);
            labelsVerticales.add(Op46V);
            labelsVerticales.add(Op47V);
            labelsVerticales.add(Op48V);
            labelsVerticales.add(Op50V);
            labelsVerticales.add(Op51V);
            labelsVerticales.add(Op52V);
            labelsVerticales.add(Op53V);
            labelsVerticales.add(Op54V);
            labelsVerticales.add(Op55V);
            labelsVerticales.add(Op56V);
            labelsVerticales.add(Op57V);
            labelsVerticales.add(Op58V);
            labelsVerticales.add(Op60V);
            labelsVerticales.add(Op61V);
            labelsVerticales.add(Op62V);
            labelsVerticales.add(Op63V);
            labelsVerticales.add(Op64V);
            labelsVerticales.add(Op65V);
            labelsVerticales.add(Op66V);
            labelsVerticales.add(Op67V);
            labelsVerticales.add(Op68V);
            labelsVerticales.add(Op70V);
            labelsVerticales.add(Op71V);
            labelsVerticales.add(Op72V);
            labelsVerticales.add(Op73V);
            labelsVerticales.add(Op74V);
            labelsVerticales.add(Op75V);
            labelsVerticales.add(Op76V);
            labelsVerticales.add(Op77V);
            labelsVerticales.add(Op78V);
            

            casillas = new javax.swing.JButton[9][9];
            casillas[0][0] = Btn00;
            casillas[0][1] = Btn01;
            casillas[0][2] = Btn02;
            casillas[0][3] = Btn03;
            casillas[0][4] = Btn04;
            casillas[0][5] = Btn05;
            casillas[0][6] = Btn06;
            casillas[0][7] = Btn07;
            casillas[0][8] = Btn08;
            casillas[1][0] = Btn10;
            casillas[1][1] = Btn11;
            casillas[1][2] = Btn12;
            casillas[1][3] = Btn13;
            casillas[1][4] = Btn14;
            casillas[1][5] = Btn15;
            casillas[1][6] = Btn16;
            casillas[1][7] = Btn17;
            casillas[1][8] = Btn18;
            casillas[2][0] = Btn20;
            casillas[2][1] = Btn21;
            casillas[2][2] = Btn22;
            casillas[2][3] = Btn23;
            casillas[2][4] = Btn24;
            casillas[2][5] = Btn25;
            casillas[2][6] = Btn26;
            casillas[2][7] = Btn27;
            casillas[2][8] = Btn28;
            casillas[3][0] = Btn30;
            casillas[3][1] = Btn31;
            casillas[3][2] = Btn32;
            casillas[3][3] = Btn33;
            casillas[3][4] = Btn34;
            casillas[3][5] = Btn35;
            casillas[3][6] = Btn36;
            casillas[3][7] = Btn37;
            casillas[3][8] = Btn38;
            casillas[4][0] = Btn40;
            casillas[4][1] = Btn41;
            casillas[4][2] = Btn42;
            casillas[4][3] = Btn43;
            casillas[4][4] = Btn44;
            casillas[4][5] = Btn45;
            casillas[4][6] = Btn46;
            casillas[4][7] = Btn47;
            casillas[4][8] = Btn48;
            casillas[5][0] = Btn50;
            casillas[5][1] = Btn51;
            casillas[5][2] = Btn52;
            casillas[5][3] = Btn53;
            casillas[5][4] = Btn54;
            casillas[5][5] = Btn55;
            casillas[5][6] = Btn56;
            casillas[5][7] = Btn57;
            casillas[5][8] = Btn58;
            casillas[6][0] = Btn60;
            casillas[6][1] = Btn61;
            casillas[6][2] = Btn62;
            casillas[6][3] = Btn63;
            casillas[6][4] = Btn64;
            casillas[6][5] = Btn65;
            casillas[6][6] = Btn66;
            casillas[6][7] = Btn67;
            casillas[6][8] = Btn68;
            casillas[7][0] = Btn70;
            casillas[7][1] = Btn71;
            casillas[7][2] = Btn72;
            casillas[7][3] = Btn73;
            casillas[7][4] = Btn74;
            casillas[7][5] = Btn75;
            casillas[7][6] = Btn76;
            casillas[7][7] = Btn77;
            casillas[7][8] = Btn78;
            casillas[8][0] = Btn80;
            casillas[8][1] = Btn81;
            casillas[8][2] = Btn82;
            casillas[8][3] = Btn83;
            casillas[8][4] = Btn84;
            casillas[8][5] = Btn85;
            casillas[8][6] = Btn86;
            casillas[8][7] = Btn87;
            casillas[8][8] = Btn88;
            
            establecerPartida();
            GuardarJuego.setEnabled(false);
            RehacerJugada.setEnabled(false);
            BorrarJugada.setEnabled(false);
            TerminarJuego.setEnabled(false);
            BorrarJuego.setEnabled(false);
            PistaButton.setEnabled(false);
            
        }
      /**
       * This method is called from within the constructor to initialize the
       * form. WARNING: Do NOT modify this code. The content of this method is
       * always regenerated by the Form Editor.
       */
      @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
      private void initComponents() {

            Opciones = new javax.swing.ButtonGroup();
            jLabel2 = new javax.swing.JLabel();
            jLabel1 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            NivelTXT = new javax.swing.JLabel();
            NombreTXT = new javax.swing.JTextField();
            NumbersPanel = new javax.swing.JPanel();
            Btn2 = new javax.swing.JToggleButton();
            Btn3 = new javax.swing.JToggleButton();
            Btn1 = new javax.swing.JToggleButton();
            Btn5 = new javax.swing.JToggleButton();
            Btn4 = new javax.swing.JToggleButton();
            Btn6 = new javax.swing.JToggleButton();
            RehacerJugada = new javax.swing.JButton();
            jPanel1 = new javax.swing.JPanel();
            Btn03 = new javax.swing.JButton();
            Btn04 = new javax.swing.JButton();
            Btn10 = new javax.swing.JButton();
            Btn11 = new javax.swing.JButton();
            Btn12 = new javax.swing.JButton();
            Btn13 = new javax.swing.JButton();
            Btn14 = new javax.swing.JButton();
            Btn00 = new javax.swing.JButton();
            Btn01 = new javax.swing.JButton();
            Btn02 = new javax.swing.JButton();
            Btn20 = new javax.swing.JButton();
            Btn21 = new javax.swing.JButton();
            Btn22 = new javax.swing.JButton();
            Btn24 = new javax.swing.JButton();
            Btn44 = new javax.swing.JButton();
            Btn30 = new javax.swing.JButton();
            Btn31 = new javax.swing.JButton();
            Btn32 = new javax.swing.JButton();
            Btn33 = new javax.swing.JButton();
            Btn34 = new javax.swing.JButton();
            Btn40 = new javax.swing.JButton();
            Btn41 = new javax.swing.JButton();
            Btn42 = new javax.swing.JButton();
            Btn43 = new javax.swing.JButton();
            Op01V = new javax.swing.JLabel();
            Op11V = new javax.swing.JLabel();
            Op21V = new javax.swing.JLabel();
            Op31V = new javax.swing.JLabel();
            panelgrid = new javax.swing.JPanel();
            Op101 = new javax.swing.JLabel();
            Op201 = new javax.swing.JLabel();
            Op301 = new javax.swing.JLabel();
            Op401 = new javax.swing.JLabel();
            Op001 = new javax.swing.JLabel();
            Op12V = new javax.swing.JLabel();
            jPanel3 = new javax.swing.JPanel();
            Op012 = new javax.swing.JLabel();
            Op112 = new javax.swing.JLabel();
            Op212 = new javax.swing.JLabel();
            Op312 = new javax.swing.JLabel();
            Op412 = new javax.swing.JLabel();
            Op512 = new javax.swing.JLabel();
            Op13V = new javax.swing.JLabel();
            Op23V = new javax.swing.JLabel();
            Op33V = new javax.swing.JLabel();
            jPanel4 = new javax.swing.JPanel();
            Op023 = new javax.swing.JLabel();
            Op123 = new javax.swing.JLabel();
            Op223 = new javax.swing.JLabel();
            Op323 = new javax.swing.JLabel();
            Op423 = new javax.swing.JLabel();
            Op14V = new javax.swing.JLabel();
            Op38V = new javax.swing.JLabel();
            Op34V = new javax.swing.JLabel();
            Op00V = new javax.swing.JLabel();
            Op02V = new javax.swing.JLabel();
            Op22V = new javax.swing.JLabel();
            jPanel6 = new javax.swing.JPanel();
            Op034 = new javax.swing.JLabel();
            Op134 = new javax.swing.JLabel();
            Op234 = new javax.swing.JLabel();
            Op334 = new javax.swing.JLabel();
            Op434 = new javax.swing.JLabel();
            Op10V = new javax.swing.JLabel();
            Op20V = new javax.swing.JLabel();
            Op32V = new javax.swing.JLabel();
            Op03V = new javax.swing.JLabel();
            Btn23 = new javax.swing.JButton();
            Btn88 = new javax.swing.JButton();
            Btn50 = new javax.swing.JButton();
            Btn51 = new javax.swing.JButton();
            Btn52 = new javax.swing.JButton();
            Btn53 = new javax.swing.JButton();
            Btn54 = new javax.swing.JButton();
            Btn60 = new javax.swing.JButton();
            Btn61 = new javax.swing.JButton();
            Btn62 = new javax.swing.JButton();
            Btn63 = new javax.swing.JButton();
            Btn64 = new javax.swing.JButton();
            Btn70 = new javax.swing.JButton();
            Btn71 = new javax.swing.JButton();
            Btn72 = new javax.swing.JButton();
            Btn73 = new javax.swing.JButton();
            Btn74 = new javax.swing.JButton();
            Btn55 = new javax.swing.JButton();
            Btn80 = new javax.swing.JButton();
            Btn85 = new javax.swing.JButton();
            Btn81 = new javax.swing.JButton();
            Btn75 = new javax.swing.JButton();
            Btn82 = new javax.swing.JButton();
            Btn83 = new javax.swing.JButton();
            Btn65 = new javax.swing.JButton();
            Btn45 = new javax.swing.JButton();
            Btn84 = new javax.swing.JButton();
            Btn35 = new javax.swing.JButton();
            Btn25 = new javax.swing.JButton();
            Btn15 = new javax.swing.JButton();
            Btn05 = new javax.swing.JButton();
            Btn06 = new javax.swing.JButton();
            Btn16 = new javax.swing.JButton();
            Btn26 = new javax.swing.JButton();
            Btn36 = new javax.swing.JButton();
            Btn46 = new javax.swing.JButton();
            Btn56 = new javax.swing.JButton();
            Btn66 = new javax.swing.JButton();
            Btn76 = new javax.swing.JButton();
            Btn86 = new javax.swing.JButton();
            Btn87 = new javax.swing.JButton();
            Btn77 = new javax.swing.JButton();
            Btn67 = new javax.swing.JButton();
            Btn57 = new javax.swing.JButton();
            Btn47 = new javax.swing.JButton();
            Btn37 = new javax.swing.JButton();
            Btn27 = new javax.swing.JButton();
            Btn17 = new javax.swing.JButton();
            Btn08 = new javax.swing.JButton();
            Btn07 = new javax.swing.JButton();
            Btn18 = new javax.swing.JButton();
            Btn28 = new javax.swing.JButton();
            Btn38 = new javax.swing.JButton();
            Btn48 = new javax.swing.JButton();
            Btn58 = new javax.swing.JButton();
            Btn68 = new javax.swing.JButton();
            Btn78 = new javax.swing.JButton();
            Op501 = new javax.swing.JLabel();
            Op523 = new javax.swing.JLabel();
            Op534 = new javax.swing.JLabel();
            Op545 = new javax.swing.JLabel();
            Op556 = new javax.swing.JLabel();
            Op567 = new javax.swing.JLabel();
            Op578 = new javax.swing.JLabel();
            Op445 = new javax.swing.JLabel();
            Op456 = new javax.swing.JLabel();
            Op467 = new javax.swing.JLabel();
            Op478 = new javax.swing.JLabel();
            Op345 = new javax.swing.JLabel();
            Op356 = new javax.swing.JLabel();
            Op367 = new javax.swing.JLabel();
            Op378 = new javax.swing.JLabel();
            Op245 = new javax.swing.JLabel();
            Op256 = new javax.swing.JLabel();
            Op267 = new javax.swing.JLabel();
            Op278 = new javax.swing.JLabel();
            Op601 = new javax.swing.JLabel();
            Op612 = new javax.swing.JLabel();
            Op623 = new javax.swing.JLabel();
            Op634 = new javax.swing.JLabel();
            Op645 = new javax.swing.JLabel();
            Op656 = new javax.swing.JLabel();
            Op667 = new javax.swing.JLabel();
            Op678 = new javax.swing.JLabel();
            Op701 = new javax.swing.JLabel();
            Op712 = new javax.swing.JLabel();
            Op723 = new javax.swing.JLabel();
            Op734 = new javax.swing.JLabel();
            Op745 = new javax.swing.JLabel();
            Op756 = new javax.swing.JLabel();
            Op767 = new javax.swing.JLabel();
            Op778 = new javax.swing.JLabel();
            Op801 = new javax.swing.JLabel();
            Op812 = new javax.swing.JLabel();
            Op823 = new javax.swing.JLabel();
            Op834 = new javax.swing.JLabel();
            Op845 = new javax.swing.JLabel();
            Op856 = new javax.swing.JLabel();
            Op867 = new javax.swing.JLabel();
            Op878 = new javax.swing.JLabel();
            Op078 = new javax.swing.JLabel();
            Op145 = new javax.swing.JLabel();
            Op156 = new javax.swing.JLabel();
            Op167 = new javax.swing.JLabel();
            Op178 = new javax.swing.JLabel();
            Op045 = new javax.swing.JLabel();
            Op056 = new javax.swing.JLabel();
            Op067 = new javax.swing.JLabel();
            Op24V = new javax.swing.JLabel();
            Op25V = new javax.swing.JLabel();
            Op26V = new javax.swing.JLabel();
            Op27V = new javax.swing.JLabel();
            Op28V = new javax.swing.JLabel();
            Op35V = new javax.swing.JLabel();
            Op36V = new javax.swing.JLabel();
            Op37V = new javax.swing.JLabel();
            Op30V = new javax.swing.JLabel();
            Op40V = new javax.swing.JLabel();
            Op41V = new javax.swing.JLabel();
            Op42V = new javax.swing.JLabel();
            Op43V = new javax.swing.JLabel();
            Op44V = new javax.swing.JLabel();
            Op45V = new javax.swing.JLabel();
            Op46V = new javax.swing.JLabel();
            Op47V = new javax.swing.JLabel();
            Op48V = new javax.swing.JLabel();
            Op50V = new javax.swing.JLabel();
            Op51V = new javax.swing.JLabel();
            Op52V = new javax.swing.JLabel();
            Op53V = new javax.swing.JLabel();
            Op54V = new javax.swing.JLabel();
            Op55V = new javax.swing.JLabel();
            Op56V = new javax.swing.JLabel();
            Op57V = new javax.swing.JLabel();
            Op58V = new javax.swing.JLabel();
            Op60V = new javax.swing.JLabel();
            Op61V = new javax.swing.JLabel();
            Op62V = new javax.swing.JLabel();
            Op63V = new javax.swing.JLabel();
            Op64V = new javax.swing.JLabel();
            Op65V = new javax.swing.JLabel();
            Op66V = new javax.swing.JLabel();
            Op67V = new javax.swing.JLabel();
            Op68V = new javax.swing.JLabel();
            Op70V = new javax.swing.JLabel();
            Op71V = new javax.swing.JLabel();
            Op72V = new javax.swing.JLabel();
            Op73V = new javax.swing.JLabel();
            Op74V = new javax.swing.JLabel();
            Op75V = new javax.swing.JLabel();
            Op76V = new javax.swing.JLabel();
            Op77V = new javax.swing.JLabel();
            Op78V = new javax.swing.JLabel();
            Op04V = new javax.swing.JLabel();
            Op05V = new javax.swing.JLabel();
            Op06V = new javax.swing.JLabel();
            Op07V = new javax.swing.JLabel();
            Op08V = new javax.swing.JLabel();
            Op15V = new javax.swing.JLabel();
            Op16V = new javax.swing.JLabel();
            Op17V = new javax.swing.JLabel();
            Op18V = new javax.swing.JLabel();
            TerminarJuego = new javax.swing.JButton();
            Top10 = new javax.swing.JButton();
            BorrarJuego = new javax.swing.JButton();
            GuardarJuego = new javax.swing.JButton();
            CargarJuego = new javax.swing.JButton();
            CerrarButton = new javax.swing.JButton();
            relojFrame = new javax.swing.JPanel();
            jLabel5 = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            HorasTXT = new javax.swing.JTextField();
            MinutosTXT = new javax.swing.JTextField();
            SegundosTXT = new javax.swing.JTextField();
            IniciarJuego = new javax.swing.JButton();
            BorrarJugada = new javax.swing.JButton();
            PistaButton = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setBackground(new java.awt.Color(255, 255, 255));
            getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel2.setBackground(new java.awt.Color(153, 0, 0));
            jLabel2.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
            jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel2.setText("Futoshiki");
            jLabel2.setOpaque(true);
            getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 110, -1));

            jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            jLabel1.setText("NIVEL");
            getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

            jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            jLabel3.setText("Nombre del jugador");
            getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

            NivelTXT.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            NivelTXT.setText("a");
            NivelTXT.setToolTipText("");
            NivelTXT.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            getContentPane().add(NivelTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 190, 30));

            NombreTXT.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
            NombreTXT.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        NombreTXTActionPerformed(evt);
                  }
            });
            getContentPane().add(NombreTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 270, -1));

            Opciones.add(Btn2);
            Btn2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn2.setText("2");
            Btn2.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn2ActionPerformed(evt);
                  }
            });

            Opciones.add(Btn3);
            Btn3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn3.setText("3");

            Opciones.add(Btn1);
            Btn1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn1.setText("1");
            Btn1.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn1ActionPerformed(evt);
                  }
            });

            Opciones.add(Btn5);
            Btn5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn5.setText("5");

            Opciones.add(Btn4);
            Btn4.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn4.setText("4");
            Btn4.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn4ActionPerformed(evt);
                  }
            });

            Opciones.add(Btn6);
            Btn6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn6.setText("Borrar");

            javax.swing.GroupLayout NumbersPanelLayout = new javax.swing.GroupLayout(NumbersPanel);
            NumbersPanel.setLayout(NumbersPanelLayout);
            NumbersPanelLayout.setHorizontalGroup(
                  NumbersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(NumbersPanelLayout.createSequentialGroup()
                        .addGroup(NumbersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(NumbersPanelLayout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addGroup(NumbersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(Btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(Btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(Btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(Btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(Btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGroup(NumbersPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(Btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
            );
            NumbersPanelLayout.setVerticalGroup(
                  NumbersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(NumbersPanelLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(Btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );

            getContentPane().add(NumbersPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, -1, 340));

            RehacerJugada.setBackground(new java.awt.Color(255, 153, 0));
            RehacerJugada.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            RehacerJugada.setText("Redo");
            RehacerJugada.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        RehacerJugadaActionPerformed(evt);
                  }
            });
            getContentPane().add(RehacerJugada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 90, 30));

            jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            Btn03.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn03.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn03ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn03, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 40, 40));

            Btn04.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn04.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn04ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn04, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 40, 40));

            Btn10.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn10.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn10ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 40, 40));

            Btn11.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn11.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn11ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 40, 40));

            Btn12.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn12.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn12ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 40, 40));

            Btn13.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn13.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn13ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 40, 40));

            Btn14.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn14.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn14ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn14, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 40, 40));

            Btn00.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn00.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn00ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn00, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 27, 40, 40));

            Btn01.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn01.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn01ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn01, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 40, 40));

            Btn02.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn02.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn02ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn02, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 40, 40));

            Btn20.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn20.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn20ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 40, 40));

            Btn21.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn21.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn21ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn21, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 40, 40));

            Btn22.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn22.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn22ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn22, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 40, 40));

            Btn24.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn24.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn24ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn24, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 40, 40));

            Btn44.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn44.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn44ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn44, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 40, 40));

            Btn30.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn30.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn30ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 40, 40));

            Btn31.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn31.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn31ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 40, 40));

            Btn32.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn32.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn32ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn32, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 40, 40));

            Btn33.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn33.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn33ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn33, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 40, 40));

            Btn34.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn34.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn34ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn34, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 40, 40));

            Btn40.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn40.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn40ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 40, 40));

            Btn41.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn41.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn41ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn41, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 40, 40));

            Btn42.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn42.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn42ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn42, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 40, 40));

            Btn43.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn43.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn43ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn43, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 40, 40));

            Op01V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op01V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op01V.setText("∧");
            jPanel1.add(Op01V, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

            Op11V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op11V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op11V.setText("∧");
            jPanel1.add(Op11V, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));

            Op21V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op21V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op21V.setText("∧");
            jPanel1.add(Op21V, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, -1));

            Op31V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op31V.setText("∧");
            jPanel1.add(Op31V, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, -1));

            panelgrid.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            Op101.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op101.setText(">");
            panelgrid.add(Op101, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 20, -1));

            Op201.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op201.setText(">");
            panelgrid.add(Op201, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

            Op301.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op301.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op301.setText(">");
            panelgrid.add(Op301, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

            Op401.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op401.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op401.setText(">");
            Op401.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            panelgrid.add(Op401, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

            Op001.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op001.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op001.setText(">");
            Op001.setFocusable(false);
            Op001.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            panelgrid.add(Op001, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

            jPanel1.add(panelgrid, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, 350));

            Op12V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op12V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op12V.setText("∧");
            jPanel1.add(Op12V, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, -1));

            jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            Op012.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op012.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op012.setText(">");
            jPanel3.add(Op012, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

            Op112.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op112.setText(">");
            jPanel3.add(Op112, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

            Op212.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op212.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op212.setText(">");
            Op212.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel3.add(Op212, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, -1, -1));

            Op312.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op312.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op312.setText(">");
            jPanel3.add(Op312, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, -1, -1));

            Op412.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op412.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op412.setText(">");
            Op412.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel3.add(Op412, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, -1));

            Op512.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op512.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op512.setText(">");
            Op512.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel3.add(Op512, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, -1, -1));

            jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 20, 400));

            Op13V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op13V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op13V.setText("∧");
            jPanel1.add(Op13V, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

            Op23V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op23V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op23V.setText("∧");
            jPanel1.add(Op23V, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, -1, -1));

            Op33V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op33V.setText("∧");
            jPanel1.add(Op33V, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, -1, -1));

            jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            Op023.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op023.setText(">");
            jPanel4.add(Op023, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 46, -1));

            Op123.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op123.setText(">");
            jPanel4.add(Op123, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

            Op223.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op223.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op223.setText(">");
            jPanel4.add(Op223, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, -1, -1));

            Op323.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op323.setText(">");
            jPanel4.add(Op323, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, -1));

            Op423.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op423.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op423.setText(">");
            jPanel4.add(Op423, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, -1, -1));

            jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 20, 340));

            Op14V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op14V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op14V.setText("∧");
            jPanel1.add(Op14V, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, -1));

            Op38V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op38V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op38V.setText("∧");
            jPanel1.add(Op38V, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, -1, -1));

            Op34V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op34V.setText("∧");
            jPanel1.add(Op34V, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

            Op00V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op00V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op00V.setText("∧");
            jPanel1.add(Op00V, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

            Op02V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op02V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op02V.setText("∧");
            jPanel1.add(Op02V, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

            Op22V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op22V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op22V.setText("∧");
            jPanel1.add(Op22V, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, -1, -1));

            jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            Op034.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op034.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op034.setText(">");
            jPanel6.add(Op034, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 20, -1));

            Op134.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op134.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op134.setText(">");
            jPanel6.add(Op134, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

            Op234.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op234.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op234.setText(">");
            jPanel6.add(Op234, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, -1, -1));

            Op334.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op334.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op334.setText(">");
            jPanel6.add(Op334, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, -1));

            Op434.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op434.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op434.setText(">");
            jPanel6.add(Op434, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, -1, -1));

            jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 30, 340));

            Op10V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op10V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op10V.setText("∧");
            jPanel1.add(Op10V, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

            Op20V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op20V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op20V.setText("∧");
            jPanel1.add(Op20V, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

            Op32V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op32V.setText("∧");
            jPanel1.add(Op32V, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, -1, -1));

            Op03V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op03V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op03V.setText("∧");
            jPanel1.add(Op03V, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

            Btn23.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn23.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn23ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn23, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 40, 40));

            Btn88.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn88.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn88ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn88, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 590, 40, 40));

            Btn50.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn50.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn50ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 40, 40));

            Btn51.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn51.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn51ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn51, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 40, 40));

            Btn52.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn52.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn52ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn52, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 40, 40));

            Btn53.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn53.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn53ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn53, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 40, 40));

            Btn54.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn54.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn54ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn54, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 40, 40));

            Btn60.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn60.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn60ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 40, 40));

            Btn61.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn61.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn61ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn61, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 40, 40));

            Btn62.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn62.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn62ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn62, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 40, 40));

            Btn63.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn63.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn63ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn63, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, 40, 40));

            Btn64.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn64.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn64ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn64, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, 40, 40));

            Btn70.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn70.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn70ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn70, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 40, 40));

            Btn71.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn71.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn71ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn71, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 520, 40, 40));

            Btn72.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn72.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn72ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn72, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, 40, 40));

            Btn73.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn73.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn73ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn73, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 40, 40));

            Btn74.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn74.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn74ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn74, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 520, 40, 40));

            Btn55.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn55.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn55ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn55, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 40, 40));

            Btn80.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn80.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn80ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn80, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 40, 40));

            Btn85.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn85.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn85ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn85, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 590, 40, 40));

            Btn81.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn81.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn81ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn81, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 590, 40, 40));

            Btn75.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn75.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn75ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn75, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 520, 40, 40));

            Btn82.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn82.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn82ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn82, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 590, 40, 40));

            Btn83.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn83.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn83ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn83, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 590, 40, 40));

            Btn65.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn65.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn65ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn65, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, 40, 40));

            Btn45.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn45.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn45ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn45, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 40, 40));

            Btn84.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn84.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn84ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn84, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 590, 40, 40));

            Btn35.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn35.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn35ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn35, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 40, 40));

            Btn25.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn25.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn25ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn25, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 40, 40));

            Btn15.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn15.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn15ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn15, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 40, 40));

            Btn05.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn05.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn05ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn05, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 40, 40));

            Btn06.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn06.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn06ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn06, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 40, 40));

            Btn16.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn16.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn16ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn16, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 40, 40));

            Btn26.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn26.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn26ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn26, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 40, 40));

            Btn36.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn36.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn36ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn36, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 40, 40));

            Btn46.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn46.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn46ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn46, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 40, 40));

            Btn56.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn56.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn56ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn56, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 380, 40, 40));

            Btn66.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn66.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn66ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn66, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, 40, 40));

            Btn76.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn76.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn76ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn76, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 520, 40, 40));

            Btn86.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn86.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn86ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn86, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 590, 40, 40));

            Btn87.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn87.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn87ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn87, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 590, 40, 40));

            Btn77.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn77.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn77ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn77, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 520, 40, 40));

            Btn67.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn67.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn67ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn67, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 450, 40, 40));

            Btn57.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn57.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn57ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn57, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 40, 40));

            Btn47.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn47.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn47ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn47, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 40, 40));

            Btn37.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn37.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn37ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn37, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 40, 40));

            Btn27.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn27.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn27ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn27, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, 40, 40));

            Btn17.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn17.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn17ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn17, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 40, 40));

            Btn08.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn08.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn08ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn08, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 40, 40));

            Btn07.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn07.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn07ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn07, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 40, 40));

            Btn18.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn18.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn18ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 40, 40));

            Btn28.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn28.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn28ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn28, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 40, 40));

            Btn38.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn38.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn38ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn38, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 40, 40));

            Btn48.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn48.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn48ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn48, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, 40, 40));

            Btn58.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn58.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn58ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn58, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, 40, 40));

            Btn68.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn68.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn68ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn68, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 450, 40, 40));

            Btn78.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
            Btn78.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn78ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn78, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 520, 40, 40));

            Op501.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op501.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op501.setText(">");
            Op501.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op501, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

            Op523.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op523.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op523.setText(">");
            Op523.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op523, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, -1, -1));

            Op534.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op534.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op534.setText(">");
            Op534.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op534, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, -1, -1));

            Op545.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op545.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op545.setText(">");
            Op545.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op545, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, -1, -1));

            Op556.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op556.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op556.setText(">");
            Op556.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op556, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, -1, -1));

            Op567.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op567.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op567.setText(">");
            Op567.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op567, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, -1, -1));

            Op578.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op578.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op578.setText(">");
            Op578.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op578, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, -1, -1));

            Op445.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op445.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op445.setText(">");
            Op445.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op445, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, -1, -1));

            Op456.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op456.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op456.setText(">");
            Op456.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op456, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, -1, -1));

            Op467.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op467.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op467.setText(">");
            Op467.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op467, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, -1, -1));

            Op478.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op478.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op478.setText(">");
            Op478.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op478, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, -1, -1));

            Op345.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op345.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op345.setText(">");
            Op345.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op345, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

            Op356.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op356.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op356.setText(">");
            Op356.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op356, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, -1, -1));

            Op367.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op367.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op367.setText(">");
            Op367.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op367, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, -1, -1));

            Op378.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op378.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op378.setText(">");
            Op378.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op378, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, -1, -1));

            Op245.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op245.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op245.setText(">");
            Op245.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op245, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, -1, -1));

            Op256.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op256.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op256.setText(">");
            Op256.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op256, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, -1, -1));

            Op267.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op267.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op267.setText(">");
            Op267.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op267, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, -1, -1));

            Op278.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op278.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op278.setText(">");
            Op278.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op278, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, -1, -1));

            Op601.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op601.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op601.setText(">");
            Op601.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op601, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, -1));

            Op612.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op612.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op612.setText(">");
            Op612.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op612, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, -1, -1));

            Op623.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op623.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op623.setText(">");
            Op623.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op623, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, -1, -1));

            Op634.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op634.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op634.setText(">");
            Op634.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op634, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, -1, -1));

            Op645.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op645.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op645.setText(">");
            Op645.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op645, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 450, -1, -1));

            Op656.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op656.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op656.setText(">");
            Op656.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op656, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, -1, -1));

            Op667.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op667.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op667.setText(">");
            Op667.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op667, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, -1, -1));

            Op678.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op678.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op678.setText(">");
            Op678.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op678, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, -1, -1));

            Op701.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op701.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op701.setText(">");
            Op701.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op701, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, -1, -1));

            Op712.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op712.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op712.setText(">");
            Op712.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op712, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 520, -1, -1));

            Op723.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op723.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op723.setText(">");
            Op723.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op723, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 520, -1, -1));

            Op734.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op734.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op734.setText(">");
            Op734.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op734, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, -1, -1));

            Op745.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op745.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op745.setText(">");
            Op745.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op745, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, -1, -1));

            Op756.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op756.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op756.setText(">");
            Op756.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op756, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 520, -1, -1));

            Op767.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op767.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op767.setText(">");
            Op767.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op767, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 520, -1, -1));

            Op778.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op778.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op778.setText(">");
            Op778.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op778, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 520, -1, -1));

            Op801.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op801.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op801.setText(">");
            Op801.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op801, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, -1, -1));

            Op812.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op812.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op812.setText(">");
            Op812.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op812, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 590, -1, -1));

            Op823.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op823.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op823.setText(">");
            Op823.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op823, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, -1, -1));

            Op834.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op834.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op834.setText(">");
            Op834.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op834, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 590, -1, -1));

            Op845.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op845.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op845.setText(">");
            Op845.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op845, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 590, -1, -1));

            Op856.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op856.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op856.setText(">");
            Op856.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op856, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 590, -1, -1));

            Op867.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op867.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op867.setText(">");
            Op867.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op867, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 590, 20, -1));

            Op878.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op878.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op878.setText(">");
            Op878.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op878, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 590, -1, -1));

            Op078.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op078.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op078.setText(">");
            Op078.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op078, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, -1, -1));

            Op145.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op145.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op145.setText(">");
            Op145.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op145, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

            Op156.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op156.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op156.setText(">");
            Op156.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op156, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

            Op167.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op167.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op167.setText(">");
            Op167.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op167, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, -1, -1));

            Op178.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op178.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op178.setText(">");
            Op178.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op178, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, -1, -1));

            Op045.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op045.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op045.setText(">");
            Op045.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op045, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));

            Op056.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op056.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op056.setText(">");
            Op056.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op056, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

            Op067.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op067.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op067.setText(">");
            Op067.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel1.add(Op067, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, -1, -1));

            Op24V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op24V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op24V.setText("∧");
            jPanel1.add(Op24V, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, -1, -1));

            Op25V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op25V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op25V.setText("∧");
            jPanel1.add(Op25V, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, -1, -1));

            Op26V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op26V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op26V.setText("∧");
            jPanel1.add(Op26V, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, -1, -1));

            Op27V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op27V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op27V.setText("∧");
            jPanel1.add(Op27V, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, -1, -1));

            Op28V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op28V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op28V.setText("∧");
            jPanel1.add(Op28V, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, -1, -1));

            Op35V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op35V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op35V.setText("∧");
            jPanel1.add(Op35V, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, -1, -1));

            Op36V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op36V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op36V.setText("∧");
            jPanel1.add(Op36V, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, -1));

            Op37V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op37V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op37V.setText("∧");
            jPanel1.add(Op37V, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, -1, -1));

            Op30V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op30V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op30V.setText("∧");
            jPanel1.add(Op30V, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

            Op40V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op40V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op40V.setText("∧");
            jPanel1.add(Op40V, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

            Op41V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op41V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op41V.setText("∧");
            jPanel1.add(Op41V, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, -1, -1));

            Op42V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op42V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op42V.setText("∧");
            jPanel1.add(Op42V, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, -1, -1));

            Op43V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op43V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op43V.setText("∧");
            jPanel1.add(Op43V, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, -1, -1));

            Op44V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op44V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op44V.setText("∧");
            jPanel1.add(Op44V, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, -1, -1));

            Op45V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op45V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op45V.setText("∧");
            jPanel1.add(Op45V, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

            Op46V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op46V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op46V.setText("∧");
            jPanel1.add(Op46V, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, -1, -1));

            Op47V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op47V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op47V.setText("∧");
            jPanel1.add(Op47V, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, -1, -1));

            Op48V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op48V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op48V.setText("∧");
            jPanel1.add(Op48V, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, -1, -1));

            Op50V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op50V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op50V.setText("∧");
            jPanel1.add(Op50V, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

            Op51V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op51V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op51V.setText("∧");
            jPanel1.add(Op51V, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, -1, -1));

            Op52V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op52V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op52V.setText("∧");
            jPanel1.add(Op52V, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, -1, -1));

            Op53V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op53V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op53V.setText("∧");
            jPanel1.add(Op53V, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, -1, -1));

            Op54V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op54V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op54V.setText("∧");
            jPanel1.add(Op54V, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, -1, -1));

            Op55V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op55V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op55V.setText("∧");
            jPanel1.add(Op55V, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, -1, -1));

            Op56V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op56V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op56V.setText("∧");
            jPanel1.add(Op56V, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, -1, -1));

            Op57V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op57V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op57V.setText("∧");
            jPanel1.add(Op57V, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, -1, -1));

            Op58V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op58V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op58V.setText("∧");
            jPanel1.add(Op58V, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 420, -1, -1));

            Op60V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op60V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op60V.setText("∧");
            jPanel1.add(Op60V, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

            Op61V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op61V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op61V.setText("∧");
            jPanel1.add(Op61V, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 490, -1, -1));

            Op62V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op62V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op62V.setText("∧");
            jPanel1.add(Op62V, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 490, -1, -1));

            Op63V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op63V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op63V.setText("∧");
            jPanel1.add(Op63V, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 490, -1, -1));

            Op64V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op64V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op64V.setText("∧");
            jPanel1.add(Op64V, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 490, -1, -1));

            Op65V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op65V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op65V.setText("∧");
            jPanel1.add(Op65V, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, -1, -1));

            Op66V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op66V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op66V.setText("∧");
            jPanel1.add(Op66V, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 490, -1, -1));

            Op67V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op67V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op67V.setText("∧");
            jPanel1.add(Op67V, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 490, -1, -1));

            Op68V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op68V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op68V.setText("∧");
            jPanel1.add(Op68V, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 490, -1, -1));

            Op70V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op70V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op70V.setText("∧");
            jPanel1.add(Op70V, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

            Op71V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op71V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op71V.setText("∧");
            jPanel1.add(Op71V, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, -1, -1));

            Op72V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op72V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op72V.setText("∧");
            jPanel1.add(Op72V, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

            Op73V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op73V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op73V.setText("∧");
            jPanel1.add(Op73V, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 560, -1, -1));

            Op74V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op74V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op74V.setText("∧");
            jPanel1.add(Op74V, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 560, -1, -1));

            Op75V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op75V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op75V.setText("∧");
            jPanel1.add(Op75V, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 560, -1, -1));

            Op76V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op76V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op76V.setText("∧");
            jPanel1.add(Op76V, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, -1, -1));

            Op77V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op77V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op77V.setText("∧");
            jPanel1.add(Op77V, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 560, -1, -1));

            Op78V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op78V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op78V.setText("∧");
            jPanel1.add(Op78V, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 560, -1, -1));

            Op04V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op04V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op04V.setText("∧");
            jPanel1.add(Op04V, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

            Op05V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op05V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op05V.setText("∧");
            jPanel1.add(Op05V, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

            Op06V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op06V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op06V.setText("∧");
            jPanel1.add(Op06V, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

            Op07V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op07V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op07V.setText("∧");
            jPanel1.add(Op07V, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, -1));

            Op08V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op08V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op08V.setText("∧");
            jPanel1.add(Op08V, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, -1, -1));

            Op15V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op15V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op15V.setText("∧");
            jPanel1.add(Op15V, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, -1, -1));

            Op16V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op16V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op16V.setText("∧");
            jPanel1.add(Op16V, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, -1, -1));

            Op17V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op17V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op17V.setText("∧");
            jPanel1.add(Op17V, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, -1, -1));

            Op18V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op18V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op18V.setText("∧");
            jPanel1.add(Op18V, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));

            getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 590, 630));

            TerminarJuego.setBackground(new java.awt.Color(0, 153, 0));
            TerminarJuego.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            TerminarJuego.setText("Terminar Juego");
            TerminarJuego.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        TerminarJuegoActionPerformed(evt);
                  }
            });
            getContentPane().add(TerminarJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 520, 160, 30));

            Top10.setBackground(new java.awt.Color(255, 255, 0));
            Top10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Top10.setText("Top 10");
            Top10.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Top10ActionPerformed(evt);
                  }
            });
            getContentPane().add(Top10, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 560, -1, 30));

            BorrarJuego.setBackground(new java.awt.Color(153, 204, 255));
            BorrarJuego.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            BorrarJuego.setText("Borrar Juego");
            BorrarJuego.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        BorrarJuegoActionPerformed(evt);
                  }
            });
            getContentPane().add(BorrarJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 480, 140, 30));

            GuardarJuego.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            GuardarJuego.setText("Guardar Juego");
            GuardarJuego.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        GuardarJuegoActionPerformed(evt);
                  }
            });
            getContentPane().add(GuardarJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 600, -1, -1));

            CargarJuego.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            CargarJuego.setText("Cargar Juego");
            CargarJuego.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        CargarJuegoActionPerformed(evt);
                  }
            });
            getContentPane().add(CargarJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 640, 150, -1));

            CerrarButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
            CerrarButton.setText("Cerrar");
            CerrarButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        CerrarButtonActionPerformed(evt);
                  }
            });
            getContentPane().add(CerrarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 680, 100, 30));

            relojFrame.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            relojFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
            jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel5.setText("Horas");
            jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            relojFrame.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 67, 24));

            jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
            jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel7.setText("Segundos");
            jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            relojFrame.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 0, 69, 24));

            jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
            jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel6.setText("Minutos");
            jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            relojFrame.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 0, 65, 24));

            HorasTXT.setText("0");
            HorasTXT.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        HorasTXTActionPerformed(evt);
                  }
            });
            relojFrame.add(HorasTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 30, -1));

            MinutosTXT.setText("0");
            relojFrame.add(MinutosTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 30, -1));

            SegundosTXT.setText("0");
            relojFrame.add(SegundosTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 30, -1));

            getContentPane().add(relojFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 270, 60));

            IniciarJuego.setBackground(new java.awt.Color(204, 0, 0));
            IniciarJuego.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            IniciarJuego.setText("Iniciar Juego");
            IniciarJuego.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        IniciarJuegoActionPerformed(evt);
                  }
            });
            getContentPane().add(IniciarJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 440, 140, 30));

            BorrarJugada.setBackground(new java.awt.Color(0, 204, 255));
            BorrarJugada.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            BorrarJugada.setText("Undo");
            BorrarJugada.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        BorrarJugadaActionPerformed(evt);
                  }
            });
            getContentPane().add(BorrarJugada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 90, 30));

            PistaButton.setBackground(new java.awt.Color(0, 204, 0));
            PistaButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            PistaButton.setText("Pista");
            PistaButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        PistaButtonActionPerformed(evt);
                  }
            });
            getContentPane().add(PistaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 90, -1));

            pack();
      }// </editor-fold>//GEN-END:initComponents


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
                  HorasTXT.setText(Integer.toString(hours)); // calcula el número de horas, minutos y segundos que deben ser desplegados
                  MinutosTXT.setText(Integer.toString(minutes));
                  SegundosTXT.setText(Integer.toString(seconds));
                  count ++; // incrementa el valor del contador cada segundo
            }
        };
        stopwatch = new Timer(delay, action);
        stopwatch.setInitialDelay(0);
        stopwatch.start(); // empieza el reloj
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
                              HorasTXT.setText(horassave);
                              MinutosTXT.setText(minutossave);
                              SegundosTXT.setText(segundossave);
                              count = Integer.valueOf(HorasTXT.getText()) * 3600 + Integer.valueOf(MinutosTXT.getText()) * 60 + Integer.valueOf(SegundosTXT.getText());
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
                        HorasTXT.setText(Integer.toString(hours));
                        MinutosTXT.setText(Integer.toString(minutes));
                        SegundosTXT.setText(Integer.toString(seconds));
                        count --;
                }
            }
        };
        stopwatch = new Timer(delay, action);
        stopwatch.setInitialDelay(0);
        stopwatch.start();
    }
      
      private void Btn01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn01ActionPerformed
            // TODO add your handling code here:
            if (!started) {// si el juego no ha empezado, los botones no hacen nada
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 0,1, this); // verifica si ya se ganó el juego
            if (win){
                  RegisterWin(); // si sí, llama la función para detener el juego y registrarlo en el top 10
            }
      }//GEN-LAST:event_Btn01ActionPerformed

      private void Btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn10ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 1,0, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn10ActionPerformed

      private void Btn00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn00ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 0,0, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn00ActionPerformed

    /**
     * Función que obtiene el texto del botón seleccioando
     * @return texto del botón seleccionado
     */
    public String getSelectedButtonText() {
        for (Enumeration<AbstractButton> buttons = Opciones.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
      // está función inicia el juego
      private void IniciarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarJuegoActionPerformed
            // TODO add your handling code here:
            if (!started) { // si no ha empezado el juego
                  if (NombreTXT.getText().length() < 1 || NombreTXT.getText().length() > 20){ // se valida el nombre del jugador
                      JOptionPane.showMessageDialog(this, "El nombre debe tener una longitud entre 1 y 20 caracteres.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  started = true; // se empieza 
                  juego = new Juego(partida, NombreTXT.getText(), casillas); // se crea el nuevo juego
                  
                  NombreTXT.setEditable(false);
                  // si hay timer
                  
                  Btn1.setSelected(true); // se selecciona el 1 como número para poner predeterminado 
                  IniciarJuego.setEnabled(false); // se desactiva este botón y se activan los demás
                  TerminarJuego.setEnabled(true);
                  GuardarJuego.setEnabled(true);
                  RehacerJugada.setEnabled(true);
                  BorrarJugada.setEnabled(true);
                  TerminarJuego.setEnabled(true);
                  BorrarJuego.setEnabled(true);
                  PistaButton.setEnabled(true);
                  if (Configuracion.getReloj().equals("Timer")){ // si hay un timer, se empieza a correr
                        HorasTXT.setEditable(false);
                        MinutosTXT.setEditable(false);
                        SegundosTXT.setEditable(false);
                        horassave = HorasTXT.getText();
                        minutossave = MinutosTXT.getText();
                        segundossave = SegundosTXT.getText();
                        
                        count = Integer.valueOf(HorasTXT.getText()) * 3600 + Integer.valueOf(MinutosTXT.getText()) * 60 + Integer.valueOf(SegundosTXT.getText()); // se calcula la cantidad de segundos totales con los que se pueden jugar.
                        startTimer(this);
                  } else if (Configuracion.getReloj().equals("Sí")){ // si hay un reloj, se empieza a correr
                        HorasTXT.setEditable(false);
                        MinutosTXT.setEditable(false);
                        SegundosTXT.setEditable(false);
                        startClock(this);
                  }
            }
      }//GEN-LAST:event_IniciarJuegoActionPerformed

      private void Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn1ActionPerformed
            // TODO add your handling code here:
            
      }//GEN-LAST:event_Btn1ActionPerformed

      private void Btn02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn02ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 0,2, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn02ActionPerformed

      private void Btn03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn03ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 0,3, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn03ActionPerformed

      private void Btn04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn04ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 0,4, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn04ActionPerformed

      private void Btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn11ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 1,1, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn11ActionPerformed

      private void Btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn12ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 1,2, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn12ActionPerformed

      private void Btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn13ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 1,3, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn13ActionPerformed

      private void Btn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn14ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 1,4, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn14ActionPerformed

      private void Btn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn20ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 2,0, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn20ActionPerformed

      private void Btn21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn21ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 2,1, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn21ActionPerformed

      private void Btn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn22ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 2,2, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn22ActionPerformed

      private void Btn23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn23ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 2,3, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn23ActionPerformed

      private void Btn24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn24ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 2,4, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn24ActionPerformed

      private void Btn30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn30ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 3,0, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn30ActionPerformed

      private void Btn31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn31ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 3,1, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn31ActionPerformed

      private void Btn32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn32ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 3,2, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn32ActionPerformed

      private void Btn33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn33ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 3,3, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn33ActionPerformed

      private void Btn34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn34ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 3,4, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn34ActionPerformed

      private void Btn40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn40ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 4,0, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn40ActionPerformed

      private void Btn41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn41ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 4,1, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn41ActionPerformed

      private void Btn42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn42ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 4,2, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn42ActionPerformed

      private void Btn43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn43ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 4,3, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn43ActionPerformed

      private void Btn44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn44ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 4,4, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn44ActionPerformed

      // esta función llama a la función de borrar jugada
      private void BorrarJugadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarJugadaActionPerformed
            // TODO add your handling code here:
            juego.borrarJugada(this);
      }//GEN-LAST:event_BorrarJugadaActionPerformed

       // esta función llama a la función de rehacer jugada
      private void RehacerJugadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RehacerJugadaActionPerformed
            // TODO add your handling code here:
            juego.rehacerJugada(this);
      }//GEN-LAST:event_RehacerJugadaActionPerformed

      private void Btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn4ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn4ActionPerformed

     // esta función termina el juego y empieza otro
    private void TerminarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerminarJuegoActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Sí",
                            "No"};
        int respuesta = JOptionPane.showOptionDialog(this,
            "¿Está seguro de terminar el juego (Sí o No)?",
            "Terminar juego",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[0]); //default button title
        if (respuesta == JOptionPane.YES_OPTION){
            IniciarJuego.setEnabled(true); // se reactiva el botón de iniciar juego
            TerminarJuego.setEnabled(false); // se desactiva este botón
            resetTablero(); // se borrar todo el tablero
            establecerPartida(); // se establece una nueva partida
            establecerTablero(); // se establece el tablero con la nueva partida
            NombreTXT.setEditable(true);
            started = false;
            if (Configuracion.getReloj().equals("Timer")) { // si había un timer o reloj, se detiene y reinicia el contador
                  stopwatch.stop();
                  HorasTXT.setEditable(true);
                  MinutosTXT.setEditable(true);
                  SegundosTXT.setEditable(true);
                  HorasTXT.setText(horassave);
                  MinutosTXT.setText(minutossave);
                  SegundosTXT.setText(segundossave);
                  count = Reloj.getHorasInt() * 3600 + Reloj.getMinutosInt() * 60 + Reloj.getSegundosInt();
            } else if(Configuracion.getReloj().equals("Sí")){
                  stopwatch.stop();
                  count = 0;
            }
            // se desactivan todos los otros botones
            GuardarJuego.setEnabled(false);
            RehacerJugada.setEnabled(false);
            BorrarJugada.setEnabled(false);
            TerminarJuego.setEnabled(false);
            BorrarJuego.setEnabled(false);
        }
    }//GEN-LAST:event_TerminarJuegoActionPerformed

    // esta función cierra la pestaña del juego
      private void CerrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarButtonActionPerformed
            // TODO add your handling code here:
            int input = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el juego y perder el progreso?", "Selecione una opción...",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            // 0=sí, 1=no, 
            if (input == 0){ // cierra la pestaña
                  started = false;
                  if (!("No".equals(Configuracion.getReloj())) && stopwatch != null){
                        stopwatch.stop();
                  }
                  dispose();
                  Menu window = new Menu();
                  window.setVisible(true);
            }
      }//GEN-LAST:event_CerrarButtonActionPerformed

      // esta función borra el juego
      private void BorrarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarJuegoActionPerformed
            // TODO add your handling code here:
            int input = JOptionPane.showConfirmDialog(null, "¿Desea borrar el juego?", "Selecione una opción...", 
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE); // pide confirmación de borrar el juego
            // 0=sí, 1=no, 
            if (input == 0){ // si sí,                  
                  resetTablero();
                  establecerTablero();
                  if (Configuracion.getReloj().equals("Sí")){
                        stopwatch.stop(); // se detiene el reloj
                        HorasTXT.setText("0"); // se restablecen todos los valores
                        MinutosTXT.setText("0");
                        SegundosTXT.setText("0");
                        count = 0;
                        stopwatch.start(); // se reinicia el reloj
                  } else if (Configuracion.getReloj().equals("Timer")){
                        stopwatch.stop(); // 
                        HorasTXT.setText(horassave);
                        MinutosTXT.setText(minutossave);
                        SegundosTXT.setText(segundossave);
                        count = Integer.valueOf(HorasTXT.getText()) * 3600 + Integer.valueOf(MinutosTXT.getText()) * 60 + Integer.valueOf(SegundosTXT.getText());
                        stopwatch.start();
                  }

                  JOptionPane.showMessageDialog(this, "Juego eliminado existosamente."); // se da un mensaje de éxito
                  
            }
      }//GEN-LAST:event_BorrarJuegoActionPerformed

      // esta función abre la pestaña de top 10. No cierra la del juego para que no se pierda el progreso.
      private void Top10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Top10ActionPerformed
            // TODO add your handling code here:
            Top10Frame window = new Top10Frame();
            window.setVisible(true);
      }//GEN-LAST:event_Top10ActionPerformed

      private void HorasTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HorasTXTActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_HorasTXTActionPerformed

      private void NombreTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreTXTActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_NombreTXTActionPerformed

      // esta función registra la victoria del juego y la escribe en la lista de top 10
      private void RegisterWin(){
            if (Configuracion.getReloj().equals("Sí") || Configuracion.getReloj().equals("Timer")){
                  stopwatch.stop(); // detiene el contador
                  int horas; // obtiene el tiempo actual
                  int minutos;
                  int segundos;
                  int tiemponow;
                  horas = Integer.valueOf(HorasTXT.getText());
                  minutos = Integer.valueOf(MinutosTXT.getText());
                  segundos = Integer.valueOf(SegundosTXT.getText());
                  tiemponow = horas *3600 + minutos * 60 + segundos;
                  
                  if (Configuracion.getReloj().equals("Timer")){ // si el reloj era un timer, el reloj tiempo actual es lo que falta, no lo que se duró
                        if (Configuracion.getNivel().equals("Multinivel") && !nivel.equals("Fácil")) {
                              int tiempoantes = horasMulti * 3600 + minutosMulti * 60 + segundosMulti;
                              tiemponow = tiempoantes - tiemponow;
                        } else {
                              int horasog = Integer.valueOf(horassave);
                              int minutosog = Integer.valueOf(minutossave);
                              int segundosog = Integer.valueOf(segundossave);
                              int tiempoog = horasog * 3600 + minutosog *60+ segundosog;
                              tiemponow  = tiempoog - tiemponow; // se obtiene el tiempo que se duró al restar el original con lo que queda.
                        }
                       
                  } else if (Configuracion.getNivel().equals("Multinivel") && !nivel.equals("Fácil")) {
                        int tiempoantes = horasMulti * 3600 + minutosMulti * 60+ segundosMulti;
                        tiemponow = tiemponow - tiempoantes;
                  }

                  // se crea una marca nueva
                  Marca marca = new Marca(NombreTXT.getText(), tiemponow, NivelTXT.getText());
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
                       fw = new FileWriter("src\\main\\java\\poo\\programa3\\futoshiki2022top10.dat");
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
            if (Configuracion.getNivel().equals("Multinivel")){
                  if (nivelCont != 3){
                        if (nivelCont == 1){
                              nivel = "Intermedio";
                        } else if (nivelCont == 2){
                              nivel = "Difícil";
                        }
                        NivelTXT.setText(nivel);
                        nivelCont++;
                        horasMulti = Integer.valueOf(HorasTXT.getText());
                        minutosMulti = Integer.valueOf(MinutosTXT.getText());
                        segundosMulti = Integer.valueOf(SegundosTXT.getText());
                        resetTablero();
                        establecerPartida();
                        establecerTablero();
                        juego = new Juego(partida, NombreTXT.getText(), casillas); // se crea el nuevo juego
                        if (Configuracion.getReloj().equals("Sí") || Configuracion.getReloj().equals("Timer")){
                              stopwatch.start();
                        }
                        return;
                  }
            }
            started = false; // detiene el juego
            GuardarJuego.setEnabled(false); // desactiva todos los botones
            RehacerJugada.setEnabled(false);
            BorrarJugada.setEnabled(false);
            TerminarJuego.setEnabled(false);
            BorrarJuego.setEnabled(false);
      }    
    
    private void GuardarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarJuegoActionPerformed
        // TODO add your handling code here:
        try{
            FileWriter save = new FileWriter("futoshiki2022juegoactual.dat"); // se escribe en el archivo
            save.write(Configuracion.getNivel() + "\n"); // se salva el nivel
            save.write(index + "\n"); // se salva el índice de la partida para volverla a desplegar luego
            save.write(Configuracion.getReloj() + "\n"); // se salva si hay reloj o no
            save.write(HorasTXT.getText() + "\n"); // se salvan las horas, minutos y segundos
            save.write(MinutosTXT.getText() + "\n");
            save.write(SegundosTXT.getText() + "\n");
            save.write(Boolean.toString(Configuracion.getLado()) + "\n"); // se salva el lado del panel
            save.write(NombreTXT.getText() + "\n"); // se salva el nombre del jugador
            save.write(horassave + "\n"); // se salva el nombre del jugador
            save.write(minutossave + "\n"); // se salva el nombre del jugador
            save.write(segundossave + "\n"); // se salva el nombre del jugador
            for (javax.swing.JButton[] fila : casillas){ // se salvan todas las casillas
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
            JOptionPane.showMessageDialog(this, "Partida guardada con éxito"); // se da un mensaje de éxito
        } catch (IOException e){
            JOptionPane.showMessageDialog(this, "No se pudo grabar la partida.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_GuardarJuegoActionPerformed

    private void CargarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarJuegoActionPerformed
        // TODO add your handling code here:
        if (started){ // si ya hay se está jugando, no se deja cargar un juevo
              JOptionPane.showMessageDialog(this, "Ya hay una partida en juego. Termine su partida y vuelva a intentar.", 
                "Error", JOptionPane.ERROR_MESSAGE);
              return;
        }
        try{ // se abre el archivo
            List<String> lineas = Files.readAllLines(Paths.get("futoshiki2022juegoactual.dat"));
            Configuracion.setNivel(lineas.get(0)); // se establece el nivel
            NivelTXT.setText(lineas.get(0)); 
            partida = Partida.getPartidasPorNivel().get(lineas.get(0)).get(Integer.parseInt(lineas.get(1))); // se obtiene la partida
            resetTablero(); // se borra el tablero
            establecerTablero(); // se vuelve a establecer el tablero con la partida recuperada
            Configuracion.setReloj(lineas.get(2)); // se obtiene el reloj
            
            // si se había guardado una partida con reloj o timer, se pone el frame
            if (Configuracion.getReloj().equals("Timer") || Configuracion.getReloj().equals("Sí")){
                relojFrame.setVisible(true);
            } else { // si no tiene reloj, se elimina.
                relojFrame.setVisible(true);
            }
            HorasTXT.setText(lineas.get(3));
            MinutosTXT.setText(lineas.get(4));
            SegundosTXT.setText(lineas.get(5));
            count = Integer.valueOf(HorasTXT.getText()) * 3600 + Integer.valueOf(MinutosTXT.getText()) * 60 + Integer.valueOf(SegundosTXT.getText()); // se calcula la cantidad de segundos totales con los que se pueden jugar.
            System.out.println("SEGUNDOS " + lineas.get(5));
            Configuracion.setLado(Boolean.parseBoolean(lineas.get(6))); // se obtiene el lado del 
            moveButtonPanel();
            NombreTXT.setText(lineas.get(7)); // se vuelve a poner el nombre
            horassave = lineas.get(8);
            minutossave = lineas.get(9);
            segundossave = lineas.get(10);
            
            for (int casilla = 0; casilla < 25; casilla++){ // se vuelven a poner los valores de las casillas
                casillas[casilla / 5][casilla % 5].setText(lineas.get(11 + casilla));
            }
            
            IniciarJuegoActionPerformed(evt); // se inicia el juego otra vez
            int indexList = 36;
            // establecemos las pilas
            System.out.println(lineas.get(indexList));
            Stack<String> temp = new Stack<>(); // pila temporal para luego conseguir el orden verdadero de la pila
            Stack<Jugada> jugadasStack = new Stack<>();
            while(!lineas.get(indexList).equals("J")){ // mientras no haya llegado al indicador de dónde termina la pila del final de la pila
                System.out.println(lineas.get(indexList));
                temp.push(lineas.get(indexList++)); // va metiendo a la pila temporal
            }
            while(!temp.isEmpty()){
                // como la pila se fue leyendo de atras para adelante, debemos popear los elementos para agregarlos en orden
                String[] entry = temp.pop().split(",");
                System.out.println(entry[0] + " " + entry[1] + " " + entry[2]);
                int fila = Integer.parseInt(entry[1]);
                int columna = Integer.parseInt(entry[2]);
                jugadasStack.push(new Jugada(fila, columna, entry[0]));
            }
            System.out.println(lineas.get(indexList));
            juego.setJugadas(jugadasStack);

            indexList++;
            temp = new Stack<>();
            Stack<Jugada> rehacerStack = new Stack<>();
            while(!lineas.get(indexList).equals("R")){ // mientras no haya llegado al indicador de dónde termina la pila del final de la pila
                System.out.println(lineas.get(indexList));
                temp.push(lineas.get(indexList++)); // va metiendo a la pila temporal
            }
            while(!temp.isEmpty()){
                // como la pila se fue leyendo de atras para adelante, debemos popear los elementos para agregarlos en orden
                String[] entry = temp.pop().split(",");
                System.out.println(entry[0] + " " + entry[1] + " " + entry[2]);
                int fila = Integer.parseInt(entry[1]);
                int columna = Integer.parseInt(entry[2]);
                rehacerStack.push(new Jugada(fila, columna, entry[0]));
            }
            System.out.println(lineas.get(indexList));
            juego.setRedoJugadas(rehacerStack);
        } catch (IOException e){
            JOptionPane.showMessageDialog(this, "La partida no se pudo cargar porque el archivo no existe.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_CargarJuegoActionPerformed

      private void Btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn2ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn2ActionPerformed

      private void Btn88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn88ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn88ActionPerformed

      private void Btn50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn50ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn50ActionPerformed

      private void Btn51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn51ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn51ActionPerformed

      private void Btn52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn52ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn52ActionPerformed

      private void Btn53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn53ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn53ActionPerformed

      private void Btn54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn54ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn54ActionPerformed

      private void Btn60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn60ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn60ActionPerformed

      private void Btn61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn61ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn61ActionPerformed

      private void Btn62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn62ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn62ActionPerformed

      private void Btn63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn63ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn63ActionPerformed

      private void Btn64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn64ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn64ActionPerformed

      private void Btn70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn70ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn70ActionPerformed

      private void Btn71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn71ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn71ActionPerformed

      private void Btn72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn72ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn72ActionPerformed

      private void Btn73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn73ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn73ActionPerformed

      private void Btn74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn74ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn74ActionPerformed

      private void Btn55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn55ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn55ActionPerformed

      private void Btn80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn80ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn80ActionPerformed

      private void Btn85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn85ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn85ActionPerformed

      private void Btn81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn81ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn81ActionPerformed

      private void Btn75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn75ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn75ActionPerformed

      private void Btn82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn82ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn82ActionPerformed

      private void Btn83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn83ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn83ActionPerformed

      private void Btn65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn65ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn65ActionPerformed

      private void Btn45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn45ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn45ActionPerformed

      private void Btn84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn84ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn84ActionPerformed

      private void Btn35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn35ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn35ActionPerformed

      private void Btn25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn25ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn25ActionPerformed

      private void Btn15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn15ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn15ActionPerformed

      private void Btn05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn05ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn05ActionPerformed

      private void Btn06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn06ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn06ActionPerformed

      private void Btn16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn16ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn16ActionPerformed

      private void Btn26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn26ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn26ActionPerformed

      private void Btn36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn36ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn36ActionPerformed

      private void Btn46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn46ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn46ActionPerformed

      private void Btn56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn56ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn56ActionPerformed

      private void Btn66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn66ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn66ActionPerformed

      private void Btn76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn76ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn76ActionPerformed

      private void Btn86ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn86ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn86ActionPerformed

      private void Btn87ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn87ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn87ActionPerformed

      private void Btn77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn77ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn77ActionPerformed

      private void Btn67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn67ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn67ActionPerformed

      private void Btn57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn57ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn57ActionPerformed

      private void Btn47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn47ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn47ActionPerformed

      private void Btn37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn37ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn37ActionPerformed

      private void Btn27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn27ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn27ActionPerformed

      private void Btn17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn17ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn17ActionPerformed

      private void Btn08ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn08ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn08ActionPerformed

      private void Btn07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn07ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn07ActionPerformed

      private void Btn18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn18ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn18ActionPerformed

      private void Btn28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn28ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn28ActionPerformed

      private void Btn38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn38ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn38ActionPerformed

      private void Btn48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn48ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn48ActionPerformed

      private void Btn58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn58ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn58ActionPerformed

      private void Btn68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn68ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn68ActionPerformed

      private void Btn78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn78ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn78ActionPerformed

      private void PistaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PistaButtonActionPerformed
            // TODO add your handling code here:
            Posibles window = new Posibles();
            window.setVisible(true);
      }//GEN-LAST:event_PistaButtonActionPerformed

      public static Juego getJuego(){
            return juego;
      }
      /**
       * @param args the command line arguments
       */
      public static void main(String args[]) {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
             */
            try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                              javax.swing.UIManager.setLookAndFeel(info.getClassName());
                              break;
                        }
                  }
            } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(JugarFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(JugarFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(JugarFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(JugarFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                        new JugarFrame().setVisible(true);
                  }
            });
      }

      // Variables declaration - do not modify//GEN-BEGIN:variables
      private javax.swing.JButton BorrarJuego;
      private javax.swing.JButton BorrarJugada;
      private javax.swing.JButton Btn00;
      private javax.swing.JButton Btn01;
      private javax.swing.JButton Btn02;
      private javax.swing.JButton Btn03;
      private javax.swing.JButton Btn04;
      private javax.swing.JButton Btn05;
      private javax.swing.JButton Btn06;
      private javax.swing.JButton Btn07;
      private javax.swing.JButton Btn08;
      private javax.swing.JToggleButton Btn1;
      private javax.swing.JButton Btn10;
      private javax.swing.JButton Btn11;
      private javax.swing.JButton Btn12;
      private javax.swing.JButton Btn13;
      private javax.swing.JButton Btn14;
      private javax.swing.JButton Btn15;
      private javax.swing.JButton Btn16;
      private javax.swing.JButton Btn17;
      private javax.swing.JButton Btn18;
      private javax.swing.JToggleButton Btn2;
      private javax.swing.JButton Btn20;
      private javax.swing.JButton Btn21;
      private javax.swing.JButton Btn22;
      private javax.swing.JButton Btn23;
      private javax.swing.JButton Btn24;
      private javax.swing.JButton Btn25;
      private javax.swing.JButton Btn26;
      private javax.swing.JButton Btn27;
      private javax.swing.JButton Btn28;
      private javax.swing.JToggleButton Btn3;
      private javax.swing.JButton Btn30;
      private javax.swing.JButton Btn31;
      private javax.swing.JButton Btn32;
      private javax.swing.JButton Btn33;
      private javax.swing.JButton Btn34;
      private javax.swing.JButton Btn35;
      private javax.swing.JButton Btn36;
      private javax.swing.JButton Btn37;
      private javax.swing.JButton Btn38;
      private javax.swing.JToggleButton Btn4;
      private javax.swing.JButton Btn40;
      private javax.swing.JButton Btn41;
      private javax.swing.JButton Btn42;
      private javax.swing.JButton Btn43;
      private javax.swing.JButton Btn44;
      private javax.swing.JButton Btn45;
      private javax.swing.JButton Btn46;
      private javax.swing.JButton Btn47;
      private javax.swing.JButton Btn48;
      private javax.swing.JToggleButton Btn5;
      private javax.swing.JButton Btn50;
      private javax.swing.JButton Btn51;
      private javax.swing.JButton Btn52;
      private javax.swing.JButton Btn53;
      private javax.swing.JButton Btn54;
      private javax.swing.JButton Btn55;
      private javax.swing.JButton Btn56;
      private javax.swing.JButton Btn57;
      private javax.swing.JButton Btn58;
      private javax.swing.JToggleButton Btn6;
      private javax.swing.JButton Btn60;
      private javax.swing.JButton Btn61;
      private javax.swing.JButton Btn62;
      private javax.swing.JButton Btn63;
      private javax.swing.JButton Btn64;
      private javax.swing.JButton Btn65;
      private javax.swing.JButton Btn66;
      private javax.swing.JButton Btn67;
      private javax.swing.JButton Btn68;
      private javax.swing.JButton Btn70;
      private javax.swing.JButton Btn71;
      private javax.swing.JButton Btn72;
      private javax.swing.JButton Btn73;
      private javax.swing.JButton Btn74;
      private javax.swing.JButton Btn75;
      private javax.swing.JButton Btn76;
      private javax.swing.JButton Btn77;
      private javax.swing.JButton Btn78;
      private javax.swing.JButton Btn80;
      private javax.swing.JButton Btn81;
      private javax.swing.JButton Btn82;
      private javax.swing.JButton Btn83;
      private javax.swing.JButton Btn84;
      private javax.swing.JButton Btn85;
      private javax.swing.JButton Btn86;
      private javax.swing.JButton Btn87;
      private javax.swing.JButton Btn88;
      private javax.swing.JButton CargarJuego;
      private javax.swing.JButton CerrarButton;
      private javax.swing.JButton GuardarJuego;
      private javax.swing.JTextField HorasTXT;
      private javax.swing.JButton IniciarJuego;
      private javax.swing.JTextField MinutosTXT;
      private javax.swing.JLabel NivelTXT;
      private javax.swing.JTextField NombreTXT;
      private javax.swing.JPanel NumbersPanel;
      private javax.swing.JLabel Op001;
      private javax.swing.JLabel Op00V;
      private javax.swing.JLabel Op012;
      private javax.swing.JLabel Op01V;
      private javax.swing.JLabel Op023;
      private javax.swing.JLabel Op02V;
      private javax.swing.JLabel Op034;
      private javax.swing.JLabel Op03V;
      private javax.swing.JLabel Op045;
      private javax.swing.JLabel Op04V;
      private javax.swing.JLabel Op056;
      private javax.swing.JLabel Op05V;
      private javax.swing.JLabel Op067;
      private javax.swing.JLabel Op06V;
      private javax.swing.JLabel Op078;
      private javax.swing.JLabel Op07V;
      private javax.swing.JLabel Op08V;
      private javax.swing.JLabel Op101;
      private javax.swing.JLabel Op10V;
      private javax.swing.JLabel Op112;
      private javax.swing.JLabel Op11V;
      private javax.swing.JLabel Op123;
      private javax.swing.JLabel Op12V;
      private javax.swing.JLabel Op134;
      private javax.swing.JLabel Op13V;
      private javax.swing.JLabel Op145;
      private javax.swing.JLabel Op14V;
      private javax.swing.JLabel Op156;
      private javax.swing.JLabel Op15V;
      private javax.swing.JLabel Op167;
      private javax.swing.JLabel Op16V;
      private javax.swing.JLabel Op178;
      private javax.swing.JLabel Op17V;
      private javax.swing.JLabel Op18V;
      private javax.swing.JLabel Op201;
      private javax.swing.JLabel Op20V;
      private javax.swing.JLabel Op212;
      private javax.swing.JLabel Op21V;
      private javax.swing.JLabel Op223;
      private javax.swing.JLabel Op22V;
      private javax.swing.JLabel Op234;
      private javax.swing.JLabel Op23V;
      private javax.swing.JLabel Op245;
      private javax.swing.JLabel Op24V;
      private javax.swing.JLabel Op256;
      private javax.swing.JLabel Op25V;
      private javax.swing.JLabel Op267;
      private javax.swing.JLabel Op26V;
      private javax.swing.JLabel Op278;
      private javax.swing.JLabel Op27V;
      private javax.swing.JLabel Op28V;
      private javax.swing.JLabel Op301;
      private javax.swing.JLabel Op30V;
      private javax.swing.JLabel Op312;
      private javax.swing.JLabel Op31V;
      private javax.swing.JLabel Op323;
      private javax.swing.JLabel Op32V;
      private javax.swing.JLabel Op334;
      private javax.swing.JLabel Op33V;
      private javax.swing.JLabel Op345;
      private javax.swing.JLabel Op34V;
      private javax.swing.JLabel Op356;
      private javax.swing.JLabel Op35V;
      private javax.swing.JLabel Op367;
      private javax.swing.JLabel Op36V;
      private javax.swing.JLabel Op378;
      private javax.swing.JLabel Op37V;
      private javax.swing.JLabel Op38V;
      private javax.swing.JLabel Op401;
      private javax.swing.JLabel Op40V;
      private javax.swing.JLabel Op412;
      private javax.swing.JLabel Op41V;
      private javax.swing.JLabel Op423;
      private javax.swing.JLabel Op42V;
      private javax.swing.JLabel Op434;
      private javax.swing.JLabel Op43V;
      private javax.swing.JLabel Op445;
      private javax.swing.JLabel Op44V;
      private javax.swing.JLabel Op456;
      private javax.swing.JLabel Op45V;
      private javax.swing.JLabel Op467;
      private javax.swing.JLabel Op46V;
      private javax.swing.JLabel Op478;
      private javax.swing.JLabel Op47V;
      private javax.swing.JLabel Op48V;
      private javax.swing.JLabel Op501;
      private javax.swing.JLabel Op50V;
      private javax.swing.JLabel Op512;
      private javax.swing.JLabel Op51V;
      private javax.swing.JLabel Op523;
      private javax.swing.JLabel Op52V;
      private javax.swing.JLabel Op534;
      private javax.swing.JLabel Op53V;
      private javax.swing.JLabel Op545;
      private javax.swing.JLabel Op54V;
      private javax.swing.JLabel Op556;
      private javax.swing.JLabel Op55V;
      private javax.swing.JLabel Op567;
      private javax.swing.JLabel Op56V;
      private javax.swing.JLabel Op578;
      private javax.swing.JLabel Op57V;
      private javax.swing.JLabel Op58V;
      private javax.swing.JLabel Op601;
      private javax.swing.JLabel Op60V;
      private javax.swing.JLabel Op612;
      private javax.swing.JLabel Op61V;
      private javax.swing.JLabel Op623;
      private javax.swing.JLabel Op62V;
      private javax.swing.JLabel Op634;
      private javax.swing.JLabel Op63V;
      private javax.swing.JLabel Op645;
      private javax.swing.JLabel Op64V;
      private javax.swing.JLabel Op656;
      private javax.swing.JLabel Op65V;
      private javax.swing.JLabel Op667;
      private javax.swing.JLabel Op66V;
      private javax.swing.JLabel Op678;
      private javax.swing.JLabel Op67V;
      private javax.swing.JLabel Op68V;
      private javax.swing.JLabel Op701;
      private javax.swing.JLabel Op70V;
      private javax.swing.JLabel Op712;
      private javax.swing.JLabel Op71V;
      private javax.swing.JLabel Op723;
      private javax.swing.JLabel Op72V;
      private javax.swing.JLabel Op734;
      private javax.swing.JLabel Op73V;
      private javax.swing.JLabel Op745;
      private javax.swing.JLabel Op74V;
      private javax.swing.JLabel Op756;
      private javax.swing.JLabel Op75V;
      private javax.swing.JLabel Op767;
      private javax.swing.JLabel Op76V;
      private javax.swing.JLabel Op778;
      private javax.swing.JLabel Op77V;
      private javax.swing.JLabel Op78V;
      private javax.swing.JLabel Op801;
      private javax.swing.JLabel Op812;
      private javax.swing.JLabel Op823;
      private javax.swing.JLabel Op834;
      private javax.swing.JLabel Op845;
      private javax.swing.JLabel Op856;
      private javax.swing.JLabel Op867;
      private javax.swing.JLabel Op878;
      private javax.swing.ButtonGroup Opciones;
      private javax.swing.JButton PistaButton;
      private javax.swing.JButton RehacerJugada;
      private javax.swing.JTextField SegundosTXT;
      private javax.swing.JButton TerminarJuego;
      private javax.swing.JButton Top10;
      private javax.swing.JLabel jLabel1;
      private javax.swing.JLabel jLabel2;
      private javax.swing.JLabel jLabel3;
      private javax.swing.JLabel jLabel5;
      private javax.swing.JLabel jLabel6;
      private javax.swing.JLabel jLabel7;
      private javax.swing.JPanel jPanel1;
      private javax.swing.JPanel jPanel3;
      private javax.swing.JPanel jPanel4;
      private javax.swing.JPanel jPanel6;
      private javax.swing.JPanel panelgrid;
      private javax.swing.JPanel relojFrame;
      // End of variables declaration//GEN-END:variables
}
