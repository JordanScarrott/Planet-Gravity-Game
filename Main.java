import javax.swing.*;


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
        Menu menu;
        Game game;
        GameOver gameOver;
        ResourceLoader.loadAudio("bgm.wav");
        //Loop
        while(true) {
            menu = new Menu(frame);
            Menu(frame, menu);
            game = new Game(frame, menu.getPlanets(), menu.getRounds(), menu.getPlayerAmount());
            Game(frame, game);
            gameOver = new GameOver(frame);
            GameOver(frame, gameOver);
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

    public static void GameOver(JFrame frame, GameOver gameOver){
        currentFrame = frame;
        currentFrame.add(gameOver);
        gameOver.setVisible(true);
        currentFrame.revalidate();
        currentFrame.repaint();
        while(!gameOver.getFinished()) {
            gameOver.repaint();
            try {
                Thread.sleep(20);
            }catch(Exception e){

            }
        }
        gameOver.setFinished(false);
        currentFrame.remove(gameOver);
        currentFrame = null;
    }
}
