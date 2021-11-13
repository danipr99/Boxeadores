package com.batalladeboxeo;

/**
 * @author Santiago Faci
 * @version curso 2014-2015
 */
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Principal {

    public static void main(String args[]) {
        
        ReentrantLock lock = new ReentrantLock();
        Ring ring = new Ring();
        Boxeador boxeador1 = new Boxeador("Tyson", ring, lock);
        Boxeador boxeador2 = new Boxeador("Rocky", ring, lock);
        boxeador1.setRival(boxeador2);
        boxeador2.setRival(boxeador1);

        Thread t1 = new Thread(boxeador1);
        Thread t2 = new Thread(boxeador2);

        t1.start();
        t2.start();

                Scanner scan = new Scanner(System.in);
                String p = scan.nextLine();
                
        boxeador1.interrupt();
        boxeador2.interrupt();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ie) {
        }

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
