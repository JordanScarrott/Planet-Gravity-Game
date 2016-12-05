import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends JPanel{
    private BufferedImage imgPlayer = null;
    private MyVector pLocation;
    private MyVector center;
    private int height;
    private int velocity;
    private int acceleration;
    private int radius;
    private int angle;

    public Player(MyVector location, float radius, BufferedImage imgPlayer){
        this.pLocation = location;
        this.imgPlayer = imgPlayer;
    }
    public Player(float x, float y, float radius, BufferedImage imgPlayer) {
        this(new MyVector(x, y), radius, imgPlayer);
    }

    public void paint(Graphics g){
        super.paint(g);
        AffineTransform transform = new AffineTransform();
        Graphics2D g2d = (Graphics2D)g;
        transform.translate(pLocation.x,pLocation.y);
        transform.rotate(Math.toRadians(angle), radius, radius);
        g2d.drawImage(imgPlayer, transform, this);
    }

}
