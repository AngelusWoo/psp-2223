import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int Puerto = 6000;
        InetAddress IPSetvidor = InetAddress.getLocalHost();
        Scanner sc = new Scanner(System.in);

        DatagramSocket client = null;
        try {
            client = new DatagramSocket();
            System.out.println("PROGRAMA CLIENTE INICIADO");
        } catch (SocketException e) {
            System.out.println("ERROR");
            System.exit(0);
        }

        int skill = 0;

        do {
            System.out.println("Introduce un numero");

            try {
                skill = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                sc.nextLine();
                skill = 1;
                System.out.println("\tNumero incorrecto\n");
                continue;
            }

            Skill s = new Skill();
            s.setId(skill);

            // CONVERTIMOS OBJETO A BYTES
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bs);
            out.reset();
            out.writeObject(s);
            byte[] enviados = bs.toByteArray();

            // ENVIAR OBJETO
            DatagramPacket envio = new DatagramPacket(enviados,enviados.length,IPSetvidor,Puerto);
            client.send(envio);

            // SE RECIBE UN OBJETO
            if (skill > 0) {
                byte[] recibidos = new byte[1024];
                DatagramPacket paqRecibido = new DatagramPacket(recibidos,recibidos.length);
                client.receive(paqRecibido);

                //CONVERTIMOS BYTES A OBJETO
                ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
                ObjectInputStream in = new ObjectInputStream(bais);

                Skill dato = new Skill();
                dato = (Skill) in.readObject();
                in.close();

                System.out.println("\tHabilidad: " + dato.getName() + ", Puntos requeridos: * " + dato.getRequirement());
            }

        } while (skill > 0);

        System.out.println("SERVIDOR UDP FINALIZADO...");

        // CERRAR STREAMS Y SOCKETS
        client.close();


    }
}