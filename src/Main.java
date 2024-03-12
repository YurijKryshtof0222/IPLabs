import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Перехідний стан системи 4 -> 5");

        TreeSet<Integer> initialState = new TreeSet<>();
        initialState.add(5);
        Pair<TreeSet<Integer>> p4_p3 = matrixAndItsTenses(Constants.folds1, initialState, "P4", "P3", "P5");
        Pair<TreeSet<Integer>> p2_k2 = matrixAndItsTenses(Constants.folds2, p4_p3.getFirst(), "P2", "K2", "P4");
        Pair<TreeSet<Integer>> p1_k3 = matrixAndItsTenses(Constants.folds3, p4_p3.getSecond(), "P1", "K3", "P3");
        Pair<TreeSet<Integer>> k4_k3 = matrixAndItsTenses(Constants.folds3, p2_k2.getFirst(), "K4", "K3", "P2");
        Pair<TreeSet<Integer>> k2_k1 = matrixAndItsTenses(Constants.folds3, p1_k3.getFirst(), "K2", "K1", "P2");
    }

    public static Pair<TreeSet<Integer>> findTenses(int[][] matrix, TreeSet<Integer> states) {
        TreeMap<Integer, ArrayList<Cell>> stateAndCellsMap = new TreeMap<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int currState = matrix[i][j];
                if (states.contains(currState)) {
                    Cell tempCell = new Cell(i, j);
                    if (!stateAndCellsMap.containsKey(currState)) {
                        ArrayList<Cell> list = new ArrayList<>();
                        list.add(tempCell);
                        stateAndCellsMap.put(currState, list);
                    } else {
                        ArrayList<Cell> list = stateAndCellsMap.get(currState);
                        if (isTense(tempCell, list)) {
                            list.add(tempCell);
                        }
                    }
                }
            }
        }

        Pair<TreeSet<Integer>> resultStates = new Pair<>(new TreeSet<>(), new TreeSet<>());

        for (ArrayList<Cell> entry : stateAndCellsMap.values()) {
            for (Cell tempCell: entry) {
                resultStates.getFirst().add(tempCell.getI() + 1);
                resultStates.getSecond().add(tempCell.getJ() + 1);
            }
        }
        return resultStates;
    }

    public static boolean isTense(Cell currCell, List<Cell> list) {
        for (Cell tempCell: list) {
            if (currCell.getI() == tempCell.getI())
                return false;
            if (currCell.getJ() >= tempCell.getJ())
                return false;
        }
        return true;
    }

    public static Pair<TreeSet<Integer>> matrixAndItsTenses(int[][] matrix, TreeSet<Integer> states, String first, String second, String out) {
        System.out.println(out + " <-\n");
        for (int[] ints : matrix) {
            System.out.print("\t");
            for (int anInt : ints) {
                System.out.print(anInt + ",\t");
            }
            System.out.println();
        }
        Pair<TreeSet<Integer>> pairOfTenses = findTenses(matrix, states);
        TreeSet<Integer> firstOfTenses = pairOfTenses.getFirst();
        TreeSet<Integer> secondOfTenses = pairOfTenses.getSecond();

        System.out.printf("\n%s = %s, %s = %s\n\n", first, firstOfTenses, second, secondOfTenses);
        return pairOfTenses;
    }
}