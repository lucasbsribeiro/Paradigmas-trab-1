package conexao;

import java.sql.*;

import academia.Aluno;
import academia.Plano;

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

            sql = "CREATE TABLE IF NOT EXISTS exercicios (num INT PRIMARY KEY AUTO_INCREMENT, nome TEXT NOT NULL, " +
                    "series INT NOT NULL, minReps INT NOT NULL, maxReps INT NOT NULL, carga FLOAT NOT NULL, tempoDescanso INT NOT NULL)";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela exercicios criada");

            sql = "CREATE TABLE IF NOT EXISTS musculos ( id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(30) NOT NULL)";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela musculos criada");

            sql = "CREATE TABLE IF NOT EXISTS exercicio_musculos (" +
                    "id_exercicio INT," +
                    "id_musculo INT," +
                    "FOREIGN KEY (id_exercicio) REFERENCES exercicios(num)," +
                    "FOREIGN KEY (id_musculo) REFERENCES musculos(id)," +
                    "PRIMARY KEY (id_exercicio, id_musculo)" +
                    ")";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela exercicio_musculos criada");


            sql = "CREATE TABLE IF NOT EXISTS planos (code INT PRIMARY KEY AUTO_INCREMENT, nome TEXT NOT NULL, valor TEXT NOT NULL)";
            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela planos criada");

            sql = "CREATE TABLE IF NOT EXISTS planos_aluno(comecoPlano DATE NOT NULL, cartao VARCHAR(13), alunoCpf VARCHAR(11), " +
                    "codPlano INT, FOREIGN KEY(alunoCpf) " +
                    "REFERENCES aluno(cpf), FOREIGN KEY(codPlano) REFERENCES planos(code));";
            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela planos_aluno criada");

        }
        catch (SQLException e){
            System.out.println(e);
        }

    }
    public static void insere_aluno(Aluno objeto){
        String sql = "INSERT INTO academia.aluno(cpf, nome, aniversario) " +
                "VALUES('%s', '%s', '%s');";


        sql = String.format(sql, objeto.cpf, objeto.nome, objeto.aniversario);
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
