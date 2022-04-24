public class Paciente extends Pessoa {
  private String endereco;

  public Paciente(String nome, String cpf, String endereco) {
    super(nome, cpf);
    this.endereco = endereco;
  }

  public String getEndereco() {
    return endereco;
  }
}
