import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Propriedade implements Serializable {
    private String nome;
    private Double valorCompra;
    private String tipoImovel;

    private static final Map<String, Double> catalogoPropriedades = new HashMap<>();

    static {
        catalogoPropriedades.put("Mansão", 50000000.0);
        catalogoPropriedades.put("Cabana", 300000.0);
        catalogoPropriedades.put("Casa", 150000.0);
        catalogoPropriedades.put("Apartamento", 250000.0);
    }

    public Propriedade(String nome, String tipoImovel) {
        this.nome = nome;
        this.tipoImovel = tipoImovel;
        this.valorCompra = catalogoPropriedades.getOrDefault(tipoImovel, 0.0);
    }

    public String getNome() { return nome; }
    public Double getValorCompra() { return valorCompra; }
    public String getTipoImovel() { return tipoImovel; }


    public Double calcularValorVenda() {
        return this.valorCompra * 0.8;
    }

    @Override
    public String toString() {
        return tipoImovel + " " + nome + " (Comprado por: R$" + valorCompra + ")";
    }
}