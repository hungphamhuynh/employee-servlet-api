package exception;

public class DegreeNotFoundException extends RuntimeException {
    public DegreeNotFoundException() {
        super();
    }

    public DegreeNotFoundException(String message) {
        super(message);
    }
}
