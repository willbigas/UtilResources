package control;

import factory.DaoFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Endereco;
import model.PessoaDependente;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.PessoaParente;
import view.TelaPrincipal;

/**
 *
 * @author William
 */
public class TelaPrincipalControl {

    TelaPrincipal telaPrincipal;
    DaoFactory daoFactory;

    public TelaPrincipalControl() {
        telaPrincipal = new TelaPrincipal(this);
        telaPrincipal.setLocationRelativeTo(null);
        daoFactory = new DaoFactory();
        carregarComboBoxDeNomes();
        telaPrincipal.setVisible(true);
    }

    public void carregarComboBoxDeNomes() {
        String[] nomes = {"Endereco", "Dependente", "Fisica", "Juridica", "Parente"};
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel(nomes);
        telaPrincipal.getCbTipoObjeto().setModel(model);
    }

    public void inserirObjetoAction() {
        String nomeObjeto = (String) telaPrincipal.getCbTipoObjeto().getSelectedItem();

        if (nomeObjeto.equals("Endereco")) {
            Endereco endereco = new Endereco();
            endereco.setNome(telaPrincipal.getTfNome().getText());
            daoFactory.inserirAlgoNoBanco(nomeObjeto, endereco);
            JOptionPane.showMessageDialog(telaPrincipal, "Endereco Gravado com sucesso!");
        }

        if (nomeObjeto.equals("Dependente")) {
            PessoaDependente pessoaDependente = new PessoaDependente();
            pessoaDependente.setNome(telaPrincipal.getTfNome().getText());
            daoFactory.inserirAlgoNoBanco(nomeObjeto, pessoaDependente);
            JOptionPane.showMessageDialog(telaPrincipal, "Dependente Gravado com sucesso!");
        }

        if (nomeObjeto.equals("Fisica")) {
            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setNome(telaPrincipal.getTfNome().getText());
            daoFactory.inserirAlgoNoBanco(nomeObjeto, pessoaFisica);
            JOptionPane.showMessageDialog(telaPrincipal, "Pessoa Fisica Gravada com sucesso!");
        }

        if (nomeObjeto.equals("Juridica")) {
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setNome(telaPrincipal.getTfNome().getText());
            daoFactory.inserirAlgoNoBanco(nomeObjeto, pessoaJuridica);
            JOptionPane.showMessageDialog(telaPrincipal, "Pessoa Juridica Gravada com sucesso!");
        }

        if (nomeObjeto.equals("Parente")) {
            PessoaParente pessoaParente = new PessoaParente();
            pessoaParente.setNome(telaPrincipal.getTfNome().getText());
            daoFactory.inserirAlgoNoBanco(nomeObjeto, pessoaParente);
            JOptionPane.showMessageDialog(telaPrincipal, "Parente Gravado com sucesso!");
        }

    }

}
