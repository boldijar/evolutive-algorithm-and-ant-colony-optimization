package evolution;

import rendering.Randomer;
import rendering.Square;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Paul on 4/19/17.
 */
public class Algorithm {


    public static void main(String[] args) {
        SquaresHolder.squares = Arrays.asList(new Square(10, Color.RED),
                new Square(30, Color.RED),
                new Square(40, Color.BLUE),
                new Square(5, Color.GREEN),
                new Square(2000, Color.CYAN),
                new Square(35, Color.GRAY),
                new Square(100, Color.BLUE),
                new Square(5, Color.DARK_GRAY));


        Randomer.getRandom().setSeed(0);
        Population population = new Population(10000, true);

        population = Algorithm.evolvePopulation(population);
        population = Algorithm.evolvePopulation(population);
        population = Algorithm.evolvePopulation(population);
        population = Algorithm.evolvePopulation(population);
        population = Algorithm.evolvePopulation(population);
        assert population.size() == 10000;
        assert population.getFittest().getFitness() == 7;
        population.getFittest().showGenesAndFitness();
    }

    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;

    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);

        newPopulation.saveIndividual(0, pop.getFittest());

        int offset = 1;
        for (int i = offset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }
        for (int i = offset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    private static void mutate(Individual indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Randomer.getRandom().nextDouble() <= mutationRate) {
                // Create random gene
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }

    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        for (int i = 0; i < indiv1.size(); i++) {
            if (Randomer.getRandom().nextDouble() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    private static Individual tournamentSelection(Population pop) {
        Population tournament = new Population(tournamentSize, false);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        return tournament.getFittest();
    }

}
