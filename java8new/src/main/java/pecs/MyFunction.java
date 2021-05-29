package pecs;

import java.util.Objects;

public interface MyFunction<T, R> {

    /**
     * Applies this MyFunction to the given argument.
     *
     * @param t the MyFunction argument
     * @return the MyFunction result
     */
    R apply(T t);

    /**
     * Returns a composed MyFunction that first applies the {@code before}
     * MyFunction to its input, and then applies this MyFunction to the result.
     * If evaluation of either MyFunction throws an exception, it is relayed to
     * the caller of the composed MyFunction.
     *
     * @param <V> the type of input to the {@code before} MyFunction, and to the
     *           composed MyFunction
     * @param before the MyFunction to apply before this MyFunction is applied
     * @return a composed MyFunction that first applies the {@code before}
     * MyFunction and then applies this MyFunction
     * @throws NullPointerException if before is null
     *
     * @see #andThen(MyFunction)
     */
    default <V> MyFunction<V, R> compose(MyFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed MyFunction that first applies this MyFunction to
     * its input, and then applies the {@code after} MyFunction to the result.
     * If evaluation of either MyFunction throws an exception, it is relayed to
     * the caller of the composed MyFunction.
     *
     * @param <V> the type of output of the {@code after} MyFunction, and of the
     *           composed MyFunction
     * @param after the MyFunction to apply after this MyFunction is applied
     * @return a composed MyFunction that first applies this MyFunction and then
     * applies the {@code after} MyFunction
     * @throws NullPointerException if after is null
     *
     * @see #compose(MyFunction)
     */
    default <V> MyFunction<T, V> andThen(MyFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * Returns a MyFunction that always returns its input argument.
     *
     * @param <T> the type of the input and output objects to the MyFunction
     * @return a MyFunction that always returns its input argument
     */
    static <T> MyFunction<T, T> identity() {
        return t -> t;
    }
}
