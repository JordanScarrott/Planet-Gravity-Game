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

    // Constructor
    public Planet(MyVector location, float radius, int bounds, BufferedImage imgPlanet) {
        this.pLocation = location;
        this.radius = radius;
        this.bounds = bounds;
        this.imgPlanet = imgPlanet;
        // The center of the image
        center = new MyVector(imgPlanet.getWidth() / 2, imgPlanet.getHeight() / 2);
        perimeter = 2 * (float)Math.PI * this.radius;
    }

    public Planet(float x, float y, float radius, int bounds, BufferedImage imgPlanet) {
        this(new MyVector(x, y), radius, bounds, imgPlanet);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imgPlanet, (int) pLocation.x, (int) pLocation.y, null);
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
}
