package com.ut3.examen;

import java.net.Socket;

public class Hilos {
    int CONNECTIONS, CURRENT, MAX;
    Socket tabla[] = new Socket[MAX];
    String mensajes;

    public Hilos(int MAX, int CURRENT, int CONEXIONES, Socket[] tabla) {
        this.CONNECTIONS = CONEXIONES;
        this.CURRENT = CURRENT;
        this.MAX = MAX;
        this.tabla = tabla;
        mensajes = "";
    }

    public Hilos() { super(); }

    public int getCONEXIONES() {
        return CONNECTIONS;
    }

    public synchronized void setCONEXIONES(int CONEXIONES) {
        this.CONNECTIONS = CONEXIONES;
    }

    public int getCURRENT() {
        return CURRENT;
    }

    public synchronized void setCURRENT(int CURRENT) {
        this.CURRENT = CURRENT;
    }

    public int getMAX() {
        return MAX;
    }

    public void setMAX(int MAX) {
        this.MAX = MAX;
    }

    public Socket[] getTabla() {
        return tabla;
    }

    public void setTabla(Socket[] tabla) {
        this.tabla = tabla;
    }

    public String getMensajes() {
        return mensajes;
    }

    public synchronized void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public synchronized void addTabla(Socket s, int i) {
        tabla[i] = s;
    }

    public Socket getElementoTabla(int i) {
        return tabla[i];
    }
}
