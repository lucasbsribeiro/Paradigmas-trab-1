package controle;

import academia.Musculo;
import academia.Plano;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleMusculo {
    private static List<Musculo> ListaMusculos = new ArrayList<>();

    public static void gerenciarMusculos() {
        Scanner scanner = new Scanner(System.in);
        boolean ligado = true;

        while (ligado) {
            System.out.println();
            System.out.println("Selecione uma opção:");
            System.out.println("1. Cadastrar músculo");
            System.out.println("2. Excluir músculo");
            System.out.println("3. Listar músculos");
            System.out.println("4. Voltar ao menu");

            int selected = scanner.nextInt();

            switch (selected) {
                case 1:
                    cadastrarMusculo();
                    break;
                case 2:
                    excluirMusculo();
                    break;
                case 3:
                    listarMusculos();
                    break;
                case 4:
                    ligado = false;
                    break;
                default:
                    break;
            }
        }
    }

    private static void cadastrarMusculo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do novo músculo:");
        String nome = scanner.nextLine();

        Musculo musculo = new Musculo(nome);

        ListaMusculos.add(musculo);

        int index = ListaMusculos.size() - 1;
        musculo.setIdMusculo(index);

        System.out.println("Músculo cadastrado com sucesso.");

        //scanner.close();
    }

    private static Musculo buscaMusculoPorId(int id) {
        for (Musculo musculo : ListaMusculos) {
            if (musculo.getIdMusculo() == id) {
                return musculo;
            }
        }
        return null;
    }
    private static void excluirMusculo() {
        Scanner scanner = new Scanner(System.in);
        int id;

        System.out.println("Digite o ID do músculo que deseja excluir:");
        id = scanner.nextInt();

        Musculo musculo = buscaMusculoPorId(id);

        if(musculo != null) {
            boolean removido = ListaMusculos.remove(musculo);

            if (removido) {
                System.out.println("Musculo com ID " + id + " removido com sucesso.");
            } else {
                System.out.println("Ocorreu um erro ao remover o músculo com ID " + id + ".");
            }
        }
        else {
            System.out.println("ID do músculo não encontrado.");
        }
    }
    private static void listarMusculos() {
        System.out.println("Músculos cadastrados:");
        for (Musculo musculo : ListaMusculos) {
            System.out.println("->ID: " + musculo.getIdMusculo() + ", Nome: " + musculo.getNomeMusculo());
        }
    }
}

