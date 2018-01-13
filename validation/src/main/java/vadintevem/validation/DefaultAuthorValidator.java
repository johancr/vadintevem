package vadintevem.validation;

import vadintevem.base.functional.Validation;
import vadintevem.entities.User;

import static vadintevem.base.functional.Validation.failure;
import static vadintevem.base.functional.Validation.success;

public class DefaultAuthorValidator implements AuthorValidator {

    @Override
    public Validation<String, User> validate(User user) {
        return notNullOrEmpty(user);
    }

    private Validation<String, User> notNullOrEmpty(User user) {
        return user.getUsername() != null && user.getUsername().length() > 0
                ? success(user)
                : failure("Author cannot be empty");
    }
}
