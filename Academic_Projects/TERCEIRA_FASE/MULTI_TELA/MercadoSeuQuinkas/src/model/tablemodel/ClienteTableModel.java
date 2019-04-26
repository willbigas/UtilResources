package model.tablemodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Cliente;
import uteis.Conversor;

/**
 *
 * @author William
 */
public class ClienteTableModel extends AbstractTableModel {
     private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int DATA_NASCIMENTO = 2;
    private static final int CEP = 3;
    private static final int NOME_CIDADE = 4;

    private List<Cliente> linhas;
    private String[] COLUNAS = {"Código", "Nome", "Nascimento", "Cep" , "Cidade"};

    public ClienteTableModel() {
        linhas = new ArrayList<>();
    }

    public ClienteTableModel(List<Cliente> listClientes) {
        linhas = new ArrayList<>(listClientes);
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
            case DATA_NASCIMENTO:
                return String.class;
            case CEP:
                return String.class;
            case NOME_CIDADE:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Cliente cliente = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                return cliente.getId();
            case NOME:
                return cliente.getNome();
            case DATA_NASCIMENTO:
                try {
                    return Conversor.data(cliente.getDataNascimento());
                } catch (Exception exception) {
                    exception.getMessage();
                }
            case CEP:
                return cliente.getCep();
            case NOME_CIDADE:
                return cliente.getCidade().getNome();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Cliente cliente = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                cliente.setId(Integer.valueOf((String) valor));
                break;
            case NOME:
                cliente.setNome((String) valor);
                break;
            case DATA_NASCIMENTO:
                cliente.setDataNascimento((Date)  valor);
                break;
            case CEP:
                cliente.setNome((String) valor);
                break;
            case NOME_CIDADE:
                cliente.getCidade().setNome((String) valor);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    public Cliente pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void adicionar(Cliente produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    public void adicionar(List<Cliente> clientes) {
        int indice = getRowCount();
        linhas.addAll(clientes);
        fireTableRowsInserted(indice, indice + clientes.size());
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

    public void atualizar(int indiceLinha, Cliente c) {
        linhas.set(indiceLinha, c);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }
    
    
}
