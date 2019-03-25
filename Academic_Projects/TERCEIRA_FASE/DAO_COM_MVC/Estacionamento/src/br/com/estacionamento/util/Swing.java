package br.com.estacionamento.util;

import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class Swing {

    public static void msg(String texto) {
        JOptionPane.showMessageDialog(null, texto);
    }
     public static int confirm(String pergunta){
        return JOptionPane.showConfirmDialog(null, pergunta);
    }
}
