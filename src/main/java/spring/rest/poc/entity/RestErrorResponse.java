package spring.rest.poc.entity;


public class RestErrorResponse {

    public final String detail;
    public final String error;

    public RestErrorResponse(Exception ex, String detail) {
        this.error = ex.getMessage();
        this.detail = detail;
    }
}
