public class Agendamento {
  int idAgendamento;
  Medico medico;
  Paciente paciente;
  String data;

  public Agendamento(int idAgendamento, Medico medico, Paciente paciente, String data) {
    this.idAgendamento = idAgendamento;
    this.medico = medico;
    this.paciente = paciente;
    this.data = data;
  }

  public int getIdAgendamento() {
    return idAgendamento;
  }

  public String getData() {
    return data;
  }

  public String toString() {
    String retorno = "";

    retorno += "------------------------------" + "\n";
    retorno += "Agendamento: " + this.getIdAgendamento() + "\n";
    retorno += "Médico: " + this.medico.getNome() + "\n";
    retorno += "Paciente: " + this.paciente.getNome() + "\n";
    retorno += "Data: " + this.getData() + "\n";
    retorno += "------------------------------";

    return retorno;
  }
}
