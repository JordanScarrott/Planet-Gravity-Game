import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.Random;

public abstract class ResourceLoader {

    protected static BufferedImage loadImage(String file) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream filePath = loader.getResourceAsStream("res/images/" + file);
        BufferedImage img = null;
        try {
            img = ImageIO.read(filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
    * Gets a random image from the specified folder
    * @return null if the folderPath specified is not a folder
    *   else returns a random BufferedImage from the folder
    */
    protected static BufferedImage getRandomImage(String folderPath) {
        File folderToSearch = new File(folderPath);
        System.out.println(folderToSearch.getAbsolutePath());

        if (!folderToSearch.isDirectory()) return null;

        Random rand = new Random();
        int randomFileNumber = rand.nextInt(folderToSearch.listFiles().length);
        System.out.println(randomFileNumber);
        
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream filePath = loader.getResourceAsStream(folderToSearch.listFiles()[randomFileNumber].getPath());
        BufferedImage img = null;
        try {
            img = ImageIO.read(filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    protected static void loadAudio(String file) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(loader.getResourceAsStream("res/sound/" + file));
            clip.open(inputStream);
            clip.start();
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException exx) {
            exx.printStackTrace();
        }
    }
}
