package activation;

import java.util.function.Function;

public class Sigmoid implements Activation, Differentiable<Double> {

    @Override
    public double activate (double z) {
        return 1. / (1 + Math.exp(-z));
    }

    @Override
    public Function<Double, Double> derivative () {
        return a -> {
            double partial = 1 + Math.exp(-a);
            return (-partial + 1) / Math.pow(partial, 2);
        };
    }

    @Override
    public Double apply (Double value) {
        double partial = 1 + Math.exp(-value);
        return (-partial + 1) / Math.pow(partial, 2);
    }
}
