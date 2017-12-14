package vadintevem.validation;

import vadintevem.base.functional.Validation;
import vadintevem.entities.Message;

import static vadintevem.base.functional.Validation.failure;
import static vadintevem.base.functional.Validation.success;

public class MessageValidator {

    public Validation<String, Message> validate(Message message) {
        return notNullOrEmpty(message)
                .ap(noLongerThan(140, message)
                        .ap(success(x -> y -> x)));
    }

    private Validation<String, Message> notNullOrEmpty(Message message) {
        return message.getContent() != null && message.getContent().length() > 0
                ? success(message)
                : failure("Message cannot be empty");
    }

    private Validation<String, Message> noLongerThan(int limit, Message message) {
        return message.getContent() != null && message.getContent().length() <= limit
                ? success(message)
                : failure("Message cannot be longer than " + limit + " characters");
    }
}
