package controle;

import academia.Plano;
import conexao.Conexao;


import static DAO.PlanoDAO.*;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.sql.*;



public class ControlePlano {
    public static void gerenciarPlanos() {
        Scanner scanner = new Scanner(System.in);
        int selecao = 99;

        while(selecao != 0) {
            System.out.println("Selecione uma opção:");
            System.out.println("0. Voltar");
            System.out.println("1. Cadastrar plano");
            System.out.println("2. Alterar plano");
            System.out.println("3. Excluir plano");
            System.out.println("4. Listar planos");
            System.out.println("5. Voltar ao menu");

            selecao = scanner.nextInt();

            switch (selecao) {
                case 1:
                    cadastrarPlano();
                    break;
                case 2:
                    alterarPlano();
                    break;
                case 3:
                    excluirPlano();
                    break;
                case 4:
                    listarPlanos();
                    break;
            }
        }
    }
    private static void cadastrarPlano() { //TESTAR
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do novo plano:");
        String nome = scanner.nextLine();

        System.out.println("Digite o valor mensal do novo plano:");
        String valorMensal = scanner.nextLine();

        Plano plano = new Plano(nome, valorMensal);

        cadastrarPlanoDAO(plano);
    }

    private static void alterarPlano() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID do plano que deseja alterar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Qual o novo nome?");
        String nome = scanner.nextLine();
        System.out.println("Qual o novo valor?");
        String valor = scanner.nextLine();
        alterarPlanoDAO(id, nome, valor);
    }

    private static void excluirPlano() {
        Scanner scanner = new Scanner(System.in);
        int id;

        System.out.println("Digite o ID do plano que deseja excluir:");
        id = scanner.nextInt();
        excluirPlanoDAO(id);
    }

    private static void listarPlanos() {
        System.out.println("Planos cadastrados:");
        ResultSet result = listarPlanoDAO();
        try{
            while(result.next()){
                String id = String.valueOf(result.getInt("code"));
                String nome = String.valueOf(result.getString("nome"));
                String valor = String.valueOf(result.getString("valor"));
                System.out.println("ID: "+id+", Nome: "+nome+", Valor mensal: "+valor);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
