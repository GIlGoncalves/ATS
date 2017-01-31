package exceptions;


public class ExceptionUserLoggedIn extends Exception {

    public ExceptionUserLoggedIn() {
        super("There is a user logged in.\n");
    }
}
