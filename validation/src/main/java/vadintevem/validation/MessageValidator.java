package vadintevem.validation;

import vadintevem.base.functional.Validation;
import vadintevem.entities.Message;

public interface MessageValidator {

    Validation<String, Message> validate(Message message);
}
