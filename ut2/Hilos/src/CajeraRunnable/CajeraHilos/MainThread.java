package CajeraRunnable.CajeraHilos;


public class MainThread {

    public static void main(String[] args) {

        ClienteThread cliente1 = new ClienteThread("Cliente 1", new int[] { 2, 2, 1, 5, 2, 3 });
        ClienteThread cliente2 = new ClienteThread("Cliente 2", new int[] { 1, 3, 5, 1, 1 });

        // Tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();

        CajeraThread cajera1 = new CajeraThread("Cajera 1", cliente1, initialTime);
        CajeraThread cajera2 = new CajeraThread("Cajera 2", cliente2, initialTime);

        Thread hilocajera1 =new Thread(cajera1);
        Thread hilocajera2 =new Thread(cajera2);

        hilocajera1.start();
        hilocajera2.start();
    }
}