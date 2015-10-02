package spring.rest.poc.exception;


public final class DuplicateRecordException extends RuntimeException {

    /**
     * For HTTP 409 errors
     */

    public DuplicateRecordException() {
        super();
    }

    public DuplicateRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateRecordException(String message) {
        super(message);
    }

    public DuplicateRecordException(Throwable cause) {
        super(cause);
    }
}
