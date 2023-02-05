import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramSocket serverSocket = new DatagramSocket(6000);

        Skill dato = new Skill();
        dato.setId(1);

        System.out.println("SERVIDOR UDP INICIADO");

        while (dato.getId() > 0) {

            // RECIBO DATAGRAMA
            dato = new Skill();
            byte[] recibidos = new byte[1024];
            DatagramPacket paqRecibido = new DatagramPacket(recibidos,recibidos.length);
            serverSocket.receive(paqRecibido);

            // CONVERTIR BYTES A OBJETO
            ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
            ObjectInputStream in = new ObjectInputStream(bais);
            dato = (Skill) in.readObject();

            long requirement = dato.getRequirement();
            String name = dato.getName();

            System.out.println("Recibo: " + dato);

            // DIRECCION ORIGEN
            InetAddress IPOrigen = paqRecibido.getAddress();
            int puerto = paqRecibido.getPort();

            // CONVERTIMOS OBJETO A BYTES
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bs);
            out.reset();
            out.writeObject(dato);

            byte[] bytes = bs.toByteArray();

            // ENVIO DATAGRAMA
            DatagramPacket paqEnviado = new DatagramPacket(bytes,bytes.length,IPOrigen,puerto);
            serverSocket.send(paqEnviado);
        }

        System.out.println("SERVIDOR UDP FINALIZADO...");

        // CERRAR STREAMS Y SOCKETS
        serverSocket.close();
    }
}