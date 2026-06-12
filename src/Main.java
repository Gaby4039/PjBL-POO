import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main {

    public static ArrayList<Jogador> jogadores = new ArrayList<>();
    public static ArrayList<Casa> tabuleiro = new ArrayList<>();
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
        tabuleiro.clear();
        tabuleiro.add(new CasaVazia("Início"));
        tabuleiro.add(new CasaFinanceira("Primeiro salário! Recebeu R$2000.", 2000, CasaFinanceira.TipoFinanceira.GANHO));
        tabuleiro.add(new CasaFinanceira("Pagou IPVA. Perdeu R$1000.", 1000, CasaFinanceira.TipoFinanceira.PERDA));
        tabuleiro.add(new CasaEspecial("Energia extra! Jogue novamente.", 0, CasaEspecial.TipoEspecial.JOGAR_NOVAMENTE));
        tabuleiro.add(new CasaEvento("Casamento! A vida a dois começou.", CasaEvento.TipoEvento.CASAMENTO, 0, null));
        tabuleiro.add(new CasaMovimento("Pegou um atalho! Avance 2 casas.", CasaMovimento.TipoMovimento.AVANCAR, 2));
        tabuleiro.add(new CasaVazia("Dia tranquilo. Nada aconteceu."));
        tabuleiro.add(new CasaEvento("Acidente de carro! Pague R$1500.", CasaEvento.TipoEvento.ACIDENTE_CARRO, 1500, null));
        tabuleiro.add(new CasaFinanceira("Restituição do IRPF! Ganhou R$1500.", 1500, CasaFinanceira.TipoFinanceira.GANHO));
        tabuleiro.add(new CasaEspecial("Doente. Pule 1 turno.", 0, CasaEspecial.TipoEspecial.PULAR_TURNO));
        tabuleiro.add(new CasaEvento("Nasceu um filho!", CasaEvento.TipoEvento.FILHO, 0, null));
        tabuleiro.add(new CasaMovimento("Pegou trânsito. Volte 2 casas.", CasaMovimento.TipoMovimento.VOLTAR, 2));
        tabuleiro.add(new CasaVazia("Férias merecidas. Aproveite."));
        tabuleiro.add(new CasaFinanceira("Conserto do telhado. Pague R$2500.", 2500, CasaFinanceira.TipoFinanceira.PERDA));
        tabuleiro.add(new CasaEvento("Promoção! Novo salário: R$8000.", CasaEvento.TipoEvento.PROMOCAO, 8000, null));
        tabuleiro.add(new CasaEspecial("Dia de Azar. Perdeu R$3000.", 3000, CasaEspecial.TipoEspecial.AZAR));
        tabuleiro.add(new CasaFinanceira("Rendimento de ações! Ganhou R$4000.", 4000, CasaFinanceira.TipoFinanceira.GANHO));
        tabuleiro.add(new CasaEvento("Aniversário! Ganhou R$500.", CasaEvento.TipoEvento.ANIVERSARIO, 500, null));
        tabuleiro.add(new CasaVazia("Fim de semana relaxante."));
        tabuleiro.add(new CasaEvento("Crise de carreira! Virou Professor.", CasaEvento.TipoEvento.TROCAR_PROFISSAO, 0, new Profissao("Professor")));
        tabuleiro.add(new CasaMovimento("Bolsa de estudos! Avance 3 casas.", CasaMovimento.TipoMovimento.AVANCAR, 3));
        tabuleiro.add(new CasaFinanceira("Viagem internacional. Pague R$4000.", 4000, CasaFinanceira.TipoFinanceira.PERDA));
        tabuleiro.add(new CasaEspecial("Dia de Sorte! Achou R$5000.", 5000, CasaEspecial.TipoEspecial.SORTE));
        tabuleiro.add(new CasaVazia("Rotina de trabalho normal."));
        tabuleiro.add(new CasaEvento("Nasceram gêmeos! (+1 filho).", CasaEvento.TipoEvento.FILHO, 0, null));
        tabuleiro.add(new CasaFinanceira("Vendeu um projeto freelancer! Ganhou R$3500.", 3500, CasaFinanceira.TipoFinanceira.GANHO));
        tabuleiro.add(new CasaEspecial("Exaustão. Pule 1 turno.", 0, CasaEspecial.TipoEspecial.PULAR_TURNO));
        tabuleiro.add(new CasaMovimento("Esqueceu documento importante. Volte 3 casas.", CasaMovimento.TipoMovimento.VOLTAR, 3));
        tabuleiro.add(new CasaEvento("Acidente! Conserto do carro: R$2000.", CasaEvento.TipoEvento.ACIDENTE_CARRO, 2000, null));
        tabuleiro.add(new CasaVazia("Domingo de preguiça."));
        tabuleiro.add(new CasaFinanceira("Golpe na internet. Perdeu R$1500.", 1500, CasaFinanceira.TipoFinanceira.PERDA));
        tabuleiro.add(new CasaEspecial("Herança inesperada! Ganhou R$10000.", 10000, CasaEspecial.TipoEspecial.SORTE));
        tabuleiro.add(new CasaEvento("Festão de Aniversário! Ganhou R$1000.", CasaEvento.TipoEvento.ANIVERSARIO, 1000, null));
        tabuleiro.add(new CasaMovimento("Pista livre! Avance 2 casas.", CasaMovimento.TipoMovimento.AVANCAR, 2));
        tabuleiro.add(new CasaFinanceira("Loteria de fim de ano! R$8000.", 8000, CasaFinanceira.TipoFinanceira.GANHO));
        tabuleiro.add(new CasaVazia("Preparando para o futuro..."));
        tabuleiro.add(new CasaEspecial("Processo judicial longo. Pague R$5000.", 5000, CasaEspecial.TipoEspecial.AZAR));
        tabuleiro.add(new CasaEvento("Promoção de diretoria! Salário: R$15000.", CasaEvento.TipoEvento.PROMOCAO, 15000, null));
        tabuleiro.add(new CasaFinanceira("Festa de gala extravagante. Pague R$3000.", 3000, CasaFinanceira.TipoFinanceira.PERDA));
        tabuleiro.add(new CasaEvento("Chegou ao Fim: Aposentadoria!", CasaEvento.TipoEvento.APOSENTADORIA, 0, null));
    }

    public static void desenharTabuleiro(Graphics g, int larguraPainel, int alturaPainel) {
        if (tabuleiro.isEmpty()) return;
        int cols = 10;
        int rows = (int) Math.ceil((double) tabuleiro.size() / cols);
        int cW = (larguraPainel  - 10) / cols;
        int cH = (alturaPainel - 10) / rows;

        for (int i = 0; i < tabuleiro.size(); i++) {
            Casa casa = tabuleiro.get(i);
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
        if (jogadores.isEmpty() || tabuleiro.isEmpty()) return;

        JogadorBox:
        {
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

            int novaCasa = Math.min(atual.getCasas() + dado, tabuleiro.size() - 1);
            atual.setCasas(novaCasa);

            System.out.println(">>> " + atual.getNome() + " estava na Casa " + (casaAnterior + 1) +
                    ". Tirou " + dado + " e caiu na Casa " + (novaCasa + 1));

            Casa casa = tabuleiro.get(novaCasa);
            casa.aplicar(atual);

            String mensagemExtra = "";
            if (atual.getCasas() != novaCasa) {
                atual.setCasas(Math.min(atual.getCasas(), tabuleiro.size() - 1));
                mensagemExtra = "\n\nEfeito da casa ativado! Você foi movido para a Casa " + (atual.getCasas() + 1) + ".";
                System.out.println(">>> EFEITO APLICADO! " + atual.getNome() +
                        " foi arremessado para a Casa " + (atual.getCasas() + 1));
            }

            JOptionPane.showMessageDialog(null,
                    atual.getNome() + " tirou " + dado + " na roleta." +
                            "\nCaiu na Casa " + (novaCasa + 1) + ": " + casa.getInstrucao() + mensagemExtra,
                    "Resultado da Jogada", JOptionPane.INFORMATION_MESSAGE);

            if (jogoPainel != null) jogoPainel.atualizarPainelDireito();

            if (atual.getCasas() >= tabuleiro.size() - 1) {
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
    }

    public static void salvarJogo() {
        try {
            Jogo jogo = new Jogo(jogadores, tabuleiro, rodada);
            Persistencia p = new Persistencia();
            p.salvar(jogo, "jogo.dat");
            JOptionPane.showMessageDialog(null, "Jogo saved com sucesso em 'jogo.dat'!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void carregarJogo() {
        try {
            Persistencia p = new Persistencia();
            Jogo jogo = p.carregar("jogo.dat");

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