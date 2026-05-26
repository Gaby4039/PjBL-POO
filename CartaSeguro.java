public class CartaSeguro extends Carta{
    public CartaSeguro(String descricao, int valor){
        super(descricao, valor);
    }
    @Override
    public void mostrarCarta() {
        System.out.println("[Seguro:]" + descricao + "| Valor: R$" + valor);
    }
}
