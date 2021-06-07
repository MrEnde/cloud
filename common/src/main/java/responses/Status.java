package responses;


public enum Status {
    OK(200),
    CREATED(201),
    ACCEPTED(203),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    COMMAND_NOT_ALLOWED(405),
    INTERNAL_SERVER_ERROR(500);

    Status(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code=" + code +
                '}';
    }
}
