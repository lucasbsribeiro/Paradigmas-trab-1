package alal.src;

import controle.ControleAluno;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Selecione uma opcao:");
        System.out.println("1. Gerenciar alunos");

        Scanner scanner = new Scanner(System.in);

        int selected = scanner.nextInt();

        switch (selected) {
            case 1:
                gerenciarAlunos();
                break;
            case 2:
                System.out.println("ALAL");
                System.out.println("123");

                break;
            default:
                break;
        }
        scanner.close();
    }

    public static void gerenciarAlunos() {
        System.out.println("Selecione uma opcao:");
        System.out.println("1. Cadastrar aluno");

        Scanner scanner = new Scanner(System.in);

        int selected = scanner.nextInt();

        switch (selected) {
            case 1:
                ControleAluno.cadastrarAlunos();
                break;
            case 2:
                System.out.println("teste");
                break;
            default:
                break;
        }
        scanner.close();
    }
}
