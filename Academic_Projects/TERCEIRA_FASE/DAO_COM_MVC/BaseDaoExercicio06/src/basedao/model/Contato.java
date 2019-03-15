package basedao.model;

import java.util.Date;

/**
 *
 * @author William
 */
public class Contato {

    private Integer id;
    private String nome;
    private String email;
    private Integer telefone;
    private Integer celular;
    private Date nascimento;

    public Contato() {
    }

    public Contato(Integer id, String nome, String email, Integer telefone, Integer celular, Date nascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.nascimento = nascimento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return "Contato{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", celular=" + celular + ", nascimento=" + nascimento + '}';
    }
    
    

}
