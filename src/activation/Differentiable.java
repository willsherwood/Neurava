package activation;

import java.util.function.Function;

public interface Differentiable<T> {

    /**
     * @return the derivative of the function
     */
    Function<T, T> derivative ();

    /**
     * @return the numerical value of the derivative
     * of the function when applied with the input 'value'.
     * This is a less expensive call than derivative().
     */
    T apply (T value);
}
