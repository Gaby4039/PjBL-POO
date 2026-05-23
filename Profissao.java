import java.util.Map;
import java.util.HashMap;

public class Profissao {

    static Map<String, Double> salarios = new HashMap<>();

    // Para add novos cargos, apenas siga a estrutura: salarios.put("Profissão", "Valor"); Substitua os valores dentro das aspas duplas pelos valores desejados.
    static {
        salarios.put("Desenvolvedor", 5000.0);
        salarios.put("Médico", 15000.0);
        salarios.put("Professor", 4500.0);
        salarios.put("Engenheiro", 8000.0);
    }

    public static Map<String, Double> getSalarios() {
        return salarios;
    }
}