package tests;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import network.Network;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ColoredNetworkVisualizer extends Stage {

    private PixelWriter writer;
    private Long hash = 0L;

    protected abstract Network network ();

    public Pane image () {

        for (int y = 0; y <= 1; y++)
            for (int x = 0; x <= 1; x++) {
                double[][] T = network().forwardPropogate(new double[]{x, y});
                double[] out = T[T.length - 1];
                System.out.printf("(%d, %d): [%.4f, %.4f, %.4f] %n", x, y, out[0], out[1], out[2]);
            }

        WritableImage image = new WritableImage(1024, 1024);
        this.writer = image.getPixelWriter();
        List<Thread> threads = new ArrayList<>();
        int mag = 1;
        for (int i = 0; i <= 1; i++)
            for (int j = 0; j <= 1; j++) {
                final int I = i;
                final int J = j;
                Thread a = new Thread(() -> {
                    for (int y = mag * 512 * I; y < mag * 512 * I + mag * 512; y++) {
                        for (int x = 512 * J * mag; x < 512 * J * mag + 512 * mag; x++) {
                            double[][] layers = network().forwardPropogate(new double[]{x / 1024. / mag / 16., y / 1024. / mag / 16.});
                            double[] output = layers[layers.length - 1];
                            Color color = new Color(output[0], output[1], output[2], 1);
                            writer().setColor(x, y, color);
                        }
                    }
                });
                a.start();
                threads.add(a);
            }
        threads.stream().forEach(a -> {
            try {
                a.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Pane root = new StackPane();
        root.getChildren().add(new ImageView(image));
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File("A:/newnets/" + hashCode() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    private synchronized PixelWriter writer () {
        return writer;
    }

    @Override
    public int hashCode () {
        if (hash == 0)
            hash = System.currentTimeMillis();
        return hash.hashCode();
    }
}
