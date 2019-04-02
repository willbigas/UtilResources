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
    ProdutoDao produtoDao = new ProdutoDao();
    Produto PRODUTO;

    public ProdutoControl() {
        TABLE_PRODUTO = new ProdutoTableModel();
        TABLE_PRODUTO.limpar();
        TABLE_PRODUTO.addListaDeProdutos(produtoDao.listar());
        setModelOfTable();
    }

    private Produto pegaProdutoSelecionado() {
        return TABLE_PRODUTO.getProduto(ViewProduto.tblProdutos.getSelectedRow());
    }

    private int pegaLinhaSelecionada() {
        return ViewProduto.tblProdutos.getSelectedRow();
    }

    /**
     * Seta o modelo da Tabela;
     */
    public void setModelOfTable() {
        ViewProduto.tblProdutos.setModel(TABLE_PRODUTO); // Tabela da View;
    }

    public void criarProdutoAction() {
        PRODUTO = new Produto();
        PRODUTO.setDescricao(ViewProduto.tfDescricao.getText());
        PRODUTO.setQtd(Integer.valueOf(ViewProduto.tfQuantidade.getText()));
        PRODUTO.setValor(Double.valueOf(ViewProduto.tfValor.getText()));
        int idRecebido = produtoDao.cadastrar(PRODUTO);
        PRODUTO.setId(idRecebido);
        TABLE_PRODUTO.addProduto(PRODUTO); // adiciona linha;
        PRODUTO = null;
        limparCampos();
    }

    public void excluirProdutoAction() {
        validaLinhaNaoSelecionada();
        PRODUTO = pegaProdutoSelecionado(); // pega produto da linha selecionada
        produtoDao.deletar(PRODUTO);
        TABLE_PRODUTO.removeProduto(pegaLinhaSelecionada()); // remove linha do produto
        PRODUTO = null;
        limparCampos();
    }

    private void validaLinhaNaoSelecionada() throws HeadlessException {
        if (pegaLinhaSelecionada() == -1) {
            JOptionPane.showMessageDialog(null, "Voce precisa selecionar uma linha!");
            return;
        }

    }

    public void alteraProdutoAction() {
        validaLinhaNaoSelecionada();

        ViewProduto.tblProdutos.setValueAt(ViewProduto.tfDescricao.getText(),
                ViewProduto.tblProdutos.getSelectedRow(), 1); // alterando coluna 1
        ViewProduto.tblProdutos.setValueAt(ViewProduto.tfQuantidade.getText(),
                ViewProduto.tblProdutos.getSelectedRow(), 2); // alterando coluna 2
        ViewProduto.tblProdutos.setValueAt(ViewProduto.tfValor.getText(),
                ViewProduto.tblProdutos.getSelectedRow(), 3); // alterando coluna 3
        PRODUTO = null;
        limparCampos();
    }

    public void limparCampos() {
        ViewProduto.tfDescricao.setText(null);
        ViewProduto.tfQuantidade.setText(null);
        ViewProduto.tfValor.setText(null);
    }
    
    

}
