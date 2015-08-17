package statistics;

public class StandardDeviation {

    public static double standardDeviation(double[] X) {
        double sum = 0;
        double mean = Mean.mean(X);
        for (double x : X) {
            double t = x - mean;
            sum += t * t;
        }
        return Math.sqrt(sum / (X.length - 1));
    }
}
