import java.util.Map;
import java.util.HashMap;

public class Profissao {

    private static final Map<String, Double> salarios = new HashMap<>();

    static {
        salarios.put("Desenvolvedor", 5000.0);
        salarios.put("M�dico", 15000.0);
        salarios.put("Professor", 4500.0);
        salarios.put("Engenheiro", 8000.0);
    }

    private Profissao() {
    }

    public static Map<String, Double> getSalarios() {
        return salarios;
    }

    public static double consultarSalario(String nomeDaProfissao) {
        return salarios.getOrDefault(nomeDaProfissao, 0.0);
    }
}
