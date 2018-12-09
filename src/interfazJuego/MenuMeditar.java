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

public class MenuMeditar extends Ventana {

    private JFormattedTextField meditacion;
    private JLabel textoMeditar;
    private JButton botonAceptar;
    private final InfinityGame game;
    private int contador, turno;

    public MenuMeditar(InfinityGame game, int contador, int turno) {
        super("Infinity Game", 400, 300);
        generarElementosVentana();
        this.game = game;
        this.contador = contador;
        this.turno = turno;
    }

    private void generarElementosVentana() {
        generarCampoMeditacion();
        generarBotonAceptar();
    }

    private void generarCampoMeditacion() {
        String texto = "Indique cuanto se moverá";
        super.generarJLabel(this.textoMeditar, texto, 20, 80, 180, 20);
        InternationalFormatter formato = super.generarFormato(-3, 3);
        this.meditacion = super.generarJFormattedTextField(formato, 200, 80, 150, 20);
        this.add(this.meditacion);
    }

    private void generarBotonAceptar() {
        String texto = "Aceptar";
        this.botonAceptar = super.generarBoton(texto, 200, 180, 100, 20);
        this.add(this.botonAceptar);
        this.botonAceptar.addActionListener(this);

    }

    private void generarMeditacion() {
        if (this.meditacion.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Recuerde elegir cuando desea moverse");
        } else {
            int meditar = Integer.parseInt(this.meditacion.getText());
            String resultado = this.game.opcionMeditar(this.contador, meditar);
            JOptionPane.showMessageDialog(this, resultado + "\n" + game.casillaOpcion(this.contador, this.turno));
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
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonAceptar) {
            generarMeditacion();
        }

    }

}
