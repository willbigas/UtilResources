package br.com.estacionamento.principal;

import br.com.estacionamento.dao.CarroDao;
import br.com.estacionamento.dao.CondutorDao;
import br.com.estacionamento.model.Condutor;
import java.util.List;

/**
 *
 * @author William
 */
public class TestandoPersistenciaBanco {
    
    public static void main(String[] args) {
        CarroDao carroDao = new CarroDao();
        CondutorDao condutorDao = new CondutorDao();
//        
//        Carro c = new Carro();
//        
//        c.setId(1);
//        c.setCor("Vermelho");
//        c.setMarca("Chevrolet");
//        c.setModelo("HB2");
//        c.setPlaca("AB-123456");
//        
//        List<Carro> carros = carroDao.pesquisar("");
//        System.out.println(carros);
//        
        
        Condutor co = new Condutor();
        co.setId(1);
        co.setNome("William - Alterado");
        List<Condutor> condutores  = condutorDao.listar();
        System.out.println(condutores);
      
    }
}
