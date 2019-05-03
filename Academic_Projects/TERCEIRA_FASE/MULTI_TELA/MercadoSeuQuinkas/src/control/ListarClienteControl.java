package control;

import dao.ClienteDao;
import java.util.List;
import model.Cliente;
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
    
    
    public void pesquisarClienteAction() {
        List<Cliente> clientesPesquisados = clienteDao.pesquisar(ListarCliente.tfPesquisa.getText());
        if (clientesPesquisados == null) {
            tabelaCliente.limpar();
            clientesPesquisados = clienteDao.listar("nome", "ASC");
        } else {
            tabelaCliente.limpar();
            tabelaCliente.adicionar(clientesPesquisados);
        }

    }
    
    
    
    public void carregarClientesDoBanco(){
       tabelaCliente.adicionar(clienteDao.listar("nome" , "ASC"));
    }
}
