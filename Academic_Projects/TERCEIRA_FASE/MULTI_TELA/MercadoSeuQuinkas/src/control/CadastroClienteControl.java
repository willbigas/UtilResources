package control;

import control.validate.CadastroClienteValidation;
import dao.CidadeDao;
import dao.ClienteDao;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import model.Cidade;
import model.Cliente;
import uteis.Conversor;
import uteis.Mensagem;
import uteis.Texto;
import uteis.Validacao;
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
    private CadastroClienteValidation validaCadastroCliente;

    public CadastroClienteControl(CadastroCliente cadastroCliente) {
        clienteDao = new ClienteDao();
        cidadeDao = new CidadeDao();
        this.frameCadastroCliente = cadastroCliente;
        validaCadastroCliente = new CadastroClienteValidation(frameCadastroCliente);
    }

    public void cadastrarClienteAction() {
        if (validaCadastroCliente.validaCamposCadastro()) return;
        
        cliente = new Cliente();
        cliente.setNome(frameCadastroCliente.getTfNome().getText());
        cliente.setCep(frameCadastroCliente.getTfCep().getText());
        try {
            cliente.setDataNascimento(Conversor.data(frameCadastroCliente.getTfDataNascimento().getText()));
        } catch (Exception exception) {
            Mensagem.msg(Texto.NASCIMENTO_INVALIDO);
        }
        cliente.setCidade(CadastroCidadeControl.cidadeSelecionada);
        cliente.setAtivo(1);
        if (clienteDao.cadastrar(cliente) != 0) {
            Mensagem.msg(Texto.CADASTRO_SUCESSO);
        } else {
            Mensagem.msg(Texto.CADASTRO_ERRO);
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
        ListarCidade.tblCidade.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                Point point = mouseEvent.getPoint();
                if (mouseEvent.getClickCount() == 2) {
                    List<Cidade> cidades = cidadeDao.listar();
                    for (int i = 0; i < cidades.size(); i++) {
                        String cidade = cidades.get(i).getNome();
                        if (cidade.equals(cidadeSelecionada.getNome())) {
                            CadastroCliente.cbCidade.setSelectedIndex(i);
                            
                        }
                    }
                }
            }
        });

    }

}
