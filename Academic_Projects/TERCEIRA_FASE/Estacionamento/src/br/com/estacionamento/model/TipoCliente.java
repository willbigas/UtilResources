package br.com.estacionamento.model;

/**
 *
 * @author William
 */
public class TipoCliente {

    private Integer id;
    private String nome;
    private Double preco;

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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "TipoCliente{" + "nome=" + nome + ", preco=" + preco + '}';
    }

}
