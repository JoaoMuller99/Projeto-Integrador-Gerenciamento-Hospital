public class Medico extends Pessoa {
  private String especialidade;

  public Medico(String nome, String cpf, String especialidade) {
    super(nome, cpf);
    this.especialidade = especialidade;
  }

  public String getEspecialidade() {
    return especialidade;
  }

  public String toString() {
    String retorno = "";

    retorno += "------------------------------" + "\n";
    retorno += "Nome: " + getNome() + "\n";
    retorno += "CPF: " + getCpf() + "\n";
    retorno += "Especialidade: " + getEspecialidade() + "\n";
    retorno += "------------------------------";

    return retorno;
  }
}
