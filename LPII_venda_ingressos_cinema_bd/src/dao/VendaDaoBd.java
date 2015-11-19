package dao;

import banco.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sessao;
import model.Venda;

/**
 *
 * @author silvinha01
 */
public class VendaDaoBd implements VendaDao {

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void cadastrar(Venda venda) {
        try {
            String sql = "INSERT INTO venda (idsessao_fk, qtdingressos) "
                    + "VALUES (?,?)";

            conectar(sql);
            comando.setInt(1, venda.getSessao().getId());
            comando.setInt(2, venda.getQtdIngressosPorVenda());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Venda> listarVendas() {
        List<Venda> listaVendas = new ArrayList<>();

        String sql = "SELECT * FROM venda ORDER BY idvenda";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("idvenda");
                Sessao sessao = this.getSessao(resultado.getInt("idsessao_fk"));
                int qtdingressos = resultado.getInt("qtdingressos");

                Venda venda = new Venda(id, sessao, qtdingressos);

                listaVendas.add(venda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaVendas);
    }

    private Sessao getSessao(int id) {
        return (new SessaoDaoBd().buscarPorId(id));
    }

    @Override
    public Venda buscarPorFilme(int idfilme) {
        String sql = "SELECT * FROM venda, sessao, filme "
                + "WHERE idsessao = idsessao_fk AND idfilme = idfilme_fk AND idfilme = ?";

        try {
            conectar(sql);
            comando.setInt(1, idfilme);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int idvenda = resultado.getInt("idvenda");
                Sessao sessao = this.getSessao(resultado.getInt("idsessao_fk"));
                int qtdingressos = resultado.getInt("qtdingressos");

                Venda venda = new Venda(idvenda, sessao, qtdingressos);
                return venda;
            }

        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public Venda buscarPorHorario(Date horario) {
        String sql = "SELECT * FROM venda, sessao "
                + "WHERE idsessao = idsessao_fk AND horario = ?";

        try {
            conectar(sql);
            java.sql.Timestamp timestSql = new java.sql.Timestamp(horario.getTime());
            comando.setTimestamp(1, timestSql);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int idvenda = resultado.getInt("idvenda");
                Sessao sessao = this.getSessao(resultado.getInt("idsessao_fk"));
                int qtdIngressos = resultado.getInt("qtdingressos");

                Venda venda = new Venda(idvenda, sessao, qtdIngressos);
                return venda;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public Venda buscarPorSala(int idsala) {
        String sql = "SELECT * FROM venda, sessao, sala "
                + "WHERE idsessao = idsessao_fk AND idsala = idsala_fk AND idsala = ?";

        try {
            conectar(sql);
            comando.setInt(1, idsala);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int idvenda = resultado.getInt("idvenda");
                Sessao sessao = this.getSessao(resultado.getInt("idsessao_fk"));
                int qtdingressos = resultado.getInt("qtdingressos");

                Venda venda = new Venda(idvenda, sessao, qtdingressos);
                return venda;
            }

        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public Venda buscarPorSessao(int id) {
        String sql = "SELECT * FROM venda WHERE idsessao_fk = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int idvenda = resultado.getInt("idvenda");
                Sessao sessao = this.getSessao(resultado.getInt("idsessao_fk"));
                int qtdingressos = resultado.getInt("qtdingressos");

                Venda venda = new Venda(idvenda, sessao, qtdingressos);
                return venda;
            }

        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public Venda buscarPorId(int id) {
        String sql = "SELECT * FROM venda WHERE idvenda = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                Sessao sessao = this.getSessao(resultado.getInt("idsessao_fk"));
                int qtdingressos = resultado.getInt("qtdingressos");

                Venda venda = new Venda(id, sessao, qtdingressos);
                return venda;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public List<Venda> listarPorFilme(int idfilme) {
        List<Venda> listaVendasPorFilme = new ArrayList<>();
        String sql = "SELECT * FROM venda, sessao, filme "
                + "WHERE idsessao = idsessao_fk AND idfilme = idfilme_fk AND idfilme = ?";

        try {
            conectar(sql);
            comando.setInt(1, idfilme);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int idvenda = resultado.getInt("idvenda");
                Sessao sessao = this.getSessao(resultado.getInt("idsessao_fk"));
                int qtdingressos = resultado.getInt("qtdingressos");

                Venda venda = new Venda(idvenda, sessao, qtdingressos);
                listaVendasPorFilme.add(venda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaVendasPorFilme);
    }

    @Override
    public List<Venda> listarPorHorario(Date horario) {
        List<Venda> listaVendasPorHorario = new ArrayList<>();
        String sql = "SELECT * FROM venda, sessao "
                + "WHERE idsessao = idsessao_fk AND horario = ?";

        try {
            conectar(sql);
            java.sql.Timestamp timestSql = new java.sql.Timestamp(horario.getTime());
            comando.setTimestamp(1, timestSql);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int idvenda = resultado.getInt("idvenda");
                Sessao sessao = this.getSessao(resultado.getInt("idsessao_fk"));
                int qtdIngressos = resultado.getInt("qtdingressos");

                Venda venda = new Venda(idvenda, sessao, qtdIngressos);
                listaVendasPorHorario.add(venda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaVendasPorHorario);
    }

    @Override
    public List<Venda> listarPorSala(int idsala) {
        List<Venda> listaVendasPorSala = new ArrayList<>();
        String sql = "SELECT * FROM venda, sessao, sala "
                + "WHERE idsessao = idsessao_fk AND idsala = idsala_fk AND idsala = ?";

        try {
            conectar(sql);
            comando.setInt(1, idsala);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int idvenda = resultado.getInt("idvenda");
                Sessao sessao = this.getSessao(resultado.getInt("idsessao_fk"));
                int qtdingressos = resultado.getInt("qtdingressos");

                Venda venda = new Venda(idvenda, sessao, qtdingressos);
                listaVendasPorSala.add(venda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaVendasPorSala);
    }

    @Override
    public List<Venda> listarPorSessao(int id) {
        List<Venda> listaVendasPorSessao = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE idsessao_fk = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int idvenda = resultado.getInt("idvenda");
                Sessao sessao = this.getSessao(resultado.getInt("idsessao_fk"));
                int qtdingressos = resultado.getInt("qtdingressos");

                Venda venda = new Venda(idvenda, sessao, qtdingressos);
                listaVendasPorSessao.add(venda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaVendasPorSessao);
    }

    @Override
    public void editar(Venda venda) {
        try {
            String sql = "UPDATE venda SET qtdingressos=? "
                    + "WHERE idvenda=?";

            conectar(sql);
            comando.setInt(1, venda.getQtdIngressosPorVenda());
            comando.setInt(2, venda.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void excluir(Venda venda) {
        try {
            String sql = "DELETE FROM venda WHERE idvenda = ?";

            conectar(sql);
            comando.setInt(1, venda.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VendaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
