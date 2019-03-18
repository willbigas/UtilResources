package br.com.estacionamento.control;

import br.com.estacionamento.dao.CarroDao;
import br.com.estacionamento.dao.CondutorDao;
import br.com.estacionamento.dao.EntradaDao;
import br.com.estacionamento.dao.TipoClienteDao;
import br.com.estacionamento.model.Entrada;
import br.com.estacionamento.util.UtilFormat;
import br.com.estacionamento.view.JanelaEntrada;

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
        try {
            e.setId(Integer.MAX_VALUE);
            e.setCarro(CARRO_DAO.lerPorId(cInserido));
            e.setCondutor(CONDUTOR_DAO.lerPorId(coInserido));
            e.setTipoCliente(TIPO_CLIENTE_DAO.lerPorId(tcInserido));
            e.setDataEntrada(UtilFormat.data(JanelaEntrada.tfData.getText()));
            ENTRADA_DAO.cadastrar(e);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
