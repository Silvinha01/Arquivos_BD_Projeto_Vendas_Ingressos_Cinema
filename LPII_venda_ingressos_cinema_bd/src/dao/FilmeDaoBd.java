package dao;

import banco.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Filme;

/**
 *
 * @author silvinha01
 */
public class FilmeDaoBd implements FilmeDao {

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    //Método cadastrar alterado para receber o id auto increment 
    //e já inserir no objeto filme (recebido por parâmetro).
    public void cadastrar(Filme filme) {
        try {
            String sql = "INSERT INTO filme (nomefilme, genero, sinopse) "
                    + "VALUES (?,?,?)";

            conectar(sql);
            comando.setString(1, filme.getNomeFilme());
            comando.setString(2, filme.getGenero());
            comando.setString(3, filme.getSinopse());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FilmeDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Filme> listarFilmes() {
        List<Filme> listaFilmes = new ArrayList<>();

        String sql = "SELECT * FROM filme ORDER BY idfilme";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("idfilme");
                String nome = resultado.getString("nomefilme");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");

                Filme filme = new Filme(id, nome, genero, sinopse);
                listaFilmes.add(filme);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FilmeDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaFilmes);
    }

    @Override
    public Filme buscarPorId(int id) {
        String sql = "SELECT * FROM filme WHERE idfilme = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nomefilme = resultado.getString("nomefilme");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");

                Filme filme = new Filme(id, nomefilme, genero, sinopse);
                return filme;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FilmeDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public List<Filme> listBuscarPorNome(String nome) {
        List<Filme> listaFilmes = new ArrayList<>();
        String sql = "SELECT * FROM filme WHERE nomefilme LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("idfilme");
                String nomef = resultado.getString("nomefilme");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");

                Filme filme = new Filme(id, nomef, genero, sinopse);
                listaFilmes.add(filme);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaFilmes);
    }

    @Override
    public Filme buscarPorNome(String nome) {
        String sql = "SELECT * FROM filme WHERE nomefilme = ?";
        try {
            conectar(sql);
            comando.setString(1, nome);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("idfilme");
                String nomef = resultado.getString("nomefilme");
                String genero = resultado.getString("genero");
                String sinopse = resultado.getString("sinopse");

                Filme filme = new Filme(id, nomef, genero, sinopse);
                return filme;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public void editar(Filme filme) {
        try {
            String sql = "UPDATE filme SET nomefilme=?, genero=?, sinopse=? "
                    + "WHERE idfilme=?";

            conectar(sql);
            comando.setString(1, filme.getNomeFilme());
            comando.setString(2, filme.getGenero());
            comando.setString(3, filme.getSinopse());
            comando.setInt(4, filme.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FilmeDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void excluir(Filme filme) {
        try {
            String sql = "DELETE FROM filme WHERE idfilme = ?";

            conectar(sql);
            comando.setInt(1, filme.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FilmeDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    public void conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
    }

    public void conectarObtendoId(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
