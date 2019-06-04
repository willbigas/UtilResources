package interfaces;

import dao.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.PessoaJuridica;
import model.PessoaParente;

/**
 *
 * @author William
 */
public class PessoaParenteDao extends Dao implements DaoI<PessoaParente> {

    public PessoaParenteDao() {
        super();
    }

    @Override
    public int inserir(PessoaParente obj) {
        String queryInsert = "INSERT INTO pessoaParente(NOME) VALUES(?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
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

}
