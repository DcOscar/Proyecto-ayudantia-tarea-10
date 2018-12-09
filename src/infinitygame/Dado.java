/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

/**
 *
 * @author Oscar
 */
public class Dado {

    private int valorDado;

    public Dado() {
        this.valorDado = (int) (Math.random() * 6) + 1;
    }

    public int getValorDado() {
        return this.valorDado;
    }

}
