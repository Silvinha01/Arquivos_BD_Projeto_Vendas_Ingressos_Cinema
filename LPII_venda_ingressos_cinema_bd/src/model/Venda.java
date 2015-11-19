package model;

/**
 *
 * @author silvinha01
 */
public class Venda {

    private int id;
    private Sessao sessao;
    private int qtdIngressosPorVenda;

    /**
     * Inicia o objeto venda com seus dados.
     *
     * @param sessao Objeto Sessao voltado para especificar a sessão de uma venda.
     * @param qtdIngressosPorVenda inteiro que referencia o atributo quantidade
     * de ingressos por venda.
     */
    public Venda(Sessao sessao, int qtdIngressosPorVenda) {
        this.sessao = sessao;
        this.qtdIngressosPorVenda = qtdIngressosPorVenda;
    }

    /**
     * Inicia o objeto venda com seus dados.
     *
     * @param id é auto incremento em bd.
     * @param sessao Objeto Sessao voltado para especificar a sessão de uma
     * venda.
     */
    public Venda(int id, Sessao sessao) {
        this.id = id;
        this.sessao = sessao;
    }

    /**
     * Inicia o objeto venda com seus dados.
     *
     * @param id é auto incremento em bd.
     * @param sessao Objeto Sessao voltado para especificar a sessão de uma
     * venda.
     * @param qtdIngressosPorVenda inteiro que referencia o atributo quantidade
     * de ingressos por venda.
     */
    public Venda(int id, Sessao sessao, int qtdIngressosPorVenda) {
        this.id = id;
        this.sessao = sessao;
        this.qtdIngressosPorVenda = qtdIngressosPorVenda;
    }

    /**
     * Retorna o id de uma venda.
     *
     * @return id de uma venda.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna a sessão de uma venda
     *
     * @return objeto sessão da venda
     */
    public Sessao getSessao() {
        return sessao;
    }

    /**
     * Altera a sessão de uma venda.
     *
     * @param sessao Objeto sessao.
     */
    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    /**
     * Retorna a quantidade de ingressos por venda de uma venda.
     *
     * @return int qtdIngressosPorVenda
     */
    public int getQtdIngressosPorVenda() {
        return qtdIngressosPorVenda;
    }

    /**
     * Altera a quantidade de ingressos de uma venda.
     *
     * @param qtdIngressosPorVenda
     */
    public void setQtdIngressosPorVenda(int qtdIngressosPorVenda) {
        this.qtdIngressosPorVenda = qtdIngressosPorVenda;
    }

}
