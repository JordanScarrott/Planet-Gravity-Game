import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Planet extends JPanel {

    /**
     * Maximum velocity of a player on this planet
     * */
    public static final float MAX_VELOCITY = 35;

    /**
     * Maximum acceleration that can be applied to a player each frame
     * */
    public static final float MAX_ACCELERATION = 1.5f;

    private BufferedImage imgPlanet = null;
    private MyVector pLocation;
    private MyVector center;
    private float radius;
    private int bounds;
    private float perimeter;
    // Acceleration applied to players on a planet
    private float planetaryAcceleration;

    // Constructor
    public Planet(MyVector location, float radius, int bounds, BufferedImage imgPlanet) {
        this.pLocation = location;
        this.radius = radius;
        this.bounds = bounds;
        this.imgPlanet = imgPlanet;
        // The center of the image
        center = new MyVector(imgPlanet.getWidth() / 2, imgPlanet.getHeight() / 2);
        center.add(pLocation);
        perimeter = 2 * (float)Math.PI * this.radius;
        // Scale the player's acceleration inversely to the planet's radius
        planetaryAcceleration = MAX_ACCELERATION * (float)(1 - ((double)this.radius / (PlanetGenerator.MAX_RADIUS + 10f)));
    }

    public Planet(float x, float y, float radius, int bounds, BufferedImage imgPlanet) {
        this(new MyVector(x, y), radius, bounds, imgPlanet);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imgPlanet, (int) pLocation.x, (int) pLocation.y, null);
    }

    /**
     * Display useful information about the Planet object represented by this instance
     * */
    public String toString() {
        return "pLocation: " + pLocation
                + "\tRadius: " + radius
                + "\tPerimeter: " + perimeter
                + "\tPlanetaryAcceleration: " + planetaryAcceleration;
    }

    // Getters and Setters
    public MyVector getpLocation() {
        return pLocation;
    }

    public void setpLocation(MyVector pLocation) {
        this.pLocation = pLocation;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getBbounds() {
        return bounds;
    }

    public BufferedImage getImgPlanet() {
        return imgPlanet;
    }

    public void setImgPlanet(BufferedImage imgPlanet) {
        this.imgPlanet = imgPlanet;
    }

    public MyVector getCenter() {
        return center;
    }

    public float getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(float perimeter) {
        this.perimeter = perimeter;
    }

    public float getPlanetaryAcceleration() {
        return planetaryAcceleration;
    }

    public void setPlanetaryAcceleration(float planetaryAcceleration) {
        this.planetaryAcceleration = planetaryAcceleration;
    }
}
