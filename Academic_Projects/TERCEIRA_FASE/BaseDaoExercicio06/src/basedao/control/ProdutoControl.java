package basedao.control;

import javax.swing.table.DefaultTableModel;
import basedao.dao.ProdutoDao;
import basedao.model.Produto;
import basedao.principal.PrincipalProduto;
import basedao.util.UtilFormat;
import basedao.view.produto.JanelaGerenciarProduto;
import basedao.view.produto.JanelaProdutoEdit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class ProdutoControl {
    
    public static ProdutoDao PRODUTO_DAO = new ProdutoDao();
    private static  Integer ID_SELECIONADO;
    
    public ProdutoControl() {
        
    }
    
    public void inserirProdutoAction() {
        if (seExisteNoBancoAction()) {
            atualizarAction();
            listarAction();
        } else {
            Produto p = new Produto();
            p.setId(Integer.MAX_VALUE);
            p.setNome(JanelaGerenciarProduto.campoNome.getText());
            p.setValor(Double.valueOf(JanelaGerenciarProduto.campoValor.getText()));
            try {
                p.setDataCadastro(basedao.util.UtilFormat.data(JanelaGerenciarProduto.campoData.getText()));
            } catch (Exception exception) {
            }
            if (PRODUTO_DAO.cadastrar(p)) {
                System.out.println("Produto Cadastrado");
                listarAction();
            } else {
                System.out.println("Deu ruim!");
            }
        }
        
    }
    
    public void listarAction() {
        DefaultTableModel model = (DefaultTableModel) JanelaGerenciarProduto.tabelaProduto.getModel();
        model.setNumRows(0);
        for (Produto p : PRODUTO_DAO.listar()) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getValor(),
                p.getDataCadastro()
            });
        }
    }
    
    public void listarAction(List<Produto> produtos) {
        DefaultTableModel model = (DefaultTableModel) JanelaGerenciarProduto.tabelaProduto.getModel();
        model.setNumRows(0);
        for (Produto p : produtos) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getValor(),
                p.getDataCadastro()
            });
        }
    }
    
    public void pesquisarProdutoAction() {
        List<Produto> produtos = null;
        try {
            produtos = PRODUTO_DAO.buscarPorTermo(JanelaGerenciarProduto.campoBuscar.getText());
        } catch (Exception exception) {
        }
        listarAction(produtos);
        
    }
    
    public void editarUsuarioAction() {
        
        int linha = JanelaGerenciarProduto.tabelaProduto.getSelectedRow();
        if (linha >= 0) {
            Integer idUsuario = (Integer) JanelaGerenciarProduto.tabelaProduto.getValueAt(linha, 0);
            ID_SELECIONADO = idUsuario;
            Produto p = null;
            try {
                p = (Produto) PRODUTO_DAO.buscaPorId(ID_SELECIONADO);
            } catch (Exception exception) {
            }
            if (p != null) {
                try {
                    mostrandoUsuarioEditado(p);
                } catch (Exception exception) {
                }
            }
            
        }
    }
    
    public void mostrandoUsuarioEditado(Produto p) {
        if (p != null) {
            try {
                JanelaProdutoEdit.campoNome.setText(p.getNome());
                JanelaProdutoEdit.campoValor.setText(String.valueOf(p.getValor()));
                JanelaProdutoEdit.campoData.setText(UtilFormat.data(p.getDataCadastro()));
            } catch (Exception exception) {
            }
            PrincipalProduto.JanelaProdutoEdit();
        }
        
    }
    
    public Boolean atualizarAction() {
        Produto p = new Produto();
        try {
            p.setId(ID_SELECIONADO);
            System.out.println(ID_SELECIONADO);
            p.setNome(JanelaProdutoEdit.campoNome.getText());
            p.setValor(Double.valueOf(JanelaProdutoEdit.campoValor.getText()));
            p.setDataCadastro(UtilFormat.data(JanelaProdutoEdit.campoData.getText()));
        } catch (Exception exception) {
        }
        try {
            if (PRODUTO_DAO.alterar(p)) {
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
    
    public void deletarProdutoAction() {
        
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int resposta = JOptionPane.showConfirmDialog(null, "Você deseja Realmente excluir Esse contato?\r\nEsta Ação é irreversivel!", "ATENÇÃO!", dialogButton);
        
        if (resposta == JOptionPane.NO_OPTION) {
            return;
        } else {
            int linha = JanelaGerenciarProduto.tabelaProduto.getSelectedRow();
            if (linha >= 0) {
                Integer idProduto = (Integer) JanelaGerenciarProduto.tabelaProduto.getValueAt(linha, 0);
                boolean apagou = false;
                try {
                    apagou = PRODUTO_DAO.deletarPorId(idProduto);
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
                List<Produto> produtos = PRODUTO_DAO.buscarPorTermo("");
                listarAction();
            } catch (Exception exception) {
            }
            
        }
        
    }
    
    public Boolean seExisteNoBancoAction() {
        List<Produto> produtosRecebidos;
        try {
            produtosRecebidos = (List<Produto>) PRODUTO_DAO.listar();
            
            for (Produto produto : produtosRecebidos) {
                if (produto.getNome().toLowerCase().equals(JanelaProdutoEdit.campoNome.getText().toLowerCase())) {
                    return true;
                } else {
                    return false;
                }
            }
            
        } catch (Exception exception) {
        }
        return null;
    }
    
}
