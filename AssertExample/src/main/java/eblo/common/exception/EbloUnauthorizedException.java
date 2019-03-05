package eblo.common.exception;

import org.springframework.http.HttpStatus;

public  class EbloUnauthorizedException extends AbstractEbloBaseException {

    private static final long serialVersionUID = 1L;

    public EbloUnauthorizedException() {
        super();
    }

    public EbloUnauthorizedException(Throwable e) {
        super(e);
    }

    public EbloUnauthorizedException(String errorMessge) {
        super(errorMessge);
    }

    public EbloUnauthorizedException(String errorMessge, Throwable e) {
        super(errorMessge, e);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}