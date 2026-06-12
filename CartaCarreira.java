import java.io.Serializable;

public class CartaCarreira implements Serializable {

    private String nome;
    private double salario;

    public CartaCarreira(String nome) {
        this.nome = nome;
        this.salario = Profissao.consultarSalario(nome);
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }
}