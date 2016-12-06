import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JPanel {

    ArrayList<Planet> planets = new ArrayList<Planet>();
    private Player player;
    private int planetID;

    public Game(JFrame frame) {
        planetID = 0;
        planets.add(new Planet(300, 200, 100, ResourceLoader.loadImage("orangeplanet.png")));
        planets.add(new Planet(0, 0, 100, ResourceLoader.loadImage("orangeplanet.png")));
        planets.add(new Planet(500, 0, 100, ResourceLoader.loadImage("orangeplanet.png")));
        player = new Player(300, 200, 100, ResourceLoader.loadImage("hamster.png"));
        player.addKeyListener(frame);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for(Planet i : planets){
            i.paint(g);
        }
        player.paint(g);
    }

    public void move() {
        player.move();
        if(player.getJumping()){
            for(int i= 0; i < planets.size(); i++) {
                if(i != planetID)
                    if(player.checkCollision(planets.get(i).getpLocation().x, planets.get(i).getpLocation().y)){
                        planetID = i;
                        break;
                    }

            }
        }
    }
}
