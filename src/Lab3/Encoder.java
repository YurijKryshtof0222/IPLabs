package Lab3;

public class Encoder {
    private String keyToEncode;
    private String newKeyToEncode;


    private int[] keyToEncodeArr;
    private int[][] inputLeft;
    private int[][] inputRight;


    public Encoder(String keyToEncode, int[][] inputLeft, int[][] inputRight) {
        this.keyToEncode = keyToEncode;
        this.newKeyToEncode =
                keyToEncode.substring( 2) +
                keyToEncode.substring(0, 2);

        this.keyToEncodeArr = Util.convertBinaryStringToIntArray(keyToEncode);
        this.inputLeft = inputLeft;
        this.inputRight = inputRight;
    }

    public int[][] temp() {
        int[][] result = new int[inputLeft.length][inputLeft.length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < keyToEncodeArr.length; j++) {
                result[i][j] = Util.xor(inputRight[i][j], keyToEncodeArr[j]);
            }
        }

        return result;
    }

    public int[][] outputLeft() {
        return Util.copyMatrix(inputRight);
    }

    public int[][] outputRight() {
        int[][] result = new int[inputLeft.length][inputLeft.length];

        int[][] temp = temp();

        for (int i = 0; i < result.length; i++) {
            for (int j =0; j < temp[i].length; j++) {
                result[i][j] = Util.xor(inputLeft[i][j], temp[i][j]);
            }
        }

        return result;
    }

    public String getKeyWithCyclicShiftTo2colsLeft() {
        return newKeyToEncode;
    }

    public String[][] strCodesArray() {
        String[][] result = new String[16][4];

        int[][] left = outputLeft();
        int[][] right = outputRight();

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = "";
            }
            for (int j = 0; j < 8; j++) {
                result[i][0] = result[i][0] + left[i][j];
            }
            for (int j = 8; j < left[i].length; j++) {
                result[i][1] = result[i][1] + left[i][j];
            }
            for (int j = 0; j < 8; j++) {
                result[i][2] = result[i][2] + right[i][j];
            }
            for (int j = 8; j < right[i].length; j++) {
                result[i][3] = result[i][3] + right[i][j];
            }
        }

        return result;
    }

    public void printInfo() {
        System.out.println("Key for encoding: " + keyToEncode);

        System.out.println("\tTemp\n");
        printMatrix(temp());
        System.out.println();

        System.out.println("\tL\n");
        printMatrix(outputLeft());
        System.out.println("\tR\n");
        printMatrix(outputRight());
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int col: row) {
                System.out.print(col + "\t\t");
            }
            System.out.println();
        }
        System.out.println();
    }

}
