/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa3.modelo;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
/**
 * Clase que representa una marca del Top 10
 * @author dandi
 */
public class Marca {
    private String nombre; // se almacena el nombre del jugador
    private int tiempo; // se almacena el tiempo del jugador
    private String nivel; // se almacena el nivel de la partida
      
    private static ArrayList<Marca> marcasFaciles5 = new ArrayList<>(); // estos arraylists estáticos almacenan todas las marcas de su nivel y cuadrícula de 5
    private static ArrayList<Marca> marcasIntermedias5 = new ArrayList<>();
    private static ArrayList<Marca> marcasDificiles5 = new ArrayList<>();
    private static HashMap<String,ArrayList<Marca>> marcas5 = new HashMap<>(); // Este hashmap se va a usar para obtener el arraylist dependiendo del nivel
    private static ArrayList<Marca> marcasFaciles6 = new ArrayList<>(); // estos arraylists estáticos almacenan todas las marcas de su nivel y cuadrícula de 6
    private static ArrayList<Marca> marcasIntermedias6 = new ArrayList<>();
    private static ArrayList<Marca> marcasDificiles6 = new ArrayList<>();
    private static HashMap<String,ArrayList<Marca>> marcas6 = new HashMap<>(); // Este hashmap se va a usar para obtener el arraylist dependiendo del nivel
    private static ArrayList<Marca> marcasFaciles7 = new ArrayList<>(); // estos arraylists estáticos almacenan todas las marcas de su nivel y cuadrícula de 7
    private static ArrayList<Marca> marcasIntermedias7 = new ArrayList<>();
    private static ArrayList<Marca> marcasDificiles7 = new ArrayList<>();
    private static HashMap<String,ArrayList<Marca>> marcas7 = new HashMap<>(); // Este hashmap se va a usar para obtener el arraylist dependiendo del nivel
    private static ArrayList<Marca> marcasFaciles8 = new ArrayList<>(); // estos arraylists estáticos almacenan todas las marcas de su nivel y cuadrícula de 8
    private static ArrayList<Marca> marcasIntermedias8 = new ArrayList<>();
    private static ArrayList<Marca> marcasDificiles8 = new ArrayList<>();
    private static HashMap<String,ArrayList<Marca>> marcas8 = new HashMap<>(); // Este hashmap se va a usar para obtener el arraylist dependiendo del nivel
    private static ArrayList<Marca> marcasFaciles9 = new ArrayList<>(); // estos arraylists estáticos almacenan todas las marcas de su nivel y cuadrícula de 9
    private static ArrayList<Marca> marcasIntermedias9 = new ArrayList<>();
    private static ArrayList<Marca> marcasDificiles9 = new ArrayList<>();
    private static HashMap<String,ArrayList<Marca>> marcas9 = new HashMap<>(); // Este hashmap se va a usar para obtener el arraylist dependiendo del nivel
    
    /**
     * HashMap que se usa para acceder a las listas de partidas de cada nivel y tamaño.
     */
    public static HashMap<Integer, HashMap<String,ArrayList<Marca>>> marcasMap = new HashMap<>();
    /**
     * constructor de la clase
     * @param pNombre
     * @param pTiempo
     * @param pNivel
     */
      public Marca (String pNombre, Integer pTiempo,  String pNivel){
            nombre = pNombre;
            tiempo = pTiempo;
            nivel = pNivel;
            
      }

