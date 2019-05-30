package pnrs.vezbe.projekat_1;

public class MyNDK {
    static {
        System.loadLibrary("MyLibrary");
    }
    public native int increment(int x);
    public native double convertDegrees(double d, int t);
}
