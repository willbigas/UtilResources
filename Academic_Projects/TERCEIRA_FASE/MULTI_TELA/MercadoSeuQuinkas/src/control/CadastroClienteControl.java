package control;

import dao.CidadeDao;
import dao.ClienteDao;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
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
        if (clienteDao.cadastrar(cliente) != 0) {
            JOptionPane.showMessageDialog(frameCadastroCliente, "Cadastro Realizado com Sucesso!");
        } else {
            JOptionPane.showMessageDialog(frameCadastroCliente, "Erro ao Realizar o Cadastro!");
        }
        
    }

    public void chamarTelaListarCidade() {
        dialogListarCidade = new ListarCidade(frameCadastroCliente, true);
        dialogListarCidade.setVisible(true);
    }

    public void pegaCidadeSelecionada() {
        listarCidadeControl = ListarCidade.getCidadeControl();
        cidadeSelecionada = listarCidadeControl.tabelaCidade.pegaObjeto(ListarCidade.tblCidade.getSelectedRow());
    }

    public void pegaCidadeSelecionadaNoJDialog() {
        System.out.println("Cidade selecionada dentro do metodo :" + cidadeSelecionada);
        ListarCidade.tblCidade.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                Point point = mouseEvent.getPoint();
                if (mouseEvent.getClickCount() == 2) {
                    System.out.println("Cidade Selecionada :" + cidadeSelecionada);
                    List<Cidade> cidades = cidadeDao.listar();
                    System.out.println("Cidades da lista:" + cidades);
                    System.out.println("Cidades da Combo:" + cidades);
                    System.out.println("Cidade Selecionada" + cidadeSelecionada);
                    for (int i = 0; i < cidades.size(); i++) {
                        String cidade = cidades.get(i).getNome();
                        System.out.println(cidade);
                        if (cidade.equals(cidadeSelecionada.getNome())) {
                            CadastroCliente.cbCidade.setSelectedIndex(i);
                            
                        }
                    }
                }
            }
        });

    }

}
