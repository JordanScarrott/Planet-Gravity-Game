import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ResourceLoader {

    protected BufferedImage loadImage(String file) {

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
}
