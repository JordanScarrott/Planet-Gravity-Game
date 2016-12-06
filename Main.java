import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static JFrame frame = new JFrame();

    public static void main(String[] args) throws InterruptedException {
        frame.setSize(800, 600);  // Window Size
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        runnit();
    }

    public static void runnit() throws InterruptedException {
        final Game game = new Game(frame);
        //Loop
        Timer animationTimer = new Timer(20, new ActionListener() { //Games Refresh Rate
            public void actionPerformed(ActionEvent event) {
                game.repaint(); //Repaints everything in the game
                game.move();    //Moves everything in the game
            }
        });
        frame.add(game);
        game.setVisible(true);
        frame.revalidate();
        frame.repaint();
        animationTimer.start();
    }
}
