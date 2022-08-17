package teste.servicepack.security.logic.Exception;

import teste.servicepack.security.logic.Exception.AccessDeniedException;

/**
 * Created by jorgemachado on 23/10/18.
 */
public class NotAuthenticatedException extends AccessDeniedException
{

    public NotAuthenticatedException() {
        super();
    }

    public NotAuthenticatedException(String message) {
        super(message);
    }

    public NotAuthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAuthenticatedException(Throwable cause) {
        super(cause);
    }

    public NotAuthenticatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "NotAuthenticatedException";
    }
}
