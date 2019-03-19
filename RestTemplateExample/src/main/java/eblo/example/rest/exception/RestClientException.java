package eblo.example.rest.exception;

public class RestClientException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public RestClientException() {
        super();
    }

    public RestClientException(String msg) {
        super(msg);
    }

    public RestClientException(String msg, Throwable e) {
        super(msg, e);
    }
    
}
