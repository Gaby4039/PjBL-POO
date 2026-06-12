import java.io.Serializable;
import java.util.ArrayList;

public class Jogo implements Serializable {
    private ArrayList<Jogador> jogadores;
    private ArrayList<Casa> tabuleiro;
    private Rodada rodada;

    public Jogo(ArrayList<Jogador> jogadores,
                ArrayList<Casa> tabuleiro,
                Rodada rodada) {
        this.jogadores = jogadores;
        this.tabuleiro = tabuleiro;
        this.rodada = rodada;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public ArrayList<Casa> getTabuleiro() {
        return tabuleiro;
    }

    public Rodada getRodada() {
        return rodada;
    }
}