public class Medico {
  private String nome;
  private String cpf;
  private String especialidade;

  public Medico(String nome, String cpf, String especialidade) {
    this.nome = nome;
    this.cpf = cpf;
    this.especialidade = especialidade;
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public String getEspecialidade() {
    return especialidade;
  }
}
