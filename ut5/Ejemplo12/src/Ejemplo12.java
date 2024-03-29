import javax.crypto.*;
import java.security.*;

public class Ejemplo12 {
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair par = keyGen.generateKeyPair();
            PrivateKey clavepriv = par.getPrivate();
            PublicKey clavepub = par.getPublic();

            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey claveSecreta = kg.generateKey();

            Cipher c = Cipher.getInstance("RSA/ECB/PKCS5Padding");
            c.init(Cipher.WRAP_MODE,clavepub);
            byte[] claveenvuelta = c.wrap(claveSecreta);

            c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE,claveSecreta);
            byte[] textoPlano = "Esto es un texto Plano".getBytes();
            byte[] textoCifrado = c.doFinal(textoPlano);
            System.out.println("Encriptado: " + new String(textoCifrado));

            Cipher c2 = Cipher.getInstance("RSA/ECB/PKCS5Padding");
            c2.init(Cipher.UNWRAP_MODE,clavepriv);

            Key clavedesenvuelta = c2.unwrap(claveenvuelta,"AES",Cipher.SECRET_KEY);

            c2 = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c2.init(Cipher.DECRYPT_MODE,clavedesenvuelta);
            byte[] desencriptado = c.doFinal(textoCifrado);
            System.out.println("Desencriptado: " + new String(desencriptado));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            e.printStackTrace();
        }
    }
}