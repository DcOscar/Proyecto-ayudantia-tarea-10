/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

import java.util.*;
import gestorArchivo.*;

public class InfinityGame {

    private ArrayList<Jugador> jugadores;
    private ArrayList<Character> casillas;
    private int largoTablero, cantidadJugadores, habilidadEspecialGuerrero;
    private Guardian guardian;
    private Reliquia reliquia;

    public InfinityGame() {

        this.jugadores = new ArrayList<Jugador>();
        this.reliquia = new Reliquia();
    }

    public int lanzarDados() {
        int valorDados = 0;
        for (int contador = 0; contador < 2; contador++) {
            Dado dado = new Dado();
            valorDados += dado.getValorDado();
        }

        return valorDados;
    }

    public void crearGuardian() {
        this.guardian = new Guardian(this.jugadores);

    }

    public boolean terminarJuego(int indice, int turno) {
        if (this.jugadores.get(indice).posicion == this.largoTablero) {
            GestorDatos gestor = new GestorDatos();
            gestor.guardarPodio(cantidadJugadoresPodio(turno), obtenerPodio(turno));
            return true;
        } else {
            return false;
        }
    }

    private String casillaFinal(int indice, int turno) {

        return "Ganó el jugador n°" + indice + 1 + " en el turno " + turno;

    }

    public int cantidadJugadoresPodio(int turno) {
        Podio podio = new Podio(this.jugadores, turno);
        return podio.obtenerCantidadDatos();
    }

    public void agregarJugadorMago(String nombre) {
        this.jugadores.add(new Mago(nombre));
    }

    public void agregarJugadorGuerrero(String nombre) {
        this.jugadores.add(new Guerrero(nombre));

    }

    public String getTipoJugador(int indice) {
        return this.jugadores.get(indice).getTipo();

    }

    public String getNombreJugador(int indice) {
        return this.jugadores.get(indice).getNombre();

    }

    private String casillaDesafio(int indice, int largoTablero) {
        int posibilidad = (int) (Math.random() * 2); //"0" se puede avanzar o retroceder, "1" gana o pierde vida
        int azar = (int) (Math.random() * 2); // 0= perder vida o retroceder, 1 ganar vida o avanzar
        int obtenerSalud;
        int perderSalud;
        int retroceder;
        int avanzar;
        System.out.println("Entro en una casilla desafio");
        if (posibilidad == 0) {
            avanzar = (int) (Math.random() * 5 + 1);
            retroceder = -avanzar;

            if (azar == 0) {
                this.jugadores.get(indice).setPosicion(retroceder);
                extremosPosiciones(indice);
                return "Entró en una casilla desafio, ha retrocedido " + avanzar + " casillas, posicion actual: " + this.jugadores.get(indice).getPosicion();

            } else {
                this.jugadores.get(indice).setPosicion(avanzar);
                extremosPosiciones(indice);
                return "Entró en una casilla desafio, ha avanzado " + avanzar + " casillas, posicion actual: " + this.jugadores.get(indice).getPosicion();
            }
        } else {
            obtenerSalud = (int) (Math.random() * 4 + 1);
            perderSalud = -obtenerSalud;
            if (azar == 0) {
                for (int i = 0; i < this.jugadores.size(); i++) {
                    this.jugadores.get(i).setSalud(perderSalud);
                }
                this.jugadores.get(indice).setSalud(obtenerSalud);
                extremosSalud();
                return "Entró en una casilla desafio, los demás jugadores han perdido " + obtenerSalud + " puntos de salud";
            } else {
                for (int i = 0; i < this.jugadores.size(); i++) {
                    this.jugadores.get(i).setSalud(obtenerSalud);
                }
                this.jugadores.get(indice).setSalud(perderSalud);
                extremosSalud();
                return "Entró en una casilla desafio, los demás jugadores han ganado " + obtenerSalud + " puntos de salud";
            }

        }

    }

    private void extremosSalud() {
        int saludMax;
        for (int i = 0; i < this.jugadores.size(); i++) {
            if (this.jugadores.get(i).getSalud() > this.jugadores.get(i).getSaludMax()) {
                saludMax = this.jugadores.get(i).getSaludMax();
                this.jugadores.get(i).setSaludExtremos(saludMax);
            }

            if (this.jugadores.get(i).getSalud() < 0) {

                this.jugadores.get(i).setSaludExtremos(0);
                System.out.println("El jugador n°" + i + 1 + "a perdido todas sus vidas, por lo tanto dejará de jugar");
                this.jugadores.remove(i);
            }
        }
    }

    private void extremosPosiciones(int indice) {

        if (this.jugadores.get(indice).getPosicion() > this.largoTablero) {
            this.jugadores.get(indice).setPosicionExtremos(this.largoTablero);
        }

        if (this.jugadores.get(indice).getPosicion() < 0) {
            this.jugadores.get(indice).setPosicionExtremos(0);
        }
    }

    public String casillaPortal(int largoTablero, int indice, int posicion) {

        ArrayList<Integer> posicionPortales = new ArrayList<Integer>();
        int portalAzar;
        int aux = -1;

        for (int i = 0; i < largoTablero; i++) {
            if (this.casillas.get(i).equals('p')) {  //cada vez que encuentra una casilla portal, agrega su posicion a un array
                posicionPortales.add(i);
                aux++;
                if (i == posicion) {
                    posicionPortales.remove(aux);
                    aux--;
                }
            }   // se elimina la posicion actual, en la suposicion de que es una casilla portal 
        }
        portalAzar = (int) (Math.random() * posicionPortales.size());
        this.jugadores.get(indice).setPosicionExtremos(posicionPortales.get(portalAzar)); //se cambia de posicion a un portal aleatorio
        return "Entró en una casilla portal, se ha transportado a la casilla n°" + (this.jugadores.get(indice).getPosicion());

    }

