package com.batalladeboxeo;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Boxeador extends Thread {

    private String nombre;
    private Ring ring;
    private int golpes;
    private boolean noqueado;
    private Boxeador rival;
    private ReentrantLock lock;
    private Condition sePuede;
    private volatile boolean hit;

    public Boxeador(String nombre, Ring ring, ReentrantLock lock) {

        this.nombre = nombre;
        this.ring = ring;
        noqueado = false;
        this.lock = lock;
        this.sePuede = lock.newCondition();
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

        while (!isInterrupted()) {
            lock.lock();
            try {
                if (noqueado) {
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
            }finally{
                lock.unlock();
            }

        }
    }
}
