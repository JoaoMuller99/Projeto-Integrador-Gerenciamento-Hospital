public class Paciente extends Pessoa {
  private String endereco;

  public Paciente(String nome, String cpf, String endereco) {
    super(nome, cpf);
    this.endereco = endereco;
  }

  public String getEndereco() {
    return endereco;
  }

  public String toString() {
    String retorno = "";

    retorno += "------------------------------" + "\n";
    retorno += "Nome: " + getNome() + "\n";
    retorno += "CPF: " + getCpf() + "\n";
    retorno += "Endereço: " + getEndereco() + "\n";
    retorno += "------------------------------";

    return retorno;
  }
}
