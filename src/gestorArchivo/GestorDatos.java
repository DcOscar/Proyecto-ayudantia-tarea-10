/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorArchivo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;

public class GestorDatos {

    private String[][] datos;

    public void guardarPodio(int cantidadDatos, String[][] datos) {
        String numeracion, nombreJugador, posicion, salud;
        this.datos = datos;
        try {
            FileWriter fw = new FileWriter("podioFinal.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            Date date = new Date();
            bw.write(date.toString());
            bw.newLine();
            for (int i = 0; i < cantidadDatos; i++) {
                numeracion = this.datos[i][0] + "-";
                nombreJugador = this.datos[i][1] + ", ";
                posicion = "posición: " + this.datos[i][2] + ", ";
                salud = "salud " + this.datos[i][3] + ".";
                bw.write(numeracion + nombreJugador + posicion + salud);
                bw.newLine();
            }
            bw.close();
            fw.close();

        } catch (IOException e) {
            System.out.println("El archivo no puede ser guardado");

        }

    }

}
