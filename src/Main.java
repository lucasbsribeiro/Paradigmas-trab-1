import controle.ControleAluno;
import controle.ControlePlano;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Selecione uma opcao:");
        System.out.println("1. Gerenciar alunos");
        System.out.println("2. Gerenciar planos");

        Scanner scanner = new Scanner(System.in);

        int selected = scanner.nextInt();

        switch (selected) {
            case 1:
                ControleAluno.gerenciarAlunos();
                break;
            case 2:
                ControlePlano.gerenciarPlanos();
                break;
            default:
                break;
        }
        scanner.close();
    }


}
