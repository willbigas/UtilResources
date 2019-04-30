/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cidade;

/**
 *
 * @author renato.dasilva
 */
public class CidadeDao extends Dao implements DaoI<Cidade>{

    public CidadeDao() {
        super();
    }

    @Override
    public List<Cidade> listar() {
        return listar("id", "DESC");
    }
    
    public List<Cidade> listar(String orderBy, String ordenacao) {
        String sql = "SELECT id, nome, uf FROM cidade WHERE ativo=1 ORDER BY "+orderBy+" "+ordenacao;
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            List<Cidade> list = new ArrayList<>();
            while(res.next()){
                Cidade c = new Cidade();
                c.setId(res.getInt("id"));
                c.setNome(res.getString("nome"));
                c.setUf(res.getString("uf"));
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int cadastrar(Cidade obj) {
        String sql = "INSERT INTO cidade(nome, uf) VALUES(?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getUf());
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
    public boolean alterar(Cidade obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cidade lerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cidade> pesquisar(String termo) {
        String sql = "SELECT id, nome, uf FROM cidade WHERE ativo=1 and nome like ? ORDER BY id desc";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%"+ termo + "%");
            ResultSet res = stmt.executeQuery();
            List<Cidade> list = new ArrayList<>();
            while(res.next()){
                Cidade c = new Cidade();
                c.setId(res.getInt("id"));
                c.setNome(res.getString("nome"));
                c.setUf(res.getString("uf"));
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
}
