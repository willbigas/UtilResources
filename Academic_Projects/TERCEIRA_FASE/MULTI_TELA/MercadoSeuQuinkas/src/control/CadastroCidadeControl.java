package control;

import dao.CidadeDao;
import java.util.ArrayList;
import java.util.List;
import model.Cidade;
import view.CadastroCliente;

/**
 *
 * @author William
 */
public class CadastroCidadeControl {
    
    CidadeDao cidadeDao;
    List<Cidade> cidades;
    public static Cidade cidadeSelecionada;

    public CadastroCidadeControl() {
        cidadeDao = new CidadeDao();
        cidades = new ArrayList<>();
    }
    
    public void abrirComboBox() {
        CadastroCliente.cbCidade.removeAllItems();
       cidades = cidadeDao.listar();
        for (Cidade cidade : cidades) {
             CadastroCliente.cbCidade.addItem(cidade.toString());
        }
       
    }
    
    
    
    public void pegarValorComboBox(){
      int indexSelecionada =  CadastroCliente.cbCidade.getSelectedIndex();
        for (int i = 0; i < cidades.size(); i++) {
            if (i == indexSelecionada) {
                cidadeSelecionada = cidades.get(i);
            }
        }
        

    }
    
}
