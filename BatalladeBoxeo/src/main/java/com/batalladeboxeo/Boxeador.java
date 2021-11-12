package com.batalladeboxeo;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Boxeador implements Runnable {

    private String nombre;
    private Ring ring;
    private int golpes;
    private boolean noqueado;
    private Boxeador rival;

    public Boxeador(String nombre, Ring ring) {

        this.nombre = nombre;
        this.ring = ring;
        noqueado = false;
 
    }

    public Boxeador getRival() {
        return rival;
    }

    public void setRival(Boxeador rival) {
        this.rival = rival;
    }
    

    public boolean isNoqueado() {
        return noqueado;
    }

    public void setNoqueado(boolean noqueado) {
        this.noqueado = noqueado;
    }

    public String getNombre() {
        return nombre;
    }

    public int getGolpes() {
        return golpes;
    }

    public void pegar() {
        golpes++;
        rival.setNoqueado(true);
    }
    public void noquear(){
        
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            if(noqueado){
                try {
                    Thread.sleep(new Random().nextInt(250));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Boxeador.class.getName()).log(Level.SEVERE, null, ex);
                }
                noqueado = false;
            }
            ring.pegar(this);
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {                          
            }
        }
    }
}
