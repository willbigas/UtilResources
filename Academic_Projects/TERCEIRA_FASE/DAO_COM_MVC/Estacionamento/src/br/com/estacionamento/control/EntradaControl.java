package br.com.estacionamento.control;

import br.com.estacionamento.dao.CarroDao;
import br.com.estacionamento.dao.CondutorDao;
import br.com.estacionamento.dao.EntradaDao;
import br.com.estacionamento.dao.TipoClienteDao;
import br.com.estacionamento.model.Entrada;
import br.com.estacionamento.util.Mensagem;
import br.com.estacionamento.util.Swing;
import br.com.estacionamento.util.UtilFormat;
import br.com.estacionamento.view.JanelaEntrada;
import java.util.Calendar;

/**
 *
 * @author William
 */
public class EntradaControl {

    CarroDao CARRO_DAO = new CarroDao();
    CondutorDao CONDUTOR_DAO = new CondutorDao();
    TipoClienteDao TIPO_CLIENTE_DAO = new TipoClienteDao();
    EntradaDao ENTRADA_DAO = new EntradaDao();

    CarroControl CARRO_CONTROL;
    CondutorControl CONDUTOR_CONTROL;
    TipoClienteControl TIPO_CLIENTE_CONTROL;

    public EntradaControl() {
        CARRO_CONTROL = new CarroControl();
        CONDUTOR_CONTROL = new CondutorControl();
        TIPO_CLIENTE_CONTROL = new TipoClienteControl();
    }

    public void inserindoEntradaAction() {
        Integer cInserido = CARRO_CONTROL.inserirAutomovel();
        Integer coInserido = CONDUTOR_CONTROL.inserirCondutor();
        Integer tcInserido = TIPO_CLIENTE_CONTROL.inserirTipoCliente();
        Entrada e = new Entrada();
        String hora = JanelaEntrada.tfHora.getText();
        System.out.println("Hora :" + hora);
        String campos[] = hora.split(":");
        try {
            e.setId(Integer.MAX_VALUE);
            e.setCarro(CARRO_DAO.lerPorId(cInserido));
            e.setCondutor(CONDUTOR_DAO.lerPorId(coInserido));
            e.setTipoCliente(TIPO_CLIENTE_DAO.lerPorId(tcInserido));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(UtilFormat.data(JanelaEntrada.tfData.getText())); //colocando o objeto Date no Calendar
            calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(campos[0])); //zerando as horas, minuots e segundos..
            calendar.set(Calendar.MINUTE, Integer.valueOf(campos[1]));
            calendar.set(Calendar.SECOND, 0);
            e.setDataEntrada(calendar.getTime());
            System.out.println("Data Gerada : +  " +e.getDataEntrada());
            if (ENTRADA_DAO.cadastrar(e) > 0) {
                Swing.msg(Mensagem.ENTRADA_SUCESSO);
            } else {
                Swing.msg(Mensagem.ENTRADA_ERRO);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
