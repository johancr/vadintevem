package vadintevem.validation;

import vadintevem.base.functional.Validation;
import vadintevem.entities.Author;

import static vadintevem.base.functional.Validation.failure;
import static vadintevem.base.functional.Validation.success;

public class DefaultAuthorValidator implements AuthorValidator {

    @Override
    public Validation<String, Author> validate(Author author) {
        return notNullOrEmpty(author);
    }

    private Validation<String, Author> notNullOrEmpty(Author author) {
        return author.getId() != null && author.getId().length() > 0
                ? success(author)
                : failure("Author cannot be empty");
    }
}
