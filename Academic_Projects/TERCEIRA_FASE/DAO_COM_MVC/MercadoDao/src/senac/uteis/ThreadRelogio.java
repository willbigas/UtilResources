package senac.uteis;

import java.text.SimpleDateFormat;
import java.util.Date;
import senac.view.Gerenciar;

/**
 *
 * @author Alunos
 */
public class ThreadRelogio {

    public static boolean play = true;

    public static void start() {
        new Thread() {

            @Override
            public void run() {
                while (play) {

                    SimpleDateFormat sdf = new SimpleDateFormat("d/M/y H:m:s");
                    Date data = new Date();
                    Gerenciar.lblHorario.setText(sdf.format(data));
                    try {

                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                    }
                }
                run();

            }
        }.start();
    }
}
