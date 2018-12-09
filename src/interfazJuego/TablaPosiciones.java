/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazJuego;

import ventana.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import infinitygame.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TablaPosiciones extends JFrame {

    private String[][] datos;
    private int  turno;
    private InfinityGame game;

    public TablaPosiciones(InfinityGame game, int turno) {
        super("Infinity Game");
        this.turno = turno;
        this.game = game;
        generarTabla();
       super.setLocationRelativeTo(null);
        super.setResizable(false);
        this.pack();
        this.setVisible(true);

    }

  

    public void cerrarVentana() {
        this.dispose();
    }

    public void generarTabla() {
        String[] nombreColumnas = {"N°", "Nombre", "Posición", "Salud"};
        this.datos = this.game.obtenerPodio(this.turno);
        DefaultTableModel dtm = new DefaultTableModel(datos, nombreColumnas);
        final JTable tabla = new JTable(dtm);

        tabla.setPreferredScrollableViewportSize(new Dimension(500, 200));
        JScrollPane scrollPane = new JScrollPane(tabla);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                cerrarVentana();
            }
        });
    }

}
