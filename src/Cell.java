public class Cell {
    private final int i;
    private final int j;

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public String toString() {
        return "Cell[" +
                "i=" + (i+1) +
                ", j=" + (j+1) +
                ']';
    }
}
