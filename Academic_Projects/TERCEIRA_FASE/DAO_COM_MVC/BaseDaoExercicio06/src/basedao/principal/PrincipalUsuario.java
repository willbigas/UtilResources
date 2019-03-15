package basedao.principal;

import javax.swing.JFrame;
import basedao.view.usuario.JanelaGerenciaUsuario;
import basedao.view.usuario.JanelaUsuarioEdit;

/**
 *
 * @author William
 */
public class PrincipalUsuario {

    public static void main(String[] args) {
        JanelaPrincipal();
    }

    public static void JanelaPrincipal() {
        JanelaGerenciaUsuario painelPrincipal = new JanelaGerenciaUsuario();
        painelPrincipal.setTitle("JANELA PRINCIPAL");
        painelPrincipal.setSize(650, 500);
        painelPrincipal.setLocationRelativeTo(null);
        painelPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelPrincipal.setVisible(true);
    }
    public static void JanelaUsuarioEdit() {
        JanelaUsuarioEdit painelEdit = new JanelaUsuarioEdit();
        painelEdit.setTitle("EDITAR USUARIO");
        painelEdit.setLocationRelativeTo(null);
        painelEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelEdit.setVisible(true);
    }
    
   
}
