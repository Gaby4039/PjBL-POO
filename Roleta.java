import java.util.Random;
import java.io.Serializable;

public class Roleta implements Serializable {
    private Random alea;

    public Roleta() {
        this.alea = new Random();
    }

    public int girar() {
        int resultado = alea.nextInt(6) + 1;
        System.out.println("Roleta girou o número: " + resultado);
        return resultado;
    }
}