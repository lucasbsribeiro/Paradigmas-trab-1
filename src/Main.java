import controle.*;

import java.util.Scanner;

import conexao.Conexao;

public class Main {
    public static void main(String[] args) {
        Conexao.definicoesDoBanco();
        Scanner scanner = new Scanner(System.in);
        int selecao = 99;
        while(selecao != 0) {
            System.out.println("Selecione uma opcao:");
            System.out.println("0. Sair");
            System.out.println("1. Gerenciar alunos");
            System.out.println("2. Gerenciar planos");
            System.out.println("3. Gerenciar exercícios");
            System.out.println("4. Gerenciar músculos");
            System.out.println("5. Gerenciar planos de alunos");

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
            }
        }
        scanner.close();
    }
}
