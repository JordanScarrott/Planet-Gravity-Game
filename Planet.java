import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Planet extends JPanel {

    private BufferedImage imgPlanet = null;
    private MyVector pLocation;
    private MyVector center;
    private float radius;

    // Constructor
    public Planet(MyVector location, float radius) {
        this.pLocation = location;
        this.radius = radius;

        // Draws the default planet image
        try {
            imgPlanet = ImageIO.read(new File("src/Images/back.jpg"));
        } catch (IOException e) {
        }

        // The center of the image
        center = new MyVector(imgPlanet.getWidth() / 2, imgPlanet.getHeight() / 2);

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
}
