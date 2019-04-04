
import view.ViewCategoria;


public class Principal {

    public static void main(String[] args) {
        JanelaPrincipal();
    }

    public static void JanelaPrincipal() {
        ViewCategoria janelaPrincipal = new ViewCategoria();
        janelaPrincipal.setTitle("Gerenciar Categorias");
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setVisible(true);
    }
    
    
}
