package control.validate;

import uteis.Mensagem;
import uteis.Texto;
import view.CadastroCliente;

/**
 *
 * @author Will
 */
public class CadastroClienteValidation {
    
   private CadastroCliente frameCadastroCliente;

    public CadastroClienteValidation(CadastroCliente cadastroCliente) {
        frameCadastroCliente = cadastroCliente;
    }
    
    
    public boolean validaCamposCadastro() {
        if (frameCadastroCliente.getTfNome().getText().isEmpty() || frameCadastroCliente.getTfCep().getText().isEmpty()
                || frameCadastroCliente.getTfDataNascimento().getText().isEmpty()) {
            Mensagem.msg(Texto.CAMPO_VAZIO);
            return true;
        }
        return false;
    }
    
}
