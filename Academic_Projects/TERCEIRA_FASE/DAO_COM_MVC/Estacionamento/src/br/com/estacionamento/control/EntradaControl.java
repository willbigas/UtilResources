package br.com.estacionamento.control;

import br.com.estacionamento.dao.CarroDao;
import br.com.estacionamento.dao.CondutorDao;
import br.com.estacionamento.dao.EntradaDao;
import br.com.estacionamento.dao.TipoClienteDao;
import br.com.estacionamento.dao.UltimaEntradaDao;
import br.com.estacionamento.model.Entrada;
import br.com.estacionamento.util.Mensagem;
import br.com.estacionamento.util.Swing;
import br.com.estacionamento.util.UtilFormat;
import br.com.estacionamento.util.Validacao;
import br.com.estacionamento.view.JanelaEntrada;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author William
 */
public class EntradaControl {

    // INTANCIAS DE INTERFACES DAOS // 
    CarroDao CARRO_DAO = new CarroDao();
    CondutorDao CONDUTOR_DAO = new CondutorDao();
    TipoClienteDao TIPO_CLIENTE_DAO = new TipoClienteDao();
    EntradaDao ENTRADA_DAO = new EntradaDao();
    UltimaEntradaDao ULTIMA_ENTRADA_DAO = new UltimaEntradaDao();

    // INSTANCIAS DAS CLASSES CONTROL UTILIZADAS NESSA CLASSE //
    CarroControl CARRO_CONTROL;
    CondutorControl CONDUTOR_CONTROL;
    TipoClienteControl TIPO_CLIENTE_CONTROL;

    /**
     * Construindo os Controls Necessarios
     */
    public EntradaControl() {
        CARRO_CONTROL = new CarroControl();
        CONDUTOR_CONTROL = new CondutorControl();
        TIPO_CLIENTE_CONTROL = new TipoClienteControl();
    }

    public void inserindoEntradaAction() {
        if (validandoCamposVazios()) {
            return;
        }

        Entrada entradaDoBanco = ENTRADA_DAO.pesquisarPorPlaca(JanelaEntrada.tfPlaca.getText());
        System.out.println("Entrada : " + entradaDoBanco);
        if (entradaDoBanco.getCarro().getPlaca() != null) {
            System.out.println("Caiu no Editando");
        }

        /**
         * Inserindo as Entidades necessarias para criar uma Entrada e pegando
         * os retornos de IDS do Banco.
         */
        Integer cInserido = CARRO_CONTROL.inserirAutomovel();
        Integer coInserido = CONDUTOR_CONTROL.inserirCondutor();
        Integer tcInserido = TIPO_CLIENTE_CONTROL.inserirTipoCliente();

        String hora = JanelaEntrada.tfHora.getText(); // Pegando Hora do Usuario
        String campos[] = hora.split(":"); // Dividindo Campos de Hora pelo ":"
        try {
            Entrada e = new Entrada();
            e.setId(Integer.MAX_VALUE);
            e.setCarro(CARRO_DAO.lerPorId(cInserido));
            e.setCondutor(CONDUTOR_DAO.lerPorId(coInserido));
            e.setTipoCliente(TIPO_CLIENTE_DAO.lerPorId(tcInserido));

            // Criando Objeto Calendar com Base na Data e Hora do Usuario
            Calendar calendar = criandoCalendarDoUsuario(campos);
            e.setDataEntrada(calendar.getTime());

            // Verificando se ja Existe uma Ultima Entrada no BD e Excluindo
            List<Entrada> entradasRecebidas
                    = ULTIMA_ENTRADA_DAO.pesquisar(JanelaEntrada.tfPlaca.getText());
            if (entradasRecebidas != null) {
                for (Entrada entradasRecebida : entradasRecebidas) {
                    ULTIMA_ENTRADA_DAO.deletarPorId(entradasRecebida.getId());
                }
                /**
                 * Recebendo nova UltimaEntrada do BD , Atribuindo a Entidade e
                 * persistindo na tabela Principal
                 */
                int idNovaultimaEntrada = ULTIMA_ENTRADA_DAO.cadastrar(e);
                e.setId(idNovaultimaEntrada);
                e.setUltimaEntrada(e);

            }

            // Cadastrando Entrada no BD
            if (ENTRADA_DAO.cadastrar(e) > 0) {
                limpandoCampos();
                Swing.msg(Mensagem.ENTRADA_SUCESSO);

            } else {
                Swing.msg(Mensagem.ENTRADA_ERRO);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private boolean validandoCamposVazios() {
        if (Validacao.isEmpty(JanelaEntrada.tfPlaca)
                || Validacao.isEmpty(JanelaEntrada.tfCor)
                || Validacao.isEmpty(JanelaEntrada.tfCondutor)
                || Validacao.isEmpty(JanelaEntrada.tfMarca)
                || Validacao.isEmpty(JanelaEntrada.tfModelo)
                || Validacao.isEmpty(JanelaEntrada.tfData)
                || Validacao.isEmpty(JanelaEntrada.tfHora)) {
            Swing.msg(Mensagem.CAMPO_VAZIO);
            return true;
        }
        return false;
    }

    private void limpandoCampos() {
        JanelaEntrada.tfCondutor.setText(null);
        JanelaEntrada.tfCor.setText(null);
        JanelaEntrada.tfData.setText(null);
        JanelaEntrada.tfHora.setText(null);
        JanelaEntrada.tfModelo.setText(null);
        JanelaEntrada.tfPlaca.setText(null);
    }

    private Calendar criandoCalendarDoUsuario(String[] campos) throws NumberFormatException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(UtilFormat.data(JanelaEntrada.tfData.getText())); //colocando o objeto Date no Calendar
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(campos[0])); // Atribuindo Hora
        calendar.set(Calendar.MINUTE, Integer.valueOf(campos[1])); // Atribuindo Minuto
        calendar.set(Calendar.SECOND, 0); // Atribuindo Segundo
        return calendar;
    }
}
