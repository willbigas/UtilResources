package com.mercadojoana.control;

import com.mercadojoana.view.CadastroCliente;
import static com.mercadojoana.view.Principal.desktopPane;

/**
 *
 * @author william.mauro
 */
public class CadastroClienteControl {

    private CadastroCliente cadastrarCliente = null;
    
     public void abrirTelaCadastroClienteAction() {
        if (cadastrarCliente == null) { // se tiver nulo chama janelan normalmente
            cadastrarCliente = new CadastroCliente();
            desktopPane.add(cadastrarCliente);
            cadastrarCliente.setVisible(true);
        } else {//se ele estiver criado
            if (cadastrarCliente.isVisible()) {
                cadastrarCliente.pack();//volta frame
            } else {
                desktopPane.add(cadastrarCliente);//adicona frame ao JDesktopPane
                cadastrarCliente.setVisible(true);
            }
        }
    }

}
