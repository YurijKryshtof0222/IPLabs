package Lab2;

public class Matrix {
    private int[][] matrix;

    public Matrix(int[][] matrix) {
        this.matrix = Util.copyMatrix(matrix);
    }

    public int[][] getMatrix() {
        return Util.copyMatrix(matrix);
    }

    public int rows() {
        return matrix.length;
    }

    public int cols() {
        return matrix[0].length;
    }

    public int[][] turn_180_degrees() {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[rows - 1 - i][cols - 1 - j];
            }
        }

        return result;
    }

    public int[][] flipHorizontal() {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[rows - 1 - i][j];
            }
        }

        return result;
    }
}
