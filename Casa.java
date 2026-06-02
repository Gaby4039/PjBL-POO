public class Casa {

   public enum TipoCasa {
        FINANCEIRA,
        EVENTO,
        MOVIMENTO,
        ESCOLHA,
        ESPECIAL,
        INICIO,
    }

    public enum TipoEvento {
        CASAMENTO,
        FILHO,
        ANIVERSARIO,
        PROMOCAO,
        ACIDENTE_CARRO,
        TROCAR_PROFISSAO,
    }

    public enum TipoFinanceira {
        GANHO,
        PERDA
    }

    public enum TipoMovimento {
        AVANÇAR,
        VOLTAR
    }

    private String cor;
    private TipoCasa tipo;
    private TipoEvento evento;
    private TipoFinanceira financeira;
    private TipoMovimento movimento;
    private String instrucao;
    private int valor;

    public Casa(String cor, TipoCasa tipo, TipoEvento evento, TipoFinanceira financeira, String instrucao, int valor) {
        this.cor = cor;
        this.tipo = tipo;
        this.instrucao = instrucao;
        this.evento = evento;
        this.valor = valor;
    }

    public void efeito(Jogador jogador) {
        if(tipo == TipoCasa.FINANCEIRA) {
            if(financeira == TipoFinanceira.GANHO) {
                jogador.ganharDinheiro(valor);
            }
            else if(financeira == TipoFinanceira.PERDA) {
                jogador.perderDinheiro(valor);
            }
        }
        else if(tipo == TipoCasa.EVENTO) {
            if(evento == TipoEvento.CASAMENTO) {
                jogador.casar();
            }
            else if(evento == TipoEvento.FILHO) {
                jogador.terFilho();
            }
            else if(evento == TipoEvento.ANIVERSARIO) {
                jogador.fazer_aniversario(valor);
            }
            else if(evento == TipoEvento.PROMOCAO) {
                jogador.promocao(valor);
            }
            else if(evento == TipoEvento.ACIDENTE_CARRO) {
                if(jogador.getSeguro()) {
                    jogador.ganharDinheiro(valor);
                }
                else {
                    jogador.perderDinheiro(valor);
                }
            }
            else if(evento == TipoEvento.TROCAR_PROFISSAO) {
                jogador.trocarProfissao();
            }
            else {
                jogador.aposentar();
            }
        }
        else if(tipo == TipoCasa.MOVIMENTO) {
            if(movimento == TipoMovimento.AVANÇAR) {
                jogador.avancarCasas(jogador.getCasas());
            }
        }
        else if(tipo == TipoCasa.ESCOLHA) {
            
        }
        else if(tipo == TipoCasa.ESPECIAL) {

        }
        else if(tipo == TipoCasa.INICIO) {

        }
        else {
            
        }
    }
    
   

}