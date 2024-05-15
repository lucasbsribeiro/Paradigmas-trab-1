package controle;

import DAO.*;
import conexao.Conexao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Scanner;

import static DAO.ExercicioDAO.retornaCarga;

public class ControleTreinoDiario {
    public static void gerenciarTreinoDiario(){
        Scanner scanner = new Scanner(System.in);
        int selecao = 99;
        while (selecao !=0){
            System.out.println("Selecione uma opção:");
            System.out.println("0. Sair");
            System.out.println("1. Começar novo treino");

            selecao = scanner.nextInt();

            switch (selecao) {
                case 1:
                    comecarTreino();
                    break;
                default:
                    break;
            }
        }
    }
    private static void comecarTreino(){
        Scanner scanner = new Scanner(System.in);
        int personalizado;
        int idExAtual;
        int continuarTreino =2;
        System.out.println("Qual o cpf do aluno?");
        String cpf = scanner.nextLine();

        if(!AlunoDAO.checaAluno(cpf)){
            System.out.println("Aluno nao cadastrado.");
        }
        else if(!PlanoAlunoDAO.checaAlunoPlano(cpf)){
            System.out.println("Nenhum plano cadastrado para o aluno.");
        }
        System.out.println("Qual o dia do treino?");
        String diaS = scanner.nextLine();
        Date dia = Date.valueOf(diaS);

        TreinoDiarioDAO.cadastraTreino(cpf, dia);

        System.out.println("Qual o ID do treino desejado?");
        TreinoDAO.listarTreinosDAO();
        int id_treino = scanner.nextInt(); //FAZER CONFERIR SE IDTREINO EXISTE

        ResultSet idExercicios = ExercicioDAO.encontraExerciciosTreino(id_treino);
        try {
            while (idExercicios.next()) {
                System.out.println("Continuar treino?\n0. Não\n1. Sim");
                while(continuarTreino !=1 && continuarTreino!=0){
                    continuarTreino = scanner.nextInt();
                }
                if(continuarTreino==0){
                    break;
                }
                continuarTreino =
                idExAtual = idExercicios.getInt("id_exercicio");
                personalizado = comecarExercicio(idExAtual);
                if(personalizado==0){
                    cargaPersonalizada(idExAtual, id_treino, dia, cpf);
                }
                else{
                    cargaNormal(idExAtual, id_treino, dia, cpf);
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Treino finalizado.");
    }
    private static int comecarExercicio(int idExercicio){
        ExercicioDAO.listarDadosExercicio(idExercicio);
        System.out.println("Manter cargas padrão?\n0. Não\n1. Sim");
        int selecao = 2;
        Scanner scanner = new Scanner(System.in);
        while(selecao!=1 && selecao !=0){
            selecao = scanner.nextInt();
        }
        return selecao;
    }
    private static void cargaPersonalizada(int idExercicio, int idTreino, Date dia, String cpf){
        System.out.println("Qual será a carga de hoje?");
        Scanner scanner = new Scanner(System.in);
        float carga = scanner.nextFloat();
        TreinoDiarioDAO.cadastraExercicioTreino(cpf, idExercicio, carga, dia);
    }
    private static void cargaNormal(int idExercicio, int idTreino, Date dia, String cpf){
        float carga;
        carga = retornaCarga(idExercicio);
        TreinoDiarioDAO.cadastraExercicioTreino(cpf,idExercicio, carga, dia);
    }
}
