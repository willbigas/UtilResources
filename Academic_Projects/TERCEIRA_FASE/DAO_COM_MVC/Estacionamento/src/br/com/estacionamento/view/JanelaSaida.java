package br.com.estacionamento.view;

import br.com.estacionamento.control.SaidaControl;
import br.com.estacionamento.principal.Principal;
import br.com.estacionamento.util.JFrameData;
import br.com.estacionamento.util.JFrameHora;
import br.com.estacionamento.util.Mensagem;
import br.com.estacionamento.util.Swing;

/**
 *
 * @author William
 */
public class JanelaSaida extends javax.swing.JFrame {

    SaidaControl SAIDA_CONTROL;

    /**
     * Creates new form JanelaSaida
     */
    public JanelaSaida() {
        initComponents();
        SAIDA_CONTROL = new SaidaControl();
        SAIDA_CONTROL.listandoEntradasAction();
        JFrameHora horaAtual = new JFrameHora();
        JFrameData dataAtual = new JFrameData();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        btCalcularPreco = new javax.swing.JButton();
        lblPlaca = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btFinalizar = new javax.swing.JButton();
        btVoltar = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        lblvalorTotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(440, 460));

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTitulo.setText("Saída de Automovel");

        jLabel4.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel4.setText("Valor Total :");

        lblValorTotal.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lblValorTotal.setText("-----");

        btCalcularTroco.setText("Troco");
        btCalcularTroco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCalcularTrocoActionPerformed(evt);
            }
        });

        tabelaSaida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PLACA", "DATA DE ENTRADA", "HORA DE ENTRADA", "CONDUTOR"
            }
        ));
        jScrollPane1.setViewportView(tabelaSaida);

        btCalcularPreco.setText("Calcular");
        btCalcularPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCalcularPrecoActionPerformed(evt);
            }
        });

        lblPlaca.setText("Placa:");

        tfPesquisarPlaca.setColumns(10);

        btPesquisar.setText("Buscar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        lblTroco.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lblTroco.setText("Valor para Devolver:");

        lblValorTroco.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lblValorTroco.setText("-----");

        tfCampoTroco.setColumns(10);

        jLabel1.setText("Calculo de Troco");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btFinalizar.setText("Finalizar");
        btFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarActionPerformed(evt);
            }
        });

        btVoltar.setText("Voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        lblTotal.setText("Total:");

        lblvalorTotal.setText("null");

        jLabel2.setText("Data:");

        jLabel3.setText("Hora:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblValorTotal))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(lblPlaca))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btVoltar)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(lblTitulo))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfPesquisarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblvalorTotal))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfHoraSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(btCalcularPreco))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTroco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValorTroco))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfCampoTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btCalcularTroco)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btFinalizar))
                    .addComponent(jSeparator1))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btVoltar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPlaca)
                            .addComponent(tfPesquisarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPesquisar)
                            .addComponent(lblTotal)
                            .addComponent(lblvalorTotal))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfHoraSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btCalcularPreco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblValorTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfCampoTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btCalcularTroco))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTroco)
                                    .addComponent(lblValorTroco)))))
                    .addComponent(btFinalizar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCalcularPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalcularPrecoActionPerformed
        // TODO add your handling code here:
        SAIDA_CONTROL.calculaPrecoAction();
    }//GEN-LAST:event_btCalcularPrecoActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        // TODO add your handling code here:
        SAIDA_CONTROL.pesquisarAction();

    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btCalcularTrocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalcularTrocoActionPerformed
        // TODO add your handling code here:
        SAIDA_CONTROL.calcularTrocoAction();
    }//GEN-LAST:event_btCalcularTrocoActionPerformed

    private void btFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarActionPerformed
        // TODO add your handling code here:
        SAIDA_CONTROL.finalizaSaidaAction();
        SAIDA_CONTROL.listandoEntradasAction();
        Swing.msg(Mensagem.SAIDA_SUCESSO);

    }//GEN-LAST:event_btFinalizarActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Principal.JanelaPrincipal();

    }//GEN-LAST:event_btVoltarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaSaida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCalcularPreco;
    public static final javax.swing.JButton btCalcularTroco = new javax.swing.JButton();
    private javax.swing.JButton btFinalizar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    public static final javax.swing.JLabel lblTroco = new javax.swing.JLabel();
    public static final javax.swing.JLabel lblValorTotal = new javax.swing.JLabel();
    public static final javax.swing.JLabel lblValorTroco = new javax.swing.JLabel();
    private javax.swing.JLabel lblvalorTotal;
    public static final javax.swing.JTable tabelaSaida = new javax.swing.JTable();
    public static final javax.swing.JTextField tfCampoTroco = new javax.swing.JTextField();
    public static final javax.swing.JFormattedTextField tfDataSaida = new javax.swing.JFormattedTextField();
    public static final javax.swing.JFormattedTextField tfHoraSaida = new javax.swing.JFormattedTextField();
    public static final javax.swing.JTextField tfPesquisarPlaca = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}
