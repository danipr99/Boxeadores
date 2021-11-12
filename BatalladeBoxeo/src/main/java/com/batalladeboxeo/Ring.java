package com.batalladeboxeo;



import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Ring {

    private final ReentrantLock l1;
    private int golpes;

    public Ring() {
        this.l1 = new ReentrantLock();
        golpes = 0;
    }

    public int getGolpes() {
        return golpes;
    }

    public synchronized void pegar(Boxeador boxeador) {
        l1.lock();
        boxeador.pegar();
        golpes++;

        System.out.println("Pegada de " + boxeador.getNombre() + " (" + boxeador.getGolpes() + ")");
        l1.unlock();
    }
}
