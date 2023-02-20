import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Ejemplo13Descifra {
    public static void main(String[] args) {
        try {
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("Clave.secreta")));
            Key claveSecreta = (Key) in.readObject();
            in.close();

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE,claveSecreta);

            CipherInputStream cin = new CipherInputStream(Files.newInputStream(Paths.get("FicheroPDF.cifrado")),c);
            int tambloque = c.getBlockSize();
            byte[] bytes = new byte[tambloque];

            FileOutputStream fileout = new FileOutputStream("FICHERO.pdf");



            int i = cin.read(bytes);
            while (i != -1) {
                fileout.write(bytes,0,i);
                i = cin.read(bytes);
            }
            fileout.close();
            cin.close();
            System.out.println("Fichero descifrado con clave secreta");
        } catch (IOException | ClassNotFoundException | NoSuchPaddingException | NoSuchAlgorithmException |
                 InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}