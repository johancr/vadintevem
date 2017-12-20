package vadintevem.message.selector;

import static vadintevem.base.functional.Attempt.attempt;

public enum Algorithm {

    RANDOM,
    POPULAR,
    UNREAD;

    public static Algorithm parse(String algorithm) {
        return attempt(() -> valueOf(algorithm)).orElse(RANDOM);
    }
}
