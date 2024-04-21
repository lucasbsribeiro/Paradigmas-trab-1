package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import academia.Aluno;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/academia";
    private static final String user = "root";
    private static final String password = "root"; //COLOCAR SENHA CORRETA

    private static Connection conn;

    public static Connection getConn() {
        try {
            if(conn == null) {
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            } else {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void definicoesDoBanco(){
        String sql;
        try {//Cria database
            Connection conn = getConn();
            Statement sqlStatement = conn.createStatement();
            sql = "CREATE DATABASE academia";
            sqlStatement.executeUpdate(sql);
            System.out.println("Database criado");

        }
        catch(SQLException e){
            System.out.println(e);
        }

        try{//Cria tabelas
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

            sql = "CREATE TABLE IF NOT EXISTS aluno ( cpf VARCHAR(11) PRIMARY KEY, nome TEXT NOT NULL," +
                    " aniversario DATE NOT NULL, comecoPlano DATE, cartao VARCHAR(13))";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela alunos criada");

            sql = "CREATE TABLE IF NOT EXISTS exercicios (num INT PRIMARY KEY AUTO_INCREMENT, nome TEXT NOT NULL, musculos TEXT NOT NULL)";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela exercicios criada");

            sql = "CREATE TABLE IF NOT EXISTS planos (code INT PRIMARY KEY AUTO_INCREMENT, nome TEXT NOT NULL, valor FLOAT NOT NULL)";
            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela planos criada");

        }
        catch (SQLException e){
            System.out.println(e);
        }

    }
    public static void insere_aluno(Aluno objeto){
        String comecoPlano;
        String cartao;
        String sql = "INSERT INTO academia.aluno(cpf, nome, aniversario) " +
                "VALUES('%s', '%s', '%s');";


        sql = String.format(sql, objeto.cpf, objeto.nome, objeto.aniversario);
        System.out.println(sql);
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();


            sqlStatement.executeUpdate(sql);
            System.out.println("Aluno inserido no banco de dados.");
//            sqlStatement.close();
//            conn.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }


    }
}
