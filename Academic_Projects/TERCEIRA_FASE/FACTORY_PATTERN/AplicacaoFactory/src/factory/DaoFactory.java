/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import interfaces.EnderecoDao;
import interfaces.PessoaDependenteDao;
import interfaces.PessoaFisicaDao;
import interfaces.PessoaJuridicaDao;
import interfaces.PessoaParenteDao;
import javax.swing.JOptionPane;
import model.Endereco;
import model.PessoaDependente;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.PessoaParente;

/**
 *
 * @author William
 */
public class DaoFactory {
    
    private static EnderecoDao enderecoDao;
    private static PessoaFisicaDao pessoaFisicaDao;
    private static PessoaJuridicaDao pessoaJuridicaDao;
    private static PessoaDependenteDao pessoaDependenteDao;
    private static PessoaParenteDao pessoaParenteDao;

    public DaoFactory() {
        enderecoDao = new EnderecoDao();
        pessoaFisicaDao = new PessoaFisicaDao();
        pessoaJuridicaDao = new PessoaJuridicaDao();
        pessoaDependenteDao = new PessoaDependenteDao();
        pessoaParenteDao = new PessoaParenteDao();
    }
    
    

    public void inserirAlgoNoBanco(String nomeDoObjeto, Object obj) {
        switch (nomeDoObjeto) {
            case "Endereco":
                enderecoDao.inserir((Endereco) obj);
                break;
            case "Dependente":
                pessoaDependenteDao.inserir((PessoaDependente) obj);
                break;
            case "Fisica":
                pessoaFisicaDao.inserir((PessoaFisica) obj);
                break;
            case "Juridica":
                pessoaJuridicaDao.inserir((PessoaJuridica) obj);
                break;
            case "Parente":
                pessoaParenteDao.inserir((PessoaParente) obj);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Não foram encontrados objetos validos para inserir!");
        }

    }

}
