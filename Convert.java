/**
 * Created by Jordan on 2016-12-10.
 */
public class Convert {

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

}
