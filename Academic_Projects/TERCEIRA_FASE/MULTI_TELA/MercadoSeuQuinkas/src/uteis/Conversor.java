/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Clony
 */
public class Conversor {
    
    public static java.sql.Date dataUtilParaSql(java.util.Date data){
        return new java.sql.Date(data.getTime());
    }
    
    /**
     * Converte uma data vinda no formato do banco de dados
     * @param data
     * @return data no formato dd/MM/yyyy
     */
    public static String dataBancoParaUsuario(java.sql.Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(data);
    }
    
    /**
    * Converte uma data vinda no formato de string do usuário
    * @param data
    * @return data no formato do banco de dados
    */
    public static java.sql.Date dataUsuarioParaBanco(String data){
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dataUtil = formatador.parse(data);
            return new java.sql.Date(dataUtil.getTime());
        } catch (ParseException ex) {
            System.out.println("Erro ao converter data");
            return null;
        }
        
    }
    
    /**
     * Retorna a data atual convertida em String no formato dd/MM/yyyy
     * @return 
     */
    public static String dataAtual(){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dataUtil = new java.util.Date();
        return formatador.format(dataUtil);
    }
    
     /**
     * Convertendo String para Data no Formato DD/MM/AAAA
     *
     * @param dataStr
     * @return Data
     * @throws Exception
     */
    public static java.util.Date data(String dataStr) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.parse(dataStr);
    }

    /**
     * Convertendo Data para String no Formato DD/MM/AAAA
     *
     * @param data
     * @return String
     * @throws Exception
     */
    public static String data(java.util.Date data) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(data);
    }
    
}
