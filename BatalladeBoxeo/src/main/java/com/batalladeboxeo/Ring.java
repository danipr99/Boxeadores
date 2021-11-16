package com.batalladeboxeo;

/**
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Ring extends Thread{

    private int []golpes;
    private Boxeador []concursantes ;
    
    public Ring(Boxeador [] a) {
        golpes = new int[a.length];
        this.concursantes = a;
        for (int i = 0; i < a.length; i++) {            
            golpes[i] = 0;
                    
        }        
    }

    public int getGolpes(Boxeador b) {
        boolean found = false;
        for (int i = 0; i < concursantes.length; i++) {
            if (concursantes[i].equals(b)) {
                found = true;
                return golpes[i];
            }

        }
        if (!found) {
            System.out.println("No se ha encontrado el jugador " + b.getNombre());
        }
        return -1;
    }

    public void pegar(Boxeador boxeador) {
        
        golpes[posicionJugador(boxeador)]++;
        int pos = posicionJugador(boxeador);
        for(int i=0; i<concursantes.length; i++){
            if(i!=pos)
                concursantes[i].noquear();
                
        }
        System.out.println("Pegada de " + concursantes[pos].getNombre() + " (" + golpes[pos] + ")");
    }

    public int posicionJugador(Boxeador box) {
        boolean found = false;

        for (int i = 0; i < concursantes.length; i++) {
            if (concursantes[i].equals(box)) {
                found = true;
                return i;
            }     
            
        }
        if(!found)
            System.out.println("No se ha encontrado al jugador" + box.getNombre());
        return -1;
        }
    }
