package exceptions;


public class ExceptionReportDoesNotExist extends Exception {

    public ExceptionReportDoesNotExist() {
        super("Report does not exist.\n");
    }
}
