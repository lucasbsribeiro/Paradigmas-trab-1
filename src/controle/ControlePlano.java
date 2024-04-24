package controle;

import academia.Plano;
import conexao.Conexao;

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

    private static List<Plano> ListaPlanos = new ArrayList<>();
    public static void gerenciarPlanos() {
        Scanner scanner = new Scanner(System.in);
        boolean ligado = true;

        while(ligado) {
            System.out.println();
            System.out.println("Selecione uma opção:");
            System.out.println("1. Cadastrar plano");
            System.out.println("2. Alterar plano");
            System.out.println("3. Excluir plano");
            System.out.println("4. Listar planos");
            System.out.println("5. Voltar ao menu");

            int selected = scanner.nextInt();

            switch (selected) {
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
                case 5:
                    ligado = false;
                    break;
                default:
                    break;
            }
        }
        //scanner.close();
    }
    private static void cadastrarPlano() { //TESTAR
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do novo plano:");
        String nome = scanner.nextLine();

        System.out.println("Digite o valor mensal do novo plano:");
        String valorMensal = scanner.nextLine();

        Plano plano = new Plano(nome, valorMensal);

        String sql = "INSERT INTO planos(nome, valor) VALUES('%s', '%s');";
        sql = String.format(sql, plano.getNomePlano(), plano.getValorMensal());

        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

             sqlStatement.execute(sql);
             System.out.println("Plano cadastrado com sucesso.");
        }
        catch (SQLException e){
            System.out.println("Seu plano NAO FOI cadastrado corretamente.");
        }
        //scanner.close();
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

        String sql = "UPDATE planos SET nome = ?, valor = ? WHERE code = ?";

        try
            (Connection conn = Conexao.getConn();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, valor);
            statement.setInt(3, id);
            statement.execute();
            System.out.println("Plano atualizado com sucesso");
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    private static void excluirPlano() {
        Scanner scanner = new Scanner(System.in);
        int id;

        System.out.println("Digite o ID do plano que deseja excluir:");
        id = scanner.nextInt();
        String sql = "DELETE FROM planos WHERE code ="+String.valueOf(id)+";";
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();
            sqlStatement.execute(sql);
            System.out.println("Plano deletado com sucesso.");
        }
        catch (SQLException e){
                System.out.println(e);
        }
    }

    private static void listarPlanos() {//TESTAR
        System.out.println("Planos cadastrados:");
        String sql = "SELECT * from academia.planos;";
        ResultSet result;
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

            result = sqlStatement.executeQuery(sql);
            while(result.next()){
                String id = String.valueOf(result.getInt("code"));
                String nome = String.valueOf(result.getString("nome"));
                String valor = String.valueOf(result.getString("valor"));
                System.out.println("ID: "+id+", Nome: "+nome+", Valor mensal: "+valor);
            }
        }
        catch (SQLException e){
            System.out.println(e);
            result = null;
        }
    }
}
