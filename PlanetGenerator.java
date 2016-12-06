
import java.util.Random;
import java.util.ArrayList;

public class PlanetGenerator {

    /**
    * Planet limiting parameters
    */
    public static final int MIN_RADIUS = 50;
    public static final int MAX_RADIUS = 250;

    public static final int PLAYER_SIZE = 45;

    private ArrayList<Planet> planets;

    private Random rand;
    private int numOfPlanets;

    private int width, height;

    // Constructor
    /**
    * @param frameX the width of the current JFrame
    * @param frameY the height of the current JFrame
    */
    public PlanetGenerator(int frameX, int frameY) {
        this.width = frameX;
        this.height = frameY;
        planets = new ArrayList<>();
        rand = new Random();
        numOfPlanets = 5;
    }


    /*
    *
    **/
    public void generatePlanets() {
        int tempRadius;
        MyVector tempLocation;
        for(int i = 0; i < numOfPlanets; i++) {
            // Choose a random location and radius for the new planet
            tempRadius = rand.nextInt(MAX_RADIUS) + MIN_RADIUS;

            /*do {
                tempLocation = MyVector.randomMyVector();
                tempLocation.respectiveMult(width, height);
            } while (!boundaryChecks(tempLocation, tempRadius) && planetChecks());

            planets.add(new Planet(tempLocation, tempRadius, ResourceLoader.getRandomPlanet()));*/
            // planets.add(new Planet(MyVector.randomMyVector(), tempRadius, ResourceLoader.randomImage()));
        }
    }

    /**
    * Returns true if Specified does not collide with the frame borders
    */
    private boolean boundaryChecks(MyVector location, int radius) {
        if ((location.x - radius - PLAYER_SIZE < 0) || (location.x + radius + PLAYER_SIZE > width)) {
            return false;
        }
        if ((location.y - radius - PLAYER_SIZE < 0) || (location.y + radius + PLAYER_SIZE > height)) {
            return false;
        }

        return true;
    }

    private boolean planetChecks() {
        return true;
    }
}
