package exceptions;


public class ExceptionChronicleNotFound extends Exception {

    public ExceptionChronicleNotFound() {
        super("Chronicle does not exist.");
    }
}
