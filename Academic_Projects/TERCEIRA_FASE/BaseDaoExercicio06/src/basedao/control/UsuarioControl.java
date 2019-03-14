package basedao.control;

import javax.swing.table.DefaultTableModel;
import basedao.dao.UsuarioDao;
import basedao.model.Usuario;
import basedao.principal.PrincipalUsuario;
import basedao.view.usuario.JanelaGerenciaUsuario;
import basedao.view.usuario.JanelaUsuarioEdit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class UsuarioControl {

    public static UsuarioDao USUARIO_DAO = new UsuarioDao();
    private static Integer ID_SELECIONADO;

    public UsuarioControl() {

    }

    public void inserindoUsuarioAction() {
        Usuario u = criandoUsuario();
        enviandoProBanco(u);
    }

    private Usuario criandoUsuario() throws NumberFormatException {
        Usuario u = new Usuario();
        u.setId(Integer.MAX_VALUE);
        u.setNome(JanelaGerenciaUsuario.campoNome.getText());
        u.setEmail(JanelaGerenciaUsuario.campoEmail.getText());
        u.setSenha(JanelaGerenciaUsuario.campoSenha.getText());
        u.setNivel(Integer.valueOf(JanelaGerenciaUsuario.campoNivel.getText()));
        return u;
    }

    private void enviandoProBanco(Usuario u) {
        if (USUARIO_DAO.cadastrar(u)) {
            System.out.println("Produto Cadastrado");
            listarUsuarioAction();
        } else {
            System.out.println("Deu ruim!");
        }
    }

    public void listarUsuarioAction() {
        DefaultTableModel model = (DefaultTableModel) JanelaGerenciaUsuario.tabelaUsuario.getModel();
        model.setNumRows(0);
        for (Usuario u : USUARIO_DAO.listar()) {
            model.addRow(new Object[]{
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getSenha(),
                u.getNivel()
            });
        }
    }

    public void listarUsuarioAction(List<Usuario> usuarios) {
        DefaultTableModel model = (DefaultTableModel) JanelaGerenciaUsuario.tabelaUsuario.getModel();
        model.setNumRows(0);
        for (Usuario u : usuarios) {
            model.addRow(new Object[]{
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getNivel()
            });
        }
    }

    public void pesquisarUsuarioAction() {
        List<Usuario> usuarios = null;
        try {
            usuarios = USUARIO_DAO.buscarPorTermo(JanelaGerenciaUsuario.campoBuscar.getText());
        } catch (Exception exception) {
        }
        listarUsuarioAction(usuarios);

    }

    public void editarUsuarioAction() {

        int linha = JanelaGerenciaUsuario.tabelaUsuario.getSelectedRow();
        if (linha >= 0) {
            Integer idUsuario = (Integer) JanelaGerenciaUsuario.tabelaUsuario.getValueAt(linha, 0);
            ID_SELECIONADO = idUsuario;
            Usuario u = null;
            try {
                u = (Usuario) USUARIO_DAO.buscaPorId(ID_SELECIONADO);
            } catch (Exception exception) {
            }
            if (u != null) {
                try {
                    mostrandoUsuarioEditado(u);
                } catch (Exception exception) {
                }
            }

        }
    }

    public void mostrandoUsuarioEditado(Usuario u) {
        if (u != null) {
            try {
                JanelaUsuarioEdit.campoNome.setText(u.getNome());
                JanelaUsuarioEdit.campoEmail.setText(u.getEmail());
                JanelaUsuarioEdit.campoSenha.setText(u.getSenha());
                JanelaUsuarioEdit.campoNivel.setText(String.valueOf(u.getNivel()));
            } catch (Exception exception) {
            }
            PrincipalUsuario.JanelaUsuarioEdit();
        }

    }

    public Boolean atualizarAction() {
        Usuario u = new Usuario();
        try {
            u.setId(ID_SELECIONADO);
            System.out.println(ID_SELECIONADO);
            u.setNome(JanelaUsuarioEdit.campoNome.getText());
            u.setSenha(JanelaUsuarioEdit.campoSenha.getText());
            u.setEmail(JanelaUsuarioEdit.campoEmail.getText());
            u.setNivel(Integer.valueOf(JanelaUsuarioEdit.campoNivel.getText()));
        } catch (Exception exception) {
        }
        try {
            if (USUARIO_DAO.alterar(u)) {
                JOptionPane.showMessageDialog(null, "Usuario Atualizado com Sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Não consegui atualizar o usuario");
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deletarUsuarioAction() {

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int resposta = JOptionPane.showConfirmDialog(null, "Você deseja Realmente excluir Esse Usuario?\r\nEsta Ação é irreversivel!", "ATENÇÃO!", dialogButton);

        if (resposta == JOptionPane.NO_OPTION) {
            return;
        } else {
            int linha = JanelaGerenciaUsuario.tabelaUsuario.getSelectedRow();
            if (linha >= 0) {
                Integer idProduto = (Integer) JanelaGerenciaUsuario.tabelaUsuario.getValueAt(linha, 0);
                boolean apagou = false;
                try {
                    apagou = USUARIO_DAO.deletarPorId(idProduto);
                    System.out.println(idProduto);
                } catch (Exception exception) {
                }
                if (apagou) {
                    JOptionPane.showMessageDialog(null, "Contato excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível excluir o contato , Verifique suas Dependencias");

                }
            }
            try {
                List<Usuario> produtos = USUARIO_DAO.buscarPorTermo("");
                listarUsuarioAction();
            } catch (Exception exception) {
            }

        }

    }

    public Boolean seExisteNoBancoAction() {
        List<Usuario> usuariosRecebidos;
        try {
            usuariosRecebidos = (List<Usuario>) USUARIO_DAO.listar();

            for (Usuario user : usuariosRecebidos) {
                if (user.getNome().toLowerCase().equals(JanelaGerenciaUsuario.campoNome.getText().toLowerCase())) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

}
