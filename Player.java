import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends JPanel implements KeyListener {
    private BufferedImage[] imgPlayer = null;
    private int currentSprite;
    private long lastTime = 0;
    private double delay = 0.5;
    private MyVector pLocation;
    private MyVector center;
    private float height;
    private int velocity;
    private int acceleration;
    private boolean clockwise;
    private int radius;
    private int angle = 135;
    private boolean moving = false;
    private boolean jumping = false;

    public Player(MyVector location, int radius, BufferedImage[] imgPlayer) {
        this.pLocation = location;
        this.imgPlayer = imgPlayer;
        this.radius = radius;
    }

    public Player(float x, float y, int radius, BufferedImage[] imgPlayer) {
        this(new MyVector(x, y), radius, imgPlayer);
    }
    //Getters and Setters
    public boolean getJumping() {
        return jumping;
    }
    //Functions
    public void paint(Graphics g) {
        long newTime = System.currentTimeMillis();
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(pLocation.x - height, pLocation.y - height);
        transform.rotate(Math.toRadians(angle), radius + height , radius + height) ;
        transform.rotate(Math.toRadians(-45), 21, 21);
        if (newTime-lastTime>=delay) {
            lastTime = newTime;
            if (currentSprite < 3) {
                currentSprite++;
            }
            else {
                currentSprite = 0;
            }
        }
        g2d.drawImage(imgPlayer[currentSprite], transform, this);
    }
    public void move() {
        if (moving)
            if (clockwise) {
                angle += 4;
            }
        else {
                angle -=4;
            }
        if(jumping) {
            height+=4;
        }
    }
    public boolean checkCollision(float planetX, float planetY){
        if(MyVector.distanceSq(new MyVector((float)(pLocation.x + 100 + (100 + 42 + height)*Math.cos(Math.toRadians(angle - 135))),(float) (pLocation.y + 100 + (100 + 42 + height)*Math.sin(Math.toRadians(angle - 135)))), new MyVector(planetX + 100, planetY + 100))<121*121){
            land(planetX, planetY);
            return true;
        }
        return false;
    }
    public void land(float planetX, float planetY){
        angle = (int) (Math.atan2((int) (300 + (100 + 42 + height)*Math.sin(Math.toRadians(angle - 135)))-100, (int) (400 + (100 + 42 + height)*Math.cos(Math.toRadians(angle - 135)))-100)*180/3.14596) + 135;
        jumping = false;
        pLocation.x = planetX;
        pLocation.y = planetY;
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

            if (keyCode == KeyEvent.VK_RIGHT) {
                moving = true;
                clockwise = true;
            }

            if (keyCode == KeyEvent.VK_LEFT) {
                moving = true;
                clockwise = false;
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
