package in.spring.model;

public class ErrorDto {
    private String error;
    private String path;

    public ErrorDto(String error, String path) {
        this.error = error;
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
