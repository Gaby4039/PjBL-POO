import java.util.ArrayList;

public class Jogador {
    private String cor;
    private String nome;
    private boolean carreira;
    private boolean faculdade;
    private Profissao profissao;
    private Profissao novaProfissao;
    private boolean aposentadoria;
    private int filhos;
    private double salario;
    private double patrimonio;
    private boolean casamento; // true or false
    private boolean seguro; // true or false
    private int casas;
    private ArrayList<Propriedade> propriedades;
    private ArrayList<Carta> cartas;

    public Jogador(String cor, 
                String nome,
                boolean carreira,
                boolean faculdade, 
                Profissao profissao,
                boolean aposentadoria,
                int filhos, 
                double salario,
                double patrimonio,
                boolean casamento,
                boolean seguro,
                int casas,
                int posicao) {
    }

    // -------- GETTERS E SETTERS --------

    // ---- GETTERS ----

    public String getCor() {
        return cor;
    }

    public String getNome() {
        return nome;
    }

    public boolean getCarreira() {
        return carreira;
    }

    public boolean getFaculdade() {
        return faculdade;
    }

    public Profissao getProfissao() {
        return profissao;
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

    public boolean getCasamento() {
        return casamento;
    }

    public boolean getSeguro() {
        return seguro;
    }

    public int getCasas() {
        return casas;
    }

    public ArrayList<Propriedade> getPropriedades() {
        return propriedades;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    // ---- SETTERS ----
    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCarreira(boolean carreira) {
        this.carreira = carreira;
    }

    public void setFaculdade(boolean faculdade) {
        this.faculdade = faculdade;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public void setFilhos(int filhos) {
        this.filhos = filhos;
    }

     public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setPatrimonio(double patrimonio) {
        this.patrimonio = patrimonio;
    }

    public void setCasamento(boolean casamento) {
        this.casamento = casamento;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public void avancarCasas(int casas) {
        this.casas += casas;
    }

    public void voltarCasas(int casas) {
        this.casas -= casas;
    }

    public void escolherProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public void trocarProfissao(Profissao novaProfissao) {
        this.profissao = novaProfissao;
    }

    public void ganharDinheiro(double valor) {
        this.patrimonio += valor;
    }

    public void perderDinheiro(double valor) {
        this.patrimonio -= valor;
    }

    public void transferirDinheiro(Jogador destino, double valor) {
        this.patrimonio -= valor;
        destino.patrimonio += valor;
    }

    public void receberSalario() {
        //saldo += Profissao.getSalario();
    }

    public void comprarPropriedade(Propriedade propriedade) {
        //this.patrimonio -= propriedade.getValor();
        this.propriedades.add(propriedade);
    }

    public void venderPropriedade(Propriedade propriedade) {
        this.propriedades.remove(propriedade);
        //this.patrimonio += Propriedade.getValor();
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

    public void fazer_aniversario(double presente) {
        this.patrimonio += presente;
    }

    public void promocao(double novoSalario) {
        this.salario = novoSalario;
    }

    public void aposentar() {
        this.aposentadoria = true;
    }

}