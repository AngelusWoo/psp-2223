package ficheros;

public class Suspender {
    private boolean solicitaSuspender;

    public synchronized void set(boolean b) {
        solicitaSuspender = b;
        notifyAll();
    }

    public synchronized void esperandoParaReanudar() throws InterruptedException {
        while (solicitaSuspender) wait();
    }
}
