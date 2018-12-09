/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

import java.util.*;

public class Guerrero extends Jugador {

    private int furia;

    public Guerrero(String nombre) {
        super(nombre);
        super.salud = 20;
        super.saludMax = 20;
        this.furia = 5;
    }
    

    public String enfurecerse(ArrayList<Jugador> jugadores, int indice) {
       
        if (this.furia == 0) {
            return "Se ha quedado sin furia";
        } else {
           
          
            jugadores.get(indice).setSalud(-1);
            return "La salud del jugador elegido ha quedado en " + jugadores.get(indice).getSalud();
        }
    }

   
    public String getTipo() {
        return "Guerrero";
    }

  
}
