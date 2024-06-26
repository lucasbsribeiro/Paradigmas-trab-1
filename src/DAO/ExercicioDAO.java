package DAO;

import academia.Exercicio;
import conexao.Conexao;

import java.sql.*;

public class ExercicioDAO
{
    public static void cadastrarExercicioDAO(Exercicio exercicio) {
        String sql = "INSERT INTO academia.exercicios(nome, series, minReps, maxReps, carga, tempoDescanso) "
                + "VALUES(?, ?, ?, ?, ?, ?);";

        PreparedStatement statement = null;

        try {
            statement = Conexao.getConn().prepareStatement(sql);
            statement.setString(1, exercicio.getNomeExercicio());
            statement.setInt(2, exercicio.getSeries());
            statement.setInt(3, exercicio.getMinReps());
            statement.setInt(4, exercicio.getMaxReps());
            statement.setFloat(5, exercicio.getCarga());
            statement.setInt(6, exercicio.getTempoDescanso());
            statement.execute();
            statement.close();
            System.out.println("Exercício cadastrado com sucesso");
        } catch (SQLException e) {
            System.out.println("O exercício NÃO FOI cadastrado corretamente: " + e.getMessage());
        }
    }
    public static int encontraIdDAO(Exercicio exercicio) {
        String sql = "SELECT num FROM exercicios WHERE nome = ?";
        int idExercicio = -1; // Valor padrão se o exercício não for encontrado
        PreparedStatement statement = null;

        try {
            statement = Conexao.getConn().prepareStatement(sql);
            statement.setString(1, exercicio.getNomeExercicio());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idExercicio = resultSet.getInt("num");
            }
            // Não feche a conexão aqui
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter ID do exercício: " + e.getMessage());
        }
        return idExercicio;
    }
    public static void inserirRelacaoExercicioMusculoDAO(Exercicio exercicio, int idMusculo) {
        String sql = "INSERT INTO academia.exercicio_musculos (id_exercicio, id_musculo) VALUES (?, ?);";

        PreparedStatement statement = null;

        try {
            statement = Conexao.getConn().prepareStatement(sql);
            statement.setInt(1, exercicio.getIdExercicio());
            statement.setInt(2, idMusculo);
            statement.execute();
            statement.close();
            System.out.println("Relação exercício-músculo criada.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir relação exercício-músculo: " + e.getMessage());
        }
    }
    public static void excluirDeExercicioMusculosDAO(int id) {
        String sql = "DELETE FROM exercicio_musculos WHERE id_exercicio ="+String.valueOf(id)+";";

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
    public static void excluirDeExerciciosDAO(int id) {
        String sql = "DELETE FROM exercicios WHERE num ="+String.valueOf(id)+";";

        PreparedStatement statement = null;

        try{
            statement = Conexao.getConn().prepareStatement(sql);
            statement.execute();
            statement.close();
            System.out.println("Exercício deletado da tabela exercicios com sucesso.");
        }
        catch (SQLException e){
            System.out.println("Erro ao deletar o exercício.");
        }
    }
    public static void listarExerciciosDAO() {
        System.out.println("Exercicios cadastrados:");
        String sql = "SELECT * from academia.exercicios;";
        ResultSet result;

        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

            result = sqlStatement.executeQuery(sql);
            while(result.next()){
                String id = String.valueOf(result.getInt("num"));
                String nome = String.valueOf(result.getString("nome"));
                System.out.println("ID: "+id+", Nome: "+nome);
            }
            sqlStatement.close();
        }
        catch (SQLException e){
            System.out.println(e);
            result = null;
        }
    }
    public static void alterarExercicioDAO(Exercicio exercicio) {
        String sql = "UPDATE exercicios SET nome = ?, series = ?, minReps = ?, maxReps = ?, carga = ?, tempoDescanso = ? WHERE num = ?";

        PreparedStatement statement = null;

        try {
            statement = Conexao.getConn().prepareStatement(sql);
            statement.setString(1, exercicio.getNomeExercicio());
            statement.setString(1, exercicio.getNomeExercicio());
            statement.setInt(2, exercicio.getSeries());
            statement.setInt(3, exercicio.getMinReps());
            statement.setInt(4, exercicio.getMaxReps());
            statement.setFloat(5, exercicio.getCarga());
            statement.setInt(6, exercicio.getTempoDescanso());
            statement.setInt(7, exercicio.getIdExercicio());
            statement.execute();
            statement.close();
            System.out.println("Exercício alterado com sucesso");
        } catch (SQLException e) {
            System.out.println("O exercício NÃO FOI alterado corretamente." + e);
        }
    }
    public static ResultSet encontraExerciciosTreino(int idTreino){
        String sql = "SELECT id_exercicio FROM academia.treino_exercicios WHERE id_treino = %s";
        sql = String.format(sql, String.valueOf(idTreino));
        ResultSet idExercicios = null;
        try{
            idExercicios = Conexao.query(sql); //RETORNA id dos exercicios do treino;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return idExercicios;
    }
    public static void listarDadosExercicio(int idExercicio){
        String sql = "SELECT * from exercicios where num = "+idExercicio+";";
        ResultSet dadosExercicios = Conexao.query(sql);
        try{
            while(dadosExercicios.next()){
                System.out.println("\nExercicio: "+dadosExercicios.getString("nome"));
                System.out.println("Quantidade de series: "+dadosExercicios.getInt("series"));
                System.out.println("Quantidade mínima de repetições: "+dadosExercicios.getInt("minReps"));
                System.out.println("Quantidade máxima de repetições: "+dadosExercicios.getInt("maxReps"));
                System.out.println("Carga: "+dadosExercicios.getFloat("carga"));
                System.out.println("Tempo de descanso: \n"+dadosExercicios.getInt("tempoDescanso"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static float retornaCarga(int idExercicio){
        float carga = -1;
        String sql = "SELECT carga from exercicios WHERE num = "+idExercicio+";";
        try{
            ResultSet result = Conexao.query(sql);
            result.next();
            carga = result.getFloat("carga");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return carga;

    }
    public static void excluirDeExercicioAluno(String cpf) {
            String sql = "DELETE FROM exercicio_aluno WHERE alunoCpf = '"+cpf+"';";

            PreparedStatement statement = null;

            try{
                statement = Conexao.getConn().prepareStatement(sql);
                statement.execute();
                statement.close();
                System.out.println("Aluno deletado da tabela exercicios_aluno com sucesso.");
            }
            catch (SQLException e){
                System.out.println("Erro ao deletar o aluno.");
            }
        }
}
