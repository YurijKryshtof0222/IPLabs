package Lab2;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Main1 {

    private static int rows = 3;
    private static int colms = 60;

    private static String str = "І_діти_мам_і_бабусь_привітають!\nМудрість_яких_вони_обміняють\nНа_радість_веселого_спілкування,\nСлухняність,_довіру,_гарне_навчання,\nІ_вдома_всілякого_допомагання!?!?!?!?!?!?!?!?!?!?";
    private static Matrix matrix = new Matrix(new int[][]{
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 1, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 1, 1, 0, 0, 1}
    });
    private static Stencil stencil = new Stencil(matrix);
    private static String code = stencil.code();

    private static int[][] codeStr = new int[3][60];

    private static int[][] coded = new int[rows][colms];

    private static int[] number = new int[60];
    private static int[][] T = new int[60][60];
    private static int[][] T1 = new int[60][60];


    public static void main(String[] args) {
        System.out.println("Default string:\n" + str);
        initT();
        initT1();
        writeStrCode();
        System.out.println("");
        System.out.println("Str codes: ");
        printMatrix(codeStr);
        codeInInt();
        decodeInCode();
    }

    private static void decodeInCode() {
        int[][] decoded = multiplyMatrices(coded, T1);
        int[] arr = new int[rows * colms];
        int l = 0;
        for (int i = 0; i < rows; i++) {
            for (int y = 0; y < colms; y++) {
                arr[l] = decoded[i][y];
                l++;
            }
        }
        System.out.println("");
        System.out.println("Decoded into Symbols: ");
        System.out.println(decodeFromWindows1251(arr));
    }

    private static void codeInInt() {
        System.out.println("");
        System.out.println("Coded symbols: ");
        printMatrix(multiplyMatrices(codeStr, T));
        coded = multiplyMatrices(codeStr, T);
        System.out.println("");
        System.out.println("Decoded Str: ");
        int[] arr = new int[180];
        int l = 0;
        for (int i = 0; i < rows; i++) {
            for (int y = 0; y < colms; y++) {
                arr[l] = coded[i][y];
                l++;
            }
        }
        System.out.println(decodeFromWindows1251(arr));
    }


    private static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    private static void initT() {
        String[] pairs = splitIntoPairs(code);
        for (int i = 0; i < pairs.length; i++) {
            number[i] = Integer.parseInt(pairs[i]);
        }
        for (int i = 0; i < pairs.length; i++) {
            for (int y = 0; y < pairs.length; y++) {
                if ((i + 1) == number[y]) {
                    T[i][y] = 1;
                } else {
                    T[i][y] = 0;
                }
            }
        }
    }

    private static void initT1() {
        String[] pairs = splitIntoPairs(code);
        for (int i = 0; i < pairs.length; i++) {
            number[i] = Integer.parseInt(pairs[i]);
        }
        for (int i = 0; i < pairs.length; i++) {
            for (int y = 0; y < pairs.length; y++) {
                if ((y + 1) == number[i]) {
                    T1[i][y] = 1;
                } else {
                    T1[i][y] = 0;
                }
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    private static void writeStrCode() {
        char[] charArray = str.toCharArray();
        int length = 0;
        for (int y = 0; y < 3; y++) {
            for (int i = 0; i < 60; i++) {
                int code = encodeToWindows1251(String.valueOf(charArray[length]))[0];
                codeStr[y][i] = code;
                length++;
            }
        }
    }

    public static String[] splitIntoPairs(String number) {
        int length = number.length();
        int numPairs = (length + 1) / 2;
        ArrayList<String> pairsList = new ArrayList<>();

        for (int i = 0; i < length; i += 2) {
            if (i + 2 <= length) {
                String pair = number.substring(i, i + 2);
                if (pair.startsWith("0")) {
                    pairsList.add(pair.substring(1));
                } else {
                    pairsList.add(pair); // Add pair as is
                }
            } else {
                pairsList.add(number.substring(i, i + 1));
            }
        }
        String[] pairs = new String[pairsList.size()];
        pairs = pairsList.toArray(pairs);
        return pairs;
    }

    private static int[] encodeToWindows1251(String input) {
        byte[] windows1251Bytes = input.getBytes(Charset.forName("Windows-1251"));
        int[] windows1251Integers = new int[windows1251Bytes.length];
        for (int i = 0; i < windows1251Bytes.length; i++) {
            windows1251Integers[i] = windows1251Bytes[i] & 0xFF; // Convert byte to unsigned integer
        }
        return windows1251Integers;
    }

    // Decode from Windows-1251 integers
    private static String decodeFromWindows1251(int[] windows1251Integers) {
        byte[] windows1251Bytes = new byte[windows1251Integers.length];
        for (int i = 0; i < windows1251Integers.length; i++) {
            windows1251Bytes[i] = (byte) windows1251Integers[i];
        }
        return new String(windows1251Bytes, Charset.forName("Windows-1251"));
    }

}
