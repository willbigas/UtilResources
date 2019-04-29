package control;

import dao.ClienteDao;
import model.Cliente;
import uteis.Conversor;
import view.CadastroCliente;
import view.ListarCidade;

/**
 *
 * @author William
 */
public class CadastroClienteControl {

    private CadastroCliente frameCadastroCliente;
    private static ListarCidade dialogListarCidade;
    private ClienteDao clienteDao;
    private Cliente cliente;
   

    public CadastroClienteControl() {
        clienteDao = new ClienteDao();
    }

    public void cadastrarClienteAction() {
        cliente = new Cliente();
        cliente.setNome(CadastroCliente.tfNome.getText());
        cliente.setCep(CadastroCliente.tfCep.getText());
        try {
            cliente.setDataNascimento(Conversor.data(CadastroCliente.tfDataNascimento.getText()));
        } catch (Exception exception) {
            System.out.println("Data de Nascimento Invalida");
        }
        cliente.setCidade(CadastroCidadeControl.cidadeSelecionada);
        cliente.setAtivo(1);
        clienteDao.cadastrar(cliente);
    }
    
    public void chamarTelaListarCidade(){
         dialogListarCidade = new ListarCidade(frameCadastroCliente, true);
         dialogListarCidade.setVisible(true);
    }

}
