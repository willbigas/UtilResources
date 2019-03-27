package br.com.estacionamento.util;

import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class Swing {

    /**
     * Executa um ShowMessage no Swing
     * @param texto Recebe como parametro uma String texto;
     */
    public static void msg(String texto) {
        JOptionPane.showMessageDialog(null, texto);
    }
    
    /**
     * Executa uma Janela de Confirmação no Swing
     * @param pergunta Recebe como Parametro uma Pergunta
     * @return Retorna Opção Selecionada
     */
    public static int confirm(String pergunta){
        return JOptionPane.showConfirmDialog(null, pergunta);
    }
}
