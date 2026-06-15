import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Profissao implements Serializable {

    private String nome;
    private double salario;

    private static final Map<String, Double> catalogoSalarios = new HashMap<>();

    public static void carregarProfissoesDoCSV(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            System.out.println("Carregando profissões do arquivo CSV...");
            
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                
                String[] partes = linha.split(";");
                
                if (partes.length == 2) {
                    String nomeProf = partes[0].trim();
                    double salarioProf = Double.parseDouble(partes[1].trim());
                    catalogoSalarios.put(nomeProf, salarioProf);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV. Carregando profissões padrão. Erro: " + e.getMessage());
            catalogoSalarios.put("Desenvolvedor Padrão", 5000.0);
            catalogoSalarios.put("Médico Padrão", 7000.0);
        } catch (NumberFormatException e) {
            System.err.println("Erro de formatação de número no CSV: " + e.getMessage());
        }
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