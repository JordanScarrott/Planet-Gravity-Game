import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel implements KeyListener {

    ArrayList<Planet> planets = new ArrayList<Planet>();
    private Player player;
    private BufferedImage[] playerImages = new BufferedImage[4];
    private int planetID;

    public Game(JFrame frame) {
        frame.addKeyListener(this);
        planetID = 0;
        planets.add(new Planet(300, 200, 100, ResourceLoader.loadImage("orangeplanet.png")));
        planets.add(new Planet(0, 0, 100, ResourceLoader.loadImage("orangeplanet.png")));
        planets.add(new Planet(500, 0, 100, ResourceLoader.loadImage("orangeplanet.png")));
        for (int i=1; i<4; i++) {
            playerImages[0] = ResourceLoader.loadImage("Hamster" + i + ".png");
        }
        player = new Player(300, 200, 100, playerImages);
        ResourceLoader.loadAudio("bgm.wav");
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
