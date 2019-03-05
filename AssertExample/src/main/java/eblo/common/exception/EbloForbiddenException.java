package eblo.common.exception;

import org.springframework.http.HttpStatus;

public  class EbloForbiddenException extends AbstractEbloBaseException {

    private static final long serialVersionUID = 1L;

    public EbloForbiddenException() {
        super();
    }

    public EbloForbiddenException(Throwable e) {
        super(e);
    }

    public EbloForbiddenException(String errorMessge) {
        super(errorMessge);
    }

    public EbloForbiddenException(String errorMessge, Throwable e) {
        super(errorMessge, e);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
