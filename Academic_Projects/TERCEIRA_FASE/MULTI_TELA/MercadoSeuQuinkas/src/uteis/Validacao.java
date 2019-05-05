package uteis;

import javax.swing.JTextField;

/**
 *
 * @author william.mauro
 */
public class Validacao {
   
   public static void tfVazio(JTextField field){
       if (field.getText().isEmpty()) {
           Mensagem.msg(Texto.CAMPO_VAZIO);
       }
   } 
    
}
