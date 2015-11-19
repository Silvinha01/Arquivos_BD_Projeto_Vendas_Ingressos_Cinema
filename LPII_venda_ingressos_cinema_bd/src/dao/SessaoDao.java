package dao;

import java.util.Date;
import java.util.List;
import model.Sessao;

/**
 *
 * @author silvinha01
 */
public interface SessaoDao {

    public void cadastrar(Sessao sessao);

    public List<Sessao> listarSessoes();

    public Sessao buscarPorId(int id);

    public Sessao buscarPorSalaEHorario(int idsala, Date horario);

    public Sessao buscarPorHorario(Date horario);

    public List<Sessao> listarPorHorario(Date horario);

    public void editar(Sessao sessao);

    public void excluir(Sessao sessao);

}
