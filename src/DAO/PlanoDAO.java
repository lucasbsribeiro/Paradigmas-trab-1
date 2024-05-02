package DAO;

import academia.Plano;
import conexao.Conexao;

import java.sql.*;

import static conexao.Conexao.query;

public class PlanoDAO {
    public static void cadastrarPlanoDAO(Plano plano){
        String sql = "INSERT INTO planos(nome, valor) VALUES('%s', '%s');";
        sql = String.format(sql, plano.getNomePlano(), plano.getValorMensal());

        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();

             sqlStatement.execute(sql);
             System.out.println("Plano cadastrado com sucesso.");
        }
        catch (SQLException e){
            System.out.println("Seu plano NAO FOI cadastrado corretamente.");
        }
    }
    public static void alterarPlanoDAO(int id, String nome, String valor){
        String sql = "UPDATE planos SET nome = ?, valor = ? WHERE code = ?";
        try
            (Connection conn = Conexao.getConn();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, valor);
            statement.setInt(3, id);
            statement.execute();
            System.out.println("Plano atualizado com sucesso");
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    public static void excluirPlanoDAO(int id){
        String sql = "DELETE FROM planos WHERE code ="+String.valueOf(id)+";";
        try{
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();
            sqlStatement.execute(sql);
            System.out.println("Plano deletado com sucesso.");
        }
        catch (SQLException e){
                System.out.println(e);
        }
    }
    public static ResultSet listarPlanoDAO(){
        String sql = "SELECT * from academia.planos;";
        ResultSet result;
        try {
            Connection conn = Conexao.getConn();
            Statement sqlStatement = conn.createStatement();
            result = sqlStatement.executeQuery(sql);
        }
        catch(SQLException e){
            System.out.println(e);
            result = null;
        }
        return result;
    }

    public static boolean checaPlano(String codPlano){
        try{
            ResultSet result = query("SELECT count(*) from academia.planos WHERE code = "+codPlano+";");
            result.next();
            if(result.getInt("count(*)")!=1){
                return false;
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return true;
    }


}
