package senac.dao;

import java.sql.Connection;
import senac.factory.Conexao;

/**
 *
 * @author Alunos
 */
public class Dao {
    protected Connection conexao;

    public Dao() {
        conexao = Conexao.getConexao();
    }
    
}
