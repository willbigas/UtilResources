package control;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.CadastroCliente;
import view.ListarCliente;
import view.Principal;

/**
 *
 * @author renato.dasilva
 */
public class PrincipalControl {
    private CadastroCliente telaCadastroCliente = null;
    private ListarCliente telaListarCliente = null;
    
    public void chamarTelaCadastrarClienteAction(){
        if(telaCadastroCliente == null){
            telaCadastroCliente = new CadastroCliente();
            Principal.dpPrincipal.add(telaCadastroCliente);
            telaCadastroCliente.show();
        } else if(telaCadastroCliente.isIcon()){
            try {
                telaCadastroCliente.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(PrincipalControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(!telaCadastroCliente.isShowing()) {
            Principal.dpPrincipal.add(telaCadastroCliente);
            telaCadastroCliente.show();
        }
    }
    
    
     public void chamarTelaListarClienteAction(){
        if(telaListarCliente == null){
            telaListarCliente = new ListarCliente();
            Principal.dpPrincipal.add(telaListarCliente);
            telaListarCliente.show();
        } else if(telaListarCliente.isIcon()){
            try {
                telaListarCliente.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(PrincipalControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(!telaListarCliente.isShowing()) {
            Principal.dpPrincipal.add(telaListarCliente);
            telaListarCliente.show();
        }
    }
}