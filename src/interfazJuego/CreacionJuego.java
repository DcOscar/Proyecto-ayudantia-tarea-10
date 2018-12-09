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
import javax.swing.text.InternationalFormatter;

public class CreacionJuego extends Ventana {

    private JButton botonAceptar, botonCancelar;
    private JFormattedTextField cantidadJugadores, largoTablero;
    private JLabel textoCantJugadores, textoLargoTablero, textoEncabezado;

    public CreacionJuego() {
        super("Infinity Game", 500, 400);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarEncabezado();
        generarCampoCantJugadores();
        generarCampoLargoTablero();
        generarBotonAceptar();
        generarBotonCancelar();
    }

    private void generarEncabezado() {
        String textoCabecera = "Generar juego";
        super.generarJLabelEncabezado(this.textoEncabezado, textoCabecera, 190, 20, 200, 50);

    }

    private void generarCampoCantJugadores() {
        String textoCantJugadores = "Ingrese la cantidad de jugadores:";
        super.generarJLabel(this.textoCantJugadores, textoCantJugadores, 20, 100, 200, 20);
        InternationalFormatter formato = super.generarFormato(1);
        this.cantidadJugadores = super.generarJFormattedTextField(formato, 230, 100, 150, 20);
        this.add(this.cantidadJugadores);
    }

    private void generarCampoLargoTablero() {
        String textoTablero = "Ingrese el largo del tablero:";
        super.generarJLabel(this.textoLargoTablero, textoTablero, 20, 200, 200, 20);
        InternationalFormatter formato = super.generarFormato(20);
        this.largoTablero = super.generarJFormattedTextField(formato, 230, 200, 150, 20);
        this.add(this.largoTablero);
    }

    private void generarBotonAceptar() {
        String textoBotonAceptar = "Aceptar";
        this.botonAceptar = super.generarBoton(textoBotonAceptar, 75, 300, 100, 20);
        this.add(this.botonAceptar);
        this.botonAceptar.addActionListener(this);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        this.botonCancelar = super.generarBoton(textoBotonCancelar, 325, 300, 100, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);

    }

    private void validarEntradas() {
        if (this.cantidadJugadores.getText().length() == 0 || this.largoTablero.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Recuerde rellenar todos los campos");
        } else {
            int cantJugadores = Integer.parseInt(this.cantidadJugadores.getText());
            int largoTablero = Integer.parseInt(this.largoTablero.getText());
            InfinityGame game = new InfinityGame();
            boolean condicion = game.validadDatosInicialesJuego(cantJugadores, largoTablero);
            if (condicion == true) {
                JOptionPane.showMessageDialog(this, "Se ha iniciado correctamente, el tablero generado es: " + game.getCasillas());
                int contador = 0;
                CrearJugador crearJugador = new CrearJugador(game, contador);
                this.dispose();

            } else if (condicion == false) {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonCancelar) {
            Bienvenida inicio = new Bienvenida();
            this.dispose();
        }
        if (e.getSource() == this.botonAceptar) {
            validarEntradas();
        }

    }

}
