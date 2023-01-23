import java.net.Socket;

public class ComunHilos {
    int CONEXIONES, ACTUALES, MAXIMO;
    Socket tabla[] = new Socket[MAXIMO];
    String mensajes;

    public ComunHilos( int MAXIMO, int ACTUALES, int CONEXIONES, Socket[] tabla) {
        this.CONEXIONES = CONEXIONES;
        this.ACTUALES = ACTUALES;
        this.MAXIMO = MAXIMO;
        this.tabla = tabla;
        mensajes = "";
    }

    public ComunHilos() { super(); }

    public int getCONEXIONES() {
        return CONEXIONES;
    }

    public synchronized void setCONEXIONES(int CONEXIONES) {
        this.CONEXIONES = CONEXIONES;
    }

    public int getACTUALES() {
        return ACTUALES;
    }

    public synchronized void setACTUALES(int ACTUALES) {
        this.ACTUALES = ACTUALES;
    }

    public int getMAXIMO() {
        return MAXIMO;
    }

    public void setMAXIMO(int MAXIMO) {
        this.MAXIMO = MAXIMO;
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
