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
    private float vx;
    private float vy;

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
        if(!jumping){
            transform.rotate(Math.toRadians(angle), radius + height, radius + height);
            g2d.drawImage(imgPlayer, transform, this);
        }
        else {
            g.drawImage(imgPlayer, (int)pLocation.x + (int)vx,(int)pLocation.y + (int)vy,null);
        }
    }
    public void move() {
        if (moving) angle += 4;
        if(jumping){
            vx += (float)(5*Math.cos(Math.toRadians(angle-135)));
            vy += (float)(5*Math.sin(Math.toRadians(angle-135)));
            checkCollision();
        }
    }
    public void checkCollision(){
        if(MyVector.distanceSq(new MyVector(pLocation.x + 21 + vx, pLocation.y + 21 + vy), new MyVector(100, 100))<121*121){
            jumping = false;
            pLocation.x = 0;
            pLocation.y = 0;
            vx = 0;
            vy = 0;
            angle += 180;
        }
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
        if (keyCode == KeyEvent.VK_J) {
            moving = false;
            if(!jumping)findNewPosition();
            jumping = true;
        }
    }
    public void findNewPosition(){
        pLocation.x = 400 + (float)(121*Math.cos(Math.toRadians(angle-135)));
        pLocation.y = 300 + (float)(121*Math.sin(Math.toRadians(angle-135)));

        System.out.println(angle);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            moving = false;
        }
    }

    public MyVector getpLocation() {
        return pLocation;
    }

    public void setpLocation(MyVector pLocation) {
        this.pLocation = pLocation;
    }
}
