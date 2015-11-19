package view;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import model.Venda;
import servico.VendaServico;
import util.Console;
import util.DateUtil;
import view.menu.RelatorioVendasMenu;

/**
 * Essa classe contém os métodos para relatórios.
 *
 * @author silvinha01
 */
public class RelatorioVendasUI {

    private VendaServico servicoVenda;

    /**
     * Inicia a classe RelatorioUI com seus dados.
     */
    public RelatorioVendasUI() {
        servicoVenda = new VendaServico();
    }

    /**
     * Esse método executar acessa o submenu RelatorioVendasMenu e suas opções.
     *
     * @author silvinha01
     * @throws java.text.ParseException
     */
    public void executar() throws ParseException {
        int opcao = 0;
        do {
            try {
                System.out.println(RelatorioVendasMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção: ");
                switch (opcao) {
                    case RelatorioVendasMenu.OP_VENDA_ID:
                        buscarVendaPorId();
                        break;
                    case RelatorioVendasMenu.OP_VENDAS_FILME:
                        listarVendasPorFilme();
                        break;
                    case RelatorioVendasMenu.OP_VENDAS_HORARIO:
                        listarVendasPorHorario();
                        break;
                    case RelatorioVendasMenu.OP_VENDAS_SALA:
                        listarVendasPorSala();
                        break;
                    case RelatorioVendasMenu.OP_VENDAS_SESSAO:
                        buscarVendaPorId();
                        break;
                    case RelatorioVendasMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Opção Inválida! Não pode digitar letras ou caracteres especiais!");
            }
        } while (opcao != RelatorioVendasMenu.OP_VOLTAR);
    }

    /**
     * Esse método busca uma venda pelo id na lista de vendas.
     *
     * @author silvinha01
     */
    private void buscarVendaPorId() {
        System.out.println("\nLista de Vendas por ID");
        int idvenda = Console.scanInt("Digite o Código da Venda: ");
        if (servicoVenda.vendaIdExiste(idvenda)) {
            Venda venda = servicoVenda.mostrarVendaPorId(idvenda);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "|CÓDIGO VENDA") + "\t"
                    + String.format("%-10s", "|CÓDIGO SESSÃO") + "\t"
                    + String.format("%-20s", "|INGRESSOS VENDIDOS"));

            System.out.println(String.format("%-10s", "|" + venda.getId()) + "\t"
                    + String.format("%-10s", "|" + venda.getSessao().getId()) + "\t"
                    + String.format("%-20s", "|" + venda.getQtdIngressosPorVenda()));
        } else {
            System.out.println("Esta sessão não está cadastrada ou não realizou nenhuma venda!");
        }
    }

    /**
     * Esse método lista as vendas pelo filme digitado na lista de vendas.
     *
     * @author silvinha01
     */
    private void listarVendasPorFilme() {
        System.out.println("\nLista de Vendas por Filme");
        int idfilme = Console.scanInt("Digite o Código do filme: ");

        if (servicoVenda.vendaFilmeExiste(idfilme)) {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "|CÓDIGO VENDA") + "\t"
                    + String.format("%-20s", "|NOME DO FILME") + "\t"
                    + String.format("%-20s", "|INGRESSOS VENDIDOS"));

            for (Venda venda : servicoVenda.listarVendasPorFilme(idfilme)) {
                System.out.println(String.format("%-10s", "|" + venda.getId()) + "\t"
                        + String.format("%-20s", "|" + venda.getSessao().getFilme().getNomeFilme()) + "\t"
                        + String.format("%-20s", "|" + venda.getQtdIngressosPorVenda()));
            }
        } else {
            System.out.println("Este filme não está cadastrado ou não será exibido em nenhuma sessão!");
        }
    }

    /**
     * Esse método lista as vendas pelo horario digitado na lista de vendas.
     *
     * @author silvinha01
     */
    private void listarVendasPorHorario() throws ParseException {
        System.out.println("\nLista de vendas por Horário");
        String hora = Console.scanString("Digite o Horário: (hh:mm): ");
        Date horario;
        try {
            horario = DateUtil.stringToHour(hora);

            if (servicoVenda.vendaHorarioExiste(horario)) {
                System.out.println("-----------------------------\n");
                System.out.println(String.format("%-10s", "|CÓDIGO VENDA") + "\t"
                        + String.format("%-10s", "|HORÁRIO") + "\t"
                        + String.format("%-20s", "|INGRESSOS VENDIDOS"));

                for (Venda venda : servicoVenda.listarVendasPorHorario(horario)) {
                    System.out.println(String.format("%-10s", "|" + venda.getId()) + "\t"
                            + String.format("%-10s", "|" + DateUtil.hourToString(venda.getSessao().getHorario())) + "\t"
                            + String.format("%-20s", "|" + venda.getQtdIngressosPorVenda()));
                }
            } else {
                System.out.println("Neste Horário não existem vendas cadastradas!");
            }
        } catch (ParseException ex) {
            System.out.println("Hora no formato inválido!");
        }
    }

    /**
     * Esse método lista as vendas pela sala digitada na lista de vendas.
     *
     * @author silvinha01
     */
    private void listarVendasPorSala() {
        System.out.println("\nLista de Vendas por Sala");
        int idsala = Console.scanInt("Digite o Número da Sala: ");

        if (servicoVenda.vendaSalaExiste(idsala)) {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "|CÓDIGO VENDA") + "\t"
                    + String.format("%-10s", "|CÓDIGO SALA") + "\t"
                    + String.format("%-20s", "|INGRESSOS VENDIDOS"));

            for (Venda venda : servicoVenda.listarVendasPorSala(idsala)) {
                System.out.println(String.format("%-10s", "|" + venda.getId()) + "\t"
                        + String.format("%-10s", "|" + venda.getSessao().getSala().getId()) + "\t"
                        + String.format("%-20s", "|" + venda.getQtdIngressosPorVenda()));
            }
        } else {
            System.out.println("Esta sala não está cadastrada ou não realizou nenhuma venda!");
        }
    }

    /**
     * Esse método lista as vendas pela sessão na lista de vendas.
     *
     * @author silvinha01
     */
    private void listarVendasPorSessao() {
        System.out.println("\nLista de Vendas por Sessão");
        int numSessao = Console.scanInt("Digite o Código da Sessão: ");

        if (servicoVenda.vendaSessaoExiste(numSessao)) {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "|CÓDIGO VENDA") + "\t"
                    + String.format("%-10s", "|CÓDIGO SESSÃO") + "\t"
                    + String.format("%-20s", "|INGRESSOS VENDIDOS"));

            for (Venda venda : servicoVenda.listarVendasPorSessao(numSessao)) {
                System.out.println(String.format("%-10s", "|" + venda.getId()) + "\t"
                        + String.format("%-10s", "|" + venda.getSessao().getId()) + "\t"
                        + String.format("%-20s", "|" + venda.getQtdIngressosPorVenda()));
            }
        } else {
            System.out.println("Esta sessão não está cadastrada ou não realizou nenhuma venda!");
        }
    }

}
