/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazJuego;

import ventana.*;
import infinitygame.*;
import javax.swing.*;
import java.awt.event.*;

public class CrearJugador extends Ventana {

    private JRadioButton tipoMago, tipoGuerrero;
    private JLabel textoEncabezado, textoNombreJugador;
    private JButton botonCrearJugador;
    private JTextField nombreJugador;
    private int contador;
    private final InfinityGame game;

    public CrearJugador(InfinityGame game, int contador) {
       
        super("Infinity Game", 500, 400);
        this.contador = contador;
        this.game = game;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarEncabezado();
        generarCampoCrearJugador();
        tipoJugador();

    }

    private void generarCampoCrearJugador() {
        String textoNombreJugador = "Ingrese el nombre del jugador:";
        String textoBotonJugador = "Crear jugador";
        super.generarJLabel(this.textoNombreJugador, textoNombreJugador, 20, 100, 200, 20);
        this.nombreJugador = super.generarJTextField(230, 100, 200, 20);
        this.add(this.nombreJugador);
        this.botonCrearJugador = super.generarBoton(textoBotonJugador, 300, 300, 150, 20);
        this.add(this.botonCrearJugador);
        this.botonCrearJugador.addActionListener(this);
    }

    private void tipoJugador() {
        String tipoGuerrero = "Jugador Guerrero";
        String tipoMago = "Jugador Mago";
        this.tipoGuerrero = super.generarJRadioButton(tipoGuerrero, 20, 200, 200, 20);
        this.add(this.tipoGuerrero);
        this.tipoGuerrero.addActionListener(this);
        this.tipoMago = super.generarJRadioButton(tipoMago, 20, 220, 200, 20);
        this.add(this.tipoMago);
        this.tipoMago.addActionListener(this);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(this.tipoGuerrero);
        grupo.add(this.tipoMago);
    }

    private void generarEncabezado() {
        String textoEncabezado = "Crear jugador";
        super.generarJLabelEncabezado(this.textoEncabezado, textoEncabezado, 200, 0, 200, 30);
    }

   
    private void crearJugador() {
        int cantidadJugadores = this.game.getCantidadJugadores();
        if (this.nombreJugador.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre de jugador");
       
        } else {
            String nombreJugador = this.nombreJugador.getText();
            if (this.tipoMago.isSelected()) {
                game.agregarJugadorMago(nombreJugador);
                this.contador++;
                compararCantidadJugadores(cantidadJugadores);
            } else if (this.tipoGuerrero.isSelected()) {
                game.agregarJugadorGuerrero(nombreJugador);
                this.contador++;
                compararCantidadJugadores(cantidadJugadores);
              
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un tipo de jugador");
                

            }
          
        }

    }
    private void compararCantidadJugadores(int cantidadJugadores){
      if (this.contador == cantidadJugadores) {
                int contador = 0;
                int turno = 1;
               
                MenuJugador menu = new MenuJugador(this.game, contador, turno);
                 this.game.crearGuardian();
                this.dispose();
            }
      else{
       CrearJugador crear = new CrearJugador(this.game, this.contador);
        this.dispose();
      }
    
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonCrearJugador) {
            crearJugador();

        }
      
    }

}
