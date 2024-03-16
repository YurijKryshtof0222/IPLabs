import java.util.*;

public class FoldMatrix {
    private int[][] matrix;
    private TreeMap<Integer, ArrayList<Cell>> stateAndCellsMap;
    private Pair<TreeSet<Integer>, TreeSet<Integer>> statesOfTenses;
    private final String firstInputName;
    private final String secondInputName;

    private double[] firstInputMatrix;
    private double[] secondInputMatrix;

    private final String name;

    FoldMatrix(int[][] matrix,
               TreeSet<Integer> states,
               String firstInputName,
               String secondInputName,
               double[] firstInputMatrix,
               double[] secondInputMatrix,
               String name) {
        this.matrix = matrix;

        this.stateAndCellsMap = initStateAndCellsMap(matrix, states);
        this.statesOfTenses = initStatesOfTenses(stateAndCellsMap);

        this.firstInputName = firstInputName;
        this.secondInputName = secondInputName;

        this.firstInputMatrix = firstInputMatrix;
        this.secondInputMatrix = secondInputMatrix;

        this.name = name;
    }

    FoldMatrix(int[][] matrix,
               TreeSet<Integer> states,
               String firstInputName,
               String secondInputName,
               String name) {
        this.matrix = matrix;

        this.stateAndCellsMap = initStateAndCellsMap(matrix, states);
        this.statesOfTenses = initStatesOfTenses(stateAndCellsMap);

        this.firstInputName = firstInputName;
        this.secondInputName = secondInputName;

        this.name = name;
    }

    public void setFirstInputMatrix(double[] firstInputMatrix) {
        this.firstInputMatrix = firstInputMatrix;
    }

    public void setSecondInputMatrix(double[] secondInputMatrix) {
        this.secondInputMatrix = secondInputMatrix;
    }

    private static TreeMap<Integer, ArrayList<Cell>> initStateAndCellsMap(int[][] matrix, TreeSet<Integer> states) {
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

        return stateAndCellsMap;
    }

    private static Pair<TreeSet<Integer>, TreeSet<Integer>> initStatesOfTenses(TreeMap<Integer, ArrayList<Cell>> stateAndCellsMap) {
        Pair<TreeSet<Integer>, TreeSet<Integer>> statesOfTenses = new Pair<>(new TreeSet<>(), new TreeSet<>());

        for (ArrayList<Cell> entry : stateAndCellsMap.values()) {
            for (Cell tempCell: entry) {
                statesOfTenses.getFirst().add(tempCell.getI() + 1);
                statesOfTenses.getSecond().add(tempCell.getJ() + 1);
            }
        }
        return statesOfTenses;
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

    public TreeSet<Integer> getFirst() {
        return statesOfTenses.getFirst();
    }

    public TreeSet<Integer> getSecond() {
        return statesOfTenses.getSecond();
    }

    public List<CellInfo> cellAndValueList() {
        List<CellInfo> result = new ArrayList<>();

        // private TreeMap<Integer, ArrayList<Cell>> stateAndCellsMap;
        for (Map.Entry<Integer, ArrayList<Cell>> mapEntry: stateAndCellsMap.entrySet()) {
            int state = mapEntry.getKey();
            for (Cell cell: mapEntry.getValue()) {
                double sum = firstInputMatrix[cell.getI()] + secondInputMatrix[cell.getJ()];
                result.add(new CellInfo(cell, state, sum));
            }
        }
        return result;
    }

    public double[] values() {
        double[] result = {0.0, 0.0, 0.0, 0.0, 0.0};
        TreeMap<Integer, Double> map = new TreeMap<>();
        for (CellInfo cellInfo: cellAndValueList()) {
            int state = cellInfo.getState();
            if (map.get(state) == null || map.get(state) > cellInfo.getResult()) {
                map.put(state, cellInfo.getResult());
            }
        }

        for(Map.Entry<Integer, Double> mapEntry: map.entrySet()) {
            result[mapEntry.getKey() - 1] = mapEntry.getValue();
        }

        return result;
    }

    public Cell findFirstMinCell() {
        double required = Double.MAX_VALUE;

        double[] values = values();
        for (int i = 0; i < values().length; i++) {
            if (values[i] != 0 && required > values[i]) {
                required = values[i];
            }
        }

        return findMinCell(required);
    }

    public Cell findCellAtIndex(int index) {
        return findMinCell(values()[index]);
    }

    private Cell findMinCell(double required) {
        for (CellInfo cellInfo: cellAndValueList()) {
            if (cellInfo.getResult() == required) {
                return cellInfo.getCell();
            }
        }

        return null;
    }

    public void printMinCell() {

    }

    public void printCellAndValueList() {
        for (CellInfo cellInfo: cellAndValueList()) {
            System.out.println(cellInfo);
        }
        System.out.println();
    }

    public void printMatrix() {
        System.out.println(name + " <-");
        for (int[] ints : matrix) {
            System.out.print("\t");
            for (int anInt : ints) {
                System.out.print(anInt + ",\t");
            }
            System.out.println();
        }
        Pair<TreeSet<Integer>, TreeSet<Integer>> pairOfTenses = statesOfTenses;
        TreeSet<Integer> firstOfTenses = pairOfTenses.getFirst();
        TreeSet<Integer> secondOfTenses = pairOfTenses.getSecond();

        for (Map.Entry<Integer, ArrayList<Cell>> entrySet : stateAndCellsMap.entrySet()) {
            System.out.print(entrySet.getKey() + ".)");
            for (Cell cell: entrySet.getValue()) {
                System.out.print(cell +", ");
            }
            System.out.println();
        }
        System.out.printf("%s = %s, %s = %s\n\n", firstInputName, firstOfTenses, secondInputName, secondOfTenses);
    }

}
