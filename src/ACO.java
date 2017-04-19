import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Paul on 4/19/17.
 */
public class ACO {

    public static List<List<Square>> getMock(List<Square> squares) {
        List<Square> l1 = new ArrayList<>(squares);
        List<Square> l2 = new ArrayList<>(squares);
        List<Square> l3 = new ArrayList<>(squares);
        Collections.shuffle(l1);
        Collections.shuffle(l2);
        Collections.shuffle(l3);
        int size = l1.size();
        for (int i = 1; i <= size; i++) {
            if (Math.random() > .5) {
                l1.remove(0);
            }
            if (Math.random() > .5) {
                l2.remove(0);
            }
            if (Math.random() > .5) {
                l3.remove(0);
            }
        }
        return Arrays.asList(l1, l2, l3);
    }
}
