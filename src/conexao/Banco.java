package conexao;

import java.sql.*;
import conexao.Conexao;
public class Banco {

    public static void main(String [] args) {
        String sql;
        try {//Cria database
            Connection conn = Conexao.getConn();
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
                    " aniversario DATE NOT NULL, comecoPlano DATE NOT NULL, cartao VARCHAR(13) NOT NULL)";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela alunos criada");

            sql = "CREATE TABLE IF NOT EXISTS exercicios (num INT PRIMARY KEY AUTO_INCREMENT, nome TEXT NOT NULL, musculos TEXT NOT NULL)";

            sqlStatement.executeUpdate(sql);


            sqlStatement.close();
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
}
