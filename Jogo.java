import java.io.Serializable;
import java.util.ArrayList;

public class Jogo implements Serializable {
    private ArrayList<Jogador> jogadores;
    private Tabuleiro tabuleiro;
    private Rodada rodada;

    public Jogo(ArrayList<Jogador> jogadores,
                Tabuleiro tabuleiro,
                Rodada rodada) {
       this.jogadores = jogadores;
       this.tabuleiro = tabuleiro;
       this.rodada = rodada;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public Rodada getRodada() {
        return rodada;
    }
}
