package com.mercadojoana.control;

import com.mercadojoana.view.TelaAjuda;

/**
 *
 * @author william.mauro
 */
public class TelaAjudaControl {

    private TelaAjuda telaAjuda = null;

    public void abrindoTelaAjudaAction() {
        if (telaAjuda == null) {
            telaAjuda = new TelaAjuda();

        }
        telaAjuda.setVisible(true);
    }

}
