package br.com.estacionamento.control;

import br.com.estacionamento.dao.EntradaDao;
import br.com.estacionamento.dao.UltimaEntradaDao;
import br.com.estacionamento.model.Entrada;
import br.com.estacionamento.util.Mensagem;
import br.com.estacionamento.util.Swing;
import br.com.estacionamento.util.UtilFormat;
import br.com.estacionamento.util.Validacao;
import br.com.estacionamento.view.JanelaSaida;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;

/**
 *
 * @author William
 */
public class SaidaControl {

    List<Entrada> listEntrada;
    EntradaDao ENTRADA_DAO = new EntradaDao();
    UltimaEntradaDao ULTIMA_ENTRADA_DAO = new UltimaEntradaDao();
    Integer valorTotal = null;
    public static Integer VALOR_TOTAL_ENTRADAS = 0;

    public void listandoEntradasAction() {
        listEntrada = ENTRADA_DAO.listarSomenteSemSaida();
        DefaultTableModel model
                = (DefaultTableModel) JanelaSaida.tabelaSaida.getModel();
        model.setNumRows(0);
        for (Entrada e : listEntrada) {
            model.addRow(new Object[]{
                e.getCarro().getPlaca(),
                UtilFormat.data(e.getDataEntrada()),
                UtilFormat.dataHour(e.getDataEntrada()),
                e.getCondutor().getNome()
            });
        }
    }

    private void listandoEntradasAction(List<Entrada> entradas) {
        DefaultTableModel model
                = (DefaultTableModel) JanelaSaida.tabelaSaida.getModel();
        model.setNumRows(0);
        for (Entrada e : entradas) {
            model.addRow(new Object[]{
                e.getCarro().getPlaca(),
                UtilFormat.data(e.getDataEntrada()),
                UtilFormat.dataHour(e.getDataEntrada()),
                e.getCondutor().getNome()
            });
        }
    }

    public void calculaPrecoAction() {
        Entrada e = getEntradaSelecionada();
        calculaPrecoEntrada(e);
    }

    private Entrada getEntradaSelecionada() {
        int i = JanelaSaida.tabelaSaida.getSelectedRow();
        if (i >= 0) {
            return listEntrada.get(i);
        }
        return null;
    }

    private void calculaPrecoEntrada(Entrada e) {

        if (Validacao.isNull(e)) {
            Swing.msg(Mensagem.PRODUTO_NAO_SELECIONADO);
            return;
        } else {
            String horaDoPainel = JanelaSaida.tfHoraSaida.getText();
            String campos[] = horaDoPainel.split(":");

            /**
             * Transformo As Datas do Banco e do Usuario em Date Time usando API
             * - JODA TIME para Calcular intevalo entre datas.
             */
            DateTime dataFinal = pegaDataFinalUsuario(campos);
            DateTime dataInicio = new DateTime(e.getDataEntrada());

            if (dataFinal.isBefore(dataInicio)) {
                Swing.msg(Mensagem.SAIDA_ANTERIOR_ENTRADA);
            } else {
                // Pegando Horas e Minutos entre As Datas;
                Hours h = Hours.hoursBetween(dataInicio, dataFinal);
                Minutes m = Minutes.minutesBetween(dataInicio, dataFinal);
                int minutos = m.getMinutes() % 60;
                Integer minutosInt = minutos;

                // Valido pelo Tipo de Cliente
                validandoPeloTipoCliente(minutosInt, e);

                Integer precoServidor = 2;
                Integer precoPublico = 4;

                somandoValorTotal(e, h, precoServidor, precoPublico);

            }

        }

    }

    public void somandoValorTotal(Entrada e, Hours h, Integer precoServidor, Integer precoPublico) {
        if (e.getTipoCliente().getIdTipo() == 1) {
            valorTotal += h.getHours() * precoServidor;
        }
        if (e.getTipoCliente().getIdTipo() == 2) {
            valorTotal += h.getHours() * precoPublico;
        }
        VALOR_TOTAL_ENTRADAS += valorTotal;
        JanelaSaida.lblValorTotal.setText(UtilFormat.decimalFormatR$(valorTotal));
    }

    public void validandoPeloTipoCliente(Integer minutosInt, Entrada e) {
        if (minutosInt >= 10 && e.getTipoCliente().getIdTipo() == 1) {
            valorTotal = 2;
        }
        if (minutosInt >= 10 && e.getTipoCliente().getIdTipo() == 2) {
            valorTotal = 4;
        }
        if (minutosInt <= 6) {
            valorTotal = 0;
        }
    }

    public DateTime pegaDataFinalUsuario(String[] campos) {
        DateTime dataFinal = new DateTime(UtilFormat.data(JanelaSaida.tfDataSaida.getText()));
        dataFinal = dataFinal.hourOfDay().setCopy(campos[0]);
        dataFinal = dataFinal.minuteOfHour().setCopy(campos[1]);
        dataFinal = dataFinal.secondOfMinute().setCopy(campos[2]);
        return dataFinal;
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
        if (JanelaSaida.tfCampoTroco.getText().isEmpty()) {
            Swing.msg(Mensagem.NENHUM_VALOR_TOTAL);
            return;
        } else {
            Double valorRecebido = Double.valueOf(JanelaSaida.tfCampoTroco.getText());
            Double valorDeTroco = valorRecebido - Double.valueOf(valorTotal);
            JanelaSaida.lblValorTroco.setText(UtilFormat.decimalFormatR$(valorDeTroco));
        }

    }

    public void finalizaSaidaAction() {
        Entrada e = getEntradaSelecionada();
        Integer valorRecebido = Integer.valueOf(JanelaSaida.tfCampoTroco.getText());

        if (valorRecebido < valorTotal) {
            Swing.msg(Mensagem.VALOR_TOTAL_MAIOR);
            return;
        } else {
            e.setDataSaida(new Date(System.currentTimeMillis()));
            e.setValorTotal(Double.valueOf(valorTotal));
            if (ENTRADA_DAO.alterar(e)) {
                Swing.msg(Mensagem.SAIDA_SUCESSO);
            } else {
                Swing.msg(Mensagem.SAIDA_ERRO);
            }
        }

    }

    public void excluirAction() {
        //Resgatar indice da linha selecionada
        Entrada e = getEntradaSelecionada();
        if (e == null) {
            Swing.msg(Mensagem.PRODUTO_NAO_SELECIONADO);
            return;
        }
        if (Swing.confirm(Mensagem.ACAO_IRREVERSIVEL + e.getCarro().getPlaca() + "?") == JOptionPane.YES_OPTION) {
            if (ENTRADA_DAO.deletarPorId(e.getId())) {
                Swing.msg(Mensagem.SUCESSO_EXCLUIR);
                listandoEntradasAction();
            } else {
                Swing.msg(Mensagem.ERRO_EXCLUIR);
            }
        }
    }

}
