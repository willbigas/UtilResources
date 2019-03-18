package br.com.estacionamento.control;

import br.com.estacionamento.dao.EntradaDao;
import br.com.estacionamento.model.Entrada;
import br.com.estacionamento.view.JanelaSaida;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.joda.time.DateTime;
import org.joda.time.Hours;

/**
 *
 * @author William
 */
public class SaidaControl {

    List<Entrada> listEntrada;
    EntradaDao ENTRADA_DAO = new EntradaDao();

    public void listandoEntradasAction() {
        listEntrada = ENTRADA_DAO.listar();
        DefaultTableModel model
                = (DefaultTableModel) JanelaSaida.tabelaSaida.getModel();
        model.setNumRows(0);
        for (Entrada e : listEntrada) {
            model.addRow(new Object[]{
                e.getCarro().getPlaca(),
                e.getDataEntrada(),
                e.getCondutor().getNome(),});
        }
    }

//    public void listarAction(List<Produto> produtos) {
//        DefaultTableModel model
//                = (DefaultTableModel) Gerenciar.tblProdutos.getModel();
//        model.setNumRows(0);
//        for (Produto p : produtos) {
//            model.addRow(new Object[]{
//                p.getId(),
//                p.getNome(),
//                p.getValor()
//            });
//        }
//    }
    public void editarEntrada() {
        Entrada e = getEntradaSelecionada();
        e.setDataSaida(new Date(System.currentTimeMillis()));
        ENTRADA_DAO.alterar(e);
    }

    private Entrada getEntradaSelecionada() {
        int i = JanelaSaida.tabelaSaida.getSelectedRow();
        if (i >= 0) {
            return listEntrada.get(i);
        }
        return null;
    }

    public void calculandoPrecoDoEstacionamento(Entrada e) {
        DateTime dataFinal = new DateTime();
        DateTime dataInicio = new DateTime(e.getDataEntrada().getTime());
        Hours h = Hours.hoursBetween(dataInicio , dataFinal);
        System.out.println("Horas: + " + h.getHours());
    }
}
