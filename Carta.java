public abstract class Carta {

    protected String descricao;
    protected int valor;

    public Carta(String descricao, int valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    // Método abstrato
    public abstract void mostrarCarta();

}
