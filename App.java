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
    ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();

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
          criaAgendamento(medicos, pacientes, agendamentos, leitorString);
          break;

        case 4:
          System.out.println("\nOpção 4 selecionada");
          break;

        case 5:
          System.out.println("\nOpção 5 selecionada");
          break;

        case 6:
          listarMedicos(medicos, leitorString);
          break;

        case 7:
          listarPacientes(pacientes, leitorString);
          break;

        case 8:
          System.out.println("\nOpção 8 selecionada");
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

    System.out.println("\nDigite o CPF do médico:");
    String cpf = leitorString.nextLine();

    System.out.println("\nDigite a especialidade do médico:");
    String especialidade = leitorString.nextLine();

    medicos.add(new Medico(nome, cpf, especialidade));
  }

  private static void cadastrarPaciente(ArrayList<Paciente> pacientes, Scanner leitorString) {
    System.out.println("\n***Novo cadastro de paciente***");

    System.out.println("\nDigite o nome do paciente:");
    String nome = leitorString.nextLine();

    System.out.println("\nDigite o CPF do paciente:");
    String cpf = leitorString.nextLine();

    System.out.println("\nDigite o endereço do paciente:");
    String endereco = leitorString.nextLine();

    pacientes.add(new Paciente(nome, cpf, endereco));
  }

  private static void criaAgendamento(ArrayList<Medico> medicos, ArrayList<Paciente> pacientes,
      ArrayList<Agendamento> agendamentos, Scanner leitorString) {
    System.out.println("\n***Novo agendamento***");

    System.out.println("\nDigite o nome do paciente:");
    String nomePaciente = leitorString.nextLine();

    boolean pacienteExiste = validaPaciente(pacientes, nomePaciente, leitorString);

    if (pacienteExiste) {
      Paciente pacienteEscolhido = null;

      for (Paciente paciente : pacientes) {
        if (paciente.getNome().equals(nomePaciente)) {
          pacienteEscolhido = paciente;
        }
      }

      System.out.println("\nDigite a especialidade procurada:");
      String especialidade = leitorString.nextLine();

      boolean medicoExiste = validaMedico(medicos, especialidade, leitorString);

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

        boolean nomeCorreto = validaMedico(listaMedicosFiltrada, nomeMedico, leitorString);

        if (nomeCorreto) {
          Medico medicoEscolhido = null;

          for (Medico medico : listaMedicosFiltrada) {
            if (medico.getNome().equals(nomeCorreto)) {
              medicoEscolhido = medico;
            }
          }

          System.out.println("\nDigite a data da consulta: (dd/mm/aaaa-hh:mm)");
          String dataConsulta = leitorString.nextLine();

          agendamentos.add(new Agendamento(agendamentos.size() + 1, medicoEscolhido, pacienteEscolhido, dataConsulta));
        }
      }
    }
  }

  private static boolean validaPaciente(ArrayList<Paciente> pacientes, String nomePaciente, Scanner leitorString) {
    boolean pacienteExiste = false;
    for (Paciente paciente : pacientes) {
      if (paciente.getNome().equals(nomePaciente)) {
        pacienteExiste = true;
      }
    }
    if (!pacienteExiste) {
      System.out.println("\nPaciente não cadastrado!");
      System.out.println("\nPressione ENTER para continuar...");
      leitorString.nextLine();
    }
    return pacienteExiste;
  }

  private static boolean validaMedico(ArrayList<Medico> medicos, String info, Scanner leitorString) {
    boolean medicoExiste = false;
    for (Medico medico : medicos) {
      if (medico.getEspecialidade().equals(info) || medico.getNome().equals(info)) {
        medicoExiste = true;
      }
    }
    if (!medicoExiste) {
      System.out.println("\nNenhum médico disponível!");
      System.out.println("\nPressione ENTER para continuar...");
      leitorString.nextLine();
    }
    return medicoExiste;
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
}
