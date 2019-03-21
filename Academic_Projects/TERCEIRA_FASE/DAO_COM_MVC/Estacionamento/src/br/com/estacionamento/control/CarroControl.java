package br.com.estacionamento.control;

import br.com.estacionamento.dao.CarroDao;
import br.com.estacionamento.model.Carro;
import br.com.estacionamento.view.JanelaEntrada;

/**
 *
 * @author William
 */
public class CarroControl {

    CarroDao CARRO_DAO = new CarroDao();

    public int inserirAutomovel() {
        Carro c = new Carro();
        c.setId(Integer.MAX_VALUE);
        c.setPlaca(JanelaEntrada.tfPlaca.getText());
        c.setCor(JanelaEntrada.tfCor.getText());
        c.setMarca(JanelaEntrada.tfMarca.getText());
        c.setModelo(JanelaEntrada.tfModelo.getText());
        return CARRO_DAO.cadastrar(c);
    }
}