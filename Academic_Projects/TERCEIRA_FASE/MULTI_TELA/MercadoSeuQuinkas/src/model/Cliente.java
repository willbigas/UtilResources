/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;


/**
 *
 * @author renato.dasilva
 */
public class Cliente {
    private Integer id;
    private String nome;
    private Date dataNascimento;
    private String cep;
    private int ativo = 1;
    private Cidade cidade;

    public Cliente() {
        cidade = new Cidade();
    }

    public Cliente(String nome, Date dataNascimento, String cep, Cidade cidade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.cidade = cidade;
    }

    public Cliente(Integer id, String nome, Date dataNascimento, String cep, Cidade cidade) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.cidade = cidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return id + " " + nome;
    }
    
}
