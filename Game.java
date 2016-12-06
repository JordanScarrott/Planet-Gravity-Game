import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements KeyListener {

    ResourceLoader resLoader;
    BufferedImage img;

    public Game(JFrame frame) {
        frame.addKeyListener(this);
        img = resLoader.loadImage("circle.png");
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 75, 45, null);
    }

    public void move() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Main.gameState = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
