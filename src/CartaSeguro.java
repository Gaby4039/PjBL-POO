public class CartaSeguro extends Carta{
    public CartaSeguro(String descricao, double valor){
        super(descricao, valor);
    }
    @Override
    public void mostrarCarta() {
        System.out.println("[Seguro:]" + descricao + "| Valor: R$" + valor);
    }
}
