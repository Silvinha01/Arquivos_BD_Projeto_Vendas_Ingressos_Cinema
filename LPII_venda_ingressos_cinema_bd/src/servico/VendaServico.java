package servico;

import dao.VendaDao;
import dao.VendaDaoBd;
import java.util.Date;
import java.util.List;
import model.Venda;

/**
 *
 * @author silvinha01
 */
public class VendaServico {

    public boolean vendaIdExiste(int id) {
        VendaDao dao = new VendaDaoBd();
        Venda venda = dao.buscarPorId(id);
        return (venda != null);
    }

    public boolean vendaSessaoExiste(int idsessao) {
        VendaDao dao = new VendaDaoBd();
        Venda venda = dao.buscarPorSessao(idsessao);
        return (venda != null);
    }

    public boolean vendaSalaExiste(int idsala) {
        VendaDao dao = new VendaDaoBd();
        Venda venda = dao.buscarPorSala(idsala);
        return (venda != null);
    }

    public boolean vendaHorarioExiste(Date horario) {
        VendaDao dao = new VendaDaoBd();
        Venda venda = dao.buscarPorHorario(horario);
        return (venda != null);
    }

    public boolean vendaFilmeExiste(int idfilme) {
        VendaDao dao = new VendaDaoBd();
        Venda venda = dao.buscarPorFilme(idfilme);
        return (venda != null);
    }

    public void addVenda(Venda venda) {
        new VendaDaoBd().cadastrar(venda);
    }

    public List<Venda> listarVendas() {
        return (new VendaDaoBd().listarVendas());
    }

    public Venda mostrarVendaPorId(int idvenda) {
        return (new VendaDaoBd().buscarPorId(idvenda));
    }

    public Venda mostrarVendaPorSessao(int idsessao) {
        return (new VendaDaoBd().buscarPorSessao(idsessao));
    }

    public List<Venda> listarVendasPorSessao(int idsessao) {
        return (new VendaDaoBd().listarPorSessao(idsessao));
    }

    public Venda mostrarVendaPorHorario(Date horario) {
        return (new VendaDaoBd().buscarPorHorario(horario));
    }

    public List<Venda> listarVendasPorHorario(Date horario) {
        return (new VendaDaoBd().listarPorHorario(horario));
    }

    public Venda mostrarVendaPorFilme(int idfilme) {
        return (new VendaDaoBd().buscarPorFilme(idfilme));
    }

    public List<Venda> listarVendasPorFilme(int idfilme) {
        return (new VendaDaoBd().listarPorFilme(idfilme));
    }

    public Venda mostrarVendaPorSala(int idsala) {
        return (new VendaDaoBd().buscarPorSala(idsala));
    }

    public List<Venda> listarVendasPorSala(int idsala) {
        return (new VendaDaoBd().listarPorSala(idsala));
    }

    public void editarVenda(Venda v) {
        VendaDao dao = new VendaDaoBd();
        dao.editar(v);
    }

    public void excluirVenda(Venda v) {
        new VendaDaoBd().excluir(v);
    }

}
