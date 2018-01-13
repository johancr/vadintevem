package vadintevem.validation;

import vadintevem.base.functional.Validation;
import vadintevem.entities.User;

public interface AuthorValidator {

    Validation<String, User> validate(User user);
}
