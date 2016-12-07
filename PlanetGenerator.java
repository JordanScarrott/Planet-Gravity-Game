
import java.util.Random;
import java.util.ArrayList;

public class PlanetGenerator {

    /**
    * Planet limiting parameters
    */
    public static final int MIN_RADIUS = 70;
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
        numOfPlanets = 4;

        generatePlanets2();
    }

    /**
     * Version 2 of the planet generation algorithm
     * */
    public void generatePlanets2() {
        int tempRadius = 0;
        int frameSpacer;
        MyVector minLocation = new MyVector();
        MyVector maxLocation = new MyVector();
        MyVector interval = new MyVector();
        MyVector tempLocation;
        int count;

        for(int i = 0; i < numOfPlanets; i++) {
            count = 0;
            do {
                if(count%1000 == 0) {
                    // Choose a random radius within the required range
                    tempRadius = rand.nextInt(MAX_RADIUS - MIN_RADIUS) + MIN_RADIUS;
                    // minimum space between planet and frame borders
                    frameSpacer = tempRadius + PLAYER_SIZE;

                    minLocation.set(frameSpacer, frameSpacer);
                    maxLocation.set(width - frameSpacer, height - frameSpacer);
                    // Interval of all acceptable planet locations for this radius
                    interval = MyVector.sub(maxLocation, minLocation);
                }

                // Determine possible location
                tempLocation = MyVector.randomMyVector();
                // Scale the random vector
                tempLocation.respectiveMult(interval.x, interval.y);
                // Shift to correct range
                tempLocation.add(minLocation);

                count++;
            } while(collidesWithExistingPlanets(tempLocation, tempRadius));

            planets.add(new Planet(tempLocation, tempRadius, ResourceLoader.getRandomPlanet()));
            Planet temp = planets.get(i);
//            System.out.println(planets.get(i).getpLocation() + "\t" + planets.get(i).getRadius());
        }
    }

    /**
     * Returns true if specified Planet collides with the existing Planets
     * @param location the location of the center of the Planet in question
     * @param radius the radius of the Planet in question
     * @return true if specified Planet collides with the existing Planets
     */
    private boolean collidesWithExistingPlanets(MyVector location, int radius) {
        if (planets.size() < 1) return false;
        float tempDistSq;
        float tempSumRadii;

        int playerBoundary = 2 * PLAYER_SIZE;
        for (Planet p : planets) {
//            if distSq between planet and proposed planet < (sum of radii)^2
            tempDistSq = MyVector.distanceSq(location, p.getCenter());
            tempSumRadii = (p.getRadius() + radius + playerBoundary) * (p.getRadius() + radius + playerBoundary);
            if (tempDistSq < tempSumRadii) {
                return true;
            }
//            System.out.println(MyVector.distanceSq(location, p.getCenter()) + "\t" + (p.getRadius() + radius + playerBoundary) * (p.getRadius() + radius + playerBoundary));
        }
        return false;
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
