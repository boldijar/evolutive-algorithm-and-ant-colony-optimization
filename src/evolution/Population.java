package evolution;

import rendering.Randomer;
import rendering.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Paul on 4/19/17.
 */
public class Population {

    public static void main(String[] args) {
        SquaresHolder.squares = Arrays.asList(new Square(10, Color.RED),
                new Square(30, Color.RED),
                new Square(40, Color.BLUE),
                new Square(5, Color.GREEN),
                new Square(13, Color.CYAN),
                new Square(35, Color.GRAY),
                new Square(100, Color.BLUE),
                new Square(5, Color.DARK_GRAY));


        Randomer.getRandom().setSeed(0);
        Population population = new Population(10000, true);
        population.getFittest().showGenesAndFitness();
    }

    private Individual[] individuals;

    public Population(int size, boolean initialize) {
        individuals = new Individual[size];
        if (!initialize) {
            return;
        }
        for (int i = 0; i < size; i++) {
            Individual newIndividual = new Individual();
            newIndividual.generate();
            saveIndividual(i, newIndividual);
        }
    }

    public List<List<Square>> toSquares(int size) {
        List<List<Square>> doubleList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Individual individual = individuals[i];
            List<Square> squares = new ArrayList<>();
            for (int j = 0; j < individual.size(); j++) {
                if (individual.getGene(j) == 1) {
                    squares.add(SquaresHolder.squares.get(j));
                }
            }
            doubleList.add(squares);
        }
        return doubleList;
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < individuals.length; i++) {
            if (fittest.getFitness() <= individuals[i].getFitness()) {
                fittest = individuals[i];
            }
        }
        return fittest;
    }

    public void saveIndividual(int i, Individual individual) {
        individuals[i] = individual;
    }

    public int size() {
        return individuals.length;
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }
}
