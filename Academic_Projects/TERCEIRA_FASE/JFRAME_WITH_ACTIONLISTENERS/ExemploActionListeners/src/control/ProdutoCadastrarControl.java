package control;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import model.entidade.Produto;
import view.ProdutoCadastrar;

/**
 *
 * @author Alunos
 */
public class ProdutoCadastrarControl {

    private JFrame frameProdutoCadastrar;
    private JFrame formPai; // Tela que chamou Esta tela.

    public ProdutoCadastrarControl() {
        frameProdutoCadastrar = new ProdutoCadastrar();
    }

    public ProdutoCadastrarControl(JFrame formPai) {
        this.formPai = formPai;
        frameProdutoCadastrar = new ProdutoCadastrar();
        frameProdutoCadastrar.setVisible(true);
        formPai.setEnabled(false);
        listenerWindow();
    }

    private void listenerWindow() {
        frameProdutoCadastrar.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                frameProdutoCadastrar.dispose();
                formPai.setEnabled(true); // habilita o edit da tela.
                formPai.setVisible(true); // sai de traz das janelas e assume
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public Produto getProduto() {
        Produto p = new Produto();
        p.setId(1);
        p.setNome(ProdutoCadastrar.tfNome.getText());
        p.setValor(Double.valueOf(ProdutoCadastrar.tfValor.getText()));
        return p;
    }

    public JFrame getFrameProdutoCadastrar() {
        return frameProdutoCadastrar;
    }

}
