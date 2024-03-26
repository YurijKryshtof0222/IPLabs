package Lab2;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(new int[][]{
                {0,	1,	0,	0,	0,	0,	0,	0,	0,	0},
                {1,	0,	0,	0,	1,	0,	1,	1,	0,	0},
                {0,	1,	0,	0,	0,	1,	0,	0,	0,	1},
                {0,	0,	0,	1,	0,	0,	0,	1,	0,	0},
                {0,	1,	0,	0,	0,	0,	0,	0,	0,	0},
                {0,	0,	1,	0,	0,	1,	1,	0,	0,	1}
        });
        Stencil stencil = new Stencil(matrix);

        String textToEncode = "Також_бажаємо_здоров'я_і_щастя!\n" +
                "Щоб_Вас_не_торкнулось_жодне_нещастя!\n" +
                "Бо_люди_веселі_його_проженуть,\n" +
                "І_тільки_здоров'я_з_собою_беруть!,\n" +
                "Криштоф_Юрій_Тарасович__!?!?!?!?!?!?!?!?!?!?!";
        Encoder encoder = new Encoder(stencil, textToEncode);


        Util.printMatrix(stencil.getEncodeMatrix());

        System.out.println("\nKey: " + stencil.key() + "\n");
        encoder.codeInInt();
        encoder.decodeInCode();
    }
}
