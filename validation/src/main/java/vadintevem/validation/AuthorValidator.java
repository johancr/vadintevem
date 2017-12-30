package vadintevem.validation;

import vadintevem.base.functional.Validation;
import vadintevem.entities.Author;

public interface AuthorValidator {

    Validation<String, Author> validate(Author author);
}
