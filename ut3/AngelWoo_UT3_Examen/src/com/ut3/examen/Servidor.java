package com.ut3.examen;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    String[] frases = {"frase1","frase2","frase3","frase4"};
    String[] libros = {"libro1","libro2","libro3","libro4"};

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5000);

        Socket cliente = serverSocket.accept();

        ObjectOutputStream salida;
        try {
            salida = new ObjectOutputStream(cliente.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        cliente.close();
    }
}


















