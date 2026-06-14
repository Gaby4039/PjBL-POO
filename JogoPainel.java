import java.awt.*;
import javax.swing.*;

public class JogoPainel extends JPanel {
    private JPanel painelTabuleiro;
    private JPanel painelDireito;

    public JogoPainel() {
        setLayout(new BorderLayout());

        painelTabuleiro = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Main.desenharTabuleiro(g, this.getWidth(), this.getHeight());
            }
        };
        painelTabuleiro.setBackground(Color.WHITE);

        painelDireito = new JPanel(new BorderLayout());
        painelDireito.setPreferredSize(new Dimension(170, 0));
        painelDireito.setBackground(new Color(30, 100, 210));
        painelDireito.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelBaixo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBaixo.setOpaque(false);
        Jogador primeiro = Main.jogadores.get(Main.rodada.getJogadorAtual());
        Main.labelStatus = new JLabel("Rodada " + Main.rodada.getNumeroRodada() + " - Vez de: " + primeiro.getNome());
        Main.labelStatus.setForeground(Color.WHITE);
        JButton btnGirar = new JButton("Girar Roleta");
        btnGirar.addActionListener(e -> Main.jogar());
        painelBaixo.add(Main.labelStatus);
        painelBaixo.add(btnGirar);

        add(painelTabuleiro, BorderLayout.CENTER);
        add(painelDireito,   BorderLayout.EAST);
        add(painelBaixo,     BorderLayout.SOUTH);

        atualizarPainelDireito();
    }

    public void atualizarPainelDireito() {
        painelDireito.removeAll();

        JPanel topo = new JPanel();
        topo.setOpaque(false);
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));
        JLabel lblTitulo = new JLabel("Jogadores:");
        lblTitulo.setForeground(Color.WHITE);
        topo.add(lblTitulo);
        topo.add(Box.createVerticalStrut(8));

        for (Jogador j : Main.jogadores) {
            JLabel lj = new JLabel(j.getNome() + " - R$" + (int) j.getPatrimonio());
            lj.setForeground(Color.WHITE);
            topo.add(lj);
            topo.add(Box.createVerticalStrut(5));
        }

        JButton btnSalvar = new JButton("Salvar Jogo");
        btnSalvar.addActionListener(e -> Main.salvarJogo());
        JButton btnCarregar = new JButton("Carregar Jogo");
        btnCarregar.addActionListener(e -> Main.carregarJogo());
        JButton btnQuit = new JButton("Sair do Jogo");
        btnQuit.addActionListener(e -> System.exit(0));
        btnQuit.setBackground(new Color(255, 100, 100));
        btnQuit.setForeground(Color.WHITE);

        JPanel baseBotoes = new JPanel(new GridLayout(3, 1, 0, 8));
        baseBotoes.setOpaque(false);
        baseBotoes.add(btnSalvar);
        baseBotoes.add(btnCarregar);
        baseBotoes.add(btnQuit);

        painelDireito.add(topo, BorderLayout.NORTH);
        painelDireito.add(baseBotoes, BorderLayout.SOUTH);

        painelDireito.revalidate();
        painelDireito.repaint();
    }

    public void repintarTabuleiro() {
        painelTabuleiro.repaint();
    }
}
