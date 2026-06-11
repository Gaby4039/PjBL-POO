public abstract class Casa implements Efeito {
    private String instrucao;

    public Casa(String instrucao) {
        this.instrucao = instrucao;
    }

    public String getInstrucao() {
        return instrucao;
    }
}

class CasaFinanceira extends Casa {
    public enum TipoFinanceira {
        GANHO,
        PERDA
    }

    private double valor;
    private TipoFinanceira tipoFinanceira;

    public CasaFinanceira(String instrucao, double valor, TipoFinanceira tipoFinanceira) {
        super(instrucao);
        this.valor = valor;
        this.tipoFinanceira = tipoFinanceira;
    }

    public TipoFinanceira getTipoFinanceira() {
        return tipoFinanceira;
    }

    @Override
    public void aplicar(Jogador jogador) {
        switch (getTipoFinanceira()) {
            case GANHO:
                jogador.ganharDinheiro(valor);
                break;

            case PERDA:
                jogador.perderDinheiro(valor);
                break;
        }
    }
}

class CasaEvento extends Casa {
    public enum TipoEvento {
        CASAMENTO,
        FILHO,
        ANIVERSARIO,
        PROMOCAO,
        ACIDENTE_CARRO,
        TROCAR_PROFISSAO,
        APOSENTADORIA
    }

    private TipoEvento tipoEvento;
    private double valor;
    private Profissao novaProfissao;

    public CasaEvento(String instrucao, TipoEvento tipoEvento, double valor, Profissao novaProfissao) {
        super(instrucao);
        this.tipoEvento = tipoEvento;
        this.valor = valor;
        this.novaProfissao = novaProfissao;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public double getValor() {
        return valor;
    }

    public Profissao getNovaProfissao() {
        return novaProfissao;
    }

    @Override
    public void aplicar(Jogador jogador) {
        switch (tipoEvento) {
            case CASAMENTO:
                jogador.setCasamento(true);
                break;

            case FILHO:
                jogador.setFilhos(1);
                break;

            case ANIVERSARIO:
                jogador.fazer_aniversario(valor);
                break;

            case PROMOCAO:
                jogador.setSalario(valor);
                break;

            case ACIDENTE_CARRO:
                if (jogador.getSeguro()) {
                    jogador.ganharDinheiro(valor);
                } else {
                    jogador.perderDinheiro(valor);
                }
                break;

            case TROCAR_PROFISSAO:
                jogador.trocarProfissao(novaProfissao);
                break;

            case APOSENTADORIA:
                jogador.aposentar();
                break;
        }
    }
}

class CasaMovimento extends Casa {
    public enum TipoMovimento {
        AVANCAR,
        VOLTAR
    }

    private int casas;
    private TipoMovimento tipoMovimento;

    public CasaMovimento(String instrucao, TipoMovimento tipomovimento, int casas) {
        super(instrucao);
        this.casas = casas;
        this.tipoMovimento = tipomovimento;
    }

    public int getCasas() {
        return casas;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    @Override
    public void aplicar(Jogador jogador) {
        switch (tipoMovimento) {
            case AVANCAR:
                jogador.avancarCasas(casas);
                break;

            case VOLTAR:
                jogador.voltarCasas(casas);
                break;
        }
    }
}

class CasaEspecial extends Casa {
    public enum TipoEspecial {
        SORTE,
        AZAR,
        PULAR_TURNO,
        JOGAR_NOVAMENTE
    }

    private double valor;
    private TipoEspecial tipoEspecial;

    public CasaEspecial(String instrucao, double valor, TipoEspecial tipoEspecial) {
        super(instrucao);
        this.valor = valor;
        this.tipoEspecial = tipoEspecial;
    }

    public double getValor() {
        return valor;
    }

    public TipoEspecial getTipoEspecial() {
        return tipoEspecial;
    }

    @Override
    public void aplicar(Jogador jogador) {
        switch (tipoEspecial) {
            case SORTE:
                jogador.ganharDinheiro(valor);
                break;

            case AZAR:
                jogador.perderDinheiro(valor);
                break;

            case PULAR_TURNO:
                jogador.pularTurno();
                break;

            case JOGAR_NOVAMENTE:
                jogador.jogarNovamente();
                break;
        }
    }
}