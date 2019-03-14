package basedao.principal;

import br.com.basedao.view.contato.JanelaContatoEdit;
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
        painelPrincipal.setLocationRelativeTo(null);
        painelPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painelPrincipal.setVisible(true);
    }

    public static void JanelaContatoEdit() {
        JanelaContatoEdit painelEdit = new JanelaContatoEdit();
        painelEdit.setTitle("EDITAR CONTATO");
        painelEdit.setLocationRelativeTo(null);
        painelEdit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painelEdit.setVisible(true);
    }
}
