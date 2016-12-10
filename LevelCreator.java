import java.util.ArrayList;

public abstract class LevelCreator {
    //Orange  planets.add(new Planet(0, 0, 100, 10, 10, ResourceLoader.loadImage("planets/orangeplanet.png")));
    //Purple  planets.add(new Planet(0, 0, 50, 8, 6, ResourceLoader.loadImage("planets/purpleplanet.png")));
    //Red     planets.add(new Planet(0, 0, 41, 9, 6, ResourceLoader.loadImage("planets/redplanet.png")));
    //Green   planets.add(new Planet(0, 0, 75, 7, 6, ResourceLoader.loadImage("planets/greenplanet.png")));
    public static  ArrayList<Planet> generateLevel(int level){
        ArrayList<Planet> planets = new ArrayList<Planet>();
        if(level == 0) {
            planets.add(new Planet(156, 174, 100,10, 10, ResourceLoader.loadImage("planets/orangeplanet.png")));
            planets.add(new Planet(575, 406, 50,8, 6, ResourceLoader.loadImage("planets/purpleplanet.png")));
            planets.add(new Planet(625, 163, 75,7, 6, ResourceLoader.loadImage("planets/greenplanet.png")));
            planets.add(new Planet(300, 400, 41, 9, 6, ResourceLoader.loadImage("planets/redplanet.png")));
        }else if(level == 1){
            planets.add(new Planet(150, 150, 100, 10, 10, ResourceLoader.loadImage("planets/orangeplanet.png")));
            planets.add(new Planet(130, 400, 75, 7, 6, ResourceLoader.loadImage("planets/greenplanet.png")));
            planets.add(new Planet(420, 130, 75, 7, 6, ResourceLoader.loadImage("planets/greenplanet.png")));
            planets.add(new Planet(330, 315, 50, 8, 6, ResourceLoader.loadImage("planets/purpleplanet.png")));

            planets.add(new Planet(680, 200, 75, 7, 6, ResourceLoader.loadImage("planets/greenplanet.png")));
            planets.add(new Planet(500, 300, 41, 9, 6, ResourceLoader.loadImage("planets/redplanet.png")));
            planets.add(new Planet(425, 420, 41, 9, 6, ResourceLoader.loadImage("planets/redplanet.png")));
            planets.add(new Planet(650, 450, 100, 10, 10, ResourceLoader.loadImage("planets/orangeplanet.png")));
        }else{
            planets.add(new Planet(156, 174, 100,10, 10, ResourceLoader.loadImage("planets/orangeplanet.png")));
            planets.add(new Planet(575, 406, 50,8, 6, ResourceLoader.loadImage("planets/purpleplanet.png")));
            planets.add(new Planet(625, 163, 75,7, 6, ResourceLoader.loadImage("planets/greenplanet.png")));
            planets.add(new Planet(300, 400, 41, 9, 6, ResourceLoader.loadImage("planets/redplanet.png")));
        }
        return planets;
    }
}
