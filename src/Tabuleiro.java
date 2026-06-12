import java.util.ArrayList;

public class Tabuleiro {
    private ArrayList<Casa> casas;
    private int quantidadeCasas;

    public Tabuleiro(int quantidadeCasas) {
        this.quantidadeCasas = quantidadeCasas;
        this.casas = new ArrayList<>(quantidadeCasas);
    }

    public ArrayList<Casa> getCasas() {
        return casas;
    }

    public int getQuantidadeCasas() {
        return quantidadeCasas;
    }

    public void setCasas(Casa casa) {
        if (casa == null) {
            return;
        }

        if (casas.size() < quantidadeCasas) {
            casas.add(casa);
        } else {
            casas.add(casa);
            quantidadeCasas = casas.size();
        }
    }

    public Casa getCasa(int indice) {
        if (indice < 0 || indice >= casas.size()) {
            return null;
        }
        return casas.get(indice);
    }
}

