package DAO;

import academia.Aluno;
import com.mysql.cj.protocol.Resultset;
import conexao.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static conexao.Conexao.query;

public class PlanoAlunoDAO {
    public static void cadastraPlanoAluno(String cpf, String codPlano, String comecoPlano, String cartao){
        String sql = "INSERT INTO planos_aluno(comecoPlano, cartao, alunoCpf, codPlano) VALUES ('%s', '%s', '%s', %s);";
        sql = String.format(sql, comecoPlano, cartao, cpf, codPlano);

        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();
            sqlStatement.executeUpdate(sql);
            System.out.println("Cadastro de plano para aluno realizado.");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public static ArrayList retornaAlunoPlano(String cpf){
        String sql = "SELECT cartao, comecoPlano FROM academia.planos_aluno WHERE alunoCpf = '%s'";
        sql = String.format(sql, cpf);
        ResultSet result;
        ArrayList<String> retornos = new ArrayList<String>();
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();
            result = sqlStatement.executeQuery(sql);
            if(result.next()) {
                retornos.add(result.getString("cartao"));
                retornos.add(result.getString("comecoPlano"));
            }
            else{
                retornos.add(null);
            }
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
        return retornos;
    }

    public static void deletaAlunoPlano(String cpf){
        String sql = "DELETE FROM planos_aluno WHERE alunoCpf = '%s'";
        sql = String.format(sql, cpf);
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();
            sqlStatement.execute(sql);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
