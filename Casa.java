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
    
    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }
}

// CLASSE CASA FINANCEIRA

class CasaFinanceira extends Casa {
    public CasaFinanceira(String cor, String instrucao) {
        super(cor, instrucao);
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
}

// CLASSE CASA EVENTO

class CasaEvento extends Casa {
    public CasaEvento(String cor, String instrucao) {
        super(cor, instrucao);
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
}

// CLASSE CASA ESCOLHA

class CasaEscolha extends Casa {
    public CasaEscolha(String cor, String instrucao) {
        super(cor, instrucao);
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
}

// CLASSE CASA MOVIMENTO

class CasaMovimento extends Casa {
    public CasaMovimento(String cor, String instrucao) {
        super(cor, instrucao);
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
}

// CLASSE CASA ESPECIAL

class CasaEspecial extends Casa {
    public CasaEspecial(String cor, String instrucao) {
        super(cor, instrucao);
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
}

// CLASSE CASA FIM

class CasaFim extends Casa {
    public CasaFim(String cor, String instrucao) {
        super(cor, instrucao);
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
}
