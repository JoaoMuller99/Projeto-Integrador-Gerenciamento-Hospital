import java.util.ArrayList;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {

    Scanner leitorString = new Scanner(System.in);
    Scanner leitorInt = new Scanner(System.in);

    int opcaoEscolhida = 0;

    // Criando um ArrayList de Médicos
    ArrayList<Medico> medicos = new ArrayList<Medico>();

    // Criando um ArrayList de Pacientes
    ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

    // Criando um ArrayList de Agendamentos
    ListaDeConsultas listaDeConsultas = new ListaDeConsultas();

    while (opcaoEscolhida != 9) {
      clearScreen();
      // Menu
      System.out.println("\n*** Escolha uma das opções abaixo ***");
      System.out.println("\n1 - Cadastrar médico");
      System.out.println("\n2 - Cadastrar paciente");
      System.out.println("\n3 - Novo agendamento");
      System.out.println("\n4 - Consultar agendamentos - por médico");
      System.out.println("\n5 - Consultar agendamentos - por paciente");
      System.out.println("\n6 - Listar médicos cadastrados");
      System.out.println("\n7 - Listar pacientes cadastrados");
      System.out.println("\n8 - Listar consultas agendadas");
      System.out.println("\n9 - Sair");

      // Escolha da opção, pelo usuário
      opcaoEscolhida = leitorInt.nextInt();

      switch (opcaoEscolhida) {
        case 1:
          cadastrarMedico(medicos, leitorString);
          break;

        case 2:
          cadastrarPaciente(pacientes, leitorString);
          break;

        case 3:
          criaAgendamento(medicos, pacientes, listaDeConsultas, leitorString);
          break;

        case 4:
          consultarAgendamentosPorMedico(medicos, listaDeConsultas, leitorString);
          break;

        case 5:
          consultarAgendamentosPorPaciente(pacientes, listaDeConsultas, leitorString);
          break;

        case 6:
          listarMedicos(medicos, leitorString);
          break;

        case 7:
          listarPacientes(pacientes, leitorString);
          break;

        case 8:
          listarConsultas(listaDeConsultas, leitorString);
          break;

        case 9:
          System.out.println("\nSaindo...");
          break;

        default:
          System.out.println("\nOpção inválida! Tente novamente.");
          break;
      }
    }

    leitorString.close();
    leitorInt.close();
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private static void cadastrarMedico(ArrayList<Medico> medicos, Scanner leitorString) {
    System.out.println("\n***Novo cadastro de médico***");

    System.out.println("\nDigite o nome do médico:");
    String nome = leitorString.nextLine();

    boolean medicoJaCadastrado = validaMedico(medicos, nome, false, leitorString);

    if (!medicoJaCadastrado) {
      System.out.println("\nDigite o CPF do médico:");
      String cpf = leitorString.nextLine();

      System.out.println("\nDigite a especialidade do médico:");
      String especialidade = leitorString.nextLine();

      medicos.add(new Medico(nome, cpf, especialidade));
      System.out.println("\nMédico cadastrado com sucesso!");
    } else {
      System.out.println("\nMédico já cadastrado!");
    }
    System.out.println("\nPressione ENTER para continuar...");
    leitorString.nextLine();
  }

  private static void cadastrarPaciente(ArrayList<Paciente> pacientes, Scanner leitorString) {
    System.out.println("\n***Novo cadastro de paciente***");

    System.out.println("\nDigite o nome do paciente:");
    String nome = leitorString.nextLine();

    boolean pacienteJaCadastrado = validaPaciente(pacientes, nome, false, leitorString);

    if (!pacienteJaCadastrado) {
      System.out.println("\nDigite o CPF do paciente:");
      String cpf = leitorString.nextLine();

      System.out.println("\nDigite o endereço do paciente:");
      String endereco = leitorString.nextLine();

      pacientes.add(new Paciente(nome, cpf, endereco));
      System.out.println("\nPaciente cadastrado com sucesso!");
    } else {
      System.out.println("\nPaciente já cadastrado!");
    }
    System.out.println("\nPressione ENTER para continuar...");
    leitorString.nextLine();
  }

  private static void criaAgendamento(ArrayList<Medico> medicos, ArrayList<Paciente> pacientes,
      ListaDeConsultas listaDeConsultas, Scanner leitorString) {
    System.out.println("\n***Novo agendamento***");

    System.out.println("\nDigite o nome do paciente:");
    String nomePaciente = leitorString.nextLine();

    boolean pacienteExiste = validaPaciente(pacientes, nomePaciente, true, leitorString);

    if (pacienteExiste) {
      Paciente pacienteEscolhido = null;

      for (Paciente paciente : pacientes) {
        if (paciente.getNome().equals(nomePaciente)) {
          pacienteEscolhido = paciente;
        }
      }

      System.out.println("\nDigite a especialidade procurada:");
      String especialidade = leitorString.nextLine();

      boolean medicoExiste = validaMedico(medicos, especialidade, true, leitorString);

      if (medicoExiste) {
        ArrayList<Medico> listaMedicosFiltrada = new ArrayList<Medico>();
        for (Medico medico : medicos) {
          if (medico.getEspecialidade().equals(especialidade)) {
            listaMedicosFiltrada.add(medico);
          }
        }
        listarMedicos(listaMedicosFiltrada, leitorString);

        System.out.println("\nDigite o nome do médico escolhido:");
        String nomeMedico = leitorString.nextLine();

        boolean nomeCorreto = validaMedico(listaMedicosFiltrada, nomeMedico, true, leitorString);

        if (nomeCorreto) {
          Medico medicoEscolhido = null;

          for (Medico medico : listaMedicosFiltrada) {
            if (medico.getNome().equals(nomeMedico)) {
              medicoEscolhido = medico;
            }
          }

          boolean continuarDigitacaoData = true;
          String dataConsulta = "";

          while (continuarDigitacaoData) {
            System.out.println("\nDigite a data da consulta: (dd/mm/aaaa hh:mm)");
            dataConsulta = leitorString.nextLine();

            boolean formatoValido = dataConsulta.matches("([0-9]{2})/([0-9]{2})/([0-9]{4}) ([0-9]{2}):([0-9]{2})");

            if (formatoValido) {
              continuarDigitacaoData = false;
            } else {
              System.out.println("\nFormato inválido! Tente novamente.");
              System.out.println("\nPressione ENTER para continuar...");
              leitorString.nextLine();
            }
          }

          listaDeConsultas
              .incluirNoFim(
                  new Agendamento(listaDeConsultas.tamanho() + 1, medicoEscolhido, pacienteEscolhido, dataConsulta));
        }
      }
    }
  }

  private static void consultarAgendamentosPorMedico(ArrayList<Medico> medicos, ListaDeConsultas listaDeConsultas,
      Scanner leitorString) {
    System.out.println("\n*** Consultar agendamentos por médico ***");
    System.out.println("\nDigite o nome do médico: ");
    String nomeMedico = leitorString.nextLine();

    boolean medicoEncontrado = validaMedico(medicos, nomeMedico, true, leitorString);

    if (medicoEncontrado) {
      System.out.println("\n*** Consultas do médico " + nomeMedico + " ***");

      for (Agendamento agendamento : listaDeConsultas.agendamentos) {
        if (agendamento.medico.getNome().equals(nomeMedico)) {
          System.out.println("\n------------------------------");
          System.out.println("Agendamento: " + agendamento.idAgendamento);
          System.out.println("Paciente: " + agendamento.paciente.getNome());
          System.out.println("Data: " + agendamento.data);
          System.out.println("------------------------------");
        }
      }

      System.out.println("\nPressione ENTER para continuar...");
      leitorString.nextLine();
    }
  }

  private static void consultarAgendamentosPorPaciente(ArrayList<Paciente> pacientes,
      ListaDeConsultas listaDeConsultas, Scanner leitorString) {
    System.out.println("\n*** Consultar agendamentos por paciente ***");
    System.out.println("\nDigite o nome do paciente: ");
    String nomePaciente = leitorString.nextLine();

    boolean pacienteEncontrado = validaPaciente(pacientes, nomePaciente, true, leitorString);

    if (pacienteEncontrado) {
      System.out.println("\n*** Consultas do paciente " + nomePaciente + " ***");

      for (Agendamento agendamento : listaDeConsultas.agendamentos) {
        if (agendamento.paciente.getNome().equals(nomePaciente)) {
          System.out.println("\n------------------------------");
          System.out.println("Agendamento: " + agendamento.idAgendamento);
          System.out.println("Médico: " + agendamento.medico.getNome());
          System.out.println("Data: " + agendamento.data);
          System.out.println("------------------------------");
        }
      }

      System.out.println("\nPressione ENTER para continuar...");
      leitorString.nextLine();
    }
  }

  private static void listarMedicos(ArrayList<Medico> medicos, Scanner leitorString) {
    System.out.println("\nLista de médicos cadastrados:");
    if (medicos.size() == 0) {
      System.out.println("\nNenhum médico cadastrado!");
    } else {
      for (Medico medico : medicos) {
        System.out.println("------------------------------");
        System.out.println("Nome: " + medico.getNome());
        System.out.println("CPF: " + medico.getCpf());
        System.out.println("Especialidade: " + medico.getEspecialidade());
        System.out.println("------------------------------");
      }
    }
    System.out.println("\nPressione ENTER para continuar...");
    leitorString.nextLine();
  }

  private static void listarPacientes(ArrayList<Paciente> pacientes, Scanner leitorString) {
    System.out.println("\nLista de pacientes cadastrados:");
    if (pacientes.size() == 0) {
      System.out.println("\nNenhum paciente cadastrado!");
    } else {
      for (Paciente paciente : pacientes) {
        System.out.println("------------------------------");
        System.out.println("Nome: " + paciente.getNome());
        System.out.println("CPF: " + paciente.getCpf());
        System.out.println("Endereço: " + paciente.getEndereco());
        System.out.println("------------------------------");
      }
    }
    System.out.println("\nPressione ENTER para continuar...");
    leitorString.nextLine();
  }

  private static void listarConsultas(ListaDeConsultas listaDeConsultas, Scanner leitorString) {
    System.out.println("\nLista de consultas:");
    if (listaDeConsultas.tamanho() == 0) {
      System.out.println("\nNenhuma consulta agendada!");
    } else {
      listaDeConsultas.ordenar();
      for (int i = 0; i < listaDeConsultas.tamanho(); i++) {
        System.out.println("\n------------------------------");
        System.out.println("Agendamento: " + listaDeConsultas.get(i).idAgendamento);
        System.out.println("Médico: " + listaDeConsultas.get(i).medico.getNome());
        System.out.println("Paciente: " + listaDeConsultas.get(i).paciente.getNome());
        System.out.println("Data: " + listaDeConsultas.get(i).data);
        System.out.println("------------------------------");
      }
    }
    System.out.println("\nPressione ENTER para continuar...");
    leitorString.nextLine();
  }

  private static boolean validaPaciente(ArrayList<Paciente> pacientes, String nomePaciente,
      boolean printaMensagemNaoCadastrado, Scanner leitorString) {
    boolean pacienteExiste = false;
    for (Paciente paciente : pacientes) {
      if (paciente.getNome().equals(nomePaciente)) {
        pacienteExiste = true;
      }
    }
    if (!pacienteExiste && printaMensagemNaoCadastrado) {
      System.out.println("\nPaciente não cadastrado!");
      System.out.println("\nPressione ENTER para continuar...");
      leitorString.nextLine();
    }
    return pacienteExiste;
  }

  private static boolean validaMedico(ArrayList<Medico> medicos, String info, boolean printaMensagemNaoCadastrado,
      Scanner leitorString) {
    boolean medicoExiste = false;
    for (Medico medico : medicos) {
      if (medico.getEspecialidade().equals(info) || medico.getNome().equals(info)) {
        medicoExiste = true;
      }
    }
    if (!medicoExiste && printaMensagemNaoCadastrado) {
      System.out.println("\nNenhum médico disponível!");
      System.out.println("\nPressione ENTER para continuar...");
      leitorString.nextLine();
    }
    return medicoExiste;
  }
}
