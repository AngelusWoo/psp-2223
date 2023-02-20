import javax.crypto.*;
import java.io.*;
import java.security.*;

public class Main {
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);

            KeyPair par = keyGen.generateKeyPair();

            PrivateKey clavepriv = par.getPrivate();
            PublicKey clavepub = par.getPublic();

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE,clavepub);

            byte[] textoPlano = "Esto es un texto Plano".getBytes();
            byte[] textoCifrado = c.doFinal(textoPlano);

            c.init(Cipher.DECRYPT_MODE,clavepriv);
            byte desencriptado[] = c.doFinal(textoCifrado);
            System.out.println("Desencriptado: " + new String(desencriptado));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            e.printStackTrace();
        }
    }
}