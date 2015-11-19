package view;

import java.util.InputMismatchException;
import model.Filme;
import servico.FilmeServico;
import util.Console;
import view.menu.FilmeMenu;

/**
 * Essa classe contém os métodos para filmes.
 *
 * @author silvinha01
 */
public class FilmeUI {

    private FilmeServico servicoFilme;

    /**
     * Inicia a classe FilmeUI com seus dados.
     */
    public FilmeUI() {
        servicoFilme = new FilmeServico();
    }

    /**
     * Esse método executar acessa o submenu FilmeMenu e suas opções.
     *
     * @author silvinha01
     */
    public void executar() {
        int opcao = 0;
        do {
            try {
                System.out.println(FilmeMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção: ");
                switch (opcao) {
                    case FilmeMenu.OP_CADASTRAR:
                        cadastrarFilmes();
                        break;
                    case FilmeMenu.OP_LISTAR:
                        listarFilmes();
                        break;
                    case FilmeMenu.OP_BUSCAR_CODIGO:
                        buscarCodigoFilme();
                        break;
                    case FilmeMenu.OP_BUSCAR_NOME:
                        buscarNomeFilme();
                        break;
                    case FilmeMenu.OP_EDITAR:
                        editarFilme();
                        break;
                    case FilmeMenu.OP_EXCLUIR:
                        excluirFilme();
                        break;
                    case FilmeMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Opção Inválida! Não pode digitar letras ou caracteres especiais!");
            }
        } while (opcao != FilmeMenu.OP_VOLTAR);
    }

    /**
     * Esse método cadastra um novo filme na lista de filmes.
     *
     * @author silvinha01
     */
    private void cadastrarFilmes() {
        String nome = Console.scanString("Nome do filme: ");
        if (servicoFilme.filmeNomeExiste(nome)) {
            System.out.println("Filme já está cadastrado!");
        } else {
            String genero = Console.scanString("Gênero: ");
            String sinopse = Console.scanString("Sinopse: ");
            if (!(nome.equals("") || genero.equals("") || sinopse.equals(""))) {
                servicoFilme.addFilme(new Filme(nome, genero, sinopse));
                System.out.println("Filme " + "'" + nome + "'" + " cadastrado com sucesso!");
            } else {
                System.out.println("Não serão aceitos campos em branco!");
            }
        }
    }

    /**
     * Esse método lista os filmes cadastrados na lista de filmes.
     *
     * @author silvinha01
     */
    public void listarFilmes() {
        System.out.println("\nLista de Filmes");

        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                + String.format("%-30s", "|NOME") + "\t"
                + String.format("%-20s", "|GÊNERO") + "\t"
                + String.format("%-20s", "|SINOPSE"));
        for (Filme filme : servicoFilme.listarFilmes()) {
            System.out.println(String.format("%-10s", filme.getId()) + "\t"
                    + String.format("%-30s", "|" + filme.getNomeFilme()) + "\t"
                    + String.format("%-20s", "|" + filme.getGenero()) + "\t"
                    + String.format("%-20s", "|" + filme.getSinopse()));
        }
    }

    /**
     * Esse método busca um filme pelo código digitado na lista de filmes.
     *
     * @author silvinha01
     */
    public void buscarCodigoFilme() {
        System.out.println("\nBusca de filme por Código");
        int id = Console.scanInt("Digite o Código do filme: ");
        if (servicoFilme.filmeIdExiste(id)) {
            Filme filme = servicoFilme.mostrarFilmePorId(id);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|GÊNERO") + "\t"
                    + String.format("%-20s", "|SINOPSE"));

            System.out.println(String.format("%-10s", filme.getId()) + "\t"
                    + String.format("%-20s", "|" + filme.getNomeFilme()) + "\t"
                    + String.format("%-20s", "|" + filme.getGenero()) + "\t"
                    + String.format("%-20s", "|" + filme.getSinopse()));
        } else {
            System.out.println("Este Código não está cadastrado na lista de filmes!");
        }
    }

