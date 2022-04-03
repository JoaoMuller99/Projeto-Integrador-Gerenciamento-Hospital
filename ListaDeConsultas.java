import java.util.ArrayList;

public class ListaDeConsultas {
  ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();

  public void incluirNoInicio(Agendamento agendamento) {
    agendamentos.add(0, agendamento);
  }

  public void incluirNoFim(Agendamento agendamento) {
    agendamentos.add(agendamento);
  }

  public void removerDoFim(Agendamento agendamento) {
    agendamentos.remove(agendamento);
  }

  public int tamanho() {
    return agendamentos.size();
  }

  public Agendamento get(int indice) {
    return agendamentos.get(indice);
  }
}
