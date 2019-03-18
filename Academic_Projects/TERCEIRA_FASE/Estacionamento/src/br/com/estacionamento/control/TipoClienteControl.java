package br.com.estacionamento.control;

import br.com.estacionamento.dao.TipoClienteDao;
import br.com.estacionamento.model.TipoCliente;
import br.com.estacionamento.view.JanelaEntrada;

/**
 *
 * @author William
 */
public class TipoClienteControl {
    
    TipoClienteDao TIPO_CLIENTE_DAO = new TipoClienteDao();
    
   public int inserirTipoCliente() {
        TipoCliente tc = new TipoCliente(); // pegar da combo
       int result = JanelaEntrada.cbTipoCliente.getSelectedIndex();
       // Pegou o primeiro Item da combo e Armazenou no Banco como TipoServidor
       if (result == 0) {
           tc.setId(Integer.MAX_VALUE);
           tc.setIdTipo(1);
       }
        // Pegou o segundo Item da combo e Armazenou no Banco como TipoCliente
       if (result == 1) {
           tc.setId(Integer.MAX_VALUE);
           tc.setIdTipo(2);
       }
      int id = TIPO_CLIENTE_DAO.cadastrar(tc);
      return id;
    }
}
