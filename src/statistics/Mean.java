package statistics;

public class Mean {

    public static double mean (double[] X) {
        double sum = 0;
        for (double x : X)
            sum += x;
        return sum / X.length;
    }
}
