package activation;

import java.util.function.Function;

public class Sine implements Activation, Differentiable<Double> {

    @Override
    public double activate (double z) {
        return (Math.sin(z) + 1) / 2;
    }

    @Override
    public Function<Double, Double> derivative () {
        return a -> Math.cos(a) / 2;
    }

    @Override
    public Double apply (Double value) {
        return Math.cos(value) / 2;
    }
}
