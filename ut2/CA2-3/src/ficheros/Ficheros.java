package ficheros;

import java.io.FileReader;
import java.util.Scanner;

public class Ficheros {
    static String[] files;
    static int num = 0;

    public Ficheros(String[] files) {
        this.files = files;
    }

    public void Procesar() {
        long t_comienzo, t_fin;
        t_comienzo = System.currentTimeMillis();
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

                while (caract != -1) num++;

                System.out.println("Numero de caracteres del fichero " + files[i] + ": " + num);
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
