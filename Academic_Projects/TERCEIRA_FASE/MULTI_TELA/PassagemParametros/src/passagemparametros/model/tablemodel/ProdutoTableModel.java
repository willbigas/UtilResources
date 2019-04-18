/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passagemparametros.model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import passagemparametros.model.entidade.Produto;

/**
 *
 * @author Clony
 */
public class ProdutoTableModel extends AbstractTableModel{

    private List<Produto> lista;

    public ProdutoTableModel() {
        lista = new ArrayList<>();
    }

    public ProdutoTableModel(List<Produto> lista) {
        this.lista = lista;
    }
    
  static class Constantes{
        private static final String[] colunas = {"Código", "Nome", "Valor"};
        private static final int CODIGO = 0;
        private static final int NOME = 1;
        private static final int VALOR = 2;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return Constantes.colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case Constantes.CODIGO:
                return lista.get(rowIndex).getId();
            case Constantes.NOME:
                return lista.get(rowIndex).getNome();
            case Constantes.VALOR:
                return lista.get(rowIndex).getValor();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return Constantes.colunas[column];
    }
    
    public Produto getRow(int row){
        return row>=0 ? lista.get(row) : null;
    }
    
    public void addRow(Produto p){
        lista.add(p);
        this.fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
    
    public void removeRow(int row){
        lista.remove(row);
        this.fireTableRowsDeleted(row, row);
    }
    
    public void updateRow(int row, Produto p){
        lista.set(row, p);
        this.fireTableRowsUpdated(row, row);
    }
        
}
