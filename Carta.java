public abstract class Carta {

    protected String descricao;
    protected double valor;

    public Carta(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    // Método abstrato
    public abstract void mostrarCarta();

}
