package view;

import java.util.InputMismatchException;
import model.Sala;
import servico.SalaServico;
import util.Console;
import view.menu.SalaMenu;

/**
 * Essa classe contém os métodos para salas.
 *
 * @author silvinha01
 */
public class SalaUI {

    private SalaServico servicoSala;

    /**
     * Inicia a classe SalaUI com seus dados. listaSalas recebe listaSalas.
     */
    public SalaUI() {
        servicoSala = new SalaServico();
    }

    /**
     * Esse método executar acessa o submenu SalaMenu e suas opções.
     *
     * @author silvinha01
     */
    public void executar() {
        int opcao = 0;
        do {
            try {
                System.out.println(SalaMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção: ");
                switch (opcao) {
                    case SalaMenu.OP_CADASTRAR:
                        cadastrarSalas();
                        break;
                    case SalaMenu.OP_LISTAR:
                        listarSalas();
                        break;
                    case SalaMenu.OP_BUSCAR_NUMERO:
                        buscarNumeroSala();
                        break;
                    case SalaMenu.OP_EDITAR:
                        editarSala();
                        break;
                    case SalaMenu.OP_EXCLUIR:
                        excluirSala();
                        break;
                    case SalaMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Opção Inválida! Não pode digitar letras ou caracteres especiais!");
            }
        } while (opcao != SalaMenu.OP_VOLTAR);
    }

    /**
     * Esse método cadastra uma nova sala na lista de salas.
     *
     * @author silvinha01
     */
    private void cadastrarSalas() {
        int qtdAssentos = Console.scanInt("Quantidade de assentos: ");
        servicoSala.addSala(new Sala(qtdAssentos));
        System.out.println("Sala cadastrada com sucesso!");
    }

    /**
     * Esse método lista as salas cadastradas na lista de salas.
     *
     * @author silvinha01
     */
    public void listarSalas() {
        System.out.println("\nLista de Salas");
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "NÚMERO") + "\t"
                + String.format("%-20s", "|QUANTIDADE DE ASSENTOS"));
        for (Sala sala : servicoSala.listarSalas()) {
            System.out.println(String.format("%-10s", sala.getId()) + "\t"
                    + String.format("%-20s", "|" + sala.getQtdAssentos()));
        }
    }

    /**
     * Esse método busca uma sala pelo número digitado na lista de salas.
     *
     * @author silvinha01
     */
    public void buscarNumeroSala() {
        System.out.println("\nBusca de sala por número");
        int id = Console.scanInt("Digite o Número da sala: ");
        if (servicoSala.salaIdExiste(id)) {
            Sala sala = servicoSala.mostrarSalaPorId(id);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "NÚMERO") + "\t"
                    + String.format("%-20s", "|QUANTIDADE DE ASSENTOS"));

            System.out.println(String.format("%-10s", sala.getId()) + "\t"
                    + String.format("%-20s", "|" + sala.getQtdAssentos()));
        } else {
            System.out.println("Este Código de sala não está cadastrado!");
        }
    }

    /**
     * Esse método busca uma sala pelo código para editar.
     *
     * @author silvinha01
     */
    private void editarSala() {
        System.out.println("\nEdição de sala por Código");

        //Mostra todas as salas cadastradas.
        new SalaUI().listarSalas();

        int id = Console.scanInt("\nDigite o Código da sala a ser editada: ");
        if (servicoSala.salaIdExiste(id)) {
            Sala sala = servicoSala.mostrarSalaPorId(id);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "NÚMERO") + "\t"
                    + String.format("%-20s", "|QUANTIDADE DE ASSENTOS"));

            System.out.println(String.format("%-10s", sala.getId()) + "\t"
                    + String.format("%-20s", "|" + sala.getQtdAssentos()));

            int qtdAssentos = Console.scanInt("Quantidade de Assentos (Atual: " + sala.getQtdAssentos() + "): ");

            sala.setQtdAssentos(qtdAssentos);
            servicoSala.editarSala(sala);
            System.out.println("\nSala " + id + " alterada com sucesso!");
        } else {
            System.out.println("\nEste Código de sala não está cadastrado!");
        }
    }

    /**
     * Esse método busca uma sala pelo código para excluir.
     *
     * @author silvinha01
     */
    public void excluirSala() {
        System.out.println("\nExclusão de sala por Código");

        //Mostra todas as salas cadastradas.
        new SalaUI().listarSalas();

        int id = Console.scanInt("\nDigite o Código da sala a ser excluída: ");
        if (servicoSala.salaIdExiste(id)) {
            Sala sala = servicoSala.mostrarSalaPorId(id);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "NÚMERO") + "\t"
                    + String.format("%-20s", "|QUANTIDADE DE ASSENTOS"));

            System.out.println(String.format("%-10s", sala.getId()) + "\t"
                    + String.format("%-20s", "|" + sala.getQtdAssentos()));

            System.out.println("\nVocê tem certeza que quer excluir esta sala? 1 - Sim / 2 - Não");
            int escolha = Console.scanInt("Digite a sua escolha: ");
            if (escolha == 1) {
                servicoSala.excluirSala(sala);
                System.out.println("\nA sala foi excluída com sucesso!!!");
            } else {
                System.out.println("\nA sala não foi excluída da lista!!!");
            }
        } else {
            System.out.println("Este Código de sala não está cadastrado!");
        }
    }

}
