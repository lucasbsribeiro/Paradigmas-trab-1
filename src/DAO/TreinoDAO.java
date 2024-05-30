package DAO;

import academia.Aluno;
import academia.Exercicio;
import academia.Musculo;
import academia.Treino;
import conexao.Conexao;

import java.sql.*;

public class TreinoDAO {
    public  static void cadastrarTreinoDAO(Treino treino) {
        String sql = "INSERT INTO academia.treinos(nome) " + "VALUES ('%s');";
        sql = String.format(sql, treino.getNomeTreino());

        PreparedStatement statement = null;

        try {
            statement = Conexao.getConn().prepareStatement(sql);
            statement.execute();
            statement.close();
            System.out.println("Treino cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("O treino NÃO FOI cadastrado corretamente.");
        }
    }
    public static int encontraTreinoDAO(Treino treino) {
        String sql = "SELECT id FROM treinos WHERE nome = ?";
        int idExercicio = -1; // Valor padrão se o exercício não for encontrado
        PreparedStatement statement = null;

        try {
            statement = Conexao.getConn().prepareStatement(sql);
            statement.setString(1, treino.getNomeTreino());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idExercicio = resultSet.getInt("id");
            }
            // Não feche a conexão aqui
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter ID do treino: " + e.getMessage());
        }
        return idExercicio;
    }
    public static void inserirRelacaoTreinoExerciciosDAO(Treino treino, int idExercicio) {
        String sql = "INSERT INTO academia.treino_exercicios (id_treino, id_exercicio) VALUES (?, ?);";

        PreparedStatement statement = null;

        try {
            statement = Conexao.getConn().prepareStatement(sql);
            statement.setInt(1, treino.getIdTreino());
            statement.setInt(2, idExercicio);
            statement.execute();
            statement.close();
            System.out.println("Relação treino-exercício criada.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir relação treino-exercício: " + e.getMessage());
        }
    }
    public static void excluirDeTreinoExerciciosDAO(int id) {
        String sql = "DELETE FROM treino_exercicios WHERE id_treino ="+String.valueOf(id)+";";

        PreparedStatement statement = null;

        try{
            statement = Conexao.getConn().prepareStatement(sql);
            statement.execute();
            statement.close();
            System.out.println("Relação deletada da tabela treino_exercicios com sucesso.");
        }
        catch (SQLException e){
            System.out.println("Erro ao deletar a relação.");
        }
    }
    public static void excluirDeTreinosDAO(int id) {
        String sql = "DELETE FROM treinos WHERE id ="+String.valueOf(id)+";";

        PreparedStatement statement = null;

        try{
            statement = Conexao.getConn().prepareStatement(sql);
            statement.execute();
            statement.close();
            System.out.println("Exercício deletado da tabela treinos com sucesso.");
        }
        catch (SQLException e){
            System.out.println("Erro ao deletar o exercício.");
        }
    }
    public static void listarTreinosDAO() {
        System.out.println("Treinos cadastrados:");
        String sql = "SELECT * from academia.treinos;";
        ResultSet result;

        try {
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

            result = sqlStatement.executeQuery(sql);
            while (result.next()) {
                String id = String.valueOf(result.getInt("id"));
                String nome = String.valueOf(result.getString("nome"));
                System.out.println("ID: " + id + ", Nome: " + nome);
            }
            sqlStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
            result = null;
        }
    }
}

