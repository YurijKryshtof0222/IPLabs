package Lab2;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Encoder {
    private int rows;
    private int cols;
    private String textToEncode;
    private String key;
    private int[][] charCodes;
    private int[][] encodedCodes;
    private int[] number;
    private int[][] T;
    private int[][] T1;


    public Encoder(Stencil stencil, String textToEncode) {
        this.rows = textToEncode.length() / (stencil.rows() * stencil.cols());
        this.cols = textToEncode.length() / rows;

        this.textToEncode = textToEncode;
        this.key = stencil.key();

        this.charCodes = initCharCode();
        this.encodedCodes = new int[rows][cols];
        this.number = new int[cols];

        this.T = initT();
        this.T1 = initT1();

        this.encodedCodes = Util.multiplyMatrices(charCodes, T);
    }

    private int[][] initT() {
        int[][] result = new int[cols][cols];

        String[] pairs = splitIntoPairs(key);
        for (int i = 0; i < pairs.length; i++) {
            number[i] = Integer.parseInt(pairs[i]);
        }
        for (int i = 0; i < pairs.length; i++) {
            for (int y = 0; y < pairs.length; y++) {
                if ((i + 1) == number[y]) {
                    result[i][y] = 1;
                } else {
                    result[i][y] = 0;
                }
            }
        }

        return result;
    }

    private int[][] initT1() {
        int[][] result = new int[cols][cols];

        String[] pairs = splitIntoPairs(key);
        for (int i = 0; i < pairs.length; i++) {
            number[i] = Integer.parseInt(pairs[i]);
        }
        for (int i = 0; i < pairs.length; i++) {
            for (int y = 0; y < pairs.length; y++) {
                if ((y + 1) == number[i]) {
                    result[i][y] = 1;
                } else {
                    result[i][y] = 0;
                }
            }
        }

        return result;
    }

    private int[][] initCharCode() {
        char[] charArray = textToEncode.toCharArray();
        int length = 0;

        int[][] result = new int[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int i = 0; i < cols; i++) {
                int code = encodeToWindows1251(String.valueOf(charArray[length]))[0];
                result[y][i] = code;
                length++;
            }
        }

        return result;
    }

    private String[] splitIntoPairs(String number) {
        int length = number.length();
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

    public void codeInInt() {
        System.out.println("Coded symbols: ");
        System.out.println("Decoded Str: ");
        int[] arr = new int[rows * cols];
        int l = 0;
        for (int i = 0; i < rows; i++) {
            for (int y = 0; y < cols; y++) {
                arr[l] = encodedCodes[i][y];
                l++;
            }
        }
        System.out.println(decodeFromWindows1251(arr));
    }



    public void decodeInCode() {
        int[][] decoded = Util.multiplyMatrices(encodedCodes, T1);
        int[] arr = new int[rows * cols];
        int l = 0;
        for (int i = 0; i < rows; i++) {
            for (int y = 0; y < cols; y++) {
                arr[l] = decoded[i][y];
                l++;
            }
        }
        System.out.println();
        System.out.println("Decoded into Symbols: ");
        System.out.println(decodeFromWindows1251(arr));
    }

    private static int[] encodeToWindows1251(String input) {
        byte[] windows1251Bytes = input.getBytes(Charset.forName("Windows-1251"));
        int[] windows1251Integers = new int[windows1251Bytes.length];
        for (int i = 0; i < windows1251Bytes.length; i++) {
            windows1251Integers[i] = windows1251Bytes[i] & 0xFF; // Convert byte to unsigned integer
        }
        return windows1251Integers;
    }

    private static String decodeFromWindows1251(int[] windows1251Integers) {
        byte[] windows1251Bytes = new byte[windows1251Integers.length];
        for (int i = 0; i < windows1251Integers.length; i++) {
            windows1251Bytes[i] = (byte) windows1251Integers[i];
        }
        return new String(windows1251Bytes, Charset.forName("Windows-1251"));
    }
}
