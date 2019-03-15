package basedao.control;

import basedao.dao.ContatoDao;
import basedao.model.Contato;
import basedao.principal.PrincipalContato;
import javax.swing.table.DefaultTableModel;
import basedao.util.UtilFormat;
import br.com.basedao.view.contato.JanelaContatoEdit;
import br.com.basedao.view.contato.JanelaGerenciarContato;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class ContatoControl {

    public static ContatoDao CONTATO_DAO = new ContatoDao();
    private static Integer ID_SELECIONADO;
   

    public ContatoControl() {
        
    }

    public void inserirContatoAction() {
        Contato c = new Contato();
        try {
            c.setId(1);
            c.setNome(JanelaGerenciarContato.campoNome.getText());
            c.setEmail(JanelaGerenciarContato.campoEmail.getText());
            c.setTelefone(Integer.valueOf(JanelaGerenciarContato.campoTelefone.getText()));
            c.setCelular(Integer.valueOf(JanelaGerenciarContato.campoCelular.getText()));
            c.setNascimento(UtilFormat.data(JanelaGerenciarContato.campoNascimento.getText()));
        } catch (Exception exception) {
        }

        
        
        if (CONTATO_DAO.cadastrar(c)) {
            System.out.println("Contato Cadastrado");
            listarAction();
        } else {
            System.out.println("Deu ruim!");
        }
    }

    public void listarAction() {
        DefaultTableModel model = (DefaultTableModel) JanelaGerenciarContato.tabelaContato.getModel();
        model.setNumRows(0);
        for (Contato c : CONTATO_DAO.listar()) {
            model.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getEmail(),
                c.getTelefone(),
                c.getCelular(),
                c.getNascimento()
            });
        }
    }
    
    public void listarAction(List<Contato> contatos) {
        DefaultTableModel model = (DefaultTableModel) JanelaGerenciarContato.tabelaContato.getModel();
        model.setNumRows(0);
        for (Contato c : contatos) {
            model.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getEmail(),
                c.getTelefone(),
                c.getCelular(),
                c.getNascimento()
            });
        }
    }
    
     public void pesquisarContatoAction() {
        List<Contato> contatos = null;
        try {
            contatos = CONTATO_DAO.buscarPorTermo(JanelaGerenciarContato.campoBuscar.getText());
        } catch (Exception exception) {
        }
        listarAction(contatos);

    }
     
     public void editarContatoAction() {

        int linha = JanelaGerenciarContato.tabelaContato.getSelectedRow();
        if (linha >= 0) {
            Integer idUsuario = (Integer) JanelaGerenciarContato.tabelaContato.getValueAt(linha, 0);
            ID_SELECIONADO = idUsuario;
            Contato c = null;
            try {
                c = (Contato) CONTATO_DAO.buscaPorId(ID_SELECIONADO);
            } catch (Exception exception) {
            }
            if (c != null) {
                try {
                    mostrandoContatoEditado(c);
                } catch (Exception exception) {
                }
            }

        }
    }
     
      public void mostrandoContatoEditado(Contato c) {
        if (c != null) {
            try {
                JanelaContatoEdit.campoNome.setText(c.getNome());
                JanelaContatoEdit.campoEmail.setText(c.getEmail());
                JanelaContatoEdit.campoNascimento.setText(UtilFormat.data(c.getNascimento()));
                JanelaContatoEdit.campoTelefone.setText(String.valueOf(c.getTelefone()));
                JanelaContatoEdit.campoCelular.setText(String.valueOf(c.getCelular()));
            } catch (Exception exception) {
            }
            PrincipalContato.JanelaContatoEdit();
        }

    }
      
      public Boolean atualizarAction() {
        Contato c = new Contato();
        try {
            c.setId(ID_SELECIONADO);
            System.out.println(ID_SELECIONADO);
            c.setNome(JanelaContatoEdit.campoNome.getText());
            c.setEmail(JanelaContatoEdit.campoEmail.getText());
            c.setNascimento(UtilFormat.data(JanelaContatoEdit.campoNascimento.getText()));
            c.setTelefone(Integer.valueOf(JanelaContatoEdit.campoTelefone.getText()));
            c.setCelular(Integer.valueOf(JanelaContatoEdit.campoCelular.getText()));
        } catch (Exception exception) {
        }
        try {
            if (CONTATO_DAO.alterar(c)) {
                JOptionPane.showMessageDialog(null, "Contato Atualizado com Sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Não consegui atualizar o Contato");
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioControl.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
        return null;
    }
      
      public void deletarContatoAction() {

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int resposta = JOptionPane.showConfirmDialog(null, "Você deseja Realmente excluir Esse Contato?\r\nEsta Ação é irreversivel!", "ATENÇÃO!", dialogButton);

        if (resposta == JOptionPane.NO_OPTION) {
            return;
        } else {
            int linha = JanelaGerenciarContato.tabelaContato.getSelectedRow();
            if (linha >= 0) {
                Integer idProduto = (Integer) JanelaGerenciarContato.tabelaContato.getValueAt(linha, 0);
                boolean apagou = false;
                try {
                    apagou = CONTATO_DAO.deletarPorId(idProduto);
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
                List<Contato> contatos = CONTATO_DAO.buscarPorTermo("");
                listarAction(contatos);
            } catch (Exception exception) {
            }

        }

    }
      
      public Boolean seExisteNoBancoAction() {
        List<Contato> contatosRecebidos;
        try {
            contatosRecebidos = (List<Contato>) CONTATO_DAO.listar();

            for (Contato c : contatosRecebidos) {
                if (c.getNome().toLowerCase().equals(JanelaGerenciarContato.campoNome.getText().toLowerCase())) {
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
