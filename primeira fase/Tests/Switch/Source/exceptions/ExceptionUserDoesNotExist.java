package exceptions;


public class ExceptionUserDoesNotExist extends Exception {

    public ExceptionUserDoesNotExist() {
        super("User does not exist.\n");
    }
}
