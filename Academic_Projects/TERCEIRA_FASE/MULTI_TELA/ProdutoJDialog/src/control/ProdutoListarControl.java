package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import model.entidade.Produto;
import model.tablemodel.ProdutoTableModel;
import view.ProdutoCadastrar;
import view.ProdutoListar;

/**
 *
 * @author Alunos
 */
public class ProdutoListarControl {

    private ProdutoTableModel produtoTableModel;
    private JFrame frameListarProdutos;
    private ProdutoCadastrarControl produtoCadastrarControl;

    public ProdutoListarControl() {
        frameListarProdutos = new ProdutoListar();
        frameListarProdutos.setVisible(true);// chamando um Jframe no control (interessante , estudar )

        // Configuração dos componentes
        produtoTableModel = new ProdutoTableModel();
        ProdutoListar.tblProdutos.setModel(produtoTableModel);

//        ProdutoListar.btNovo.removeActionListener(acaoBotaoNovo);
        ProdutoListar.btNovo.addActionListener(acaoBotaoNovo);
        ProdutoCadastrar.btSalvar.addActionListener(botaoSalvar());
    }

    //Ação para clique do botão novo
    ActionListener acaoBotaoNovo = botaoNovo();

    private ActionListener botaoNovo() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                produtoCadastrarControl = new ProdutoCadastrarControl(frameListarProdutos, true);
                limpaCamposCadastrarProduto();
            }

        };

    }

    // Ação para clique do botão salvar
    private ActionListener botaoSalvar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resgatarDados();
                limpaCamposCadastrarProduto();
            }
        };
    }

    public void resgatarDados() {
        Produto p = getProduto();
        produtoTableModel.adicionar(p);

    }

    public void limpaCamposCadastrarProduto() {
        ProdutoCadastrar.tfNome.setText(null);
        ProdutoCadastrar.tfValor.setText(null);
    }

    public Produto getProduto() {
        Produto p = new Produto();
        p.setId(1);
        p.setNome(ProdutoCadastrar.tfNome.getText());
        p.setValor(Double.valueOf(ProdutoCadastrar.tfValor.getText()));
        return p;
    }

}
