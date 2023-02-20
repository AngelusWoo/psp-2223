import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AlmacenClaveSecreta {
    public static void main(String[] args) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);

            SecretKey clave = kg.generateKey();

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Clave.secreta"));
            
            out.writeObject(clave);
            out.close();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Clave.secreta"));
            Key secreta = (Key) in.readObject();
            in.close();
            
        } catch (NoSuchAlgorithmException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}