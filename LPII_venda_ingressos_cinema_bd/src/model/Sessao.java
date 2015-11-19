package model;

import java.util.Date;

/**
 *
 * @author silvinha01
 */
public class Sessao {

    private int id;
    private Sala sala;
    private int qtdAssentosDisponiveis;
    private Filme filme;
    private Date horario;

    /**
     * Inicia o objeto sessao com seus dados.
     *
     * @param sala Objeto Sala voltado especifica a sala de uma sessão.
     * @param qtdAssentosDisponiveis Inteiro que referencia o atributo
     * qtdAssentosDisponiveis.
     * @param filme Objeto Filme especifica o filme de uma sessão.
     * @param horario Objeto da Classe Date especifica o horário de uma sessão.
     */
    public Sessao(Sala sala, int qtdAssentosDisponiveis, Filme filme, Date horario) {
        this.sala = sala;
        this.qtdAssentosDisponiveis = qtdAssentosDisponiveis;
        this.filme = filme;
        this.horario = horario;
    }

    /**
     * Inicia o objeto sessao com seus dados.
     *
     * @param id é auto incremento em bd.
     * @param sala Objeto Sala voltado especifica a sala de uma sessão.
     * @param qtdAssentosDisponiveis Inteiro que referencia o atributo
     * qtdAssentosDisponiveis.
     * @param filme Objeto Filme especifica o filme de uma sessão.
     * @param horario Objeto da Classe Date especifica o horário de uma sessão.
     */
    public Sessao(int id, Sala sala, int qtdAssentosDisponiveis, Filme filme, Date horario) {
        this.id = id;
        this.sala = sala;
        this.qtdAssentosDisponiveis = qtdAssentosDisponiveis;
        this.filme = filme;
        this.horario = horario;
    }

    /**
     * Retorna o id de uma sessão.
     *
     * @return id de uma sessão.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna a sala de uma sessão
     *
     * @return objeto sala da sessão
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * Altera a sala de uma sessão
     *
     * @param sala objeto sala da sessão
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     * Retorna a quantidade de assentos disponíveis em uma sessão.
     *
     * @return int qtdAssentos.
     */
    public int getQtdAssentosDisponiveis() {
        return qtdAssentosDisponiveis;
    }

    /**
     * Altera a quantidade de assentos disponiveis de uma sessão.
     *
     * @param qtdAssentosDisponiveis int qtdAssentos.
     */
    public void setQtdAssentosDisponiveis(int qtdAssentosDisponiveis) {
        this.qtdAssentosDisponiveis = qtdAssentosDisponiveis;
    }

    /**
     * Retorna o filme de uma sessão
     *
     * @return objeto filme da sessão
     */
    public Filme getFilme() {
        return filme;
    }

    /**
     * Altera o filme de uma sessão
     *
     * @param filme objeto filme da sessão
     */
    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    /**
     * Retorna o horário de uma sessão
     *
     * @return objeto horário da sessão
     */
    public Date getHorario() {
        return horario;
    }

    /**
     * Altera o horário de uma sessão
     *
     * @param horario objeto horário da sessão
     */
    public void setHorario(Date horario) {
        this.horario = horario;
    }

}
