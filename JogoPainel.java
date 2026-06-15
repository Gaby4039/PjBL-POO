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

        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        
        JLabel lblTitulo = new JLabel("JOGADORES:");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 13));
        painel.add(lblTitulo);
        painel.add(Box.createVerticalStrut(10));

        for (Jogador j : Main.jogadores) {
            JLabel lbl = new JLabel(j.getNome() + " - R$" + (int) j.getPatrimonio());
            lbl.setForeground(Color.WHITE);
            painel.add(lbl);
            
            JLabel lblProf = new JLabel("Profissão: " + j.getProfissao().getNome());
            lblProf.setForeground(new Color(200, 200, 255));
            lblProf.setFont(new Font("Arial", Font.PLAIN, 10));
            painel.add(lblProf);
            
            JLabel lblProps = new JLabel("Propriedades: " + j.getPropriedades().size());
            lblProps.setForeground(new Color(200, 200, 255));
            lblProps.setFont(new Font("Arial", Font.PLAIN, 10));
            painel.add(lblProps);
            
            painel.add(Box.createVerticalStrut(8));
        }
        
        painel.add(Box.createVerticalStrut(5));
        
        // Carta Seguro
        Jogador jogadorAtual = Main.jogadores.get(Main.rodada.getJogadorAtual());
        JPanel cartaSeguro = new JPanel();
        cartaSeguro.setLayout(new BorderLayout());
        cartaSeguro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cartaSeguro.setPreferredSize(new Dimension(150, 40));
        
        if (jogadorAtual.temSeguro()) {
            cartaSeguro.setBackground(new Color(100, 200, 255));
            CartaSeguro seguro = jogadorAtual.getSeguro();
            JLabel lblSeguro = new JLabel("✓ " + seguro.getTipo() + " - R$" + (int)seguro.getValor());
            lblSeguro.setFont(new Font("Arial", Font.BOLD, 9));
            lblSeguro.setForeground(Color.BLACK);
            lblSeguro.setHorizontalAlignment(JLabel.CENTER);
            cartaSeguro.add(lblSeguro, BorderLayout.CENTER);
        } else {
            cartaSeguro.setBackground(new Color(150, 150, 150));
            JLabel lblSeguro = new JLabel("SEGURO INATIVO");
            lblSeguro.setFont(new Font("Arial", Font.PLAIN, 10));
            lblSeguro.setForeground(Color.BLACK);
            lblSeguro.setHorizontalAlignment(JLabel.CENTER);
            cartaSeguro.add(lblSeguro, BorderLayout.CENTER);
        }
        
        painel.add(cartaSeguro);
        painel.add(Box.createVerticalStrut(15));

        // Botões
        JButton btnComprarCasa = new JButton("Comprar Casa");
        btnComprarCasa.addActionListener(e -> Main.tentarComprarCasaDoTurno());

        JButton btnSeguro = new JButton("Comprar Seguro");
        btnSeguro.addActionListener(e -> Main.tentarComprarSeguro());

        JButton btnSalvar = new JButton("Salvar Jogo");
        btnSalvar.addActionListener(e -> Main.salvarJogo());
        
        JButton btnCarregar = new JButton("Carregar Jogo");
        btnCarregar.addActionListener(e -> Main.carregarJogo());
        
        JButton btnQuit = new JButton("Sair do Jogo");
        btnQuit.addActionListener(e -> System.exit(0));
        btnQuit.setBackground(new Color(255, 100, 100));
        btnQuit.setForeground(Color.WHITE);

        JPanel botoes = new JPanel(new GridLayout(5, 1, 0, 8));
        botoes.setOpaque(false);
        botoes.add(btnComprarCasa);
        botoes.add(btnSeguro);
        botoes.add(btnSalvar);
        botoes.add(btnCarregar);
        botoes.add(btnQuit);

        painelDireito.add(painel, BorderLayout.NORTH);
        painelDireito.add(botoes, BorderLayout.SOUTH);

        painelDireito.revalidate();
        painelDireito.repaint();
    }

    public void repintarTabuleiro() {
        painelTabuleiro.repaint();
    }
}
