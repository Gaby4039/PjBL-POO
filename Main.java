import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main {

    static ArrayList<Jogador> jogadores = new ArrayList<>();
    static ArrayList<CasaEnum> tabuleiro = new ArrayList<>();
    static Roleta roleta = new Roleta();
    static Rodada rodada = new Rodada();

    static JLabel labelStatus;
    static JPanel painelTabuleiro;
    static JPanel painelDireito;

    public static void main(String[] args) {
        montarTabuleiro();

        Profissao dev = new Profissao("Desenvolvedor");
        Profissao med = new Profissao("Medico");
        jogadores.add(new Jogador("Azul",     "Alice", false, dev, 0, dev.getSalario(), 10000, false, false, 0));
        jogadores.add(new Jogador("Vermelho", "Bob",   false, med, 0, med.getSalario(), 10000, false, false, 0));

        criarJanela();
    }

    // ordem correta: cor, tipo, evento, financeira, movimento, especial, instrucao, opcao1, opcao2, valor
    static void montarTabuleiro() {
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.INICIO,     null,                            null,                      null,                       null,                         "INICIO",                 null, null, 0));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.FINANCEIRA, null,                            CasaEnum.TipoFinanceira.GANHO, null,                       null,                         "Bonus +2000",            null, null, 2000));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.MOVIMENTO,  null,                            null,                      CasaEnum.TipoMovimento.AVANCAR, null,                         "Avance 2",               null, null, 2));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.EVENTO,     CasaEnum.TipoEvento.CASAMENTO,       null,                      null,                       null,                         "Casamento!",             null, null, 0));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.FINANCEIRA, null,                            CasaEnum.TipoFinanceira.PERDA, null,                       null,                         "Imposto -1500",          null, null, 1500));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.MOVIMENTO,  null,                            null,                      CasaEnum.TipoMovimento.VOLTAR,  null,                         "Volte 1",                null, null, 1));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.EVENTO,     CasaEnum.TipoEvento.FILHO,           null,                      null,                       null,                         "Filho +1",               null, null, 0));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.ESPECIAL,   null,                            null,                      null,                       CasaEnum.TipoEspecial.SORTE,      "Sorte +3000",            null, null, 3000));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.FINANCEIRA, null,                            CasaEnum.TipoFinanceira.GANHO, null,                       null,                         "Investimento +1000",     null, null, 1000));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.ESPECIAL,   null,                            null,                      null,                       CasaEnum.TipoEspecial.PULAR_TURNO,"Pule o turno",           null, null, 0));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.EVENTO,     CasaEnum.TipoEvento.ANIVERSARIO,     null,                      null,                       null,                         "Aniversario +500",       null, null, 500));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.EVENTO,     CasaEnum.TipoEvento.PROMOCAO,        null,                      null,                       null,                         "Promocao sal.8000",      null, null, 8000));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.FINANCEIRA, null,                            CasaEnum.TipoFinanceira.PERDA, null,                       null,                         "Reforma -2500",          null, null, 2500));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.EVENTO,     CasaEnum.TipoEvento.ACIDENTE_CARRO,  null,                      null,                       null,                         "Acidente -3000",         null, null, 3000));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.MOVIMENTO,  null,                            null,                      CasaEnum.TipoMovimento.AVANCAR, null,                         "Atalho avance 3",        null, null, 3));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.ESPECIAL,   null,                            null,                      null,                       CasaEnum.TipoEspecial.AZAR,       "Azar -1000",             null, null, 1000));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.EVENTO,     CasaEnum.TipoEvento.FILHO,           null,                      null,                       null,                         "Gemeos filho +1",        null, null, 0));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.FINANCEIRA, null,                            CasaEnum.TipoFinanceira.GANHO, null,                       null,                         "Loteria +5000",          null, null, 5000));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.MOVIMENTO,  null,                            null,                      CasaEnum.TipoMovimento.VOLTAR,  null,                         "Carro quebrou volte 2",  null, null, 2));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.FINANCEIRA, null,                            CasaEnum.TipoFinanceira.PERDA, null,                       null,                         "Multa -800",             null, null, 800));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.ESPECIAL,   null,                            null,                      null,                       CasaEnum.TipoEspecial.SORTE,      "Sorte +2000",            null, null, 2000));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.EVENTO,     CasaEnum.TipoEvento.ANIVERSARIO,     null,                      null,                       null,                         "Festa +300",             null, null, 300));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.MOVIMENTO,  null,                            null,                      CasaEnum.TipoMovimento.VOLTAR,  null,                         "Engarrafamento volte 1", null, null, 1));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.FINANCEIRA, null,                            CasaEnum.TipoFinanceira.GANHO, null,                       null,                         "Freelance +1500",        null, null, 1500));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.ESPECIAL,   null,                            null,                      null,                       CasaEnum.TipoEspecial.PULAR_TURNO,"Viagem pule turno",      null, null, 0));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.EVENTO,     CasaEnum.TipoEvento.FILHO,           null,                      null,                       null,                         "Mais um filho!",         null, null, 0));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.FINANCEIRA, null,                            CasaEnum.TipoFinanceira.PERDA, null,                       null,                         "Dentista -600",          null, null, 600));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.MOVIMENTO,  null,                            null,                      CasaEnum.TipoMovimento.AVANCAR, null,                         "Atalho avance 2",        null, null, 2));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.ESPECIAL,   null,                            null,                      null,                       CasaEnum.TipoEspecial.AZAR,       "Celular quebrou -1200",  null, null, 1200));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.FINANCEIRA, null,                            CasaEnum.TipoFinanceira.GANHO, null,                       null,                         "Dividendos +2500",       null, null, 2500));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.EVENTO,     CasaEnum.TipoEvento.PROMOCAO,        null,                      null,                       null,                         "Socio sal.12000",        null, null, 12000));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.ESPECIAL,   null,                            null,                      null,                       CasaEnum.TipoEspecial.SORTE,      "Achou dinheiro +500",    null, null, 500));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.FINANCEIRA, null,                            CasaEnum.TipoFinanceira.PERDA, null,                       null,                         "IPTU -1800",             null, null, 1800));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.MOVIMENTO,  null,                            null,                      CasaEnum.TipoMovimento.VOLTAR,  null,                         "Volte 3",                null, null, 3));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.FINANCEIRA, null,                            CasaEnum.TipoFinanceira.GANHO, null,                       null,                         "Bonus fim de ano +4000", null, null, 4000));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.ESPECIAL,   null,                            null,                      null,                       CasaEnum.TipoEspecial.AZAR,       "Cano estourou -900",     null, null, 900));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.EVENTO,     CasaEnum.TipoEvento.ANIVERSARIO,     null,                      null,                       null,                         "Presente +1000",         null, null, 1000));
        tabuleiro.add(new CasaEnum("", CasaEnum.TipoCasa.EVENTO,     CasaEnum.TipoEvento.APOSENTADORIA,   null,                      null,                       null,                         "APOSENTADORIA - FIM!",   null, null, 0));
    }

    static void criarJanela() {
        JFrame janela = new JFrame("Jogo da Vida");
        janela.setSize(900, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        painelTabuleiro = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharTabuleiro(g);
            }
        };
        painelTabuleiro.setBackground(Color.WHITE);

        painelDireito = new JPanel();
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        painelDireito.setPreferredSize(new Dimension(220, 0));
        painelDireito.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 8));

        painelDireito.add(new JLabel("Jogadores:"));
        painelDireito.add(Box.createVerticalStrut(5));
        for (Jogador j : jogadores) {
            JLabel lj = new JLabel(j.getNome() + " - R$" + (int) j.getPatrimonio());
            lj.setName(j.getNome());
            painelDireito.add(lj);
        }



        JPanel painelBaixo = new JPanel(new FlowLayout());
        labelStatus = new JLabel("Vez de: " + jogadores.get(0).getNome());
        JButton btnGirar = new JButton("Girar Roleta");
        btnGirar.addActionListener(e -> jogar());
        painelBaixo.add(labelStatus);
        painelBaixo.add(btnGirar);

        janela.add(painelTabuleiro, BorderLayout.CENTER);
        janela.add(painelDireito,   BorderLayout.EAST);
        janela.add(painelBaixo,     BorderLayout.SOUTH);
        janela.setVisible(true);
    }

    static void desenharTabuleiro(Graphics g) {
        int cols = 10;
        int rows = (int) Math.ceil((double) tabuleiro.size() / cols);
        int cW = (painelTabuleiro.getWidth()  - 10) / cols;
        int cH = (painelTabuleiro.getHeight() - 10) / rows;

        for (int i = 0; i < tabuleiro.size(); i++) {
            CasaEnum casa = tabuleiro.get(i);
            int row = i / cols;
            int col = (row % 2 == 0) ? (i % cols) : (cols - 1 - (i % cols));
            int x = 5 + col * cW;
            int y = 5 + row * cH;

            g.setColor(corDaCasa(casa));
            g.fillRect(x, y, cW - 1, cH - 1);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(x, y, cW - 1, cH - 1);

            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 9));
            g.drawString(String.valueOf(i + 1), x + 2, y + 10);

            String instrucao = casa.getInstrucao();
            if (instrucao != null) {
                g.setFont(new Font("SansSerif", Font.PLAIN, 8));
                String[] palavras = instrucao.split(" ");
                int linhaY = y + 22;
                String linha = "";
                for (String p : palavras) {
                    if ((linha + p).length() > 10) {
                        g.drawString(linha.trim(), x + 2, linhaY);
                        linhaY += 10;
                        linha = p + " ";
                    } else {
                        linha += p + " ";
                    }
                }
                if (!linha.trim().isEmpty()) {
                    g.drawString(linha.trim(), x + 2, linhaY);
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

    static Color corDaCasa(CasaEnum c) {
        if (c.getTipo() == CasaEnum.TipoCasa.INICIO) return new Color(255, 215, 0);
        if (c.getTipo() == CasaEnum.TipoCasa.FINANCEIRA) {
            if (c.getFinanceira() == CasaEnum.TipoFinanceira.GANHO) return new Color(180, 230, 180);
            else return new Color(230, 180, 180);
        }
        if (c.getTipo() == CasaEnum.TipoCasa.MOVIMENTO) {
            if (c.getMovimento() == CasaEnum.TipoMovimento.AVANCAR) return new Color(180, 210, 240);
            else return new Color(240, 200, 150);
        }
        if (c.getTipo() == CasaEnum.TipoCasa.EVENTO)   return new Color(255, 240, 150);
        if (c.getTipo() == CasaEnum.TipoCasa.ESPECIAL) {
            if (c.getEspecial() == CasaEnum.TipoEspecial.SORTE)       return new Color(180, 230, 180);
            if (c.getEspecial() == CasaEnum.TipoEspecial.AZAR)        return new Color(230, 180, 180);
            if (c.getEspecial() == CasaEnum.TipoEspecial.PULAR_TURNO) return new Color(210, 210, 210);
        }
        return Color.LIGHT_GRAY;
    }

    static void jogar() {
        Jogador atual = jogadores.get(rodada.getJogadorAtual());
        int dado = roleta.girar();

        int novaCasa = Math.min(atual.getCasas() + dado, tabuleiro.size() - 1);
        atual.setCasas(novaCasa);

        CasaEnum casa = tabuleiro.get(novaCasa);
        casa.aplicar(atual);

        JOptionPane.showMessageDialog(null,
                atual.getNome() + " tirou " + dado + "\n" + casa.getInstrucao(),
                "Casa " + (novaCasa + 1), JOptionPane.INFORMATION_MESSAGE);

        // Atualiza labels dos jogadores
        for (Component comp : painelDireito.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel lj = (JLabel) comp;
                for (Jogador j : jogadores) {
                    if (j.getNome().equals(lj.getName())) {
                        lj.setText(j.getNome() + " - R$" + (int) j.getPatrimonio());
                    }
                }
            }
        }

        if (atual.getCasas() >= tabuleiro.size() - 1) {
            JOptionPane.showMessageDialog(null,
                    atual.getNome() + " venceu!\nPatrimonio final: R$" + (int) atual.getPatrimonio(),
                    "Fim de Jogo!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        rodada.proximoTurno(jogadores);
        Jogador proximo = jogadores.get(rodada.getJogadorAtual());
        labelStatus.setText("Rodada " + rodada.getNumeroRodada() + " - Vez de: " + proximo.getNome());

        painelDireito.revalidate();
        painelDireito.repaint();
        painelTabuleiro.repaint();
    }
}