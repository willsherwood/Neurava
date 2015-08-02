package network;

import activation.Activation;
import matrix.Linear;
import activation.Sigmoid;

public class NeuralNetwork implements Network {

    private double[][][] theta;
    private Activation activation;

    /**
     * @param inputs the number of input neurons
     * @param hidden the number of hidden layers
     * @param hiddenSize the number of neurons in each hidden layer
     * @param outputs the number of output neurons
     */
    public NeuralNetwork (int inputs, int hidden, int hiddenSize, int outputs) {
        this.activation = new Sigmoid();
    }

    @Override
    public int layers () {
        return theta.length + 1;
    }

    @Override
    public int nodes (int layer) {
        return 0;
    }

    @Override
    public double[][] forwardPropogate (double[] inputs) {
        double[][] outputs = new double[theta.length][];
        for (int i = 0; i < theta.length; i++) {
            outputs[i] = Linear.apply(theta[i], inputs);
            outputs[i] = activation.activate(outputs[i]);
            inputs = outputs[i];
        }
        return outputs;
    }

    @Override
    public void supplyTheta (double[][][] theta) {
        this.theta = theta;
    }

    @Override
    public double[][][] theta () {
        return theta;
    }

    @Override
    public double[][] error () {
        throw new UnsupportedOperationException();
    }
}
