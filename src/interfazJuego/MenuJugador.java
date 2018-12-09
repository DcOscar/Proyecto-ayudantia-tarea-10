/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazJuego;

import ventana.*;
import javax.swing.*;
import java.awt.event.*;
import infinitygame.*;

public class MenuJugador extends Ventana {

    private JRadioButton botonLanzarDados, botonHabilidadEspecial, botonMeditar;
    private int contador, turno;
    private String tipoJugada;
    private final InfinityGame game;
    private JButton botonTablero, botonAceptar, botonPodio;
    private JLabel textoEncabezado, textoHacerJugada;
    private JFormattedTextField meditacion;

    public MenuJugador(InfinityGame game, int contador, int turno) {
        super("Infinity Game", 500, 400);
        this.contador = contador;
        this.game = game;
        this.turno = turno;
        this.tipoJugada = tipoJugada();
        generarElementosVentana();

    }

    private String tipoJugada() {
        if (game.getTipoJugador(this.contador).equals("Guerrero")) {
            return "Enfurecerse";
        } else {
            return "Concentración";
        }
    }

    private void generarElementosVentana() {
        
        generarBotonPodio();
        generarBotonTablero();
        generarBotonAceptar();
        generarTextoEncabezado();
        elegirJugada();

    }

    private void generarTextoEncabezado() {
        String textoEncabezado = "Turno n°" + this.turno + "  jugador " + game.getNombreJugador(this.contador);
        super.generarJLabelEncabezado(this.textoEncabezado, textoEncabezado, 70, 10, 300, 20);
    }

    private void elegirJugada() {
        String elegirJugada = "Elegir jugada";
        String lanzarDados = "Lanzar dados";
        String meditar = "Meditar";
        super.generarJLabel(this.textoHacerJugada, elegirJugada, 25, 100, 200, 20);
        this.botonLanzarDados = super.generarJRadioButton(lanzarDados, 20, 130, 200, 20);
        this.add(this.botonLanzarDados);
        this.botonLanzarDados.addActionListener(this);
        this.botonMeditar = super.generarJRadioButton(meditar, 20, 160, 200, 20);
        this.add(this.botonMeditar);
        this.botonMeditar.addActionListener(this);
        this.botonHabilidadEspecial = super.generarJRadioButton(this.tipoJugada, 20, 190, 200, 20);
        this.add(this.botonHabilidadEspecial);
        this.botonHabilidadEspecial.addActionListener(this);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(this.botonLanzarDados);
        grupo.add(this.botonMeditar);
        grupo.add(this.botonHabilidadEspecial);

    }

    private void generarBotonTablero() {
        String textoBoton = "Ver tablero";
        this.botonTablero = super.generarBoton(textoBoton, 350, 130, 100, 20);
        this.add(this.botonTablero);
        this.botonTablero.addActionListener(this);
    }

    private void generarBotonPodio() {
        String textoBoton = "Ver podio";
        this.botonPodio = super.generarBoton(textoBoton, 350, 170, 100, 20);
        this.add(this.botonPodio);
        this.botonPodio.addActionListener(this);
    }

    private void generarBotonAceptar() {
        String textoBoton = "Aceptar";
        this.botonAceptar = super.generarBoton(textoBoton, 300, 300, 100, 20);
        this.add(this.botonAceptar);
        this.botonAceptar.addActionListener(this);

    }

    private void verTablero() {
        JOptionPane.showMessageDialog(this, "El tablero es: " + this.game.getCasillas());
    }

    private void crearMenuJugador() {
        boolean condicionJuego = this.game.terminarJuego(this.contador, this.turno);
        if (condicionJuego == true) {
            System.exit(0);
        } else {
            this.contador++;
            if (this.contador == this.game.getCantidadJugadores()) {
                this.contador = 0;
                this.turno++;
                MenuJugador menu = new MenuJugador(this.game, this.contador, this.turno);
                this.dispose();
            } else {
                MenuJugador menu = new MenuJugador(this.game, this.contador, this.turno);
                this.dispose();
            }
        }
    }

    private void generarCampoHabilidadEspecial() {
        if (this.game.getTipoJugador(this.contador).equals("Guerrero")) {
            int cantidadJugadores = this.game.getCantidadJugadores();
            if (cantidadJugadores == 1) {
                JOptionPane.showMessageDialog(this, "No hay jugadores para dañar");
            } else {
                MenuHabilidadEspecialGuerrero menuGuerrero = new MenuHabilidadEspecialGuerrero(this.game, this.contador, this.turno);
                this.dispose();
            }
        } else if (this.game.getTipoJugador(this.contador).equals("Mago")) {
            String resultado = this.game.opcionHabilidadConcentracion(this.contador);
            JOptionPane.showMessageDialog(this, resultado);

            crearMenuJugador();

        }

    }

    private void jugar() {
        if (this.botonLanzarDados.isSelected()) {

            JOptionPane.showMessageDialog(this, game.opcionLanzarDados(this.contador) + "\n" + game.casillaOpcion(this.contador, this.turno));
            crearMenuJugador();

        } else if (this.botonMeditar.isSelected()) {

            MenuMeditar meditar = new MenuMeditar(this.game, this.contador, this.turno);
            this.dispose();

        } else if (this.botonHabilidadEspecial.isSelected()) {

            generarCampoHabilidadEspecial();
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una jugada");

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonTablero) {
            verTablero();

        }
        if (e.getSource() == this.botonAceptar) {
             jugar();
        }
        if (e.getSource() == this.botonPodio) {
            TablaPosiciones tabla = new TablaPosiciones(this.game, this.turno);

        }

    }
}
