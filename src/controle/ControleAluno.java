package controle;

import DAO.AlunoDAO;
import academia.Aluno;
import java.sql.Date;
import java.util.Scanner;

import static DAO.AlunoDAO.inserirAlunoBD;

public class ControleAluno {
    public static void gerenciarAlunos() {
        System.out.println("Selecione uma opção:");
        System.out.println("1. Cadastrar aluno");

        Scanner scanner = new Scanner(System.in);

        int selected = scanner.nextInt();

        switch (selected) {
            case 1:
                cadastrarAlunos();
                break;
            case 2:
                break;
            default:
                break;
        }
        //scanner.close();
    }

    private static void cadastrarAlunos() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o CPF do novo aluno:");
        String cpf = scanner.nextLine();

        System.out.println("Digite o nome do novo aluno:");
        String nome = scanner.nextLine();

        System.out.println("Digite a data de nascimento do novo aluno (formato: YYYY-MM-DD):");
        String dateString = scanner.nextLine();
        java.sql.Date aniversario;
        try {
            aniversario = java.sql.Date.valueOf(dateString);
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de data inválido. Use o formato YYYY-MM-DD.");
            return;
        }

        Aluno aluno = new Aluno(nome, cpf, aniversario);

        inserirAlunoBD(aluno);
        System.out.println("Aluno cadastrado com sucesso.");

        //scanner.close();
    }
}

