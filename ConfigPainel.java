import java.awt.*;
import javax.swing.*;

public class ConfigPainel extends JPanel {
    private JTextField[] camposNome;
    private JLabel[] labelsNome;
    private JComboBox<String> comboQtd;
    private final Image fundoConfig;

    public ConfigPainel() {
        fundoConfig = Main.carregarImagem("config_background.png");
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Configuração de Jogadores");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblTitulo.setForeground(Color.BLACK);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        JLabel lblQtd = new JLabel("Número de Jogadores:");
        gbc.gridy = 1; gbc.gridwidth = 1;
        add(lblQtd, gbc);

        String[] opcoes = {"2", "3", "4"};
        comboQtd = new JComboBox<>(opcoes);
        gbc.gridx = 1;
        add(comboQtd, gbc);

        camposNome = new JTextField[4];
        labelsNome = new JLabel[4];
        String[] coresFixas = {"Azul", "Vermelho", "Verde", "Rosa"};

        for (int i = 0; i < 4; i++) {
            labelsNome[i] = new JLabel("Nome Jogador " + (i + 1) + " (" + coresFixas[i] + "):");
            camposNome[i] = new JTextField(15);
            gbc.gridx = 0; gbc.gridy = 2 + i;
            add(labelsNome[i], gbc);
            gbc.gridx = 1;
            add(camposNome[i], gbc);

            if (i >= 2) {
                labelsNome[i].setVisible(false);
                camposNome[i].setVisible(false);
            }
        }

        comboQtd.addActionListener(e -> {
            int qtd = comboQtd.getSelectedIndex() + 2;
            for (int i = 0; i < 4; i++) {
                boolean visivel = i < qtd;
                labelsNome[i].setVisible(visivel);
                camposNome[i].setVisible(visivel);
            }
            revalidate();
            repaint();
        });

        JButton btnIniciar = new JButton("Iniciar Partida!");
        btnIniciar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnIniciar.setBackground(new Color(200, 20, 20));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(140, 0, 0), 3),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        btnIniciar.addActionListener(e -> {
            int qtd = comboQtd.getSelectedIndex() + 2;
            Main.jogadores.clear();
            Main.rodada = new Rodada();

            StringBuilder resumoProfissoes = new StringBuilder("Profissões sorteadas:\n");

            for (int i = 0; i < qtd; i++) {
                String nome = camposNome[i].getText().trim();
                if (nome.isEmpty()) nome = "Jogador " + (i + 1);
                Profissao profissaoSorteada = Main.sortearProfissaoAleatoria();
                Main.jogadores.add(new Jogador(coresFixas[i], nome, profissaoSorteada, false));
                resumoProfissoes.append("- ")
                        .append(nome)
                        .append(": ")
                        .append(profissaoSorteada.getNome())
                        .append(" (R$")
                        .append((int) profissaoSorteada.getSalario())
                        .append(")\n");
            }

            Main.tabuleiro = new Tabuleiro();
            Main.tabuleiro.montarTabuleiro();
            Main.criarJanelaJogo();
            JOptionPane.showMessageDialog(null, resumoProfissoes.toString(), "Sorteio de Profissões", JOptionPane.INFORMATION_MESSAGE);
        });

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        add(btnIniciar, gbc);

        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.setBackground(new Color(0, 119, 255));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnVoltar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 29, 220), 4),
                BorderFactory.createEmptyBorder(12, 30, 12, 30)));

        btnVoltar.addActionListener(e -> Main.mostrarMenuInicial());
        gbc.gridy = 7;
        add(btnVoltar, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fundoConfig, 0, 0, getWidth(), getHeight(), this);
    }
}