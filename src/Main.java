import io.NetworkIO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import matrix.Operations;
import network.Network;
import network.RandomNetworkFactory;
import statistics.StandardDeviation;
import tests.ColoredNetworkVisualizer;

import java.io.File;

public class Main extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
//        final Network theta = new RandomNetworkFactory().create(5.2, 2, 30, 20, 3);
//        final Network theta = choose(8, 40, 40, 1000);
        final Network theta = NetworkIO.loadNetwork(new File("A:/newnets/253317999.obj"));
        ColoredNetworkVisualizer visualizer = new ColoredNetworkVisualizer() {
            @Override
            protected Network network () {
                return theta;
            }
        };
        System.out.println(rating(theta, 3));
        primaryStage.setScene(new Scene(visualizer.image(), 1024, 1024));
        primaryStage.show();
        NetworkIO.saveNetwork(theta, new File("A:/newnets/" + visualizer.hashCode() + ".obj"));
        System.out.println("done");
        System.out.println(rating(theta, 16));
    }

    private Network choose (double ep, int A, int B, int iterations) {
        Network best = null;
        double max = -1;
        for (int i = 0; i < iterations; i++) {
            Network test = new RandomNetworkFactory().create(ep, 2, A, B, 3);
            double current = rating(test, 18);
            if (current > max) {
                max = current;
                best = test;
            }
        }
        return best;
    }

    private double rating (Network net, int points) {
        double[][] rates = new double[3][points * points];
        for (int i = 0; i < points; i++)
            for (int j = 0; j < points; j++) {
                double[] out = Operations.last(net.forwardPropogate(new double[]{i / (double) points, j / (double) points}));
                for (int k = 0; k < 3; k++)
                    rates[k][i * points + j] = out[k];
            }
        double sum = 0;
        for (int i = 0; i < 3; i++)
            sum += StandardDeviation.standardDeviation(rates[i]);
        return sum;
    }
}
