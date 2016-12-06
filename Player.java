import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends JPanel implements KeyListener {
    private BufferedImage[] imgPlayer = null;
    private BufferedImage test = null;
    private MyVector pLocation;
    private MyVector center;
    private int currentSprite;
    private long lastTime = 0;
    private double delay = 100;
    private float height;
    private int velocity;
    private int acceleration;
    private int radius;
    private int angle = 135;
    private boolean moving = false;
    private boolean jumping = false;
    private int[] keys;
    private int accelerationtimer;

    public Player(MyVector location, int radius, BufferedImage[] imgPlayer, int[] keys, BufferedImage test) {
        this.pLocation = location;
        this.imgPlayer = imgPlayer;
        this.radius = radius;
        this.keys = keys;
        this.test = test;
        acceleration = 0;
        accelerationtimer = 0;
    }

    public Player(float x, float y, int radius, BufferedImage[] imgPlayer, int[] keys, BufferedImage test) {
        this(new MyVector(x, y), radius, imgPlayer, keys, test);
    }
    //Getters and Setters
    public boolean getJumping() {
        return jumping;
    }
    public int getAngle(){
        return angle;
    }
    public int getAcceleration(){
        return acceleration;
    }
    //Functions
    public void paint(Graphics g){
        long newTime = System.currentTimeMillis();
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(pLocation.x - height, pLocation.y - height);
        transform.rotate(Math.toRadians(angle), radius + height , radius + height) ;
        transform.rotate(Math.toRadians(-45), 21,21);
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
    public void move(int planetID) {
        fixAngle();
        if (moving) {
            angle += acceleration;
            if(accelerationtimer > 100){
                if(planetID != 3){
                    if (acceleration < 7) {
                        acceleration++;
                    }
                    if(acceleration > 7){
                        acceleration-= 3;
                    }
                }else{
                    if (acceleration < 21) {
                        acceleration+= 3;
                    }
                }
                accelerationtimer = 0;
            }
           accelerationtimer++;
        }
        if(jumping){
            height+=acceleration;
        }
    }
    public void fixAngle(){
        if(angle> 360){
            angle = 0;
        }
        if(angle < 0)
        angle = 360;
    }
    public boolean checkCollision(float planetX, float planetY){
        if(MyVector.distanceSq(new MyVector((float)(pLocation.x + 100 + (100 + 42 + height)*Math.cos(Math.toRadians(angle - 135))),(float) (pLocation.y + 100 + (100 + 42 + height)*Math.sin(Math.toRadians(angle - 135)))), new MyVector(planetX + 100, planetY + 100))<121*121){
            land(planetX, planetY);
            return true;
        }
        return false;
    }
    public void land(float planetX, float planetY){
        angle = (int) (Math.atan2((int) (pLocation.y + 100 + (100 + 42 + height)*Math.sin(Math.toRadians(angle - 135)))-planetY-100, (int) (pLocation.x + 100 + (100 + 42 + height)*Math.cos(Math.toRadians(angle - 135)))-planetX - 100)*180/3.14596) + 135;
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
            if (keyCode == keys[0]) {
                moving = true;
                if(acceleration == 0)acceleration++;
            }
        }
        if (keyCode == keys[1]) {
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
