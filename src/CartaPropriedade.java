public class CartaPropriedade extends Carta {
    public CartaPropriedade (String descricao, double valor){
        super(descricao, valor);
    }
    @Override
    public void mostrarCarta(){
        System.out.println("[Propriedade]" + descricao + "| valor: R$" + valor);
    }

}
