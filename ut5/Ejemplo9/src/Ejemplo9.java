import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class Ejemplo9 {
    public static void main(String[] args) {
        try {
            FileInputStream inpub = new FileInputStream("Clave.publica");
            byte[] bufferPriv = new byte[inpub.available()];
            inpub.read(bufferPriv);
            inpub.close();

            KeyFactory keyDSA = KeyFactory.getInstance("DSA");
            PKCS8EncodedKeySpec clavePublicaSpec = new PKCS8EncodedKeySpec(bufferPriv);
            PublicKey clavePublica = keyDSA.generatePublic(clavePublicaSpec);

            FileInputStream firmafic = new FileInputStream("FICHERO.DAT");
            byte[] firma = new byte[firmafic.available()];
            firmafic.read(firma);
            firmafic.close();

            Signature dsa = Signature.getInstance("SHA256withDSA");
            dsa.initVerify(clavePublica);

            FileInputStream fichero = new FileInputStream("FICHERO.DAT");
            BufferedInputStream bis = new BufferedInputStream(fichero);
            byte[] buffer = new byte[bis.available()];
            int len;

            while ((len = bis.read(buffer)) >= 0) dsa.update(buffer,0,len);
            bis.close();

            boolean verifica = dsa.verify(firma);

            if (verifica) System.out.println("LOS DATOS CORRESPONDEN CON SU FIRMA");
            else System.out.println("LOS DATOS NO SE CORRESPONDEN CON SU FIRMA");
        } catch (NoSuchAlgorithmException |
                 SignatureException |
                 InvalidKeyException |
                 IOException |
                 InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}