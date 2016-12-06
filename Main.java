import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static JFrame frame = new JFrame();
    static byte gameState = 0;

    public static void main(String[] args) throws InterruptedException{
        frame.setSize(800,600);  // Window Size
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        runnit();
    }
    public static void runnit() throws InterruptedException {
        final Menu menu = new Menu(frame);
        final Game game = new Game(frame);

        //Loop
        Timer animationTimer = new Timer(20, new ActionListener() { //Games Refresh Rate
            public void actionPerformed(ActionEvent event) {
                if (gameState == 1) {
                    game.move();
                    game.repaint();
                }
                else if (gameState == 0) {
                    try {
                        frame.remove(game);
                        runMenu(game, menu);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        runMenu(game, menu);
        animationTimer.start();
    }
    public static void runMenu(Game game, Menu menu) throws InterruptedException{
        frame.add(menu);
        menu.setVisible(true);
        frame.revalidate();
        frame.repaint();

        while(gameState == 0) {
            menu.repaint();
            Thread.sleep(20);
        }
        frame.remove(menu);
        frame.add(game);
        game.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }
}
