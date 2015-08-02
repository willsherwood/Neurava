import network.Network;
import network.RandomNetworkFactory;

public class Main {

    public static void main (String[] args) {
        Network X = new RandomNetworkFactory().create(10, 2, 15, 15, 1);
        for (int i=0; i<=1; i++)
            for (int j=0; j<=1; j++)
                System.out.printf("(%d, %d): %f %n", i, j, X.forwardPropogate(new double[]{i, j})[2][0]);
    }
}
