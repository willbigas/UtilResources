package dao;

import interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;

/**
 *
 * @author William
 */
public class CategoriaDao extends Dao implements DaoI<Categoria> {

    public CategoriaDao() {
        //Contrutor da super classe Dao. Faz a conexão.
        super();
    }

    @Override
    public List<Categoria> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT * FROM categorias");
            ResultSet result = stmt.executeQuery();
            List<Categoria> lista = new ArrayList<>();
            while (result.next()) {
                Categoria p = new Categoria();
                p.setId(result.getInt("id"));
                p.setNome(result.getString("nome"));
                p.setAtivo(result.getBoolean("ativo"));
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
    public int cadastrar(Categoria obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "INSERT INTO categorias(nome, ativo)"
                    + " VALUES(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.setBoolean(2, obj.getAtivo());
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
    public boolean alterar(Categoria obj) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(""
                    + "UPDATE categorias SET nome = ?, ativo = ? WHERE  id = ?");
            stmt.setString(1, obj.getNome());
            stmt.setBoolean(2, obj.getAtivo());
            stmt.setInt(3, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Categoria obj) {
        try {
            PreparedStatement stmt = conexao.prepareStatement("DELETE from categorias WHERE id = ?");
            stmt.setInt(1, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Categoria> pesquisarPorTermo(String termo) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(""
                    + "SELECT id, nome, ativo FROM categorias "
                    + "WHERE (nome = ?) ");
            stmt.setString(1, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Categoria> lista = new ArrayList<>();
            while (result.next()) {
                Categoria c = new Categoria();
                c.setId(result.getInt("id"));
                c.setNome(result.getString("nome"));
                c.setAtivo(result.getBoolean("ativo"));
                lista.add(c);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public Categoria lerPorId(int id) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(""
                    + "SELECT id, nome, ativo FROM produtos "
                    + "WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Categoria c = new Categoria();
                c.setId(result.getInt("id"));
                c.setNome(result.getString("nome"));
                c.setAtivo(result.getBoolean("ativo"));
                return c;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public List<Categoria> listarComLimit(Integer limite) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT * FROM categorias limit ?,?");
            stmt.setInt(1, limite);
            stmt.setInt(2, limite);
            ResultSet result = stmt.executeQuery();
            List<Categoria> lista = new ArrayList<>();
            while (result.next()) {
                Categoria p = new Categoria();
                p.setId(result.getInt("id"));
                p.setNome(result.getString("nome"));
                p.setAtivo(result.getBoolean("ativo"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public List<Categoria> listarComLimit(Integer limiteInicial , Integer limiteFinal) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT * FROM categorias limit ?,?");
            stmt.setInt(1, limiteInicial);
            stmt.setInt(2, limiteFinal);
            ResultSet result = stmt.executeQuery();
            List<Categoria> lista = new ArrayList<>();
            while (result.next()) {
                Categoria p = new Categoria();
                p.setId(result.getInt("id"));
                p.setNome(result.getString("nome"));
                p.setAtivo(result.getBoolean("ativo"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
