import java.util.ArrayList;

public class Rodada {
    private int numeroRodada;
    private int jogadorAtual;

    public Rodada(){
        numeroRodada = 1;
        jogadorAtual = 0;
    }
    public void iniciarTurno(ArrayList<Jogador> jogadores) {
        Jogador atual = jogadores.get(jogadorAtual);

        System.out.println("\n === Rodada: " + numeroRodada + " ===");
        System.out.println("Turno de " + atual.getNome());

    }

    public void proximoTurno(ArrayList<Jogador> jogadores){
        jogadorAtual++;
        if (jogadorAtual >= jogadores.size()){
            jogadorAtual = 0;
            numeroRodada++;
        }
    }
    public int getNumeroRodada(){
        return numeroRodada;
    }
    public int getJogadorAtual(){
        return jogadorAtual;
    }
}