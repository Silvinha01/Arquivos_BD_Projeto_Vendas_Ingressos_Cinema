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
import model.Sala;

/**
 *
 * @author silvinha01
 */
public class SalaDaoBd implements SalaDao {

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    //Método cadastrar alterado para receber o id auto increment 
    //e já inserir no objeto sala (recebido por parâmetro).
    public void cadastrar(Sala sala) {
        try {
            String sql = "INSERT INTO sala (qtdassentos) "
                    + "VALUES (?)";

            conectar(sql);
            comando.setInt(1, sala.getQtdAssentos());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SalaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Sala> listarSalas() {
        List<Sala> listaSalas = new ArrayList<>();

        String sql = "SELECT * FROM sala ORDER BY idsala";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int idsala = resultado.getInt("idsala");
                int qtdassentos = resultado.getInt("qtdassentos");

                Sala sala = new Sala(idsala, qtdassentos);
                listaSalas.add(sala);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SalaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaSalas);
    }

    @Override
    public Sala buscarPorId(int id) {
        String sql = "SELECT * FROM sala WHERE idsala = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int qtdassentos = resultado.getInt("qtdassentos");

                Sala sala = new Sala(id, qtdassentos);
                return sala;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SalaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public void editar(Sala sala) {
        try {
            String sql = "UPDATE sala SET qtdassentos=? "
                    + "WHERE idsala=?";

            conectar(sql);
            comando.setInt(1, sala.getQtdAssentos());
            comando.setInt(2, sala.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SalaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void excluir(Sala sala) {
        try {
            String sql = "DELETE FROM sala WHERE idsala = ?";

            conectar(sql);
            comando.setInt(1, sala.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SalaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SalaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
