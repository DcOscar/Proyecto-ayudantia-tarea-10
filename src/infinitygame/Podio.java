/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

import java.util.*;

/**
 *
 * @author Oscar
 */
public class Podio {

    private ArrayList<Integer> posicionPodio;
    private ArrayList<String> nombrePodio;
    private ArrayList <String>saludPodio;
    private ArrayList<String> numeracion;
    private String[][] datos;

    public Podio(ArrayList<Jugador> jugadores, int turno) {
        this.posicionPodio = new ArrayList<Integer>();
        this.nombrePodio = new ArrayList<String>();
        this.saludPodio= new ArrayList<String>();
        this.numeracion= new ArrayList<String>();
        for (int contador = 0; contador < jugadores.size(); contador++) {
            this.posicionPodio.add(jugadores.get(contador).getPosicion());
            this.nombrePodio.add(jugadores.get(contador).getNombre());
            this.saludPodio.add(Integer.toString(jugadores.get(contador).getSalud()));
            this.numeracion.add(Integer.toString((contador+1)));
        }
        ordenarPodio();
        
    }
   private void ordenarPodio(){
   int posicionMenor;
   int posicionMayor;
   String saludMenor;
   String saludMayor;
   String nombreMenor;
   String nombreMayor;
   for(int x=0; x<=this.posicionPodio.size(); x++){
   for(int contador=0; contador<this.posicionPodio.size()-1; contador++){
    if(this.posicionPodio.get(contador)<this.posicionPodio.get(contador+1)){
    posicionMenor= this.posicionPodio.get(contador);
    posicionMayor= this.posicionPodio.get(contador+1);
    nombreMenor= this.nombrePodio.get(contador);
    nombreMayor= this.nombrePodio.get(contador+1);
    saludMenor= this.saludPodio.get(contador);
    saludMayor= this.saludPodio.get(contador+1);
    this.posicionPodio.set(contador, posicionMayor); //dato mayor
    this.posicionPodio.set(contador+1,posicionMenor); //dato menor
    this.nombrePodio.set(contador,nombreMayor);
    this.nombrePodio.set(contador+1, nombreMenor);
    this.saludPodio.set(contador, saludMayor);
    this.saludPodio.set(contador+1, saludMenor);
    }
    } 
    
}
   
}

public int obtenerCantidadDatos(){
return this.nombrePodio.size();
}
 public String[][]  generarTabla(){
 this.datos= new String[this.nombrePodio.size()][4];
 for(int filas=0; filas<this.nombrePodio.size(); filas++){
 this.datos[filas][0]= this.numeracion.get(filas);
 this.datos[filas][1]=this.nombrePodio.get(filas);
 this.datos[filas][2]= Integer.toString(this.posicionPodio.get(filas));
 this.datos[filas][3]=this.saludPodio.get(filas);

 }
 
 
 return this.datos;
 }
}
