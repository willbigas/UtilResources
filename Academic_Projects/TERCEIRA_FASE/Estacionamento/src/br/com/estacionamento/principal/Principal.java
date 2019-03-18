/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estacionamento.principal;

import br.com.estacionamento.view.JanelaEntrada;
import br.com.estacionamento.view.JanelaPrincipal;
import br.com.estacionamento.view.JanelaSaida;

/**
 *
 * @author William
 */
public class Principal {

    public static void main(String[] args) {
        JanelaPrincipal();
    }

    public static void JanelaPrincipal() {
        JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
        janelaPrincipal.setTitle("JanelaPrincipal");
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setVisible(true);
    }

    public static void JanelaEntrada() {
        JanelaEntrada janelaEntrada = new JanelaEntrada();
        janelaEntrada.setTitle("Janela Entrada");
        janelaEntrada.setLocationRelativeTo(null);
        janelaEntrada.setVisible(true);
    }

    public static void JanelaSaida() {
        JanelaSaida janelaSaida = new JanelaSaida();
        janelaSaida.setTitle("Janela Entrada");
        janelaSaida.setLocationRelativeTo(null);
        janelaSaida.setVisible(true);
    }
}
