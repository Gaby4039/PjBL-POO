import java.util.ArrayList;

public class Tabuleiro {
    private ArrayList<Casa> casas;
    private int quantidadeCasas;

    public Tabuleiro(int quantidadeCasas) {
        ArrayList<Casa> casas = new ArrayList<>(quantidadeCasas);
    }

    public ArrayList<Casa> getCasas() {
        return casas;
    }

}


