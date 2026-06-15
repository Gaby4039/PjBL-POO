public class CartaPropriedade extends Carta {
    private String nome;
    private String tipoImovel;
    private double valorCompra;

    public CartaPropriedade(String nome, String tipoImovel, double valorCompra) {
        super(nome, valorCompra);
        this.nome = nome;
        this.tipoImovel = tipoImovel;
        this.valorCompra = valorCompra;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    @Override
    public void mostrarCarta() {
        System.out.println("[Propriedade] " + tipoImovel + " " + nome + " - R$" + valorCompra);
    }

}