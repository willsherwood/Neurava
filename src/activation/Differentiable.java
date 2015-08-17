package activation;

import java.util.function.DoubleFunction;

public interface Differentiable {

    /**
     * @return the derivative of the function
     */
    DoubleFunction<Double> derivative ();

    /**
     * @return the numerical value of the derivative
     * of the function when applied with the input 'value'.
     * This is a less expensive call than derivative().
     */
    Double apply (Double value);

    default double[] apply (double[] values) {
        double[] out = new double[values.length];
        for (int i = 0; i < out.length; i++)
            out[i] = apply(values[i]);
        return out;
    }

}
