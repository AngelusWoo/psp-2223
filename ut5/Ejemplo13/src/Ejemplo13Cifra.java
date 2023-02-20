import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Ejemplo13Cifra {
    public static void main(String[] args) {
        try {
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("Clave.secreta")));
            Key claveSecreta = (Key) in.readObject();
            in.close();

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE,claveSecreta);

            FileInputStream filein = new FileInputStream("FICHERO.pdf");

            CipherOutputStream out = new CipherOutputStream(Files.newOutputStream(Paths.get("FicheroPDF.cifrado")),c);
            int tambloque = c.getBlockSize();
            byte[] bytes = new byte[tambloque];

            int i = filein.read(bytes);
            while (i != -1) {
                out.write(bytes,0,i);
                i = filein.read(bytes);
            }
            out.flush();
            out.close();
            filein.close();
            System.out.println("Fichero cifrado con clave secreta");
        } catch (IOException | ClassNotFoundException | NoSuchPaddingException | NoSuchAlgorithmException |
                 InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}