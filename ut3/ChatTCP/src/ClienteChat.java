import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteChat extends JFrame implements ActionListener, Runnable {
    private static final long serialVersionUID = 1L;
    Socket socket = null;
    DataInputStream fentrada;
    DataOutputStream fsalida;
    String nombre;
    static JTextField mensaje = new JTextField();
    private JScrollPane scrollPane1;
    static JTextArea textarea1;
    JButton botonEnviar = new JButton("Enviar"), botonSalir = new JButton("Salir");
    boolean repetir = true;

    public ClienteChat(Socket s, String nombre) {
        super(" CONEXION DEL CLIENTE CHAT: " + nombre);
        setLayout(null);
        mensaje.setBounds(10,10,400,30);
        add(scrollPane1);

        botonEnviar.setBounds(420,10,100,30);
        add(botonEnviar);
        botonSalir.setBounds(420,10,100,30);
        add(botonSalir);

        textarea1.setEditable(false);
        botonEnviar.addActionListener(this);
        botonSalir.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.socket = s;
        this.nombre = nombre;

        try {
            fentrada = new DataInputStream(socket.getInputStream());
            fsalida = new DataOutputStream(socket.getOutputStream());
            String texto = " > Entra en el chat..." + nombre;
            fsalida.writeUTF(texto);
        } catch (IOException e) {
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        int puerto = 44444;
        Socket s = null;
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick: ");
        if (nombre.trim().length() == 0) {
            System.out.println("El nombre esta vacio...");
            return;
        }

        try {
            // cambiar "localhost" por la IP del servidor para conectar a otra maquina
            s = new Socket("localhost",puerto);
            ClienteChat cliente = new ClienteChat(s,nombre);
            cliente.setBounds(0,0,540,400);
            cliente.setVisible(true);
            new Thread(cliente).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(), "<<MENSAJE DE ERROR:1>>",JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEnviar) {
            if (mensaje.getText().trim().length() == 0) return;
            String texto = nombre + "> " + mensaje.getText();
            try {
                mensaje.setText("");
                fsalida.writeUTF(texto);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == botonSalir) {
            String texto = " > Abandona el chat..." + nombre;
            try {
                fsalida.writeUTF(texto);
                fsalida.writeUTF("*");
                repetir = false;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        String texto = "";
        while (repetir) {
            try {
                texto = fentrada.readUTF();
                textarea1.setText(texto);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(), "<<MENSAJE DE ERROR:2>>",JOptionPane.ERROR_MESSAGE);
                repetir = false;
            }
        }
    }


}
