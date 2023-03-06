package com.example.v3;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ClienteSSL {
    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException {
        String host = "localhost";
        int puerto = 6000;

        FileInputStream ficCerConf = new FileInputStream("src/com/example/v3/CliCertConfianza");
        String claveCerConf = "890123";

        KeyStore almacenConf = KeyStore.getInstance(KeyStore.getDefaultType());
        almacenConf.load(ficCerConf,claveCerConf.toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(almacenConf);

        SSLContext contextoSSL = SSLContext.getInstance("TLS");
        contextoSSL.init(null,tmf.getTrustManagers(),null);

        System.out.println("PROGRAMA CLIENTE INICIADO...");

        SSLSocketFactory sfact = contextoSSL.getSocketFactory();
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
