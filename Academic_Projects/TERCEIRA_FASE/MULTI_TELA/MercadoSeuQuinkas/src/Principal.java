
import dao.CidadeDao;
import model.Cidade;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */
public class Principal {
    public static void main(String[] args) {
        CidadeDao cidadeDao = new CidadeDao();
        Cidade c = new Cidade();
        c.setId(1);
        c.setNome("Palhoça");
        c.setUf("SC");
        c.setAtivo(1);
        cidadeDao.cadastrar(c);
        
    }
}
