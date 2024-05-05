package DAO;

import conexao.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class TreinoDiarioDAO {
    public static void cadastraExercicio(String cpf, String id_treino, String id_exercicio){
        return;
    }
    public static void cadastraExercicioTreino(String cpf, int id_treino, int id_exercicio, float carga, Date dia){
        String sql = "INSERT INTO exercicio_aluno(dia, alunoCpf, codExercicio, cargaPersonalizada) " +
                "VALUES('%s', '%s', %s, %s);";
        sql = String.format(sql, dia, cpf, id_treino, id_exercicio, carga);
        try{
            Connection con = Conexao.getConn();
            Statement sqlStatement = con.createStatement();
            sqlStatement.executeUpdate(sql);

        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
}
