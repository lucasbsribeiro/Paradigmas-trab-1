import controle.ControleAluno;
import controle.ControleExercicio;
import controle.ControleMusculo;
import controle.ControlePlano;

import java.util.Scanner;

import conexao.Conexao;

public class Main {
    public static void main(String[] args) {
        Conexao.definicoesDoBanco();
        System.out.println("Selecione uma opcao:");
        System.out.println("1. Gerenciar alunos");
        System.out.println("2. Gerenciar planos");
        System.out.println("3. Gerenciar exercícios");
        System.out.println("4. Gerenciar músculos");

        Scanner scanner = new Scanner(System.in);

        int selected = scanner.nextInt();

        switch (selected) {
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
            default:
                break;
        }
        scanner.close();
    }
}
