import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by Jack on 09/12/2016.
 */
public class GameOver extends JPanel implements KeyListener {

    private BufferedImage imgBackground = ResourceLoader.loadImage("background.png");

    private boolean finished;
    private JFrame jframe;

    public GameOver(JFrame frame) {
        frame.addKeyListener(this);
        jframe = frame;
        finished = false;

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imgBackground, 0, 0, null);
        g.setFont(new Font("Arial", 0, 90));
        g.setColor(Color.WHITE);
        if (Game.getWinner() != 0) {
            g.drawString("PLAYER " + Game.getWinner() + " WINS!", 30, 300);
        }
        else {
            g.drawString("GAME IS TIE", 100, 300);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            finished = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public boolean getFinished(){
        return finished;
    }

    public void setFinished(boolean finished){
        this.finished = finished;
    }
}
