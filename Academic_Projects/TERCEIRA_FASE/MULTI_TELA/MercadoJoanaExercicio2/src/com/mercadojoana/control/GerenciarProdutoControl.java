package com.mercadojoana.control;

import com.mercadojoana.view.GerenciarProduto;
import static com.mercadojoana.view.Principal.desktopPane;

/**
 *
 * @author william.mauro
 */
public class GerenciarProdutoControl {

    private GerenciarProduto gerenciarProduto = null;

    public void abrirTelaGerenciarProdutoAction() {
        if (gerenciarProduto == null) {
            gerenciarProduto = new GerenciarProduto();
            desktopPane.add(gerenciarProduto);
            gerenciarProduto.setVisible(true);
        } else {
            if (gerenciarProduto.isVisible()) {
                gerenciarProduto.pack();
            } else {
                desktopPane.add(gerenciarProduto);
                gerenciarProduto.setVisible(true);
            }
        }
    }

}
