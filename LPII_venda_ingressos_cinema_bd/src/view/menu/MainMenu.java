package view.menu;

/**
 * Essa classe MainMenu como menu principal acessa os submenus.
 *
 * @author silvinha01
 */
public class MainMenu {

    public static final int OP_FILMES = 1;
    public static final int OP_SALAS = 2;
    public static final int OP_SESSOES = 3;
    public static final int OP_VENDAS = 4;
    public static final int OP_RELATORIOS = 5;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1 - Menu Filmes\n"
                + "2 - Menu Salas\n"
                + "3 - Menu Sessões\n"
                + "4 - Menu Vendas\n"
                + "5 - Menu Relatórios Vendas\n"
                + "0 - Sair da Aplicação"
                + "\n--------------------------------------");
    }

}
