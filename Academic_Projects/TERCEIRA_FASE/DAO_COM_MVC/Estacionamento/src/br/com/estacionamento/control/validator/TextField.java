package br.com.estacionamento.control.validator;

import javax.swing.JTextField;

/**
 *
 * @author William
 */
public class TextField {

    /**
     * Verificar se o valor do JtextField está vazio
     * @param value
     * @return 
     */
    public static boolean isEmpty(JTextField value) {
        if (value.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Limpa o Conteudo do JtextField
     *
     * @param value
     */
    public static void clearTf(JTextField value) {
        value.setText(null);
    }

}
