package Lab2;

public class Encoder {
    private Stencil stencil;
    private String textToEncode;
    private int rows;
    private int cols;

    private int[][] codeStr;

    public Encoder(Stencil stencil, String textToEncode) {
        this.stencil = stencil;
        this.textToEncode = textToEncode;

        this.rows = textToEncode.length() / (stencil.rows() * stencil.cols());
        this.cols = textToEncode.length() / rows;

    }


    private static int[][] initCodeStr(int rows, int cols, String textToEncode) {
        int[][] result = new int[rows][cols];

        int temp = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = textToEncode.charAt(temp++);
            }
        }

        return result;
    }
}
