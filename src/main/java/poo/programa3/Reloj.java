/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa3;

/**
 * Esta clase implementa las funcionalidades para el reloj y el temporizador
 * @author Daniel Granados Retana, carné 2022104692, y Diego Granados Retana, carné 2022158363
 */
public class Reloj {
    private static int horas = 0;
    private static int minutos = 0;
    private static int segundos = 0;

    /**
     * Obtiene las horas guardadas como string
     * @return horas
     */
    public static String getHoras() {
        return "" + horas;
    }

    /**
     * Establece las horas guardadas
     * @param horasStr
     */
    public static void setHoras(String horasStr) {
        horas = Integer.parseInt(horasStr);
    }

    /**
     * Obtiene los minutos guardados como string
     * @return minutos
     */
    public static String getMinutos() {
        return "" + minutos;
    }

    /**
     * Establece los minutos
     * @param minutosStr
     */
    public static void setMinutos(String minutosStr) {
        minutos = Integer.parseInt(minutosStr);
    }

    /**
     * Obtiene los segundos guardados como string
     * @return segundos
     */
    public static String getSegundos() {
        return "" + segundos;
    }

    /**
     * Establece los segundos
     * @param segundosStr
     */
    public static void setSegundos(String segundosStr) {
        segundos = Integer.parseInt(segundosStr);
    }
     
    /**
     * Obtiene las horas
     * @return horas
     */
    public static int getHorasInt() {
        return horas;
    }

    /**
     * Obtiene los minutos
     * @return minutos
     */
    public static int getMinutosInt() {
        return  minutos;
    }

    /**
     * Obtiene los segundos
     * @return segundos
     */
    public static int getSegundosInt() {
        return segundos;
    }
    
    /**
     * Función para validar si una entrada es máxima para ser minutos, segundos u horas.
     * @param entrada con el string a convertir a entero.
     * @param min
     * @param max
     * @throws NumberFormatException
     * @throws RangeException 
     */
    public static void validarEntrada(String entrada, int min, int max) throws NumberFormatException, RangeException{
        int number;
        try{
            System.out.println(entrada);
            number = Integer.parseInt(entrada);
            System.out.println(number);
        } catch (NumberFormatException e){
            throw new NumberFormatException();
        }
        if (min > number || max < number ){
            throw new RangeException("Número fuera de rango");
        }
    }
}
