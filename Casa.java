public class Casa implements Efeito {

    public enum TipoCasa {
        FINANCEIRA,
        EVENTO,
        MOVIMENTO,
        ESCOLHA,
        ESPECIAL,
        INICIO
    }

    public enum TipoEvento {
        CASAMENTO,
        FILHO,
        ANIVERSARIO,
        PROMOCAO,
        ACIDENTE_CARRO,
        TROCAR_PROFISSAO,
        APOSENTADORIA
    }

    public enum TipoFinanceira {
        GANHO,
        PERDA
    }

    public enum TipoMovimento {
        AVANCAR,
        VOLTAR
    }

    public enum TipoEspecial {
        SORTE,
        AZAR,
        PULAR_TURNO,
        JOGAR_NOVAMENTE
    }

    private String cor;
    private TipoCasa tipo;
    private TipoEvento evento;
    private TipoFinanceira financeira;
    private TipoMovimento movimento;
    private TipoEspecial especial;
    private String instrucao;
    private String opcao1;
    private String opcao2;
    private int valor;

    public Casa(String cor,
                TipoCasa tipo,
                TipoEvento evento,
                TipoFinanceira financeira,
                TipoMovimento movimento,
                TipoEspecial especial,
                String instrucao,
                String opcao1,
                String opcao2,
                int valor) {
        this.cor = cor;
        this.tipo = tipo;
        this.evento = evento;
        this.financeira = financeira;
        this.movimento = movimento;
        this.especial = especial;
        this.opcao1 = opcao1;
        this.opcao2 = opcao2;
        this.instrucao = instrucao;
        this.valor = valor;
    }

    public String getCor() {
        return cor;
    }

    public TipoCasa getTipo() {
        return tipo;
    }

    public TipoEvento getEvento() {
        return evento;
    }

    public TipoFinanceira getFinanceira() {
        return financeira;
    }

    public TipoMovimento getMovimento() {
        return movimento;
    }

    public TipoEspecial getEspecial() {
        return especial;
    }

    public String getInstrucao() {
        return instrucao;
    }

    public String getOpcao1() {
        return opcao1;
    }

    public String getOpcao2() {
        return opcao2;
    }

    public int getValor() {
        return valor;
    }

    public void aplicar(Jogador jogador) {
        if (jogador == null) {
            return;
        }

        switch (tipo) {
            case FINANCEIRA:
                if (financeira == TipoFinanceira.GANHO) {
                    jogador.ganharDinheiro(valor);
                } else if (financeira == TipoFinanceira.PERDA) {
                    jogador.perderDinheiro(valor);
                }
                break;
            case EVENTO:
                if (evento == TipoEvento.CASAMENTO) {
                    jogador.casar();
                } else if (evento == TipoEvento.FILHO) {
                    jogador.terFilho();
                } else if (evento == TipoEvento.ANIVERSARIO) {
                    jogador.fazer_aniversario(valor);
                } else if (evento == TipoEvento.PROMOCAO) {
                    jogador.promocao(valor);
                } else if (evento == TipoEvento.ACIDENTE_CARRO) {
                    if (jogador.getSeguro()) {
                        jogador.ganharDinheiro(valor / 2);
                    } else {
                        jogador.perderDinheiro(valor);
                    }
                } else if (evento == TipoEvento.TROCAR_PROFISSAO) {
                    if (jogador.getNovaProfissao() != null) {
                        jogador.trocarProfissao(jogador.getNovaProfissao());
                    }
                } else if (evento == TipoEvento.APOSENTADORIA) {
                    jogador.aposentar();
                }
                break;
            case MOVIMENTO:
                if (movimento == TipoMovimento.AVANCAR) {
                    jogador.avancarCasas(valor);
                } else if (movimento == TipoMovimento.VOLTAR) {
                    jogador.voltarCasas(valor);
                }
                break;
            case ESCOLHA:
                System.out.println(instrucao);
                System.out.println("1 - " + opcao1);
                System.out.println("2 - " + opcao2);
                break;
            case ESPECIAL:
                if (especial == TipoEspecial.SORTE) {
                    jogador.ganharDinheiro(valor);
                } else if (especial == TipoEspecial.AZAR) {
                    jogador.perderDinheiro(valor);
                } else if (especial == TipoEspecial.PULAR_TURNO) {
                    jogador.pularTurno();
                } else if (especial == TipoEspecial.JOGAR_NOVAMENTE) {
                    jogador.jogarNovamente();
                }
                break;
            case INICIO:
                break;
        }
    }

    public void efeito(Jogador jogador) {
        aplicar(jogador);
    }

    @Override
    public String toString() {
        String descricao = tipo + ": " + (instrucao != null ? instrucao : "Casa sem descrição");
        if (valor != 0) {
            descricao += " (Valor: " + valor + ")";
        }
        return descricao;
    }
}
