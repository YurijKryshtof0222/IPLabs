package Lab3;

public class Main {
    private static int[][] left;
    private static int[][] right;

    public static void main(String[] args) {
        String str = "Коли_є_до_чогось_натхнення,то_кипить_в_руках_будь-яка_робота.!!!";
        TextToEncode textToEncode = new TextToEncode(str);

        Character[][] textAsArray = textToEncode.getTextAsArray();
        Integer[][] characterCodes = textToEncode.getCharacterCodes();
        String[][] binaryCharacterCodes = textToEncode.getBinaryCharacterCodes();

        String initialKey = "1010110100101000";

        Util.printMatrix(textToEncode.getTextAsArray());
        Util.printMatrix(textToEncode.getCharacterCodes());
        Util.printMatrix(textToEncode.getBinaryCharacterCodes());

        initLeftAndRight(binaryCharacterCodes);

        Encoder encoderRound1 = new Encoder(initialKey, left, right);
        Encoder encoderRound2 = new Encoder(
                    encoderRound1.getKeyWithCyclicShiftTo2colsLeft(),
                    encoderRound1.outputLeft(),
                    encoderRound1.outputRight());

        String[][] strEncodedArray = encoderRound2.strCodesArray();
        Integer[][] encodedArray = TextToEncode.getCharacterCodesFromBinary(strEncodedArray);
        String encodeText = TextToEncode.getTextFromCharacterCodes(encodedArray);

        Decoder decoderRound2 = new Decoder(
                encoderRound1.getKeyWithCyclicShiftTo2colsLeft(),
                encoderRound2.outputLeft(),
                encoderRound2.outputRight());
        Decoder decoderRound1 = new Decoder(
                initialKey,
                decoderRound2.outputLeft(),
                decoderRound2.outputRight());

        String[][] strDecodedArray = decoderRound1.strCodesArray();
        Integer[][] decodedArray = TextToEncode.getCharacterCodesFromBinary(strDecodedArray);
        String decodeText = TextToEncode.getTextFromCharacterCodes(decodedArray);

        System.out.println("Encoding round #1\n");
        encoderRound1.printInfo();
        System.out.println("Encoding round #2\n");
        encoderRound2.printInfo();

        System.out.println("Encoded: ");
        Util.printMatrix(strDecodedArray);
        Util.printMatrix(decodedArray);
        System.out.println(encodeText);

        System.out.println("Decoding round #2\n");
        decoderRound2.printInfo();
        System.out.println("Decoding round #1\n");
        decoderRound1.printInfo();

        System.out.println("Decoded: ");
        Util.printMatrix(strDecodedArray);
        Util.printMatrix(decodedArray);
        System.out.println(decodeText);
    }

    private static void initLeftAndRight(String[][] characterCodes) {
        int len = characterCodes.length;

        left = new int[len][len];
        right = new int[len][len];

        for (int i = 0; i < characterCodes.length; i++) {
            int[] l1 = Util.convertBinaryStringToIntArray(characterCodes[i][0]);
            int[] l2 = Util.convertBinaryStringToIntArray(characterCodes[i][1]);
            int[] r1 = Util.convertBinaryStringToIntArray(characterCodes[i][2]);
            int[] r2 = Util.convertBinaryStringToIntArray(characterCodes[i][3]);

            System.arraycopy(l1, 0, left[i], 0, l1.length);
            System.arraycopy(l2, 0, left[i], l2.length, l2.length);
            System.arraycopy(r1, 0, right[i], 0, r1.length);
            System.arraycopy(r2, 0, right[i], r2.length, r2.length);
        }
    }
}
