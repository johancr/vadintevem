package vadintevem.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public class ErrorDto {

    private final List<String> errors;

    public ErrorDto(@JsonProperty("errors") List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public static ErrorDto from(List<String> errors) {
        return new ErrorDto(errors);
    }

    public static ErrorDto from(String error) {
        return from(Collections.singletonList(error));
    }
}
