package exception;

public class PayrollExistsException extends RuntimeException {
    public PayrollExistsException(String message) {
        super(message);
    }
}
