package ficherosHilos;

public class MainHilos {

    public static void main(String[] args) {
        FicherosHilos f = new FicherosHilos(new String[] {"text/Test1.txt","text/Test2.txt"}, System.currentTimeMillis());
        f.start();
    }
}