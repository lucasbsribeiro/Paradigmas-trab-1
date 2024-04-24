package DAO;

import academia.Musculo;
import conexao.Conexao;

import java.sql.*;

public class MusculoDAO {

    public  static void cadastrarMusculoDAO(Musculo musculo) {
        String sql = "INSERT INTO academia.musculos(nome) " + "VALUES ('%s');";
        sql = String.format(sql, musculo.getNomeMusculo());

        PreparedStatement statement = null;

        try {
            statement = Conexao.getConn().prepareStatement(sql);
            statement.execute();
            statement.close();
            System.out.println("Músculo cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("O músculo NÃO FOI cadastrado corretamente.");
        }
    }
    public static void excluirDeExercicioMusculosDAO(int id) {
        String sql = "DELETE FROM exercicio_musculos WHERE id_musculo ="+String.valueOf(id)+";";

        PreparedStatement statement = null;

        try{
            statement = Conexao.getConn().prepareStatement(sql);
            statement.execute();
            statement.close();
            System.out.println("Relação deletada da tabela exercicio_musculos com sucesso.");
        }
        catch (SQLException e){
            System.out.println("Erro ao deletar a relação.");
        }
    }
    public static void excluirDeMusculosDAO(int id) {
        String sql = "DELETE FROM musculos WHERE id ="+String.valueOf(id)+";";

        PreparedStatement statement = null;

        try{
            statement = Conexao.getConn().prepareStatement(sql);
            statement.execute();
            statement.close();
            System.out.println("Músculo deletado com sucesso.");
        }
        catch (SQLException e){
            System.out.println("Erro ao deletar o músculo.");
        }
    }
    public static void listarMusculosDAO() {
        System.out.println("Músculos cadastrados:");
        String sql = "SELECT * from academia.musculos;";
        PreparedStatement statement = null;
        ResultSet result;

        try{
            statement = Conexao.getConn().prepareStatement(sql);

            result = statement.executeQuery(sql);
            while(result.next()){
                String id = String.valueOf(result.getInt("id"));
                String nome = String.valueOf(result.getString("nome"));
                System.out.println("ID: "+id+", Nome: "+nome);
            }
            statement.close();
            result.close();
        }
        catch (SQLException e){
            System.out.println(e);
            result = null;
        }
    }
}
