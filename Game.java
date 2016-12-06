import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel {

    ArrayList<Planet> planets = new ArrayList<Planet>();
    private Player player;
    private Player player2;
    private int planetID;
    private int planetID2;
    private int[] keys1 = new int[2]; //Keyboard inputs for player 1
    private int[] keys2 = new int[2];
    private BufferedImage[] playerImages = new BufferedImage[4];
    public Game(JFrame frame) {
        keys1[0] = KeyEvent.VK_A;
        keys2[0] = KeyEvent.VK_O;
        keys1[1] = KeyEvent.VK_S;
        keys2[1] = KeyEvent.VK_P;
        planetID = 0;
        planetID2 = 1;
        generatePlanets();

        for (int i=1; i<=4; i++) {
            playerImages[i-1] = ResourceLoader.loadImage("Hamster" + i + ".png");
        }
        player = new Player(250, 0, 100, playerImages, keys1, ResourceLoader.loadImage("test.png"));
        player2 = new Player(0, 0, 100, playerImages, keys2, ResourceLoader.loadImage("test.png"));

        player.addKeyListener(frame);
        player2.addKeyListener(frame);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for(Planet i : planets){
            i.paint(g);
        }
        player.paint(g);
        player2.paint(g);
    }

    public void move() {
        player.move(planetID);
        player2.move(planetID2);
        collision();
        if(player.getJumping()){
            for(int i= 0; i < planets.size(); i++) {
                if(i != planetID)
                    if(player.checkCollision(planets.get(i).getpLocation().x, planets.get(i).getpLocation().y)){
                        planetID = i;
                        break;
                    }

            }
        }
        if(player2.getJumping()){
            for(int i= 0; i < planets.size(); i++) {
                if(i != planetID2)
                    if(player2.checkCollision(planets.get(i).getpLocation().x, planets.get(i).getpLocation().y)){
                        planetID2 = i;
                        break;
                    }

            }
        }
    }
    public void collision() {
        if (planetID == planetID2) {
            if (player.getAngle() > player2.getAngle() - 20 && player.getAngle() < player2.getAngle() + 20)  {
                if(player.getAcceleration() > player2.getAcceleration()) {
                    System.out.println("player1 WINS");
                }else System.out.println("player2 WINS");
            }
        }
    }
    public void generatePlanets(){
        planets.add(new Planet(0, 0, 100, ResourceLoader.loadImage("background.png")));
        planets.add(new Planet(250, 0, 100, ResourceLoader.loadImage("planets/orangeplanet.png")));
        planets.add(new Planet(550, 0, 100, ResourceLoader.loadImage("planets/orangeplanet.png")));
        planets.add(new Planet(250, 250, 100, ResourceLoader.loadImage("planets/redplanet.png")));
        planets.add(new Planet(500, 500, 100, ResourceLoader.loadImage("planets/orangeplanet.png")));
        planets.add(new Planet(0, 500, 100, ResourceLoader.loadImage("planets/orangeplanet.png")));
        planets.add(new Planet(0,250, 100, ResourceLoader.loadImage("planets/orangeplanet.png")));
        planets.add(new Planet(500, 250, 100, ResourceLoader.loadImage("planets/orangeplanet.png")));
    }
}
