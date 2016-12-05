import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel {

    ResourceLoader resLoader;
    Planet planet;
    Player player;
    BufferedImage img;

    public Game(JFrame frame) {
        //img = resLoader.loadImage("ball2.png");
        planet = new Planet(300, 200, 100, ResourceLoader.loadImage("circle.png"));
        player = new Player(300, 200, 100, ResourceLoader.loadImage("ball2.png"));
    }

    public void paint(Graphics g) {
        super.paint(g);
        planet.paint(g);
        player.paint(g);
    }

    public void move() {
    }
}
