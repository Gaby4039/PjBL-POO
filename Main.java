import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main {

    public static ArrayList<Jogador> jogadores = new ArrayList<>();
    public static Tabuleiro tabuleiro = new Tabuleiro();
    public static Roleta roleta = new Roleta();
    public static Rodada rodada = new Rodada();
    public static Banco banco = new Banco();

    public static JFrame janela;
    public static JLabel labelStatus;
    public static JogoPainel jogoPainel;

    public static void main(String[] args) {
        janela = new JFrame("Jogo da Vida");
        janela.setSize(900, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);

        mostrarMenuInicial();
    }

    public static void mostrarMenuInicial() {
        janela.setContentPane(new MenuPainel());
        janela.revalidate();
        janela.repaint();
        janela.setVisible(true);
    }

    public static void mostrarConfiguracaoJogadores() {
        janela.setContentPane(new ConfigPainel());
        janela.revalidate();
        janela.repaint();
    }

    public static void criarJanelaJogo() {
        jogoPainel = new JogoPainel();
        janela.setContentPane(jogoPainel);
        janela.revalidate();
        janela.repaint();
    }

    public static void montarTabuleiro() {
        tabuleiro = new Tabuleiro();
        tabuleiro.montarTabuleiro();
    }

    public static void desenharTabuleiro(Graphics g, int larguraPainel, int alturaPainel) {
        if (tabuleiro == null || tabuleiro.getCasas().isEmpty()) return;
        int cols = 10;
        int rows = (int) Math.ceil((double) tabuleiro.getCasas().size() / cols);
        int cW = (larguraPainel  - 10) / cols;
        int cH = (alturaPainel - 10) / rows;

        for (int i = 0; i < tabuleiro.getCasas().size(); i++) {
            Casa casa = tabuleiro.getCasa(i);
            int row = i / cols;
                int col = (row % 2 == 0) ? (i % cols) : (cols - 1 - (i % cols));
            int x = 5 + col * cW;
            int y = 5 + row * cH;

            g.setColor(corDaCasa(casa));
            g.fillRect(x, y, cW - 1, cH - 1);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(x, y, cW - 1, cH - 1);

            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 10));
            g.drawString(String.valueOf(i + 1), x + 2, y + 12);

            String instrucao = casa.getInstrucao();
            if (instrucao != null) {
                g.setFont(new Font("SansSerif", Font.PLAIN, 11));
                FontMetrics fm = g.getFontMetrics();
                String[] palavras = instrucao.split(" ");
                int linhaY = y + 26;
                int espacamentoLinha = 14;
                String linha = "";

                for (String p : palavras) {
                    if (fm.stringWidth(linha + p) > (cW - 8)) {
                        g.drawString(linha.trim(), x + 4, linhaY);
                        linhaY += espacamentoLinha;
                        linha = p + " ";
                    } else {
                        linha += p + " ";
                    }
                }
                if (!linha.trim().isEmpty()) {
                    g.drawString(linha.trim(), x + 4, linhaY);
                }
            }

            Color[] coresJog = {Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA};
            int px = x + 3;
            for (int j = 0; j < jogadores.size(); j++) {
                if (jogadores.get(j).getCasas() == i) {
                    g.setColor(coresJog[j % coresJog.length]);
                    g.fillOval(px, y + cH - 15, 11, 11);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("SansSerif", Font.BOLD, 7));
                    g.drawString(jogadores.get(j).getNome().substring(0, 1), px + 3, y + cH - 6);
                    px += 13;
                }
            }
        }
    }

    public static Color corDaCasa(Casa c) {
        if (c instanceof CasaFinanceira) {
            if (((CasaFinanceira) c).getTipoFinanceira() == CasaFinanceira.TipoFinanceira.GANHO) return new Color(180, 230, 180);
            else return new Color(230, 180, 180);
        }
        if (c instanceof CasaMovimento) {
            if (((CasaMovimento) c).getTipoMovimento() == CasaMovimento.TipoMovimento.AVANCAR) return new Color(180, 210, 240);
            else return new Color(240, 200, 150);
        }
        if (c instanceof CasaEvento) return new Color(255, 240, 150);
        if (c instanceof CasaEspecial) {
            CasaEspecial.TipoEspecial tipo = ((CasaEspecial) c).getTipoEspecial();
            if (tipo == CasaEspecial.TipoEspecial.SORTE)       return new Color(180, 230, 180);
            if (tipo == CasaEspecial.TipoEspecial.AZAR)        return new Color(230, 180, 180);
            if (tipo == CasaEspecial.TipoEspecial.PULAR_TURNO) return new Color(210, 210, 210);
            if (tipo == CasaEspecial.TipoEspecial.JOGAR_NOVAMENTE) return new Color(180, 230, 180);
        }
        return Color.LIGHT_GRAY;
    }

    public static void jogar() {
        if (jogadores.isEmpty() || tabuleiro == null || tabuleiro.getCasas().isEmpty()) return;

        Jogador atual = jogadores.get(rodada.getJogadorAtual());

            if (!atual.isTurnoAtivo()) {
                JOptionPane.showMessageDialog(null,
                        atual.getNome() + " está com o turno pulado e perdeu a vez!",
                        "Turno Pulado", JOptionPane.WARNING_MESSAGE);
                atual.jogarNovamente();
                rodada.proximoTurno(jogadores);
                Jogador proximo = jogadores.get(rodada.getJogadorAtual());
                labelStatus.setText("Rodada " + rodada.getNumeroRodada() + " - Vez de: " + proximo.getNome());
                return;
            }

            int casaAnterior = atual.getCasas();
            int dado = roleta.girar();

            int novaCasa = Math.min(atual.getCasas() + dado, tabuleiro.getCasas().size() - 1);
            atual.setCasas(novaCasa);

            System.out.println(">>> " + atual.getNome() + " estava na Casa " + (casaAnterior + 1) +
                    ". Tirou " + dado + " e caiu na Casa " + (novaCasa + 1));

            Casa casa = tabuleiro.getCasa(novaCasa);
            casa.aplicar(atual);

            String mensagemExtra = "";
            if (atual.getCasas() != novaCasa) {
                atual.setCasas(Math.min(atual.getCasas(), tabuleiro.getCasas().size() - 1));
                mensagemExtra = "\n\nEfeito da casa ativado! Você foi movido para a Casa " + (atual.getCasas() + 1) + ".";
                System.out.println(">>> EFEITO APLICADO! " + atual.getNome() +
                        " foi arremessado para a Casa " + (atual.getCasas() + 1));
            }

            JOptionPane.showMessageDialog(null,
                    atual.getNome() + " tirou " + dado + " na roleta." +
                            "\nCaiu na Casa " + (novaCasa + 1) + ": " + casa.getInstrucao() + mensagemExtra,
                    "Resultado da Jogada", JOptionPane.INFORMATION_MESSAGE);

            if (jogoPainel != null) jogoPainel.atualizarPainelDireito();

            if (atual.getCasas() >= tabuleiro.getCasas().size() - 1) {
                JOptionPane.showMessageDialog(null,
                        atual.getNome() + " venceu!\nPatrimônio final: R$" + (int) atual.getPatrimonio(),
                        "Fim de Jogo!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            rodada.proximoTurno(jogadores);
            Jogador proximo = jogadores.get(rodada.getJogadorAtual());
            labelStatus.setText("Rodada " + rodada.getNumeroRodada() + " - Vez de: " + proximo.getNome());

            if (jogoPainel != null) jogoPainel.repintarTabuleiro();
    }

    public static void salvarJogo() {
        try {
            Jogo jogo = new Jogo(jogadores, tabuleiro, rodada);
            Persistencia.salvar(jogo, "jogo.dat");
            JOptionPane.showMessageDialog(null, "Jogo saved com sucesso em 'jogo.dat'!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void carregarJogo() {
        try {
            Jogo jogo = Persistencia.carregar("jogo.dat");

            jogadores = jogo.getJogadores();
            tabuleiro = jogo.getTabuleiro();
            rodada = jogo.getRodada();

            criarJanelaJogo();

            Jogador proximo = jogadores.get(rodada.getJogadorAtual());
            labelStatus.setText("Rodada " + rodada.getNumeroRodada() + " - Vez de: " + proximo.getNome());

            if (jogoPainel != null) {
                jogoPainel.atualizarPainelDireito();
                jogoPainel.repintarTabuleiro();
            }

            JOptionPane.showMessageDialog(null, "Jogo carregado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
