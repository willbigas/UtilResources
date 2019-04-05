package control;

import dao.CategoriaDao;
import java.awt.HeadlessException;
import java.util.List;
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

    CategoriaTableModel CATEGORIA_TABLE;
    CategoriaDao CATEGORIA_DAO = new CategoriaDao();
    Categoria CATEGORIA;

    public final String CINCO_PAGINAS = "5";
    public final String DEZ_PAGINAS = "10";
    public final int ZERO_PAGINAS_INT = 0;
    public final int CINCO_PAGINAS_INT = 5;
    public final int DEZ_PAGINAS_INT = 10;
    public int CONTROLE_DE_PAGINA = 0;
    public int PAGINA_ATUAL = 1;

    public int TOTAL_PAGINAS = 0;

    public int CONTADOR_DE_PAGINAS = 0;

    public CategoriaControl() {
        CATEGORIA_TABLE = new CategoriaTableModel();
        CATEGORIA_TABLE.clear(); // limpa tudo que estiver em memoria
        CATEGORIA_TABLE.addListOfObject(CATEGORIA_DAO.listar()); // puxa os dados do banco
        setModelOfTable(); // seta modelo da tabela
    }

    /**
     * Pega linha selecionada da Tabela da View e Transforma em Um Produto da
     * Lista do TableModel
     *
     * @return Produto com o Indice da List do TableModel
     */
    private Categoria pegaCategoriaSelecionada() {
        return CATEGORIA_TABLE.getObject(ViewCategoria.tblCategoria.getSelectedRow());
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
        ViewCategoria.tblCategoria.setModel(CATEGORIA_TABLE); // Tabela da View;
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
        CATEGORIA_TABLE.addObject(CATEGORIA); // adiciona linha;
        CATEGORIA = null;
        OptionPane.msgInfo(Text.SUCESS_CREATE);
        atualizaTotalDeRegistros();
        limparCamposAction();
        paginaInicial();
    }

    public void deleteCategoryAction() {
        validaLinhaNaoSelecionada();
        if (OptionPane.msgConfirm(Text.QUESTION_DELETE) == JOptionPane.YES_OPTION) {
            CATEGORIA = pegaCategoriaSelecionada(); // pega produto da linha selecionada
            CATEGORIA_DAO.deletar(CATEGORIA);
            CATEGORIA_TABLE.removeObject(pegaLinhaSelecionada()); // remove linha do produto
            CATEGORIA = null;
            atualizaTotalDeRegistros();
            limparCamposAction();
            paginaInicial();
        } else {
            return;
        }

    }

    private void validaLinhaNaoSelecionada() throws HeadlessException {
        if (pegaLinhaSelecionada() == -1) {
            OptionPane.msgInfo(Text.NOT_SELECTED_CATEGORY);
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

    public void searchCategoryAction() {
        String valor = (String) ViewCategoria.tfPesquisarCategoria.getText();
        if (valor.equals("")) {
            paginaInicial();
            return;
        } else {
            List<Categoria> categorias = CATEGORIA_DAO.pesquisarPorTermo(valor);
            System.out.println(categorias);
            CATEGORIA_TABLE.clear();
            CATEGORIA_TABLE.addListOfObject(categorias);
            ViewCategoria.btProximo.setEnabled(false);
            ViewCategoria.btAnterior.setEnabled(false);
            ViewCategoria.cbItensPorPagina.setEnabled(false);
            ViewCategoria.lblTotalListagem.setText(String.valueOf(CATEGORIA_TABLE.getRowCount()));
            
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

    public void disableEdit() {
        ViewCategoria.btAlterar.setEnabled(false);
        ViewCategoria.btDeletar.setEnabled(false);
    }

    public void enableSave() {
        ViewCategoria.btSalvar.setEnabled(true);
    }

    public void disableSave() {
        ViewCategoria.btSalvar.setEnabled(false);
    }

    public void enableTfNome() {
        ViewCategoria.tfNome.setEnabled(true);
        ViewCategoria.checkAtivo.setEnabled(true);
    }

    public void disableTfNome() {
        ViewCategoria.tfNome.setEnabled(false);
        ViewCategoria.checkAtivo.setEnabled(false);
    }

    public void adicionaComboQtdPaginas() {
        ViewCategoria.cbItensPorPagina.removeAllItems();
        ViewCategoria.cbItensPorPagina.addItem(CINCO_PAGINAS);
        ViewCategoria.cbItensPorPagina.addItem(DEZ_PAGINAS);
        ViewCategoria.cbItensPorPagina.setSelectedItem(DEZ_PAGINAS);
    }

    public void paginaInicial() {
        PAGINA_ATUAL = 1;
        atualizaTotalPaginas();
        CATEGORIA_TABLE.clear();
        ViewCategoria.btProximo.setEnabled(true);
        ViewCategoria.btUltimo.setEnabled(true);
        ViewCategoria.btAnterior.setEnabled(false);
        ViewCategoria.cbItensPorPagina.setEnabled(true);
        String valor = null;
        valor = (String) ViewCategoria.cbItensPorPagina.getSelectedItem();
        System.out.println(valor);
        CONTADOR_DE_PAGINAS = 0;
        if (valor.equals(DEZ_PAGINAS)) {
            CONTROLE_DE_PAGINA = DEZ_PAGINAS_INT;
            ViewCategoria.lblTotalListagem.setText(valor);
            List<Categoria> categorias = CATEGORIA_DAO.listarComLimit(ZERO_PAGINAS_INT, DEZ_PAGINAS_INT);
            CATEGORIA_TABLE.addListOfObject(categorias);
            primeiroRegistro();
        }
        if (valor.equals(CINCO_PAGINAS)) {
            CONTROLE_DE_PAGINA = CINCO_PAGINAS_INT;
            ViewCategoria.lblTotalListagem.setText(valor);
            List<Categoria> categorias = CATEGORIA_DAO.listarComLimit(ZERO_PAGINAS_INT, CINCO_PAGINAS_INT);
            CATEGORIA_TABLE.addListOfObject(categorias);
            primeiroRegistro();
        }

    }

    public void proximaPagina() {
        PAGINA_ATUAL += 1;
        atualizaTotalPaginas();
        CATEGORIA_TABLE.clear();
        ViewCategoria.btAnterior.setEnabled(true);
        ViewCategoria.btPrimeiro.setEnabled(true);
        if (CONTROLE_DE_PAGINA == DEZ_PAGINAS_INT) {
            int linhaInicial = CONTADOR_DE_PAGINAS + DEZ_PAGINAS_INT;
            int quantidadeDeLinhas = DEZ_PAGINAS_INT;
            List<Categoria> categorias = CATEGORIA_DAO.listarComLimit(linhaInicial, quantidadeDeLinhas);
            CATEGORIA_TABLE.addListOfObject(categorias);
            CONTADOR_DE_PAGINAS += CONTROLE_DE_PAGINA;
            ultimoRegistro();

        }

        if (CONTROLE_DE_PAGINA == CINCO_PAGINAS_INT) {
            int linhaInicial = CONTADOR_DE_PAGINAS + CINCO_PAGINAS_INT;
            int quantidadeDeLinhas = CINCO_PAGINAS_INT;
            List<Categoria> categorias = CATEGORIA_DAO.listarComLimit(linhaInicial, quantidadeDeLinhas);
            CATEGORIA_TABLE.addListOfObject(categorias);
            CONTADOR_DE_PAGINAS += CONTROLE_DE_PAGINA;
            ultimoRegistro();
        }

    }

    public void AnteriorPagina() {
        PAGINA_ATUAL = PAGINA_ATUAL - 1;
        atualizaTotalPaginas();
        primeiroRegistro();
        CATEGORIA_TABLE.clear();
        ViewCategoria.btProximo.setEnabled(true);
        if (CONTROLE_DE_PAGINA == DEZ_PAGINAS_INT) {
            int linhaInicial = CONTADOR_DE_PAGINAS - CONTROLE_DE_PAGINA;
            int quantidadeDeLinhas = DEZ_PAGINAS_INT;
            List<Categoria> categorias = CATEGORIA_DAO.listarComLimit(linhaInicial, quantidadeDeLinhas);
            CATEGORIA_TABLE.addListOfObject(categorias);
            CONTADOR_DE_PAGINAS = CONTADOR_DE_PAGINAS - CONTROLE_DE_PAGINA;
        }
        if (CONTROLE_DE_PAGINA == CINCO_PAGINAS_INT) {

            int linhaInicial = CONTADOR_DE_PAGINAS - CONTROLE_DE_PAGINA;
            int quantidadeDeLinhas = CINCO_PAGINAS_INT;
            CATEGORIA_TABLE.clear();
            List<Categoria> categorias = CATEGORIA_DAO.listarComLimit(linhaInicial, quantidadeDeLinhas);
            CATEGORIA_TABLE.addListOfObject(categorias);
            CONTADOR_DE_PAGINAS = CONTADOR_DE_PAGINAS - CONTROLE_DE_PAGINA;
        }

    }

    public void paginaFinal() {
        PAGINA_ATUAL = TOTAL_PAGINAS;
        atualizaTotalPaginas();
        ultimoRegistro();
        PAGINA_ATUAL = TOTAL_PAGINAS;
        CATEGORIA_TABLE.clear();
        List<Categoria> categorias = CATEGORIA_DAO.listar();
        if (CONTROLE_DE_PAGINA == DEZ_PAGINAS_INT) {
            int linhaInicial = categorias.size() - DEZ_PAGINAS_INT;
            int quantidadeDeLinhas = DEZ_PAGINAS_INT;
            List<Categoria> categoriasFiltrada = CATEGORIA_DAO.listarComLimit(linhaInicial, quantidadeDeLinhas);
            CATEGORIA_TABLE.addListOfObject(categoriasFiltrada);
        }
        if (CONTROLE_DE_PAGINA == CINCO_PAGINAS_INT) {
            int linhaInicial = categorias.size() - CINCO_PAGINAS_INT;
            int quantidadeDeLinhas = CINCO_PAGINAS_INT;
            List<Categoria> categoriasFiltradas = CATEGORIA_DAO.listarComLimit(linhaInicial, quantidadeDeLinhas);
            CATEGORIA_TABLE.addListOfObject(categoriasFiltradas);
        }
        ViewCategoria.btPrimeiro.setEnabled(true);
        ViewCategoria.btProximo.setEnabled(false);

    }

    public void atualizaTotalDeRegistros() {
        List<Categoria> categorias = CATEGORIA_DAO.listar();
        ViewCategoria.lblTotalRegistros.setText(String.valueOf(categorias.size()));
    }

    public void atualizaTotalPaginas() {
        List<Categoria> categorias = CATEGORIA_DAO.listar();
        if (ViewCategoria.cbItensPorPagina.getSelectedItem() == DEZ_PAGINAS) {
            TOTAL_PAGINAS = 1;
            TOTAL_PAGINAS += categorias.size() / DEZ_PAGINAS_INT;
        }
        if (ViewCategoria.cbItensPorPagina.getSelectedItem() == CINCO_PAGINAS) {
            TOTAL_PAGINAS = 1;
            TOTAL_PAGINAS += categorias.size() / CINCO_PAGINAS_INT;
        }
        ViewCategoria.lblTotalPaginas.setText(String.valueOf(TOTAL_PAGINAS));
        ViewCategoria.lblPaginaAtual.setText(String.valueOf(PAGINA_ATUAL));
    }

    public void ultimoRegistro() {
        Integer pgAtual = Integer.valueOf(ViewCategoria.lblPaginaAtual.getText());
        Integer pgTotal = Integer.valueOf(ViewCategoria.lblTotalPaginas.getText());
        if (pgAtual > 1 && pgAtual == pgTotal) {
            ViewCategoria.btProximo.setEnabled(false);
            ViewCategoria.btUltimo.setEnabled(false);
            ViewCategoria.lblTotalListagem.setText(String.valueOf(CATEGORIA_TABLE.getRowCount()));
        }
    }

    public void primeiroRegistro() {
        Integer pgAtual = Integer.valueOf(ViewCategoria.lblPaginaAtual.getText());
        Integer pgTotal = Integer.valueOf(ViewCategoria.lblTotalPaginas.getText());
        if (pgAtual == 1) {
            ViewCategoria.btAnterior.setEnabled(false);
            ViewCategoria.btPrimeiro.setEnabled(false);
        }
    }

}
