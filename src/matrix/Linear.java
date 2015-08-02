package matrix;

public class Linear {

    public static double[] apply(double[][] theta, double[] inputs) {
        double[] sum = new double[theta.length];
        for (int i=0; i<theta.length; i++) {
            for (int j=1; j<theta[i].length; j++) {
                sum[i] += theta[i][j] * inputs[j-1];
            }
            sum[i] += theta[i][0];
        }
        return sum;
    }
}
