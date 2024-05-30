package DAO;

import conexao.Conexao;

import java.sql.*;

public class TreinoDiarioDAO {
    public static void cadastraTreino(String cpf, Date dia){
        String sql = "INSERT INTO treino_diario(dia, alunoCpf) VALUES('%s', '%s');";
        sql = String.format(sql, dia, cpf);
        try{
            Connection con = Conexao.getConn();
            Statement sqlStatement = con.createStatement();
            sqlStatement.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    public static void cadastraExercicioTreino(String cpf, int id_exercicio, float carga, Date dia){
        String sql = "INSERT INTO exercicio_aluno(dia, alunoCpf, codExercicio, cargaPersonalizada) " +
                "VALUES('%s', '%s', %s, %s);";
        sql = String.format(sql, dia, cpf, id_exercicio, carga);
        try{
            Connection con = Conexao.getConn();
            Statement sqlStatement = con.createStatement();
            sqlStatement.executeUpdate(sql);

        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public static boolean checaAlunoData(String cpf, Date dia){
        String sql = "SELECT count(*) from treino_diario WHERE alunoCpf = '%s' AND dia = '%s';";
        sql = String.format(sql, cpf, dia);
        ResultSet result = null;
        try{
            result = Conexao.query(sql);
            result.next();
            if(result.getInt("count(*)")>=1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public static ResultSet cargasExercicio(String cpf, int codExercicio){
        String sql = "SELECT cargaPersonalizada, dia FROM exercicio_aluno WHERE alunoCpf = '%s' AND codExercicio = %s ORDER BY dia;";
        sql = String.format(sql, cpf, codExercicio);
        ResultSet result = null;
        try{
            result = Conexao.query(sql);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return result;
    }
    public static void excluirDeTreinoDiario(String cpf) {
        String sql = "DELETE FROM treino_diario WHERE alunoCpf = '"+cpf+"';";

        PreparedStatement statement = null;

        try{
            statement = Conexao.getConn().prepareStatement(sql);
            statement.execute();
            statement.close();
            System.out.println("Aluno deletado da tabela treino_diario com sucesso.");
        }
        catch (SQLException e){
            System.out.println("Erro ao deletar o aluno.");
        }
    }
}
