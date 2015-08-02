package logistic;

public interface CostFunction {
    double cost (double[][] output, double[][] expected, double[][][] theta);
}
