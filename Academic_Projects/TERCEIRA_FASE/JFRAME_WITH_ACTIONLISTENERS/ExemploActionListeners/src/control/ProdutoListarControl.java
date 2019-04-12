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
    
    
    //Ação para clique do botão novo
    ActionListener acaoBotaoNovo = acaoBotaoNovo();
    private ActionListener acaoBotaoNovo(){
         return new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 produtoCadastrarControl = new ProdutoCadastrarControl(frameListarProdutos);
             }
             
         };
        
    }

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
    
    private ActionListener botaoSalvar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               resgatarDados();
            }
        };
    }
    
    public void resgatarDados() {
        Produto p = produtoCadastrarControl.getProduto();
        produtoTableModel.adicionar(p);
    }

}
