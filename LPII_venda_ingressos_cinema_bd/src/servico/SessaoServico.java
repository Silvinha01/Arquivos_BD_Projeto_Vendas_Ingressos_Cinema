package servico;

import dao.SessaoDao;
import dao.SessaoDaoBd;
import java.util.Date;
import java.util.List;
import model.Sessao;

/**
 *
 * @author silvinha01
 */
public class SessaoServico {

    public boolean sessaoIdExiste(int id) {
        SessaoDao dao = new SessaoDaoBd();
        Sessao sessao = dao.buscarPorId(id);
        return (sessao != null);
    }

    public boolean sessaoHorarioExiste(Date horario) {
        SessaoDao dao = new SessaoDaoBd();
        Sessao sessao = dao.buscarPorHorario(horario);
        return (sessao != null);
    }

    public void addSessao(Sessao sessao) {
        new SessaoDaoBd().cadastrar(sessao);
    }

    public List<Sessao> listarSessoes() {
        return (new SessaoDaoBd().listarSessoes());
    }

    public Sessao mostrarSessaoPorId(int id) {
        return (new SessaoDaoBd().buscarPorId(id));
    }

    public List<Sessao> listarSessoesPorHorario(Date horario) {
        return (new SessaoDaoBd().listarPorHorario(horario));
    }

    public void editarSessao(Sessao s) {
        SessaoDao dao = new SessaoDaoBd();
        dao.editar(s);
    }

    public void excluirSessao(Sessao s) {
        new SessaoDaoBd().excluir(s);
    }

    /**
     * Esse método verifica se uma determinada sala e um determinado horário
     * estão ocupados na lista de sessões.
     *
     * @author silvinha01
     * @param idsala
     * @param horario
     * @return
     */
    public boolean sessaoSalaHorarioOcupados(int idsala, Date horario) {
        SessaoDao dao = new SessaoDaoBd();
        Sessao sessao = dao.buscarPorSalaEHorario(idsala, horario);
        if (sessao == null) {
            return false;
        }
        return true;
    }

}
