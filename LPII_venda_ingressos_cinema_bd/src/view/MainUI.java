package view;

import java.text.ParseException;
import java.util.InputMismatchException;
import servico.FilmeServico;
import servico.SalaServico;
import servico.SessaoServico;
import servico.VendaServico;
import util.Console;
import view.menu.MainMenu;

/**
 * Essa classe acessa o menu principal e os submenus.
 *
 * @author silvinha01
 */
public class MainUI {

    private FilmeServico servicoFilme;
    private SalaServico servicoSala;
    private SessaoServico servicoSessao;
    private VendaServico servicoVenda;

    /**
     * Inicia a classe MainUI com seus dados. servicoFilme recebe FilmeServico.
     */
    public MainUI() {
        servicoFilme = new FilmeServico();
        servicoSala = new SalaServico();
        servicoVenda = new VendaServico();
    }

    /**
     * Esse método executar acessa o menu principal MainMenu e suas opções.
     *
     * @author silvinha01
     * @throws java.text.ParseException
     */
    public void executar() throws ParseException {
        int opcao = - 1;
        do {
            try {
                System.out.println(MainMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção: ");

                switch (opcao) {
                    case MainMenu.OP_FILMES:
                        new FilmeUI().executar();
                        break;
                    case MainMenu.OP_SALAS:
                        new SalaUI().executar();
                        break;
                    case MainMenu.OP_SESSOES:
                        new SessaoUI().executar();
                        break;
                    case MainMenu.OP_VENDAS:
                        new VendaUI().executar();
                        break;
                    case MainMenu.OP_RELATORIOS:
                        new RelatorioVendasUI().executar();
                        break;
                    case MainMenu.OP_SAIR:
                        System.out.println("Aplicação finalizada!!!");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Opção Inválida! Não pode digitar letras ou caracteres especiais!");
            }
        } while (opcao != MainMenu.OP_SAIR);
    }

}
