import java.io.Serializable;

public class Banco implements Serializable {
    private double saldoBanco;

    public Banco (){
        this.saldoBanco = 1000000000;
    }
    public void pagar(Jogador jogador, double valor){

        if(saldoBanco >= valor){
            jogador.setPatrimonio(jogador.getPatrimonio() + valor);
            saldoBanco -= valor;

            System.out.println(jogador.getNome() + "Recebeu R$: " + valor);
        }
    }
    public void cobrar (Jogador jogador, double valor){
        if (jogador.getPatrimonio() >= valor){
            jogador.setPatrimonio(jogador.getPatrimonio() - valor);
            saldoBanco += valor;
        System.out.println(jogador.getNome() + "Pagou R$: " + valor);
        }
    }

    public void emprestimo(Jogador jogador, double valor){
        pagar(jogador, valor);
        System.out.println("Banco concedeu empréstimo.");
    }

    public double getSaldoBanco(){
        return saldoBanco;
    }
}

