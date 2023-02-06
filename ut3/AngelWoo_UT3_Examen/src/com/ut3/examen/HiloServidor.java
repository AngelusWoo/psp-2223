package com.ut3.examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {
    private final int MAXIMO = 3;
    String[] frases = {"frase1","frase2","frase3","frase4"};
    String[] libros = {"libro1","libro2","libro3","libro4"};

    BufferedReader fentrada;
    PrintWriter fsalida;
    Socket socket;
    Hilos hilo;

    public HiloServidor(Socket s) { this.socket = s; }

    public void run() {
        while (true) {
            String cadena = "";
            try {
                cadena = fentrada.readLine();
                if (cadena.trim().equals("libro")) {
                    int randomNumber = ((int) (Math.random() * libros.length));
                    fsalida.write(libros[randomNumber]);
                    break;
                } else if (cadena.trim().equals("frase")) {
                    int randomNumber = ((int) (Math.random() * libros.length));
                    fsalida.write(frases[randomNumber]);
                    break;
                }
                hilo.setMensajes(hilo.getMensajes() + cadena + "\n");
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        try { socket.close(); }
        catch (IOException e) { e.printStackTrace(); }
    }
}