    public ArrayList getCasillas() {
        return this.casillas;
    }

    public String casillaSalud(int indice) {
        int azar = (int) (Math.random() * 2);
        int obtenerSalud = (int) (Math.random() * 3 + 1);
        int perderSalud = -obtenerSalud;
        boolean reliquia = this.reliquia.reliquiaSalud(this.jugadores, indice);
        System.out.println("Entro en una casilla salud");
        if (azar == 0) {
            this.jugadores.get(indice).setSalud(obtenerSalud); //obtiene entre 1 y 3 de vida
        } else {
            this.jugadores.get(indice).setSalud(perderSalud);

        }
        extremosSalud();
        if (reliquia == true) {
            return "Entró en una casilla salud, la salud actual es: " + this.jugadores.get(indice).getSalud() + "\n Gracias a la reliquia, obtuvo una meditación extra";
        } else {
            return "Entró en una casilla salud, la salud actual es: " + this.jugadores.get(indice).getSalud();
        }

    }

    public String opcionLanzarDados(int indice) {
        int mover = lanzarDados();
        boolean resultado;
        this.jugadores.get(indice).setPosicion(mover);
        extremosPosiciones(indice);
        resultado = this.reliquia.reliquiaDados(this.jugadores, indice, mover);
        if (resultado == true) {
            return "Se ha movido  " + mover + " espacios, posición actual: " + this.jugadores.get(indice).getPosicion() + "\n Gracias a la reliquia su vida se ha restaurado al máximo!";
        } else {
            return "Se ha movido  " + mover + " espacios, posición actual: " + this.jugadores.get(indice).getPosicion();
        }

    }

    public int getMeditar(int indice) {
        return this.jugadores.get(indice).getMeditar();
    }

    public String opcionMeditar(int indice, int mover) {
        int cantidadMeditar;
        cantidadMeditar = this.jugadores.get(indice).getMeditar();
        if (cantidadMeditar == 0) {
            this.jugadores.get(indice).setSalud(-1);
            return "No quedan meditaciones, posicion Actual: " + this.jugadores.get(indice).getPosicion() + ", salud: " + this.jugadores.get(indice).getSalud();
        } else {
            this.jugadores.get(indice).setMeditar(mover);
            this.jugadores.get(indice).setSalud(1);
            extremosSalud();
            extremosPosiciones(indice);
            return "Meditación exitosa, posicion Actual: " + this.jugadores.get(indice).getPosicion() + ", salud: " + this.jugadores.get(indice).getSalud();
        }

    }

    public String mostrarJugadores(int indice) {
        String mostrarJugadores = "Mostrar jugadores\n";
        for (int i = 0; i < this.jugadores.size(); i++) {
            if (i != indice) {
                mostrarJugadores += (i + 1) + "-" + this.jugadores.get(i).getNombre() + "\n";
            }

        }
        return mostrarJugadores;
    }

    public String opcionHabilidadEnfurecerse(String nombreJugador, int indice) {

        for (int i = 0; i < this.jugadores.size(); i++) {
            if (this.jugadores.get(i).getNombre().equals(nombreJugador)) {
                this.habilidadEspecialGuerrero = i;
            }
        }
        Guerrero guerrero = (Guerrero) this.jugadores.get(indice);
        return guerrero.enfurecerse(this.jugadores, this.habilidadEspecialGuerrero);
    }

    public String opcionHabilidadConcentracion(int indice) {

        Mago mago = (Mago) this.jugadores.get(indice);
        return mago.concentracion(this.guardian);

    }

    public int getSizeJugadores() {
        return this.jugadores.size();

    }

    public String casillaOpcion(int indice, int turno) {
        String resultado;
        int posicion = this.jugadores.get(indice).getPosicion();
        if (posicion >= this.casillas.size()) {
            posicion = this.casillas.size();

        }

        switch (this.casillas.get(posicion)) {
            case 'p':
                return casillaPortal(largoTablero, indice, posicion);

            case 's':
                return casillaSalud(indice);

            case 'd':
                return casillaDesafio(indice, largoTablero);

            case 'b':
                return "Entro en una casilla en blanco";

            case 'I':
                return "Está en la casilla inicial";

            case 'F':
                return casillaFinal(indice, turno);

            default:
                return "error";
        }

    }

    public int getCantidadJugadores() {

        return this.cantidadJugadores;
    }

    public boolean validadDatosInicialesJuego(int cantJugadores, int tamañoTablero) {

        if (cantJugadores >= 1 && tamañoTablero >= 20) {
            this.cantidadJugadores = cantJugadores;
            this.largoTablero = tamañoTablero;
            crearTablero();
            return true;
        } else {
            return false;
        }

    }

    private void crearTablero() {
        Tablero tablero = new Tablero(this.largoTablero);
        tablero.generarTablero();
        this.casillas = tablero.getCasillas();
    }

    public String[][] obtenerPodio(int turno) {
        Podio podio = new Podio(this.jugadores, turno);
        return podio.generarTabla();
    }

}
