/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa3;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
/**
 *
 * @author dandi
 */
public class Marca {
      private String nombre; // se almacena el nombre del jugador
      private int tiempo; // se almacena el tiempo del jugador
      private String nivel; // se almacena el nivel de la partida

    /**
     * ArrayList que almacena las marcas del nivel fácil
     */
    public static ArrayList<Marca> marcasFaciles = new ArrayList<>(); // estos arraylists estáticos almacenan todas las marcas de su nivel

    /**
     * ArrayList que almacena las marcas del nivel intermedio
     */
    public static ArrayList<Marca> marcasIntermedias = new ArrayList<>();

    /**
     * ArrayList que almacena las marcas del nivel difícil
     */
    public static ArrayList<Marca> marcasDificiles = new ArrayList<>();

    /**
     * HashMap que mapea los ArrayLists de cada nivel por un string con su nombre de nivel.
     */
    public static HashMap<String,ArrayList<Marca>> marcas = new HashMap<>(); // Este hashmap se va a usar para obtener el arraylist dependiendo del nivel
      
      private static ArrayList<String> strings = new ArrayList<>(); // este arraylist se usa para guardar las líneas leídas del archivo de top 10
      
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
     * esta función crea el hashmap que contiene los arraylist de marcas y se lee el archivo con el top 10 
     */
      public static void createHashMap(){
            marcas.put("Fácil", marcasFaciles);
            marcas.put("Intermedio", marcasIntermedias);
            marcas.put("Difícil", marcasDificiles);
            try {
                  BufferedReader bf = new BufferedReader(new FileReader("src\\main\\java\\poo\\programa3\\futoshiki2022top10.dat")); // se abre el archivo
                  
                  String line = bf.readLine(); // se lee la primer línea
                  while (line != null){ // se lee hasta que se termine el archivo
                        strings.add(line);
                        line = bf.readLine();
                  }
                  bf.close(); // se cierra el archivo
                  for (int i = 0; i < strings.size(); i++ ){ // por cada string,
                        String [] partes = strings.get(i).split(" "); // se divide en las partes, que son el nombre, el tiempo y el nivel
                        
                        if  (!(partes[0].equals("N")) && !(partes[1].equals("N")) && !(partes[2].equals("N"))){ // si la marca no es nula
                              Marca.marcas.get(partes[2]).add(new Marca(partes[0], Integer.valueOf(partes[1]), partes[2])); // se crea la marca y se añade a la lista
                              
                        }
                  }
            }
            catch (Exception e){
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
