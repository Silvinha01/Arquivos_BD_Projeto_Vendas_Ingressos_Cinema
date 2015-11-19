package view.menu;

/**
 * Essa classe FilmeMenu acessa as opções do submenu.
 *
 * @author silvinha01
 */
public class FilmeMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_BUSCAR_CODIGO = 3;
    public static final int OP_BUSCAR_NOME = 4;
    public static final int OP_EDITAR = 5;
    public static final int OP_EXCLUIR = 6;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1 - Cadastrar Filme\n"
                + "2 - Listar Filmes\n"
                + "3 - Buscar Filme por Código\n"
                + "4 - Buscar Filme por Nome\n"
                + "5 - Editar Filme\n"
                + "6 - Excluir Filme\n"
                + "0 - Voltar"
                + "\n--------------------------------------");
    }

}
