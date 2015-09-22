package network;

import activation.Activation;
import activation.Sigmoid;
import activation.Sine;
import logistic.LogisticCostFunction;
import matrix.Linear;
import matrix.Operations;

import java.util.Arrays;

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
        for (int i=0; i<input.length; i++) {
            train(input[i], expected[i]);
        }
    }

    private void train(double X, double Y) {
        double cost = Math.pow(Y - Operations.last(forwardPropogate(new double[]{X}))[0], 2);
        System.out.println(cost);
        for (int i=0; i<16; i++)
            for (int j=0; j<theta.length; j++)
                for (int k=0; k<theta[j].length; k++)
                    for (int l=0; l<theta[j][k].length; l++) {
                        double save = theta[j][k][l];
                        theta[j][k][l] = Math.random();
                        double cost2 = Math.pow(Y - Operations.last(forwardPropogate(new double[]{X}))[0], 2);
                        if (cost2 > cost)
                            theta[j][k][l] = save;
                    }
        System.out.println(Math.pow(Y - Operations.last(forwardPropogate(new double[]{X}))[0], 2));

    }
}
