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
         Menu menu = new Menu(frame);
         Game game = new Game(frame);
        //Loop
        while(true) {
            menu = new Menu(frame);
            Menu(frame, menu);
            game = new Game(frame);
            Game(frame, game);
        }
    }
    public static void Menu(JFrame frame, Menu menu){
        frame.add(menu);
        menu.setVisible(true);
        frame.revalidate();
        while(!menu.getFinished()){
            menu.repaint();
            menu.move();
            try {
                Thread.sleep(20);
            }catch(Exception e){

            }
        }
        menu.setFinished(false);
        frame.remove(menu);
    }
    public static void Game(JFrame frame, Game game){
        frame.add(game);
        game.setVisible(true);
        frame.revalidate();
        frame.repaint();
        while(!game.getFinished()){
            game.repaint();
            game.move();
            try {
                Thread.sleep(20);
            }catch(Exception e){

            }
        }
        game.setFinished(false);
        frame.remove(game);
    }
}
