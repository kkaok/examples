package eblo.common.exception;

import org.springframework.http.HttpStatus;

public class EbloSystemException extends AbstractEbloBaseException {

    private static final long serialVersionUID = 1L;

    public EbloSystemException() {
        super();
    }

    public EbloSystemException(Throwable e) {
        super(e);
    }

    public EbloSystemException(String errorMessge) {
        super(errorMessge);
    }

    public EbloSystemException(String errorMessge, Throwable e) {
        super(errorMessge, e);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
