import java.awt.*;
import javax.swing.*;

public class MenuPainel extends JPanel {
    private final Image fundoMenu;

    public MenuPainel() {
        fundoMenu = new ImageIcon("backgroundMenu4.png").getImage();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("JOGO DA VIDA");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 58));
        titulo.setForeground(new Color(255, 255, 255));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(200, 20, 20));
        titulo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(140, 0, 0), 4),
                BorderFactory.createEmptyBorder(12, 30, 12, 30)));

        JButton btnStart = new JButton("Iniciar jornada");
        btnStart.setFont(new Font("Segoe UI", Font.BOLD, 26));
        btnStart.setBackground(new Color(37, 183, 0));
        btnStart.setForeground(Color.WHITE);
        btnStart.setFocusPainted(false);
        btnStart.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 151, 0), 3),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        btnStart.setPreferredSize(new Dimension(300, 70));
        btnStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStart.addActionListener(e -> Main.mostrarConfiguracaoJogadores());
        
        JButton btnQuit = new JButton("Sair");
        btnQuit.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnQuit.setBackground(new Color(200, 20, 20));
        btnQuit.setForeground(Color.WHITE);
        btnQuit.setFocusPainted(false);
        btnQuit.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(140, 0, 0), 3),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)));
        btnQuit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fundoMenu, 0, 0, getWidth(), getHeight(), this);
    }
}
