package br.com.estacionamento.control;

import br.com.estacionamento.dao.EntradaDao;
import br.com.estacionamento.model.Entrada;
import br.com.estacionamento.util.UtilFormat;
import br.com.estacionamento.view.JanelaSaida;
import java.util.ArrayList;
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
    Integer valorTotal = null;

    public void listandoEntradasAction() {
        listEntrada = ENTRADA_DAO.listarSomenteSemSaida();
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

    private void listandoEntradasAction(List<Entrada> entradas) {
        DefaultTableModel model
                = (DefaultTableModel) JanelaSaida.tabelaSaida.getModel();
        model.setNumRows(0);
        for (Entrada e : entradas) {
            model.addRow(new Object[]{
                e.getCarro().getPlaca(),
                e.getDataEntrada(),
                e.getCondutor().getNome(),});
        }
    }

    public void calculaPrecoAction() {
        Entrada e = getEntradaSelecionada();
        calculandoPrecoDoEstacionamento(e);
    }

    private Entrada getEntradaSelecionada() {
        int i = JanelaSaida.tabelaSaida.getSelectedRow();
        if (i >= 0) {
            return listEntrada.get(i);
        }
        return null;
    }

    private void calculandoPrecoDoEstacionamento(Entrada e) {
        DateTime dataFinal = new DateTime();
        DateTime dataInicio = new DateTime(e.getDataEntrada().getTime());
        Hours h = Hours.hoursBetween(dataInicio, dataFinal);
        System.out.println("Horas: + " + h.getHours());
        Integer precoServidor = 2;
        Integer precoPublico = 4;
        if (e.getTipoCliente().getIdTipo() == 1) {
            valorTotal = h.getHours() * precoServidor;
        }
        if (e.getTipoCliente().getIdTipo() == 2) {
            valorTotal = h.getHours() * precoPublico;
        }
        System.out.println("Result :" + UtilFormat.decimalFormatR$(valorTotal));
        JanelaSaida.lblValorTotal.setText(UtilFormat.decimalFormatR$(valorTotal));
    }

    public void pesquisarAction() {
        List<Entrada> entradas = null;
        try {
            entradas = pesquisar(JanelaSaida.tfPesquisarPlaca.getText());
        } catch (Exception exception) {
        }
        listandoEntradasAction(entradas);
    }

    private List<Entrada> pesquisar(String termo) {
        List<Entrada> retorno = new ArrayList<>();

        try {
            List<?> objs = ENTRADA_DAO.listar();
            List<Entrada> ENTRADAS = (List<Entrada>) (Object) objs;
            for (Entrada e : ENTRADAS) {
                if (e.getCarro().getPlaca().toLowerCase().contains(termo.toLowerCase())) {
                    retorno.add(e);
                }
            }

        } catch (Exception exception) {
        }
        return retorno;
    }

    public void calcularTrocoAction() {
        Double valorRecebido = Double.valueOf(JanelaSaida.tfCampoTroco.getText());
        Double valorDeTroco = valorRecebido - valorTotal;
        JanelaSaida.lblValorTroco.setText(UtilFormat.decimalFormatR$(valorDeTroco));
    }

    public void finalizaSaidaAction() {
        Entrada e = getEntradaSelecionada();
        e.setDataSaida(new Date(System.currentTimeMillis()));
        e.setValorTotal(Double.valueOf(valorTotal));
        ENTRADA_DAO.alterar(e);
    }

}
