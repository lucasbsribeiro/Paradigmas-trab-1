package DAO;

import academia.Aluno;
import conexao.Conexao;

import java.sql.*;

import static conexao.Conexao.query;

public class AlunoDAO {
    public static void cadastrarAlunoDAO(Aluno aluno) {
        String sql = "INSERT INTO academia.aluno(cpf, nome, aniversario) " +
                "VALUES('%s', '%s', '%s');";


        sql = String.format(sql, aluno.getCpf(), aluno.getNome(), aluno.getAniversario());
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

            sqlStatement.executeUpdate(sql);
            System.out.println("Aluno inserido no banco de dados.");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public static void listarTodosAlunosDAO() {
        System.out.println("Alunos cadastrados:");
        String sql = "SELECT * from academia.aluno;";
        ResultSet result;

        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

            result = sqlStatement.executeQuery(sql);
            while(result.next()){
                String cpf = String.valueOf(result.getInt("cpf"));
                String nome = String.valueOf(result.getString("nome"));
                System.out.println("CPF: "+cpf+", Nome: "+nome);
            }
            sqlStatement.close();
        }
        catch (SQLException e){
            System.out.println(e);
            result = null;
        }
    }
    public static void listarAlunoCpf(String cpf){
        String sql = "SELECT * FROM academia.aluno where cpf = '"+cpf+"';";
        ResultSet result;
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

            result = sqlStatement.executeQuery(sql);
            if(result.isBeforeFirst()){
                while(result.next()){
                    String nome = result.getString("nome");
                    Date aniversario = result.getDate("aniversario");
                    Aluno aluno = new Aluno(nome, cpf, aniversario);
                    aluno.printaAluno();
                    PlanoAlunoDAO.encontraDadosPlano(cpf);
                }
            }
            else{
                System.out.println("Nenhum aluno com o CPF informado cadastrado.");
            }

        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public static void listarAlunoNome(String nome){
        String sql = "SELECT * FROM academia.aluno where nome = '"+nome+"';";
        ResultSet result;
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

            result = sqlStatement.executeQuery(sql);
            if(result.isBeforeFirst()){
                while(result.next()){
                    String cpf = result.getString("cpf");
                    Date aniversario = result.getDate("aniversario");
                    Aluno aluno = new Aluno(nome, cpf, aniversario);
                    aluno.printaAluno();
                    PlanoAlunoDAO.encontraDadosPlano(cpf);
                }
            }
            else{
                System.out.println("Nenhum aluno com o Nome informado cadastrado.");
            }

        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public static void deleteAluno(String cpf){
        String sql = "DELETE FROM aluno WHERE cpf = '"+cpf+"';";
                try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

            sqlStatement.executeUpdate(sql);
            System.out.println("Aluno deletado do banco de dados.");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public static void atualizaAluno(String cpf, String nome, Date aniversario){
        String sql = "UPDATE aluno SET nome = '%s', aniversario = '%s' WHERE cpf = '"+cpf+"';";

        sql = String.format(sql, nome, aniversario);
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

            sqlStatement.executeUpdate(sql);
            System.out.println("Aluno inserido no banco de dados.");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public static boolean checaAluno(String cpf){
        ResultSet result = query("SELECT count(*) from academia.aluno where cpf = '"+cpf+"';");
        try{
            result.next();
            if(result.getInt("count(*)")==1){
                return true;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public static Aluno sqlParaAluno(String cpf){
        ResultSet result = query("SELECT * FROM academia.aluno WHERE cpf = '"+cpf+"';");
        Aluno aluno;
        try{
            result.next();
            String nome = result.getString("nome");
            Date aniversario = result.getDate("aniversario");

            aluno = new Aluno(nome, cpf, aniversario);
        }
        catch(Exception e){
            System.out.println(e);
            aluno = null;
        }
        return aluno;
    }
}
