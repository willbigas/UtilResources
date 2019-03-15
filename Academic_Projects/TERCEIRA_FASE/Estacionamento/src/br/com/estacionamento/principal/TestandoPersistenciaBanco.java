package br.com.estacionamento.principal;

import br.com.estacionamento.dao.CarroDao;
import br.com.estacionamento.model.Carro;
import java.util.List;

/**
 *
 * @author William
 */
public class TestandoPersistenciaBanco {
    
    public static void main(String[] args) {
        CarroDao carroDao = new CarroDao();
        
        Carro c = new Carro();
        
        c.setId(1);
        c.setCor("Vermelho");
        c.setMarca("Chevrolet");
        c.setModelo("HB2");
        c.setPlaca("AB-123456");
        
        List<Carro> carros = carroDao.pesquisar("");
        System.out.println(carros);
      
    }
}
