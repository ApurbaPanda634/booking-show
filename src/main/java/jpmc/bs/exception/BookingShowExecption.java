package jpmc.bs.exception;

public class BookingShowExecption extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BookingShowExecption(String message) {
        super(message);
    }
}
