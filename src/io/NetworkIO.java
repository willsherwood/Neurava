package io;

import network.Network;
import network.NeuralNetwork;

import java.io.*;

public class NetworkIO {

    public static void saveNetwork (Network X, File file) {
        try {
            file.createNewFile();
            FileOutputStream F = new FileOutputStream(file);
            ObjectOutputStream O = new ObjectOutputStream(F);
            O.writeObject(X.theta());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Network loadNetwork (File file) {
        try {
            double[][][] theta = (double[][][]) new ObjectInputStream(new FileInputStream(file)).readObject();
            Network x = new NeuralNetwork();
            x.supplyTheta(theta);
            return x;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
