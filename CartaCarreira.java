public class CartaCarreira extends Carta {
    public CartaCarreira(String descricao, int valor) {
        super(descricao, valor);
    }
    @Override
    public void mostrarCarta(){
        System.out.println("[Carreira]" + descricao +"| Valor: R$" + valor);
    }
}
