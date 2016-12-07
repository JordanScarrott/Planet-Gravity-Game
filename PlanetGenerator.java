
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

    /**
     * Constructor
    * @param frameX the width of the current JFrame
    * @param frameY the height of the current JFrame
    */
    public PlanetGenerator(int frameX, int frameY) {
        this.width = frameX;
        this.height = frameY;
        planets = new ArrayList<>();
        rand = new Random();
        numOfPlanets = 3;

        generatePlanets();
    }

    /**
     * Randomly generate a number of Planet objects
     * */
    public void generatePlanets() {
        int tempRadius;
        MyVector tempLocation;
        for(int i = 0; i < numOfPlanets; i++) {
            // Choose a random location and radius for the new planet
            tempRadius = rand.nextInt(MAX_RADIUS) + MIN_RADIUS;

            do {
                tempLocation = MyVector.randomMyVector();
                tempLocation.respectiveMult(width, height);
            } while (!boundaryChecks(tempLocation, tempRadius) && planetChecks(tempLocation, tempRadius));

            planets.add(new Planet(tempLocation, (float)tempRadius, ResourceLoader.getRandomPlanet()));

            System.out.println(planets.get(i).getpLocation() + "\t" + planets.get(i).getRadius());
        }
    }

    /**
    * Returns true if specified Planet does NOT collide with the frame borders
     * @param location the location of the center of the Planet in question
     * @param radius the radius of the Planet in question
     * @return true if specified Planet does NOT collide with the frame borders
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

    /**
     * Returns true if specified Planet does NOT collide with the existing Planets
     * @param location the location of the center of the Planet in question
     * @param radius the radius of the Planet in question
     * @return true if specified Planet does NOT collide with the existing Planets
     */
    private boolean planetChecks(MyVector location, int radius) {
        int playerBoundary = 2 * PLAYER_SIZE;
        for (Planet p : planets) {
//            if distSq between planet and proposed planet < (sum of radii)^2
            if (MyVector.distanceSq(location, p.getpLocation())
                    < (p.getRadius() + radius + playerBoundary) * (p.getRadius() + radius + playerBoundary)) {
                return false;
            }
        }
        return true;
    }


    // Getters and Setters
    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(ArrayList<Planet> planets) {
        this.planets = planets;
    }

    public int getNumOfPlanets() {
        return numOfPlanets;
    }

    public void setNumOfPlanets(int numOfPlanets) {
        this.numOfPlanets = numOfPlanets;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
