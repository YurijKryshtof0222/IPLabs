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
        String textToEncode = "Також_бажаємо_здоров'я_і_щастя!¶Щоб_Вас_не_торкнулось_жодне_нещастя!¶Бо_люди_веселі_його_проженуть,¶І_тільки_здоров'я_з_собою_беруть!¶,,Криштоф_Юрій_Тарасович__!!!!!!!!!!!!!!!!!!!!";

        Util.printMatrix(matrix.getMatrix());
        stencil.printEncodeMatrix();

        System.out.println();
//        encoder.printCodeStr();
    }
}
