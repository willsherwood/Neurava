package activation;

public interface Activation {

    /**
     * @return the activated value Z
     */
    double activate (double z);

    default double[] activate (double[] z) {
        for (int i = 0; i < z.length; i++)
            z[i] = activate(z[i]);
        return z;
    }

    default double[][] activate (double[][] z) {
        for (int i = 0; i < z.length; i++)
            for (int j = 0; j < z[i].length; j++)
                z[i][j] = activate(z[i][j]);
        return z;
    }
}
