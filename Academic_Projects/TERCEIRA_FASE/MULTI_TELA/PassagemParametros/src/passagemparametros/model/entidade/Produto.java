package passagemparametros.model.entidade;

/**
 *
 * @author Clony
 */
public class Produto {

    private Integer id;
    private String nome;
    private double valor;

    public Produto() {
    }

    public Produto(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Produto(Integer id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setValor(String valor) {
        try {
            setValor(Double.parseDouble(valor));
        } catch (NumberFormatException e) {
            setValor(0);
        }
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", valor=" + valor + '}';
    }

}
