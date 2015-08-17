package network;

import activation.Sigmoid;
import matrix.Linear;
import matrix.Operations;

public class NeuralNetwork implements Network {

    private double[][][] theta;
    private Sigmoid activation;

    /**
     * @param inputs the number of input neurons
     * @param hidden the number of hidden layers
     * @param hiddenSize the number of neurons in each hidden layer
     * @param outputs the number of output neurons
     */
    public NeuralNetwork (int inputs, int hidden, int hiddenSize, int outputs) {
        this();
    }

    public NeuralNetwork () {
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

    public void train(double[] input, double[] expected) {
        double[][] out = forwardPropogate(input);
        double[] observed = Operations.last(out);

        // Calculate Total Error
        double totalError = 0;
        for (int i=0; i<observed.length; i++) {
            double t = input[i] - expected[i];
            totalError += t * t;
        }
        totalError /= 2;

        // d etotal / d out
        double[] outputError1 = new double[observed.length];
        for (int i=0; i<observed.length; i++)
            outputError1[i] = observed[i] - expected[i];

        // d out o1 / d net o1
        double[] outputError2 = activation.apply(Linear.apply(Operations.last(theta), out[out.length - 2]));

        // dout o1 / d net o1
        double[] outputError3 =
    }
}
