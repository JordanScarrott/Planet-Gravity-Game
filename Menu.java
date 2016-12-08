import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Menu extends JPanel implements KeyListener{
    ArrayList<Planet> planets = new ArrayList<Planet>();
    private boolean finished;
    private int cursY = 110;
    private BufferedImage[] playerSprites = new BufferedImage[4];
    private BufferedImage imgBackground = ResourceLoader.loadImage("background.png");
    private BufferedImage menuPane = ResourceLoader.loadImage("MenuScreen/TExtMenuThing.png");
    private BufferedImage playImg = ResourceLoader.loadImage("MenuScreen/playbutton.png");
    private BufferedImage optImg = ResourceLoader.loadImage("MenuScreen/optionsbutton.png");
    private BufferedImage quitImg = ResourceLoader.loadImage("MenuScreen/Exitbutton.png");

    private long currentTime;
    private long lastTime = 0;
    private double delay = 100;

    private long nSpriteTime;
    private long lSpriteTime = 0;
    private int currentSprite;

    private boolean levelSelect;

    private JFrame jframe;

    public Menu(JFrame frame){
        addKeyListener(frame);
        jframe = frame;
        levelSelect = false;
        finished = false;
        for (int i=1; i<4; i++) {
            playerSprites[i-1] = ResourceLoader.loadImage("Hamster" + i + ".png");
        }
    }

    public void addKeyListener(JFrame frame) {
        frame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            if (cursY > 110) {
                cursY -= 150;
            }
            else {
                cursY = 410;
            }
            if (currentTime-lastTime>=delay) {
                ResourceLoader.loadAudio("cursorMove.wav");
                lastTime = currentTime;
            }
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            if (cursY < 410) {
                cursY += 150;
            }
            else {
                cursY = 110;
            }
            if (currentTime-lastTime>=delay) {
                ResourceLoader.loadAudio("cursorMove.wav");
                lastTime = currentTime;
            }
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (!levelSelect) {
                if (cursY == 110) {
                    if (currentTime - lastTime >= delay) {
                        ResourceLoader.loadAudio("selectSound.wav");
                        lastTime = currentTime;
                    }
                    levelSelect = true;
                } else if (cursY == 410) {
                    jframe.dispatchEvent(new WindowEvent(jframe, WindowEvent.WINDOW_CLOSING));
                }
            }
            else {
                if (currentTime - lastTime >= delay) {
                    ResourceLoader.loadAudio("selectSound.wav");
                    lastTime = currentTime;
                }
                planets = LevelCreator.generateLevel((cursY-110)/150);
                finished = true;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    public boolean getFinished(){
        return finished;
    }
    public ArrayList<Planet> getPlanets(){
        return planets;
    }
    public void setFinished(boolean finished){
        this.finished = finished;
    }

    public void move(){
        currentTime = System.currentTimeMillis();
        nSpriteTime = System.currentTimeMillis();

        if (nSpriteTime-lSpriteTime>=delay) {
            lSpriteTime = nSpriteTime;
            if (currentSprite < 2) {
                currentSprite++;
            }
            else {
                currentSprite = 0;
            }
        }
    }
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(imgBackground, 0, 0, null);
        g.drawImage(menuPane, 215, 0, null);
        if (!levelSelect) {
            g.drawImage(playImg, 260, 90, null);
            g.drawImage(optImg, 260, 240, null);
            g.drawImage(quitImg, 260, 390, null);
        }
        else {
            g.drawImage(playImg, 260, 90, null);
            g.drawImage(playImg, 260, 240, null);
            g.drawImage(playImg, 260, 390, null);
        }
        g.drawImage(playerSprites[currentSprite], 210, cursY, null);
    }
}
