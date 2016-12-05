import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel {

    ResourceLoader resLoader;
    BufferedImage img;

    public Game(JFrame frame, ResourceLoader in_resLoader) {

        resLoader = in_resLoader;
        img = resLoader.loadImage("ball2.png");
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    public void move() {
    }
}
