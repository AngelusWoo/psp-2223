package ficheros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{

    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try {
            MainHilo hilo = new MainHilo();
            String cad;

            while (true) {
                System.out.print("\tIntroduce CADENA: ");
                cad = br.readLine();

                if (!hilo.isAlive()) {
                    System.out.println("Lanzando hilo...");
                    hilo.start();
                }

                if (cad.equals("*")) break;
                if (cad.equals("S")) hilo.Suspende();
                if (cad.equals("R")) hilo.Reanuda();
            }
            hilo.Reanuda();
            System.out.println(" Contador:" + hilo.getContador());
            hilo.pararHilo();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }



}