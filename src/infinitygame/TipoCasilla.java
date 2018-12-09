/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

/**
 *
 * @author  Oscar
 */
public enum TipoCasilla {
      INICIO('I'),
     BLANCO('b') ,
    SALUD('s'),
    PORTAL('p'),
    DESAFIO('d'),
    FINAL('F');
    
    private final char tipoCasilla;
    private TipoCasilla(char tipo){
    this.tipoCasilla= tipo;}
    public char getTipoCasilla(){
    return this.tipoCasilla;}
}
