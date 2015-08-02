package network;

public class RandomNetworkFactory {

    /**
     * @param epsilon    the largest value, in magnitude, of each theta
     * @param inputs     the number of input neurons
     * @param hidden     the number of hidden layers
     * @param hiddenSize the number of neurons in each hidden layer
     * @param outputs    the number of output neurons
     */
    public Network create (double epsilon, int inputs, int hidden, int hiddenSize, int outputs) {
        Network net = new NeuralNetwork(inputs, hidden, hiddenSize, outputs);
        double[][][] theta = new double[hidden + 1][][];
        for (int i = 0; i <= hidden; i++) {
            int nodes = i == 0 ? inputs : i == hidden ? outputs : hidden;
            int current = i == hidden ? outputs : hidden;
            theta[i] = new double[current][];
            for (int j=0; j<current; j++) {
                theta[i][j] = new double[nodes + 1];
                for (int k=0; k<=nodes; k++) {
                    theta[i][j][k] = Math.random() * epsilon * 2 - epsilon;
                }
            }
        }
        net.supplyTheta(theta);
        return net;
    }
}
