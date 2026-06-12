import java.awt.*;
import javax.swing.*;

public class MenuPainel extends JPanel {
    public MenuPainel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 248, 255));

        JLabel titulo = new JLabel("JOGO DA VIDA");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 54));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnStart = new JButton("Start");
        btnStart.setFont(new Font("SansSerif", Font.BOLD, 24));
        btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStart.addActionListener(e -> Main.mostrarConfiguracaoJogadores());

        JButton btnQuit = new JButton("Quit");
        btnQuit.setFont(new Font("SansSerif", Font.BOLD, 24));
        btnQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuit.addActionListener(e -> System.exit(0));

        add(Box.createVerticalGlue());
        add(titulo);
        add(Box.createVerticalStrut(60));
        add(btnStart);
        add(Box.createVerticalStrut(20));
        add(btnQuit);
        add(Box.createVerticalGlue());
    }
}