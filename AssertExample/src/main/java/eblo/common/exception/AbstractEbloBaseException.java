package eblo.common.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractEbloBaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AbstractEbloBaseException() {
        super();
    }

    public AbstractEbloBaseException(String msg) {
        super(msg);
    }

    public AbstractEbloBaseException(Throwable e) {
        super(e);
    }

    public AbstractEbloBaseException(String errorMessge, Throwable e) {
        super(errorMessge, e);
    }

    public abstract HttpStatus getHttpStatus();

}
