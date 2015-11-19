package view;

import java.util.InputMismatchException;
import model.Sessao;
import model.Venda;
import servico.SessaoServico;
import servico.VendaServico;
import util.Console;
import view.menu.SessaoMenu;
import view.menu.VendaMenu;

/**
 * Essa classe contém os métodos para vendas.
 *
 * @author silvinha01
 */
public class VendaUI {

    private VendaServico servicoVenda;
    private SessaoServico servicoSessao;

    /**
     * Inicia a classe VendaUI com seus dados.
     */
    public VendaUI() {
        servicoVenda = new VendaServico();
        servicoSessao = new SessaoServico();
    }

    /**
     * Esse método executar acessa o submenu VendaMenu e suas opções.
     *
     * @author silvinha01
     */
    public void executar() {
        int opcao = 0;
        do {
            try {
                System.out.println(VendaMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção: ");
                switch (opcao) {
                    case VendaMenu.OP_CADASTRAR:
                        cadastrarVenda();
                        break;
                    case VendaMenu.OP_LISTAR:
                        listarVendas();
                        break;
                    case VendaMenu.OP_EDITAR:
                        editarVenda();
                        break;
                    case VendaMenu.OP_EXCLUIR:
                        excluirVenda();
                        break;
                    case VendaMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Opção Inválida! Não pode digitar letras ou caracteres especiais!");
            }
        } while (opcao != SessaoMenu.OP_VOLTAR);
    }

    /**
     * Esse método cadastra uma nova venda na lista de vendas.
     *
     * @author silvinha01
     */
    private void cadastrarVenda() {
        //Relaciona as sessões:
        System.out.println("\nRelacione a sessão abaixo para efetuar a venda: ");
        //Mostra todas as sessões cadastradas no BD. A SessãoUI tem uma função exclusiva para mostrar na tela.
        new SessaoUI().listarSessoes();
        int idSessao = Console.scanInt("\nDigite o Código da Sessão: ");
        Sessao sessao = servicoSessao.mostrarSessaoPorId(idSessao);
        if (servicoSessao.sessaoIdExiste(idSessao)) {

            if ((sessao.getQtdAssentosDisponiveis()) <= 0) {
                System.out.println("\nEsta sessão está com lotação esgotada!");
                System.out.println("\nIngressos disponíveis para venda: " + (sessao.getQtdAssentosDisponiveis()));
            } else {
                System.out.println("\nIngressos disponíveis para venda: " + (sessao.getQtdAssentosDisponiveis()));

                int qtdIngressos = Console.scanInt("\nDigite a quantidade de ingressos que irá comprar: ");

                if ((sessao.getQtdAssentosDisponiveis()) < qtdIngressos) {
                    System.out.println("A quantidade de ingressos disponíveis é inferior à solicitação!");
                    System.out.println("\nIngressos disponíveis para venda: " + (sessao.getQtdAssentosDisponiveis()));

                } else {
                    //Instancia a venda
                    servicoVenda.addVenda(new Venda(sessao, qtdIngressos));
                    System.out.println("Venda cadastrada com sucesso!");
                }
            }
        } else {
            System.out.println("Código da sessão não encontrado!");
        }
    }

    /**
     * Esse método lista as vendas cadastradas na lista de vendas.
     *
     * @author silvinha01
     */
    private void listarVendas() {
        System.out.println("\nLista de Vendas");

        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "CÓDIGO VENDA") + "\t"
                + String.format("%-10s", "CÓDIGO SESSÃO") + "\t"
                + String.format("%-20s", "|INGRESSOS VENDIDOS"));

        for (Venda venda : servicoVenda.listarVendas()) {
            System.out.println(String.format("%-10s", venda.getId()) + "\t"
                    + String.format("%-10s", venda.getSessao().getId()) + "\t"
                    + String.format("%-20s", "|" + venda.getQtdIngressosPorVenda()));
        }
    }

    private void editarVenda() {
        System.out.println("\nEdição de venda por Código");

        //Mostra todas as vendas cadastradas.
        new VendaUI().listarVendas();

        int id = Console.scanInt("\nDigite o Código da venda a ser editada: ");
        if (!servicoVenda.vendaIdExiste(id)) {
            System.out.println("\nEste Código de venda não está cadastrado!");
        } else {

            Venda venda = servicoVenda.mostrarVendaPorId(id);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CÓDIGO VENDA") + "\t"
                    + String.format("%-10s", "CÓDIGO SESSÃO") + "\t"
                    + String.format("%-20s", "|INGRESSOS VENDIDOS"));

            System.out.println(String.format("%-10s", venda.getId()) + "\t"
                    + String.format("%-10s", venda.getSessao().getId()) + "\t"
                    + String.format("%-20s", "|" + venda.getQtdIngressosPorVenda()));

            int qtdIngVenda = Console.scanInt("\nQuantidade de ingressos que irá comprar (Atual: " + venda.getQtdIngressosPorVenda() + "): ");

            venda.setQtdIngressosPorVenda(qtdIngVenda);
            servicoVenda.editarVenda(venda);
            System.out.println("\nVenda " + id + " alterada com sucesso!");
        }
    }

    private void excluirVenda() {
        System.out.println("\nExclusão de venda por Código");

        //Mostra todas as vendas cadastradas.
        new VendaUI().listarVendas();

        int id = Console.scanInt("\nDigite o Código da venda ser excluída: ");
        if (servicoVenda.vendaIdExiste(id)) {
            Venda venda = servicoVenda.mostrarVendaPorId(id);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CÓDIGO VENDA") + "\t"
                    + String.format("%-10s", "CÓDIGO SESSÃO") + "\t"
                    + String.format("%-20s", "|INGRESSOS VENDIDOS"));

            System.out.println(String.format("%-10s", venda.getId()) + "\t"
                    + String.format("%-10s", venda.getSessao().getId()) + "\t"
                    + String.format("%-20s", "|" + venda.getQtdIngressosPorVenda()));

            System.out.println("\nVocê tem certeza que quer excluir esta venda? 1 - Sim / 2 - Não");
            int escolha = Console.scanInt("Digite a sua escolha: ");
            if (escolha == 1) {
                servicoVenda.excluirVenda(venda);
                System.out.println("\nA venda foi excluída com sucesso!!!");
            } else {
                System.out.println("\nA venda não foi excluída da lista!!!");
            }
        } else {
            System.out.println("\nEste Código não está cadastrado na lista de vendas!");
        }
    }

}
