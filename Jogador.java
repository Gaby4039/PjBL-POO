import java.util.ArrayList;

public class Jogador implements Movimentavel {
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
    private boolean casamento;
    private boolean seguro;
    private int casas;
    private int turno;
    private boolean turnoAtivo;
    private ArrayList<Propriedade> propriedades;
    private ArrayList<Carta> cartas;

    public Jogador(String cor,
                   String nome,
                   boolean carreira,
                   boolean faculdade,
                   Profissao profissao,
                   Profissao novaProfissao,
                   boolean aposentadoria,
                   int filhos,
                   double salario,
                   double patrimonio,
                   boolean casamento,
                   boolean seguro,
                   int casas,
                   int turno) {
        this.cor = cor;
        this.nome = nome;
        this.carreira = carreira;
        this.faculdade = faculdade;
        this.profissao = profissao;
        this.novaProfissao = novaProfissao;
        this.aposentadoria = aposentadoria;
        this.filhos = filhos;
        this.salario = salario;
        this.patrimonio = patrimonio;
        this.casamento = casamento;
        this.seguro = seguro;
        this.casas = casas;
        this.turno = turno;
        this.turnoAtivo = true;
        this.propriedades = new ArrayList<>();
        this.cartas = new ArrayList<>();
    }

    public Jogador(String cor,
                   String nome,
                   boolean carreira,
                   Profissao profissao,
                   int filhos,
                   double salario,
                   double patrimonio,
                   boolean casamento,
                   boolean seguro,
                   int casas) {
        this(cor, nome, carreira, false, profissao, null, false, filhos, salario, patrimonio, casamento, seguro, casas, 0);
    }

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

    public Profissao getNovaProfissao() {
        return novaProfissao;
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

    public int getTurno() {
        return turno;
    }

    public boolean isTurnoAtivo() {
        return turnoAtivo;
    }

    public ArrayList<Propriedade> getPropriedades() {
        return propriedades;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

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

    public void setNovaProfissao(Profissao profissao) {
        this.novaProfissao = profissao;
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

    public void setCasas(int casas) {
        this.casas = casas;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setTurno(boolean turnoAtivo) {
        this.turnoAtivo = turnoAtivo;
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
    }

    public void trocarProfissao(Profissao novaProfissao) {
        if (novaProfissao != null) {
            this.profissao = novaProfissao;
        }
    }

    public void ganharDinheiro(double valor) {
        this.patrimonio += valor;
    }

    public void perderDinheiro(double valor) {
        this.patrimonio -= valor;
    }

    public void transferirDinheiro(Jogador destino, double valor) {
        if (destino == null) {
            return;
        }
        this.patrimonio -= valor;
        destino.patrimonio += valor;
    }

    public void receberSalario(double salario) {
        this.patrimonio += salario;
    }

    public void comprarPropriedade(Propriedade propriedade) {
        if (propriedade == null) {
            return;
        }
        this.patrimonio -= propriedade.getValorCompra();
        this.propriedades.add(propriedade);
    }

    public void venderPropriedade(Propriedade propriedade) {
        if (propriedade == null) {
            return;
        }
        if (this.propriedades.remove(propriedade)) {
            this.patrimonio += propriedade.getValorCompra();
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

    public void fazer_aniversario(double presente) {
        this.patrimonio += presente;
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
