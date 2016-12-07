import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel {
    private BufferedImage imgBackground;
    ArrayList<Planet> planets = new ArrayList<Planet>();
    ArrayList<Player> players = new ArrayList<Player>();
    private int[] planetID = new int[2]; //Indicates on which planet the respective player is on
    private int[][] keys = new int[2][2]; //Keyboard inputs for player 1
    private int playerbounds = 20; //Used for player collision
    private boolean finished;
    private BufferedImage[] playerImages = new BufferedImage[4];
    private BufferedImage[] tumbleImages = new BufferedImage[4];
    public Game(JFrame frame) {
        imgBackground = ResourceLoader.loadImage("background.png");
        finished = false;
        generatePlanets();
        addPlayers(frame);
        for (int i=1; i<=4; i++) { //Load Hamster Animation
            playerImages[i-1] = ResourceLoader.loadImage("Hamster" + i + ".png");
            tumbleImages[i-1] = ResourceLoader.loadImage("Tumble" + i + ".png");
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imgBackground, 0, 0, null);
        for(Planet i : planets){
            i.paint(g);
        }
        for(Player i : players){
            i.paint(g);
        }
    }

    public void move() {
        for(int i = 0; i < players.size(); i++){
            players.get(i).move(planetID[i]);
            if(players.get(i).getJumping()){
                for(int j= 0; j < planets.size(); j++) {
                    if(j != planetID[i])
                        if(players.get(i).checkCollision(planets.get(j).getpLocation().x, planets.get(j).getpLocation().y, planets.get(j).getRadius(),planets.get(j).getBbounds())){
                            planetID[i] = j;
                            break;
                        }
                }
            }
        }
        collision(); //Check player Collisions
    }
    public boolean getFinished(){
        return finished;
    }
    public void setFinished(boolean finished){
        this.finished = finished;
    }
    public void collision() {
        if (planetID[0] == planetID[1]) {
            if (players.get(0).getAngle() > players.get(1).getAngle() - playerbounds && players.get(0).getAngle() < players.get(1).getAngle() + playerbounds)  {
                if(players.get(0).getAcceleration() > players.get(1).getAcceleration()) {
                    Winner(1);
                }else Winner(2);
            }
        }
    }
    public void addPlayers(JFrame frame){
        planetID[0] = 0;
        planetID[1] = 1;
        players.add(new Player(planets.get(planetID[0]).getpLocation().x, planets.get(planetID[0]).getpLocation().y, (int)planets.get(planetID[0]).getRadius(),planets.get(planetID[0]).getBbounds(), playerImages, tumbleImages, keys[0]));
        players.add(new Player(planets.get(planetID[1]).getpLocation().x, planets.get(planetID[1]).getpLocation().y, (int)planets.get(planetID[1]).getRadius(),planets.get(planetID[1]).getBbounds(), playerImages, tumbleImages, keys[1]));
        setPlayerKeys();
        for(int i = 0; i < players.size(); i++){
            players.get(i).addKeyListener(frame); //Enables use of Keyboard inputs for players
        }
    }
    public void setPlayerKeys(){
        //Player1
        keys[0][0] = KeyEvent.VK_A; //Move
        keys[0][1] = KeyEvent.VK_S; //Jump
        //Player 2
        keys[1][0] = KeyEvent.VK_O; //Move
        keys[1][1] = KeyEvent.VK_P; //Jump
    }
    public void generatePlanets(){
        planets.add(new Planet(56, 74, 100, 6, ResourceLoader.loadImage("planets/orangeplanet.png")));
        planets.add(new Planet(525, 356, 50, 20, ResourceLoader.loadImage("planets/testpurpleplanet.png")));
        planets.add(new Planet(550, 88, 75, 10, ResourceLoader.loadImage("planets/greenplanet.png")));
        planets.add(new Planet(250, 350, 50,14, ResourceLoader.loadImage("planets/redplanet.png")));
    }
    public void Winner(int playerID){
        System.out.println("Winner is " + playerID); //Do winning stuff here
        finished = true;
    }
}
