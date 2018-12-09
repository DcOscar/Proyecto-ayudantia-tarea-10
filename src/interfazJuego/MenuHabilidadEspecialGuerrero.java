/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfazJuego;
import ventana.*;
import java.util.*;
import infinitygame.*;
import javax.swing.*;
import java.awt.event.*;


public class MenuHabilidadEspecialGuerrero extends Ventana {
     
    private JLabel textoMostrarJugadores;
    private  ArrayList<JRadioButton> botones; 
    private final InfinityGame game;
    private int contador,turno;
    
    public MenuHabilidadEspecialGuerrero(InfinityGame game, int contador, int turno){
   super("Infinity Game",400,400);
    this.botones = new ArrayList<JRadioButton>();
    this.game=game;
    this.contador=contador;
    this.turno=turno;
    generarElementosVentana();
    
    }

    private void generarElementosVentana(){
    generarBotonesJugadores();
    generarTextoEncabezado();
    }
 private void generarTextoEncabezado(){
 String texto= "Elije un jugador para hacerle daño";
 super.generarJLabelEncabezado(this.textoMostrarJugadores, texto, 0, 0, 300, 20);
 }
  private void generarBotonesJugadores(){
  int cantidadJugadores= game.getSizeJugadores();
  int posicionY= 25;
  int  contador=0; 
  for(int i=0; i<cantidadJugadores; i++){
  if(i!=this.contador){
  JRadioButton boton;    
  boton = super.generarJRadioButton(game.getNombreJugador(i) , 20, posicionY, 100, 20);
  this.botones.add(boton);
 this.add(this.botones.get(contador));
this.botones.get(contador).addActionListener(this);
  posicionY+=15;
   contador++;
  }
  }
   ButtonGroup grupo = new ButtonGroup();
   for(int i=0; i<this.botones.size(); i++){
   grupo.add(this.botones.get(i));
   }
  }
  private void accionBotonJugador(){
      String nombreJugador, resultado;
  for(int i=0; i<this.botones.size();i++){
  if(this.botones.get(i).isSelected()){
  nombreJugador=this.botones.get(i).getText();
  resultado= this.game.opcionHabilidadEnfurecerse(nombreJugador, this.contador);
  JOptionPane.showMessageDialog(this, resultado);
 this.contador++;
if(this.contador==this.game.getCantidadJugadores()){
this.contador=0;
this.turno++;
MenuJugador menu= new MenuJugador(this.game, this.contador, this.turno);
this.dispose();
}
else{
MenuJugador menu= new MenuJugador(this.game, this.contador, this.turno);
this.dispose();
}
  }
  
  }
  }
  @Override
    public void actionPerformed(ActionEvent e) {
    accionBotonJugador();
    
    }
    
    
}
