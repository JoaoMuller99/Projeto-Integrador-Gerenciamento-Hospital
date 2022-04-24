import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

  public void set(int indice, Agendamento agendamento) {
    agendamentos.set(indice, agendamento);
  }

  public Agendamento get(int indice) {
    return agendamentos.get(indice);
  }

  public void ordenar() {
    // Ordenação por bolha
    for (int i = 0; i < agendamentos.size() - 1; ++i) {
      for (int j = 0; j < agendamentos.size() - i - 1; ++j) {

        try {
          Date data1 = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(agendamentos.get(j).data);
          Date data2 = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(agendamentos.get(j + 1).data);

          if (data1.compareTo(data2) > 0) {
            Agendamento swap = agendamentos.get(j);
            agendamentos.set(j, agendamentos.get(j + 1));
            agendamentos.set(j + 1, swap);
          }
        } catch (ParseException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
