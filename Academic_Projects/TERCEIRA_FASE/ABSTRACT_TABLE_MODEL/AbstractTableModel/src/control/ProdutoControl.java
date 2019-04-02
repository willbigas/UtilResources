package control;

import dao.ProdutoDao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import model.Produto;
import model.ProdutoTableModel;
import util.OptionPane;
import util.Text;
import view.ViewProduto;

/**
 *
 * @author William
 */
public class ProdutoControl {

    ProdutoTableModel TABLE_PRODUTO;
    ProdutoDao PRODUTO_DAO = new ProdutoDao();
    Produto PRODUTO;

    public ProdutoControl() {
        TABLE_PRODUTO = new ProdutoTableModel();
        TABLE_PRODUTO.limpar(); // limpa tudo que estiver em memoria
        TABLE_PRODUTO.addListaDeProdutos(PRODUTO_DAO.listar()); // puxa os dados do banco
        setModelOfTable(); // seta modelo da tabela
    }

    /**
     * Pega linha selecionada da Tabela da View e Transforma em Um Produto da
     * Lista do TableModel
     *
     * @return Produto com o Indice da List do TableModel
     */
    private Produto pegaProdutoSelecionado() {
        return TABLE_PRODUTO.getProduto(ViewProduto.tblProdutos.getSelectedRow());
    }

    /**
     * Pega Linha selecionada da JTable da view
     *
     * @return int com o indice selecionado
     */
    private int pegaLinhaSelecionada() {
        return ViewProduto.tblProdutos.getSelectedRow();
    }

    /**
     * Seta o modelo da Tabela para o proprio TableModel;
     */
    public void setModelOfTable() {
        ViewProduto.tblProdutos.setModel(TABLE_PRODUTO); // Tabela da View;
    }

    /**
     *
     */
    public void createProductAction() {
        PRODUTO = new Produto();
        PRODUTO.setDescricao(ViewProduto.tfDescricao.getText());
        PRODUTO.setQtd(Integer.valueOf(ViewProduto.tfQuantidade.getText()));
        PRODUTO.setValor(Double.valueOf(ViewProduto.tfValor.getText()));
        int idRecebido = PRODUTO_DAO.cadastrar(PRODUTO);
        PRODUTO.setId(idRecebido);
        TABLE_PRODUTO.addProduto(PRODUTO); // adiciona linha;
        PRODUTO = null;
        OptionPane.msgInfo(Text.SUCESS_CREATE);
        limparCamposAction();
    }

    public void deleteProductAction() {
        validaLinhaNaoSelecionada();
        PRODUTO = pegaProdutoSelecionado(); // pega produto da linha selecionada
        PRODUTO_DAO.deletar(PRODUTO);
        TABLE_PRODUTO.removeProduto(pegaLinhaSelecionada()); // remove linha do produto
        PRODUTO = null;
        limparCamposAction();
    }

    private void validaLinhaNaoSelecionada() throws HeadlessException {
        if (pegaLinhaSelecionada() == -1) {
            JOptionPane.showMessageDialog(null, "Voce precisa selecionar uma linha!");
            return;
        }

    }

    private void updateProductAction() {
        validaLinhaNaoSelecionada();
        ViewProduto.tblProdutos.setValueAt(ViewProduto.tfDescricao.getText(),
                pegaLinhaSelecionada(), 1); // alterando coluna 1
        ViewProduto.tblProdutos.setValueAt(ViewProduto.tfQuantidade.getText(),
                pegaLinhaSelecionada(), 2); // alterando coluna 2
        ViewProduto.tblProdutos.setValueAt(ViewProduto.tfValor.getText(),
                pegaLinhaSelecionada(), 3); // alterando coluna 3
        if (PRODUTO_DAO.alterar(PRODUTO)) {
            PRODUTO = null;
            OptionPane.msgInfo(Text.SUCESS_EDIT);
            limparCamposAction();
        } else {
            OptionPane.msgError(Text.ERROR_EDIT);
        }

    }

    public void limparCamposAction() {
        ViewProduto.tfDescricao.setText(null);
        ViewProduto.tfQuantidade.setText(null);
        ViewProduto.tfValor.setText(null);
    }

    public void carregaProdutoNoFormAction() {
        PRODUTO = pegaProdutoSelecionado();
        ViewProduto.tfDescricao.setText(PRODUTO.getDescricao());
        ViewProduto.tfQuantidade.setText(String.valueOf(PRODUTO.getQtd()));
        ViewProduto.tfValor.setText(String.valueOf(PRODUTO.getValor()));
    }

    public void saveProductAction() {
        if (PRODUTO == null) {
            createProductAction();
        } else {
            updateProductAction();
        }
    }

}
