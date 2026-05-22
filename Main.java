import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Jogo da Vida");
        janela.setVisible(true);
        janela.setSize(300, 150);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Olá");
        JPanel painel = new JPanel(null); //Padrão, sem gerenciador de estilos
        // JTextField campo1 = new
        JButton botao1 = new JButton();

        janela.add(painel);
        painel.add(label);

        Dimension dimensao = label.getPreferredSize();
        label.setBounds(300 /2, 150 / 2, dimensao.width, dimensao.height);

       

        
    




    }
}