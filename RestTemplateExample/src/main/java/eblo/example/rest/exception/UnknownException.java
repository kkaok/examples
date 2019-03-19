package eblo.example.rest.exception;

public class UnknownException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public UnknownException() {
        super();
    }

    public UnknownException(String msg) {
        super(msg);
    }

    public UnknownException(String msg, Throwable e) {
        super(msg, e);
    }
    
}
