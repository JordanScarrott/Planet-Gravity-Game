import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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