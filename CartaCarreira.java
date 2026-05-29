public class CartaCarreira {

    private String nome;
    private double salario;

    public CartaCarreira(String nome) {
        this.nome = nome;
        this.salario = Profissao.consultarSalario(nome);
    }
}