package conexao;

import java.sql.*;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/academia";
    private static final String user = "root";
    private static final String password = "root"; //COLOCAR SENHA CORRETA

    private static Connection conn;

    public static Connection getConn() {
        try {
            if(conn.isClosed() || conn == null) {
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

    public static Connection getConnFirst(){
        try {
            if(conn == null) {
                String url = "jdbc:mysql://localhost:3306";
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
            Connection conn = getConnFirst();
            Statement sqlStatement = conn.createStatement();
            try{
                sql = "CREATE DATABASE academia";
                sqlStatement.executeUpdate(sql);
                System.out.println("Database criado");
            }
            catch (SQLException e){
                System.out.println(e);
            }
            conn.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        try{//Cria tabelas
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

            //aluno
            sql = "CREATE TABLE IF NOT EXISTS aluno ( " +
                    "cpf VARCHAR(11) PRIMARY KEY, " +
                    "nome TEXT NOT NULL," +
                    " aniversario DATE NOT NULL, " +
                    "comecoPlano DATE, " +
                    "cartao VARCHAR(13))";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela alunos criada");

            //EXERCICIOS
            sql = "CREATE TABLE IF NOT EXISTS exercicios (" +
                    "num INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nome TEXT NOT NULL, " +
                    "series INT NOT NULL, " +
                    "minReps INT NOT NULL, " +
                    "maxReps INT NOT NULL, " +
                    "carga FLOAT NOT NULL, " +
                    "tempoDescanso INT NOT NULL)";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela exercicios criada");

            //MUSCULOS
            sql = "CREATE TABLE IF NOT EXISTS musculos (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nome VARCHAR(30) NOT NULL)";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela musculos criada");

            //EXERCICIO_MUSCULOS
            sql = "CREATE TABLE IF NOT EXISTS exercicio_musculos (" +
                    "id_exercicio INT," +
                    "id_musculo INT," +
                    "FOREIGN KEY (id_exercicio) REFERENCES exercicios(num)," +
                    "FOREIGN KEY (id_musculo) REFERENCES musculos(id)," +
                    "PRIMARY KEY (id_exercicio, id_musculo)" +
                    ")";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela exercicio_musculos criada");

            //TREINO
            sql = "CREATE TABLE IF NOT EXISTS treinos (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nome VARCHAR(30) NOT NULL)";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela treinos criada");

            //TREINO_EXERCICIO
            sql = "CREATE TABLE IF NOT EXISTS treino_exercicios (" +
                    "id_treino INT," +
                    "id_exercicio INT," +
                    "FOREIGN KEY (id_treino) REFERENCES treinos(id)," +
                    "FOREIGN KEY (id_exercicio) REFERENCES exercicios(num)," +
                    "PRIMARY KEY (id_treino, id_exercicio)" +
                    ")";

            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela treino_exercicios criada");

            //PLANOS
            sql = "CREATE TABLE IF NOT EXISTS planos (" +
                    "code INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nome TEXT NOT NULL, " +
                    "valor TEXT NOT NULL)";
            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela planos criada");

            //PLANOS_ALUNO
            sql = "CREATE TABLE IF NOT EXISTS planos_aluno(" +
                    "comecoPlano DATE NOT NULL, " +
                    "cartao VARCHAR(13), " +
                    "alunoCpf VARCHAR(11), " +
                    "codPlano INT, " +
                    "FOREIGN KEY(alunoCpf) REFERENCES aluno(cpf), " +
                    "FOREIGN KEY(codPlano) REFERENCES planos(code));";
            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela planos_aluno criada");

            sql = "CREATE TABLE IF NOT EXISTS treino_diario("+
                    "dia DATE NOT NULL, "+
                    "alunoCpf VARCHAR(11),"+
                    "codTreino INT,"+
                    "FOREIGN KEY(alunoCpf) REFERENCES aluno(cpf),"+
                    "FOREIGN KEY(codTreino) REFERENCES treinos(id));";
            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela treino_diario criada");

            sql = "CREATE TABLE IF NOT EXISTS exercicio_aluno(" +
                    "dia DATE NOT NULL, " +
                    "alunoCpf VARCHAR(11) NOT NULL, " +
                    "codExercicio INT NOT NULL," +
                    "cargaPersonalizada FLOAT NOT NULL, " +
                    "FOREIGN KEY(alunoCPF) REFERENCES aluno(cpf)," +
                    "FOREIGN KEY(codExercicio) REFERENCES exercicios(num));";
            sqlStatement.executeUpdate(sql);
            System.out.println("Tabela exercicio_aluno criada");

        }
        catch (SQLException e){
            System.out.println(e);
        }

    }

    public static ResultSet query(String sql){
        ResultSet result;
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();
            result = sqlStatement.executeQuery(sql);
        }
        catch (SQLException e){
            System.out.println(e);
            result = null;
        }
        return result;
    }
}
