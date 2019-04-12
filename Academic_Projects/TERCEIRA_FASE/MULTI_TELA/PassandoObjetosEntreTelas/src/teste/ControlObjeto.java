package teste;

/**
 *
 * @author William
 */
public class ControlObjeto {

    Objeto object;

    public ControlObjeto() {
        object = new Objeto();
    }

    
    public Objeto criarObjeto(){
        Objeto obj = new Objeto();
        obj.setNome(Frame1.tfNome.getText());
        return obj;
    }
}
