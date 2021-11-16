package com.batalladeboxeo;

/**
 * @author Santiago Faci
 * @version curso 2014-2015
 */
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
        

public class Principal {

    public static void main(String args[]) {
        System.out.println("Introduce cuantos boxeadores desea");
        int nJugadores = 3;
        ReentrantLock lock = new ReentrantLock();
         ExecutorService executor = Executors.newFixedThreadPool(nJugadores+1);
        Ring ring;
        Boxeador boxeador1; // = new Boxeador("Tyson", ring, lock);
        Boxeador boxeador2; // = new Boxeador("Rocky", ring, lock);
        Boxeador boxeador3;
        Boxeador concursantes[] = new Boxeador[nJugadores];
        ring = new Ring(concursantes);
        boxeador1 = new Boxeador("Tyson", ring, lock);
        boxeador2 = new Boxeador("Rocky", ring, lock);
        boxeador3 = new Boxeador("Popeye", ring, lock);
        concursantes[0]= boxeador1;
        concursantes[1] = boxeador2;
        concursantes[2] = boxeador3;

        Thread t1 = new Thread(ring);
        Thread t2 = new Thread(boxeador1);
        Thread t3 = new Thread(boxeador2);
        Thread t4 = new Thread(boxeador3);

//        executor.execute(ring);
//        executor.execute(boxeador1);
//        executor.execute(boxeador2);
//        executor.execute(boxeador3);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        Scanner scan = new Scanner(System.in);
        String p = scan.nextLine();
                
        boxeador1.interrupt();
        boxeador2.interrupt();
        boxeador3.interrupt();
        ring.interrupt();
 
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException ie) {
        }
        //executor.shutdownNow();
        System.out.println("Fin del combate");
        if (boxeador1.getGolpes() > boxeador2.getGolpes()) {
            System.out.println("Ha ganado " + boxeador1.getNombre());
        } else if (boxeador1.getGolpes() < boxeador2.getGolpes()) {
            System.out.println("Ha ganado " + boxeador2.getNombre());
        } else {
            System.out.println("Empate");
        }
    }
}
