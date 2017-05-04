package ants;

import evolution.Algorithm;
import evolution.Population;
import evolution.SquaresHolder;
import rendering.Square;
import rendering.View;

import java.io.IOException;
import java.util.List;

/**
 * Created by Paul on 4/19/17.
 */
public class AntsView extends View {

    private List<Square> squares;
    private Map map;

    int mutations = -1;

    private AntsView() throws IOException {
        super();
        map = new Map();
    }

    @Override
    public List<List<Square>> getSolutions() {
        if (map == null) {
            return null;
        }
        mutations++;
        setTitle("Evolved " + mutations + " times");
        return map.doAntStuff();
    }

    public static void main(String[] args) throws IOException {
        new AntsView();
    }
}
