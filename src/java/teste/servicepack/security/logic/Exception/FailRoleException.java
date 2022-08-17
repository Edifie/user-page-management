package teste.servicepack.security.logic.Exception;

import teste.servicepack.security.logic.Exception.AccessDeniedException;

/**
 * Created by jorgemachado on 23/10/18.
 */
public class FailRoleException extends AccessDeniedException
{

    public FailRoleException() {
        super();
    }

    public FailRoleException(String message) {
        super(message);
    }

    public FailRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailRoleException(Throwable cause) {
        super(cause);
    }

    public FailRoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "FailRoleException";
    }
}
