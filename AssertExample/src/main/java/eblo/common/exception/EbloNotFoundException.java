package eblo.common.exception;

import org.springframework.http.HttpStatus;

public class EbloNotFoundException extends AbstractEbloBaseException {

    private static final long serialVersionUID = 1L;

    public EbloNotFoundException() {
        super();
    }

    public EbloNotFoundException(Throwable e) {
        super(e);
    }

    public EbloNotFoundException(String errorMessge) {
        super(errorMessge);
    }

    public EbloNotFoundException(String errorMessge, Throwable e) {
        super(errorMessge, e);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
