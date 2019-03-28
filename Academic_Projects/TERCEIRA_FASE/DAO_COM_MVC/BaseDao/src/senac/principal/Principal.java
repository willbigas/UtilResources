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
