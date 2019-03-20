package br.com.estacionamento.dao;

import br.com.estacionamento.interfaces.DaoI;
import br.com.estacionamento.model.Entrada;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class EntradaDao extends Dao implements DaoI<Entrada> {
    
    CondutorDao CONDUTOR_DAO = new CondutorDao();
    CarroDao CARRO_DAO = new CarroDao();
    TipoClienteDao TIPO_CLIENTE_DAO = new TipoClienteDao();

    public EntradaDao() {
        //Contrutor da super classe Dao. Faz a conexão.
        super();
    }

    @Override
    public List<Entrada> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT "
                    + "id, dataEntrada, dataSaida , valorTotal, fk_carro , fk_condutor, fk_tipoCliente FROM entrada ORDER BY id DESC");
            ResultSet result = stmt.executeQuery();
            List<Entrada> lista = new ArrayList<>();
            while (result.next()) {
                Entrada e = new Entrada();
                e.setId(result.getInt("id"));
                e.setDataEntrada(result.getDate("dataEntrada"));
                e.setDataSaida(result.getDate("dataSaida"));
                e.setValorTotal(result.getDouble("valorTotal"));
                e.setCarro(CARRO_DAO.lerPorId(result.getInt("fk_carro")));
                e.setCondutor(CONDUTOR_DAO.lerPorId(result.getInt("fk_condutor")));
                e.setTipoCliente(TIPO_CLIENTE_DAO.lerPorId(result.getInt("fk_tipoCliente")));
                lista.add(e);
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
    public int cadastrar(Entrada obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "INSERT INTO entrada(dataEntrada, dataSaida , valorTotal, fk_carro , fk_condutor, fk_tipoCliente)"
                    + " VALUES(?, ?, ? , ? , ? , ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, new Date(obj.getDataEntrada().getTime()));
            if (obj.getDataSaida() == null) { // se for nulo
                stmt.setNull(2, Types.DATE);
            } else {
                stmt.setDate(2, new Date(obj.getDataSaida().getTime()));
            }
            if (obj.getValorTotal() == null) { // se for nulo
                stmt.setNull(3, Types.DOUBLE);
            } else {
                stmt.setDouble(3, obj.getValorTotal());
            }
            stmt.setInt(4, obj.getCarro().getId());
            stmt.setInt(5, obj.getCondutor().getId());
            stmt.setInt(6, obj.getTipoCliente().getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                obj.setId(idInserido);
                return obj.getId();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
        return 0;
    }

    @Override
    public boolean alterar(Entrada obj) {
        try {
            PreparedStatement stmt = conexao.prepareStatement("UPDATE ENTRADA "
                    + "SET DATAENTRADA = ? , DATASAIDA = ? , VALORTOTAL = ? , "
                    + "FK_CARRO = ? , FK_CONDUTOR = ? , FK_TIPOCLIENTE = ? WHERE ID  =?");
            stmt.setDate(1, new Date(obj.getDataEntrada().getTime()));
            stmt.setDate(2, new Date(obj.getDataSaida().getTime()));
            stmt.setDouble(3, obj.getValorTotal());
            stmt.setInt(4, obj.getCarro().getId());
            stmt.setInt(5, obj.getCondutor().getId());
            stmt.setInt(6, obj.getTipoCliente().getId());
            stmt.setInt(7, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletarPorId(int id) {
        try {
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM ENTRADA WHERE ID = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Entrada lerPorId(int id) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(""
                    + "SELECT id, dataEntrada , dataSaida , valorTotal FROM entrada "
                    + " WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                Entrada e = new Entrada();
                e.setId(res.getInt("id"));
                e.setDataEntrada(res.getDate("dataEntrada"));
                e.setDataSaida(res.getDate("dataSaida"));
                e.setValorTotal(res.getDouble("valorTotal"));
                return e;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    /**
     * @deprecated Não é funcional para esse objeto.
     * @param termo
     * @return
     */
    @Override
    public List<Entrada> pesquisar(String termo) {
        return null;
    }
    
    public List<Entrada> listarSomenteSemSaida() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT id, dataEntrada, dataSaida , "
                    + "valorTotal, fk_carro , fk_condutor, fk_tipoCliente "
                    + "FROM entrada where entrada.dataSaida is null ORDER BY id DESC");
            ResultSet result = stmt.executeQuery();
            List<Entrada> lista = new ArrayList<>();
            while (result.next()) {
                Entrada e = new Entrada();
                e.setId(result.getInt("id"));
                e.setDataEntrada(result.getDate("dataEntrada"));
                e.setDataSaida(result.getDate("dataSaida"));
                e.setValorTotal(result.getDouble("valorTotal"));
                e.setCarro(CARRO_DAO.lerPorId(result.getInt("fk_carro")));
                e.setCondutor(CONDUTOR_DAO.lerPorId(result.getInt("fk_condutor")));
                e.setTipoCliente(TIPO_CLIENTE_DAO.lerPorId(result.getInt("fk_tipoCliente")));
                lista.add(e);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
