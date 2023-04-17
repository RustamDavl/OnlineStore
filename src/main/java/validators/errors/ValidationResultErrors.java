package validators.errors;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResultErrors {


    @Getter
    private final List<String> errorList = new ArrayList<>();

    public void add(String errorMessage) {
        errorList.add(errorMessage);
    }

    public boolean isEmpty() {
        return errorList.isEmpty();
    }
}
