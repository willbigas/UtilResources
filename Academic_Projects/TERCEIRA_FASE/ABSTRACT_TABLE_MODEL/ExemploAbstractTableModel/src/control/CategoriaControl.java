package control;

import dao.CategoriaDao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import model.Categoria;
import model.tablemodel.CategoriaTableModel;
import util.OptionPane;
import util.Text;
import view.ViewCategoria;

/**
 *
 * @author William
 */
public class CategoriaControl {

    CategoriaTableModel CATEGORIA_PRODUTO;
    CategoriaDao CATEGORIA_DAO = new CategoriaDao();
    Categoria CATEGORIA;

    public CategoriaControl() {
        CATEGORIA_PRODUTO = new CategoriaTableModel();
        CATEGORIA_PRODUTO.clear(); // limpa tudo que estiver em memoria
        CATEGORIA_PRODUTO.addListOfObject(CATEGORIA_DAO.listar()); // puxa os dados do banco
        setModelOfTable(); // seta modelo da tabela
    }

    /**
     * Pega linha selecionada da Tabela da View e Transforma em Um Produto da
     * Lista do TableModel
     *
     * @return Produto com o Indice da List do TableModel
     */
    private Categoria pegaCategoriaSelecionada() {
        return CATEGORIA_PRODUTO.getObject(ViewCategoria.tblCategoria.getSelectedRow());
    }

    /**
     * Pega Linha selecionada da JTable da view
     *
     * @return int com o indice selecionado
     */
    public int pegaLinhaSelecionada() {
        int linha = ViewCategoria.tblCategoria.getSelectedRow();
        if (linha == -1) {
            OptionPane.msgInfo(Text.NOT_SELECTED_CATEGORY);
            return -1;
        } else {
            return linha;
        }
    }

    /**
     * Seta o modelo da Tabela para o proprio TableModel;
     */
    public void setModelOfTable() {
        ViewCategoria.tblCategoria.setModel(CATEGORIA_PRODUTO); // Tabela da View;
    }

    /**
     *
     */
    public void createCategoryAction() {
        CATEGORIA = new Categoria();
        CATEGORIA.setNome(ViewCategoria.tfNome.getText());
        if (ViewCategoria.checkAtivo.isSelected()) {
            CATEGORIA.setAtivo(Boolean.TRUE);
        } else {
            CATEGORIA.setAtivo(Boolean.FALSE);
        }
        int idRecebido = CATEGORIA_DAO.cadastrar(CATEGORIA);
        CATEGORIA.setId(idRecebido);
        CATEGORIA_PRODUTO.addObject(CATEGORIA); // adiciona linha;
        CATEGORIA = null;
        OptionPane.msgInfo(Text.SUCESS_CREATE);
        limparCamposAction();
    }

    public void deleteCategoryAction() {
        validaLinhaNaoSelecionada();
        CATEGORIA = pegaCategoriaSelecionada(); // pega produto da linha selecionada
        CATEGORIA_DAO.deletar(CATEGORIA);
        CATEGORIA_PRODUTO.removeObject(pegaLinhaSelecionada()); // remove linha do produto
        CATEGORIA = null;
        limparCamposAction();
    }

    private void validaLinhaNaoSelecionada() throws HeadlessException {
        if (pegaLinhaSelecionada() == -1) {
            JOptionPane.showMessageDialog(null, "Voce precisa selecionar uma linha!");
            return;
        }

    }

    private void updateCategoryAction() {
        validaLinhaNaoSelecionada();
//        ViewCategoria.tblCategoria.setValueAt(ViewCategoria.tfNome.getText(),
//                pegaLinhaSelecionada(), 1); // alterando coluna 1 // implementar id 
        ViewCategoria.tblCategoria.setValueAt(ViewCategoria.tfNome.getText(),
                pegaLinhaSelecionada(), 1); // alterando coluna 2
        ViewCategoria.tblCategoria.setValueAt(ViewCategoria.checkAtivo.isSelected(),
                pegaLinhaSelecionada(), 2); // alterando coluna 3
        if (CATEGORIA_DAO.alterar(CATEGORIA)) {
            CATEGORIA = null;
            OptionPane.msgInfo(Text.SUCESS_EDIT);
            limparCamposAction();
        } else {
            OptionPane.msgError(Text.ERROR_EDIT);
        }

    }

    public void limparCamposAction() {
        ViewCategoria.tfNome.setText(null);
        ViewCategoria.checkAtivo.setSelected(false);
    }

    public void carregaProdutoNoFormAction() {
        CATEGORIA = pegaCategoriaSelecionada();
        ViewCategoria.tfNome.setText(CATEGORIA.getNome());
        ViewCategoria.checkAtivo.setSelected(CATEGORIA.getAtivo());
    }

    public void saveProductAction() {
        if (CATEGORIA == null) {
            createCategoryAction();
        } else {
            updateCategoryAction();
        }
    }

    public void enableEdit() {
        int i = pegaLinhaSelecionada();
        if (i != -1) {
            ViewCategoria.btAlterar.setEnabled(true);
            ViewCategoria.btDeletar.setEnabled(true);
        } else {
            return;
        }

    }

}
