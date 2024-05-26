package controle;
import DAO.ExercicioDAO;
import DAO.MusculoDAO;
import DAO.TreinoDAO;
import academia.Treino;

import java.util.Scanner;

public class ControleTreino {
    public static void gerenciarTreino() {
        Scanner scanner = new Scanner(System.in);
        int selecao = 99;

        while (selecao != 0) {
            System.out.println();
            System.out.println("Selecione uma opção:");
            System.out.println("0. Sair");
            System.out.println("1. Cadastrar treino");
            System.out.println("2. Excluir treino");
            System.out.println("3. Listar treinos");

            selecao = scanner.nextInt();

            switch (selecao) {
                case 1:
                    cadastrarTreino();
                    break;
               case 2:
                    excluirTreino();
                    break;
                case 3:
                    TreinoDAO.listarTreinosDAO();
                    break;
                default:
                    break;
            }
        }
    }
    private static void cadastrarTreino() {
        Scanner scanner = new Scanner(System.in);
        boolean ligado = true;

        System.out.println("Digite o nome do novo treino:");
        String nome = scanner.nextLine();

        Treino treino = new Treino(nome);

        TreinoDAO.cadastrarTreinoDAO(treino);

        treino.setIdTreino(TreinoDAO.encontraTreinoDAO(treino));

        while (ligado) {
            ExercicioDAO.listarExerciciosDAO();
            System.out.println("Selecione os IDs dos exercícios do treino (pressione '0' para parar):");
            int idExercicio = scanner.nextInt();
            if (idExercicio == 0) {
                ligado = false;
            } else {
                //System.out.println("TESTE");
                TreinoDAO.inserirRelacaoTreinoExerciciosDAO(treino, idExercicio);
            }
            //scanner.close();
        }
    }
    private static void excluirTreino() {
        Scanner scanner = new Scanner(System.in);
        int id;
        TreinoDAO.listarTreinosDAO();
        System.out.println("Digite o ID do treino que deseja excluir:");
        id = scanner.nextInt();
        TreinoDAO.excluirDeTreinoExerciciosDAO(id);
        TreinoDAO.excluirDeTreinosDAO(id);
    }

}
