package passagemparametros.control;

import javax.swing.JFrame;
import passagemparametros.model.entidade.Produto;
import passagemparametros.model.tablemodel.ProdutoTableModel;
import passagemparametros.view.DialogProdutoFormulario;
import passagemparametros.view.ProdutoListar;
import passagemparametros.view.ProdutoVer;

/**
 *
 * @author Clony
 */
public class ProdutoListarControl {

    public static ProdutoTableModel tableModel;
    private static Produto produto;
    private JFrame frame;
    Integer idProduto = 1;
  

    public ProdutoListarControl(JFrame frame) {
        this.frame = frame;
        configurarTableProdutos();
    }

    public ProdutoListarControl() {

    }

    private void configurarTableProdutos() {
        tableModel = new ProdutoTableModel();
        ProdutoListar.tbProdutos.setModel(tableModel);
    }

    public void chamarTelaFormularioProdutoAction() {
        DialogProdutoFormulario dlg = new DialogProdutoFormulario(frame, true);
        dlg.setVisible(true);
        produto = dlg.getControl().getProdutoFormularioAction();
        produto.setId(idProduto);
        if (dlg.getControl().isValidado()) {
            tableModel.addRow(produto);
            idProduto++;
        }
    }

    public void chamarTelaVisualizarProdutoAction() {
        ProdutoVer dlg = new ProdutoVer(frame, true, produto);
        populaFormVer();
        System.out.println("Produto da Tela visualizar" + produto);
        dlg.setVisible(true);
    }

//    public void chamarTelaEditarProdutoAction() {
//        System.out.println("Produto da view" + produto);
//        System.out.println("Novo Produto" + produto);
////            tableModel.updateRow(indexSelecionada, produto);
//
//    }

    public void populaFormVer() {
        produto = tableModel.getRow(ProdutoListar.tbProdutos.getSelectedRow());
        ProdutoVer.lblCodigoProduto.setText(String.valueOf(produto.getId()));
        ProdutoVer.lblNomeProduto.setText(String.valueOf(produto.getNome()));
        ProdutoVer.lblValorProduto.setText(String.valueOf(produto.getValor()));
        System.out.println("Produto Recebido " + produto);

    }

    public void limpaFormVer() {
        produto = null;
        ProdutoVer.lblCodigoProduto.setText(null);
        ProdutoVer.lblNomeProduto.setText(null);
        ProdutoVer.lblValorProduto.setText(null);

    }

    

}
