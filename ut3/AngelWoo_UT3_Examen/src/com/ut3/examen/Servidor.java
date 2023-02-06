package com.ut3.examen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server iniciado...");

        while (true) {
            Socket cliente = serverSocket.accept();
            HiloServidor hilo = new HiloServidor(cliente);

            hilo.start();

            ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream());
            String str = (String) perEnt.readObject();

            ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
            String ser = "";
            outObjeto.writeObject(ser);
        }

    }
}


















