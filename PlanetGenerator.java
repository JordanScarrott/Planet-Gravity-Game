
import java.util.Random;
import java.util.ArrayList;

public class PlanetGenerator {

    /**
    * Planet limiting parameters
    */
    public static final int MIN_RADIUS = 50;
    public static final int MAX_RADIUS = 250;

    private ArrayList<Planet> planets;

    private Random rand;
    private int numOfPlanets;

    private int x, y;

    // Constructor
    /**
    * @param frameX the width of the current JFrame
    * @param frameY the height of the current JFrame
    */
    public PlanetGenerator(int frameX, int frameY) {
        this.x = frameX;
        this.x = frameY;
        planets = new ArrayList<>();
        rand = new Random();
        numOfPlanets = 5;
    }


    /*
    *
    **/
    public void generatePlanets() {
        int tempRadius;
        for(int i = 0; i < numOfPlanets; i++) {
//             tempRadius = rand.nextInt(MIN_RADIUS, MAX_RADIUS);
//             planets.add(new Planet(MyVector.randomMyVector(), tempRadius, ResourceLoader.randomImage()));
        }
    }

}
