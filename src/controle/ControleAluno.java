package controle;

import DAO.AlunoDAO;
import DAO.ExercicioDAO;
import DAO.PlanoAlunoDAO;
import DAO.TreinoDiarioDAO;
import academia.Aluno;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static DAO.AlunoDAO.*;
import static DAO.PlanoAlunoDAO.*;
import static conexao.Conexao.query;

import conexao.Conexao;

public class ControleAluno {
    public static void gerenciarAlunos() {
        int selecao = 99;
        while(selecao != 0){
            System.out.println();
            System.out.println("Selecione uma opção:");
            System.out.println("0. Voltar");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Busca por CPF");
            System.out.println("3. Busca por nome");
            System.out.println("4. Listar alunos");
            System.out.println("5. Deletar aluno");
            System.out.println("6. Atualizar dados pessoais");

            Scanner scanner = new Scanner(System.in);

            selecao = scanner.nextInt();

                switch (selecao) {
                    case 1:
                        cadastrarAlunos();
                        break;
                    case 2:
                        buscaAlunoCpf();
                        break;
                    case 3:
                        buscaAlunoNome();
                        break;
                    case 4:
                        listarTodosAlunosDAO();
                        break;
                    case 5:
                        deletaAluno();
                        break;
                    case 6:
                        atualizaAluno();
                        break;
                }
        }
    }

    private static void cadastrarAlunos() {
		Scanner entrada = new Scanner (System.in);
		String cpf;
		String nome;
		String aniversario;

		System.out.println("Nome do aluno: ");
		nome = entrada.nextLine();
		System.out.println("CPF de "+nome+": ");
		cpf = entrada.nextLine();
		System.out.println("Data de nascimento (YYYY-MM-DD) de "+nome+": ");
		aniversario  = entrada.nextLine();

		Date aniverDate = Date.valueOf(aniversario);

		Aluno obj = new Aluno(nome, cpf, aniverDate);

        DAO.AlunoDAO.cadastrarAlunoDAO(obj);
    }
    private static void buscaAlunoCpf(){
        System.out.println("Qual o cpf do aluno?");
        Scanner scanner = new Scanner(System.in);
        String cpf = scanner.nextLine();
        DAO.AlunoDAO.listarAlunoCpf(cpf);
    }
    private static void buscaAlunoNome(){
        System.out.println("Qual o nome do aluno?");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        DAO.AlunoDAO.listarAlunoNome(nome);
    }
    private static void deletaAluno(){
        System.out.println("Qual o CPF do aluno que será deletado?");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        ExercicioDAO.excluirDeExercicioAluno(nome);
        TreinoDiarioDAO.excluirDeTreinoDiario(nome);
        deletaAlunoPlano(nome);
        DAO.AlunoDAO.deleteAluno(nome);
    }
    private static void atualizaAluno(){
        System.out.println("Qual o CPF do aluno que será atualizado?");
        Scanner scanner = new Scanner(System.in);
        String cpf = scanner.nextLine();
        System.out.println("Qual o novo nome?");
        String nome = scanner.nextLine();
        System.out.println("Qual o novo aniversario? (YYYY-MM-DD)");
        String niver = scanner.next();
        Date aniversario = Date.valueOf(niver);
        DAO.AlunoDAO.atualizaAluno(cpf, nome, aniversario);
    }

}


