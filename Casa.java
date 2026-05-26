public abstract class Casa {
    private String cor;
    private String instrucao;

    public Casa(String cor, String instrucao) {
        this.cor = cor;
        this.instrucao = instrucao;
    }
    
    public abstract void setInstrucao(String instrucao);
}

class CasaSalario extends Casa {
    public CasaSalario(String cor, String instrucao) {
        super(cor, instrucao);
    }

    @Override
    public void setInstrucao(String instrucao) {
        
    } 
}

class CasaImposto extends Casa {
    public CasaImposto(String cor, String instrucao) {
        super(cor, instrucao);
    }

    @Override
    public void setInstrucao(String instrucao) {

    }
}

class CasaSorte extends Casa {
    public CasaSorte(String cor, String instrucao) {
        super(cor, instrucao);
    }

    @Override
    public void setInstrucao(String instrucao) {

    }
}

class CasaAzar extends Casa {
    public CasaAzar(String cor, String instrucao) {
        super(cor, instrucao);
    }

    @Override
    public void setInstrucao(String instrucao) {

    }
}

// Classes mais genéricas herdando da base

// CasaFamilia
// CasaEvento
// CasaProfissao

// Classes mais específicas 

class CasaDivida {

}

class CasaProfissao {

}

class CasaFaculdade {

}

class CasaPromocao {

}

class casaDemissao {

}

class CasaAumento {

}

class CasaCasamento {

}

class CasaFilho {

}

class CasaGemeos {

}

class CasaDivorcio {

}

class CasaAniversario {

}

class CasaVoltar {

}

class CasaAvancar {

}

class CasaPerdeTurno {

}

class CasaJogueNovamente {
    
}

class CasaCompraPropriedade {

}

class CasaVendePropriedade {

}

class CasaAposentadoria {

}

class CasaFerias {

}

class CasaHospital {

}