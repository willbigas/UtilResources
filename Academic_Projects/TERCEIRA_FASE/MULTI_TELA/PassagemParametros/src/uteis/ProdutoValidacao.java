package uteis;

public class ProdutoValidacao {

    public static boolean validaNome(String nome) {
        return !nome.trim().equals("");
    }

    public static boolean validaValor(double valor) {
        return valor > 0;
    }

}
