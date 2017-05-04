package evolution;

import rendering.Randomer;
import rendering.Square;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Paul on 4/19/17.
 */
public class FitnessCalculator {

    public static void main(String[] args) {
        SquaresHolder.squares = Arrays.asList(new Square(10, Color.RED),
                new Square(30, Color.RED),
                new Square(40, Color.BLUE),
                new Square(5, Color.GREEN));

        Randomer.getRandom().setSeed(1);
        Individual individual = new Individual();
        for (int i = 0; i <= 10; i++) {
            individual.generate();
            individual.showGenesAndFitness();
        }
        assert individual.getFitness() == 4;
    }

    private static final int FITNESS_EMPTY = Integer.MIN_VALUE;
    private static final int PENALTY_SIZE = 2;
    private static final int PENALTY_COLOR = 1;

    public static int getFitness(Individual individual) {
        int fitness = 0;

        Square lastSquare = null;
        for (int i = 0; i < individual.getSize(); i++) {
            if (individual.getGene(i) == 0) {
                // not using this square
                continue;
            }
            Square currentSquare = SquaresHolder.squares.get(i);
            if (lastSquare != null) {
                if (currentSquare.size < lastSquare.size) {
                    fitness -= PENALTY_SIZE * i;
                } else {
                    fitness += PENALTY_SIZE;
                }
                if (currentSquare.color.equals(lastSquare.color)) {
                    fitness -= PENALTY_COLOR * i;
                } else {
                    fitness += PENALTY_COLOR;
                }
            }
            lastSquare = currentSquare;
        }
        if (lastSquare == null) {
            return FITNESS_EMPTY;
        }
        return fitness;
    }
}
