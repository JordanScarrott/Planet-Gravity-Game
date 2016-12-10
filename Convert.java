/**
 * Created by Jordan on 2016-12-10.
 */
public class Convert {
    public static float scale;
    public static int screenWidth = 1366;
    public static int screenHeight = 768;
    public static int cropx = (int)((1366 - 800*((float)768/600))/2);
    public static float virtualAspectRatio = (float)3/4;  //Virtual aspect ratio refers to the aspect ratio the game was made in
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