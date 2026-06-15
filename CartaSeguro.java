public class CartaSeguro extends Carta {
    private String tipo;
    private double valor;

    public CartaSeguro(String tipo, double valor) {
        super(tipo, valor);
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public void mostrarCarta() {
        System.out.println("[Seguro] " + tipo + " - R$" + valor);
    }

}
