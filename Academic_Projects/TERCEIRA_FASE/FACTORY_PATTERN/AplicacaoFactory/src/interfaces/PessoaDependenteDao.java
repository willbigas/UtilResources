/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dao.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.PessoaDependente;

/**
 *
 * @author William
 */
public class PessoaDependenteDao extends Dao implements DaoI<PessoaDependente> {

    public PessoaDependenteDao() {
        super();
    }

    @Override
    public int inserir(PessoaDependente obj) {
        String queryInsert = "INSERT INTO pessoaDependente(NOME) VALUES(?)";
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
