package exceptions;


public class ExceptionNoUserLoggedIn extends Exception {

    public ExceptionNoUserLoggedIn() {
        super("No user is logged in.\n");
    }
}
