package evolution;


import rendering.Randomer;

/**
 * Created by Paul on 4/19/17.
 */
public class Individual {

    private byte[] genes;
    private int size;

    public Individual() {
        size = SquaresHolder.squares.size();
        genes = new byte[size];
    }

    public void generate() {
        for (int i = 0; i < size; i++) {
            byte gene = Randomer.randomByte();
            genes[i] = gene;
        }
    }

    public byte getGene(int index) {
        return genes[index];
    }


    public int getSize() {
        return size;
    }

    public void showGenesAndFitness() {
        String output = "Genes: ";
        for (int i = 0; i < size; i++) {
            output += genes[i] + "";
        }
        output += " Fitness: " + getFitness();
        System.out.println(output);
    }

    public int getFitness() {
        return FitnessCalculator.getFitness(this);
    }

    public void setGene(int i, byte gene) {
        genes[i] = gene;
    }

    public int size() {
        return genes.length;
    }
}
