import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Menu extends JPanel implements KeyListener {
    private final int MAX_PLAYERS = 8;
    private final int MAX_ROUNDS = 7;
    private final int MAX_GAMESPEED = 4;
    ArrayList<Planet> planets = new ArrayList<Planet>();
    private int playerAmount = 2;
    private int roundsAmount = 3;
    private int gameSpeed = 2;
    private boolean finished;
    private int cursY = 110;
    private int levelY = 110;
    private BufferedImage[] playerSprites = new BufferedImage[4];
    private BufferedImage imgBackground = Convert.scaleImage(ResourceLoader.loadImage("background.png"));
    private BufferedImage menuPane = Convert.scaleImage(ResourceLoader.loadImage("MenuScreen/TExtMenuThing.png"), Convert.scale(354),Convert.scale(573));
    private BufferedImage playImg = Convert.scaleImage(ResourceLoader.loadImage("MenuScreen/playbutton.png"), Convert.scale(261),Convert.scale(92));
    private BufferedImage optImg = Convert.scaleImage(ResourceLoader.loadImage("MenuScreen/optionsbutton.png"), Convert.scale(261),Convert.scale(92));
    private BufferedImage quitImg = Convert.scaleImage(ResourceLoader.loadImage("MenuScreen/Exitbutton.png"), Convert.scale(261),Convert.scale(92));
    private BufferedImage LVLeclipseImg = Convert.scaleImage(ResourceLoader.loadImage("MenuScreen/LvlEclipse.png"), Convert.scale(249),Convert.scale(80));
    private BufferedImage LVLcloseEncountersImg = Convert.scaleImage(ResourceLoader.loadImage("MenuScreen/LvlCloseEncounters.png"), Convert.scale(249),Convert.scale(80));
    private BufferedImage LVLasteroidBeltImg = Convert.scaleImage(ResourceLoader.loadImage("MenuScreen/LvlAsteroidBelt.png"), Convert.scale(249),Convert.scale(80));
    private BufferedImage playerAmtImg = Convert.scaleImage(ResourceLoader.loadImage("MenuScreen/8HamsterColours.png"), Convert.scale(42),Convert.scale(42));
    private BufferedImage roundsAmtImg = Convert.scaleImage(ResourceLoader.loadImage("/Tumble1.png"), Convert.scale(42),Convert.scale(42));
    private long currentTime;
    private long lastTime = 0;
    private double delay = 100;
    private long nSpriteTime;
    private long lSpriteTime = 0;
    private int currentSprite;
    private boolean levelSelect;
    private boolean gameSetup;
    private JFrame jframe;

    public Menu(JFrame frame) {
        addKeyListener(frame);
        jframe = frame;
        levelSelect = false;
        gameSetup = false;
        finished = false;
        for (int i = 1; i < 4; i++) {
            playerSprites[i - 1] = Convert.scaleImage(ResourceLoader.loadImage("Hamster" + i + ".png"), Convert.scale(42),Convert.scale(42));
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
            } else {
                cursY = 410;
            }
            if (currentTime - lastTime >= delay) {
                ResourceLoader.loadAudio("cursorMove.wav");
                lastTime = currentTime;
            }
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            if (cursY < 410) {
                cursY += 150;
            } else {
                cursY = 110;
            }
            if (currentTime - lastTime >= delay) {
                ResourceLoader.loadAudio("cursorMove.wav");
                lastTime = currentTime;
            }
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            if (gameSetup) {
                if (cursY == 110) {
                    if (playerAmount < MAX_PLAYERS) playerAmount++;
                } else if (cursY == 260) {
                    if (roundsAmount < MAX_ROUNDS) roundsAmount++;
                } else if (cursY == 410) {
                    if (gameSpeed < MAX_GAMESPEED) gameSpeed++;
                }
                if (currentTime - lastTime >= delay) {
                    ResourceLoader.loadAudio("cursorMove.wav");
                    lastTime = currentTime;
                }
            }
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            if (gameSetup) {
                if (cursY == 110) {
                    if (playerAmount > 2) playerAmount--;
                } else if (cursY == 260) {
                    if (roundsAmount > 1) roundsAmount--;
                } else if (cursY == 410) {
                    if (gameSpeed > 1) gameSpeed--;
                }
                if (currentTime - lastTime >= delay) {
                    ResourceLoader.loadAudio("cursorMove.wav");
                    lastTime = currentTime;
                }
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
            } else if (!gameSetup) {
                if (currentTime - lastTime >= delay) {
                    ResourceLoader.loadAudio("selectSound.wav");
                    lastTime = currentTime;
                }
                levelY = cursY;
                gameSetup = true;
            } else {
                if (currentTime - lastTime >= delay) {
                    ResourceLoader.loadAudio("selectSound.wav");
                    lastTime = currentTime;
                }
                planets = LevelCreator.generateLevel((levelY - 110) / 150);
                finished = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g.fillRect(0, 0, Convert.getScreenWidth(), Convert.getScreenHeight());
        g2d.drawImage(imgBackground, Convert.getCropX(), 0, null);
        g2d.drawImage(menuPane, Convert.getCropX() + Convert.scale(223) , 0, null);
        if (!levelSelect) {
            g2d.drawImage(playImg,Convert.getCropX() +Convert.scale(260), 90, null);
            g2d.drawImage(optImg,Convert.getCropX() +Convert.scale(260), 240, null);
            g2d.drawImage(quitImg,Convert.getCropX() +Convert.scale(260), 390, null);
        } else if (!gameSetup) {
            g2d.drawImage(LVLeclipseImg, Convert.getCropX() +Convert.scale(260), 90,null);
            g2d.drawImage(LVLcloseEncountersImg, Convert.getCropX() +Convert.scale(260), 240,null);
            g2d.drawImage(LVLasteroidBeltImg, Convert.getCropX() +Convert.scale(260), 390,null);
        } else {
            g.setFont(new Font("Arial", 0, 20));
            g.setColor(Color.WHITE);
            g.drawString("Number of Players: ", 260, 90);
            g.drawString("Number of Rounds: ", 260, 240);
            g.drawString("Game Speed: ", 260, 390);
            for (int i = 0; i < playerAmount; i++)
                g.drawImage(playerAmtImg.getSubimage( 5, 0, 5, 5), 260 + i * 44, 140, null);
            for (int i = 0; i < roundsAmount; i++)
                g.drawImage(roundsAmtImg, 260 + i * 44, 290, null);
            for (int i = 0; i < gameSpeed; i++)
                g.drawImage(roundsAmtImg, 260 + i * 44, 440, null);
        }

        g2d.drawImage(playerSprites[currentSprite],Convert.getCropX() +Convert.scale(210), cursY, null);
    }

    public void move() {
        currentTime = System.currentTimeMillis();
        nSpriteTime = System.currentTimeMillis();

        if (nSpriteTime - lSpriteTime >= delay) {
            lSpriteTime = nSpriteTime;
            if (currentSprite < 2) {
                currentSprite++;
            } else {
                currentSprite = 0;
            }
        }
    }


    // Getters and Setters
    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

    public int getRounds() {
        return roundsAmount;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }
}
