package modelsInterfaceToUI.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 23/10/13
 * Time: 22:21
 */

public class RepetedRestrictionException extends Exception {
    public RepetedRestrictionException() {
        super("La restriccion que intenta guardar ya ha sido registrada");
    }
}
