package Lab3;

import java.nio.charset.Charset;
import java.util.Arrays;

public class TextToEncode {
    private String text;

    public TextToEncode(String text) {
        this.text = text;
    }

    public Character[][] getTextAsArray() {
        Character[][] array = new Character[16][4];
        int index = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                array[i][j] = text.charAt(index++);
            }
        }
        return array;
    }

    public Integer[][] getCharacterCodes() {
        byte[] windows1251Bytes = text.getBytes(Charset.forName("Windows-1251"));
        Integer[][] codes = new Integer[16][4];

        int index = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                codes[i][j] = windows1251Bytes[index++] & 0xFF;
            }
        }
        return codes;
    }

    public static String getTextFromCharacterCodes(Integer[][] codes) {
        byte[] bytes = new byte[codes.length * codes[0].length];

        int iter = 0;
        for( int i =0; i < codes.length; i++) {
            for (int j =0; j < codes[i].length; j++) {
                bytes[iter++] = codes[i][j].byteValue();
            }
        }

        return new String(bytes, Charset.forName("Windows-1251"));
    }


    public String[][] getBinaryCharacterCodes() {
        Integer[][] codes = getCharacterCodes();
        String[][] binaryCodes = new String[16][4];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                String binaryString = Integer.toBinaryString(codes[i][j]);
                // Доповнення нулями зліва, якщо довжина рядка менша за 16
                binaryCodes[i][j] = String.format("%8s", binaryString).replace(' ', '0');
            }
        }
        return binaryCodes;
    }

    public static Integer[][] getCharacterCodesFromBinary(String[][] binaryCodes) {
        Integer[][] codes = new Integer[16][4];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                codes[i][j] = Integer.parseInt(binaryCodes[i][j], 2);
            }
        }
        return codes;
    }
}

