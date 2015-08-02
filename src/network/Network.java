package network;

public interface Network {

    /**
     * @return the number of layers in this network
     */
    int layers ();

    /**
     * @return the number of nodes in layer 'layer'.
     * Note: this is a 1-based reading, and the bias unit
     * does not count as a separate node.
     */
    int nodes (int layer);

    /**
     * @return the values generated as a result of running the
     * network with the given input node values.
     */
    double[][] forwardPropogate (double[] inputState);

    /**
     * tells this network to use a new value of theta
     */
    void supplyTheta(double[][][] theta);

    /**
     *
     */
    double[][] error();

    /**
     * @return the parameters of this neural network
     */
    double[][][] theta();
}
