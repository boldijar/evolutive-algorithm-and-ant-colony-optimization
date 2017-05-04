package evolution;

import rendering.Square;
import rendering.View;

import java.io.IOException;
import java.util.List;

/**
 * Created by Paul on 4/19/17.
 */
public class EvolutionView extends View {

    private List<Square> squares;
    private Population population;

    int mutations = -1;

    private EvolutionView() throws IOException {
        super();
        squares = Square.readAll();
        SquaresHolder.squares = squares;
        population = new Population(10000, true);
    }

    @Override
    public List<List<Square>> getSolutions() {
        if (population == null) {
            return null;
        }
        mutations++;
        if (mutations > 0) {
            population = Algorithm.evolvePopulation(population);
        }
        setTitle("Evolved " + mutations + " times");
        return population.toSquares(5);
    }

    public static void main(String[] args) throws IOException {
        new EvolutionView();
    }
}
