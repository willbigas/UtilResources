/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passagemparametros.control;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import passagemparametros.model.entidade.Produto;
import passagemparametros.view.DialogProdutoFormulario;
import passagemparametros.view.ProdutoVer;

/**
 *
 * @author Clony
 */
public class ProdutoVisualizarControl {

    private JDialog dialog;
    private JDialog dialogProdutoForm;
    private static Produto produto;
    public static Integer indexSelecionada;

    public ProdutoVisualizarControl(JDialog dialog) {
        this.dialog = dialog;
    }

    public ProdutoVisualizarControl(JDialog dialog, Produto produto) {
        this.dialog = dialog;
        this.produto = produto;
    }

    public Produto getProdutoVerAction() {
        Produto produto = new Produto();
        produto.setId(Integer.valueOf(ProdutoVer.lblCodigoProduto.getText()));
        produto.setNome(ProdutoVer.lblNomeProduto.getText());
        produto.setValor(ProdutoVer.lblValorProduto.getText());
        return produto;
    }

    public void acessarAtualizacaoDeProdutoAction() {
        System.out.println("Produto da view" + produto);
        dialogProdutoForm = new DialogProdutoFormulario();
        dialogProdutoForm.setModal(true);
        dialogProdutoForm.setVisible(true);
    }

    public static void atualizarProdutoAction() {
        produto.setNome(DialogProdutoFormulario.tfNomeD.getText());
        produto.setValor(DialogProdutoFormulario.tfValorD.getText());
        ProdutoListarControl.tableModel.updateRow(indexSelecionada, produto);
        ProdutoVer.lblNomeProduto.setText(produto.getNome());
        ProdutoVer.lblValorProduto.setText(String.valueOf(produto.getValor()));
        
    }
    
    public void excluirProdutoAction() {
        int confirm = JOptionPane.showConfirmDialog(null, "Você Deseja realmente excluir esse produto?", "Pergunta", JOptionPane.YES_OPTION);
        if (confirm == JOptionPane.CANCEL_OPTION || confirm == JOptionPane.NO_OPTION) {
            return;
        }
        if (confirm == JOptionPane.OK_OPTION) {
            ProdutoListarControl.tableModel.removeRow(indexSelecionada);
            dialog.dispose();
            JOptionPane.showMessageDialog(null, "Produto Excluido com sucesso!");
        }

    }

}
