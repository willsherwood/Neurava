package activation;

import java.util.function.DoubleFunction;

public class Sigmoid implements Activation, Differentiable {

    @Override
    public double activate (double z) {
        return 1. / (1 + Math.exp(-z));
    }

    @Override
    public DoubleFunction<Double> derivative () {
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
