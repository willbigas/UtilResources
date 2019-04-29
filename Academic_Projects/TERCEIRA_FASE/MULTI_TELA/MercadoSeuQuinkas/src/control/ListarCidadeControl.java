package control;

import dao.CidadeDao;
import model.tablemodel.CidadeTableModel;
import view.ListarCidade;

/**
 *
 * @author William
 */
public class ListarCidadeControl {
    
    CidadeDao cidadeDao;
    CidadeTableModel tabelaCidade;

    public ListarCidadeControl() {
        cidadeDao = new CidadeDao();
        tabelaCidade = new CidadeTableModel();
        ListarCidade.tblCidade.setModel(tabelaCidade);
    }
    
    
    
    public void carregarClientesDoBanco(){
       tabelaCidade.adicionar(cidadeDao.listar("nome" , "asc"));
    }
}
