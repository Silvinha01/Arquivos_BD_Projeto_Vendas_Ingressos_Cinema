package view;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import model.Filme;
import model.Sala;
import model.Sessao;
import servico.FilmeServico;
import servico.SalaServico;
import servico.SessaoServico;
import util.Console;
import util.DateUtil;
import view.menu.SessaoMenu;

/**
 * Essa classe contém os métodos para sessões.
 *
 * @author silvinha01
 */
public class SessaoUI {

    private SessaoServico servicoSessao;
    private SalaServico servicoSala;
    private FilmeServico servicoFilme;

    /**
     * Inicia a classe SessaoUI com seus dados.
     */
    public SessaoUI() {
        servicoSessao = new SessaoServico();
        servicoSala = new SalaServico();
        servicoFilme = new FilmeServico();
    }

    /**
     * Esse método executar acessa o submenu SessaoMenu e suas opções.
     *
     * @author silvinha01
     * @throws java.text.ParseException
     */
    public void executar() throws ParseException {
        int opcao = 0;
        do {
            try {
                System.out.println(SessaoMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção: ");
                switch (opcao) {
                    case SessaoMenu.OP_CADASTRAR:
                        cadastrarSessao();
                        break;
                    case SessaoMenu.OP_LISTAR:
                        listarSessoes();
                        break;
                    case SessaoMenu.OP_BUSCAR_ID:
                        buscarSessaoPorId();
                        break;
                    case SessaoMenu.OP_LISTAR_HORARIO:
                        listarSessoesPorHorario();
                        break;
                    case SessaoMenu.OP_EDITAR:
                        editarSessao();
                        break;
                    case SessaoMenu.OP_EXCLUIR:
                        excluirSessao();
                        break;
                    case SessaoMenu.OP_VOLTAR:
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
     * Esse método cadastra uma nova sessão na lista de sessões.
     *
     * @author silvinha01
     */
    private void cadastrarSessao() {

        //Relaciona as salas:
        System.out.println("\nRelacione a sala abaixo para a sessão: ");
        //Mostra todas as salas cadastradas. A SalaUI tem uma função exclusiva para mostrar na tela.
        new SalaUI().listarSalas();
        int numSala = Console.scanInt("\nDigite o Número da Sala: ");
        Sala sala = servicoSala.mostrarSalaPorId(numSala);
        if (!servicoSala.salaIdExiste(numSala)) {
            System.out.println("Sala não encontrada!");
        } else {

            int qtdAssentosDisponiveis = Console.scanInt("Digite a quantidade de assentos disponíveis na sala: ");
            if (qtdAssentosDisponiveis != sala.getQtdAssentos()) {
                System.out.println("A quantidade de assentos digitada não é igual a quantidade de assentos disponíveis na sala!");
            } else {

                //Relaciona os filmes:
                System.out.println("\nRelacione o filme abaixo para a seção: ");
                //Mostra todos os filmes cadastrados. O FilmeUI tem uma função exclusiva para mostrar na tela.
                new FilmeUI().listarFilmes();
                int numFilme = Console.scanInt("\nDigite o Código do filme: ");
                Filme filme = servicoFilme.mostrarFilmePorId(numFilme);
                if (!servicoFilme.filmeIdExiste(numFilme)) {
                    System.out.println("Filme não encontrado!");
                } else {

                    String horaString = Console.scanString("Hora (hh:mm): ");
                    //Instancia a sessão
                    try {
                        if (servicoSessao.sessaoSalaHorarioOcupados(numFilme, DateUtil.stringToHour(horaString))) {
                            System.out.println("Sala e horario estão ocupados!");
                        } else {
                            servicoSessao.addSessao(new Sessao(sala, qtdAssentosDisponiveis, filme, DateUtil.stringToHour(horaString)));
                            System.out.println("Sessão cadastrada com sucesso!");
                        }
                    } catch (ParseException ex) {
                        System.out.println("Formato de Hora inválido!");
                    }
                }
            }
        }
    }

    /**
     * Esse método lista as sessões cadastradas na lista de sessões.
     *
     * @author silvinha01
     */
    public void listarSessoes() {
        System.out.println("\nLista de Sessões");

        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-15s", "CÓDIGO SESSÃO") + "\t"
                + String.format("%-10s", "|SALA") + "\t"
                + String.format("%-10s", "|HORÁRIO") + "\t"
                + String.format("%-20s", "|ASSENTOS DISPONÍVEIS") + "\t"
                + String.format("%-20s", "|FILME"));

        for (Sessao sessao : servicoSessao.listarSessoes()) {
            System.out.println(String.format("%-15s", sessao.getId()) + "\t"
                    + String.format("%-10s", "|" + sessao.getSala().getId()) + "\t"
                    + String.format("%-10s", "|" + DateUtil.hourToString(sessao.getHorario())) + "\t"
                    + String.format("%-20s", "|" + sessao.getQtdAssentosDisponiveis()) + "\t"
                    + String.format("%-20s", "|" + sessao.getFilme().getNomeFilme()));
        }
    }

    /**
     * Esse método busca uma sessão pelo filme digitado na lista de sessões.
     *
     * @author silvinha01
     */
    private void buscarSessaoPorId() {
        System.out.println("\nBusca de Sessão por Código");
        int id = Console.scanInt("Digite o código da sessão: ");
        if (servicoSessao.sessaoIdExiste(id)) {
            Sessao sessao = servicoSessao.mostrarSessaoPorId(id);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-15s", "CÓDIGO SESSÃO") + "\t"
                    + String.format("%-10s", "|SALA") + "\t"
                    + String.format("%-10s", "|HORÁRIO") + "\t"
                    + String.format("%-20s", "|ASSENTOS DISPONÍVEIS") + "\t"
                    + String.format("%-20s", "|FILME"));

            System.out.println(String.format("%-15s", sessao.getId()) + "\t"
                    + String.format("%-10s", "|" + sessao.getSala().getId()) + "\t"
                    + String.format("%-10s", "|" + DateUtil.hourToString(sessao.getHorario())) + "\t"
                    + String.format("%-20s", "|" + sessao.getQtdAssentosDisponiveis()) + "\t"
                    + String.format("%-20s", "|" + sessao.getFilme().getNomeFilme()));
        } else {
            System.out.println("Este código não está cadastrado ou não será exibido em nenhuma sessão!");
        }
    }

    /**
     * Esse método busca uma sessão pelo horário digitado na lista de sessões.
     *
     * @author silvinha01
     */
    private void listarSessoesPorHorario() throws ParseException {
        System.out.println("\nBusca de Sessão por Horário");
        String hora = Console.scanString("Digite o Horário: (hh:mm): ");
        Date horario;
        try {
            horario = DateUtil.stringToHour(hora);

            if (servicoSessao.sessaoHorarioExiste(horario)) {

                System.out.println("-----------------------------\n");
                System.out.println(String.format("%-15s", "CÓDIGO SESSÃO") + "\t"
                        + String.format("%-10s", "|HORÁRIO") + "\t"
                        + String.format("%-10s", "|SALA") + "\t"
                        + String.format("%-20s", "|ASSENTOS DISPONÍVEIS") + "\t"
                        + String.format("%-20s", "|FILME"));
                for (Sessao sessao : servicoSessao.listarSessoesPorHorario(horario)) {
                    System.out.println(String.format("%-15s", sessao.getId()) + "\t"
                            + String.format("%-10s", "|" + DateUtil.hourToString(sessao.getHorario())) + "\t"
                            + String.format("%-10s", "|" + sessao.getSala().getId()) + "\t"
                            + String.format("%-20s", "|" + sessao.getQtdAssentosDisponiveis()) + "\t"
                            + String.format("%-20s", "|" + sessao.getFilme().getNomeFilme()));
                }
            } else {
                System.out.println("Neste Horário não existem sessões cadastradas!");
            }
        } catch (ParseException ex) {
            System.out.println("Hora no formato inválido!");
        }
    }

    private void editarSessao() {
        System.out.println("\nEdição de sessão por Código");

        //Mostra todas as sessões cadastradas.
        new SessaoUI().listarSessoes();

        int id = Console.scanInt("\nDigite o Código da sessão a ser editada: ");
        if (!servicoSessao.sessaoIdExiste(id)) {
            System.out.println("\nEste Código não está cadastrado na lista de sessões!");
        } else {

            Sessao sessao = servicoSessao.mostrarSessaoPorId(id);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-15s", "CÓDIGO SESSÃO") + "\t"
                    + String.format("%-10s", "|SALA") + "\t"
                    + String.format("%-20s", "|ASSENTOS DISPONÍVEIS") + "\t"
                    + String.format("%-20s", "|FILME") + "\t"
                    + String.format("%-10s", "|HORÁRIO"));

            System.out.println(String.format("%-15s", sessao.getId()) + "\t"
                    + String.format("%-10s", "|" + sessao.getSala().getId()) + "\t"
                    + String.format("%-20s", "|" + sessao.getQtdAssentosDisponiveis()) + "\t"
                    + String.format("%-20s", "|" + sessao.getFilme().getNomeFilme()) + "\t"
                    + String.format("%-10s", "|" + DateUtil.hourToString(sessao.getHorario())));

            //Mostra todos os filmes cadastrados. O FilmeUI tem uma função exclusiva para mostrar na tela.
            new FilmeUI().listarFilmes();
            int numFilme = Console.scanInt("\nCódigo do filme (Atual: " + sessao.getFilme().getId() + "): ");
            Filme filme = servicoFilme.mostrarFilmePorId(numFilme);
            if (!servicoFilme.filmeIdExiste(numFilme)) {
                System.out.println("\nFilme não encontrado!");
            } else {

                String horaString = Console.scanString("\nHora (Atual: " + DateUtil.hourToString(sessao.getHorario()) + "(hh:mm): ");

                try {
                    sessao.setFilme(filme);
                    sessao.setHorario(DateUtil.stringToHour(horaString));

                    servicoSessao.editarSessao(sessao);
                    System.out.println("\nSessão " + id + " alterada com sucesso!");
                } catch (ParseException ex) {
                    System.out.println("\nHora no formato inválido!");
                }
            }
        }
    }

    private void excluirSessao() {
        System.out.println("\nExclusão de sessão por Código");

        //Mostra todas as sessões cadastradas.
        new SessaoUI().listarSessoes();

        int id = Console.scanInt("\nDigite o Código da sessão ser excluída: ");
        if (!servicoSessao.sessaoIdExiste(id)) {
            System.out.println("\nEste Código não está cadastrado na lista de sessões!");
        } else {

            Sessao sessao = servicoSessao.mostrarSessaoPorId(id);

            if (sessao.getQtdAssentosDisponiveis() != sessao.getSala().getQtdAssentos()) {
                System.out.println("\nEsta sessão já tem ingressos vendidos, portanto não pode ser excluída!");
            } else {

                System.out.println("-----------------------------\n");
                System.out.println(String.format("%-15s", "CÓDIGO SESSÃO") + "\t"
                        + String.format("%-10s", "|SALA") + "\t"
                        + String.format("%-10s", "|HORÁRIO") + "\t"
                        + String.format("%-20s", "|ASSENTOS DISPONÍVEIS") + "\t"
                        + String.format("%-20s", "|FILME"));

                System.out.println(String.format("%-15s", sessao.getId()) + "\t"
                        + String.format("%-10s", "|" + sessao.getSala().getId()) + "\t"
                        + String.format("%-10s", "|" + DateUtil.hourToString(sessao.getHorario())) + "\t"
                        + String.format("%-20s", "|" + sessao.getQtdAssentosDisponiveis()) + "\t"
                        + String.format("%-20s", "|" + sessao.getFilme().getNomeFilme()));

                System.out.println("\nVocê tem certeza que quer excluir esta sessão? 1 - Sim / 2 - Não");
                int escolha = Console.scanInt("Digite a sua escolha: ");
                if (escolha == 1) {
                    servicoSessao.excluirSessao(sessao);
                    System.out.println("\nA sessão foi excluída com sucesso!!!");
                } else {
                    System.out.println("\nA sessão não foi excluída da lista!!!");
                }
            }
        }
    }

}
