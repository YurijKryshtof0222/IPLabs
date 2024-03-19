package Lab1;

public final class Util {
    public static int[][] reversed(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int row = 0; row <= matrix.length / 2; row++) {
            result[row] = matrix[matrix.length - 1 - row];
            result[matrix.length - 1 - row] = matrix[row];
        }
        return result;
    }
}
