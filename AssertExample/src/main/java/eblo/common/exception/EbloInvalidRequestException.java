package eblo.common.exception;

import org.springframework.http.HttpStatus;

public  class EbloInvalidRequestException extends AbstractEbloBaseException {

    private static final long serialVersionUID = 1L;

    public EbloInvalidRequestException() {
        super();
    }

    public EbloInvalidRequestException(Throwable e) {
        super(e);
    }

    public EbloInvalidRequestException(String errorMessge) {
        super(errorMessge);
    }

    public EbloInvalidRequestException(String errorMessge, Throwable e) {
        super(errorMessge, e);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}