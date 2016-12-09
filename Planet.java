import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Planet extends JPanel {

    private BufferedImage imgPlanet = null;
    private MyVector pLocation;
    private MyVector center;
    private float radius;
    private int bounds;
    private float perimeter;

    public static final float MAX_VELOCITY = 0.8f;
    public static final float MAX_ACCELERATION = 0.001f;

    private float planetaryAcceleration;

    // Constructor
    public Planet(MyVector location, float radius,int bounds, BufferedImage imgPlanet) {
        this.pLocation = location;
        this.radius = radius;
        this.bounds = bounds;
        this.imgPlanet = imgPlanet;
        perimeter = 2 * (float)Math.PI * this.radius;

        planetaryAcceleration = MAX_ACCELERATION * (1 - (this.radius / (250 + 10)));
    }
    public Planet(float x, float y, float radius, int bounds, BufferedImage imgPlanet) {
        this(new MyVector(x, y), radius,bounds, imgPlanet);
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
    public int getBbounds() {
        return bounds;
    }

    public float getPlanetaryAcceleration() {
        return planetaryAcceleration;
    }

    public float getPerimeter() {
        return perimeter;
    }

    public void setRadius(float radius) {
        this.radius = radius;
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

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imgPlanet, (int) pLocation.x - (int)radius, (int) pLocation.y - (int)radius, null);
    }

}