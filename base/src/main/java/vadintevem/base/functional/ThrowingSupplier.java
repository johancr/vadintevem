package vadintevem.base.functional;

public interface ThrowingSupplier<T> {

    T get() throws Exception;
}
