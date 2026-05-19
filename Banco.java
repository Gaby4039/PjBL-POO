// Criar função para distribuir dinheiro entre os jogadores a cada rodada
class banco {

  public void receba (double income){
    this.saldo += income;
  }

  public void tira (double outcome) {
    this.saldo -= outcome;
  }
}
  
