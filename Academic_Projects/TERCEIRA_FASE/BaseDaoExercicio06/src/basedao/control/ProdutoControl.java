package basedao.control;

import javax.swing.table.DefaultTableModel;
import basedao.dao.ProdutoDao;
import basedao.model.Produto;
import basedao.view.produto.JanelaGerenciarProduto;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class ProdutoControl {

    public static ProdutoDao PRODUTO_DAO = new ProdutoDao();

    public ProdutoControl() {

    }

    public void inserirProdutoAction() {
        Produto p = new Produto();
        p.setId(1);
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
    
    public void deletarProdutoAction(){
        
        
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
    

}
