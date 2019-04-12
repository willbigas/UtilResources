/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

/**
 *
 * @author William
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Janela1();
    }
    
    public static void Janela1() {
        Frame1 janela1 = new Frame1();
        janela1.setTitle("JANELA DE LOGIN");
        janela1.setLocationRelativeTo(null);
        janela1.setVisible(true);
    }
    public static void Janela2(Objeto objeto) {
        Frame2 janela1 = new Frame2(objeto);
        janela1.setTitle("JANELA DE LOGIN");
        janela1.setLocationRelativeTo(null);
        janela1.setVisible(true);
    }
    
}
