import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.X509Certificate;

public class ClienteSSL {
    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int puerto = 6000;

        //System.setProperty("javax.net.ssl.trustStore","CliCertConfianza");
        //System.setProperty("javax.net.ssl.trustStorePassword","890123");

        System.out.println("PROGRAMA CLIENTE INICIADO...");

        SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket cliente = (SSLSocket) sfact.createSocket(host,puerto);

        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
        flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");

        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

        System.out.println("Recibiendo del servidor: \n\t" + flujoEntrada.readUTF());

        //sessionInfo(cliente);

        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();
    }

    public static void sessionInfo(SSLSocket cliente) throws SSLPeerUnverifiedException {
        SSLSession session = ((SSLSocket) cliente).getSession();
        System.out.println("Host: " + session.getPeerHost());
        System.out.println("Cifrado: " + session.getCipherSuite());
        System.out.println("Protocolo: " + session.getProtocol());
        System.out.println("IDentificador: " + new BigInteger(session.getId()));
        System.out.println("Creacion de la sesion: " + session.getCreationTime());

        X509Certificate certificate = (X509Certificate) session.getPeerCertificates()[0];
        System.out.println("Propietario: " + certificate.getSubjectDN());
        System.out.println("Algoritmo: " + certificate.getSigAlgName());
        System.out.println("Tipo: " + certificate.getType());
        System.out.println("Emisor: " + certificate.getIssuerDN());
        System.out.println("Numero serie: " + certificate.getSerialNumber());
    }

}
