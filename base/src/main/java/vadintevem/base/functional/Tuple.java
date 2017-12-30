package vadintevem.base.functional;

import java.util.function.Function;

public class Tuple<T, T2> {

    public final T _1;
    public final T2 _2;

    public static <T, T2> Tuple<T, T2> of(T x, T2 y) {
        return new Tuple<>(x, y);
    }

    public static <T, T2> Function<T, Function<T2, Tuple<T, T2>>> create() {
        return x -> y -> of(x, y);
    }

    private Tuple(T x, T2 y) {
        this._1 = x;
        this._2 = y;
    }
}
