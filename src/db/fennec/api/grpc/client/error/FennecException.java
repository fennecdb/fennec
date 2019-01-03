package db.fennec.api.grpc.client.error;

public class FennecException extends Exception {

    private int code;
    private String message;
    private Throwable internal;

    public FennecException(int code, String message) {
        this(code, message, null);
    }

    public FennecException(int code, String message, Throwable internal) {
        this.code = code;
        this.message = message;
        this.internal = internal;
    }
}
