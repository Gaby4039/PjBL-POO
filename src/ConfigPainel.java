import java.awt.*;
import javax.swing.*;

public class ConfigPainel extends JPanel {
    private JTextField[] camposNome;
    private JLabel[] labelsNome;
    private JComboBox<String> comboQtd;

    public ConfigPainel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Configuração de Jogadores");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
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
        btnIniciar.addActionListener(e -> {
            int qtd = comboQtd.getSelectedIndex() + 2;
            Main.jogadores.clear();

            for (int i = 0; i < qtd; i++) {
                String nome = camposNome[i].getText().trim();
                if (nome.isEmpty()) nome = "Jogador " + (i + 1);
                Main.jogadores.add(new Jogador(coresFixas[i], nome, new Profissao("Desenvolvedor"), false));
            }

            Main.montarTabuleiro();
            Main.criarJanelaJogo();
        });

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        add(btnIniciar, gbc);

        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> Main.mostrarMenuInicial());
        gbc.gridy = 7;
        add(btnVoltar, gbc);
    }
}