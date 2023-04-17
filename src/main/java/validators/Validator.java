package validators;

import validators.errors.ValidationResultErrors;

public interface Validator<T> {

    ValidationResultErrors isValid(T object);
}
