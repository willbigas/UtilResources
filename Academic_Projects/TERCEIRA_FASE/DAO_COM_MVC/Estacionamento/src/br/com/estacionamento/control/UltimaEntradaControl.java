package br.com.estacionamento.control;

import br.com.estacionamento.dao.EntradaDao;
import br.com.estacionamento.dao.UltimaEntradaDao;
import br.com.estacionamento.model.Entrada;
import br.com.estacionamento.view.JanelaEntrada;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class UltimaEntradaControl {
    
    EntradaDao ENTRADA_DAO;
    UltimaEntradaDao ULTIMA_ENTRADA_DAO;
    
    public UltimaEntradaControl() {
        ENTRADA_DAO = new EntradaDao();
        ULTIMA_ENTRADA_DAO = new UltimaEntradaDao();
    }
    
    public void pesquisarAction() {
        Entrada entrada = null;
        try {
            entrada = pesquisar(JanelaEntrada.tfPlaca.getText());
            JanelaEntrada.lblDataUltimaEntrada.setText(String.valueOf(entrada.getDataEntrada()));
            JanelaEntrada.lblHoraUltimaEntrada.setText(String.valueOf(entrada.getDataEntrada().getTime()));
            JanelaEntrada.tfMarca.setText(entrada.getCarro().getMarca());
            JanelaEntrada.tfCondutor.setText(entrada.getCondutor().getNome());
            JanelaEntrada.tfModelo.setText(entrada.getCarro().getModelo());
            JanelaEntrada.tfCor.setText(entrada.getCarro().getCor());
            
        } catch (Exception exception) {
        }
        
    }
    
    private Entrada pesquisar(String termo) {
        
        try {
            List<Entrada> ENTRADAS = ULTIMA_ENTRADA_DAO.pesquisar(termo);
            for (Entrada e : ENTRADAS) {
                if (e.getCarro().getPlaca().toLowerCase().equals(termo.toLowerCase())) {
                    Entrada retorno = e;
                    return retorno;
                }
            }
            return null;
            
        } catch (Exception exception) {
        }
        return null;
    }
}
