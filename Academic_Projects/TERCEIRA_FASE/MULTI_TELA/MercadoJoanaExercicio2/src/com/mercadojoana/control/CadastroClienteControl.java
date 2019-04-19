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
//         Janela.naoDuplicaJanela(cadastrarCliente);
        if (cadastrarCliente == null) { // se tiver nulo chama janela normalmente
            cadastrarCliente = new CadastroCliente();
            desktopPane.add(cadastrarCliente);
            cadastrarCliente.setVisible(true);
        } else {//se ele estiver criado
            if (cadastrarCliente.isVisible()) {
                cadastrarCliente.pack();//Redimensiona ao Quadro Original
            } else {
                desktopPane.add(cadastrarCliente);//adiciona frame ao JDesktopPane
                cadastrarCliente.setVisible(true);
            }
        }
    }

}
