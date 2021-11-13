package com.batalladeboxeo;

/**
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Ring {

    private int golpes;
  
    
    public Ring() {
        golpes = 0;
   
    }

    public int getGolpes() {
        return golpes;
    }

    public void pegar(Boxeador boxeador) {
  
            boxeador.pegar();
            golpes++;
            System.out.println("Pegada de " + boxeador.getNombre() + " (" + boxeador.getGolpes() + ")");

        

    }
}
