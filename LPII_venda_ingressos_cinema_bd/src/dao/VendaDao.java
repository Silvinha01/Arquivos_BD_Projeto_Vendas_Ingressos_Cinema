package dao;

import java.util.Date;
import java.util.List;
import model.Venda;

/**
 *
 * @author silvinha01
 */
public interface VendaDao {

    public void cadastrar(Venda venda);

    public List<Venda> listarVendas();

    public Venda buscarPorFilme(int idfilme);

    public Venda buscarPorHorario(Date horario);

    public Venda buscarPorSala(int idsala);

    public Venda buscarPorSessao(int idsessao);

    public Venda buscarPorId(int idvenda);

    public List<Venda> listarPorFilme(int idfilme);

    public List<Venda> listarPorHorario(Date horario);

    public List<Venda> listarPorSala(int idsala);

    public List<Venda> listarPorSessao(int idsessao);

    public void editar(Venda venda);

    public void excluir(Venda venda);

}
