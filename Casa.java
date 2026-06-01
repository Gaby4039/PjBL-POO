public class Casa {

   public enum TipoCasa {
        FINANCEIRA,
        EVENTO,
        MOVIMENTO,
        ESCOLHA,
        ESPECIAL,
        INICIO,
        FIM
    }

    private String cor;
    private TipoCasa tipo;
    private String instrucao;
    private String evento;
    private int valor;

    public Casa(String cor, TipoCasa tipo, String instrucao, String evento, int valor) {
        this.cor = cor;
        this.tipo = tipo;
        this.instrucao = instrucao;
        this.evento = evento;
        this.valor = valor;
    }

    public void efeito(Jogador jogador) {
        if(tipo == TipoCasa.FINANCEIRA) {
            
        }
    }
    
   

}