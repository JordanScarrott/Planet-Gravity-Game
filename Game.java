import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel implements KeyListener {
    private BufferedImage imgBackground;
    private static int rounds;
    private static int maxRounds;
    public static int gameSpeed;
    private static int winner;
    ArrayList<Planet> planets = new ArrayList<Planet>();
    ArrayList<Player> players = new ArrayList<Player>();
    private int[] planetID = new int[8]; //Indicates on which planet the respective player is on
    private int[][] keys = new int[8][2]; //Keyboard inputs for player 1
    private int playerbounds = 20; //Used for player collision
    private boolean finished;
    public Game(JFrame frame, ArrayList<Planet> planets, int rounds, int playersAmount, int gameSpeed) {
        frame.addKeyListener(this);
        this.rounds = rounds;
        this.gameSpeed = gameSpeed;
        maxRounds = rounds;
        this.planets = planets;
        imgBackground = ResourceLoader.loadImage("background.png");
        finished = false;
        addPlayers(frame, playersAmount);
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imgBackground, 0, 0, null);
        for(Planet i : planets){
            i.paint(g);
        }
        for(Player i : players){
            if(i.getAlive())i.paint(g);
        }
        //GUI
        g.setFont(new Font("Arial", 0, 20));
        g.setColor(Color.WHITE);
        g.drawString("Round " + (maxRounds-rounds + 1) + "/" + maxRounds, 680, 40);
        for(int i = 0; i < players.size(); i++) {
            if(players.get(i).getAlive())g.setColor(Color.WHITE);
            else g.setColor(Color.GRAY);
            g.drawString("P" + (i+1) + ": " + players.get(i).getWins(), 10 + 100*i, 550);
        }
        //
    }

    public void move() {
        System.out.println("p1: " + players.get(0).getRadVelocity() + "  p2: " + players.get(1).getRadVelocity());
        for(int i = 0; i < players.size(); i++) {
            if (players.get(i).getAlive()) {
                players.get(i).move(gameSpeed);
                if (players.get(i).getJumping()) {
                    if(checkPlayerInsideGrid(i))killPlayer(i);
                    for (int j = 0; j < planets.size(); j++) {
                        if (j != planetID[i])
                            if (players.get(i).checkCollision(planets.get(j))) {
                                planetID[i] = j;
                                break;
                            }
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
        for(int i = 0; i < players.size(); i++) {
            for(int j = i+1; j < players.size(); j++) {
                if (players.get(i).getAlive() && players.get(j).getAlive() && checkPlayerSpriteCollision(i, j)) {
                    findSlowerPlayer(i, j);
                }
            }
        }
    }
    public boolean checkPlayerInsideGrid(int i){
        if(players.get(i).getpLocation().x + 21 < 0
                || players.get(i).getpLocation().x - 21 > 800
                || players.get(i).getpLocation().y + 21 < 0
                || players.get(i).getpLocation().y + 21 > 600)return true;
        else return false;
    }
    public boolean checkPlayerSpriteCollision(int i, int j){
        if(MyVector.distanceSq(players.get(i).getpLocation(), players.get(j).getpLocation())< 42*42)return true;
        else return false;
    }
    public void findSlowerPlayer(int i, int j){
        if (players.get(i).getRadVelocity() > players.get(j).getRadVelocity()) {
            killPlayer(j);
        }
        else {
            killPlayer(i);
        }
    }
    public void killPlayer(int i){
        players.get(i).setAlive(false);
        System.out.println("player " + (i+1) + " was killed");
        ResourceLoader.loadAudio("death.wav");
        int x = checkIfWinner();
        if(x != -1)Winner(x);
    }
    public int checkIfWinner(){
        int x = 0;
        int j = 0;
        for(int i = 0; i < players.size(); i++){
            if(!players.get(i).getAlive())x++;
            else j = i;
        }
        if(x == players.size()-1)return j;
        else return -1;
    }
    public void addPlayers(JFrame frame, int playersAmount){
        for(int i = 0; i < playersAmount; i++) {
            randomSpawn(i);
            players.add(new Player(planets.get(planetID[i]), ResourceLoader.loadImage("animate2.png"), keys[i]));
            players.get(i).addKeyListener(frame);
        }
        setPlayerKeys();
    }
    public void randomSpawn(int i){ //Random a planet for player to spawn on

        do {
            planetID[i] = (int)(Math.random()*planets.size());
        } while (!stopPlayersSpawningTooClose(i));
        //System.out.println((int)(Math.random()*planets.size()));     //printing out values in menu screen, Investigate this
    }
    public boolean stopPlayersSpawningTooClose(int i){//Return false if players are too close
        for(int j = 0; j < i; j++){
            if(planetID[j] == planetID[i]) {
                return false;
            }
        }
        return true;
    }
    public void setPlayerKeys(){
        //Player1
        keys[0][0] = KeyEvent.VK_A; //Move
        keys[0][1] = KeyEvent.VK_S; //Jump
        //Player 2
        keys[1][0] = KeyEvent.VK_O; //Move
        keys[1][1] = KeyEvent.VK_P; //Jump
        //Player 3
        keys[2][0] = KeyEvent.VK_C; //Move
        keys[2][1] = KeyEvent.VK_V; //Jump
        //Player 4
        keys[3][0] = KeyEvent.VK_N; //Move
        keys[3][1] = KeyEvent.VK_M; //Jump
        //Player 5
        keys[4][0] = KeyEvent.VK_N; //Move
        keys[4][1] = KeyEvent.VK_M; //Jump
        //Player 6
        keys[5][0] = KeyEvent.VK_N; //Move
        keys[5][1] = KeyEvent.VK_M; //Jump
        //Player 7
        keys[6][0] = KeyEvent.VK_N; //Move
        keys[6][1] = KeyEvent.VK_M; //Jump
        //Player 8
        keys[7][0] = KeyEvent.VK_N; //Move
        keys[7][1] = KeyEvent.VK_M; //Jump
    }
    public void Winner(int playerID){
        players.get(playerID).win();
        rounds--;
        winner = playerID + 1;
        try{Thread.sleep(1000);}
        catch(Exception e){}
        if (rounds == 0) {
            finished = true;
        }
        else {
            for(int i = 0; i < players.size(); i++) {
                randomSpawn(i);
                players.get(i).randomAngle();
                players.get(i).spawn(planets.get(planetID[i]));
                if (!players.get(i).getAlive()) {
                    players.get(i).setAlive(true);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            finished = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void setRounds(int in_rounds) {
        rounds = in_rounds;
    }

    public static int getWinner() {
        return winner;
    }
}
