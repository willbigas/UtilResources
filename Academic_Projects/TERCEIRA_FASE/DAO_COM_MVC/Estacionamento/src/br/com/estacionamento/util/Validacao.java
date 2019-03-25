package br.com.estacionamento.util;

import javax.swing.JTextField;

/**
 *
 * @author William
 */
public class Validacao {

    public static boolean isEmpty(JTextField value) {
        if (value.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
