package controle;

import academia.Plano;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class ControlePlano {

    private static List<Plano> ListaPlanos = new ArrayList<>();
    public static void gerenciarPlanos() {
        Scanner scanner = new Scanner(System.in);
        boolean ligado = true;

        while(ligado) {
            System.out.println();
            System.out.println("Selecione uma opção:");
            System.out.println("1. Cadastrar plano");
            System.out.println("2. Alterar plano");
            System.out.println("3. Excluir plano");
            System.out.println("4. Listar planos");
            System.out.println("5. Voltar ao menu");

            int selected = scanner.nextInt();

            switch (selected) {
                case 1:
                    cadastrarPlano();
                    break;
                case 2:
                    alterarPlano();
                    break;
                case 3:
                    excluirPlano();
                    break;
                case 4:
                    listarPlanos();
                    break;
                case 5:
                    ligado = false;
                    break;
                default:
                    break;
            }
        }
        //scanner.close();
    }
    private static void cadastrarPlano() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do novo plano:");
        String nome = scanner.nextLine();

        System.out.println("Digite o valor mensal do novo plano:");
        String valorMensal = scanner.nextLine();

        Plano plano = new Plano(nome, valorMensal);

        ListaPlanos.add(plano);

        int index = ListaPlanos.size() - 1;
        plano.setId(index);

        System.out.println("Plano cadastrado com sucesso.");

        //scanner.close();
    }


    private static void alterarPlano() {
        Scanner scanner = new Scanner(System.in);
        int id;

        System.out.println("Digite o ID do plano que deseja alterar:");
        id = scanner.nextInt();

        Plano plano = buscaPlanoPorId(id);

        if (plano != null) {
            scanner.nextLine();
            int selected;

            System.out.println("Selecione um campo para alterar:");
            System.out.println("1. Nome do plano");
            System.out.println("2. Valor do plano");
            selected = scanner.nextInt();

            switch (selected) {
                case 1:
                    System.out.println("Digite o novo nome do plano:");
                    String novoNome = scanner.nextLine();
                    plano.setNomePlano(novoNome);
                    break;
                case 2:
                    System.out.println("Digite o novo valor mensal do plano:");
                    String novoValorMensal = scanner.nextLine();
                    plano.setValorMensal(novoValorMensal);
                    break;
                default:
                    System.out.println("Digite uma opcao valida.");
                    break;
            }
            System.out.println("Plano alterado com sucesso.");
        } else {
            System.out.println("ID do plano não encontrado.");
        }

        //scanner.close();
    }

    private static Plano buscaPlanoPorId(int id) {
        for (Plano plano : ListaPlanos) {
            if (plano.getId() == id) {
                return plano;
            }
        }
        return null;
    }
    private static void excluirPlano() {
        Scanner scanner = new Scanner(System.in);
        int id;

        System.out.println("Digite o ID do plano que deseja excluir:");
        id = scanner.nextInt();

        Plano plano = buscaPlanoPorId(id);

        if(plano != null) {
            boolean removido = ListaPlanos.remove(plano);

            if (removido) {
                System.out.println("Plano com ID " + id + " removido com sucesso.");
            } else {
                System.out.println("Ocorreu um erro ao remover o plano com ID " + id + ".");
            }
        }
        else {
            System.out.println("ID do plano não encontrado.");
        }
    }

    private static void listarPlanos() {
        System.out.println("Planos cadastrados:");
        for (Plano plano : ListaPlanos) {
            System.out.println("->ID: " + plano.getId() + ", Nome: " + plano.getNomePlano() + ", Valor mensal: " + plano.getValorMensal());
        }
    }
}
