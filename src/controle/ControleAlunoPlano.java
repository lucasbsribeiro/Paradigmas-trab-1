package controle;

import DAO.PlanoAlunoDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static DAO.AlunoDAO.checaAluno;
import static DAO.PlanoAlunoDAO.retornaAlunoPlano;
import static DAO.PlanoDAO.checaPlano;
import static conexao.Conexao.query;

public class ControleAlunoPlano {
    public static void gerenciarAlunoPlano(){
        System.out.println("Selecione uma opção:");
        System.out.println("1. Adicionar plano a aluno");
        System.out.println("2. Deletar plano de aluno");

        Scanner scanner = new Scanner(System.in);

        int selected = scanner.nextInt();

        switch (selected) {
            case 1:
                cadastrarPlanoAluno();
                break;
            case 2:
                deletarPlanoAluno();
                break;
        }

    }
    private static void cadastrarPlanoAluno(){
        String cpf;
        String codPlano;
        System.out.println("Qual o cpf do aluno?");
        Scanner scanner = new Scanner(System.in);
        cpf = scanner.nextLine();
        System.out.println("Qual o codigo do plano desejado?");
        codPlano = scanner.nextLine();
        System.out.println("Qual a data de comeco do plano (YYYY-MM-DD)?");
        String comeco = scanner.nextLine();
        System.out.println("Qual o cartao para pagamento (13 caracteres)? ");
        String cartao = scanner.nextLine();
        try{
            if(!checaAluno(cpf)){
                System.out.println("CPF invalido.");
                return;
            }
            if(!checaPlano(codPlano)){
                System.out.println("Codigo de plano invalido.");
                return;
            }

            ArrayList<String> dados = retornaAlunoPlano(cpf);
            if(dados != null){
                System.out.println("O aluno já possui planos cadastrados.");
                return;
            }


            PlanoAlunoDAO.cadastraPlanoAluno(cpf, String.valueOf(codPlano), comeco, cartao);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    private static void deletarPlanoAluno(){
        System.out.println("Qual o CPF do aluno que terá seu plano cancelado?");
        Scanner scanner = new Scanner(System.in);
        String cpf = scanner.nextLine();
        DAO.PlanoAlunoDAO.deletaAlunoPlano(cpf);
    }
}
