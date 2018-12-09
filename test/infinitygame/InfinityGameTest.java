/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;


public class InfinityGameTest {
    
    InfinityGame game= new InfinityGame();
    Tablero tablero= new Tablero();
    int indice;
    public InfinityGameTest() {
    }
    
  
    @Test
    public void testLanzarDados() {
        System.out.println("Metodo lanzar dados");
        for(int i=0; i<500; i++){
        int resultado= game.lanzarDados();
        boolean condicion= false;
        if(resultado>1 && resultado<13){
           condicion= true; }
        else{System.out.println("error");}
        assertTrue(condicion);
        }
    }
    @Test
    public void testCasillaPortal(){
      ArrayList<Jugador> jugadores= new ArrayList<Jugador>();
      ArrayList<Character> casillas= tablero.getCasillas();
       jugadores.add(new Jugador("Usuario"));
       int posicionInicial;
       int posicionFinal;
      tablero.setLargoTablero(20);
      tablero.generarTablero();
       
        
        System.out.println(casillas);
        for(int i=0; i<casillas.size(); i++){
        if(casillas.get(i).equals('p')){
          this.indice= i;
            break;  
        }}
     jugadores.get(0).setPosicionExtremos(indice);
      posicionInicial= jugadores.get(0).getPosicion();
        System.out.println(posicionInicial+1);
        game.casillaPortal(jugadores,casillas,20,0,this.indice);
        posicionFinal= jugadores.get(0).getPosicion();
        System.out.println(posicionFinal+1);
        assertNotEquals(posicionInicial,posicionFinal);
    }
   @Test 
    public void testCasillaSalud(){
       ArrayList<Jugador> jugadores= new ArrayList<Jugador>();
       int saludInicial;
       int saludFinal;
       int cantidadJugadores=1;
       boolean condicion= false;
       jugadores.add(new Jugador("jugador 1"));
       jugadores.get(0).setSalud(-5);
      saludInicial= jugadores.get(0).getSalud();    //la salud Inicial es 10
      game.casillaSalud(jugadores, 0, cantidadJugadores);
      saludFinal= jugadores.get(0).getSalud();
      System.out.println("salud inicial:"+saludInicial+", salud final:"+saludFinal);
      if(saludFinal>6 && saludFinal<14){
      condicion= true;}
      else{System.out.println("Error");}
       assertTrue(condicion);
        }
        
    
}
