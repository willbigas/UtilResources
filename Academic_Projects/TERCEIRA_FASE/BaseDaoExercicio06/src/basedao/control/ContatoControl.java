package basedao.control;

import basedao.dao.ContatoDao;
import basedao.model.Contato;
import javax.swing.table.DefaultTableModel;
import basedao.util.UtilFormat;
import br.com.basedao.view.contato.JanelaGerenciarContato;

/**
 *
 * @author William
 */
public class ContatoControl {

    public static ContatoDao CONTATO_DAO = new ContatoDao();

   

    public ContatoControl() {
        
    }

    public void inserirContatoAction() {
        Contato c = new Contato();
        try {
            c.setId(1);
            c.setNome(JanelaGerenciarContato.campoNome.getText());
            c.setEmail(JanelaGerenciarContato.campoEmail.getText());
            c.setTelefone(Integer.valueOf(JanelaGerenciarContato.campoTelefone.getText()));
            c.setCelular(Integer.valueOf(JanelaGerenciarContato.campoCelular.getText()));
            c.setNascimento(UtilFormat.data(JanelaGerenciarContato.campoNascimento.getText()));
        } catch (Exception exception) {
        }

        
        
        if (CONTATO_DAO.cadastrar(c)) {
            System.out.println("Contato Cadastrado");
            listarAction();
        } else {
            System.out.println("Deu ruim!");
        }
    }

    public void listarAction() {
        DefaultTableModel model = (DefaultTableModel) JanelaGerenciarContato.tabelaContato.getModel();
        model.setNumRows(0);
        for (Contato c : CONTATO_DAO.listar()) {
            model.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getEmail(),
                c.getTelefone(),
                c.getCelular(),
                c.getNascimento()
            });
        }
    }

}
