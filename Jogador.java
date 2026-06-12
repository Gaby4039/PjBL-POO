import java.util.ArrayList;
import java.io.Serializable;

public class Jogador implements Movimentavel, Serializable {
    private String cor;
    private String nome;
    private Profissao profissao;
    private boolean faculdade;
    private boolean aposentadoria = false;
    private int filhos = 0;
    private double salario;
    private double patrimonio = 10000.00;
    private boolean casamento = false;
    private boolean seguro = false;
    private int casas = 0;
    private boolean turnoAtivo = true;
    private final ArrayList<Propriedade> propriedades;
    private final ArrayList<Carta> cartas;

    public Jogador(String cor,
                   String nome,
                ) {
        this.cor = cor;
        this.nome = nome;
        this.salario = profissao.getSalario();
        this.propriedades = new ArrayList<>();
        this.cartas = new ArrayList<>();
    }

    public String getCor() {
        return cor;
    }

    public String getNome() {
        return nome;
    }

    public int getFilhos() {
        return filhos;
    }

    public double getSalario() {
        return salario;
    }

    public double getPatrimonio() {
        return patrimonio;
    }

    public boolean isCasado() {
        return casamento;
    }

    public boolean temSeguro() {
        return seguro;
    }

    public int getCasas() {
        return casas;
    }

    public boolean isTurnoAtivo() {
        return turnoAtivo;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public boolean temFaculdade() {
        return faculdade;
    }

    public boolean isAposentado() {
        return aposentadoria;
    }

    public ArrayList<Propriedade> getPropriedades() {
        return propriedades;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public void avancarCasas(int casas) {
        this.casas += casas;
        if (this.casas < 0) {
            this.casas = 0;
        }
    }

    public void voltarCasas(int casas) {
        this.casas -= casas;
        if (this.casas < 0) {
            this.casas = 0;
        }
    }

    public void escolherProfissao(Profissao profissao) {
        this.profissao = profissao;
        this.salario = profissao.getSalario();
    }

    public void ganharDinheiro(double valor) {
        this.patrimonio += valor;
    }

    public void perderDinheiro(double valor) {
        this.patrimonio -= valor;
    }

    public void transferirDinheiro(Jogador destino, double valor) {
        if(destino == null) {
            return;
        }

        perderDinheiro(valor);
        destino.ganharDinheiro(valor);
    }

    public void receberSalario() {
        this.patrimonio += this.salario;
    }

    public void comprarPropriedade(Propriedade propriedade) {
        if (propriedade == null) {
            return;
        }
        perderDinheiro(propriedade.getValorCompra());
        this.propriedades.add(propriedade);
    }

    public void venderPropriedade(Propriedade propriedade) {
        if (propriedade == null) {
            return;
        }
        if (this.propriedades.remove(propriedade)) {
            ganharDinheiro(propriedade.getValorCompra());
        }
    }

    public void casar() {
        this.casamento = true;
    }

    public void divorciar() {
        this.casamento = false;
    }

    public void terFilho() {
        this.filhos += 1;
    }

    public void fazerAniversario(double valor) {
        this.patrimonio += valor;
    }

    public void promocao(double novoSalario) {
        this.salario = novoSalario;
    }

    public void pularTurno() {
        this.turnoAtivo = false;
    }

    public void jogarNovamente() {
        this.turnoAtivo = true;
    }

    public void aposentar() {
        this.aposentadoria = true;
    }
}
