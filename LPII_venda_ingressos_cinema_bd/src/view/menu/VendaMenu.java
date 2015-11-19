package view.menu;

/**
 * Essa classe VendaMenu acessa as opções do submenu.
 *
 * @author silvinha01
 */
public class VendaMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_EDITAR = 3;
    public static final int OP_EXCLUIR = 4;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1 - Cadastrar Venda\n"
                + "2 - Listar Vendas\n"
                + "3 - Editar Venda\n"
                + "4 - Excluir Venda\n"
                + "0 - Voltar"
                + "\n--------------------------------------");
    }

}
