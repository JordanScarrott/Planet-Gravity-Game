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
    private int currentSpriteX;
    private int currentSpriteY;
    private long lastTime = 0;
    private double delay = 100;
    private float height;
    private int acceleration;
    private int radius;
    private int bounds;
    private int angle;
    private boolean moving = false;
    private boolean jumping = false;
    private int[] keys;
    private int accelerationtimer;
    private int frameSize;
    private boolean animationLand;
    private boolean alive;

    public Player(MyVector location, int radius, int bounds,BufferedImage imgPlayer, int[] keys) {
        this.pLocation = location;
        this.imgPlayer = imgPlayer;
        this.radius = radius;
        this.bounds = bounds;
        this.keys = keys;
        alive = true;
        angle = randomAngle();
        acceleration = 0;
        accelerationtimer = 0;
        animationLand = false;
    }

    public Player(float x, float y, int radius, int bounds, BufferedImage imgPlayer, int[] keys) {
        this(new MyVector(x, y), radius,bounds, imgPlayer, keys);
    }
    //Getters and Setters
    public boolean getJumping() {
        return jumping;
    }
    public int getAngle(){
        return angle;
    }
    public boolean getAlive(){
        return alive;
    }
    public int getAcceleration(){
        return acceleration;
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    //Functions
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(pLocation.x - height - bounds, pLocation.y - height- bounds);
        transform.rotate(Math.toRadians(angle), radius + height + bounds, radius + height + bounds) ;
        transform.rotate(Math.toRadians(-45), 21,21);
        animate();
        if(!jumping){
            currentSpriteY = 0;
            frameSize = 42;
        }
        else currentSpriteY = 1;

        g2d.drawImage(imgPlayer.getSubimage(currentSpriteX* frameSize, currentSpriteY*frameSize, frameSize, frameSize), transform, this);
        if(animationLand){
            AffineTransform transform2 = new AffineTransform();
            transform2.translate(pLocation.x - height - bounds + 15, pLocation.y - height- bounds + 15);
            transform2.rotate(Math.toRadians(angle), radius + height + bounds-15, radius + height + bounds-15) ;
            transform2.rotate(Math.toRadians(-45), 21,21);
            g2d.drawImage(imgPlayer.getSubimage(currentSpriteX* 64, currentSpriteY+2*frameSize, frameSize, frameSize), transform2, this);
            if(currentSpriteX == 3){
                animationLand = false;
            }
        }

        //g.fillRect((int)(pLocation.x + radius + (radius + height + height/2 + bounds)*Math.cos(Math.toRadians(angle - 135))),(int)(pLocation.y + radius + (radius + height + height/2 + bounds)*Math.sin(Math.toRadians(angle - 135))), 10, 10);

    }
    public void move(int planetID) {
        fixAngle(); //so the angles is always between 0 and 360
        accelerate(planetID);
        if(jumping){
            height+=acceleration;
        }
    }
    public void animate(){
        long newTime = System.currentTimeMillis();
        if (newTime-lastTime>=delay) {
            lastTime = newTime;
            if (currentSpriteX < 3) {
                currentSpriteX++;
            }
            else {
                currentSpriteX = 0;
            }
        }
    }
    public void fixAngle(){
        if(angle> 360)angle = 0;
        if(angle < 0)angle = 360;
    }
    public int randomAngle(){
        return (int)(Math.random()*360);
    }
    public void accelerate(int planetID){ //very retarded basic acceleration
        if (moving) {
            angle += acceleration;
            if(accelerationtimer > 100){
                if(planetID != 3){
                    if (acceleration < 1) {
                        acceleration++;
                    }
                    if(acceleration > 1){
                        acceleration-= 1;
                    }
                }else{
                    if (acceleration <1) {
                        acceleration+= 1;
                    }
                }
                accelerationtimer = 0;
            }
            accelerationtimer++;
        }
    }
    public boolean checkCollision(float planetX, float planetY, float pRadius, int pBounds){
        if(MyVector.distanceSq(new MyVector((float)(pLocation.x + radius + (radius + height+ height/2 + bounds)*Math.cos(Math.toRadians(angle - 135))),(float) (pLocation.y + radius + (radius + height + height/2+ bounds)*Math.sin(Math.toRadians(angle - 135)))), new MyVector(planetX + pRadius, planetY + pRadius))<=(21 + pRadius)*(21+pRadius)){
            land(planetX, planetY, pRadius, pBounds);
            return true;
        }
        return false;
    }
    public void land(float planetX, float planetY, float pRadius, int pBounds){
        //Calculate new angle
        angle = (int) (Math.atan2((int) (pLocation.y + radius + (radius + height + height/2 + bounds)*Math.sin(Math.toRadians(angle - 135)))-planetY-pRadius, (int) (pLocation.x + radius + (radius + height + height/2+ bounds)*Math.cos(Math.toRadians(angle - 135)))-planetX - pRadius)*180/Math.PI) + 135;
        jumping = false;
        moving = true;
        radius = (int)pRadius;
        bounds = pBounds;
        pLocation.x = planetX;
        pLocation.y = planetY;
        currentSpriteX = 0;
        currentSpriteY = 0;
        animationLand = true;
        height = 0;
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
                if(acceleration == 0)acceleration++; //retarded initial acceleration
            }
        }
        if(moving) {
            if (keyCode == keys[1]) {
                moving = false;
                jumping = true;
            }
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
