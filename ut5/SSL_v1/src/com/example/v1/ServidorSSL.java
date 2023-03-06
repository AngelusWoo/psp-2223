package com.example.v1;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
// java -Djavax.net.ssl.keyStore=AlmacenSrv -Djavax.net.ssl.keyStorePassword=1234567 ServidorSSL.java

public class ServidorSSL {
    public static void main(String[] args) throws IOException {
        int puerto = 6000;

        SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket servidorSSL = (SSLServerSocket) sfact.createServerSocket(puerto);
        SSLSocket clienteConectado = null;
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;

        for (int i = 0; i < 5; i++) {
            System.out.println("Esperando al cliente " + i);
            clienteConectado = (SSLSocket) servidorSSL.accept();
            flujoEntrada = new DataInputStream(clienteConectado.getInputStream());

            System.out.println("Recibiendo del CLIENTE: " + i + " \n\t" + flujoEntrada.readUTF());

            flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

            flujoSalida.writeUTF("Saludos al cliente del servidor");
        }

        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidorSSL.close();
    }
}