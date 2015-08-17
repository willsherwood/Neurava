package activation;

import java.util.function.DoubleFunction;

public class Sine implements Activation, Differentiable {

    @Override
    public double activate (double z) {
        return (Math.sin(z) + 1) / 2;
    }

    @Override
    public DoubleFunction<Double> derivative () {
        return a -> Math.cos(a) / 2;
    }

    @Override
    public Double apply (Double value) {
        return Math.cos(value) / 2;
    }
}
