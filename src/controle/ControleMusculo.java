package controle;

import DAO.ExercicioDAO;
import DAO.MusculoDAO;
import academia.Musculo;
import academia.Plano;
import conexao.Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleMusculo {
    public static void gerenciarMusculos() {
        Scanner scanner = new Scanner(System.in);
        int selecao = 99;;

        while (selecao!=0) {
            System.out.println();
            System.out.println("Selecione uma opção:");
            System.out.println("0. Voltar");
            System.out.println("1. Cadastrar músculo");
            System.out.println("2. Excluir músculo");
            System.out.println("3. Listar músculos");
            System.out.println("4. Voltar ao menu");

            selecao = scanner.nextInt();

            switch (selecao) {
                case 1:
                    cadastrarMusculo();
                    break;
                case 2:
                    excluirMusculo();
                    break;
                case 3:
                    MusculoDAO.listarMusculosDAO();
                    break;
            }
        }
    }

    private static void cadastrarMusculo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do novo músculo:");
        String nome = scanner.nextLine();

        Musculo musculo = new Musculo(nome);

        MusculoDAO.cadastrarMusculoDAO(musculo);
        //scanner.close();
    }

    private static void excluirMusculo() {
        Scanner scanner = new Scanner(System.in);
        int id;
        MusculoDAO.listarMusculosDAO();
        System.out.println("Digite o ID do músculo que deseja excluir:");
        id = scanner.nextInt();

        MusculoDAO.excluirDeExercicioMusculosDAO(id);
        MusculoDAO.excluirDeMusculosDAO(id);
    }
}

