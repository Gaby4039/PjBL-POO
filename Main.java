import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main {

    static final Color COR_FUNDO    = new Color(245, 240, 230);
    static final Color COR_AZUL     = new Color(70, 130, 180);
    static final Color COR_VERDE    = new Color(60, 160, 80);
    static final Color COR_VERMELHO = new Color(200, 60, 60);
    static final Color COR_AMARELO  = new Color(255, 200, 50);
    static final Color COR_ROXO     = new Color(130, 80, 180);
    static final Color COR_LARANJA  = new Color(220, 130, 30);
    static final Color COR_TEXTO    = new Color(40, 40, 40);

    static ArrayList<Jogador> jogadores = new ArrayList<>();
    static ArrayList<Casa>    tabuleiro = new ArrayList<>();
    static ArrayList<Carta>   baralho   = new ArrayList<>();
    static Roleta roleta = new Roleta();
    static Rodada rodada = new Rodada();
    static int    indiceCarta = 0;

    static JLabel    labelRodada, labelTurno, labelRoleta;
    static JTextArea areaLog;
    static JPanel    painelTabuleiro, painelJogadores;

    // ── Helper para criar JLabel ───────────────────────────────────
    static JLabel lbl(String t, int sz, int st, Color c) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("SansSerif", st, sz));
        l.setForeground(c);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    static Casa casa(Casa.TipoCasa tipo, Casa.TipoEvento ev, Casa.TipoFinanceira fin,
                     Casa.TipoMovimento mov, Casa.TipoEspecial esp, String instr, int val) {
        return new Casa("", tipo, ev, fin, mov, esp, instr, null, null, val);
    }

    // ══════════════════════════════════════════════════════════════
    public static void main(String[] args) {
        montarTabuleiro();
        montarBaralho();

        Profissao dev = new Profissao("Desenvolvedor");
        Profissao med = new Profissao("Médico");
        jogadores.add(new Jogador("Azul",    "Alice", false, dev, 0, dev.getSalario(), 10000, false, false, 0));
        jogadores.add(new Jogador("Vermelho","Bob",   false, med, 0, med.getSalario(), 10000, false, false, 0));

        SwingUtilities.invokeLater(Main::abrirJanela);
    }

    // ── 38 casas ──────────────────────────────────────────────────
    static void montarTabuleiro() {
        // Tipo          Evento                   Fin                       Mov                       Esp                         Instrução                              Val
        add(Casa.TipoCasa.INICIO,      null,                            null,                     null,                     null,                       "Início!",                             0);
        add(Casa.TipoCasa.FINANCEIRA,  null,                            Casa.TipoFinanceira.GANHO,null,                     null,                       "Bônus! +R$2000",                      2000);
        add(Casa.TipoCasa.MOVIMENTO,   null,                            null,                     Casa.TipoMovimento.AVANCAR,null,                      "Atalho! Avance 2",                    2);
        add(Casa.TipoCasa.EVENTO,      Casa.TipoEvento.CASAMENTO,      null,                     null,                     null,                       "Você se casou!",                      0);
        add(Casa.TipoCasa.FINANCEIRA,  null,                            Casa.TipoFinanceira.PERDA,null,                     null,                       "Imposto! -R$1500",                    1500);
        add(Casa.TipoCasa.MOVIMENTO,   null,                            null,                     Casa.TipoMovimento.VOLTAR,null,                       "Volte 1 casa",                        1);
        add(Casa.TipoCasa.EVENTO,      Casa.TipoEvento.FILHO,          null,                     null,                     null,                       "Bebê chegou! +1 filho",               0);
        add(Casa.TipoCasa.ESPECIAL,    null,                            null,                     null,                     Casa.TipoEspecial.SORTE,    "Sorte! +R$3000",                      3000);
        add(Casa.TipoCasa.FINANCEIRA,  null,                            Casa.TipoFinanceira.GANHO,null,                     null,                       "Investimento! +R$1000",               1000);
        add(Casa.TipoCasa.ESPECIAL,    null,                            null,                     null,                     Casa.TipoEspecial.PULAR_TURNO,"Descansando... Pule turno",          0);
        add(Casa.TipoCasa.EVENTO,      Casa.TipoEvento.ANIVERSARIO,    null,                     null,                     null,                       "Aniversário! +R$500",                 500);
        add(Casa.TipoCasa.EVENTO,      Casa.TipoEvento.PROMOCAO,       null,                     null,                     null,                       "Promoção! Salário R$8000",            8000);
        add(Casa.TipoCasa.FINANCEIRA,  null,                            Casa.TipoFinanceira.PERDA,null,                     null,                       "Reforma! -R$2500",                    2500);
        add(Casa.TipoCasa.EVENTO,      Casa.TipoEvento.ACIDENTE_CARRO, null,                     null,                     null,                       "Acidente! Seguro cobre metade",       3000);
        add(Casa.TipoCasa.MOVIMENTO,   null,                            null,                     Casa.TipoMovimento.AVANCAR,null,                      "Avance 3",                            3);
        add(Casa.TipoCasa.ESPECIAL,    null,                            null,                     null,                     Casa.TipoEspecial.AZAR,     "Azar! -R$1000",                       1000);
        add(Casa.TipoCasa.EVENTO,      Casa.TipoEvento.FILHO,          null,                     null,                     null,                       "Gêmeos! +1 filho",                    0);
        add(Casa.TipoCasa.FINANCEIRA,  null,                            Casa.TipoFinanceira.GANHO,null,                     null,                       "Loteria! +R$5000",                    5000);
        add(Casa.TipoCasa.MOVIMENTO,   null,                            null,                     Casa.TipoMovimento.VOLTAR,null,                       "Carro quebrou! Volte 2",              2);
        add(Casa.TipoCasa.FINANCEIRA,  null,                            Casa.TipoFinanceira.PERDA,null,                     null,                       "Multa! -R$800",                       800);
        add(Casa.TipoCasa.ESPECIAL,    null,                            null,                     null,                     Casa.TipoEspecial.SORTE,    "Promoção relâmpago! +R$2000",         2000);
        add(Casa.TipoCasa.EVENTO,      Casa.TipoEvento.ANIVERSARIO,    null,                     null,                     null,                       "Festa! +R$300",                       300);
        add(Casa.TipoCasa.MOVIMENTO,   null,                            null,                     Casa.TipoMovimento.VOLTAR,null,                       "Engarrafamento! Volte 1",             1);
        add(Casa.TipoCasa.FINANCEIRA,  null,                            Casa.TipoFinanceira.GANHO,null,                     null,                       "Freelance! +R$1500",                  1500);
        add(Casa.TipoCasa.ESPECIAL,    null,                            null,                     null,                     Casa.TipoEspecial.PULAR_TURNO,"Viagem adiada... Pule turno",        0);
        add(Casa.TipoCasa.EVENTO,      Casa.TipoEvento.FILHO,          null,                     null,                     null,                       "Mais um filho!",                      0);
        add(Casa.TipoCasa.FINANCEIRA,  null,                            Casa.TipoFinanceira.PERDA,null,                     null,                       "Dentista! -R$600",                    600);
        add(Casa.TipoCasa.MOVIMENTO,   null,                            null,                     Casa.TipoMovimento.AVANCAR,null,                      "Atalho secreto! Avance 2",            2);
        add(Casa.TipoCasa.ESPECIAL,    null,                            null,                     null,                     Casa.TipoEspecial.AZAR,     "Celular caiu na água! -R$1200",       1200);
        add(Casa.TipoCasa.FINANCEIRA,  null,                            Casa.TipoFinanceira.GANHO,null,                     null,                       "Dividendos! +R$2500",                 2500);
        add(Casa.TipoCasa.EVENTO,      Casa.TipoEvento.PROMOCAO,       null,                     null,                     null,                       "Virou sócio! Salário R$12000",        12000);
        add(Casa.TipoCasa.ESPECIAL,    null,                            null,                     null,                     Casa.TipoEspecial.SORTE,    "Achei dinheiro! +R$500",              500);
        add(Casa.TipoCasa.FINANCEIRA,  null,                            Casa.TipoFinanceira.PERDA,null,                     null,                       "IPTU! -R$1800",                       1800);
        add(Casa.TipoCasa.MOVIMENTO,   null,                            null,                     Casa.TipoMovimento.VOLTAR,null,                       "Volte 3 casas",                       3);
        add(Casa.TipoCasa.FINANCEIRA,  null,                            Casa.TipoFinanceira.GANHO,null,                     null,                       "Bônus de fim de ano! +R$4000",        4000);
        add(Casa.TipoCasa.ESPECIAL,    null,                            null,                     null,                     Casa.TipoEspecial.AZAR,     "Cano estourou! -R$900",               900);
        add(Casa.TipoCasa.EVENTO,      Casa.TipoEvento.ANIVERSARIO,    null,                     null,                     null,                       "Presente surpresa! +R$1000",          1000);
        add(Casa.TipoCasa.EVENTO,      Casa.TipoEvento.APOSENTADORIA,  null,                     null,                     null,                       "Aposentado! Fim da jornada!",         0);
    }

    static void add(Casa.TipoCasa tipo, Casa.TipoEvento ev, Casa.TipoFinanceira fin,
                    Casa.TipoMovimento mov, Casa.TipoEspecial esp, String instr, int val) {
        tabuleiro.add(new Casa("", tipo, ev, fin, mov, esp, instr, null, null, val));
    }

    static void montarBaralho() {
        baralho.add(new CartaPropriedade("Apartamento em SP",    250000));
        baralho.add(new CartaPropriedade("Casa na praia",        150000));
        baralho.add(new CartaPropriedade("Mansão em Alphaville", 5000000));
        baralho.add(new CartaSeguro("Seguro de vida",    500));
        baralho.add(new CartaSeguro("Seguro do carro",   300));
        baralho.add(new CartaSeguro("Seguro residencial",200));
    }

    // ── Janela ─────────────────────────────────────────────────────
    static void abrirJanela() {
        JFrame f = new JFrame("🎲 Jogo da Vida");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(900, 640);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout(6, 6));
        f.getContentPane().setBackground(COR_FUNDO);

        f.add(criarTopo(),        BorderLayout.NORTH);
        painelTabuleiro = criarPainelTabuleiro();
        f.add(painelTabuleiro,    BorderLayout.CENTER);
        f.add(criarLadoDireito(), BorderLayout.EAST);
        f.add(criarRodape(),      BorderLayout.SOUTH);

        f.setVisible(true);
        atualizarTela();
    }

    // ── Topo ───────────────────────────────────────────────────────
    static JPanel criarTopo() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 7));
        p.setBackground(COR_AZUL);
        labelRodada = lbl("Rodada: 1",     13, Font.PLAIN, Color.WHITE);
        labelTurno  = lbl("Vez de: Alice", 13, Font.BOLD,  COR_AMARELO);
        p.add(lbl("🎲 JOGO DA VIDA", 18, Font.BOLD, Color.WHITE));
        p.add(Box.createHorizontalStrut(16));
        p.add(labelRodada);
        p.add(Box.createHorizontalStrut(8));
        p.add(labelTurno);
        return p;
    }

    // ── Tabuleiro ──────────────────────────────────────────────────
    static JPanel criarPainelTabuleiro() {
        JPanel p = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharTabuleiro(g);
            }
        };
        p.setBackground(COR_FUNDO);
        p.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        return p;
    }

    static void desenharTabuleiro(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int total = tabuleiro.size(); // 38
        int cols  = 10;
        int rows  = (int) Math.ceil((double) total / cols); // 4 linhas
        int W     = painelTabuleiro.getWidth()  - 12;
        int H     = painelTabuleiro.getHeight() - 12;
        int cW    = W / cols;
        int cH    = H / rows;

        Color[] coresJog = {COR_AZUL, COR_VERMELHO, COR_VERDE, COR_ROXO};

        for (int i = 0; i < total; i++) {
            Casa casa = tabuleiro.get(i);
            int row = i / cols;
            // linhas pares: esquerda→direita | ímpares: direita→esquerda (serpentina)
            int col = (row % 2 == 0) ? (i % cols) : (cols - 1 - (i % cols));
            int x = 6 + col * cW;
            int y = 6 + row * cH;

            Color bg = corDaCasa(casa);
            g2.setColor(bg);
            g2.fillRoundRect(x+1, y+1, cW-2, cH-2, 8, 8);
            g2.setColor(bg.darker());
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawRoundRect(x+1, y+1, cW-2, cH-2, 8, 8);

            // Número pequeno
            g2.setColor(new Color(0,0,0,120));
            g2.setFont(new Font("SansSerif", Font.BOLD, 9));
            g2.drawString(String.valueOf(i+1), x+3, y+10);

            // Ícone centralizado
            g2.setFont(new Font("SansSerif", Font.PLAIN, 15));
            g2.setColor(COR_TEXTO);
            g2.drawString(icone(casa), x + cW/2 - 8, y + cH/2 + 5);

            // Peões
            int px = x + 3;
            for (int j = 0; j < jogadores.size(); j++) {
                if (jogadores.get(j).getCasas() == i) {
                    g2.setColor(coresJog[j % coresJog.length]);
                    g2.fillOval(px, y + cH - 16, 12, 12);
                    g2.setColor(Color.WHITE);
                    g2.setFont(new Font("SansSerif", Font.BOLD, 7));
                    g2.drawString(jogadores.get(j).getNome().substring(0,1), px+3, y+cH-7);
                    px += 14;
                }
            }
        }
    }

    static Color corDaCasa(Casa c) {
        switch (c.getTipo()) {
            case FINANCEIRA: return c.getFinanceira()==Casa.TipoFinanceira.GANHO
                    ? new Color(144,238,144) : new Color(255,160,160);
            case EVENTO:     return new Color(255,228,100);
            case MOVIMENTO:  return c.getMovimento()==Casa.TipoMovimento.AVANCAR
                    ? new Color(135,206,250) : new Color(255,185,100);
            case ESPECIAL:
                if (c.getEspecial()==Casa.TipoEspecial.SORTE)       return new Color(144,238,144);
                if (c.getEspecial()==Casa.TipoEspecial.AZAR)        return new Color(255,160,160);
                if (c.getEspecial()==Casa.TipoEspecial.PULAR_TURNO) return new Color(200,200,200);
                return new Color(200,160,220);
            case INICIO:     return new Color(255,215,0);
            default:         return Color.LIGHT_GRAY;
        }
    }

    static String icone(Casa c) {
        switch (c.getTipo()) {
            case FINANCEIRA: return c.getFinanceira()==Casa.TipoFinanceira.GANHO ? "💰" : "💸";
            case ESPECIAL:
                if (c.getEspecial()==Casa.TipoEspecial.SORTE)       return "🍀";
                if (c.getEspecial()==Casa.TipoEspecial.AZAR)        return "💀";
                if (c.getEspecial()==Casa.TipoEspecial.PULAR_TURNO) return "⏸";
                return "🔄";
            case MOVIMENTO:  return c.getMovimento()==Casa.TipoMovimento.AVANCAR ? "⏩" : "⏪";
            case EVENTO:
                if (c.getEvento()==null) return "⭐";
                switch (c.getEvento()) {
                    case CASAMENTO:      return "💍";
                    case FILHO:          return "👶";
                    case ANIVERSARIO:    return "🎂";
                    case PROMOCAO:       return "📈";
                    case ACIDENTE_CARRO: return "🚗";
                    case APOSENTADORIA:  return "🏆";
                    default:             return "⭐";
                }
            case INICIO: return "🚀";
            default:     return "?";
        }
    }

    // ── Lado direito ───────────────────────────────────────────────
    static JPanel criarLadoDireito() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setPreferredSize(new Dimension(205, 0));
        p.setBackground(COR_FUNDO);
        p.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 8));

        painelJogadores = new JPanel();
        painelJogadores.setLayout(new BoxLayout(painelJogadores, BoxLayout.Y_AXIS));
        painelJogadores.setBackground(COR_FUNDO);
        p.add(painelJogadores);
        p.add(Box.createVerticalStrut(8));
        p.add(lbl("📋 Log", 13, Font.BOLD, COR_AZUL));
        p.add(Box.createVerticalStrut(3));

        areaLog = new JTextArea(14, 17);
        areaLog.setEditable(false);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 10));
        areaLog.setBackground(new Color(240,245,255));
        areaLog.setLineWrap(true);
        areaLog.setWrapStyleWord(true);
        areaLog.setBorder(BorderFactory.createEmptyBorder(3,5,3,5));
        JScrollPane sc = new JScrollPane(areaLog);
        sc.setAlignmentX(Component.LEFT_ALIGNMENT);
        sc.setBorder(BorderFactory.createLineBorder(COR_AZUL, 1));
        p.add(sc);
        return p;
    }

    // ── Rodapé ─────────────────────────────────────────────────────
    static JPanel criarRodape() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 7));
        p.setBackground(new Color(220,220,220));

        labelRoleta = new JLabel("Gire a roleta para jogar!");
        labelRoleta.setFont(new Font("SansSerif", Font.ITALIC, 12));
        labelRoleta.setForeground(new Color(90,90,90));

        JButton btnGirar = botao("🎲 Girar Roleta", COR_VERDE, e -> executarTurno());
        JButton btnCarta = botao("🃏 Pegar Carta",  COR_ROXO,  e -> pegarCarta());

        p.add(labelRoleta);
        p.add(btnGirar);
        p.add(btnCarta);
        return p;
    }

    static JButton botao(String texto, Color cor, ActionListener al) {
        JButton b = new JButton(texto);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setBackground(cor); b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(7,18,7,18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(al);
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){ b.setBackground(cor.darker()); }
            public void mouseExited (MouseEvent e){ b.setBackground(cor); }
        });
        return b;
    }

    // ── Lógica do turno ───────────────────────────────────────────
    static void executarTurno() {
        Jogador atual = jogadores.get(rodada.getJogadorAtual());
        int dado = roleta.girar();
        labelRoleta.setText("🎲 " + atual.getNome() + " tirou: " + dado);

        int novaCasa = Math.min(atual.getCasas() + dado, tabuleiro.size() - 1);
        atual.setCasas(novaCasa);
        log("── " + atual.getNome() + " tirou " + dado + " → casa " + (novaCasa+1));

        Casa casa = tabuleiro.get(novaCasa);
        aplicar(casa, atual);
        atualizarTela();

        if (atual.getCasas() >= tabuleiro.size() - 1) {
            JOptionPane.showMessageDialog(null,
                    "🏆 " + atual.getNome() + " se aposentou!\nPatrimônio: R$ "
                            + String.format("%.0f", atual.getPatrimonio()),
                    "Fim de Jogo 🎉", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        rodada.proximoTurno(jogadores);
        atualizarTela();
    }

    static void aplicar(Casa casa, Jogador jogador) {
        String instr = casa.getInstrucao() != null ? casa.getInstrucao() : casa.toString();
        log("  " + icone(casa) + " " + instr);
        casa.aplicar(jogador);

        // Popup resumido
        String extra = "";
        if (casa.getTipo()==Casa.TipoCasa.FINANCEIRA || casa.getTipo()==Casa.TipoCasa.ESPECIAL) {
            boolean ganho = casa.getFinanceira()==Casa.TipoFinanceira.GANHO
                    || casa.getEspecial()==Casa.TipoEspecial.SORTE;
            extra = ganho ? "\n+R$"+casa.getValor() : "\n-R$"+casa.getValor();
        }
        if (casa.getTipo()==Casa.TipoCasa.MOVIMENTO) {
            boolean av = casa.getMovimento()==Casa.TipoMovimento.AVANCAR;
            extra = (av?"\n⏩ Avançou ":"\n⏪ Voltou ") + casa.getValor()
                    + " casa(s) → agora na " + (jogador.getCasas()+1);
        }
        JOptionPane.showMessageDialog(null,
                icone(casa) + "  " + instr + extra,
                "Casa " + (jogador.getCasas()+1), JOptionPane.INFORMATION_MESSAGE);
    }

    static void pegarCarta() {
        Jogador atual = jogadores.get(rodada.getJogadorAtual());
        Carta carta = baralho.get(indiceCarta++ % baralho.size());
        atual.getCartas().add(carta);

        String msg = "";
        if (carta instanceof CartaSeguro) {
            atual.setSeguro(true);
            msg = "[SEGURO] " + carta.descricao + " - R$" + carta.valor + "\n✅ Seguro ativado!";
        } else {
            msg = "[PROPRIEDADE] " + carta.descricao + " - R$" + carta.valor;
        }
        log("  🃏 " + atual.getNome() + " pegou: " + carta.descricao);
        JOptionPane.showMessageDialog(null, "🃏 " + atual.getNome() + " pegou:\n\n" + msg,
                "Carta", JOptionPane.INFORMATION_MESSAGE);
        atualizarTela();
    }

    // ── Atualizar UI ──────────────────────────────────────────────
    static void atualizarTela() {
        Jogador atual = jogadores.get(rodada.getJogadorAtual());
        labelRodada.setText("Rodada: " + rodada.getNumeroRodada());
        labelTurno.setText("Vez de: " + atual.getNome());

        painelJogadores.removeAll();
        painelJogadores.add(lbl("👤 Jogadores", 13, Font.BOLD, COR_AZUL));
        painelJogadores.add(Box.createVerticalStrut(5));

        Color[] cj = {COR_AZUL, COR_VERMELHO, COR_VERDE, COR_ROXO};
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador j = jogadores.get(i);
            Color c = cj[i % cj.length];
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(c.brighter().brighter());
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(c, 2),
                    BorderFactory.createEmptyBorder(4, 7, 4, 7)));
            card.setMaximumSize(new Dimension(195, 100));
            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            card.add(lbl(j.getNome() + (i==rodada.getJogadorAtual()?" ◀":""), 12, Font.BOLD, c.darker().darker()));
            card.add(lbl("Casa: "+(j.getCasas()+1)+"/"+tabuleiro.size(), 10, Font.PLAIN, COR_TEXTO));
            card.add(lbl("R$ "+String.format("%.0f",j.getPatrimonio()), 10, Font.PLAIN, COR_TEXTO));
            card.add(lbl((j.getProfissao()!=null?j.getProfissao().getNome():"-")
                    +" | Filhos: "+j.getFilhos(), 10, Font.PLAIN, COR_TEXTO));

            painelJogadores.add(card);
            painelJogadores.add(Box.createVerticalStrut(5));
        }
        painelJogadores.revalidate();
        painelJogadores.repaint();
        painelTabuleiro.repaint();
    }

    static void log(String msg) {
        areaLog.append(msg + "\n");
        areaLog.setCaretPosition(areaLog.getDocument().getLength());
    }
}