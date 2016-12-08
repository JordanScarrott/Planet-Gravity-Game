import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    static JFrame frame = new JFrame();
    static JFrame currentFrame;

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
        ResourceLoader.loadAudio("bgm.wav");
        //Loop
        while(true) {
            menu = new Menu(frame);
            Menu(frame, menu);
            game = new Game(frame);
            Game(frame, game);
        }
    }
    public static void Menu(JFrame frame, Menu menu){
        currentFrame = frame;
        currentFrame.add(menu);
        menu.setVisible(true);
        currentFrame.revalidate();
        while(!menu.getFinished()){
            menu.repaint();
            menu.move();
            try {
                Thread.sleep(20);
            }catch(Exception e){

            }
        }
        menu.setFinished(false);
        currentFrame.remove(menu);
        currentFrame = null;
    }
    public static void Game(JFrame frame, Game game){
        currentFrame = frame;
        currentFrame.add(game);
        game.setVisible(true);
        currentFrame.revalidate();
        currentFrame.repaint();
        while(!game.getFinished()){
            game.repaint();
            game.move();
            try {
                Thread.sleep(20);
            }catch(Exception e){

            }
        }
        game.setFinished(false);
        currentFrame.remove(game);
        currentFrame = null;
    }
}
