import java.util.HashMap;
import java.util.Map;

public class Propriedade {
    private String nome;
    private Double valorCompra;
    private String tipoImovel;

    public Propriedade (String nome, Double valorCompra, String tipoImovel) {
        this.nome = nome;
        this.valorCompra = valorCompra;
        this.tipoImovel = tipoImovel;
    }

    public String getNome() {
        return nome;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    static Map<String, Double> propriedades = new HashMap<>();

    // Para add novos tipos de propriedades, apenas siga a estrutura: salarios.put("Propriedades", "Valor"); Substitua os valores dentro das aspas duplas pelos valores desejados.
    static {
        propriedades.put("Mansão", 50000000.0);
        propriedades.put("Cabana", 300000.0);
        propriedades.put("Casa", .0);
        propriedades.put("Apartamento", .0);
    }

}