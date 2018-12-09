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
public class Casilla {

    private char casilla;
    private TipoCasilla tipoCasilla;
    public Casilla() {
        
        tipoCasilla();
        switch (tipoCasilla) {
            case BLANCO:
                this.casilla = tipoCasilla.BLANCO.getTipoCasilla();
                break;
            case PORTAL:
                this.casilla =  tipoCasilla.PORTAL.getTipoCasilla();
                break;
            case SALUD:
                this.casilla =  tipoCasilla.SALUD.getTipoCasilla();
                break;
            case DESAFIO:
                this.casilla = tipoCasilla.DESAFIO.getTipoCasilla();
                break;
            default:
                break;
        }
    }

    public char getCasilla() {
        return this.casilla;
    }
  private void tipoCasilla(){
   int probabilidad= (int)(Math.random()*3);
   switch(probabilidad){
       case 0:
    this.tipoCasilla= tipoCasilla.BLANCO;
   break;
       case 1:
    this.tipoCasilla= tipoCasilla.PORTAL;
   break;
       case 2:
    this.tipoCasilla= tipoCasilla.SALUD;
    break;
       case 3:
    this.tipoCasilla= tipoCasilla.DESAFIO;
   break;}
 }
 public char tipoCasillaInicio(){
 this.casilla= tipoCasilla.INICIO.getTipoCasilla();
 return this.casilla;}
 public char tipoCasillaFinal(){
  this.casilla= tipoCasilla.FINAL.getTipoCasilla();
 return this.casilla;}
}
