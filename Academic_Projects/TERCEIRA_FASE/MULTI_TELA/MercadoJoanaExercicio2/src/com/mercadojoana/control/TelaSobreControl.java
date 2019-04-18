package com.mercadojoana.control;

import com.mercadojoana.view.TelaSobre;

/**
 *
 * @author william.mauro
 */
public class TelaSobreControl {

    private TelaSobre telaSobre = null;
    
    
     public void chamandoTelaSobre() {
        if (telaSobre == null) {
            telaSobre = new TelaSobre();
            
        }
        telaSobre.setVisible(true);
    }

}
