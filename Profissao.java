import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Profissao implements Serializable {

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

    public static String[] listarProfissoesDisponiveis() {
        ArrayList<String> nomes = new ArrayList<>(catalogoSalarios.keySet());
        Collections.sort(nomes);
        return nomes.toArray(new String[0]);
    }

    @Override
    public String toString() {
        return "Profissão: " + nome + " | Salário: R$" + salario;
    }
}