package senac.control;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import senac.dao.ProdutoDao;
import senac.model.Produto;
import senac.uteis.Mensagens;
import senac.uteis.Painel;
import senac.uteis.Validacao;
import senac.view.Gerenciar;

/**
 *
 * @author William
 */
public class ProdutoControl {

    List<Produto> listProdutos;
    ProdutoDao produtoDao;
    Produto produto;

    public ProdutoControl() {
        produtoDao = new ProdutoDao();
    }

    public void listarAction() {
        listProdutos = produtoDao.listar();
        atualizarJTable();
    }

    private void atualizarJTable() {
        DefaultTableModel model
                = (DefaultTableModel) Gerenciar.tblProdutos.getModel();
        model.setNumRows(0);
        double total = 0;
        for (Produto p : listProdutos) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getValor()
            });

            total += p.getValor();
        }
        atualizarLabelTotal(total);
    }

    private void atualizarLabelTotal(double total) {
        Gerenciar.lblValorTotal.setText("R$" + total);
    }

    public void cadastrarAction() {
        if (validandoCampos()) {
            return;
        }

        produto = new Produto();
        produto.setNome(Gerenciar.tfNome.getText());
        produto.setValor(Gerenciar.tfValor.getText());
        
        int res = produtoDao.cadastrar(produto);
        if (res > 0) {
            Painel.msg(Mensagens.SUCESSO_CADASTRO);
            listarAction();
        } else {
            Painel.msg(Mensagens.ERRO_CADASTRAR);
        }

        limpandoCampos();
        produto = null;
    }

    private Produto getProdutoSelecionadoTable() {
        int i = Gerenciar.tblProdutos.getSelectedRow();
        if (i >= 0) {
            return listProdutos.get(i);
        }
        return null;
    }

    public void excluirAction() {
        //Resgatar indice da linha selecionada
        Produto p = getProdutoSelecionadoTable();
        if (p == null) {
            Painel.msg(Mensagens.PRODUTO_NAO_SELECIONADO);
            return;
        }
        if (Painel.questao(Mensagens.ACAO_IRREVERSIVEL + p.getNome() + "?") == JOptionPane.YES_OPTION) {
            if (produtoDao.deletar(p.getId())) {
                Painel.msg(Mensagens.SUCESSO_EXCLUIR);
                listarAction();
            } else {
                Painel.msg(Mensagens.ERRO_EXCLUIR);
            }
        }
    }

    public void popularFormAction() {
        produto = getProdutoSelecionadoTable();
        if (produto == null) {
            Painel.msg(Mensagens.PRODUTO_NAO_SELECIONADO);
            return;
        }
        Gerenciar.tfNome.setText(produto.getNome());
        Gerenciar.tfValor.setText(String.valueOf(produto.getValor()));
        Gerenciar.tfNome.requestFocus();
    }

    public void editarAction() {
        if (validandoCampos()) {
            return;
        }

        produto.setNome(Gerenciar.tfNome.getText());
        produto.setValor(Gerenciar.tfValor.getText());
        if (produtoDao.alterar(produto)) {
            Painel.msg(Mensagens.SUCESSO_EDITAR);
            listarAction();
        } else {
            Painel.msg(Mensagens.ERRO_EDITAR);
        }

        limpandoCampos();
        produto = null;
    }

    private boolean validandoCampos() {
        if (Validacao.campoVazio(Gerenciar.tfNome)) {
            Painel.msg(Mensagens.ERRO_NOME_INVALIDO);
            Gerenciar.tfNome.requestFocus();
            return true;
        }
        if (Validacao.campoVazio(Gerenciar.tfValor)) {
            Painel.msg(Mensagens.ERRO_VALOR_INVALIDO);
            Gerenciar.tfValor.requestFocus();
            return true;
        }
        return false;
    }

    public void salvarAction() {
        if (produto == null) {
            cadastrarAction();
        } else {
            editarAction();
        }
    }

    public void pesquisarAction() {
        listProdutos = produtoDao.pesquisar(Gerenciar.tfPesquisa.getText());
        atualizarJTable();
    }

    private void limpandoCampos() {
        Gerenciar.tfNome.setText("");
        Gerenciar.tfValor.setText("");
    }

}
