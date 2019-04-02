package model;

import dao.ProdutoDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProdutoTableModel extends AbstractTableModel {

    // Constantes representando o índice das colunas
    private static final int ID = 0;
    private static final int DESCRICAO = 1;
    private static final int QTD = 2;
    private static final int VALOR = 3;

    // Lista de Produtos a serem exibidos na tela.
    private List<Produto> linhas;
    ProdutoDao produtoDao = new ProdutoDao();

    // array com o nome das colunas.
    private String[] colunas = {"ID", "DESCRIÇÃO", "QTD", "VALOR"}; // COLUNAS DA TABELA

    // Cria um ProdutoTableModel sem nenhuma linha
    public ProdutoTableModel() {
        linhas = new ArrayList<>();
    }

    // Cria um ProdutoTableModel contendo a lista recebida por parâmetro 
    public ProdutoTableModel(List<Produto> listaDeProdutos) {
        linhas = listaDeProdutos;
    }

    /**
     * Retorna quantidade de Linhas.
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return linhas.size();
    }

    /**
     * Retorna a quantidade de colunas.
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    /**
     * Retorna o Nome das colunas.
     *
     * @param columnIndex
     * @return
     */
    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Integer.class;
            case DESCRICAO:
                return String.class;
            case QTD:
                return Integer.class;
            case VALOR:
                return Double.class;
            default:
                // não deve ocorrer , pois só existem 4 colunas;
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    /**
     * Pega o Valor de uma Coluna/Linha da Tabela
     *
     * @param linha
     * @param coluna
     * @return
     */
    @Override
    public Object getValueAt(int linha, int coluna) {
        Produto produto = linhas.get(linha);
        switch (coluna) {
            case ID:
                return produto.getId();
            case DESCRICAO:
                return produto.getDescricao();
            case QTD:
                return produto.getQtd();
            case VALOR:
                return produto.getValor();
            default:
                // Não deve ocorrer, pois só existem 4 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    /**
     * Altera Valor de uma Coluna / Linha da tabela
     *
     * @param valor
     * @param linha
     * @param coluna
     */
    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Produto produto = linhas.get(linha);
        switch (coluna) {
            case 0:
                produto.setId(Integer.valueOf((String) valor));
                break;
            case 1:
                produto.setDescricao((String) valor);
                break;
            case 2:
                produto.setQtd(Integer.valueOf((String) valor));
                break;
            case 3:
                produto.setValor(Double.valueOf((String) valor));
                break;
            default:
                // Não deve ocorrer, pois só existem 3 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celular

    }

    /**
     * Retorna o Produto referente a linha especificada
     *
     * @param indiceLinha
     * @return
     */
    public Produto getProduto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    /**
     * Adiciona o sócio especificado ao modelo.
     *
     * @param produto
     */
    public void addProduto(Produto produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    /**
     * Remove o sócio da linha especificada.
     *
     * @param indiceLinha
     */
    public void removeProduto(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    /**
     * Adiciona uma lista de sócios no final da lista.
     *
     * @param produtos
     */
    public void addListaDeProdutos(List<Produto> produtos) {
        int indice = getRowCount();
        linhas.addAll(produtos);
        fireTableRowsInserted(indice, indice + produtos.size());
    }

    /* Remove todos os registros. */
    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

}
