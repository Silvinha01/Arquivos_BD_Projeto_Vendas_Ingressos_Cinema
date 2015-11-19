package view.menu;

/**
 * Essa classe RelatorioVendasMenu acessa as opções do submenu.
 *
 * @author silvinha01
 */
public class RelatorioVendasMenu {

    public static final int OP_VENDA_ID = 1;
    public static final int OP_VENDAS_FILME = 2;
    public static final int OP_VENDAS_HORARIO = 3;
    public static final int OP_VENDAS_SALA = 4;
    public static final int OP_VENDAS_SESSAO = 5;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1 - Buscar venda por Código\n"
                + "2 - Listar vendas por Filme\n"
                + "3 - Listar vendas por Horário\n"
                + "4 - Listar vendas por Sala\n"
                + "5 - Listar vendas por Sessão\n"
                + "0 - Voltar"
                + "\n--------------------------------------");
    }
}
