import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel {

    ResourceLoader resLoader;
    Planet planet;
    BufferedImage img;

    public Game(JFrame frame, ResourceLoader in_resLoader) {

        resLoader = in_resLoader;
        //img = resLoader.loadImage("ball2.png");
        planet = new Planet(300, 200, 100, in_resLoader.loadImage("circle.png"));
    }

    public void paint(Graphics g) {
        super.paint(g);
        planet.paint(g);
    }

    public void move() {
    }
}
