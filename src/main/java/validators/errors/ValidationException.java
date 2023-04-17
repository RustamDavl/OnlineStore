package validators.errors;

import lombok.Getter;

import java.util.List;

public class ValidationException extends RuntimeException{

    @Getter
    private final List<String> list;

    public ValidationException(List<String> list) {
        this.list = list;
    }

}
