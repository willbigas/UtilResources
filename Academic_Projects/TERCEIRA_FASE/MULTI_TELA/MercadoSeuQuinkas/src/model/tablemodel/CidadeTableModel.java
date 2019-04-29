package model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Cidade;

/**
 *
 * @author William
 */
public class CidadeTableModel extends AbstractTableModel {
     private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int UF = 2;

    private List<Cidade> linhas;
    private String[] COLUNAS = {"Código", "Nome", "Uf"};

    public CidadeTableModel() {
        linhas = new ArrayList<>();
    }

    public CidadeTableModel(List<Cidade> listCidades) {
        linhas = new ArrayList<>(listCidades);
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return COLUNAS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUNAS[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case CODIGO:
                return Integer.class;
            case NOME:
                return String.class;
            case UF:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Cidade cidade = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                return cidade.getId();
            case NOME:
                return cidade.getNome();
            case UF:
                return cidade.getUf();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Cidade cidade = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                cidade.setId(Integer.valueOf((String) valor));
                break;
            case NOME:
                cidade.setNome((String) valor);
                break;
            case UF:
                cidade.setUf((String)  valor);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    public Cidade pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void adicionar(Cidade cidade) {
        linhas.add(cidade);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    public void adicionar(List<Cidade> listCidades) {
        int indice = getRowCount();
        linhas.addAll(listCidades);
        fireTableRowsInserted(indice, indice + listCidades.size());
        fireTableDataChanged();
    }

    public void remover(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha); // atualiza delete
    }

    public void remover(int linhaInicio, int linhaFim) {

        for (int i = linhaInicio; i <= linhaFim; i++) {
            linhas.remove(i);
            fireTableRowsDeleted(linhaInicio, linhaFim); // atualiza delete
        }

    }

    public void atualizar(int indiceLinha, Cidade cidade) {
        linhas.set(indiceLinha, cidade);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }
    
    
}
