import java.util.Random;

public class Roleta {
    public static Random alea = new Random();

    public static int GirarRoleta() {
        return alea.nextInt(6) + 1;
    }
}
