/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infinitygame;
import java.util.*;

public class Mago extends Jugador{
    private int mana;
    public Mago(String nombre) {
        super(nombre);
        super.meditar=7;
        super.saludMax=15;
        super.meditar=7;
        this.mana=4;
    }
public String concentracion(Guardian guardian){
 if(this.mana==0){
 return "Se ha quedado sin maná para la concentración";
 }
 else{
 guardian.dañarGuardian(-2);
 this.mana--;
 return "Activando habilidad especial concentración, maná actual: "+this.mana;
 }
}

public String getTipo(){
return "Mago";
    }

  
}
