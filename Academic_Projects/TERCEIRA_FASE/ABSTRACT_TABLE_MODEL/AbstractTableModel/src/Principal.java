
import view.ViewProduto;


public class Principal {

    public static void main(String[] args) {
        JanelaPrincipal();
    }

    public static void JanelaPrincipal() {
        ViewProduto janelaPrincipal = new ViewProduto();
        janelaPrincipal.setTitle("Gerenciar Produtos");
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setVisible(true);
    }
}
