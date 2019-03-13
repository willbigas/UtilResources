package basedao.principal;

import javax.swing.JFrame;
import br.com.basedao.view.contato.JanelaGerenciarContato;

/**
 *
 * @author William
 */
public class PrincipalContato {

    public static void main(String[] args) {
        JanelaPrincipal();
    }

    public static void JanelaPrincipal() {
        JanelaGerenciarContato painelPrincipal = new JanelaGerenciarContato();
        painelPrincipal.setTitle("JANELA PRINCIPAL");
        painelPrincipal.setSize(800, 600);
        painelPrincipal.setLocationRelativeTo(null);
        painelPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painelPrincipal.setVisible(true);
    }
}
