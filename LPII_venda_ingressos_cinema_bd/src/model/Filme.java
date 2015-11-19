package model;

/**
 * Essa classe serve para trabalhar com o objeto filme. Trabalha com
 * informações: código, nomeFilme, genero e sinopse.
 *
 * @author silvinha01
 */
public class Filme {

    private int id;
    private String nomeFilme;
    private String genero;
    private String sinopse;

    /**
     * Inicia o objeto filme com seus dados.
     *
     * @param nomeFilme String que referencia o atributo nome do filme.
     * @param genero String que referencia o atributo genero do filme.
     * @param sinopse String que referencia o atributo sinopse do filme.
     */
    public Filme(String nomeFilme, String genero, String sinopse) {
        this.nomeFilme = nomeFilme;
        this.genero = genero;
        this.sinopse = sinopse;
    }

    /**
     * Inicia o objeto filme com seus dados.
     *
     * @param id O Id é auto incremento em bd.
     * @param nomeFilme String que referencia o atributo nome do filme.
     * @param genero String que referencia o atributo genero do filme.
     * @param sinopse String que referencia o atributo sinopse do filme.
     */
    public Filme(int id, String nomeFilme, String genero, String sinopse) {
        this.id = id;
        this.nomeFilme = nomeFilme;
        this.genero = genero;
        this.sinopse = sinopse;
    }

    /**
     * Retorna o id de um filme.
     *
     * @return id de um filme.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o nome de um filme.
     *
     * @return String nomeFilme.
     */
    public String getNomeFilme() {
        return nomeFilme;
    }

    /**
     * Altera o nome de um filme.
     *
     * @param nomeFilme String nomeFilme.
     */
    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    /**
     * Retorna o gênero de um filme.
     *
     * @return String genero.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Altera o gênero de um filme.
     *
     * @param genero String genero.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Retorna a sinopse de um filme.
     *
     * @return String sinopse.
     */
    public String getSinopse() {
        return sinopse;
    }

    /**
     * Altera a sinopse de um filme.
     *
     * @param sinopse String sinopse.
     */
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

}
