package model;

/**
 * Essa classe serve para trabalhar com o objeto sala. Trabalha com informações:
 * id, qtdAssentos.
 *
 *
 * @author silvinha01
 */
public class Sala {

    private int id;
    private int qtdAssentos;

    /**
     * Inicia o objeto sala com seus dados.
     *
     * @param qtdAssentos Inteiro que referencia o atributo quantidade de
     * assentos.
     */
    public Sala(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }

    /**
     * Inicia o objeto sala com seus dados.
     *
     * @param id é auto incremento em bd.
     * @param qtdAssentos Inteiro que referencia o atributo quantidade de
     * assentos.
     */
    public Sala(int id, int qtdAssentos) {
        this.id = id;
        this.qtdAssentos = qtdAssentos;
    }

    /**
     * Retorna o id de uma sala.
     *
     * @return int id.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna a quantidade de assentos de uma sala.
     *
     * @return int qtdAssentos.
     */
    public int getQtdAssentos() {
        return qtdAssentos;
    }

    /**
     * Altera a quantidade de assentos de uma sala.
     *
     * @param qtdAssentos int qtdAssentos.
     */
    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }
}
