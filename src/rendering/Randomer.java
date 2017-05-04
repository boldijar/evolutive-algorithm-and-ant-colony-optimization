package rendering;

import java.util.Random;

/**
 * Created by Paul on 4/19/17.
 */
public class Randomer {

    private static Random random;

    public static Random getRandom() {
        if (random == null) {
            random = new Random();
        }
        return random;
    }

    public static byte randomByte() {
        return (byte) (getRandom().nextBoolean() ? 1 : 0);
    }
}
