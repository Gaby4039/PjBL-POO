import java.io.Serializable;

public class Banco implements Serializable {
    private double saldoBanco;

    public Banco () {
        this.saldoBanco = 1000000000;
    }

    public void pagar(Jogador jogador, double valor) {
        if (saldoBanco >= valor) {
            jogador.ganharDinheiro(valor);
            saldoBanco -= valor;
            System.out.println(jogador.getNome() + " recebeu R$: " + valor);
        }
    }

    public void cobrar(Jogador jogador, double valor) {
        if (jogador.getPatrimonio() >= valor) {
            jogador.perderDinheiro(valor);
            saldoBanco += valor;
            System.out.println(jogador.getNome() + " pagou R$: " + valor);
        } else {
            double saldoRestante = jogador.getPatrimonio();
            jogador.perderDinheiro(saldoRestante);
            saldoBanco += saldoRestante;
            System.out.println(jogador.getNome() + " não tinha saldo suficiente! Pagou apenas R$: " + saldoRestante);
        }
    }

    public void emprestimo(Jogador jogador, double valor) {
        pagar(jogador, valor);
        System.out.println("Banco concedeu empréstimo.");
    }

    public double getSaldoBanco() {
        return saldoBanco;
    }
}