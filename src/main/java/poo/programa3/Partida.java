/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa3;

import java.util.ArrayList;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * Esta clase contiene la configuración de la partida que se va a jugar.
 * @author Daniel Granados Retana, carné 2022104692 <dagranados@estudiantec.cr>
 */
public class Partida {
    private ArrayList<Operacion> operaciones; // ArrayList para guardar las desigualdades
    private ArrayList<Operacion> constantes; // ArrayList para guardar las constantes
    private static ArrayList<Partida> faciles = new ArrayList<>();
    private static ArrayList<Partida> intermedias = new ArrayList<>();
    private static ArrayList<Partida> dificiles = new ArrayList<>();
    private static HashMap<String, ArrayList<Partida>> partidasPorNivel = new HashMap<>();
    private static ArrayList<Integer> ordenFacil = new ArrayList<>();
    private static ArrayList<Integer> ordenIntermedio = new ArrayList<>();
    private static ArrayList<Integer> ordenDificil = new ArrayList<>();
    private static HashMap<String, ArrayList<Integer>> ordenesPorNivel = new HashMap<>();
    private static Document document;
    
    /**
     * Constructor
     */
    public Partida(){
        operaciones = new ArrayList<>();
        constantes = new ArrayList<>();
    }
        
    /**
     *
     * @return
     */
    public ArrayList<Operacion> getOperaciones() {
        return operaciones;
    }

    /**
     *
     * @param operaciones
     */
    public void setOperaciones(ArrayList<Operacion> operaciones) {
        this.operaciones = operaciones;
    }
    
    /**
     * Método que recibe un string con el nombre del nivel y genera un orden aleatorio para escoger partidas sin que se repitan.
     * @param pNivel 
     */
    public static void setIndex(String pNivel){
        ArrayList<Integer> orden = ordenesPorNivel.get(pNivel);
        for (int index = 0; index < partidasPorNivel.get(pNivel).size(); index++){
            orden.add(index);
        } // llenamos el arreglo con un orden ascendente.
        Collections.shuffle(orden); // se desordena aleatoriamente.
    }
    
    /**
     * Método que recibe un string con el nombre del nivel y retorna el índice que sigue en el orden aleatorio del nivel.
     * @param pNivel
     * @return index
     */
    public static int getIndex(String pNivel){
        ArrayList<Integer> orden = ordenesPorNivel.get(pNivel);
        int index = orden.get(0);
        orden.remove(0);
        if (orden.isEmpty()){
            setIndex(pNivel); // si borramos todos los índices, generamos otro orden
        }
        return index;
    }
    
    /**
     * Método que lee las partidas del archivo futoshiki2022partidas.xml y las almacena en objetos para cada partida.
     */
    public static void leerPartidas(){
        partidasPorNivel.put("Fácil", faciles); // inicializamos los hashmaps de las partidas
        partidasPorNivel.put("Intermedio", intermedias);
        partidasPorNivel.put("Difícil", dificiles);
        ordenesPorNivel.put("Fácil", ordenFacil);
        ordenesPorNivel.put("Intermedio", ordenIntermedio);
        ordenesPorNivel.put("Difícil", ordenDificil);
        
        try{
            File partidasFile = new File("src\\main\\java\\poo\\programa3\\futoshiki2022partidas.xml");
            SAXReader reader = new SAXReader();
            document = reader.read(partidasFile); // leemos el partido
            List<Node> nodosPartida = document.selectNodes("/partidasFutoshiki/partida"); // obtenemos una lista con los nodos que empiezan con partida.
            
            for (Node partidaNodo : nodosPartida){ // por cada partida, creamos un objeto de la partida
                String nivel = partidaNodo.selectSingleNode("nivel").getText();
                List<Node> desigualdades = partidaNodo.selectNodes("des");
                Partida partida = new Partida();
                for (Node des : desigualdades){ // leemos las desigualdades
                    String entry[] = des.getText().split(",");
                    System.out.println(entry[0] + " " + entry[1] + " " + entry[2]);
                    partida.getOperaciones().add(new Operacion(entry[0].charAt(0), 
                            Integer.parseInt(entry[1]), Integer.parseInt(entry[2])));
                }
                List<Node> constantesNodos = partidaNodo.selectNodes("cons"); // leemos las constantes
                for (Node constante : constantesNodos){
                    String entry[] = constante.getText().split(",");
                    System.out.println(entry[0] + " " + entry[1] + " " + entry[2]);
                    partida.getConstantes().add(new Operacion(entry[0].charAt(0), 
                            Integer.parseInt(entry[1]), Integer.parseInt(entry[2])));
                }
                partidasPorNivel.get(nivel).add(partida);
            }
        } catch(DocumentException e){
            e.printStackTrace();
        }
        setIndex("Fácil"); // establecemos los ordenes para cada nivel.
        setIndex("Intermedio");
        setIndex("Difícil");
    }

    /**
     * Método para obtener las constantes de la partida
     * @return Arreglo de constantes
     */
    public ArrayList<Operacion> getConstantes() {
        return constantes;
    }

    /**
     * Método para establecer las constantes de la partida
     * @param constantes
     */
    public void setConstantes(ArrayList<Operacion> constantes) {
        this.constantes = constantes;
    }

    /**
     * Obtiene la lista de partidas fáciles
     * @return lista de partidas fáciles
     */
    public static ArrayList<Partida> getFaciles() {
        return faciles;
    }

    /**
     * Obtiene la lista de partidas intermedias
     * @return lista de partidas intermedias
     */
    public static ArrayList<Partida> getIntermedias() {
        return intermedias;
    }

    /**
     * Obtiene la lista de partidas difíciles
     * @return lista de partidas difíciles
     */
    public static ArrayList<Partida> getDificiles() {
        return dificiles;
    }

    /**
     * HashMap que permite obtener la lista de partidas por nivel a partir del nombre del nivel
     * @return hash map partidas por nivel
     */
    public static HashMap<String, ArrayList<Partida>> getPartidasPorNivel() {
        return partidasPorNivel;
    }
}
