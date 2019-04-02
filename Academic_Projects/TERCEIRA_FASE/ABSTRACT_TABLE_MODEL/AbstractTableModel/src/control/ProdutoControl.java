package control;

import dao.ProdutoDao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import model.Produto;
import model.ProdutoTableModel;
import view.ViewProduto;

/**
 *
 * @author William
 */
public class ProdutoControl {

    ProdutoTableModel TABLE_PRODUTO;
    ProdutoDao vendaDao = new ProdutoDao();
    Produto PRODUTO;

    public ProdutoControl() {
        TABLE_PRODUTO = new ProdutoTableModel();
        TABLE_PRODUTO.limpar();
        TABLE_PRODUTO.addListaDeProdutos(vendaDao.listar());
    }

    /**
     * Seta o modelo da Tabela;
     */
    public void setModelOfTable() {
        ViewProduto.tblProdutos.setModel(TABLE_PRODUTO); // Tabela da View;
    }

    public void criarProdutoAction() {
        PRODUTO = new Produto();
        PRODUTO.setId(Integer.MAX_VALUE);
        PRODUTO.setDescricao(ViewProduto.tfDescricao.getText());
        PRODUTO.setQtd(Integer.valueOf(ViewProduto.tfQuantidade.getText()));
        PRODUTO.setValor(Double.valueOf(ViewProduto.tfValor.getText()));
        TABLE_PRODUTO.addProduto(PRODUTO); // adiciona linha;
        PRODUTO = null;
    }

    public void excluirProdutoAction() {
        validaLinhaNaoSelecionada();
        TABLE_PRODUTO.removeProduto(ViewProduto.tblProdutos.getSelectedRow());
        PRODUTO = null;
    }

    private void validaLinhaNaoSelecionada() throws HeadlessException {
        if (ViewProduto.tblProdutos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Voce precisa selecionar uma linha!");
            return;
        }

    }

    public void alteraProdutoAction() {
        validaLinhaNaoSelecionada();
        ViewProduto.tblProdutos.setValueAt(ViewProduto.tfDescricao.getText(),
                ViewProduto.tblProdutos.getSelectedRow(), 1);
        ViewProduto.tblProdutos.setValueAt(ViewProduto.tfQuantidade.getText(),
                ViewProduto.tblProdutos.getSelectedRow(), 2);
        ViewProduto.tblProdutos.setValueAt(ViewProduto.tfValor.getText(),
                ViewProduto.tblProdutos.getSelectedRow(), 3);

        PRODUTO = null;

    }

}
