/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senac.principal;

import senac.view.Gerenciar;

/**
 *
 * @author William
 */
public class Principal {

    public static void main(String[] args) {
        JanelaPrincipal();
    }

    public static void JanelaPrincipal() {
        Gerenciar janelaPrincipal = new Gerenciar();
        janelaPrincipal.setTitle("Gerenciar Produtos");
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setVisible(true);
    }
}
