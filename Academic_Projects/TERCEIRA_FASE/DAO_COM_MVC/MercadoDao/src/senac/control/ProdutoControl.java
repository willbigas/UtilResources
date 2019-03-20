/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senac.control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import senac.dao.ProdutoDao;
import senac.model.Produto;
import senac.uteis.Mensagens;
import senac.uteis.Painel;
import senac.uteis.Validacao;
import senac.view.Gerenciar;

/**
 *
 * @author Alunos
 */
public class ProdutoControl {

    List<Produto> listProdutos;
    ProdutoDao produtoDao;
    Boolean alterando = false;
    Produto PRODUTO_DO_EDIT;
    Double totalProdutos = 0.0;

    public ProdutoControl() {
        produtoDao = new ProdutoDao();
    }

    public void listarAction() {
        listProdutos = produtoDao.listar();
        DefaultTableModel model
                = (DefaultTableModel) Gerenciar.tblProdutos.getModel();
        model.setNumRows(0);
        for (Produto p : listProdutos) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getValor()
            });
        }
        pegandoSomatorio();
    }

    public void listarAction(List<Produto> produtos) {
        DefaultTableModel model
                = (DefaultTableModel) Gerenciar.tblProdutos.getModel();
        model.setNumRows(0);
        for (Produto p : produtos) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getValor()
            });
        }
        pegandoSomatorio();

    }

    public void cadastrarAction() {
        //Validar dados
        if (Validacao.campoVazio(Gerenciar.tfNome)) {
            Painel.msg(Mensagens.ERRO_NOME_INVALIDO);
            Gerenciar.tfNome.requestFocus();
            return;
        }
        if (Validacao.campoVazio(Gerenciar.tfValor)) {
            Painel.msg(Mensagens.ERRO_VALOR_INVALIDO);
            Gerenciar.tfValor.requestFocus();
            return;
        }

        if (alterando) {
            System.out.println(PRODUTO_DO_EDIT);
            PRODUTO_DO_EDIT.setNome(Gerenciar.tfNome.getText());
            PRODUTO_DO_EDIT.setValor(Gerenciar.tfValor.getText());
            editar(PRODUTO_DO_EDIT);
            PRODUTO_DO_EDIT = null;
            alterando = false;
        } else {
            Produto p = new Produto();
            p.setNome(Gerenciar.tfNome.getText());
            p.setValor(Gerenciar.tfValor.getText());

            int res = produtoDao.cadastrar(p);
            if (res > 0) {
                Painel.msg(Mensagens.SUCESSO_CADASTRO);
                listarAction();
            } else {
                Painel.msg(Mensagens.ERRO_CADASTRAR);
            }
        }

    }

    private Produto getProdutoSelecionadoTable() {
        int i = Gerenciar.tblProdutos.getSelectedRow();
        if (i >= 0) {
            return listProdutos.get(i);
        }
        return null;
    }

    public void excluirAction() {
        //Resgatar indice da linha selecionada
        Produto p = getProdutoSelecionadoTable();
        if (p == null) {
            Painel.msg(Mensagens.PRODUTO_NAO_SELECIONADO);
            return;
        } else {
            int resposta = Painel.msgConfirm(Mensagens.ACAO_IRREVERSIVEL);
            System.out.println(resposta);
            if (resposta == JOptionPane.CANCEL_OPTION) {
                return;
            }
            if (resposta == JOptionPane.NO_OPTION) {
                return;
            } else {
                if (produtoDao.deletar(p.getId())) {
                    Painel.msg(Mensagens.SUCESSO_EXCLUIR);
                    listarAction();
                } else {
                    Painel.msg(Mensagens.ERRO_EXCLUIR);
                }
            }
        }

    }

    public Boolean editarAction() {
        PRODUTO_DO_EDIT = new Produto();
        PRODUTO_DO_EDIT = getProdutoSelecionadoTable();
        if (PRODUTO_DO_EDIT == null) {
            Painel.msg(Mensagens.PRODUTO_NAO_SELECIONADO);
            alterando = false;
        } else {
            alterando = true;
            Gerenciar.tfNome.setText(PRODUTO_DO_EDIT.getNome());
            Gerenciar.tfValor.setText(String.valueOf(PRODUTO_DO_EDIT.getValor()));
        }
        return null;
    }

    public void editar(Produto p) {
        if (produtoDao.alterar(p)) {
            Painel.msg(Mensagens.SUCESSO_EDITAR);
            listarAction();
        } else {
            Painel.msg(Mensagens.ERRO_EDITAR);
        }
    }

    public void pesquisarAction() {
        List<Produto> produtos = null;
        try {
            produtos = pesquisar(Gerenciar.tfPesquisa.getText());
        } catch (Exception exception) {
        }
        listarAction(produtos);
    }

    public List<Produto> pesquisar(String termo) {
        List<Produto> retorno = new ArrayList<>();

        try {
            List<?> objs = produtoDao.listar();
            List<Produto> PRODUTOS = (List<Produto>) (Object) objs;
            for (Produto p : PRODUTOS) {
                if (p.getNome().toLowerCase().contains(termo.toLowerCase())) {
                    retorno.add(p);
                }
            }

        } catch (Exception exception) {
        }
        return retorno;
    }

    public void pegandoSomatorio() {
        for (Produto listProduto : listProdutos) {
            totalProdutos += listProduto.getValor();
        }
        Gerenciar.lblValorTotal.setText(String.valueOf(totalProdutos));
    }

}
