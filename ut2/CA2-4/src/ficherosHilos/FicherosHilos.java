package ficherosHilos;

import java.io.FileReader;
import java.util.Scanner;

public class FicherosHilos extends Thread implements Runnable {
    static String[] files;
    static int num = 0;
    private long initialTime;

    public FicherosHilos(String[] files, long initialTime) {
        this.files = files;
        this.initialTime = initialTime;
    }

    @Override
    public void run() {
        long t_comienzo, t_fin;
        t_comienzo = initialTime;
        Proceso();
        t_fin = System.currentTimeMillis();
        long tiempoTotal = t_fin - t_comienzo;
        System.out.println("El proceso ha tardado " + tiempoTotal + " milisegundos.");
    }

    public void Proceso() {
        try {
            for (int i = 0; i < files.length; i++) {
                FileReader fr = new FileReader(files[i]);
                int caract = fr.read();

                while (caract != -1) if (caract == ' ' || caract == '\n') num++;

                System.out.println("Numero de palabras del fichero " + files[i] + ": " + num);
            }
        } catch (Exception ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        }
    }

    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
