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
import model.Filme;
import model.Sala;
import model.Sessao;

/**
 *
 * @author silvinha01
 */
public class SessaoDaoBd implements SessaoDao {

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void cadastrar(Sessao sessao) {
        try {
            String sql = "INSERT INTO sessao (idsala_fk, qtdassentosdisponiveis, idfilme_fk, horario) "
                    + "VALUES (?,?,?,?)";

            conectar(sql);
            comando.setInt(1, sessao.getSala().getId());
            comando.setInt(2, sessao.getQtdAssentosDisponiveis());
            comando.setInt(3, sessao.getFilme().getId());
            //Trabalhando com data: lembrando dataUtil -> dataSql            
            java.sql.Timestamp timestSql = new java.sql.Timestamp(sessao.getHorario().getTime());
            comando.setTimestamp(4, timestSql);
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SessaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Sessao> listarSessoes() {
        List<Sessao> listaSessoes = new ArrayList<>();

        String sql = "SELECT * FROM sessao ORDER BY idsessao";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("idsessao");
                Sala sala = this.getSala(resultado.getInt("idsala_fk"));
                int qtdAssentosDisponiveis = resultado.getInt("qtdassentosdisponiveis");
                Filme filme = this.getFilme(resultado.getInt("idfilme_fk"));
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Timestamp dataSql = resultado.getTimestamp("horario");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());

                Sessao sessao = new Sessao(id, sala, qtdAssentosDisponiveis, filme, dataUtil);

                listaSessoes.add(sessao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaSessoes);
    }

    private Sala getSala(int id) {
        return (new SalaDaoBd().buscarPorId(id));
    }

    private Filme getFilme(int id) {
        return (new FilmeDaoBd().buscarPorId(id));
    }

    @Override
    public Sessao buscarPorId(int id) {
        String sql = "SELECT * FROM sessao WHERE idsessao = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                Sala sala = this.getSala(resultado.getInt("idsala_fk"));
                int qtdAssentosDisponiveis = resultado.getInt("qtdassentosdisponiveis");
                Filme filme = this.getFilme(resultado.getInt("idfilme_fk"));
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Timestamp dataSql = resultado.getTimestamp("horario");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());

                Sessao sessao = new Sessao(id, sala, qtdAssentosDisponiveis, filme, dataUtil);
                return sessao;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SessaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public Sessao buscarPorHorario(Date horario) {
        String sql = "SELECT * FROM sessao WHERE horario = ?";

        try {
            conectar(sql);
            java.sql.Timestamp timestSql = new java.sql.Timestamp(horario.getTime());
            comando.setTimestamp(1, timestSql);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("idsessao");
                Sala sala = this.getSala(resultado.getInt("idsala_fk"));
                int qtdAssentosDisponiveis = resultado.getInt("qtdassentosdisponiveis");
                Filme filme = this.getFilme(resultado.getInt("idfilme_fk"));
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Timestamp dataSql = resultado.getTimestamp("horario");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());

                Sessao sessao = new Sessao(id, sala, qtdAssentosDisponiveis, filme, dataUtil);
                return sessao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public List<Sessao> listarPorHorario(Date horario) {
        List<Sessao> listaSessoes = new ArrayList<>();
        String sql = "SELECT * FROM sessao WHERE horario = ? ORDER BY idsessao";

        try {
            conectar(sql);
            java.sql.Timestamp timestSql = new java.sql.Timestamp(horario.getTime());
            comando.setTimestamp(1, timestSql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("idsessao");
                Sala sala = this.getSala(resultado.getInt("idsala_fk"));
                int qtdAssentosDisponiveis = resultado.getInt("qtdassentosdisponiveis");
                Filme filme = this.getFilme(resultado.getInt("idfilme_fk"));
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Timestamp dataSql = resultado.getTimestamp("horario");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());

                Sessao sessao = new Sessao(id, sala, qtdAssentosDisponiveis, filme, dataUtil);
                listaSessoes.add(sessao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaSessoes);
    }

    @Override
    public Sessao buscarPorSalaEHorario(int idsala, Date horario) {
        String sql = "SELECT * FROM sessao WHERE idsala_fk = ? and horario = ?";

        try {
            conectar(sql);
            comando.setInt(1, idsala);
            java.sql.Timestamp timestSql = new java.sql.Timestamp(horario.getTime());
            comando.setTimestamp(2, timestSql);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("idsessao");
                Sala sala = this.getSala(resultado.getInt("idsala_fk"));
                int qtdAssentosDisponiveis = resultado.getInt("qtdassentosdisponiveis");
                Filme filme = this.getFilme(resultado.getInt("idfilme_fk"));
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Timestamp dataSql = resultado.getTimestamp("horario");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());

                Sessao sessao = new Sessao(id, sala, qtdAssentosDisponiveis, filme, dataUtil);
                return sessao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }   

    @Override
    public void editar(Sessao sessao) {
        try {
            String sql = "UPDATE sessao SET idfilme_fk=?, horario=? "
                    + "WHERE idsessao=?";

            conectar(sql);
            comando.setInt(1, sessao.getFilme().getId());
            //Trabalhando com data: lembrando dataUtil -> dataSql            
            java.sql.Timestamp timestSql = new java.sql.Timestamp(sessao.getHorario().getTime());
            comando.setTimestamp(2, timestSql);
            comando.setInt(3, sessao.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SessaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void excluir(Sessao sessao) {
        try {
            String sql = "DELETE FROM sessao WHERE idsessao = ?";

            conectar(sql);
            comando.setInt(1, sessao.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SessaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SessaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
