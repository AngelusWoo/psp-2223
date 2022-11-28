package ficheros;

public class MainHilo extends Thread {
   private Suspender suspender = new Suspender();
   private boolean stopHilo = false;
   private int c = 0;

   public void pararHilo() {stopHilo = true;}

   int getContador() {return c;}

    public void Suspende() {suspender.set(true);}
    public void Reanuda() {suspender.set(false);}

    public void run() {
       try {
           while (!stopHilo) {
               c++;
               System.out.print(" " + c + " ");
               sleep(1000);
               suspender.esperandoParaReanudar();
           }
           System.out.println("Fin del hilo");
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
    }
}
