package vadintevem.base.functional;

public interface Effect<T> {
    void apply(T t);
}
