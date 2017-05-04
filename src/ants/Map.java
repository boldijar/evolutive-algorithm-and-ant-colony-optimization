package ants;

import rendering.Randomer;
import rendering.Square;

import java.io.IOException;
import java.util.*;

/**
 * Created by Paul on 4/23/17.
 */
public class Map {
    private static final int PHEROMONE_EXPIRATION_INTERVAL = 5;
    private static final float PROBABILITY_STOP = 0.1f;
    private static final float PROBABILITY_STOP_IF_PROBLEM = 0.3f;
    private static final Integer PHEROMONE_PER_GOOD_SIZE = 1;
    private static final Integer PHEROMONE_PER_GOOD_COLOR = 2;
    private List<Square> squares;
    private HashMap<String, Integer> feromoneMap;
    private int iterations = 0;

    public static void main(String[] args) throws IOException {
        Map map = new Map();
        map.doAntStuff();
    }

    public Map() throws IOException {
        squares = Square.readAll();
        feromoneMap = new HashMap<>();
    }

    private Integer getRandomAvailablePosition(List<Integer> availablePositions, List<Integer> currentPositions) {
        Integer value;
        if (currentPositions.size() == 0) {
            // no logic needed for first position
            List<Integer> availablePositionsCopy = new ArrayList<>(availablePositions);
            Collections.sort(availablePositionsCopy, Comparator.comparingInt(o -> -squares.get(o).size));
            value = availablePositionsCopy.get(0);

        } else {
//            Integer position = Randomer.getRandom().nextInt(availablePositions.size());
//            value = availablePositions.get(position);
            value = getNextBestPosition(availablePositions, currentPositions);
        }
        availablePositions.remove(value);
        currentPositions.add(value);
        return value;

    }

    private Integer getNextBestPosition(List<Integer> availablePositions, List<Integer> currentPositions) {
        int lastValue = currentPositions.get(currentPositions.size() - 1);
        List<Integer> distributedValues = new ArrayList<>();
        distributedValues.addAll(availablePositions);
        for (Integer availablePosition : availablePositions) {
            String key = lastValue + "->" + availablePosition;
            int probability = feromoneMap.getOrDefault(key, 0);
            for (int i = 1; i <= probability; i++) {
                distributedValues.add(availablePosition);
            }
        }
        Collections.shuffle(distributedValues);
        return distributedValues.get(0);

    }

    private List<List<Square>> toSquares(List<Integer> positions) {
        List<Square> squares = new ArrayList<>();
        for (Integer value : positions) {
            squares.add(this.squares.get(value));
        }
        return Arrays.asList(squares);

    }

    public List<List<Square>> doAntStuff() {
        iterations++;
        List<Integer> availablePositions = new ArrayList<>();
        for (int i = 0; i < squares.size(); i++) {
            availablePositions.add(i);
        }
        List<Integer> currentPositions = new ArrayList<>();

        // choose starting point
        Integer startingPoint = getRandomAvailablePosition(availablePositions, currentPositions);

        while (Randomer.getRandom().nextDouble() > PROBABILITY_STOP && availablePositions.size() > 0) {
            // find next
            getRandomAvailablePosition(availablePositions, currentPositions);

            updatePheromoneMap(currentPositions);

            if (currentPositions.size() > 1 && Randomer.getRandom().nextDouble() > PROBABILITY_STOP_IF_PROBLEM) {
                int last = currentPositions.get(currentPositions.size() - 1);
                int beforeLast = currentPositions.get(currentPositions.size() - 2);
                if (squares.get(last).color.equals(squares.get(beforeLast).color)) {
                    currentPositions.remove(currentPositions.size() - 1);
                    break;
                }
                if (squares.get(last).size > squares.get(beforeLast).size) {
                    currentPositions.remove(currentPositions.size() - 1);
                    break;
                }
            }

        }
        System.out.println(feromoneMap);
        if (iterations % PHEROMONE_EXPIRATION_INTERVAL == 0) {
            Set<String> keys = feromoneMap.keySet();
            for (String key : keys) {
                if (feromoneMap.get(key) != null) {
                    feromoneMap.put(key, feromoneMap.get(key) - 1);
                }
            }
        }
        return toSquares(currentPositions);
    }

    private void updatePheromoneMap(List<Integer> currentPositions) {
        // update pheromone map from end to start
        if (currentPositions.size() <= 1) {
            return;
        }
        for (int i = currentPositions.size() - 1; i >= 1; i--) {
            int next = i - 1;
            String key = i + "->" + next;
            if (squares.get(i).size > squares.get(next).size) {
                feromoneMap.put(key, feromoneMap.getOrDefault(key, 0) + PHEROMONE_PER_GOOD_SIZE);
            }
            if (!squares.get(i).color.equals(squares.get(next).color)) {
                feromoneMap.put(key, feromoneMap.getOrDefault(key, 0) + PHEROMONE_PER_GOOD_COLOR);
            }
        }
    }

}
