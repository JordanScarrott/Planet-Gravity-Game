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
    private float height;
    private int velocity;
    private int acceleration;
    private int radius;
    private int angle = 135;
    private boolean moving = false;
    private boolean jumping = false;

    public Player(MyVector location, int radius, BufferedImage imgPlayer) {
        this.pLocation = location;
        this.imgPlayer = imgPlayer;
        this.radius = radius;
    }

    public Player(float x, float y, int radius, BufferedImage imgPlayer) {
        this(new MyVector(x, y), radius, imgPlayer);
    }
    //Getters and Setters
    public boolean getJumping() {
        return jumping;
    }
    //Functions
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(pLocation.x - height, pLocation.y - height);
        transform.rotate(Math.toRadians(angle), radius + height , radius + height) ;
        g2d.drawImage(imgPlayer, transform, this);
    }
    public void move() {
        if (moving) angle += 4;
        if(jumping){
            height+=4;
            checkCollision();
        }
    }
    public void checkCollision(){
        if(MyVector.distanceSq(new MyVector((float)(400 + (100 + 42 + height)*Math.cos(Math.toRadians(angle - 135))),(float) (300 + (100 + 42 + height)*Math.sin(Math.toRadians(angle - 135)))), new MyVector(100, 100))<121*121){
            land();

        }
    }
    public void land(){
        angle = (int) (Math.atan2((int) (300 + (100 + 42 + height)*Math.sin(Math.toRadians(angle - 135)))-100, (int) (400 + (100 + 42 + height)*Math.cos(Math.toRadians(angle - 135)))-100)*180/3.14596) + 135;
        jumping = false;
        pLocation.x = 0;
        pLocation.y = 0;
        height = 0;
        moving = true;

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
        if(!jumping) {
            if (keyCode == KeyEvent.VK_A) {
                moving = true;
            }
        }
        if (keyCode == KeyEvent.VK_J) {
            moving = false;
            jumping = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public MyVector getpLocation() {
        return pLocation;
    }

    public void setpLocation(MyVector pLocation) {
        this.pLocation = pLocation;
    }
}
