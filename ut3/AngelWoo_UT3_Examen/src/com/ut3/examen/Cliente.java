package com.ut3.examen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        int PUERTO = 5000;
        String HOST = "localhost";

        Socket socket = new Socket(HOST,PUERTO);

    }
}
