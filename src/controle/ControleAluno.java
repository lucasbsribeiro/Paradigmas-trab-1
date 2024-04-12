package controle;

import DAO.AlunoDAO;
import academia.Aluno;

import java.sql.Date;
import java.util.Scanner;
public class ControleAluno {
    public static void cadastrarAlunos() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o CPF do novo aluno:");
        String cpf = scanner.nextLine();

        System.out.println("Digite o nome do novo aluno:");
        String nome = scanner.nextLine();

        System.out.println("Digite a data de nascimento do novo aluno:");
        String date = scanner.nextLine();
        Date data = Date.valueOf(date);

        Aluno aluno = new Aluno(nome, cpf, data);


        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.cadastrarAluno(aluno);
    }
}

