import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Menu extends JPanel implements KeyListener {

    ResourceLoader resLoader;
    BufferedImage cursor;

    private int cursX = 250;
    private int cursY = 160;

    public Menu(JFrame frame) {
        frame.addKeyListener(this);
        resLoader.loadAudio("bgm.wav");
        cursor = resLoader.loadImage("cursor.png");
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(cursor, cursX, cursY, null);
        g.setFont(new Font("Arial", 0, 64));
        g.drawString("Play", 325, 200);
        g.drawString("Quit", 325, 400);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (cursY > 160) {
                cursY -= 200;
            }
            else {
                cursY = 360;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (cursY < 360) {
                cursY += 200;
            }
            else {
                cursY = 160;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cursY == 160) {
//                 Main.gameState = 1;
            }
            else if (cursY == 360) {
//                 Main.gameState = -1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
