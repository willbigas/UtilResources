package dao;

import interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import uteis.Conversor;

/**
 *
 * @author renato.dasilva
 */
public class ClienteDao extends Dao implements DaoI<Cliente>{

    public ClienteDao() {
        super();
    }

    @Override
    public List<Cliente> listar() {
        return listar("cli.id", "DESC");
    }
    
    public List<Cliente> listar(String orderBy, String ordenacao) {
        String sql = "SELECT "
                + "     cli.id, cli.nome, cli.cep, cli.dataNascimento, cli.cidade_id,"
                + "     cid.nome AS cidade, cid.uf"
                + "   FROM"
                + "     cliente cli "
                + "   INNER JOIN "
                + "     cidade cid ON cli.cidade_id = cid.id"
                + "   WHERE"
                + "     cli.ativo = 1"
                + "   AND "
                + "     cid.ativo = 1"
                + "   ORDER BY " + orderBy + " " + ordenacao;
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            List<Cliente> list = new ArrayList<>();
            while(res.next()){
                Cliente c = new Cliente();
                c.setId(res.getInt("id"));
                c.setNome(res.getString("nome"));
                c.setCep(res.getString("cep"));
                c.setDataNascimento(res.getDate("dataNascimento"));
                
                c.getCidade().setId(res.getInt("cidade_id"));
                c.getCidade().setNome(res.getString("cidade"));
                c.getCidade().setUf(res.getString("uf"));
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Cliente obj) {
       String sql = "INSERT INTO cliente(nome, cep, dataNascimento, cidade_id) "
               + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCep());
            stmt.setDate(3, Conversor.dataUtilParaSql(obj.getDataNascimento()));
            stmt.setInt(4, obj.getCidade().getId());
            
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
    public boolean alterar(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente lerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> pesquisar(String termo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
