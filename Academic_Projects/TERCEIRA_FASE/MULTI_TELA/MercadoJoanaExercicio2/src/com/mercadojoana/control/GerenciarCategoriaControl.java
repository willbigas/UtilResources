package com.mercadojoana.control;

import com.mercadojoana.view.GerenciarCategoria;
import static com.mercadojoana.view.Principal.desktopPane;

/**
 *
 * @author william.mauro
 */
public class GerenciarCategoriaControl {

    private GerenciarCategoria gerenciarCategoria = null;
    
    public void abrirTelaGerenciarCategoriaAction(){
          if (gerenciarCategoria == null) {
            gerenciarCategoria = new GerenciarCategoria();
            desktopPane.add(gerenciarCategoria);
            gerenciarCategoria.setVisible(true);
        } else {
            if (gerenciarCategoria.isVisible()) {
                gerenciarCategoria.pack();
            } else {
                desktopPane.add(gerenciarCategoria);
                gerenciarCategoria.setVisible(true);
            }
        }
    }

}
