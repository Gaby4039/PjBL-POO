import java.util.Map;
import java.util.HashMap;

public class Profissao {

    private String nome;
    private double salario;

    private static final Map<String, Double> catalogoSalarios = new HashMap<>();

    static {
        catalogoSalarios.put("Desenvolvedor", 5000.0);
        catalogoSalarios.put("Médico", 15000.0);
        catalogoSalarios.put("Professor", 4500.0);
        catalogoSalarios.put("Engenheiro", 8000.0);
    }


    public Profissao(String nome) {
        this.nome = nome;
        this.salario = consultarSalario(nome);
    }

    public String getNome() { return nome; }
    public double getSalario() { return salario; }

    public static double consultarSalario(String nomeDaProfissao) {
        return catalogoSalarios.getOrDefault(nomeDaProfissao, 0.0);
    }

    @Override
    public String toString() {
        return "Profissão: " + nome + " | Salário: R$" + salario;
    }
}