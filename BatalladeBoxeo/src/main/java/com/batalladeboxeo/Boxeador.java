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
    
    private boolean noqueado;
    private final ReentrantLock lock;
    private final Condition sePuede;
    private volatile boolean play;

    public Boxeador(String nombre, Ring ring, ReentrantLock lock) {

        this.nombre = nombre;
        this.ring = ring;
        noqueado = false;
        this.lock = lock;
        this.sePuede = lock.newCondition();
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
        return ring.getGolpes(this);
    }


    public void noquear(){
        noqueado=true;
    }

    @Override
    public void run() {

        while (!isInterrupted()) {
            System.out.println("Va a golpear " + getNombre());
            lock.lock();
            try {
                if (noqueado) {

                    System.out.println(getNombre() + " esta noqueado");
                    try {
                        Thread.sleep(new Random().nextInt(100));
                    } catch (InterruptedException ex) {
                        interrupt();
                      Logger.getLogger(Boxeador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    noqueado = false;
                }

                ring.pegar(this);
                try {
                    Thread.sleep(new Random().nextInt(1000));

                } catch (InterruptedException ex) {
                    interrupt();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
