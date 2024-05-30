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

    public static boolean retornaAlunoPlano(String cpf) {
        String sql = "SELECT comecoPlano, cartao FROM academia.planos_aluno WHERE alunoCpf = '%s'";
        sql = String.format(sql, cpf);
        ResultSet result;
        try {
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();
            result = sqlStatement.executeQuery(sql);
            if (result.next()) {
                // Linha encontrada
                return true;
            } else {
                // Nenhuma linha encontrada
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            // Em caso de exceção, considera que não encontrou a linha
            return false;
        }
    }
    public static void deletaAlunoPlano(String cpf){
        String sql = "DELETE FROM planos_aluno WHERE alunoCpf = '%s'";
        sql = String.format(sql, cpf);
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();
            sqlStatement.execute(sql);
            System.out.println("Plano deletado de aluno com sucesso.");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public static boolean checaAlunoPlano(String cpf){
        boolean checa = retornaAlunoPlano(cpf);
        if(checa == false){
            return false;
        }
        return true;
    }

    public static void encontraDadosPlano(String cpf) {
        String sql = "SELECT comecoPlano, codPlano FROM academia.planos_aluno WHERE alunoCpf = '%s'";
        sql = String.format(sql, cpf);
        ResultSet result;
        try {
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();
            result = sqlStatement.executeQuery(sql);

            while (result.next()) {
                String comecoPlano = result.getString("comecoPlano");
                int codPlano = result.getInt("codPlano");
                System.out.println("Código do plano: " + codPlano);
                System.out.println("Data de início do plano: " + comecoPlano);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
