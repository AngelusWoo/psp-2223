import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Ejemplo6 {
    public static void main(String[] args) {
        try {
            FileInputStream filein = new FileInputStream("DATOS.DAT");
            ObjectInputStream dataOS = new ObjectInputStream(filein);
            Object o = dataOS.readObject();

            String datos = (String) o;
            System.out.println("Datos: " + datos);


            o = dataOS.readObject();

            byte resumenOriginal[] = (byte[]) o;

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(datos.getBytes());
            byte resumenActual[] = md.digest();

            if (MessageDigest.isEqual(resumenActual, resumenOriginal))
                System.out.println("DATOS VALIDOS");
            else System.out.println("DATOS NO VALIDOS");

            dataOS.close();
            filein.close();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}