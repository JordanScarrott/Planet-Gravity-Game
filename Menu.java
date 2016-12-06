import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Menu extends JPanel implements KeyListener{
    private boolean finished;
    public Menu(JFrame frame){
        addKeyListener(frame);
        finished = false;
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
        if(keyCode == KeyEvent.VK_M){
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
    public void move(){

    }
    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(100,100, 100, 100);
    }
}
