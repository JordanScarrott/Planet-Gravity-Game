import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public abstract class ResourceLoader {

    protected static BufferedImage loadImage(String file) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream filePath = loader.getResourceAsStream("res/images/" + file);
        BufferedImage img = null;
        try {
            img = ImageIO.read(filePath);
        } catch (IOException e) {
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

        // Return null if this method was not supplied with a directory
        if (!folderToSearch.isDirectory()) {
            System.out.println("ResourceLoader - THIS IS NOT A DIR");
            return null;
        }

        File[] imageFiles = folderToSearch.listFiles();
        Random rand = new Random();
        int randomFileNumber;

        // Make sure we don't pick a folder instead of a file
        do {
            randomFileNumber = rand.nextInt(imageFiles.length);
        } while(imageFiles[randomFileNumber].isDirectory());

        // Prepare string to send to the loadImage() method
        String[] pathSegments = folderPath.split("images/");
        String imageFileName = imageFiles[randomFileNumber].getName();

        return loadImage(pathSegments[pathSegments.length-1] + "/" + imageFileName);
    }

    protected static BufferedImage getRandomPlanet() {
        return getRandomImage("src/res/images/Planets");
    }
    
    protected static void loadAudio(String file) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(loader.getResourceAsStream("res/sound/" + file));
            clip.open(inputStream);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exx) {
            exx.printStackTrace();
        }
    }
}
