package Lab3;

import java.util.Arrays;

public class Decoder {
    private String keyToDecode;
    private String newKeyToDecode;

    private int[] keyToDecodeArr;
    private int[][] inputLeft;
    private int[][] inputRight;


    public Decoder(String keyToDecode, int[][] inputLeft, int[][] inputRight) {
        this.keyToDecode = keyToDecode;
        this.newKeyToDecode = cyclicShiftRight(keyToDecode);

        this.keyToDecodeArr = Util.convertBinaryStringToIntArray(keyToDecode);
        this.inputLeft = inputLeft;
        this.inputRight = inputRight;
    }

    public int[][] temp() {
        int[][] result = new int[inputLeft.length][inputLeft.length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < keyToDecodeArr.length; j++) {
                result[i][j] = Util.xor(inputLeft[i][j], keyToDecodeArr[j]);
            }
        }

        return result;
    }

    public int[][] outputLeft() {
        int[][] result = new int[inputLeft.length][inputLeft.length];

        int[][] temp = temp();

        for (int i = 0; i < result.length; i++) {
            for (int j =0; j < temp[i].length; j++) {
                result[i][j] = Util.xor(inputRight[i][j], temp[i][j]);
            }
        }

        return result;
    }

    public int[][] outputRight() {
        return Util.copyMatrix(inputLeft);
    }

    private static String cyclicShiftRight(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        int length = input.length();

        StringBuilder shifted = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (i + 2) % length; // Визначення нового індексу
            shifted.append(input.charAt(index)); // Додавання символу на новий індекс
        }

        return shifted.toString();
    }

    public String getKeyWithCyclicShiftTo2colsRight() {
        return newKeyToDecode;
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
        System.out.println("Key for decoding: " + keyToDecode);

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
