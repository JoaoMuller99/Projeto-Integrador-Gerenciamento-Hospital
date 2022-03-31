import java.util.ArrayList;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {

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
          medicos.add(cadastrarMedico());
          break;

        case 2:
          pacientes.add(cadastrarPaciente());
          break;

        case 3:
          criaAgendamento(medicos, pacientes, agendamentos);
          break;

        case 4:
          System.out.println("\nOpção 4 selecionada");
          break;

        case 5:
          System.out.println("\nOpção 5 selecionada");
          break;

        case 6:
          listarMedicos(medicos);
          break;

        case 7:
          listarPacientes(pacientes);
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

  }

  private static Medico cadastrarMedico() {
    Scanner leitorString = new Scanner(System.in);

    System.out.println("\nDigite o nome do médico:");
    String nome = leitorString.nextLine();

    System.out.println("\nDigite o CPF do médico:");
    String cpf = leitorString.nextLine();

    System.out.println("\nDigite a especialidade do médico:");
    String especialidade = leitorString.nextLine();

    return new Medico(nome, cpf, especialidade);
  }

  private static Paciente cadastrarPaciente() {
    Scanner leitorString = new Scanner(System.in);

    System.out.println("\nDigite o nome do paciente:");
    String nome = leitorString.nextLine();

    System.out.println("\nDigite o CPF do paciente:");
    String cpf = leitorString.nextLine();

    System.out.println("\nDigite o endereço do paciente:");
    String endereco = leitorString.nextLine();

    return new Paciente(nome, cpf, endereco);
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private static void criaAgendamento(ArrayList<Medico> medicos, ArrayList<Paciente> pacientes,
      ArrayList<Agendamento> agendamentos) {

  }

  private static void listarMedicos(ArrayList<Medico> medicos) {
    Scanner leitorString = new Scanner(System.in);

    System.out.println("\nLista de médicos cadastrados:");
    for (Medico medico : medicos) {
      System.out.println("------------------------------");
      System.out.println("Nome: " + medico.getNome());
      System.out.println("CPF: " + medico.getCpf());
      System.out.println("Especialidade: " + medico.getEspecialidade());
      System.out.println("------------------------------");
    }
    System.out.println("Pressione ENTER para continuar...");
    leitorString.nextLine();
  }

  private static void listarPacientes(ArrayList<Paciente> pacientes) {
    Scanner leitorString = new Scanner(System.in);

    System.out.println("\nLista de pacientes cadastrados:");
    for (Paciente paciente : pacientes) {
      System.out.println("------------------------------");
      System.out.println("Nome: " + paciente.getNome());
      System.out.println("CPF: " + paciente.getCpf());
      System.out.println("Endereço: " + paciente.getEndereco());
      System.out.println("------------------------------");
    }
    System.out.println("Pressione ENTER para continuar...");
    leitorString.nextLine();
  }
}
