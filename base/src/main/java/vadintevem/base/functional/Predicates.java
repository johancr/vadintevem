package vadintevem.base.functional;

import java.util.Collection;
import java.util.function.Predicate;

public class Predicates {

    public static <T> Predicate<T> notIn(Collection<T> ts) {
        return t -> !ts.contains(t);
    }
}
