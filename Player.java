import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends JPanel implements KeyListener {
    private BufferedImage imgPlayer = null;
    private MyVector pLocation;
    private MyVector center;
    private int height;
    private int velocity;
    private int acceleration;
    private int radius;
    private int angle;
    private boolean moving = false;

    public Player(MyVector location, int radius, BufferedImage imgPlayer) {
        this.pLocation = location;
        this.imgPlayer = imgPlayer;
        this.radius = radius;
    }

    public Player(float x, float y, int radius, BufferedImage imgPlayer) {
        this(new MyVector(x, y), radius, imgPlayer);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(pLocation.x, pLocation.y);
        transform.rotate(Math.toRadians(angle), radius, radius);
        g2d.drawImage(imgPlayer, transform, this);
    }

    public void move() {
        if (moving) angle += 4;
    }

    public void addKeyListener(JFrame frame) {
        frame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            moving = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            moving = false;
        }
    }

}
