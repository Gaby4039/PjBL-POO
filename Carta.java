import java.io.Serializable;

public abstract class Carta implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String descricao;
    protected double valor;

    public Carta(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    // Método abstrato
    public abstract void mostrarCarta();

}
