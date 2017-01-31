package exceptions;


public class ExceptionNoEditorLoggedIn extends Exception {

    public ExceptionNoEditorLoggedIn() {
        super("No editor is logged in.");
    }
}
