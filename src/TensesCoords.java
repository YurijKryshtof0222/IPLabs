import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class TensesCoords {
    private double[] firstMatrix;
    private double[] secondMatrix;
    TreeMap<Integer, ArrayList<Cell>> stateAndCellsMap;
    private String firstName;
    private String secondName;
    private String thirdName;

    public TensesCoords(double[] firstMatrix,
                        double[] secondMatrix,
                        TreeMap<Integer, ArrayList<Cell>> stateAndCellsMap,
                        String firstName,
                        String secondName,
                        String thirdName) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        // TreeMap<Integer, ArrayList<Cell>> stateAndCellsMap;
        this.stateAndCellsMap = stateAndCellsMap;
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
    }

//    public double[] outputMatrix() {
//        double[] result = new double[5];
//        for (int i = 0; i < 5; i++) {
//            if (stateAndCellsMap.containsKey(i+1)) {
//                ArrayList<Cell> cellList = stateAndCellsMap.get(i+1);
//                double minCellValue = cellList.get(0).getI() + cellList.get(0).getJ();
//                Cell cellValue = cellList.get(0);
//                for (int j = 1; j < cellList.size(); j++) {
//                    double tempCellValue = cellList.get(j).getI() + cellList.get(j).getJ();
//                    if (tempCellValue < minCellValue) {
//
//                    }
//                }
//            } else {
//                result[i] = 0;
//            }
//        }
//    }


}
