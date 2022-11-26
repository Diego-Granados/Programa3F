/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa3;

/**
 * Esta clase es una excepción que tiene como objetivo señalar cuando un número se encuentra fuera de un rango.
 * @author Daniel Granados Retana, carné 2022104692 <dagranados@astudiantec.cr>
 */
public class RangeException extends Exception{
    public RangeException(String pMensaje){
        super(pMensaje);
    }
}
