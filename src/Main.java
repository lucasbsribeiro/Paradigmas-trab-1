import controle.*;

import java.util.Scanner;

import conexao.Conexao;

public class Main {
    public static void main(String[] args) {
        Conexao.definicoesDoBanco();
        Scanner scanner = new Scanner(System.in);
        int selecao = 99;
        int modo = 99;

        while (modo != 0) {
            System.out.println();
            System.out.println("SISTEMA ACADEMIA");
            System.out.println("Selecione uma opcao:");
            System.out.println("0. Sair");
            System.out.println("1. Instrutor");
            System.out.println("2. Aluno");

            modo = scanner.nextInt();

            switch (modo) {
                case 1:
                    opcoesInstrutor();
                    break;
                case 2:
                    opcoesAluno();
                    break;
                default:
                    break;
            }
        }
    }
    private static void opcoesInstrutor() {
        Scanner scanner = new Scanner(System.in);
        int selecao = 99;

        while (selecao != 0) {
            System.out.println();
            System.out.println("Selecione uma opcao:");
            System.out.println("0. Sair");
            System.out.println("1. Gerenciar alunos");
            System.out.println("2. Gerenciar planos");
            System.out.println("3. Gerenciar exercícios");
            System.out.println("4. Gerenciar músculos");
            System.out.println("5. Gerenciar planos de alunos");
            System.out.println("6. Gerenciar treinos");

            selecao = scanner.nextInt();

            switch (selecao) {
                case 1:
                    ControleAluno.gerenciarAlunos();
                    break;
                case 2:
                    ControlePlano.gerenciarPlanos();
                    break;
                case 3:
                    ControleExercicio.gerenciarExercicios();
                    break;
                case 4:
                    ControleMusculo.gerenciarMusculos();
                    break;
                case 5:
                    ControleAlunoPlano.gerenciarAlunoPlano();
                    break;
                case 6:
                    ControleTreino.gerenciarTreino();
                    break;
                default:
                    break;
            }
        }
    }
    private static void opcoesAluno() {
        Scanner scanner = new Scanner(System.in);
        int selecao = 99;

        while (selecao != 0) {
            System.out.println("Selecione uma opcao:");
            System.out.println("0. Sair");
            System.out.println("1. Realizar treino");
            System.out.println("2. Relatórios");

            selecao = scanner.nextInt();

            switch (selecao) {
                case 1:
                    ControleTreinoDiario.gerenciarTreinoDiario();
                    break;
                case 2:
                    ControleRelatorios.gerenciarRelatorios();
                default:
                    break;
            }
        }
    }
}
