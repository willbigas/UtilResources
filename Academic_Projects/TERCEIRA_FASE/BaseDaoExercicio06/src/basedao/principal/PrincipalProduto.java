package basedao.principal;

import javax.swing.JFrame;
import basedao.view.produto.JanelaGerenciarProduto;
import basedao.view.produto.JanelaProdutoEdit;

/**
 *
 * @author William
 */
public class PrincipalProduto {

    public static void main(String[] args) {
        JanelaPrincipal();
    }

    public static void JanelaPrincipal() {
        JanelaGerenciarProduto painelPrincipal = new JanelaGerenciarProduto();
        painelPrincipal.setTitle("JANELA PRINCIPAL");
        painelPrincipal.setLocationRelativeTo(null);
        painelPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painelPrincipal.setVisible(true);
    }
    
     public static void JanelaProdutoEdit() {
         JanelaProdutoEdit janelaEdit = new JanelaProdutoEdit();
        janelaEdit.setTitle("EDITAR PRODUTO");
        janelaEdit.setLocationRelativeTo(null);
        janelaEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janelaEdit.setVisible(true);
    }
}
