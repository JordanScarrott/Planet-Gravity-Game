import java.util.ArrayList;

public abstract class LevelCreator {
    //Orange     planets.add(new Planet(0, 0, 100, 10, 10, ResourceLoader.loadImage("planets/orangeplanet.png")));
    //Purple     planets.add(new Planet(0, 0, 50, 8, 6, ResourceLoader.loadImage("planets/purpleplanet.png")));
    //Red        planets.add(new Planet(0, 0, 41, 9, 6, ResourceLoader.loadImage("planets/redplanet.png")));
    //Green      planets.add(new Planet(0, 0, 75, 7, 6, ResourceLoader.loadImage("planets/greenplanet.png")));
    //Asteroid   planets.add(new Planet(0, 0, 40, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
    public static  ArrayList<Planet> generateLevel(int level){
        ArrayList<Planet> planets = new ArrayList<Planet>();
        if(level == 0) {
            planets.add(new Planet(80, 360, 41, 9, 6, ResourceLoader.loadImage("planets/redplanet.png")));
            planets.add(new Planet(100, 100, 40, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(100, 620, 40, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(280, 360, 50, 8, 6, ResourceLoader.loadImage("planets/purpleplanet.png")));
            planets.add(new Planet(250, 510, 50, 8, 6, ResourceLoader.loadImage("planets/purpleplanet.png")));
            planets.add(new Planet(250, 210, 50, 8, 6, ResourceLoader.loadImage("planets/purpleplanet.png")));
            planets.add(new Planet(460, 460, 75, 7, 6, ResourceLoader.loadImage("planets/greenplanet.png")));
            planets.add(new Planet(460, 260, 75, 7, 6, ResourceLoader.loadImage("planets/greenplanet.png")));
            planets.add(new Planet(780, 360, 100, 10, 10, ResourceLoader.loadImage("planets/orangeplanet.png")));
            planets.add(new Planet(1100, 150, 100, 10, 10, ResourceLoader.loadImage("planets/orangeplanet.png")));
            planets.add(new Planet(1100, 570, 100, 10, 10, ResourceLoader.loadImage("planets/orangeplanet.png")));
            planets.add(new Planet(860, 130, 75, 7, 6, ResourceLoader.loadImage("planets/greenplanet.png")));
            planets.add(new Planet(860, 590, 75, 7, 6, ResourceLoader.loadImage("planets/greenplanet.png")));
            planets.add(new Planet(680, 165, 50, 8, 6, ResourceLoader.loadImage("planets/purpleplanet.png")));
            planets.add(new Planet(680, 555, 50, 8, 6, ResourceLoader.loadImage("planets/purpleplanet.png")));
            planets.add(new Planet(1100, 360, 50, 8, 6, ResourceLoader.loadImage("planets/purpleplanet.png")));

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
            planets.add(new Planet(80, 80, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(206, 100, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(332, 80, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(458, 100, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(584, 80, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(710, 100, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));

            planets.add(new Planet(80, 206, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(206, 226, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(332, 206, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(458, 226, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(584, 206, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(710, 226, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));

            planets.add(new Planet(80, 332, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(206, 352, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(332, 332, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(458, 352, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(584, 332, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(710, 352, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));

            planets.add(new Planet(80, 458, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(206, 478, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(332, 458, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(458, 478, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(584, 458, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));
            planets.add(new Planet(710, 478, 41, 11, 6, ResourceLoader.loadImage("planets/asteroid.png")));

        }
        return planets;
    }
}
