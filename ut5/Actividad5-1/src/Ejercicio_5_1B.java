import javax.sound.midi.Soundbank;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Scanner;

public class Ejercicio_5_1B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Texto 1 (Clave): ");
            String clave = sc.nextLine();

            System.out.println("Texto 2: ");
            String texto2 = sc.nextLine();

            System.out.println("Texto 3: ");
            String texto3 = sc.nextLine();

            byte resumenClave[] = getResumen(clave);
            String resClave = new String(resumenClave);

            byte resumen1[] = getResumen(texto2);
            String res1 = new String(resumen1);

            byte resumen2[] = getResumen(texto3);
            String res2 = new String(resumen2);

            System.out.println("Resumen clave: " + resClave);
            System.out.println("Resumen 1: " + res1);
            System.out.println("Resumen 2: " + res2);

            if (!res1.equals(resClave) && !res2.equals(resClave)) System.out.println("Ningun texto coincide con la clave");
            else if (res1.equals(resClave) && !res2.equals(resClave)) System.out.println("El primer texto coincide con la clave");
            else if (!res1.equals(resClave) && res2.equals(resClave)) System.out.println("El segundo texto coincide con la clave");
            else System.out.println("Ambos textos coinciden con la clave");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    static byte[] getResumen (String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");;
        byte dataBytes[] = s.getBytes();
        md.update(dataBytes);
        return md.digest();
    }
}