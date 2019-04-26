package control;

import dao.ClienteDao;
import model.tablemodel.ClienteTableModel;
import view.ListarCliente;

/**
 *
 * @author William
 */
public class ListarClienteControl {
    
    ClienteDao clienteDao;
    ClienteTableModel tabelaCliente;

    public ListarClienteControl() {
        clienteDao = new ClienteDao();
        tabelaCliente = new ClienteTableModel();
        ListarCliente.tblCliente.setModel(tabelaCliente);
    }
    
    
    
    public void carregarClientesDoBanco(){
       tabelaCliente.adicionar(clienteDao.listar("nome" , "asc"));
    }
}
