/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa3.modelo;

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
    private static ArrayList<Partida> faciles5 = new ArrayList<>();
    private static ArrayList<Partida> intermedias5 = new ArrayList<>();
    private static ArrayList<Partida> dificiles5 = new ArrayList<>();
    private static HashMap<String, ArrayList<Partida>> partidasPorNivel5 = new HashMap<>();
    private static ArrayList<Integer> ordenFacil5 = new ArrayList<>();
    private static ArrayList<Integer> ordenIntermedio5 = new ArrayList<>();
    private static ArrayList<Integer> ordenDificil5 = new ArrayList<>();
    private static HashMap<String, ArrayList<Integer>> ordenesPorNivel5 = new HashMap<>();
    
    private static ArrayList<Partida> faciles6 = new ArrayList<>();
    private static ArrayList<Partida> intermedias6 = new ArrayList<>();
    private static ArrayList<Partida> dificiles6 = new ArrayList<>();
    private static HashMap<String, ArrayList<Partida>> partidasPorNivel6 = new HashMap<>();
    private static ArrayList<Integer> ordenFacil6 = new ArrayList<>();
    private static ArrayList<Integer> ordenIntermedio6 = new ArrayList<>();
    private static ArrayList<Integer> ordenDificil6 = new ArrayList<>();
    private static HashMap<String, ArrayList<Integer>> ordenesPorNivel6 = new HashMap<>();
  
    private static ArrayList<Partida> faciles7 = new ArrayList<>();
    private static ArrayList<Partida> intermedias7 = new ArrayList<>();
    private static ArrayList<Partida> dificiles7 = new ArrayList<>();
    private static HashMap<String, ArrayList<Partida>> partidasPorNivel7 = new HashMap<>();
    private static ArrayList<Integer> ordenFacil7 = new ArrayList<>();
    private static ArrayList<Integer> ordenIntermedio7 = new ArrayList<>();
    private static ArrayList<Integer> ordenDificil7 = new ArrayList<>();
    private static HashMap<String, ArrayList<Integer>> ordenesPorNivel7 = new HashMap<>();
    
    private static ArrayList<Partida> faciles8 = new ArrayList<>();
    private static ArrayList<Partida> intermedias8 = new ArrayList<>();
    private static ArrayList<Partida> dificiles8 = new ArrayList<>();
    private static HashMap<String, ArrayList<Partida>> partidasPorNivel8 = new HashMap<>();
    private static ArrayList<Integer> ordenFacil8 = new ArrayList<>();
    private static ArrayList<Integer> ordenIntermedio8 = new ArrayList<>();
    private static ArrayList<Integer> ordenDificil8 = new ArrayList<>();
    private static HashMap<String, ArrayList<Integer>> ordenesPorNivel8 = new HashMap<>();
    
    private static ArrayList<Partida> faciles9 = new ArrayList<>();
    private static ArrayList<Partida> intermedias9 = new ArrayList<>();
    private static ArrayList<Partida> dificiles9 = new ArrayList<>();
    private static HashMap<String, ArrayList<Partida>> partidasPorNivel9 = new HashMap<>();
    private static ArrayList<Integer> ordenFacil9 = new ArrayList<>();
    private static ArrayList<Integer> ordenIntermedio9 = new ArrayList<>();
    private static ArrayList<Integer> ordenDificil9 = new ArrayList<>();
    private static HashMap<String, ArrayList<Integer>> ordenesPorNivel9 = new HashMap<>();
    private static Document document;
    
    private static HashMap<Integer, HashMap<String, ArrayList<Partida>>> nivelesMap = new HashMap<>();
    private static HashMap<Integer, HashMap<String, ArrayList<Integer>>> ordenesMap = new HashMap<>();
    
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
     * Método que recibe el tamaño de la cuadrícula y un string con el nombre del nivel y genera un orden aleatorio para escoger partidas sin que se repitan.
     * @param cuadricula 
     * @param pNivel
     */
    public static void setIndex(int cuadricula, String pNivel){
        ArrayList<Integer> orden = ordenesMap.get(cuadricula).get(pNivel);
        for (int index = 0; index < nivelesMap.get(cuadricula).get(pNivel).size(); index++){
            orden.add(index);
        } // llenamos el arreglo con un orden ascendente.
        Collections.shuffle(orden); // se desordena aleatoriamente.
    }
    
    /**
     * Método que recibe el tamaño de la cuadrícula y un string con el nombre del nivel y retorna el índice que sigue en el orden aleatorio del nivel.
     * @param cuadricula
     * @param pNivel
     * @return index
     */
    public static int getIndex(int cuadricula, String pNivel){
        ArrayList<Integer> orden = ordenesMap.get(cuadricula).get(pNivel);
        int index = orden.get(0);
        orden.remove(0);
        if (orden.isEmpty()){
            setIndex(cuadricula, pNivel); // si borramos todos los índices, generamos otro orden
        }
        return index;
    }
    
    /**
     * Método que lee las partidas del archivo futoshiki2022partidas.xml y las almacena en objetos para cada partida.
     */
    public static void leerPartidas(){
        partidasPorNivel5.put("Fácil", faciles5); // inicializamos los hashmaps de las partidas
        partidasPorNivel5.put("Intermedio", intermedias5);
        partidasPorNivel5.put("Difícil", dificiles5);
        ordenesPorNivel5.put("Fácil", ordenFacil5);
        ordenesPorNivel5.put("Intermedio", ordenIntermedio5);
        ordenesPorNivel5.put("Difícil", ordenDificil5);
        
        partidasPorNivel6.put("Fácil", faciles6); // inicializamos los hashmaps de las partidas
        partidasPorNivel6.put("Intermedio", intermedias6);
        partidasPorNivel6.put("Difícil", dificiles6);
        ordenesPorNivel6.put("Fácil", ordenFacil6);
        ordenesPorNivel6.put("Intermedio", ordenIntermedio6);
        ordenesPorNivel6.put("Difícil", ordenDificil6);
        
        partidasPorNivel7.put("Fácil", faciles7); // inicializamos los hashmaps de las partidas
        partidasPorNivel7.put("Intermedio", intermedias7);
        partidasPorNivel7.put("Difícil", dificiles7);
        ordenesPorNivel7.put("Fácil", ordenFacil7);
        ordenesPorNivel7.put("Intermedio", ordenIntermedio7);
        ordenesPorNivel7.put("Difícil", ordenDificil7);
        
        partidasPorNivel8.put("Fácil", faciles8); // inicializamos los hashmaps de las partidas
        partidasPorNivel8.put("Intermedio", intermedias8);
        partidasPorNivel8.put("Difícil", dificiles8);
        ordenesPorNivel8.put("Fácil", ordenFacil8);
        ordenesPorNivel8.put("Intermedio", ordenIntermedio8);
        ordenesPorNivel8.put("Difícil", ordenDificil8);
        
        partidasPorNivel9.put("Fácil", faciles9); // inicializamos los hashmaps de las partidas
        partidasPorNivel9.put("Intermedio", intermedias9);
        partidasPorNivel9.put("Difícil", dificiles9);
        ordenesPorNivel9.put("Fácil", ordenFacil9);
        ordenesPorNivel9.put("Intermedio", ordenIntermedio9);
        ordenesPorNivel9.put("Difícil", ordenDificil9);
        
        nivelesMap.put(5, partidasPorNivel5);
        nivelesMap.put(6, partidasPorNivel6);
        nivelesMap.put(7, partidasPorNivel7);
        nivelesMap.put(8, partidasPorNivel8);
        nivelesMap.put(9, partidasPorNivel9);
        
        ordenesMap.put(5, ordenesPorNivel5);
        ordenesMap.put(6, ordenesPorNivel6);
        ordenesMap.put(7, ordenesPorNivel7);
        ordenesMap.put(8, ordenesPorNivel8);
        ordenesMap.put(9, ordenesPorNivel9);
        
        try{
            File partidasFile = new File("src\\main\\java\\poo\\programa3\\modelo\\futoshiki2022partidas.xml");
            SAXReader reader = new SAXReader();
            document = reader.read(partidasFile); // leemos el partido
            List<Node> nodosPartida = document.selectNodes("/partidasFutoshiki/partida"); // obtenemos una lista con los nodos que empiezan con partida.
            
            for (Node partidaNodo : nodosPartida){ // por cada partida, creamos un objeto de la partida
                String nivel = partidaNodo.selectSingleNode("nivel").getText();
                int cuadricula = Integer.parseInt(partidaNodo.selectSingleNode("cuadricula").getText());
                List<Node> desigualdades = partidaNodo.selectNodes("des");
                Partida partida = new Partida();
                for (Node des : desigualdades){ // leemos las desigualdades
                    String entry[] = des.getText().split(",");
                    partida.getOperaciones().add(new Operacion(entry[0].charAt(0), 
                            Integer.parseInt(entry[1]), Integer.parseInt(entry[2])));
                }
                List<Node> constantesNodos = partidaNodo.selectNodes("cons"); // leemos las constantes
                for (Node constante : constantesNodos){
                    String entry[] = constante.getText().split(",");
                    partida.getConstantes().add(new Operacion(entry[0].charAt(0), 
                            Integer.parseInt(entry[1]), Integer.parseInt(entry[2])));
                }
                nivelesMap.get(cuadricula).get(nivel).add(partida);
                // se obtiene el arreglo respectivo al nivel.
            }
        } catch(DocumentException e){
            e.printStackTrace();
        }
        setIndex(5, "Fácil"); // establecemos los ordenes para cada nivel.
        setIndex(5, "Intermedio");
        setIndex(5, "Difícil");
        setIndex(6, "Fácil"); // establecemos los ordenes para cada nivel.
        setIndex(6, "Intermedio");
        setIndex(6, "Difícil");
        setIndex(7, "Fácil"); // establecemos los ordenes para cada nivel.
        setIndex(7, "Intermedio");
        setIndex(7, "Difícil");
        setIndex(8, "Fácil"); // establecemos los ordenes para cada nivel.
        setIndex(8, "Intermedio");
        setIndex(8, "Difícil");
        setIndex(9, "Fácil"); // establecemos los ordenes para cada nivel.
        setIndex(9, "Intermedio");
        setIndex(9, "Difícil");
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
     * Obtiene la lista de partidas fáciles. Recibe el tamaño de la cuadrícula
     * @param cuadricula
     * @return lista de partidas fáciles
     */
    public static ArrayList<Partida> getFaciles(int cuadricula) {
        return nivelesMap.get(cuadricula).get("Fácil");
    }

    /**
     * Obtiene la lista de partidas intermedias. Recibe el tamaño de la cuadrícula
     * @param cuadricula
     * @return lista de partidas intermedias
     */
    public static ArrayList<Partida> getIntermedias(int cuadricula) {
        return nivelesMap.get(cuadricula).get("Intermedio");
    }

    /**
     * Obtiene la lista de partidas difíciles. Recibe el tamaño de la cuadrícula
     * @param cuadricula
     * @return lista de partidas difíciles
     */
    public static ArrayList<Partida> getDificiles(int cuadricula) {
        return nivelesMap.get(cuadricula).get("Difícil");
    }

    /**
     * HashMap que permite obtener la lista de partidas por nivel a partir del nombre del nivel
     * @return hash map partidas por nivel
     */
    public static HashMap<String, ArrayList<Partida>> getPartidasPorNivel(int cuadricula) {
        return nivelesMap.get(cuadricula);
    }
}
