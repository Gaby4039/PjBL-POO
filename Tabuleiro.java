import java.util.ArrayList;
import java.io.Serializable;

public class Tabuleiro implements Serializable {
    private ArrayList<Casa> casas;
    private int quantidadeCasas = 40;

    public Tabuleiro() {
        this.casas = new ArrayList<>(quantidadeCasas);
    }

    public ArrayList<Casa> getCasas() {
        return casas;
    }

    public int getQuantidadeCasas() {
        return quantidadeCasas;
    }

    public void setCasas(Casa casa) {
        if (casa == null) {
            return;
        }

        if (casas.size() < quantidadeCasas) {
            casas.add(casa);
        } else {
            casas.add(casa);
            quantidadeCasas = casas.size();
        }
    }

    public Casa getCasa(int indice) {
        if (indice < 0 || indice >= casas.size()) {
            return null;
        }
        return casas.get(indice);
    }

    public void montarTabuleiro() {
        setCasas(new CasaVazia("Início: escolha seu caminho"));
        setCasas(new CasaEvento("Escolha sua carreira", CasaEvento.TipoEvento.CARREIRA));
        setCasas(new CasaFinanceira("Recebe salário", 5000.0, CasaFinanceira.TipoFinanceira.GANHO));
        setCasas(new CasaMovimento("Avance 2 casas", CasaMovimento.TipoMovimento.AVANCAR, 2));
        setCasas(new CasaEvento("Casamento! Celebre com seu parceiro.", CasaEvento.TipoEvento.CASAMENTO));
        setCasas(new CasaFinanceira("Pague manutenção da casa", 200.0, CasaFinanceira.TipoFinanceira.PERDA));
        setCasas(new CasaEspecial("Sorte: ganhou um prêmio", 1000.0, CasaEspecial.TipoEspecial.SORTE));
        setCasas(new CasaEvento("Promoção no trabalho", CasaEvento.TipoEvento.PROMOCAO, 1500.0));
        setCasas(new CasaMovimento("Volte 1 casa", CasaMovimento.TipoMovimento.VOLTAR, 1));
        setCasas(new CasaFinanceira("Bônus de aniversário", 300.0, CasaFinanceira.TipoFinanceira.GANHO));
        setCasas(new CasaEvento("Nasceu um filho", CasaEvento.TipoEvento.FILHO));
        setCasas(new CasaEspecial("Azar: multa inesperada", 250.0, CasaEspecial.TipoEspecial.AZAR));
        setCasas(new CasaFinanceira("Perda em investimento", 500.0, CasaFinanceira.TipoFinanceira.PERDA));
        setCasas(new CasaEvento("Troque de profissão", CasaEvento.TipoEvento.TROCAR_PROFISSAO, new Profissao("Médico")));
        setCasas(new CasaMovimento("Avance 3 casas", CasaMovimento.TipoMovimento.AVANCAR, 3));
        setCasas(new CasaFinanceira("Recebe herança", 5000.0, CasaFinanceira.TipoFinanceira.GANHO));
        setCasas(new CasaEspecial("Jogue novamente", 0.0, CasaEspecial.TipoEspecial.JOGAR_NOVAMENTE));
        setCasas(new CasaEvento("Acidente de carro", CasaEvento.TipoEvento.ACIDENTE_CARRO, 1000.0));
        setCasas(new CasaFinanceira("Pague seguro", 300.0, CasaFinanceira.TipoFinanceira.PERDA));
        setCasas(new CasaVazia("Pausa: respire"));
        setCasas(new CasaEvento("Festa de aniversário", CasaEvento.TipoEvento.ANIVERSARIO, 200.0));
        setCasas(new CasaFinanceira("Venda de propriedade", 10000.0, CasaFinanceira.TipoFinanceira.GANHO));
        setCasas(new CasaMovimento("Volte 2 casas", CasaMovimento.TipoMovimento.VOLTAR, 2));
        setCasas(new CasaEvento("Reavaliação de carreira", CasaEvento.TipoEvento.CARREIRA));
        setCasas(new CasaFinanceira("Pague imposto", 750.0, CasaFinanceira.TipoFinanceira.PERDA));
        setCasas(new CasaEspecial("Sorte: investimento deu certo", 800.0, CasaEspecial.TipoEspecial.SORTE));
        setCasas(new CasaFinanceira("Bônus anual", 400.0, CasaFinanceira.TipoFinanceira.GANHO));
        setCasas(new CasaEvento("Promoção surpresa", CasaEvento.TipoEvento.PROMOCAO, 1000.0));
        setCasas(new CasaMovimento("Avance 1 casa", CasaMovimento.TipoMovimento.AVANCAR, 1));
        setCasas(new CasaFinanceira("Perda no negócio", 600.0, CasaFinanceira.TipoFinanceira.PERDA));
        setCasas(new CasaEvento("Troca de profissão", CasaEvento.TipoEvento.TROCAR_PROFISSAO, new Profissao("Professor")));
        setCasas(new CasaEspecial("Pule uma vez", 0.0, CasaEspecial.TipoEspecial.PULAR_TURNO));
        setCasas(new CasaFinanceira("Recebe prêmio", 1200.0, CasaFinanceira.TipoFinanceira.GANHO));
        setCasas(new CasaEvento("Aposentadoria", CasaEvento.TipoEvento.APOSENTADORIA));
        setCasas(new CasaVazia("Quase lá"));
        setCasas(new CasaFinanceira("Taxa do banco", 350.0, CasaFinanceira.TipoFinanceira.PERDA));
        setCasas(new CasaEspecial("Grande sorte", 3000.0, CasaEspecial.TipoEspecial.SORTE));
        setCasas(new CasaMovimento("Avance 1 casa rumo à aposentadoria", CasaMovimento.TipoMovimento.AVANCAR, 1));
        setCasas(new CasaFinanceira("Bônus final", 2000.0, CasaFinanceira.TipoFinanceira.GANHO));
        setCasas(new CasaEvento("Último evento: comemore", CasaEvento.TipoEvento.ANIVERSARIO, 500.0));
    }
}

