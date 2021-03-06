package basedao.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import basedao.interfaces.DaoI;
import basedao.model.Contato;

/**
 *
 * @author William
 */
public class ContatoDao extends Dao implements DaoI<Contato> {

    public ContatoDao() {
        super();
    }

    @Override
    public List<Contato> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT id , nome  , email , telefone , celular , dataNascimento from contatos");
            ResultSet result = stmt.executeQuery();
            List<Contato> listContato = new ArrayList<>();
            while (result.next()) {
                Contato contato = new Contato();
                contato.setId(result.getInt("id"));
                contato.setNome(result.getString("nome"));
                contato.setEmail(result.getString("email"));
                contato.setTelefone(result.getInt("telefone"));
                contato.setCelular(result.getInt("celular"));
                contato.setNascimento(result.getDate("dataNascimento"));
                listContato.add(contato);

            }
            return listContato;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean cadastrar(Contato obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("INSERT INTO contatos (nome,email,telefone,celular,dataNascimento) VALUES (?, ?, ? , ? , ?)");
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEmail());
            stmt.setInt(3, obj.getTelefone());
            stmt.setInt(4, obj.getCelular());
            stmt.setDate(5, new java.sql.Date(obj.getNascimento().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alterar(Contato obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("UPDATE contatos SET nome=? , email=? , telefone=? , celular=?, dataNascimento =? where id=?");
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEmail());
            stmt.setInt(3, obj.getTelefone());
            stmt.setInt(4, obj.getCelular());
            stmt.setDate(5, new java.sql.Date(obj.getNascimento().getTime()));
            stmt.setInt(6, obj.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deletar(Contato obj) {

        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("DELETE FROM contatos where id=?");
            stmt.setInt(1, obj.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Contato buscaPorId(int id) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT id , nome  , email , telefone , celular , dataNascimento from contatos where id=?");
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            Contato contato = null;
            if (result.next()) {
                contato = new Contato();
                contato.setId(result.getInt("id"));
                contato.setNome(result.getString("nome"));
                contato.setEmail(result.getString("email"));
                contato.setTelefone(result.getInt("telefone"));
                contato.setCelular(result.getInt("celular"));
                contato.setNascimento(result.getDate("dataNascimento"));

            }
            return contato;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Contato> buscarPorTermo(String termo) {
        List<Contato> produtosPesquisados = listar();
        List<Contato> novaListaDeResultados = new ArrayList<>();
        for (Contato c : produtosPesquisados) {
            if (c.getNome().toLowerCase().contains(termo.toLowerCase())) {
                novaListaDeResultados.add(c);
            }
        }
        return novaListaDeResultados;

    }

    @Override
    public boolean deletarPorId(Integer id) {
        try {
            PreparedStatement statement = conexao.prepareStatement(
                    "delete from contatos where id = ? ");
            statement.setInt(1, id);
            int executeUpdate = statement.executeUpdate();
            return executeUpdate != 0;
        } catch (Exception e) {
            return false;

        }
    }

}
