package dao;

import interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

/**
 *
 * @author William
 */
public class VendaDao extends Dao implements DaoI<Produto> {

    public VendaDao() {
        //Contrutor da super classe Dao. Faz a conexão.
        super();
    }

    @Override
    public List<Produto> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT * FROM vendas");
            ResultSet result = stmt.executeQuery();
            List<Produto> lista = new ArrayList<>();
            while (result.next()) {
                Produto p = new Produto();
                p.setId(result.getInt("id"));
                p.setDescricao(result.getString("descricao"));
                p.setQtd(result.getInt("quantidade"));
                p.setValor(result.getDouble("valor"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Método para cadastrar um produto no banco de dados
     * <br>Retorna id do produto cadastrado
     * <br><b>Retorna 0 (zero) se houver erro</b>
     *
     * @param obj
     * @return int
     */
    @Override
    public int cadastrar(Produto obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "INSERT INTO vendas(descricao, quantidade , valor)"
                    + " VALUES(?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getDescricao());
            stmt.setInt(2, obj.getQtd());
            stmt.setDouble(3, obj.getValor());
            ResultSet res;
            if (stmt.executeUpdate() > 0) {
                res = stmt.getGeneratedKeys();
                res.next();
                return res.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public boolean alterar(Produto obj) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(""
                    + "UPDATE vendas SET descricao = ?, quantidade = ?, valor = ? WHERE  id = ?");
            stmt.setString(1, obj.getDescricao());
            stmt.setInt(2, obj.getQtd());
            stmt.setDouble(3, obj.getValor());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Produto obj) {
        try {
            PreparedStatement stmt = conexao.prepareStatement("DELETE from vendas WHERE id = ?");
            stmt.setInt(1, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Produto> pesquisarPorTermo(String termo) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(""
                    + "SELECT id, descricao, quantidade , valor FROM vendas "
                    + "WHERE (descricao = ?) ");
            stmt.setString(1, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Produto> lista = new ArrayList<>();
            while (result.next()) {
                Produto p = new Produto();
                p.setId(result.getInt("id"));
                p.setDescricao(result.getString("descricao"));
                p.setQtd(result.getInt("quantidade"));
                p.setValor(result.getDouble("valor"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public Produto lerPorId(int id) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(""
                    + "SELECT id, descricao, quantidade , valor FROM vendas "
                    + "WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Produto p = new Produto();
                p.setId(result.getInt("id"));
                p.setDescricao(result.getString("descricao"));
                p.setQtd(result.getInt("quantidade"));
                p.setValor(result.getDouble("valor"));
                return p;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
