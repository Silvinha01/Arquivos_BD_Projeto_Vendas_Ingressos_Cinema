package servico;

import dao.FilmeDao;
import dao.FilmeDaoBd;
import java.util.List;
import model.Filme;

/**
 *
 * @author silvinha01
 */
public class FilmeServico {

    public boolean filmeIdExiste(int id) {
        FilmeDao dao = new FilmeDaoBd();
        Filme filme = dao.buscarPorId(id);
        return (filme != null);
    }

    public boolean filmeNomeExiste(String nome) {
        FilmeDao dao = new FilmeDaoBd();
        Filme filme = dao.buscarPorNome(nome);
        return (filme != null);
    }

    public void addFilme(Filme f) {
        new FilmeDaoBd().cadastrar(f);
    }

    public List<Filme> listarFilmes() {
        return (new FilmeDaoBd().listarFilmes());
    }

    public Filme mostrarFilmePorId(int id) {
        return (new FilmeDaoBd().buscarPorId(id));
    }

    public Filme mostrarFilmePorNome(String nome) {
        return (new FilmeDaoBd().buscarPorNome(nome));
    }

    public List<Filme> listarFilmesPorNome(String nome) {
        return (new FilmeDaoBd().listBuscarPorNome(nome));
    }

    public void editarFilme(Filme f) {
        FilmeDao dao = new FilmeDaoBd();
        dao.editar(f);
    }

    public void excluirFilme(Filme f) {
        new FilmeDaoBd().excluir(f);
    }

}
