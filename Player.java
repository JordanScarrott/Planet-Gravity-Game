import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends JPanel implements KeyListener {

    private BufferedImage imgPlayer = null;
    private MyVector pLocation;
    private MyVector pVelocity;
    private Planet relativePlanet;

    private float radLocation;
    private float radVelocity;
    private float radAcceleration;

    private int currentSpriteX;
    private int currentSpriteY;
    private long lastTime = 0;
    private double delay = 100;
    private float angle;
    private boolean moving = false;
    private boolean jumping = false;
    private int wins;
    private int[] keys;
    private int frameSize = 42;
    private boolean animationLand;
    private boolean alive;

    public Player(Planet relPla, BufferedImage imgPlayer, int[] keys) {
        this.pLocation = relPla.getpLocation().copy();
        pVelocity = new MyVector();
        this.imgPlayer = imgPlayer;
        this.keys = keys;
        alive = true;
        this.wins = 0;
        angle = randomAngle();
        this.pLocation.add((relPla.getRadius() + 21) * (float) Math.cos(angle), (relPla.getRadius() + 21) * (float) Math.sin(angle));
        animationLand = false;

        this.relativePlanet = relPla;
        this.wins = 0;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(Convert.getCropX() + Convert.scale(pLocation.x - 21), Convert.scale(pLocation.y - 21));
        transform.rotate(angle + Math.PI / 2, Convert.scale(21), Convert.scale(21));
        if (moving || jumping) {
            animate();
        }
        transform.scale(1.28,1.28);g2d.drawImage(imgPlayer.getSubimage(currentSpriteX * frameSize, currentSpriteY * frameSize, frameSize, frameSize), transform, this);
    }

    public void move(int gameSpeed) {
        update(gameSpeed);
        if (jumping) {
            pVelocity = MyVector.sub(pLocation, relativePlanet.getpLocation()).normalize();
            pVelocity.mult(radVelocity);
            pLocation.add(pVelocity);
        }
    }

    public void animate() {
        long newTime = System.currentTimeMillis();
        if (newTime - lastTime >= delay) {
            lastTime = newTime;
            if (currentSpriteX < 3) {
                currentSpriteX++;
            } else {
                currentSpriteX = 0;
            }
        }
    }

    public void update(int gameSpeed) {
        applyForce(gameSpeed);
        clampRadLocation();
        clampRadVelocity();

        MyVector temp = MyVector.sub(pLocation, relativePlanet.getpLocation());
        temp.rotate(computeRadianAngle());
        temp.add(relativePlanet.getpLocation());
        pLocation.set(temp.x, temp.y);

        angle += computeRadianAngle();
        // Reset radLocation for the next rotation
        radLocation = 0;
    }

    public float computeRadianAngle() {
        return (radLocation * (float) Math.PI * 2) / relativePlanet.getPerimeter();
    }

    public void clampRadLocation() {
        radLocation %= (relativePlanet.getPerimeter() * 2 * Math.PI);
    }

    public void clampRadVelocity() {
        if (radVelocity > Planet.MAX_VELOCITY) radVelocity = Planet.MAX_VELOCITY;
    }

    public void applyForce(int gameSpeed) {
        if (moving) {
            //Apply Acceleration;
            if (radVelocity < relativePlanet.getThisPlanetsMax()) {
                radVelocity += radAcceleration * gameSpeed * 0.25;
                radLocation += radVelocity;
            } else {
                if (radVelocity > 7) {
                    radVelocity -= 10 * radAcceleration * gameSpeed * 0.25;
                    radLocation += radVelocity;
                } else {
                    radVelocity -= 2 * radAcceleration * gameSpeed * 0.25;
                    radLocation += radVelocity;
                }

            }
        }
    }

    public float randomAngle() {
        return (float) (Math.random() * 2 * Math.PI);
    }

    public boolean checkCollision(Planet planet) {
        if (MyVector.distanceSq(pLocation, planet.getpLocation()) < (21 + planet.getRadius()) * (21 + planet.getRadius())) {
            land(planet);
            return true;
        }
        return false;
    }

    public void land(Planet planet) {
        //Calculate new angle
        this.relativePlanet = planet;
        angle = (float) Math.atan((pLocation.y - relativePlanet.getpLocation().y) / (pLocation.x - relativePlanet.getpLocation().x));
        if (pLocation.x < relativePlanet.getpLocation().x) angle += Math.PI;
        jumping = false;
        moving = true;
        currentSpriteX = 0;
        currentSpriteY = 0;
        animationLand = true;
        radAcceleration = relativePlanet.getPlanetaryAcceleration();
    }

    public void spawn(Planet planet) {
        this.pLocation = planet.getpLocation().copy();
        this.relativePlanet = planet;
        this.pLocation.add((relativePlanet.getRadius() + 21) * (float) Math.cos(angle), (relativePlanet.getRadius() + 21) * (float) Math.sin(angle));
        moving = false;
        jumping = false;
        radLocation = 0;
        radVelocity = 0;
        currentSpriteX = 0;
        currentSpriteY = 0;
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
        if (!jumping) {
            if (keyCode == keys[0]) {
                moving = true;
                radAcceleration = relativePlanet.getPlanetaryAcceleration();
            }
        }
        if (moving) {
            if (keyCode == keys[1]) {
                moving = false;
                jumping = true;
                ResourceLoader.loadAudio("jump.wav");
                currentSpriteY = 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void win() {
        this.wins++;
    }


    // Getters and Setters
    public boolean getJumping() {
        return jumping;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public float getRadVelocity() {
        return radVelocity;
    }

    public MyVector getpLocation() {
        return pLocation;
    }

    public void setpLocation(MyVector pLocation) {
        this.pLocation = pLocation;
    }

    public void setNewLocation() {
        angle = randomAngle();
        this.pLocation.add((relativePlanet.getRadius() + 21) * (float) Math.cos(angle), (relativePlanet.getRadius() + 21) * (float) Math.sin(angle));
    }

    public int getWins() {
        return this.wins;
    }
}