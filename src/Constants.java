public class Constants {
    public static final double[][] costs = {
            {221.3,	480.1,	641.6,	939.5,	1156.9},
            {188.3,	366.3,	568.2,	764,	909.9},
            {168.5,	279.4,	404.6,	500.6,	662.8},
            {63.4,	102.9,	157.8,	229,	269.3}
    };

    public static final int[][] folds1 = Util.reversed(new int[][]{
            {3,	3,	4,	5,	5},
            {2,	3,	4,	4,	5},
            {2,	3,	3,	4,	5},
            {1,	2,	3,	4,	4},
            {1,	2,	3,	3,	4},
    });

    public static final int[][] folds2 = Util.reversed(new int[][]{
            {3,	3,	4,	5,	5},
            {2,	3,	4,	4,	5},
            {2,	2,	3,	4,	4},
            {1,	2,	3,	3,	4},
            {1,	2,	2,	3,	3}
    });

    public static final int[][] folds3 = Util.reversed(new int[][]{
            {2,	3,	4,	5,	5},
            {2,	3,	4,	4,	4},
            {1,	2,	3,	3,	4},
            {1,	2,	3,	3,	3},
            {1,	1,	2,	2,	3}
    });

    public static final int[][] folds4 = Util.reversed(new int[][]{
            {2,	3,	4,	4,	5},
            {2,	3,	3,	4,	4},
            {1,	2,	3,	4,	4},
            {1,	2,	2,	3,	3},
            {1,	1,	2,	2,	3}
    });

    public static final int[][] folds5 = Util.reversed(new int[][]{
            {1,	2,	3,	4,	5},
            {1,	2,	3,	4,	4},
            {1,	2,	3,	4,	4},
            {1,	2,	2,	3,	3},
            {1,	1,	1,	2,	2}
    });
}
