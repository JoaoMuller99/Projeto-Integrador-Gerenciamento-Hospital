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
}