    /**
     * esta función crea el hashmap que contiene todos los hashmaps con los arraylist de marcas y se lee el archivo con el top 10 
     */
      public static void createHashMap(){
            marcas5.put("Fácil", marcasFaciles5);
            marcas5.put("Intermedio", marcasIntermedias5);
            marcas5.put("Difícil", marcasDificiles5);
            marcas6.put("Fácil", marcasFaciles6);
            marcas6.put("Intermedio", marcasIntermedias6);
            marcas6.put("Difícil", marcasDificiles6);
            marcas7.put("Fácil", marcasFaciles7);
            marcas7.put("Intermedio", marcasIntermedias7);
            marcas7.put("Difícil", marcasDificiles7);
            marcas8.put("Fácil", marcasFaciles8);
            marcas8.put("Intermedio", marcasIntermedias8);
            marcas8.put("Difícil", marcasDificiles8);
            marcas9.put("Fácil", marcasFaciles9);
            marcas9.put("Intermedio", marcasIntermedias9);
            marcas9.put("Difícil", marcasDificiles9);
            marcasMap.put(5, marcas5);
            marcasMap.put(6, marcas6);
            marcasMap.put(7, marcas7);
            marcasMap.put(8, marcas8);
            marcasMap.put(9, marcas9);
            try {
                  BufferedReader bf = new BufferedReader(new FileReader("src\\main\\java\\poo\\programa3\\modelo\\futoshiki2022top10.dat")); // se abre el archivo
                  
                  String line = bf.readLine(); // se lee la primer línea
                  String[] niveles = new String[] {"Fácil", "Intermedio", "Difícil"};
                  
                  for (int size = 5; size < 10 && line != null; size++){ // se itera sobre el tamaño
                      for (int nivel = 0; nivel < 3 && line != null; nivel++){ // si itera sobre el nivel
                          for (int entrada = 0; entrada < 10 && line != null; entrada++){
                                String [] partes = line.split(" "); // se divide en las partes, que son el nombre, el tiempo y el nivel
                                // partes[0] = nombre, 1 = tiempo y 2 = nivel
                                if  (!(partes[0].equals("N")) && !(partes[1].equals("N")) && !(partes[2].equals("N"))){ // si la marca no es nula
                                      marcasMap.get(size).get(niveles[nivel]).add(new Marca(partes[0], Integer.valueOf(partes[1]), partes[2])); // se crea la marca y se añade a la lista
                                }
                                line = bf.readLine();
                          }
                      }
                  }
                  bf.close(); // se cierra el archivo
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("ERROR: NO SE PUEDE LEER ESTE ARCHIVO.");
            }
      }

    /**
     * Obtiene el nivel de la marca
     * @return nivel
     */
    public String getNivel() {
            return nivel;
      }

    /**
     * Establece el nivel de la marca
     * @param nivel
     */
    public void setNivel(String nivel) {
            this.nivel = nivel;
      }

    /**
     * Obtiene el nombre de la marca
     * @return nombre
     */
    public String getNombre() {
            return nombre;
      }

    /**
     * Obtiene el nombre del tiempo
     * @return tiempo
     */
    public int getTiempo() {
            return tiempo;
      }

    /**
     * Establece el nombre de la marca
     * @param nombre
     */
    public void setNombre(String nombre) {
            this.nombre = nombre;
      }

    /**
     * Establece el tiempo de la marca
     * @param tiempo
     */
    public void setTiempo(int tiempo) {
            this.tiempo = tiempo;
      }
      
      /**
     * Método para convertir el resultado de tiempo de un string a un int.
     * @param pHoras
     * @param pMinutos
     * @param pSegundos
     * @return el equivalente en int del tiempo dado
     */
    public static int convertToTime(String pHoras, String pMinutos, String pSegundos){
         
         int hours = Integer.parseInt(pHoras);
         int minutes = Integer.parseInt(pMinutos);
         int seconds = Integer.parseInt(pSegundos);
         hours *= 3600;
         minutes *= 60;
         return hours + minutes + seconds;
    }
    
    /**
     * Método que convierte el resultado de un int a un string.
     * @param tiempo
     * @return Equivalente en string del resultado.
     */
    public static String convertToString(int tiempo){
         
         int hours = tiempo / 3600;
         tiempo %= 3600;
         int minutes = tiempo / 60;
         int seconds = tiempo % 60;
         String time = new String();
         if (hours < 10){
              time += "0";
         }
         time +=Integer.toString(hours) +":";
         if (minutes < 10){
              time += "0";
         }
         time +=Integer.toString(minutes) +":";
         if (seconds < 10){
              time += "0";
         }
         time +=Integer.toString(seconds);
         
         return time;         
    }      
}
