package control;

import dao.CidadeDao;
import java.util.List;
import model.Cidade;
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

    public void pesquisarCidadeAction() {
        List<Cidade> cidadesPesquisadas = cidadeDao.pesquisar(ListarCidade.tfPesquisa.getText());
        if (cidadesPesquisadas == null) {
            tabelaCidade.limpar();
            cidadesPesquisadas = cidadeDao.listar();
        } else {
            tabelaCidade.limpar();
            tabelaCidade.adicionar(cidadesPesquisadas);
        }

    }

    public void carregarClientesDoBanco() {
        tabelaCidade.adicionar(cidadeDao.listar("nome", "asc"));
    }

}
