package passagemparametros.control;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import passagemparametros.model.entidade.Produto;
import passagemparametros.view.DialogProdutoFormulario;
import uteis.ProdutoValidacao;

public class ProdutoFormularioControl {

    private JDialog dialog;
    private Produto produto;
    private boolean validado;

    public ProdutoFormularioControl(JDialog dialog) {
        this.dialog = dialog;
        limparCamposFormulario();
    }

    public ProdutoFormularioControl(JDialog dialog, Produto produto) {
        this.dialog = dialog;
        this.produto = produto;
        this.popularForm(produto);
    }

    private void popularForm(Produto p) {
        DialogProdutoFormulario.tfNomeD.setText(p.getNome());
        DialogProdutoFormulario.tfValorD.setText(String.valueOf(p.getValor()));
    }

    public Produto getProdutoFormularioAction() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome(DialogProdutoFormulario.tfNomeD.getText());
        produto.setValor(DialogProdutoFormulario.tfValorD.getText());
        return produto;
    }

    public void enviarDadosAction() {
        if (validaProduto(getProdutoFormularioAction())) {
            dialog.dispose();
        }
    }

    private boolean validaProduto(Produto p) {
        validado = true;
        if (ProdutoValidacao.validaNome(p.getNome()) == false) {
            JOptionPane.showMessageDialog(null, "Preencha o campo nome");
            DialogProdutoFormulario.tfNomeD.requestFocus();
            validado = false;
        } else if (ProdutoValidacao.validaValor(p.getValor()) == false) {
            JOptionPane.showMessageDialog(null, "Preencha corretamente o campo idade");
            DialogProdutoFormulario.tfValorD.requestFocus();
            validado = false;
        }
        return validado;
    }

    public boolean isValidado() {
        return validado;
    }
    
    public void limparCamposFormulario(){
        DialogProdutoFormulario.tfNomeD.setText(null);
        DialogProdutoFormulario.tfValorD.setText(null);
    }

}
