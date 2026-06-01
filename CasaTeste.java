public abstract class Casa {
    private String cor;
    private String instrucao;

    public Casa(String cor, String instrucao) {
        this.cor = cor;
        this.instrucao = instrucao;
    }

    public String getCor() {
        return this.cor;
    }

    public String getInstrucao() {
        return this.instrucao;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }

    public abstract void efeito()
}

// CLASSE CASA FINANCEIRA

class CasaFinanceira extends Casa {
    private double valor;

    public CasaFinanceira(String cor, String instrucao, double valor) {
        super(cor, instrucao);
        this.valor = valor;
    }

    public void configurarInstrucao(String instrucao) {
        this.setInstrucao(instrucao);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public

}

// CLASSE CASA EVENTO

class CasaEvento extends Casa {
    private String evento;
    private double bonificacao;

    public CasaEvento(String cor, String instrucao, String evento) {
        super(cor, instrucao);
        this.evento = evento;
    }

    public String getCor() {
        return this.getCor();
    }

    public String getInstrucao() {
        return this.getInstrucao();
    }

    public void configurarInstrucao(String instrucao) {
        this.setInstrucao(instrucao);
    }

    public double getBonificacao() {
        return this.bonificacao;
    }

    public void setBonificacao(double valor) {
        this.bonificacao = valor;
    }

    public void efeitoEvento(Jogador jogador) {
        if(evento.equals("Casamento")) {
            jogador.setCasamento(true);
        }
        else if(evento.equals("Ter um filho")) {
            jogador.setFilhos(jogador.getFilhos() + 1);
        }
        else if(evento.equals("Ter gemeos")) {
            jogador.setFilhos(jogador.getFilhos() + 2);
        }
        else if(evento.equals("Aniversario")) {
            jogador.setPatrimonio(jogador.getPatrimonio() + bonificacao);
        }
        else if(evento.equals("Formatura")) {
            
        }

    }
}

// CLASSE CASA ESCOLHA

class CasaEscolha extends Casa {
    String opcao;

    public CasaEscolha(String cor, String instrucao) {
        super(cor, instrucao);
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public String getOpcao() {
        return this.opcao;
    }

    public void configurarInstrucao(String instrucao) {
        this.setInstrucao(instrucao);
    }

    public void mostrarOpcoes() {
        System.out.println(opcao);
    }

}

// CLASSE CASA MOVIMENTO

class CasaMovimento extends Casa {
    private int passos;

    public CasaMovimento(String cor, String instrucao) {
        super(cor, instrucao);
    }

    public void configurarInstrucao(String instrucao) {
        this.setInstrucao(instrucao);
    }
}

// CLASSE CASA ESPECIAL

class CasaEspecial extends Casa {
    public CasaEspecial(String cor, String instrucao) {
        super(cor, instrucao);
    }

    public void configurarInstrucao(String instrucao) {
        this.setInstrucao(instrucao);
    }
}

// CLASSE CASA FIM

class CasaFim extends Casa {
    public CasaFim(String cor, String instrucao) {
        super(cor, instrucao);
    }

    public void configurarInstrucao(String instrucao) {
        this.setInstrucao(instrucao);
    }
}
