import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel {
    private BufferedImage imgBackground;
    ArrayList<Planet> planets = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();
    private int[] planetID = new int[4]; //Indicates on which planet the respective player is on
    private int[][] keys = new int[4][2]; //Keyboard inputs for player 1
    private int playerbounds = 20; //Used for player collision
    private boolean finished;
    public Game(JFrame frame) {
        imgBackground = ResourceLoader.loadImage("background.png");
        finished = false;
        generatePlanets();
        addPlayers(frame);
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
        for (int i = 0; i < players.size(); i++){
            players.get(i).move();
            if (players.get(i).getJumping()){
                for (int j = 0; j < planets.size(); j++) {
                    if (players.get(i).getRelativePlanet() != planets.get(j)) {
                        if (players.get(i).checkCollision(planets.get(j))) {
                            System.out.println(planets.get(j).getpLocation());
                            planetID[i] = j;
                            break;
                        }
                    }
                }
            }
        }
        collision(); //Check player Collisions
    }

    public void collision() {
        for(int i = 0; i < players.size()-1; i++) {
            if (planetID[i] != -1){
                if (planetID[i] == planetID[i + 1]) {
                    if (players.get(i).getAngle() > players.get(i + 1).getAngle() - playerbounds && players.get(i).getAngle() < players.get(i + 1).getAngle() + playerbounds) {
                        if (players.get(i).getRadAcceleration() > players.get(i + 1).getRadAcceleration()) {
                            players.remove(i + 1);

                            planetID[i+1] = -1;
                            System.out.println("player " + (i + 2) + " was killed");
                        } else {
                            players.remove(i);
                            planetID[i] = -1;
                            System.out.println("player " + (i+1) + " was killed");
                        }
                    }
                }
            }
        }
    }

    public void addPlayers(JFrame frame){
        for(int i = 0; i < 4; i++) {
            randomSpawn(i);
            players.add(new Player(planets.get(planetID[i]), ResourceLoader.loadImage("TheSpriteSheet.png"), keys[i]));
        }
        setPlayerKeys();
        for(int i = 0; i < players.size(); i++){
            players.get(i).addKeyListener(frame); //Enables use of Keyboard inputs for players
        }
    }

    public void randomSpawn(int i){ //Random a planet for player to spawn on
        planetID[i] = (int)(Math.random()*planets.size());
        //System.out.println((int)(Math.random()*planets.size()));     //printing out values in menu screen, Investigate this
    }

    public void setPlayerKeys(){
        //Player1
        keys[0][0] = KeyEvent.VK_A; //Move
        keys[0][1] = KeyEvent.VK_S; //Jump
        //Player 2
        keys[1][0] = KeyEvent.VK_O; //Move
        keys[1][1] = KeyEvent.VK_P; //Jump
        //Player 3
        keys[2][0] = KeyEvent.VK_N; //Move
        keys[2][1] = KeyEvent.VK_M; //Jump
        //Player 4
        keys[3][0] = KeyEvent.VK_C; //Move
        keys[3][1] = KeyEvent.VK_V; //Jump
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

    // Getters and Setters
    public boolean getFinished(){
        return finished;
    }

    public void setFinished(boolean finished){
        this.finished = finished;
    }
}
