import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends JPanel implements KeyListener {

    public static final int JUMP_VELOCITY = 1;

    private BufferedImage imgPlayer = null;
    private MyVector pLocation;
    private MyVector pVelocity;
    private MyVector center;
    private Planet relativePlanet;

    private float radLocation;
    private float radVelocity;
    private float radAcceleration;

    private int currentSpriteX;
    private int constcurrentSpriteX;
    private int currentSpriteY;
    private int constcurrentSpriteY;
    private long lastTime = 0;
    private double delay = 100;
    private float height;
    private int acceleration;
    private int radius;
    private int bounds;
    private float angle;
    private boolean moving = false;
    private boolean jumping = false;
    private int[] keys;
    private int accelerationtimer;
    private int frameSize = 42;
    private boolean animationLand;
    private boolean alive;

    public Player(MyVector location, int radius, int bounds,BufferedImage imgPlayer, int[] keys) {
        this.pLocation = location;
        pVelocity = new MyVector();
        this.center = new MyVector(location.x , location.y);
        this.imgPlayer = imgPlayer;
        this.radius = radius;
        this.bounds = bounds;
        this.keys = keys;
        alive = true;
        angle = randomAngle();
        this.pLocation.add((radius+21) * (float)Math.cos(angle), (radius+21) * (float)Math.sin(angle));
        acceleration = 0;
        accelerationtimer = 0;
        animationLand = false;
    }
    public Player(Planet relPla, BufferedImage imgPlayer, int[] keys){
        this(relPla.getpLocation().copy()
                , (int)relPla.getRadius() // <-- BUGSS!!!!
                , relPla.getBbounds()
                , imgPlayer
                , keys);
        this.relativePlanet = relPla;

    }
    //Getters and Setters
    public boolean getJumping() {
        return jumping;
    }
    public float getAngle(){
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
    public void setAngle(int angle){
        this.angle = angle;
    }
    //Functions
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(pLocation.x-21, pLocation.y-21);
//        System.out.println(angle);
        transform.rotate(angle + Math.PI/2, 21,21);
        if(moving || jumping) {
            animate();
        }
        g2d.drawImage(imgPlayer.getSubimage(currentSpriteX*frameSize,currentSpriteY*frameSize, frameSize, frameSize), transform, this);

        //g.fillRect((int)(pLocation.x + radius + (radius + height + height/2 + bounds)*Math.cos(Math.toRadians(angle - 135))),(int)(pLocation.y + radius + (radius + height + height/2 + bounds)*Math.sin(Math.toRadians(angle - 135))), 10, 10);

    }
    public void move() {
//        System.out.println(radVelocity);
        update();
        if(jumping){
            pVelocity = MyVector.sub(pLocation, relativePlanet.getpLocation()).normalize();
            pVelocity.mult(JUMP_VELOCITY);
            pLocation.add(pVelocity);
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
    public void update(){
        applyForce();
        clampRadLocation();
        clampRadVelocity();

        MyVector temp = MyVector.sub(pLocation, center);
        temp.rotate(computeRadianAngle());
        temp.add(center);
        pLocation.set(temp.x, temp.y);

        angle += computeRadianAngle();
        // Reset radLocation for the next rotation
        radLocation = 0;
    }
    public float computeRadianAngle(){
        return (radLocation * (float)Math.PI * 2) / relativePlanet.getPerimeter();
    }
    public void clampRadLocation(){
        radLocation %= (relativePlanet.getPerimeter()*2*Math.PI);
    }
    public void clampRadVelocity(){
        if(radVelocity > Planet.MAX_VELOCITY) radVelocity = Planet.MAX_VELOCITY;
    }
    public void applyForce(){
        if(moving){
            //Apply Acceleration;
            radVelocity += radAcceleration;
            radLocation += radVelocity;
        }
    }
    public float randomAngle(){
        return (float)(Math.random()*2*Math.PI);
    }

    public boolean checkCollision(Planet planet){
        if (MyVector.distanceSq(pLocation, planet.getpLocation()) < (21 + planet.getRadius()) * (21 + planet.getRadius())) {
            land(planet);
            return true;
        }
        return false;
    }
    public float findGridX(){
        return (float)(pLocation.x + (radius)*Math.cos(Math.toRadians(angle)));
    }
    public float findGridY(){
        return (float)(pLocation.y + (radius)*Math.sin(Math.toRadians(angle)));
    }
    public void land(Planet planet){
        //Calculate new angle
        this.relativePlanet = planet;
        this.center.set(relativePlanet.getpLocation());
        angle = (float)Math.toRadians(MyVector.angle(pLocation, relativePlanet.getpLocation()));
        jumping = false;
        moving = true;
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
                if(acceleration == 0)radAcceleration = relativePlanet.getPlanetaryAcceleration();
            }
        }
        if(moving) {
            if (keyCode == keys[1]) {
                moving = false;
                jumping = true;
                currentSpriteY = 1;
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