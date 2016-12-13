import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class pauseMenu extends JPanel implements KeyListener {
    private boolean paused;
    public pauseMenu(JFrame frame){
        frame.addKeyListener(this);
        paused = true;
    }
    public void move(){

    }
    public boolean getPaused(){
        return paused;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("work");
        paused = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
