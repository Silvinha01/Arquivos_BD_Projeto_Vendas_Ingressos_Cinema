package view.menu;

/**
 * Essa classe SessaoMenu acessa as opções do submenu.
 *
 * @author silvinha01
 */
public class SessaoMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_BUSCAR_ID = 3;
    public static final int OP_LISTAR_HORARIO = 4;
    public static final int OP_EDITAR = 5;
    public static final int OP_EXCLUIR = 6;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1 - Cadastrar Sessão\n"
                + "2 - Listar Sessões\n"
                + "3 - Buscar Sessão por Código\n"
                + "4 - Listar Sessões por Horário\n"
                + "5 - Editar Sessão\n"
                + "6 - Excluir Sessão\n"
                + "0 - Voltar"
                + "\n--------------------------------------");
    }

}
