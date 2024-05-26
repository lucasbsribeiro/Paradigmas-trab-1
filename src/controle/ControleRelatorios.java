package controle;

import DAO.ExercicioDAO;
import DAO.TreinoDAO;
import DAO.TreinoDiarioDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Scanner;

public class ControleRelatorios {
    public static void gerenciarRelatorios(){
        Scanner scanner = new Scanner(System.in);
        int selecao = 99;
        while(selecao!=0){
            System.out.println();
            System.out.println("Selecione uma opção:");
            System.out.println("0. Voltar");
            System.out.println("1. Presença entre datas na academia");
            System.out.println("2. Evolução em exercicíos");

            selecao = scanner.nextInt();

            switch (selecao){
                case 1:
                    presencas();
                    break;
                case 2:
                    evolucao();
                    break;
            }
        }
    }
    private static void presencas(){ //FAZER dia1 AVANCAR NAO FUNCIONANDO
        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual o cpf do aluno?");
        String cpf = scanner.nextLine();

        System.out.println("Data de inicio:");
        String data = scanner.nextLine();
        Date data1= Date.valueOf(data);

        System.out.println("Data final:");
        data = scanner.nextLine();
        Date data2 = Date.valueOf(data);
        System.out.println("Dias em que o aluno esteve na academia:\n");

        while(data1.before(data2)){
            if(TreinoDiarioDAO.checaAlunoData(cpf, data1)){
                System.out.println(data1);
            }
            data1 = Date.valueOf(data1.toLocalDate().plusDays(1));
        }
        if(TreinoDiarioDAO.checaAlunoData(cpf, data1)){
            System.out.println(data1);
        }
    }
    private static void evolucao(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual o cpf do aluno?");
        String cpf = scanner.nextLine();

        ExercicioDAO.listarExerciciosDAO();
        System.out.println("Qual o ID do exercicio?");
        int idExercicio = scanner.nextInt();

        ResultSet resultadoQuery = TreinoDiarioDAO.cargasExercicio(cpf, idExercicio);

        try{
            while(resultadoQuery.next()){
                System.out.println("Data: " + resultadoQuery.getDate("dia") + "  " + "Carga: " + resultadoQuery.getFloat("cargaPersonalizada"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}