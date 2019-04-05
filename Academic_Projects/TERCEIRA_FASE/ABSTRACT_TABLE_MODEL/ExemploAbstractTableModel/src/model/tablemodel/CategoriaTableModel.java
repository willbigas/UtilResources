package model.tablemodel;

import interfaces.ActionsTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Categoria;

public class CategoriaTableModel extends AbstractTableModel implements ActionsTableModel<Categoria>{

    // Constantes representando o índice das colunas
    private static final int ID = 0;
    private static final int NOME = 1;
    private static final int ATIVO = 2;

    // Lista de Objetos<Produtos> a serem exibidos na tela.
    private List<Categoria> linhas;

    // Colunas da Tabela (View)
    private String[] colunas = {"ID", "NOME", "ATIVO"}; 

    // Cria um ProdutoTableModel sem nenhuma linha
    public CategoriaTableModel() {
        linhas = new ArrayList<>();
    }

    // Cria um ProdutoTableModel contendo a lista recebida por parâmetro 
    public CategoriaTableModel(List<Categoria> listCategorias) {
        linhas = listCategorias;
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
            case NOME:
                return String.class;
            case ATIVO:
                return String.class;
            default:
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
        Categoria produto = linhas.get(linha);
        switch (coluna) {
            case ID:
                return produto.getId();
            case NOME:
                return produto.getNome();
            case ATIVO:
                return produto.getAtivo();
            default:
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
        Categoria produto = linhas.get(linha);
        switch (coluna) {
            case ID:
                produto.setId(Integer.valueOf((String) valor));
                break;
            case NOME:
                produto.setNome((String) valor);
                break;
            case ATIVO:
                produto.setAtivo((Boolean) valor);
                break;
            default:
                // Não deve ocorrer, pois só existem 4 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    /**
     * Retorna o Produto referente a linha especificada
     *
     * @param indiceLinha
     * @return
     */
    public Categoria getObject(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    /**
     * Adiciona o Produto especificado ao modelo.
     *
     * @param produto
     */
    public void addObject(Categoria produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    /**
     * Remove o produto da linha especificada.
     *
     * @param indiceLinha
     */
    public void removeObject(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha); // atualiza delete
    }
    /**
     * Remove o produto da linha especificada.
     *
     * @param indiceLinha
     * @param p
     */
    public void updateObject(int indiceLinha, Categoria p) {
        linhas.set(indiceLinha , p);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    /**
     * Adiciona uma lista de Produtos no final da lista.
     *
     * @param produtos
     */
    public void addListOfObject(List<Categoria> produtos) {
        int indice = getRowCount();
        linhas.addAll(produtos);
        fireTableRowsInserted(indice, indice + produtos.size());
        fireTableDataChanged();
    }

    /**
     * Limpa todos os registros da tabela
     */
    public void clear() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
