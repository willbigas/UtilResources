package control;

import dao.CidadeDao;
import dao.ClienteDao;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;
import model.Cidade;
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
    private ListarCidade dialogListarCidade;
    private ListarCidadeControl listarCidadeControl;
    private ClienteDao clienteDao;
    private CidadeDao cidadeDao;
    private Cliente cliente;
    private Cidade cidadeSelecionada;
    
    public CadastroClienteControl() {
        clienteDao = new ClienteDao();
        cidadeDao = new CidadeDao();
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
    
    public void chamarTelaListarCidade() {
        dialogListarCidade = new ListarCidade(frameCadastroCliente, true);
        dialogListarCidade.setVisible(true);
    }
    
    public void pegaCidadeSelecionadaNoJDialog() {
        ListarCidade.tblCidade.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    listarCidadeControl = ListarCidade.getCidadeControl();
                    cidadeSelecionada = listarCidadeControl.tabelaCidade.pegaObjeto(ListarCidade.tblCidade.getSelectedRow());
                    System.out.println("Cidade Selecionada :" + cidadeSelecionada);
                }
            }
        });
        
        
        List<Cidade> cidades = cidadeDao.listar();
        System.out.println("Cidades da Combo:" + cidades);
        
        for (int i = 0; i < cidades.size(); i++) {
            String cidade = cidades.get(i).getNome();
            System.out.println(cidade);
            if (cidade.equals(cidadeSelecionada.getNome())) {
                CadastroCliente.cbCidade.setSelectedIndex(i);
            }
        }
        
    }
    
}
