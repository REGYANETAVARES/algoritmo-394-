/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vetor42;

import java.util.Scanner;

public class GerenciadorDeAlunos {
    private static final int MAX_ALUNOS = 50;
    private String[] nomes = new String[MAX_ALUNOS];
    private double[] nota1 = new double[MAX_ALUNOS];
    private double[] nota2 = new double[MAX_ALUNOS];
    private double[] media = new double[MAX_ALUNOS];
    private boolean[] cadastrado = new boolean[MAX_ALUNOS];
    private boolean notas1Inseridas = false;
    private boolean notas2Inseridas = false;

    public void executar() {
        Scanner scanner = new Scanner(System.in);
        String opcao;

        do {
            System.out.println("\nMENU");
            System.out.println("1 - ENTRAR NOMES");
            System.out.println("2 - ENTRAR 1ª NOTA");
            System.out.println("3 - ENTRAR 2ª NOTA");
            System.out.println("4 - CALCULAR MÉDIA");
            System.out.println("5 - LISTAR NO DISPLAY");
            System.out.println("6 - SAIR");
            System.out.print("OPÇÃO: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    cadastrarNomes(scanner);
                    break;
                case "2":
                    cadastrarNota(scanner, 1);
                    break;
                case "3":
                    cadastrarNota(scanner, 2);
                    break;
                case "4":
                    calcularMedias();
                    break;
                case "5":
                    listarAlunos();
                    break;
                case "6":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }
        } while (!opcao.equals("6"));

        scanner.close();
    }

    private void cadastrarNomes(Scanner scanner) {
        for (int i = 0; i < MAX_ALUNOS; i++) {
            System.out.print("Digite o nome do aluno " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            while (nome.length() > 30) {
                System.out.println("Nome deve ter no máximo 30 caracteres.");
                System.out.print("Digite o nome do aluno " + (i + 1) + ": ");
                nome = scanner.nextLine();
            }
            nomes[i] = nome;
            cadastrado[i] = true;
        }
        System.out.println("Nomes cadastrados com sucesso!");
    }

    private void cadastrarNota(Scanner scanner, int notaNumero) {
        if (!verificaNomesCadastrados()) {
            System.out.println("NENHUM NOME CADASTRADO.");
            return;
        }

        for (int i = 0; i < MAX_ALUNOS; i++) {
            System.out.print("Digite a " + notaNumero + "ª nota do aluno " + nomes[i] + ": ");
            if (notaNumero == 1) {
                nota1[i] = scanner.nextDouble();
                notas1Inseridas = true;
            } else if (notaNumero == 2) {
                nota2[i] = scanner.nextDouble();
                notas2Inseridas = true;
            }
        }
        scanner.nextLine(); // Limpa o buffer
        System.out.println("Notas " + notaNumero + " cadastradas com sucesso!");
    }

    private void calcularMedias() {
        if (!notas1Inseridas || !notas2Inseridas) {
            System.out.println("NOTAS INCOMPLETAS.");
            return;
        }

        for (int i = 0; i < MAX_ALUNOS; i++) {
            media[i] = (3 * nota1[i] + 7 * nota2[i]) / 10;
        }
        System.out.println("Médias calculadas com sucesso!");
    }

    private void listarAlunos() {
        if (!notas1Inseridas || !notas2Inseridas) {
            System.out.println("NOTAS INCOMPLETAS.");
            return;
        }

        System.out.printf("\n%-30s %-10s %-10s %-10s\n", "NOME", "NOTA 1", "NOTA 2", "MÉDIA");
        for (int i = 0; i < MAX_ALUNOS; i++) {
            System.out.printf("%-30s %-10.2f %-10.2f %-10.2f\n", nomes[i], nota1[i], nota2[i], media[i]);
        }
    }

    private boolean verificaNomesCadastrados() {
        for (boolean cadastrado : cadastrado) {
            if (cadastrado) return true;
        }
        return false;
    }
}
