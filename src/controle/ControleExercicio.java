package controle;

import DAO.ExercicioDAO;
import DAO.MusculoDAO;
import academia.Exercicio;
import academia.Musculo;
import conexao.Conexao;

import java.sql.*;
import java.util.Scanner;

public class ControleExercicio {
    public static void gerenciarExercicios() {
        Scanner scanner = new Scanner(System.in);
        int selecao = 99;

        while(selecao!=0) {
            System.out.println("Selecione uma opção:");
            System.out.println("0. Voltar");
            System.out.println("1. Cadastrar exercício");
            System.out.println("2. Alterar exercício");
            System.out.println("3. Excluir exercício");
            System.out.println("4. Listar exercícios");

            selecao = scanner.nextInt();

            switch (selecao) {
                case 1:
                    cadastrarExercicio();
                    break;
                case 2:
                    alterarExercicio();
                    break;
                case 3:
                    excluirExercicio();
                    break;
                case 4:
                    ExercicioDAO.listarExerciciosDAO();
                    break;
            }
        }
    }

    private static void cadastrarExercicio() {
        Scanner scanner = new Scanner(System.in);
        boolean ligado = true;

        System.out.println("Digite o nome do novo exercício:");
        String nome = scanner.nextLine();

        System.out.println("Digite o número de séries do novo exercício:");
        int series = scanner.nextInt();

        System.out.println("Digite o número mínimo de repetições do novo exercício:");
        int minReps = scanner.nextInt();

        System.out.println("Digite o número máximo de repetições do novo exercício:");
        int maxReps = scanner.nextInt();

        System.out.println("Digite a carga do novo exercício:");
        float carga = scanner.nextFloat();

        System.out.println("Digite o tempo de descanso do novo exercício:");
        int tempoDescanso = scanner.nextInt();

        Exercicio exercicio = new Exercicio(nome, series, minReps, maxReps, carga, tempoDescanso);
        ExercicioDAO.cadastrarExercicioDAO(exercicio);

        exercicio.setIdExercicio(ExercicioDAO.encontraIdDAO(exercicio));

        while (ligado) {
            System.out.println("Selecione os músculos relacionados com o exercício (pressione '0' para parar):");
            MusculoDAO.listarMusculosDAO();
            int idMusculo = scanner.nextInt();
            if (idMusculo == 0) {
                ligado = false;
            } else {
                //System.out.println("TESTE");
                ExercicioDAO.inserirRelacaoExercicioMusculoDAO(exercicio, idMusculo);
            }
        }
    }
    private static void excluirExercicio() {
        Scanner scanner = new Scanner(System.in);
        int id;
        System.out.println("Digite o ID do exercício que deseja excluir:");
        id = scanner.nextInt();
        ExercicioDAO.excluirDeExercicioMusculosDAO(id);
        ExercicioDAO.excluirDeExerciciosDAO(id);
    }

    private static void alterarExercicio() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID do exercício que você deseja alterar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o novo nome do exercício:");
        String nome = scanner.nextLine();

        System.out.println("Digite o novo número de séries do exercício:");
        int series = scanner.nextInt();

        System.out.println("Digite o novo número mínimo de repetições do exercício:");
        int minReps = scanner.nextInt();

        System.out.println("Digite o novo número máximo de repetições do exercício:");
        int maxReps = scanner.nextInt();

        System.out.println("Digite a nova carga do exercício:");
        float carga = scanner.nextFloat();

        System.out.println("Digite o novo tempo de descanso do exercício:");
        int tempoDescanso = scanner.nextInt();

        Exercicio exercicio = new Exercicio(nome, series, minReps, maxReps, carga, tempoDescanso);
        exercicio.setIdExercicio(id);
        ExercicioDAO.alterarExercicioDAO(exercicio);
    }

}
