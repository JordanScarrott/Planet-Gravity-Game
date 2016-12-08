import java.util.ArrayList;

public abstract class LevelCreator {
    public static  ArrayList<Planet> generateLevel(int level){
        ArrayList<Planet> planets = new ArrayList<Planet>();
        if(level == 0) {
            planets.add(new Planet(56, 74, 100, 6, ResourceLoader.loadImage("planets/orangeplanet.png")));
            planets.add(new Planet(525, 356, 50, 20, ResourceLoader.loadImage("planets/testpurpleplanet.png")));
            planets.add(new Planet(550, 88, 75, 10, ResourceLoader.loadImage("planets/greenplanet.png")));
            planets.add(new Planet(250, 350, 50, 14, ResourceLoader.loadImage("planets/redplanet.png")));
        }else if(level == 1){
            planets.add(new Planet(56, 74, 100, 6, ResourceLoader.loadImage("planets/redplanet.png")));
            planets.add(new Planet(525, 356, 50, 20, ResourceLoader.loadImage("planets/testpurpleplanet.png")));
            planets.add(new Planet(550, 88, 75, 10, ResourceLoader.loadImage("planets/redplanet.png")));
            planets.add(new Planet(250, 350, 50, 14, ResourceLoader.loadImage("planets/redplanet.png")));
        }else{
            planets.add(new Planet(56, 74, 100, 6, ResourceLoader.loadImage("planets/orangeplanet.png")));
            planets.add(new Planet(525, 356, 50, 20, ResourceLoader.loadImage("planets/testpurpleplanet.png")));
            planets.add(new Planet(550, 88, 75, 10, ResourceLoader.loadImage("planets/orangeplanet.png")));
            planets.add(new Planet(250, 350, 50, 14, ResourceLoader.loadImage("planets/orangeplanet.png")));
        }
        return planets;
    }
}
