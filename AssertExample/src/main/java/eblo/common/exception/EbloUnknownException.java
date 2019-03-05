package eblo.common.exception;

import org.springframework.http.HttpStatus;

public  class EbloUnknownException extends AbstractEbloBaseException {

    private static final long serialVersionUID = 1L;

    public EbloUnknownException() {
        super();
    }

    public EbloUnknownException(Throwable e) {
        super(e);
    }

    public EbloUnknownException(String errorMessge) {
        super(errorMessge);
    }

    public EbloUnknownException(String errorMessge, Throwable e) {
        super(errorMessge, e);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}