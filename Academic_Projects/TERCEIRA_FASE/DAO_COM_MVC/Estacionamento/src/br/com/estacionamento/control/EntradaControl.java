package br.com.estacionamento.control;

import br.com.estacionamento.control.validator.EntradaValidator;
import br.com.estacionamento.dao.CarroDao;
import br.com.estacionamento.dao.CondutorDao;
import br.com.estacionamento.dao.EntradaDao;
import br.com.estacionamento.dao.TipoClienteDao;
import br.com.estacionamento.dao.UltimaEntradaDao;
import br.com.estacionamento.model.Entrada;
import br.com.estacionamento.util.Mensagem;
import br.com.estacionamento.util.Swing;
import br.com.estacionamento.util.UtilFormat;
import br.com.estacionamento.control.validator.TextField;
import br.com.estacionamento.view.JanelaEntrada;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author William
 */
public class EntradaControl {

    public Double VALOR_TOTAL_ENTRADAS = 0.0;

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

    public void inserirEntradaAction() {
        if (validaCamposVazios()) {
            return;
        } else {
            Entrada entradaDoBanco = ENTRADA_DAO.pesquisarPorPlaca(JanelaEntrada.tfPlaca.getText());
            // Editar
            if (EntradaValidator.isNull(entradaDoBanco)) {
                editarEntrada(entradaDoBanco);
            } else {
                inserirEntrada();
            }

        }

    }

    private void inserirEntrada() {
        /**
         * Inserindo as Entidades necessarias para criar uma Entrada e pegando
         * os retornos de IDS do Banco.
         */
        Integer cInserido = CARRO_CONTROL.inserirAutomovel();
        Integer coInserido = CONDUTOR_CONTROL.inserirCondutor();
        Integer tcInserido = TIPO_CLIENTE_CONTROL.inserirTipoCliente();

        String hora = JanelaEntrada.tfHora.getText(); // Pegando Hora do Usuario
        String campos[] = hora.split(":"); // Dividindo Campos de Hora pelo ":"
        Entrada e = criandoEntidadeEntrada(cInserido, coInserido, tcInserido, campos);

        persistindoEntradaNoBanco(e);
    }

    public void persistindoEntradaNoBanco(Entrada e) {
        // Cadastrando Entrada no BD
        if (ENTRADA_DAO.cadastrar(e) > 0) {
            limparCampos();
            Swing.msg(Mensagem.ENTRADA_SUCESSO);

        } else {
            Swing.msg(Mensagem.ENTRADA_ERRO);
        }
    }

    public Entrada criandoEntidadeEntrada(Integer cInserido, Integer coInserido, Integer tcInserido, String[] campos) throws NumberFormatException {
        Entrada e = new Entrada();
        e.setId(Integer.MAX_VALUE);
        e.setCarro(CARRO_DAO.lerPorId(cInserido));
        e.setCondutor(CONDUTOR_DAO.lerPorId(coInserido));
        e.setTipoCliente(TIPO_CLIENTE_DAO.lerPorId(tcInserido));
        // Criando Objeto Calendar com Base na Data e Hora do Usuario
        Calendar calendar = criandoCalendar();
        e.setDataEntrada(calendar.getTime());
        e = criandoUltimaEntrada(e);
        return e;
    }

    private Entrada criandoUltimaEntrada(Entrada e) {
        // Verificando se ja Existe uma Ultima Entrada no BD e Excluindo
        Entrada entradaDoBanco = ULTIMA_ENTRADA_DAO.pesquisarPorPlaca(JanelaEntrada.tfPlaca.getText());

        // Se existir uma ultima entrada , sobrescreve.
        if (!EntradaValidator.isNull(entradaDoBanco)) {
            e.setId(entradaDoBanco.getId());
            ULTIMA_ENTRADA_DAO.alterar(e);
            return e;

            // Se não existir cria e aponta pra tabela principal
        } else {
            int idNovaultimaEntrada = ULTIMA_ENTRADA_DAO.cadastrar(e);
            e.setId(idNovaultimaEntrada);
            e.setUltimaEntrada(e);
            return e;
        }
    }

    /**
     * Validando Campos Vazios
     *
     * @return
     */
    private boolean validaCamposVazios() {
        if (TextField.isEmpty(JanelaEntrada.tfPlaca)
                && TextField.isEmpty(JanelaEntrada.tfCor)
                && TextField.isEmpty(JanelaEntrada.tfCondutor)
                && TextField.isEmpty(JanelaEntrada.tfMarca)
                && TextField.isEmpty(JanelaEntrada.tfModelo)
                && TextField.isEmpty(JanelaEntrada.tfData)
                && TextField.isEmpty(JanelaEntrada.tfHora)) {
            Swing.msg(Mensagem.CAMPO_VAZIO);
            return true;
        }
        return false;
    }

    /**
     * Limpa Campos do Painel
     */
    private void limparCampos() {
        TextField.clearTf(JanelaEntrada.tfCondutor);
        TextField.clearTf(JanelaEntrada.tfCor);
        TextField.clearTf(JanelaEntrada.tfData);
        TextField.clearTf(JanelaEntrada.tfHora);
        TextField.clearTf(JanelaEntrada.tfModelo);
        TextField.clearTf(JanelaEntrada.tfPlaca);
        TextField.clearTf(JanelaEntrada.tfMarca);
    }

    /**
     * Pegando Dados do Usuario e transformando um Calendar Com Date e Hour
     *
     * @param campos
     * @return
     * @throws NumberFormatException
     */
    private Calendar criandoCalendar() throws NumberFormatException {
        String hora = JanelaEntrada.tfHora.getText(); // Pegando Hora do Usuario
        String campos[] = hora.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(UtilFormat.data(JanelaEntrada.tfData.getText())); //colocando o objeto Date no Calendar
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(campos[0])); // Atribuindo Hora
        calendar.set(Calendar.MINUTE, Integer.valueOf(campos[1])); // Atribuindo Minuto
        calendar.set(Calendar.SECOND, 0); // Atribuindo Segundo
        return calendar;
    }

    private void editarEntrada(Entrada e) {
        atualizaTabelasDeEntrada(e);
        Calendar calendar = criandoCalendar();
        e.setDataEntrada(calendar.getTime());
        e.setDataSaida(null);
        e = criandoUltimaEntrada(e);
        alterandoEntradaNoBanco(e);

    }

    public void alterandoEntradaNoBanco(Entrada e) {
        if (ENTRADA_DAO.alterar(e)) {
            limparCampos();
            Swing.msg(Mensagem.ATUALIZADA_SUCESSO);

        } else {
            Swing.msg(Mensagem.ATUALIZADA_ERRO);
        }
    }

    public void atualizaTabelasDeEntrada(Entrada e) {
        CARRO_CONTROL.atualizarAutomovel(e.getCarro());
        CONDUTOR_CONTROL.atualizarCondutor(e.getCondutor());
        TIPO_CLIENTE_CONTROL.atualizarTipoCliente(e.getTipoCliente());
    }

    private void calculandoValorTotalEntrada() {
        List<Entrada> entradas = ENTRADA_DAO.listar();
        for (Entrada entrada : entradas) {
            VALOR_TOTAL_ENTRADAS += entrada.getValorTotal();
        }
    }

    public void atualizaLabelValorToTalAction() {
        calculandoValorTotalEntrada();
        JanelaEntrada.lblValorTotalCarro.setText(UtilFormat.decimalFormatR$(VALOR_TOTAL_ENTRADAS));
    }

}
