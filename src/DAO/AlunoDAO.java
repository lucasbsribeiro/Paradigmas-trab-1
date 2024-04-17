package DAO;

import academia.Aluno;
import conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {
    public static void inserirAlunoBD(Aluno aluno) {
//
//        String sql = "INSERT INTO ALUNO (CPF, NOME, DATA_NASCIMENTO) VALUES (?, ?, ?)";
//
//        PreparedStatement ps = null;
//
//        try {
//            ps = Conexao.getConn().prepareStatement(sql);
//            ps.setString(1, aluno.getCpf());
//            ps.setString(2, aluno.getNome());
//            ps.setDate(3, aluno.getData());
//
//            ps.execute();
//            ps.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
