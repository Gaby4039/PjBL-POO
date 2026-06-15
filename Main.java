import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {

    public static ArrayList<Jogador> jogadores = new ArrayList<>();
    public static Tabuleiro tabuleiro = new Tabuleiro();
    public static Roleta roleta = new Roleta();
    public static Rodada rodada = new Rodada();
    public static Banco banco = new Banco();

    public static JFrame janela;
    public static JLabel labelStatus;
    public static JogoPainel jogoPainel;
    public static JLabel lblRoleta;
    private static final Random RANDOM = new Random();
    private static int contadorCasas = 1;
    private static final String[] IMG_NAMES = {"redCar.png", "blueCar.png", "greenCar.png", "pinkCar.png"};
    private static final int PLAYER_IMG_W = 32;
    private static final int PLAYER_IMG_H = 32;
    public static BufferedImage[] jogadorImgs = new BufferedImage[IMG_NAMES.length];
    private static BufferedImage fundoTabuleiro;

    public static void main(String[] args) {
        Profissao.carregarProfissoesDoCSV("PjBL-POO/profissoes.csv");
        janela = new JFrame("Jogo da Vida");
        janela.setSize(900, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);

        mostrarMenuInicial();
    }

    public static void mostrarMenuInicial() {
        janela.setContentPane(new MenuPainel());
        janela.revalidate();
        janela.repaint();
        janela.setVisible(true);
    }

    public static void mostrarConfiguracaoJogadores() {
        janela.setContentPane(new ConfigPainel());
        janela.revalidate();
        janela.repaint();
    }

    public static void criarJanelaJogo() {
        carregarImagensJogadores();

        fundoTabuleiro = carregarImagem("backgroundJogo2.png");

        jogoPainel = new JogoPainel();
        janela.setContentPane(jogoPainel);
        janela.revalidate();
        janela.repaint();
    }

    private static void carregarImagensJogadores() {
        for (int i = 0; i < IMG_NAMES.length; i++) {
            jogadorImgs[i] = carregarImagem(IMG_NAMES[i]);
        }
    }

    public static BufferedImage carregarImagem(String nome) {
        try {
            File f = new File(nome);
            if (f.exists()) return ImageIO.read(f);
        } 
        catch (Exception ignored) {}
        try {
            if (Main.class.getResourceAsStream("/" + nome) != null) {
                return ImageIO.read(Main.class.getResourceAsStream("/" + nome));
            }
        } 
        catch (Exception ignored) {}
        return null;
    }

    private static final int ANIMACAO_DELAY_MS = 150;
    private static boolean animando = false;

    public static void montarTabuleiro() {
        tabuleiro = new Tabuleiro();
        tabuleiro.montarTabuleiro();
    }

    private static void moverJogadorPassoAPasso(Jogador jogador, int destino, Runnable aoFinalizar) {
        if (jogador.getCasas() == destino) {
            if (aoFinalizar != null) aoFinalizar.run();
            return;
        }

        animando = true;
        int passo = destino > jogador.getCasas() ? 1 : -1;
        Timer timer = new Timer(ANIMACAO_DELAY_MS, null);
        timer.addActionListener(e -> {
            int casaAtual = jogador.getCasas();
            if (casaAtual == destino) {
                timer.stop();
                animando = false;
                if (aoFinalizar != null) aoFinalizar.run();
                return;
            }

            jogador.setCasas(casaAtual + passo);
            if (jogoPainel != null) jogoPainel.repintarTabuleiro();
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public static void desenharTabuleiro(Graphics g, int larguraPainel, int alturaPainel) {
        if (tabuleiro == null || tabuleiro.getCasas().isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(
            fundoTabuleiro,
            0,
            0,
            larguraPainel,
            alturaPainel,
            null
        );


        int total = tabuleiro.getCasas().size();
        int cols = 12;
        int rows = 7;
        int margin = 12;
        int tamanhoCasa = Math.min((larguraPainel - margin * 2) / cols, (alturaPainel - margin * 2) / rows) + 5;

        int offsetX = 2;
        int offsetY = 3;

        Font numeroFonte = new Font("SansSerif", Font.BOLD, 14);
        Font textoFonte = new Font("SansSerif", Font.BOLD, 12);

        for (int i = 0; i < total; i++) {
            Casa casa = tabuleiro.getCasa(i);
            int row;
            int col;
            if (i < 12) {
                row = 0;
                col = i;
            }
            else if (i == 12) {
                row = 1;
                col = 11;
            }
            else if (i == 13) {
                row = 2;
                col = 11;
            }
            else if (i < 25) {
                row = 2;
                col = 24 - i;
            }
            else if (i == 25) {
                row = 3;
                col = 0;
            }
            else if (i == 26) {
                row = 4;
                col = 0;
            }
            else if (i < 39) {
                row = 4;
                col = i - 27;
            }
            else if (i == 39) {
                row = 5;
                col = 11;
            }

            else if (i == 40) {
                row = 6;
                col = 11;
            }

            else {
                row = 6;
                col = 51 - i;
            }

            int x = offsetX + col * (tamanhoCasa + 2);
            int y = offsetY + row * (tamanhoCasa + 12);
            int w = tamanhoCasa;
            int h = tamanhoCasa + 17;

            Color corFundo = corDaCasa(casa);
            g2.setColor(new Color(0, 0, 0, 50));
            g2.fillRoundRect(x + 8, y + 12, w, h, 16, 16);
            g2.setColor(corFundo);
            g2.fillRoundRect(x + 3, y + 3, w, h, 16, 16);

            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.gray);
            g2.drawRoundRect(x + 3, y + 3, w, h, 16, 16);

            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(x + 5, y + 5, w - 4, h - 4, 12, 12);

            g2.setFont(numeroFonte);
            g2.setColor(Color.WHITE);
            String numero = String.valueOf(i + 1);
            FontMetrics fmn = g2.getFontMetrics();
            int numeroX = x + (w - fmn.stringWidth(numero)) / 2;
            int numeroY = y + 20;
            g2.drawString(numero, numeroX, numeroY);

            String instrucao = casa.getInstrucao();
            if (instrucao != null) {
                g2.setFont(textoFonte);
                g2.setColor(Color.BLACK);
                FontMetrics fmi = g2.getFontMetrics();
                int linhaY = y + 36;
                int espacamento = 13;
                int maxWidth = w - 10;
                String linha = "";

                for (String palavra : instrucao.split(" ")) {
                    String teste = linha.isEmpty() ? palavra : linha + " " + palavra;
                    if (fmi.stringWidth(teste) > maxWidth) {
                        g2.drawString(linha, x + 8, linhaY);
                        linha = palavra;
                        linhaY += espacamento;
                        if (linhaY > y + h - 10) break;
                    } 
                    
                    else {
                        linha = teste;
                    }
                }

                if (!linha.isEmpty() && linhaY <= y + h - 10) {
                    g2.drawString(linha, x + 8, linhaY);
                }
            }

            int contador = 0;

            for (int j = 0; j < jogadores.size(); j++) {
                if (jogadores.get(j).getCasas() == i) {

                    int coluna = contador % 2;
                    int linha = contador / 2;

                    int jogadorX = x + 6 + coluna * (PLAYER_IMG_W + 4);
                    int jogadorY = y + h - PLAYER_IMG_H - 4 - linha * (PLAYER_IMG_H + 4);

                    BufferedImage img = jogadorImgs[j % jogadorImgs.length];

                    g2.drawImage(img, jogadorX, jogadorY, PLAYER_IMG_W, PLAYER_IMG_H, null);
    
                    contador++;
                }
            }
        }
    }

    public static Profissao sortearProfissaoAleatoria() {
        String[] profissoes = Profissao.listarProfissoesDisponiveis();
        if (profissoes.length == 0) {
            return new Profissao("Desenvolvedor");
        }
        String nomeSorteado = profissoes[RANDOM.nextInt(profissoes.length)];
        return new Profissao(nomeSorteado);
    }

    public static void tentarComprarCasaDoTurno() {
        if (animando || jogadores.isEmpty()) {
            return;
        }

        Jogador atual = jogadores.get(rodada.getJogadorAtual());
        
        double valor = 28000;
        
        CartaPropriedade casa = new CartaPropriedade("Casa " + contadorCasas, "Casa", valor);

        try {
            atual.tentarComprarPropriedade(casa);
            
            contadorCasas++;
            JOptionPane.showMessageDialog(null,
                    atual.getNome() + " comprou uma casa!\n" +
                    "Casa: " + casa.getNome() +
                    "\nValor: R$" + (int) casa.getValorCompra() + 
                    "\nSaldo atual: R$" + (int) atual.getPatrimonio(),
                    "Compra realizada", JOptionPane.INFORMATION_MESSAGE);
                    
        } catch (SaldoInsuficienteException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(), 
                    "Saldo insuficiente", JOptionPane.WARNING_MESSAGE);
        }

        if (jogoPainel != null) {
            jogoPainel.atualizarPainelDireito();
        }
    }

    public static void tentarComprarSeguro() {
        if (animando || jogadores.isEmpty()) {
            return;
        }

        Jogador atual = jogadores.get(rodada.getJogadorAtual());
        double CUSTO_SEGURO = 1000;

        if (atual.temSeguro()) {
            JOptionPane.showMessageDialog(null,
                    "Você já possui um seguro contratado!",
                    "Seguro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (atual.getPatrimonio() < CUSTO_SEGURO) {
            JOptionPane.showMessageDialog(null,
                    "Saldo insuficiente!\n" +
                    "Custo do seguro: R$" + (int)CUSTO_SEGURO +
                    "\nSeu saldo: R$" + (int)atual.getPatrimonio(),
                    "Compra falhou", JOptionPane.WARNING_MESSAGE);
            return;
        }

        atual.perderDinheiro(CUSTO_SEGURO);
        CartaSeguro novoSeguro = new CartaSeguro("Seguro Completo", CUSTO_SEGURO);
        atual.setSeguro(novoSeguro);

        JOptionPane.showMessageDialog(null,
                "Seguro contratado com sucesso!\n" +
                "Você está protegido contra eventos ruins (pode usar uma vez).\n" +
                "Saldo: R$" + (int)atual.getPatrimonio(),
                "Seguro Adquirido", JOptionPane.INFORMATION_MESSAGE);

        if (jogoPainel != null) {
            jogoPainel.atualizarPainelDireito();
        }
    }

    public static String resumoPropriedades(Jogador jogador) {
        if (jogador.getPropriedades().isEmpty()) {
            return "Nenhuma";
        }

        String resultado = "";
        for (int i = 0; i < jogador.getPropriedades().size(); i++) {
            CartaPropriedade p = jogador.getPropriedades().get(i);
            if (i > 0) {
                resultado = resultado + ", ";
            }
            // Usa getTipoImovel() e getNome() da nova classe
            resultado = resultado + p.getTipoImovel() + " " + p.getNome();
        }
        return resultado;
    }

    public static Color corDaCasa(Casa c) {
        if (c instanceof CasaFinanceira) {
            if (((CasaFinanceira) c).getTipoFinanceira() == CasaFinanceira.TipoFinanceira.GANHO) return new Color(102, 204, 102);
            else return new Color(255, 153, 153);
        }
        if (c instanceof CasaMovimento) {
            if (((CasaMovimento) c).getTipoMovimento() == CasaMovimento.TipoMovimento.AVANCAR) return new Color(102, 178, 255);
            else return new Color(255, 204, 102);
        }
        if (c instanceof CasaEvento) return new Color(255, 221, 102);
        if (c instanceof CasaEspecial) {
            CasaEspecial.TipoEspecial tipo = ((CasaEspecial) c).getTipoEspecial();
            if (tipo == CasaEspecial.TipoEspecial.SORTE)       return new Color(102, 255, 178);
            if (tipo == CasaEspecial.TipoEspecial.AZAR)        return new Color(255, 153, 153);
            if (tipo == CasaEspecial.TipoEspecial.PULAR_TURNO) return new Color(183, 183, 255);
            if (tipo == CasaEspecial.TipoEspecial.JOGAR_NOVAMENTE) return new Color(102, 255, 178);
        }
        return new Color(220, 220, 220);
    }

    public static void jogar() {
        if (jogadores.isEmpty() || tabuleiro == null || tabuleiro.getCasas().isEmpty()) return;
        if (animando) return;

        Jogador atual = jogadores.get(rodada.getJogadorAtual());

        if (!atual.isTurnoAtivo()) {
            JOptionPane.showMessageDialog(null,
                    atual.getNome() + " está com o turno pulado e perdeu a vez!",
                    "Turno Pulado", JOptionPane.WARNING_MESSAGE);
            atual.jogarNovamente();
            rodada.proximoTurno(jogadores);
            Jogador proximo = jogadores.get(rodada.getJogadorAtual());
            labelStatus.setText("Rodada " + rodada.getNumeroRodada() + " - Vez de: " + proximo.getNome());
            if (jogoPainel != null) jogoPainel.atualizarPainelDireito();
            return;
        }

        animando = true;

        int dado = roleta.girar();
        int novaCasa = Math.min(atual.getCasas() + dado, tabuleiro.getCasas().size() - 1);

        System.out.println(">>> " + atual.getNome() + " estava na Casa " + (atual.getCasas() + 1) +
                ". Tirou " + dado + " e vai para a Casa " + (novaCasa + 1));


        Timer timerRoleta = new Timer(60, null);
        int[] contadorGiros = {0}; 
        int maxGiros = 15; 

        timerRoleta.addActionListener(e -> {
            contadorGiros[0]++;
            
            if (contadorGiros[0] < maxGiros) {
                int numeroFalso = RANDOM.nextInt(6) + 1;
                lblRoleta.setText(String.valueOf(numeroFalso));
                lblRoleta.setBackground(new Color(220, 220, 220));
            } else {
                ((Timer)e.getSource()).stop();
                lblRoleta.setText(String.valueOf(dado));
                lblRoleta.setBackground(new Color(255, 255, 150));
                
                moverJogadorPassoAPasso(atual, novaCasa, () -> {
                    
                    Casa casa = tabuleiro.getCasa(novaCasa);
                    String instrucao = casa.getInstrucao();
                    
                    boolean ehEventoRuim = instrucao != null && (instrucao.contains("Multa") || instrucao.contains("Azar"));
                    
                    if (atual.temSeguro() && ehEventoRuim) {
                        atual.setSeguro(null);
                        JOptionPane.showMessageDialog(null,
                                "Seu seguro foi acionado!\nVocê está protegido desta vez.",
                                "Seguro Acionado!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        casa.aplicar(atual);
                    }

                    if ((novaCasa + 1) % 5 == 0) {
                        atual.receberSalario();
                        JOptionPane.showMessageDialog(null,
                                atual.getNome() + " chegou na Casa " + (novaCasa + 1) + " e recebeu salário!\n" +
                                "Salário: R$" + (int) atual.getSalario() +
                                "\nSaldo total: R$" + (int) atual.getPatrimonio(),
                                "Salário Recebido!", JOptionPane.INFORMATION_MESSAGE);
                    }

                    String mensagemExtra = "";
                    if (atual.getCasas() != novaCasa) {
                        atual.setCasas(Math.min(atual.getCasas(), tabuleiro.getCasas().size() - 1));
                        mensagemExtra = "\n\nEfeito da casa ativado! Você foi movido para a Casa " + (atual.getCasas() + 1) + ".";
                        System.out.println(">>> EFEITO APLICADO! " + atual.getNome() +
                                " foi arremessado para a Casa " + (atual.getCasas() + 1));
                    }

                    JOptionPane.showMessageDialog(null,
                            atual.getNome() + " tirou " + dado + " na roleta." +
                                    "\nCaiu na Casa " + (novaCasa + 1) + ": " + casa.getInstrucao() + mensagemExtra,
                            "Resultado da Jogada", JOptionPane.INFORMATION_MESSAGE);

                    if (jogoPainel != null) jogoPainel.atualizarPainelDireito();

                    if (atual.getCasas() >= tabuleiro.getCasas().size() - 1) {
                        JOptionPane.showMessageDialog(null,
                            atual.getNome() + " venceu!" +
                                "\nProfissão: " + atual.getProfissao().getNome() +
                                "\nPatrimônio final: R$" + (int) atual.getPatrimonio() +
                                "\nPropriedades: " + atual.getPropriedades().size() +
                                "\nLista: " + resumoPropriedades(atual),
                                "Fim de Jogo!", JOptionPane.INFORMATION_MESSAGE);
                        animando = false;
                        return;
                    }

                    lblRoleta.setBackground(Color.WHITE);
                    lblRoleta.setText(" ? ");
                    
                    rodada.proximoTurno(jogadores);
                    Jogador proximo = jogadores.get(rodada.getJogadorAtual());
                    labelStatus.setText("Rodada " + rodada.getNumeroRodada() + " - Vez de: " + proximo.getNome());
                    
                    if (jogoPainel != null) {
                        jogoPainel.atualizarPainelDireito();
                        jogoPainel.repintarTabuleiro();
                    }
                    
                    animando = false;
                });
            }
        });
        
        timerRoleta.start(); // Dá o play na roleta!
    }

    public static void salvarJogo() {
        try {
            Jogo jogo = new Jogo(jogadores, tabuleiro, rodada);
            Persistencia.salvar(jogo, "jogo.dat");
            JOptionPane.showMessageDialog(null, "Jogo saved com sucesso em 'jogo.dat'!");
        } 
        
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void carregarJogo() {
        try {
            Jogo jogo = Persistencia.carregar("jogo.dat");

            jogadores = jogo.getJogadores();
            tabuleiro = jogo.getTabuleiro();
            rodada = jogo.getRodada();

            criarJanelaJogo();

            Jogador proximo = jogadores.get(rodada.getJogadorAtual());
            labelStatus.setText("Rodada " + rodada.getNumeroRodada() + " - Vez de: " + proximo.getNome());

            if (jogoPainel != null) {
                jogoPainel.atualizarPainelDireito();
                jogoPainel.repintarTabuleiro();
            }

            JOptionPane.showMessageDialog(null, "Jogo carregado com sucesso!");
        } 
        
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
