import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends JPanel implements KeyListener {

    public static final int JUMP_VELOCITY = 5;

    private BufferedImage imgPlayer = null;
    private MyVector pLocation;
    private MyVector center;

    /**
     * The planet that the player will rotate around
     * */
    private Planet relativePlanet;

    private float radLocation;
    private float radVelocity;
    private float radAcceleration;

    private int currentSpriteX;
    private int currentSpriteY;
    private long lastTime = 0;
    private double delay = 100;
    private float height;
    private float radius;
    private int bounds;
    private double angle;
    private boolean moving = false;
    private boolean jumping = false;
    private int[] keys;

    public Player(MyVector location, float radius, int bounds, BufferedImage imgPlayer, int[] keys) {
        this.pLocation = location;
        radLocation = 0;
        radVelocity = 0;
        radAcceleration = 0;
        this.imgPlayer = imgPlayer;
        this.radius = radius;
        this.bounds = bounds;
        this.keys = keys;
        angle = randomAngle();
    }

    public Player(float x, float y, float radius, int bounds, BufferedImage imgPlayer, int[] keys) {
        this(new MyVector(x, y), radius, bounds, imgPlayer, keys);
    }

    /**
     * @param relPla the planet that this player is relative to / rotates around
     * @param imgPlayer the players image
     * @param keys controls for this player
     * */
    public Player(Planet relPla, BufferedImage imgPlayer, int[] keys) {
        this(relPla.getpLocation().x
                , relPla.getpLocation().y
                , relPla.getRadius()
                , relPla.getBbounds()
                , imgPlayer
                , keys
        );
        this.relativePlanet = relPla;
    }

    public void paint(Graphics g){
        super.paint(g);
        angle = Math.toDegrees(radLocation / relativePlanet.getPerimeter());
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(pLocation.x - height - bounds, pLocation.y - height- bounds);
        transform.rotate(radLocation / relativePlanet.getPerimeter(), radius + height + bounds, radius + height + bounds) ;
        transform.rotate(Math.toRadians(-45), 21, 21);

        animate();
        if(!jumping)currentSpriteY = 0;
        else currentSpriteY = 1;


        g2d.drawImage(imgPlayer.getSubimage(currentSpriteX* 42, currentSpriteY*42, 42, 42), transform, this);
        //g.fillRect((int)(pLocation.x + radius + (radius + height + height/2 + bounds)*Math.cos(Math.toRadians(angle - 135))),(int)(pLocation.y + radius + (radius + height + height/2 + bounds)*Math.sin(Math.toRadians(angle - 135))), 10, 10);

    }

    public void move() {
        update();
        if(jumping){
            // temporary thing I think 10:57am 08-12-2016
            height += JUMP_VELOCITY;
        }
    }

    public void update() {
        clampRadLocation();
        clampVelocity();
        applyForce();
    }

    /**
     * Keeps the radLocation between 0 and 2.PI.r
     * */
    public void clampRadLocation() {
        radLocation %= (relativePlanet.getPerimeter() * 2 * Math.PI);
    }

    /**
     * If radVelocity is greater than Planet.MAX_VELOCITY sets it to Planet.MAX_VELOCITY
     * */
    public void clampVelocity() {
        radVelocity = (radVelocity > Planet.MAX_VELOCITY) ? Planet.MAX_VELOCITY : radVelocity;
    }


    public void applyForce() {
        if(moving) {
            // Apply Acceleration
            radVelocity += radAcceleration;
            radLocation += radVelocity;
        }
    }

    public void animate(){
        long newTime = System.currentTimeMillis();
        if (newTime - lastTime >= delay) {
            lastTime = newTime;
            if (currentSpriteX < 3) {
                currentSpriteX++;
            }
            else {
                currentSpriteX = 0;
            }
        }
    }

    public int randomAngle(){
        return (int)(Math.random()*360);
    }


    public boolean checkCollision(Planet planet){
        /*if(MyVector.distanceSq(new MyVector((float)(pLocation.x + radius + (radius + height+ height/2 + bounds)*Math.cos(Math.toRadians(angle - 135))),(float) (pLocation.y + radius + (radius + height + height/2+ bounds)*Math.sin(Math.toRadians(angle - 135)))), new MyVector(planet.getpLocation().x + planet.getRadius(), planet.getpLocation().y + planet.getRadius())) <= (21 + planet.getRadius())*(21 + planet.getRadius())){
            land(planet.getpLocation().x, planet.getpLocation().y, planet.getRadius(), planet.getBbounds());
            return true;
        }*/
        System.out.println(MyVector.distanceSq(MyVector.add(this.getpLocation(), new MyVector(21, 21)), planet.getCenter()) + "\t" + (21 + planet.getRadius()) * (21 + planet.getRadius()));
        if (MyVector.distanceSq(MyVector.add(this.getpLocation(), new MyVector(21, 21)), planet.getCenter()) < (21 + planet.getRadius()) * (21 + planet.getRadius())) {
//            System.out.println("askdfhalksjdhflkjashdflkjaslkdfh");
            relativePlanet = planet;
            return true;
        }
        return false;
    }

    public void land(float planetX, float planetY, float pRadius, int pBounds) {
        //Calculate new angle
        angle = (int) (Math.atan2((int) (pLocation.y + radius + (radius + height + height/2 + bounds)*Math.sin(Math.toRadians(angle - 135)))-planetY-pRadius, (int) (pLocation.x + radius + (radius + height + height/2+ bounds)*Math.cos(Math.toRadians(angle - 135)))-planetX - pRadius)*180/Math.PI) + 135;
        jumping = false;
        moving = true;
        radius = (int)pRadius;
        bounds = pBounds;
        pLocation.x = planetX;
        pLocation.y = planetY;
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
                if(radAcceleration == 0) radAcceleration = relativePlanet.getPlanetaryAcceleration();
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

    //Getters and Setters
    public boolean getJumping() {
        return jumping;
    }

    public MyVector getpLocation() {
        return pLocation;
    }

    public void setpLocation(MyVector pLocation) {
        this.pLocation = pLocation;
    }

    public double getAngle(){
        return angle;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public Planet getRelativePlanet() {
        return relativePlanet;
    }

    /**
     * Set which planet this player will rotate around
     * */
    public void setRelativePlanet(Planet relativePlanet) {
        this.relativePlanet = relativePlanet;
    }

    public float getRadLocation() {
        return radLocation;
    }

    public void setRadLocation(float radLocation) {
        this.radLocation = radLocation;
    }

    public float getRadVelocity() {
        return radVelocity;
    }

    public void setRadVelocity(float radVelocity) {
        this.radVelocity = radVelocity;
    }

    public float getRadAcceleration() {
        return radAcceleration;
    }

    public void setRadAcceleration(float radAcceleration) {
        this.radAcceleration = radAcceleration;
    }
}
