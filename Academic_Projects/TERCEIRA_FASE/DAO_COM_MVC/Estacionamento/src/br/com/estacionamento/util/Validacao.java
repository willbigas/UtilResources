package br.com.estacionamento.util;

import br.com.estacionamento.model.Entrada;
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

    public static boolean isNull(Entrada obj) {
        return obj == null;
    }

}
