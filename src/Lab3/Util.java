package Lab3;

public class Util {
    public static int xor(int x, int y) {
        return Math.abs(x - y);
    }

    public static int[] convertBinaryStringToIntArray(String binaryString) {
        int[] intArray = new int[binaryString.length()];

        for (int i = 0; i < binaryString.length(); i++) {
            char c = binaryString.charAt(i);
            intArray[i] = Character.getNumericValue(c);
        }

        return intArray;
    }

    public static int[][] copyMatrix(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = matrix[i].clone();
        }
        return result;
    }

    public static <T> void printMatrix(T[][] matrix) {
        for (T[] row : matrix) {
            for (T col: row) {
                System.out.print(col + "\t\t");
            }
            System.out.println();
        }
        System.out.println();
    }

}
