import java.util.Random;

public class Roleta {
    private Random alea;

    public Roleta() {
        this.alea = new Random();
    }

    public int girar() {
        return alea.nextInt(6) + 1;
    }
}