    /**
     * Esse método busca um filme pelo nome digitado na lista de filmes.
     *
     * @author silvinha01
     */
    public void buscarNomeFilme() {
        System.out.println("\nBusca de filme por Nome");
        String nome = Console.scanString("Digite o Nome do filme: ");
        if (servicoFilme.filmeNomeExiste(nome)) {
            Filme filme = servicoFilme.mostrarFilmePorNome(nome);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-10s", "CÓDIGO") + "\t"
                    + String.format("%-20s", "|GÊNERO") + "\t"
                    + String.format("%-20s", "|SINOPSE"));

            System.out.println(String.format("%-20s", "|" + filme.getNomeFilme()) + "\t"
                    + String.format("%-10s", filme.getId()) + "\t"
                    + String.format("%-20s", "|" + filme.getGenero()) + "\t"
                    + String.format("%-20s", "|" + filme.getSinopse()));

        } else {
            System.out.println("Este filme não está cadastrado na lista de filmes!");
        }
    }

    /**
     * Esse método busca um filme pelo id para editar.
     *
     * @author silvinha01
     */
    private void editarFilme() {
        System.out.println("\nEdição de filme por Código");

        //Mostra todos os filmes cadastrados.
        new FilmeUI().listarFilmes();

        int id = Console.scanInt("\nDigite o Código do filme a ser editado: ");
        if (servicoFilme.filmeIdExiste(id)) {
            Filme filme = servicoFilme.mostrarFilmePorId(id);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|GÊNERO") + "\t"
                    + String.format("%-20s", "|SINOPSE"));

            System.out.println(String.format("%-10s", filme.getId()) + "\t"
                    + String.format("%-20s", "|" + filme.getNomeFilme()) + "\t"
                    + String.format("%-20s", "|" + filme.getGenero()) + "\t"
                    + String.format("%-20s", "|" + filme.getSinopse()));

            String nome = Console.scanString("\nNome (Atual: " + filme.getNomeFilme() + "): ");
            String genero = Console.scanString("Gênero (Atual: " + filme.getGenero() + "): ");
            String sinopse = Console.scanString("Sinopse (Atual: " + filme.getSinopse() + "): ");
            if (!(nome.equals("") || genero.equals("") || sinopse.equals(""))) {

                filme.setNomeFilme(nome);
                filme.setGenero(genero);
                filme.setSinopse(sinopse);
                servicoFilme.editarFilme(filme);
                System.out.println("\nFilme " + nome + " alterado com sucesso!");
            } else {
                System.out.println("Não serão aceitos campos em branco!");
            }
        } else {
            System.out.println("\nEste Código não está cadastrado na lista de filmes!");
        }
    }

    /**
     * Esse método busca um filme pelo código para deletar.
     *
     * @author silvinha01
     */
    public void excluirFilme() {
        System.out.println("\nExclusão de filme por Código");

        //Mostra todos os filmes cadastrados.
        new FilmeUI().listarFilmes();

        int id = Console.scanInt("\nDigite o Código do filme a ser excluído: ");
        if (servicoFilme.filmeIdExiste(id)) {
            Filme filme = servicoFilme.mostrarFilmePorId(id);

            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|GÊNERO") + "\t"
                    + String.format("%-20s", "|SINOPSE"));

            System.out.println(String.format("%-10s", filme.getId()) + "\t"
                    + String.format("%-20s", "|" + filme.getNomeFilme()) + "\t"
                    + String.format("%-20s", "|" + filme.getGenero()) + "\t"
                    + String.format("%-20s", "|" + filme.getSinopse()));

            System.out.println("\nVocê tem certeza que quer exluir este filme? 1 - Sim / 2 - Não");
            int escolha = Console.scanInt("Digite a sua escolha: ");
            if (escolha == 1) {
                servicoFilme.excluirFilme(filme);
                System.out.println("\nO filme foi excluído com sucesso!!!");
            } else {
                System.out.println("\nO filme não foi excluído da lista!!!");
            }
        } else {
            System.out.println("\nEste Código não está cadastrado na lista de filmes!");
        }
    }

}
