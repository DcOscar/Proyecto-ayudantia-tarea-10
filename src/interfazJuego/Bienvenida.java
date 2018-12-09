/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazJuego;

import ventana.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Bienvenida extends Ventana {

    private JLabel bienvenida;
    private JButton botonInicio;

    public Bienvenida() {
        super("Infinity Game", 500, 400);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeBienvenida();
        generarBotonInicio();
    }

    private void generarMensajeBienvenida() {
        String textoBienvenida = "Bienvenidos a Infinity Game, ¡haga click en comenzar! ";
        super.generarJLabelEncabezado(this.bienvenida, textoBienvenida, 20, 30, 500, 30);
    }

    private void generarBotonInicio() {
        String textoBoton = "Comenzar";
        this.botonInicio = super.generarBoton(textoBoton, 175, 300, 150, 40);
        this.add(this.botonInicio);
        this.botonInicio.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonInicio) {
            CreacionJuego crear = new CreacionJuego();

            this.dispose();

        }

    }
}
