package eblo.common.exception;

import org.springframework.http.HttpStatus;
public class EbloArrayIndexOutOfBoundsException extends AbstractEbloBaseException {

    private static final long serialVersionUID = 1L;

    public EbloArrayIndexOutOfBoundsException() {
        super();
    }

    public EbloArrayIndexOutOfBoundsException(Throwable e) {
        super(e);
    }

    public EbloArrayIndexOutOfBoundsException(String errorMessge) {
        super(errorMessge);
    }

    public EbloArrayIndexOutOfBoundsException(String errorMessge, Throwable e) {
        super(errorMessge, e);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
