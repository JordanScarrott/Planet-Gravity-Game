import javafx.scene.transform.Affine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by Jordan on 2016-12-10.
 */
public class Convert {
    public static float scale;
    public static int screenWidth = 1366;
    public static int screenHeight = 768;
    public static int cropx = (int)((1366 - 1366*((float)768/768))/2);
    public static float virtualAspectRatio = (float)9/16;  //Virtual aspect ratio refers to the aspect ratio the game was made in
    /**
     * Maps a number from one range to another
     * @param x the number that you want to map to another range
     * @param a1 lower bound of the current range of x
     * @param a2 upper bound of the current range of x
     * @param b1 lower bound of the new range of x
     * @param b2 upper bound of the new range of x
     * @returns the value of x mapped to the new range
     * */
    public static float map(float x, float a1, float a2, float b1, float b2) {
        return b1 + (b2 - b1) * (x - a1) / (a2 - a1);
    }
    public static BufferedImage scaleImage(BufferedImage src){
        BufferedImage resizedImg = new BufferedImage((int)(screenWidth*1),screenHeight, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(src, 0, 0, (int)(screenWidth*1),screenHeight, null);
        g2.dispose();
        return resizedImg;
    }
    public static BufferedImage scaleImage(BufferedImage src, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w,h, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(src, 0, 0, w,h, null);
        g2.dispose();
        return resizedImg;
    }
    //Translation
    public static AffineTransform transform(double x, double y, AffineTransform previousTransform){
        AffineTransform transform = previousTransform;
        transform.translate(x, y);
        return transform;
    }
    //Translation/Rotation
    public static AffineTransform transform(int x, int y, float theta, int rotateX, int rotateY, AffineTransform previousTransform){
        AffineTransform transform = previousTransform;
        transform.translate(x, y);
        transform.rotate(theta, rotateX, rotateY);
        return transform;
    }
    //Translation/Rotation/Scale
    public static AffineTransform transform(double x, double y, float theta, int rotateX, int rotateY, double scaleX, double scaleY, AffineTransform previousTransform){
        AffineTransform transform = previousTransform;
        transform.translate(x, y);
        transform.rotate(theta, rotateX, rotateY);
        transform.scale(scaleX, scaleY);
        return transform;
    }
    //Translation/Scale
    public static AffineTransform transform(double x, double y, double scaleX, double scaleY, AffineTransform previousTransform){
        AffineTransform transform = previousTransform;
        transform.translate(x, y);
        transform.scale(scaleX, scaleY);
        return transform;
    }
    public static int scale(int x){
        return (int)(x*scale);
    }
    public static int scale(float x){
        return (int)(x*scale);
    }
    public static int getScreenWidth(){
        return screenWidth;
    }
    public static int getScreenHeight(){
        return screenHeight;
    }
    public static int getCropX(){
        return cropx;
    }
    public static float getVirtualAspectRatio(){
        return virtualAspectRatio;
    }
    public static void setScale(float s){
        scale = s;
    }
}