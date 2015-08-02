package logistic;

public class LogisticCostFunction implements CostFunction {

    /**
     * regularization constant
     */
    public static double lambda = 1;

    @Override
    public double cost (double[][] output, double[][] expected, double[][][] theta) {
        int m = expected.length; // Number of training sets
        double J = 0;
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < output[i].length; k++) {
                double x = output[i][k], y = expected[i][k];
                J -= (y * Math.log(x) + (1 - y) * Math.log(1 - x));
            }
        }
        return J / m + regularization(theta) * lambda / 2 / m; // average cost added to
                                                               // the regularization term
    }

    private double regularization(double[][][] X) {
        double sum = 0;
        for (int i = 0; i < X.length; i++)
            for (int j = 0; j < X[i].length; j++)
                for (int k = 1; k < X[i][j].length; k++) // do not regularize constant term
                    sum += Math.pow(X[i][k][k], 2);
        return sum;
    }
}
