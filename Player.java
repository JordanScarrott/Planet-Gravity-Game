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
    private MyVector pVelocity;
    private MyVector center;

    /**
     * The planet that the player will rotate around
     * */
    private Planet relativePlanet;
    private MyVector transform;

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
    /**
     * True if Normal Vector has already been computed for the current jump
     * */
    private boolean isFlying = false;
    private int[] keys;

    public Player(MyVector location, float radius, int bounds, BufferedImage imgPlayer, int[] keys) {
        this.pLocation = location;
        pVelocity = new MyVector();
        transform = new MyVector();
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
        if(!jumping) currentSpriteY = 0;
        else currentSpriteY = 1;

        g2d.drawImage(imgPlayer.getSubimage(currentSpriteX * 42, currentSpriteY * 42, 42, 42), transform, this);
        //g.fillRect((int)(pLocation.x + radius + (radius + height + height/2 + bounds)*Math.cos(Math.toRadians(angle - 135))),(int)(pLocation.y + radius + (radius + height + height/2 + bounds)*Math.sin(Math.toRadians(angle - 135))), 10, 10);

    }

    public void move() {
        update();
        transform = Rotation.rotate(MyVector.add(pLocation, relativePlanet.getCenter())
                , radLocation / relativePlanet.getPerimeter());
        if (jumping) {
            if (!isFlying) {
                transform = Rotation.rotate(MyVector.add(pLocation, relativePlanet.getCenter())
                        , radLocation / relativePlanet.getPerimeter());
//                System.out.println(transform);
                // Normalized Difference Vector representing Normal vector (heading)
                pVelocity = MyVector.sub(relativePlanet.getCenter(), transform).normalize();
//                System.out.println(pVelocity);
                // Scale it
                pVelocity.mult(JUMP_VELOCITY);
//                System.out.println(relativePlanet.getCenter() + "\t" + pLocation);
//                System.out.println(pVelocity);
                isFlying = true;
            }
            // Apply jumping velocity to location
            pLocation.add(pVelocity);
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
        if(MyVector.distanceSq(new MyVector((float)(pLocation.x + radius + (radius + height+ height/2 + bounds)*Math.cos(Math.toRadians(angle - 135))),(float) (pLocation.y + radius + (radius + height + height/2+ bounds)*Math.sin(Math.toRadians(angle - 135)))), new MyVector(planet.getpLocation().x + planet.getRadius(), planet.getpLocation().y + planet.getRadius())) <= (21 + planet.getRadius())*(21 + planet.getRadius())){
            land(planet.getpLocation().x, planet.getpLocation().y, planet.getRadius(), planet.getBbounds());
            relativePlanet = planet;
            return true;
        }
        return false;
    }

    public double findGridX(){
        return pLocation.x + radius + (radius + height+ height/2 + bounds)*Math.cos(Math.toRadians(angle - 135));
    }
    public double findGridY(){
        return pLocation.y + radius + (radius + height + height/2+ bounds)*Math.sin(Math.toRadians(angle - 135));
    }

    /**
     *
     * */
    public void land(float planetX, float planetY, float pRadius, int pBounds){
        //Calculate new angle
        angle = (int) -(Math.atan2((int) findGridY()-planetY-pRadius, (int) findGridX()-planetX - pRadius)*180/Math.PI) + 135;
        jumping = false;
        moving = true;
        radius = (int)pRadius;
        bounds = pBounds;
        pLocation.x = planetX;
        pLocation.y = planetY;
        currentSpriteX = 0;
        currentSpriteY = 0;
//        animationLand = true;
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
            // Move
            if (keyCode == keys[0]) {
                moving = true;
                // Set the acceleration for the player on the specific planet
                if(radAcceleration == 0) radAcceleration = relativePlanet.getPlanetaryAcceleration();
            }
        }
        if(moving) {
            // Jump
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

    public MyVector getpVelocity() {
        return pVelocity;
    }

    public void setpVelocity(MyVector pVelocity) {
        this.pVelocity = pVelocity;
    }
}
