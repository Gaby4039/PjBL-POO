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

        painelDireito = new JPanel();
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        painelDireito.setPreferredSize(new Dimension(220, 0));
        painelDireito.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 8));

        JPanel painelBaixo = new JPanel(new FlowLayout());
        Jogador primeiro = Main.jogadores.get(Main.rodada.getJogadorAtual());
        Main.labelStatus = new JLabel("Rodada " + Main.rodada.getNumeroRodada() + " - Vez de: " + primeiro.getNome());
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
        painelDireito.add(new JLabel("Jogadores:"));
        painelDireito.add(Box.createVerticalStrut(5));
        for (Jogador j : Main.jogadores) {
            JLabel lj = new JLabel(j.getNome() + " - R$" + (int) j.getPatrimonio());
            painelDireito.add(lj);
        }

        painelDireito.add(Box.createVerticalStrut(20));

        JButton btnSalvar = new JButton("Salvar Jogo");
        btnSalvar.addActionListener(e -> Main.salvarJogo());
        painelDireito.add(btnSalvar);

        painelDireito.add(Box.createVerticalStrut(5));

        JButton btnCarregar = new JButton("Carregar Jogo");
        btnCarregar.addActionListener(e -> Main.carregarJogo());
        painelDireito.add(btnCarregar);

        painelDireito.add(Box.createVerticalStrut(20));

        JButton btnQuit = new JButton("Sair do Jogo");
        btnQuit.addActionListener(e -> System.exit(0));
        btnQuit.setBackground(new Color(255, 100, 100));
        btnQuit.setForeground(Color.WHITE);
        painelDireito.add(btnQuit);

        painelDireito.revalidate();
        painelDireito.repaint();
    }

    public void repintarTabuleiro() {
        painelTabuleiro.repaint();
    }
}