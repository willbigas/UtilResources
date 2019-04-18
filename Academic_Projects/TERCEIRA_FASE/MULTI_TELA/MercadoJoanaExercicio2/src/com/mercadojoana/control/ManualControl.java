package com.mercadojoana.control;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author william.mauro
 */
public class ManualControl {

    public void abrirArquivoManualAction() {
        // TODO add your handling code here:
        try {
            File arquivo = new File(getClass().getResource("/com/mercadojoana/files/loremipsum.pdf").getFile());
            Desktop.getDesktop().open(arquivo);
        } catch (IOException iOException) {
        }
    }

}
