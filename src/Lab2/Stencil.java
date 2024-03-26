package Lab2;

public class Stencil {
    private Matrix matrix;
    private int quarter_of_len;
    private int[] fillValues;
    private int[][] encodeMatrix;

    public Stencil(Matrix matrix) {
        this.matrix = matrix;
        this.quarter_of_len = (matrix.rows() * matrix.cols()) / 4;
        this.fillValues = initFillValues(quarter_of_len);
        this.encodeMatrix = initEncodeMatrix(matrix, fillValues);
    }

    private static int[] initFillValues(int quarter_of_len) {
        int[] result = new int[4];
        for (int i = 1; i < 4; i++) {
            result[i] = quarter_of_len * i;
        }

        return result;
    }

    private static int[][] initEncodeMatrix(Matrix matrix, int[] fillValues) {
        int[][][] stencils = new int[4][matrix.rows()][matrix.cols()];
        stencils[0] = matrix.getMatrix();
        stencils[1] = matrix.turn_180_degrees();
        stencils[2] = matrix.flipHorizontal();
        stencils[3] = new Matrix(matrix.turn_180_degrees()).flipHorizontal();

        int[][] result = new int[matrix.rows()][matrix.cols()];

        Util.printMatrix(stencils[0]);
        Util.printMatrix(stencils[1]);
        Util.printMatrix(stencils[2]);
        Util.printMatrix(stencils[3]);

        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.cols(); j++) {
                for (int k = 0; k < fillValues.length; k++) {
                    if (stencils[k][i][j] != 0) {
                        result[i][j] = ++fillValues[k];
                        break;
                    }
                }
//                throw new RuntimeException("Stencil is not filled correctly");
            }
        }

        return result;
    }

    public int rows() {
        return matrix.rows();
    }

    public int cols() {
        return matrix.cols();
    }

    public void printEncodeMatrix() {
        Util.printMatrix(encodeMatrix);
    }

    public int[][] getEncodeMatrix() {
        return Util.copyMatrix(encodeMatrix);
    }

    public String key() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < encodeMatrix.length; i++ ) {
            for (int j = 0; j < encodeMatrix[i].length; j++) {
                int character = encodeMatrix[i][j];
                if (character < 10) {
                    result.append(0).append(character);
                } else {
                    result.append(character);
                }
            }
        }

        return result.toString();
    }

}
