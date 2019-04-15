package control;

import javax.swing.JDialog;
import javax.swing.JFrame;
import view.ProdutoCadastrar;

/**
 *
 * @author Alunos
 */
public class ProdutoCadastrarControl {

    private JDialog dialogProdutoCadastrar;

    public ProdutoCadastrarControl(JFrame framePai , boolean modal) {
        dialogProdutoCadastrar = new ProdutoCadastrar(framePai , modal);
        dialogProdutoCadastrar.setVisible(true);
    }


}
